package ru.vsu.cs.daa.engtester.painters;

import ru.vsu.cs.daa.engtester.utils.UIPreferences;

import javax.swing.*;
import java.awt.*;

public class TfPainter extends BgFgPainter {
	public TfPainter(Component component, int bgScheme, int fgScheme) {
		super(component, bgScheme, fgScheme);
	}

	@Override
	public void paint(UIPreferences preferences) {
		super.paint(preferences);
		((JTextField)component).setCaretColor(preferences.textColorSolutions.get(fgScheme));
	}
}
