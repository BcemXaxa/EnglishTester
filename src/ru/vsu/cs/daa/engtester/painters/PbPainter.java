package ru.vsu.cs.daa.engtester.painters;

import ru.vsu.cs.daa.engtester.experimental.settings.ColorScheme;

import java.awt.*;

public class PbPainter extends BgFgPainter {
	public PbPainter(Component component, int bgScheme, int fgScheme) {
		super(component, bgScheme, fgScheme);
	}

	@Override
	public void paint(ColorScheme scheme) {
		component.setBackground(scheme.backgroundSolutions.get(bgScheme));
		component.setForeground(scheme.optionalSolutions.get(fgScheme));
	}
}
