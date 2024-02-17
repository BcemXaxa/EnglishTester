package ru.vsu.cs.daa.engtester;


import java.util.Locale;

public class Main {
	public static void main(String[] args) {
		try {
			Locale.setDefault(Locale.ROOT);

			FormMain formMain = new FormMain();
			java.awt.EventQueue.invokeLater(() -> formMain.setVisible(true));

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
}