package ru.vsu.cs.daa.engtester.painters;

import ru.vsu.cs.daa.engtester.experimental.settings.ColorScheme;

import javax.swing.*;
import java.awt.*;

public class TfPainter extends BgFgPainter {
	public TfPainter(Component component, int bgScheme, int fgScheme) {
		super(component, bgScheme, fgScheme);
	}

	@Override
	public void paint(ColorScheme scheme) {
		super.paint(scheme);
		((JTextField)component).setCaretColor(scheme.textColorSolutions.get(fgScheme));
	}
}
