package ru.vsu.cs.daa.engtester.painters;

import ru.vsu.cs.daa.engtester.experimental.settings.ColorScheme;

import java.awt.*;

public class BgFgPainter implements ComponentPainter {
	protected Component component;
	public int bgScheme;
	public int fgScheme;

	public BgFgPainter(Component component, int bgScheme, int fgScheme) {
		this.component = component;
		this.bgScheme = bgScheme;
		this.fgScheme = fgScheme;
	}

	@Override
	public void paint(ColorScheme scheme) {
		component.setBackground(scheme.backgroundSolutions.get(bgScheme));
		component.setForeground(scheme.textColorSolutions.get(fgScheme));
	}
}
