package fr.thejordan.dev.helper;

import java.awt.Image;
import java.awt.image.BufferedImage;

import fr.thejordan.dev.Main;

public class Card {
	
	public final String name;
	public final BufferedImage image;
	
	public Card(String name, BufferedImage image) {
		this.name = name;
		this.image = image;
	}

	public Image getImage() {
		return image.getScaledInstance(GameConstants.IMAGE_WIDTH, GameConstants.IMAGE_HEIGHT, Image.SCALE_SMOOTH);
	}
	
	public static Image defaultImage() {
		return Main.defaultIcon.getScaledInstance(GameConstants.IMAGE_WIDTH, GameConstants.IMAGE_HEIGHT, Image.SCALE_SMOOTH);
	}
	
}
