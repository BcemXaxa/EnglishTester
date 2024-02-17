package ru.vsu.cs.daa.engtester.utils;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileManager {
	private final File file;

	public FileManager(String pathStr) throws Exception {
		Path path = Path.of(pathStr).toAbsolutePath();
		if (Files.exists(path)) {
			file = path.toFile();
		} else {
			throw new Exception("Corrupted path");
		}
	}

	public File getFile() {
		return file;
	}

	public Scanner getFileInput() throws Exception {
		if (file.canRead()) {
			return new Scanner(file);
		} else {
			throw new Exception("Non-readable file");
		}
	}

	public PrintStream getFileOutput() throws Exception {
		if (file.canWrite()) {
			return new PrintStream(file);
		} else {
			throw new Exception("Non-writable file");
		}
	}

	public File[] getSubfiles() {
		return file.listFiles();
	}
}
