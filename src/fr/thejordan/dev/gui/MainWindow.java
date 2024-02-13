package fr.thejordan.dev.gui;

import javax.swing.JFrame;

import fr.thejordan.dev.game.Difficulty;
import fr.thejordan.dev.game.Game;
import fr.thejordan.dev.helper.Card;
import fr.thejordan.dev.helper.GameConstants;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class MainWindow {
	
	private final Game game;
	
	public Integer tries = 50;
	public final List<Card> found = new ArrayList<Card>();
	public final List<CardButton> buttons = new ArrayList<CardButton>();
	
	private JFrame frame;
	public JFrame frame() {
		return frame;
	}
	
	
	public ActionListener onCardClick(int slot, Card card) {
		return (e)->{
			if (tries == 0) return;
			if (found.contains(card)) return;
			if (game.lastClicked == null) {
				game.lastClicked = buttons.get(slot);
				buttons.get(slot).toggle();
			} else {
				if (game.lastClicked == buttons.get(slot)) return;
				if (game.lastClicked.card == card) {
					found.add(card);
					buttons.get(slot).toggle();
					if (found.size() == game.difficulty.volume()/2) {
						System.out.println("FINI !");
					}
				} else {
					game.lastClicked.toggle();
					tries--;
					if (tries == 0) {
						System.out.println("Plus de vie :'(");
					}
				}
				game.lastClicked = null;
			}
		};
	}
	
	public MainWindow() {
		this.game = new Game(Difficulty.EASY);
		initialize();
	}
	
	public MainWindow(Game game) {
		this.game = game;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 
				GameConstants.IMAGE_WIDTH*game.difficulty.col, 
				GameConstants.IMAGE_HEIGHT*game.difficulty.row
		);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(
				game.difficulty.row,
				game.difficulty.col,
				0, 0
		));
		
		for (Entry<Integer, Card> entry : game.slots.entrySet()) {
			System.out.println(entry.getKey() + " "+entry.getValue().name);
	    	CardButton cardButton = new CardButton(entry.getKey(), entry.getValue());
	    	cardButton.addActionListener(onCardClick(entry.getKey(),entry.getValue()));
	    	buttons.add(cardButton);
	    	frame.getContentPane().add(cardButton);
		}
	}

}
