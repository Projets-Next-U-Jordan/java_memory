package fr.thejordan.dev.helper;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.thejordan.dev.gui.GameChoser;

public class Utils {

	public static String formatTime(long timer) {
		long minutes = timer / 60;
		long seconds = timer % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}
	
	public static void endGameDialog(JFrame frame, String content) {
		int answer = JOptionPane.showConfirmDialog(frame, content, "", JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION)
			GameChoser.invoke();
		frame.setVisible(false);
		frame.dispose();
	}
	
}
