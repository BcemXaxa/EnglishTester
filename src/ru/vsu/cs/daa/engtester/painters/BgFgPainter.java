package ru.vsu.cs.daa.engtester.painters;

import ru.vsu.cs.daa.engtester.utils.UIPreferences;

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
	public void paint(UIPreferences preferences) {
		component.setBackground(preferences.backgroundSolutions.get(bgScheme));
		component.setForeground(preferences.textColorSolutions.get(fgScheme));
	}
}
