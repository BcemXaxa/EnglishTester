package ru.vsu.cs.daa.engtester.painters;

import ru.vsu.cs.daa.engtester.experimental.settings.ColorScheme;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BdTlPainter extends BgFgPainter {
	public int bdScheme;
	public int tlScheme;

	public BdTlPainter(Component component, int bgScheme, int fgScheme) {
		super(component, bgScheme, fgScheme);
		bdScheme = fgScheme;
		tlScheme = fgScheme;
	}

	public BdTlPainter(Component component, int bgScheme, int fgScheme, int bdScheme, int tlScheme) {
		super(component, bgScheme, fgScheme);
		this.bdScheme = bdScheme;
		this.tlScheme = tlScheme;
	}

	@Override
	public void paint(ColorScheme scheme) {
		super.paint(scheme);
		((JComponent) component).setBorder(
				recolorBorder(((JComponent) component).getBorder(),
							  scheme.textColorSolutions.get(bdScheme),
							  scheme.textColorSolutions.get(tlScheme)));
	}

	protected Border recolorBorder(Border border, Color bdColor, Color tlColor) {
		try {
			if (border instanceof LineBorder lineBorder) {
				int thickness = lineBorder.getThickness();
				boolean roundedCorners = lineBorder.getRoundedCorners();
				return new LineBorder(bdColor, thickness, roundedCorners);

			} else if (border instanceof TitledBorder titledBorder) {
				String title = titledBorder.getTitle();
				int titleJustification = titledBorder.getTitleJustification();
				int titlePosition = titledBorder.getTitlePosition();
				Font titleFont = titledBorder.getTitleFont();

				TitledBorder newBorder = new TitledBorder(new LineBorder(bdColor, 1), title, titleJustification,
														  titlePosition, titleFont, tlColor);

				return newBorder;
			}
			return border;
		} catch (Exception exception) {
			return border;
		}
	}
}
