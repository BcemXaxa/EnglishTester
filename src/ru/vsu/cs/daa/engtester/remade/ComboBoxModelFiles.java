package ru.vsu.cs.daa.engtester.remade;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComboBoxModelFiles implements ComboBoxModel<String> {
	private final String directory;
	private final String extension;
	public ArrayList<String> files;

	public ComboBoxModelFiles(String directory, String extension) {
		this.directory = directory;
		this.extension = extension;
		refresh();
	}

	public void refresh() {
		files = new ArrayList<>();
		File currentDirectory = Path.of(directory).toAbsolutePath().toFile();
		File[] fileList = currentDirectory.listFiles();
		if (fileList != null) {
			Pattern pattern = Pattern.compile("(.+)(\\..+)");
			Matcher matcher;
			for (File file : fileList) {
				if (file.isFile()) {
					matcher = pattern.matcher(file.getName());
					if (matcher.matches() && matcher.group(2).equals(extension)) {
						files.add(matcher.group(1));
					}
				}
			}
		}
	}

	private String selectedItem = null;
	@Override
	public void setSelectedItem(Object anItem) {
		selectedItem = anItem.toString();
	}

	@Override
	public Object getSelectedItem() {
		return selectedItem;
	}

	@Override
	public int getSize() {
		return files.size();
	}

	@Override
	public String getElementAt(int index) {
		return files.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {

	}

	@Override
	public void removeListDataListener(ListDataListener l) {

	}
}
