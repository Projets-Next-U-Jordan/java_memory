package fr.thejordan.dev.game;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.thejordan.dev.Main;
import fr.thejordan.dev.gui.MainWindow;
import fr.thejordan.dev.helper.Card;
import fr.thejordan.dev.helper.CardButton;

public class Game {

	public Difficulty difficulty = Difficulty.EASY;
	
	public CardButton lastClicked = null;
	public Map<Integer, Card> slots = new HashMap<Integer, Card>();
	
	public Game(Difficulty difficulty) {
		this.difficulty = difficulty;
		List<Card> chosen = new ArrayList<Card>(Main.images);
		chosen = chosen.subList(0, this.difficulty.volume()/2);
		chosen.addAll(chosen);
		Collections.shuffle(chosen);
		for (int i = 0; i < chosen.size(); i++) {
			slots.put(i, chosen.get(i));
		}
	}
	
	public void start() {
		Game game = this;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow(game);
					window.frame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}