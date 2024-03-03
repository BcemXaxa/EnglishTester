package ru.vsu.cs.daa.engtester.experimental.settings;

import ru.vsu.cs.daa.engtester.experimental.utils.FileUtils;
import ru.vsu.cs.daa.engtester.utils.FileManager;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorSchemeFileIO {

	public static final String SCHEME_EXTENSION = ".schm";
	public static final Pattern COLOR_PATTERN = Pattern.compile("\\[(\\d+),(\\d+),(\\d+)]");

	public static File[] getSchemeFiles(Path directory) {
		return FileUtils.getFilesWithExtension(directory, SCHEME_EXTENSION);
	}

	public static ColorScheme read(File file) throws Exception {
		Scanner scanner = new Scanner(file);
		ColorScheme scheme = new ColorScheme(FileUtils.nameOffExtension(file, SCHEME_EXTENSION));
		Matcher matcher;
		if (hasNextProperty(scanner, "BGSolutions:")) {
			while (scanner.hasNext(COLOR_PATTERN)) {
				matcher = COLOR_PATTERN.matcher(scanner.next());
				scheme.backgroundSolutions.add(parseColor(matcher));
			}
		}
		if (hasNextProperty(scanner, "FGSolutions:")) {
			while (scanner.hasNext(COLOR_PATTERN)) {
				matcher = COLOR_PATTERN.matcher(scanner.next());
				scheme.textColorSolutions.add(parseColor(matcher));
			}
		}
		if (hasNextProperty(scanner, "OpSolutions:")) {
			while (scanner.hasNext(COLOR_PATTERN)) {
				matcher = COLOR_PATTERN.matcher(scanner.next());
				scheme.optionalSolutions.add(parseColor(matcher));
			}
		}
		return scheme;
	}

	private static Color parseColor(Matcher matcher) throws Exception {
		if (matcher.matches()) {
			int r = Integer.parseInt(matcher.group(1));
			int g = Integer.parseInt(matcher.group(2));
			int b = Integer.parseInt(matcher.group(3));
			return new Color(r, g, b);
		} else {
			throw new Exception("Color parsing failed");
		}
	}

	private static boolean hasNextProperty(Scanner scanner, String property) throws Exception {
		if (scanner.next().equals(property)) {
			return true;
		} else {
			throw new Exception("Corrupted or outdated file");
		}
	}
}
