package ru.vsu.cs.daa.engtester.painters;

import ru.vsu.cs.daa.engtester.utils.UIPreferences;

import java.awt.*;

public class PbPainter extends BgFgPainter {
	public PbPainter(Component component, int bgScheme, int fgScheme) {
		super(component, bgScheme, fgScheme);
	}

	@Override
	public void paint(UIPreferences preferences) {
		component.setBackground(preferences.backgroundSolutions.get(bgScheme));
		component.setForeground(preferences.optionalSolutions.get(fgScheme));
	}
}
