package ru.vsu.cs.daa.engtester.remade;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.io.File;
import java.util.Arrays;

public class ComboBoxModelFiles implements ComboBoxModel<String> {
	public File[] files;
	public String placeholder = "None";

	public ComboBoxModelFiles() {
	}
	public ComboBoxModelFiles(String placeholder) {
		this.placeholder = placeholder;
	}

	public void refresh(File[] files) {
		this.files = files;
	}

	private File selectedItem = null;

	@Override
	public void setSelectedItem(Object anItem) {
		for (File file : files) {
			if (file.getName().equals(anItem.toString())) {
				selectedItem = file;
				return;
			}
		}
		selectedItem = null;
	}

	@Override
	public Object getSelectedItem() {
		return selectedItem == null ? placeholder : selectedItem.getName();
	}
	public File getSelectedFile() {
		return selectedItem;
	}

	@Override
	public int getSize() {
		return files.length;
	}

	@Override
	public String getElementAt(int index) {
		return files[index].getName();
	}

	@Override
	public void addListDataListener(ListDataListener l) {

	}

	@Override
	public void removeListDataListener(ListDataListener l) {

	}
}
