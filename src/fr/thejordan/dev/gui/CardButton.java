package fr.thejordan.dev.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.thejordan.dev.helper.Card;

public class CardButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	
	public final Integer slot;
	public final Card card;
	
	public boolean showed = false;
	public boolean found = false;
	
	public CardButton(int slot, Card card) {
		this.slot = slot;
		this.card = card;
		this.setIcon(new ImageIcon(Card.defaultImage()));
	}
	
	public void toggle() {
		if (found) return;
		showed = !showed;
		if (showed) {
			this.setIcon(new ImageIcon(card.getImage()));
		} else {
			this.setIcon(new ImageIcon(Card.defaultImage()));
		}
	}

}
