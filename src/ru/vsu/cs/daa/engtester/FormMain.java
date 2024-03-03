package ru.vsu.cs.daa.engtester;

import ru.vsu.cs.daa.engtester.experimental.TestProcessor;
import ru.vsu.cs.daa.engtester.experimental.dictionaries.DictionariesManager;
import ru.vsu.cs.daa.engtester.experimental.settings.SettingsProcessor;
import ru.vsu.cs.daa.engtester.painters.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;
import java.util.ArrayList;

public class FormMain extends JFrame {
	private JTextField textFieldAnswer;
	private JPanel panelMain;
	private JTabbedPane tabbedPane1;
	private JProgressBar progressBar1;
	private JScrollPane scrollPaneDefinitionContainer;
	private JTextArea textAreaDefinition;
	private JPanel panelTest;
	private JPanel panelSettings;
	private JLabel labelAnswerStatus;
	private JButton buttonRestart;
	private JButton buttonSkip;
	private JPanel panelDictionaries;
	private JComboBox comboBoxColorSchemes;
	private JCheckBox checkBoxCountMistakes;
	private JCheckBox checkBoxTimeMode;
	private JCheckBox checkBoxEndlessMode;
	private JComboBox comboBoxDictionaries;
	private JButton buttonSetDictionary;
	private JButton buttonAddNew;
	private JLabel labelDictionary;
	private JLabel labelUsedScheme;
	private JLabel labelCountMistakes;
	private JLabel labelTimeMode;
	private JLabel labelEndlessMode;
	private ArrayList<ComponentPainter> painters = new ArrayList<>();
	private Timer timerRefresher;

	public FormMain() throws Exception {
		DictionariesManager.init(comboBoxDictionaries, buttonSetDictionary, buttonAddNew/*panelDictionaryView*/);
		SettingsProcessor.init(comboBoxColorSchemes, checkBoxCountMistakes, checkBoxTimeMode, checkBoxEndlessMode);
		TestProcessor.init(progressBar1, buttonRestart, buttonSkip, textAreaDefinition, scrollPaneDefinitionContainer,
						   textFieldAnswer, labelAnswerStatus);

		formInit();
		//preferencesInit();
		textFieldAnswerInit();
		timerRefresherInit();
	}

	private void formInit() {
		this.setTitle("English Tester");
		this.setContentPane(panelMain);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {

				} catch (Exception exception) {
				}
			}
		});
		this.pack();
	}

	private void timerRefresherInit() {
		TestProcessor.refreshTextAreaDefinition();
		timerRefresher = new Timer(500, e -> {
			TestProcessor.refreshTextAreaDefinition();
		});
		timerRefresher.start();
	}

	private void textFieldAnswerInit() {
		textFieldAnswer.setText("Type the word here");

		textFieldAnswer.addFocusListener(new FocusListener() {
			private final String defaultText = "Type the word here";

			@Override
			public void focusGained(FocusEvent e) {
				if (textFieldAnswer.getText().equals(defaultText)) {
					textFieldAnswer.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldAnswer.getText().isEmpty()) {
					textFieldAnswer.setText(defaultText);
				}
			}
		});
	}

	private void preferencesInit() throws Exception {
		painters.add(new BgFgPainter(panelMain, 0, 0));
		painters.add(new BgFgPainter(textAreaDefinition, 0, 0));
		painters.add(new BdTlPainter(scrollPaneDefinitionContainer, 0, 0));
		painters.add(new BgFgPainter(labelAnswerStatus, 0, 0));
		painters.add(new TfPainter(textFieldAnswer, 0, 0));
		painters.add(new BgFgPainter(labelEndlessMode, 0, 0));
		painters.add(new BgFgPainter(labelTimeMode, 0, 0));
		painters.add(new BgFgPainter(labelUsedScheme, 0, 0));
		painters.add(new BgFgPainter(labelCountMistakes, 0, 0));
		painters.add(new BgFgPainter(labelDictionary, 0, 0));
		painters.add(new BgFgPainter(tabbedPane1, 1, 0));
		painters.add(new BgFgPainter(panelTest, 1, 0));
		painters.add(new BgFgPainter(panelSettings, 1, 0));
		painters.add(new BgFgPainter(panelDictionaries, 1, 0));
		painters.add(new BgFgPainter(buttonRestart, 1, 0));
		painters.add(new BgFgPainter(buttonSkip, 1, 0));
		painters.add(new PbPainter(progressBar1, 0, 0));
		painters.add(new BgFgPainter(buttonAddNew, 1, 0));
		painters.add(new BgFgPainter(buttonSetDictionary, 1, 0));

	}


}
