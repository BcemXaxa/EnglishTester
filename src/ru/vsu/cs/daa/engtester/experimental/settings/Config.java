package ru.vsu.cs.daa.engtester.experimental.settings;

import ru.vsu.cs.daa.engtester.experimental.dictionaries.DictionariesManager;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;

public class Config {
	public static final String CONFIG_EXTENSION = ".cfg";

	public String colorScheme;
	public boolean countMistakes;
	public boolean timeMode;
	public boolean endlessMode;
	public String dictionary;

	public Config(File file) {

	}

	public Config() {
		DictionariesManager.updateConfig(this);
		SettingsProcessor.updateConfig(this);
	}

	public void write(Path path) throws Exception {
		if (!path.endsWith(CONFIG_EXTENSION)) {
			throw new Exception("incorrect file extension");
		}

		File file = path.toFile();
		file.createNewFile();

		PrintWriter printWriter = new PrintWriter(file);

		printWriter.printf("ColorScheme: %s%n", colorScheme);
		printWriter.printf("CountMistakes: %s%n", countMistakes);
		printWriter.printf("TimeMode: %s%n", timeMode);
		printWriter.printf("EndlessMode: %s%n", endlessMode);
		printWriter.printf("Dictionary: %s%n", dictionary);

		printWriter.close();
	}
}
