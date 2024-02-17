package ru.vsu.cs.daa.engtester;

import ru.vsu.cs.daa.engtester.painters.*;
import ru.vsu.cs.daa.engtester.remade.ComboBoxModelFiles;
import ru.vsu.cs.daa.engtester.utils.UIPreferences;

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
	private JComboBox comboBoxColorTheme;
	private JCheckBox checkBoxCountMistakes;
	private JCheckBox checkBoxTimeMode;
	private JCheckBox checkBoxEndlessMode;
	private JComboBox comboBox2;
	private JButton buttonSetDictionary;
	private JButton buttonAddNew;
	private JTable table1;
	private JLabel labelDictionary;
	private JLabel labelUsedScheme;
	private JLabel labelCountMistakes;
	private JLabel labelTimeMode;
	private JLabel labelEndlessMode;
	private LogicProcessor logicProcessor = new LogicProcessor();
	private UIPreferences preferences;
	private ArrayList<ComponentPainter> painters = new ArrayList<>();
	private Timer timerRefresher;

	public FormMain() throws Exception {
		formInit();
		preferencesInit();
		textFieldAnswerInit();
		comboBoxColorThemeInit();
		buttonRestartInit();
		buttonSkipInit();
		textAreaDefinitionInit();
		timerRefresherInit();
	}

	private void formInit() {
		this.setTitle("English Tester");
		this.setContentPane(panelMain);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					preferences.loadSettingsToFile();
				} catch (Exception exception) {
				}
			}
		});
		this.pack();
	}

	private void timerRefresherInit() {
		timerRefresher = new Timer(500, e -> {
			refreshTextAreaDefinition();
		});
	}

	private void textAreaDefinitionInit() {
		setTextAreaDefinitionTitle("Chosen Dictionary: " + preferences.getSelectedDictionary());
	}

	private void buttonRestartInit() {
		buttonRestart.grabFocus();
		buttonRestart.setText("Start");
		buttonRestart.addActionListener(e -> {
			buttonRestart.setText("Restart");
			logicProcessor.reset();
			refreshTextAreaDefinition();
			progressBar1.setValue(0);
			labelAnswerStatus.setText("Restarted!");
			textFieldAnswer.grabFocus();
			if (!timerRefresher.isRunning()) {
				timerRefresher.start();
			}
		});
	}

	private void buttonSkipInit() {
		buttonSkip.addActionListener(e -> {
			logicProcessor.skipWord();
			refreshTextAreaDefinition();
			labelAnswerStatus.setText("Skipped!");
			textFieldAnswer.grabFocus();
		});
	}

	private void textFieldAnswerInit() {
		textFieldAnswer.setForeground(preferences.textColorSolutions.get(1));
		textFieldAnswer.setText("Type the word here");

		textFieldAnswer.addFocusListener(new FocusListener() {
			private final String defaultText = "Type the word here";

			@Override
			public void focusGained(FocusEvent e) {
				if (textFieldAnswer.getText().equals(defaultText)) {
					textFieldAnswer.setText("");
					textFieldAnswer.setForeground(preferences.textColorSolutions.get(0));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldAnswer.getText().isEmpty()) {
					textFieldAnswer.setForeground(preferences.textColorSolutions.get(1));
					textFieldAnswer.setText(defaultText);
				}
			}
		});
		textFieldAnswer.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkWord(textFieldAnswer.getText());
					textFieldAnswer.setText("");
				}
			}
		});
	}

	private void comboBoxColorThemeInit() {
		ComboBoxModelFiles model = new ComboBoxModelFiles("Settings/ColorSchemes", UIPreferences.EXTENSION_COLORSCHEME);
		model.setSelectedItem(preferences.getSelectedScheme());
		comboBoxColorTheme.setModel(model);
		comboBoxColorTheme.addActionListener(e -> {
			try {
				preferences.changeColorScheme(comboBoxColorTheme.getSelectedItem().toString());
				schemeRefresh();
			} catch (Exception exception) {
			}
		});
	}

	private void preferencesInit() throws Exception {
		preferences = new UIPreferences();

		painters.add(new BgFgPainter(panelMain, 0, 0));
		painters.add(new BgFgPainter(textAreaDefinition, 0, 0));
		painters.add(new BdTlPainter(scrollPaneDefinitionContainer, 0, 0));
		painters.add(new BgFgPainter(labelAnswerStatus, 0, 0));
		painters.add(new TfPainter(textFieldAnswer, 0, 0));
		painters.add(new BgFgPainter(table1, 0, 0));
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

		schemeRefresh();
	}

	private void schemeRefresh() {
		for (ComponentPainter painter : painters) {
			painter.paint(preferences);
		}
	}

	private void checkWord(String word) {
		String correctWord = logicProcessor.getWord(word);
		if (correctWord.equals(word)) {
			labelAnswerStatus.setText("Correct!");
			progressBar1.setValue(logicProcessor.getProgress());
		} else {
			labelAnswerStatus.setText("Incorrect! It was \"" + correctWord + "\"");
		}

		refreshTextAreaDefinition();

		if (logicProcessor.wordsRemaining() == 0) {
			textAreaDefinition.setText("Congratulations! You have completed the test!");
			labelAnswerStatus.setText("Nice!");
		}
	}

	private void refreshTextAreaDefinition() {
		if (logicProcessor.wordsRemaining() > 0) {
			textAreaDefinition.setText(logicProcessor.getDefinition());
		}

		StringBuilder stringBuilder = new StringBuilder();
		if (!checkBoxEndlessMode.isSelected()) {
			stringBuilder.append("Words remaining: ");
			stringBuilder.append(logicProcessor.wordsRemaining());
		} else {
			stringBuilder.append("Words are infinite");
		}
		if (checkBoxTimeMode.isSelected()) {
			stringBuilder.append("  |  Time elapsed: ");
			stringBuilder.append(logicProcessor.getTime());
		}
		if (checkBoxCountMistakes.isSelected()) {
			stringBuilder.append("  |  Mistakes: ");
			stringBuilder.append(logicProcessor.getMistakes());
		}

		setTextAreaDefinitionTitle(stringBuilder.toString());
	}

	private void setTextAreaDefinitionTitle(String title) {
		((TitledBorder) (scrollPaneDefinitionContainer.getBorder())).setTitle(title);
		scrollPaneDefinitionContainer.repaint();
	}
}
