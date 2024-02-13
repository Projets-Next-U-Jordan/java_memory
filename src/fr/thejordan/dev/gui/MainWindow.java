package fr.thejordan.dev.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.thejordan.dev.game.Difficulty;
import fr.thejordan.dev.game.Game;
import fr.thejordan.dev.helper.Card;
import fr.thejordan.dev.helper.CardButton;
import fr.thejordan.dev.helper.GameConstants;
import fr.thejordan.dev.helper.TaskRunnable;
import fr.thejordan.dev.helper.Utils;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class MainWindow {
	
	private final Game game;
	private JLabel timerLabel;
	private JLabel triesLabel;
	
	public Integer tries = 0;
	public long timer = 0L;
	public final List<Card> found = new ArrayList<Card>();
	public final List<CardButton> buttons = new ArrayList<CardButton>();
	
	private final TaskRunnable timerTask = new TaskRunnable() {
		@Override
		public void run() {
			if (timerLabel == null) return;
			timer--;
			if (timer == 0) {
				stop();
				Utils.endGameDialog(frame,GameConstants.GAME_OVER_TIME_UP_MESSAGE);
			}
			String timerText = Utils.formatTime(timer);
			timerLabel.setText(GameConstants.GAME_TIMER_LABEL(timerText));
		}
	};
	
	private JFrame frame;
	public JFrame frame() { return frame;}
	

	
	public ActionListener onCardClick(int slot, Card card) {
	    return (e) -> {
	        if (tries > 0 && !found.contains(card)) {
	            if (game.lastClicked == null) {
	                game.lastClicked = buttons.get(slot);
	                buttons.get(slot).toggle();
	            } else {
	                if (game.lastClicked != buttons.get(slot)) {
	                    if (game.lastClicked.card == card) {
	                        found.add(card);
	                        buttons.get(slot).toggle();

	                        if (found.size() == game.difficulty.volume() / 2) {
	                            this.timerTask.stop();
	                            long elapsed = game.difficulty.maxTime - timer;
	                            String elapsedTime = Utils.formatTime(elapsed);
	                            Utils.endGameDialog(frame, GameConstants.GAME_OVER_FINISHED_MESSAGE(elapsedTime));
	                        }
	                    } else {
	                        game.lastClicked.toggle();
	                        tries--;
	                        triesLabel.setText(GameConstants.GAME_TRIES_LABEL(tries));

	                        if (tries == 0) {
	                            this.timerTask.stop();
	                            Utils.endGameDialog(frame, GameConstants.GAME_OVER_NO_MORE_TRIES_MESSAGE);
	                        }
	                    }
	                }
	                game.lastClicked = null;
	            }
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
		this.tries = game.difficulty.maxTries;
		this.timer = game.difficulty.maxTime;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 
				GameConstants.IMAGE_WIDTH*game.difficulty.col, 
				GameConstants.IMAGE_HEIGHT*game.difficulty.row
		);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel timerPanel = new JPanel();
		timerLabel = new JLabel(GameConstants.GAME_TIMER_LABEL("00:00"));
		
		timerPanel.add(timerLabel);
		
		frame.getContentPane().add(timerPanel,BorderLayout.NORTH);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(
				game.difficulty.row,
				game.difficulty.col,
				0, 0
		));
		
		for (Entry<Integer, Card> entry : game.slots.entrySet()) {
	    	CardButton cardButton = new CardButton(entry.getKey(), entry.getValue());
	    	cardButton.addActionListener(onCardClick(entry.getKey(),entry.getValue()));
	    	buttons.add(cardButton);
	    	mainPanel.add(cardButton);
		}
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		JPanel downPanel = new JPanel();
		triesLabel = new JLabel(GameConstants.GAME_TRIES_LABEL(tries));
		downPanel.add(triesLabel);
		frame.getContentPane().add(downPanel, BorderLayout.SOUTH);
		this.timerTask.runTaskTimer(0L, 1000L);
	}

}
