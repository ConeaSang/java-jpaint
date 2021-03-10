package application.color;

import model.ShapeColor;

import java.awt.*;
import java.util.EnumMap;

// Singleton
public class ColorTranslation {
	// Data
	private static final ColorTranslation m_instance = new ColorTranslation();
	private static EnumMap<ShapeColor, java.awt.Color> m_mapColor;

	static {
		ColorTranslation.m_mapColor = linkTheColor();
	}

	// Constructors ("private" because this class is singleton)
	private ColorTranslation() {
	}

	// Methods
	// getInstance() method
	public static ColorTranslation getInstance() {
		return m_instance;
	}

	// Public methods
	public Color getColor(ShapeColor _shapeColor) {
		return m_mapColor.get(_shapeColor);
	}

	// Private methods
	private static EnumMap<ShapeColor, java.awt.Color> linkTheColor() {
		EnumMap<ShapeColor, java.awt.Color> map = new EnumMap<>(ShapeColor.class);

		map.put(ShapeColor.BLACK, Color.BLACK);
		map.put(ShapeColor.BLUE, Color.BLUE);
		map.put(ShapeColor.CYAN, Color.CYAN);
		map.put(ShapeColor.DARK_GRAY, Color.DARK_GRAY);
		map.put(ShapeColor.GRAY, Color.GRAY);
		map.put(ShapeColor.GREEN, Color.GREEN);
		map.put(ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
		map.put(ShapeColor.MAGENTA, Color.MAGENTA);
		map.put(ShapeColor.ORANGE, Color.ORANGE);
		map.put(ShapeColor.PINK, Color.PINK);
		map.put(ShapeColor.RED, Color.RED);
		map.put(ShapeColor.WHITE, Color.WHITE);
		map.put(ShapeColor.YELLOW, Color.YELLOW);

		return map;
	}
}
