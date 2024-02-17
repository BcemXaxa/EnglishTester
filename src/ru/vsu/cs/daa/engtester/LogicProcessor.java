package ru.vsu.cs.daa.engtester;

import ru.vsu.cs.daa.engtester.utils.Dictionary;
import ru.vsu.cs.daa.engtester.utils.Timer;

import java.util.Random;

public class LogicProcessor {
	private final Random random = new Random();
	private String dictionaryName;
	private Dictionary currentDictionary = new Dictionary(1);
	private final Timer timer = new Timer();
	private int maxWords;
	private int currentWord;
	private int mistakes;

	public void reset() {
		currentDictionary = new Dictionary();
		maxWords = currentDictionary.words.size();
		nextWord();
		mistakes = 0;

		timer.start();
	}

	public String getWord(String guess) {
		String word = currentDictionary.words.get(currentWord).word;
		if (word.equals(guess)) {
			currentDictionary.words.remove(currentWord);
			nextWord();
		} else {
			mistakes++;
			nextWord();
		}
		return word;
	}

	public String getDefinition() {
		return currentDictionary.words.get(currentWord).definition;
	}

	public int wordsRemaining() {
		return currentDictionary.words.size();
	}

	public String getTime() {
		if (!currentDictionary.words.isEmpty()) {
			timer.end();
		}
		int min = (int) timer.durationMinutes();
		int sec = (int) ((timer.durationMinutes() - min) * 60);
		StringBuilder time = new StringBuilder();
		time.append(min);
		time.append(':');
		if (sec < 10) {
			time.append('0');
		}
		time.append(sec);
		return time.toString();
	}

	public int getMistakes() {
		return mistakes;
	}

	public int getProgress() {
		return 100 - Math.round(currentDictionary.words.size() * 100f / maxWords);
	}


	private void nextWord() {
		if (currentDictionary.words.isEmpty()) {
			currentWord = -1;
		} else {
			currentWord = random.nextInt(0, currentDictionary.words.size());
		}
	}

	public void skipWord() {
		if (currentDictionary.words.size() > 1) {
			int nextWord;
			while (true) {
				nextWord = random.nextInt(0, currentDictionary.words.size());
				if (nextWord != currentWord) {
					currentWord = nextWord;
					return;
				}
			}
		}
	}
}
