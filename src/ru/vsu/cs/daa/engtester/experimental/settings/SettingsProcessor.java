package ru.vsu.cs.daa.engtester.experimental.settings;

import ru.vsu.cs.daa.engtester.experimental.dictionaries.DictionaryFileIO;
import ru.vsu.cs.daa.engtester.experimental.utils.Configurable;
import ru.vsu.cs.daa.engtester.remade.ComboBoxModelFiles;

import javax.swing.*;
import java.io.File;
import java.nio.file.Path;

public class SettingsProcessor implements Configurable {
	private static final String SETTINGS_DIRECTORY = "./Settings/";
	private static final String SCHEMES_DIRECTORY = "./Settings/ColorSchemes/";

	private static JComboBox<String> comboBoxUsedScheme;
	private static ComboBoxModelFiles comboBoxModelSchemes;
	private static JCheckBox checkBoxCountMistakes;
	private static JCheckBox checkBoxTimeMode;
	private static JCheckBox checkBoxEndlessMode;

	public static void init(JComboBox<String> comboBoxUsedScheme,
							JCheckBox checkBoxCountMistakes,
							JCheckBox checkBoxTimeMode,
							JCheckBox checkBoxEndlessMode) {

		SettingsProcessor.comboBoxUsedScheme = comboBoxUsedScheme;
		SettingsProcessor.checkBoxCountMistakes = checkBoxCountMistakes;
		SettingsProcessor.checkBoxTimeMode = checkBoxTimeMode;
		SettingsProcessor.checkBoxEndlessMode = checkBoxEndlessMode;

		comboBoxModelSchemes = new ComboBoxModelFiles("No scheme");
		comboBoxUsedScheme.setModel(comboBoxModelSchemes);
		comboBoxModelSchemes.refresh(DictionaryFileIO.getDictionaryFiles(Path.of(SCHEMES_DIRECTORY)));
	}

	public static void updateConfig(Config config) {
		config.colorScheme = comboBoxUsedScheme.getSelectedItem().toString();
		config.countMistakes = checkBoxCountMistakes.isSelected();
		config.timeMode = checkBoxTimeMode.isSelected();
		config.endlessMode = checkBoxEndlessMode.isSelected();
	}

	public static boolean isCountMistakes() {
		return checkBoxCountMistakes.isSelected();
	}

	public static boolean isTimeMode() {
		return checkBoxTimeMode.isSelected();
	}

	public static boolean isEndlessMode() {
		return checkBoxEndlessMode.isSelected();
	}
}
