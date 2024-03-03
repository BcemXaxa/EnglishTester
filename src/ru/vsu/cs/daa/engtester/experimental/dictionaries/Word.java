package ru.vsu.cs.daa.engtester.experimental.dictionaries;

import java.util.ArrayList;
import java.util.List;

public class Word {
	public final String word;
	public final List<String> definitions = new ArrayList<>();

	public Word(String word, String definition) {
		this.word = word;
		addDefinitions(definition);
	}

	public Word(String word, List<String> definitions) {
		this.word = word;
		addDefinitions(definitions);
	}

	public void addDefinitions(List<String> definitions) {
		this.definitions.addAll(definitions);
	}

	public void addDefinitions(String definition) {
		this.definitions.add(definition);
	}

	public boolean removeDefinitions(String definition) {
		return definitions.remove(definition);
	}

	public boolean removeDefinitions(int definitionIndex) {
		if (0 <= definitionIndex && definitionIndex < definitions.size()) {
			definitions.remove(definitionIndex);
			return true;
		}
		return false;
	}
}