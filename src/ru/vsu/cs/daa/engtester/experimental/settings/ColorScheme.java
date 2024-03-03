package ru.vsu.cs.daa.engtester.experimental.settings;

import java.awt.*;
import java.util.ArrayList;

public class ColorScheme {
	public String name;
	public ArrayList<Color> backgroundSolutions = new ArrayList<>();
	public ArrayList<Color> textColorSolutions = new ArrayList<>();
	public ArrayList<Color> optionalSolutions = new ArrayList<>();

	public ColorScheme(String name){
		this.name = name;
	}
}
