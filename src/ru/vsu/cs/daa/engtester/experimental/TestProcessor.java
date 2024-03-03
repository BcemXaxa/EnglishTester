package ru.vsu.cs.daa.engtester.experimental;

import ru.vsu.cs.daa.engtester.FormMain;
import ru.vsu.cs.daa.engtester.experimental.dictionaries.DictionariesManager;
import ru.vsu.cs.daa.engtester.experimental.dictionaries.Dictionary;
import ru.vsu.cs.daa.engtester.experimental.settings.SettingsProcessor;
import ru.vsu.cs.daa.engtester.experimental.utils.Stopwatch;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class TestProcessor {
	private static JProgressBar progressBar;
	private static JButton buttonStart;
	private static JButton buttonSkip;
	private static JTextArea textAreaDefinition;
	private static JScrollPane scrollPaneDefinition;
	private static JTextField textFieldAnswer;
	private static JLabel labelAnswerStatus;

	public static void init(JProgressBar progressBar,
							JButton buttonStart,
							JButton buttonSkip,
							JTextArea textAreaDefinition,
							JScrollPane scrollPaneDefinition,
							JTextField textFieldAnswer,
							JLabel labelAnswerStatus) {

		TestProcessor.progressBar = progressBar;
		TestProcessor.buttonStart = buttonStart;
		TestProcessor.buttonSkip = buttonSkip;
		TestProcessor.textAreaDefinition = textAreaDefinition;
		TestProcessor.scrollPaneDefinition = scrollPaneDefinition;
		TestProcessor.textFieldAnswer = textFieldAnswer;
		TestProcessor.labelAnswerStatus = labelAnswerStatus;

		buttonStart.addActionListener(event -> {
			reset();
			start();
		});
		buttonSkip.addActionListener(event -> {
			nextWord();
		});
		textFieldAnswer.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					guess(textFieldAnswer.getText());
					textFieldAnswer.setText("");
				}
			}
		});
	}

	private static Dictionary currentDictionary;
	private static int maxWords;
	private static int curWord;
	private static int curDef;
	private static int mistakes;
	private static final Stopwatch stopwatch = new Stopwatch();
	private static boolean running = false;

	public static boolean isRunning() {
		return running;
	}

	public static String getDictionaryName() {
		return currentDictionary == null ? "None" : currentDictionary.name;
	}

	public static void reset() {
		running = false;

		currentDictionary = DictionariesManager.getChosenDictionary();
		maxWords = currentDictionary != null ? currentDictionary.size() : 0;
		mistakes = 0;
		curWord = -1;
		curDef = -1;

		buttonStart.setText("Start");
	}

	private static void start() {
		if (currentDictionary != null) {
			buttonStart.setText("Restart");

			running = true;
			stopwatch.start();
			nextWord();
		}
	}

	private static void guess(String guess) {
		if (currentDictionary.size() <= 0) {
			return;
		}
		String answer = currentDictionary.get(curWord).word;
		if (guess.equals(answer)) {
			currentDictionary.removeWord(curWord, curDef);
			labelAnswerStatus.setText("Correct!");
			curWord = -1;
			curDef = -1;
		} else {
			mistakes++;
			labelAnswerStatus.setText("Incorrect! It was \"" + answer + "\"");
		}
		nextWord();
	}

	private static void nextWord() {
		if (!isRunning()) {
			return;
		}
		if (currentDictionary.size() <= 0) {
			finish();
		} else {
			Random rand = new Random();
			int newWord, newDef;
			do {
				newWord = rand.nextInt(currentDictionary.size());
				newDef = rand.nextInt(currentDictionary.get(newWord).definitions.size());
			}
			while (newWord == curWord && newDef == curDef &&
					(currentDictionary.size() > 1 || currentDictionary.get(0).definitions.size() > 1));

			curWord = newWord;
			curDef = newDef;

			textAreaDefinition.setText(currentDictionary.get(curWord).definitions.get(curDef));
		}
		progressBar.setValue(100 - Math.round(currentDictionary.size() * 100f / maxWords));
	}

	private static void finish() {
		stopwatch.stop();
		textAreaDefinition.setText("Congratulations! You have completed the test!");
		labelAnswerStatus.setText("Nice!");
	}

	public static int getMistakes() {
		return mistakes;
	}

	public static int wordsRemaining() {
		return currentDictionary.size();
	}

	public static String getTime() {
		int min = (int) stopwatch.getTime(Stopwatch.Unit.MINUTES);
		int sec = ((int) stopwatch.getTime(Stopwatch.Unit.SECONDS)) % 60;
		StringBuilder time = new StringBuilder();
		time.append(min);
		time.append(':');
		if (sec < 10) {
			time.append('0');
		}
		time.append(sec);
		return time.toString();
	}

	public static void refreshTextAreaDefinition() {
		if (TestProcessor.isRunning()) {
			StringBuilder stringBuilder = new StringBuilder();
			if (!SettingsProcessor.isEndlessMode()) {
				stringBuilder.append("Words remaining: ");
				stringBuilder.append(TestProcessor.wordsRemaining());
			} else {
				stringBuilder.append("Endless mode");
			}
			if (SettingsProcessor.isTimeMode()) {
				stringBuilder.append("  |  Time elapsed: ");
				stringBuilder.append(TestProcessor.getTime());
			}
			if (SettingsProcessor.isCountMistakes()) {
				stringBuilder.append("  |  Mistakes: ");
				stringBuilder.append(TestProcessor.getMistakes());
			}
			setTextAreaDefinitionTitle(stringBuilder.toString());
		} else {
			setTextAreaDefinitionTitle("Current dictionary: " + TestProcessor.getDictionaryName());
		}
	}

	public static void setTextAreaDefinitionTitle(String title) {
		((TitledBorder) (scrollPaneDefinition.getBorder())).setTitle(title);
		scrollPaneDefinition.repaint();
	}
}
