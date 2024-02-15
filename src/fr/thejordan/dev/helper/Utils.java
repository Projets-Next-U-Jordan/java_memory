package fr.thejordan.dev.helper;

import java.util.StringJoiner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.thejordan.dev.game.Difficulty;
import fr.thejordan.dev.game.Score;
import fr.thejordan.dev.game.ScoreManager;
import fr.thejordan.dev.gui.GameChoser;

public class Utils {

	public static String formatTime(long timer) {
		long minutes = timer / 60;
		long seconds = timer % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}
	
	public static void endGameSaveScore(JFrame frame, String content, Difficulty difficulty, Long elapsed, Integer tries) {
		StringJoiner leaderboard = new StringJoiner("\n");
		for (Score score : ScoreManager.instance().getTop3(difficulty)) {
			leaderboard.add(score.format());
		}
		String fullContent = content+"\n"+leaderboard.toString();
		String answer = JOptionPane.showInputDialog(frame, fullContent, "", JOptionPane.QUESTION_MESSAGE);
		if (answer != null) {
			ScoreManager.instance().addScore(difficulty, new Score(answer, elapsed, tries));
			ScoreManager.instance().saveScores();
		}
		GameChoser.invoke();
		frame.setVisible(false);
		frame.dispose();
	}
	
	public static void endGameDialog(JFrame frame, String content) {
		int answer = JOptionPane.showConfirmDialog(frame, content, "", JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION)
			GameChoser.invoke();
		frame.setVisible(false);
		frame.dispose();
	}
	
}
