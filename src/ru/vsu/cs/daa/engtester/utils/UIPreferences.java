package ru.vsu.cs.daa.engtester.utils;

import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UIPreferences {
	public static final String EXTENSION_DICTIONARY = ".dic";
	public static final String EXTENSION_PREFERENCES = ".pref";
	public static final String EXTENSION_COLORSCHEME = ".schm";
	public static final Pattern COLOR_PATTERN = Pattern.compile("\\[(\\d+),(\\d+),(\\d+)]");
	private String user = "user";
	private String selectedDictionary;
	private boolean countMistakes;
	private boolean timeMode;
	private boolean endlessMode;
	private String selectedScheme;
	public ArrayList<Color> backgroundSolutions = new ArrayList<>();
	public ArrayList<Color> textColorSolutions = new ArrayList<>();
	public ArrayList<Color> optionalSolutions = new ArrayList<>();

	public UIPreferences() throws Exception {
		loadFromFile(user);
	}

	public void loadFromFile(String user) throws Exception {
		FileManager fileManager = new FileManager("Settings/" + user + EXTENSION_PREFERENCES);
		Scanner scanner = fileManager.getFileInput();

		if (hasNextProperty(scanner, "Settings")) {
			if (hasNextProperty(scanner, "ColorScheme:")) {
				selectedScheme = scanner.next();
			}
			if (hasNextProperty(scanner, "CountMistakes:")) {
				countMistakes = scanner.nextBoolean();
			}
			if (hasNextProperty(scanner, "TimeMode:")) {
				timeMode = scanner.nextBoolean();
			}
			if (hasNextProperty(scanner, "EndlessMode:")) {
				endlessMode = scanner.nextBoolean();
			}
		}
		if (hasNextProperty(scanner, "Dictionaries")) {
			if (hasNextProperty(scanner, "Dictionary:")) {
				selectedDictionary = scanner.next();
			}
		}
		loadSchemeFromFile();
	}

	public void loadSettingsToFile() throws Exception {
		loadSettingsToFile(user);
	}

	public void loadSettingsToFile(String user) throws Exception {
		FileManager fileManager = new FileManager("Settings/" + user + EXTENSION_PREFERENCES);
		PrintStream output = fileManager.getFileOutput();

		output.println("Settings");
		output.printf("ColorScheme: %s%n", selectedScheme);
		output.printf("CountMistakes: %s%n", countMistakes);
		output.printf("TimeMode: %s%n", timeMode);
		output.printf("EndlessMode: %s%n", endlessMode);

		output.println("Dictionaries");
		output.printf("Dictionary: %s", selectedDictionary);

		output.close();
	}

	public void loadSchemeFromFile() throws Exception {
		FileManager fileManager = new FileManager("Settings/ColorSchemes/" + selectedScheme + EXTENSION_COLORSCHEME);
		Scanner scanner = fileManager.getFileInput();
		Matcher matcher;
		if (hasNextProperty(scanner, "BGSolutions:")) {
			backgroundSolutions.clear();
			while (scanner.hasNext(COLOR_PATTERN)) {
				matcher = COLOR_PATTERN.matcher(scanner.next());
				backgroundSolutions.add(parseColor(matcher));
			}
		}
		if (hasNextProperty(scanner, "FGSolutions:")) {
			textColorSolutions.clear();
			while (scanner.hasNext(COLOR_PATTERN)) {
				matcher = COLOR_PATTERN.matcher(scanner.next());
				textColorSolutions.add(parseColor(matcher));
			}
		}
		if (hasNextProperty(scanner, "OpSolutions:")) {
			optionalSolutions.clear();
			while (scanner.hasNext(COLOR_PATTERN)) {
				matcher = COLOR_PATTERN.matcher(scanner.next());
				optionalSolutions.add(parseColor(matcher));
			}
		}
	}

	private Color parseColor(Matcher matcher) throws Exception {
		if (matcher.matches()) {
			int r = Integer.parseInt(matcher.group(1));
			int g = Integer.parseInt(matcher.group(2));
			int b = Integer.parseInt(matcher.group(3));
			return new Color(r, g, b);
		} else {
			throw new Exception("Color parsing failed");
		}
	}

	private boolean hasNextProperty(Scanner scanner, String property) throws Exception {
		if (scanner.next().equals(property)) {
			return true;
		} else {
			throw new Exception("Corrupted or outdated file");
		}
	}

	public String getSelectedScheme() {
		return selectedScheme;
	}

	public void changeColorScheme(String colorScheme) throws Exception {
		selectedScheme = colorScheme;
		loadSchemeFromFile();
	}

	public String getSelectedDictionary() {
		return selectedDictionary;
	}
}
