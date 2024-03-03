package ru.vsu.cs.daa.engtester.experimental.utils;

import java.io.File;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {
	public static String nameOffExtension(File file, String extension) {
		String name = file.getName();
		Matcher matcher = Pattern.compile("(.+)" + extension).matcher(name);
		if (matcher.matches()) {
			name = matcher.group(1);
		}
		return name;
	}

	public static File[] getFilesWithExtension(Path directory, String extension) {
		return directory.toFile().listFiles(file -> file.isFile() && file.getName().endsWith(extension));
	}
}
