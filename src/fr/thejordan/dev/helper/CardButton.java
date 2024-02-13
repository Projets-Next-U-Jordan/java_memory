package fr.thejordan.dev.helper;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CardButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	
	public final Integer slot;
	public final Card card;
	
	public boolean showed = false;
	
	public CardButton(int slot, Card card) {
		this.slot = slot;
		this.card = card;
		this.setIcon(new ImageIcon(Card.defaultImage()));
	}
	
	public void toggle() {
		showed = !showed;
		if (showed) this.setIcon(new ImageIcon(card.getImage()));
		else this.setIcon(new ImageIcon(Card.defaultImage()));
	}

}
