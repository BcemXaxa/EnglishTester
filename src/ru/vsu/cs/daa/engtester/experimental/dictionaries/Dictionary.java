package ru.vsu.cs.daa.engtester.experimental.dictionaries;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	public String name;
	public final List<Word> container = new ArrayList<>();

	public Dictionary() {
		name = "Unknown";
	}

	public Dictionary(String name) {
		this.name = name;
	}

	public Dictionary(Dictionary other) {
		this.name = other.name;
		this.container.addAll(other.container);
	}


	public void addWord(Word word) {
		container.add(word);
	}

	public void addWord(String word, String definition) {
		container.add(new Word(word, definition));
	}

	public void addWord(String word, List<String> definitions) {
		container.add(new Word(word, definitions));
	}


	public boolean removeWord(String word) {
		for (int i = 0; i < container.size(); i++) {
			if (word.equals(container.get(i).word)) {
				container.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean removeWord(int wordIndex) {
		if (0 <= wordIndex && wordIndex < container.size()) {
			container.remove(wordIndex);
			return true;
		}
		return false;
	}

	public boolean removeWord(String word, int definitionIndex) {
		for (Word value : container) {
			if (word.equals(value.word)) {

				boolean result = value.removeDefinitions(definitionIndex);
				if (value.definitions.isEmpty()) {
					container.remove(value);
					return true;
				}

				return result;
			}
		}
		return false;
	}

	public boolean removeWord(int wordIndex, int definitionIndex) {
		if (0 <= wordIndex && wordIndex < container.size()) {

			Word word = container.get(wordIndex);
			boolean result = word.removeDefinitions(definitionIndex);

			if (word.definitions.isEmpty()) {
				container.remove(wordIndex);
				return true;
			}
			return result;
		}
		return false;
	}

	public int size() {
		return container.size();
	}

	public Word get(int index) {
		return container.get(index);
	}
}
