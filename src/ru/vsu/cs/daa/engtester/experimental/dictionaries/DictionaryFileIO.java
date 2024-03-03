package ru.vsu.cs.daa.engtester.experimental.dictionaries;

import ru.vsu.cs.daa.engtester.experimental.utils.FileUtils;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryFileIO {
	public static final String DICTIONARY_EXTENSION = ".dic";
	public static final Pattern WORD_PATTERN = Pattern.compile(">\\s*(.*)");

	public static File[] getDictionaryFiles(Path directory) {
		return FileUtils.getFilesWithExtension(directory, DICTIONARY_EXTENSION);
	}

	public static Dictionary read(File file) throws Exception {
		try (Scanner scanner = new Scanner(file)) {

			String name = FileUtils.nameOffExtension(file, DICTIONARY_EXTENSION);
			Dictionary dictionary = new Dictionary(name);
			String word = null;
			List<String> defs = new ArrayList<>();

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				Matcher wordMatcher = WORD_PATTERN.matcher(line);

				if (wordMatcher.matches()) {
					if (word != null && (!defs.isEmpty() || !scanner.hasNextLine())) {
						dictionary.addWord(word, defs);
					}
					word = wordMatcher.group(1);
					defs = new ArrayList<>();

				} else {
					defs.add(line);
				}
			}
			if (word != null && !defs.isEmpty()) {
				dictionary.addWord(word, defs);
			}
			return dictionary;
		}
	}

	public static void write(Dictionary dictionary, Path path) throws Exception {
		if (!path.endsWith(DICTIONARY_EXTENSION)) {
			throw new Exception("incorrect file extension");
		}

		File file = path.toFile();
		file.createNewFile();

		PrintWriter printWriter = new PrintWriter(file);

		for (Word word : dictionary.container) {
			printWriter.printf(">%s%n", word.word);
			for (String def : word.definitions) {
				printWriter.println(def);
			}
		}

		printWriter.close();
	}
}
