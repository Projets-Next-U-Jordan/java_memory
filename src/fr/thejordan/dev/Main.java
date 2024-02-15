package fr.thejordan.dev;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import fr.thejordan.dev.game.Difficulty;
import fr.thejordan.dev.game.ScoreManager;
import fr.thejordan.dev.gui.GameChoser;
import fr.thejordan.dev.helper.Card;
import fr.thejordan.dev.helper.ImageLoader;

public class Main {

	public static BufferedImage defaultIcon = null;
	public static List<Card> images = new ArrayList<Card>();
	
	public static void main(String[] args) {
		defaultIcon = ImageLoader.loadImage("resources/default.jpg");
		images = ImageLoader.loadImages("resources/images");
		System.out.println("Chargement des images...");
		ScoreManager.instance();
		System.out.println(ScoreManager.instance().getTop3(Difficulty.EASY));
		images.forEach((c -> {
			System.out.println(c.name+" chargée !");
		}));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameChoser window = new GameChoser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}

