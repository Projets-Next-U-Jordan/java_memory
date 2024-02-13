package fr.thejordan.dev.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

import fr.thejordan.dev.Main;
import fr.thejordan.dev.game.Difficulty;
import fr.thejordan.dev.game.Game;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.SpringLayout;
import javax.swing.JButton;

public class GameChoser {

	public JFrame frame;

	/**
	 * Create the application.
	 */
	public GameChoser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Quelle difficultée ?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		int loaded_images_size = Main.images.size();
		
		JLabel image_loaded = new JLabel("Nombre d'image");
		image_loaded.setText("Images chargées: "+loaded_images_size);
		image_loaded.setVerticalAlignment(SwingConstants.TOP);
		image_loaded.setHorizontalAlignment(SwingConstants.LEFT);
		image_loaded.setFont(new Font("Tahoma", Font.PLAIN, 19));
		panel.add(image_loaded, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		
		JPanel panel_2 = new JPanel();
		sl_panel_1.putConstraint(SpringLayout.NORTH, panel_2, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, panel_2, 10, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, panel_2, -10, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, panel_2, -10, SpringLayout.EAST, panel_1);
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JComboBox<Difficulty> difficultyComboBox = new JComboBox<Difficulty>();
		for (Difficulty difficulty : Difficulty.values()) {
			if (loaded_images_size >= difficulty.volume()/2)
				difficultyComboBox.addItem(difficulty);
		}
		panel_2.add(difficultyComboBox);
		
		JButton playButton = new JButton("Jouer");
		playButton.addActionListener(e -> {
			Difficulty difficulty = (Difficulty) difficultyComboBox.getSelectedItem();
			this.frame.setVisible(false);
			this.frame.dispose();
			new Game(difficulty).start();
		});
		
		panel_2.add(playButton);
	}
}
