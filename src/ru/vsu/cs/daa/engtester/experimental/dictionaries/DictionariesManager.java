package ru.vsu.cs.daa.engtester.experimental.dictionaries;

import ru.vsu.cs.daa.engtester.experimental.TestProcessor;
import ru.vsu.cs.daa.engtester.experimental.utils.Configurable;
import ru.vsu.cs.daa.engtester.experimental.settings.Config;
import ru.vsu.cs.daa.engtester.remade.ComboBoxModelFiles;

import javax.swing.*;
import java.io.File;
import java.nio.file.Path;

public class DictionariesManager implements Configurable {
	private static final String DICTIONARIES_DIRECTORY = "./Dictionaries/";

	private static JComboBox<String> comboBoxDictionaries;
	private static ComboBoxModelFiles comboBoxModelDictionaries;
	private static JButton buttonChoose;
	private static JButton buttonNewDictionary;
	private static JPanel panelDictionaryView;

	public static void init(JComboBox<String> comboBoxDictionaries,
							JButton buttonChoose,
							JButton buttonNewDictionary/*,
							JPanel panelDictionaryView*/) {

		DictionariesManager.comboBoxDictionaries = comboBoxDictionaries;
		DictionariesManager.buttonChoose = buttonChoose;
		DictionariesManager.buttonNewDictionary = buttonNewDictionary;
		//DictionariesManager.panelDictionaryView = panelDictionaryView;

		comboBoxModelDictionaries = new ComboBoxModelFiles("No dictionary");
		comboBoxDictionaries.setModel(comboBoxModelDictionaries);
		comboBoxModelDictionaries.refresh(DictionaryFileIO.getDictionaryFiles(Path.of(DICTIONARIES_DIRECTORY)));

		buttonChoose.addActionListener(event -> {
			TestProcessor.reset();
		});
	}

	public static Dictionary getChosenDictionary() {
		try {
			return DictionaryFileIO.read(comboBoxModelDictionaries.getSelectedFile());
		} catch (Exception exception) {
			System.err.println("file reading failed");
			return null;
		}
	}

	public static void updateConfig(Config config) {
		try {
			config.dictionary = comboBoxDictionaries.getSelectedItem().toString();
		} catch (Exception exception) {
			config.dictionary = "none";
		}
	}
}
