package fr.thejordan.dev.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ScoreManager {

	private static ScoreManager instance;
	
	public File getDifficultyFile(Difficulty difficulty) {
		return new File("./"+difficulty.name()+".leaderboard");
	}
	
	private Map<Difficulty,List<Score>> scores = new HashMap<>();
	
	public static ScoreManager instance() {
		if (instance == null) instance = new ScoreManager();
		return instance;
	}
	
	public ScoreManager() {
		for (Difficulty difficulty : Difficulty.values()) {
			scores.put(difficulty, new ArrayList<Score>());
			loadScores(difficulty);
		}
	}
	
	public List<Score> getScores(Difficulty difficulty) {
		return scores.getOrDefault(difficulty, new ArrayList<Score>());
	}
	
	public List<Score> getTop3(Difficulty difficulty) {
		List<Score> top3 = new ArrayList<Score>();
		List<Score> scores = getScores(difficulty);
		scores.sort((a, b) -> Long.compare(a.elapsed, b.elapsed));
		for (int i = 0; i < 3 && i < scores.size(); i++) {
			top3.add(scores.get(i));
		}
		return top3;
	}
	
	public void addScore(Difficulty difficulty, Score score) {
		this.scores.get(difficulty).add(score);
	}

	private void loadScores(Difficulty difficulty) {
		File scoreFile = getDifficultyFile(difficulty);
		if (!scoreFile.exists()) {
			try {
				scoreFile.createNewFile();
			} catch (IOException e) {
				return;
			}
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(scoreFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|-\\|");
				if (parts.length == 3) {
					String name = parts[0].trim();
					long scoreL = Long.parseLong(parts[1].trim());
					Integer tries = Integer.parseInt(parts[2].trim());
					Score score = new Score(name, scoreL, tries);
					scores.get(difficulty).add(score);
				}
			}
		} catch (Exception e) {
			return;
		}
	}
	
	public void saveScores() {
		for (Difficulty difficulty : Difficulty.values()) {
			File scoreFile = getDifficultyFile(difficulty);
			if (!scoreFile.exists()) {
				try {
					scoreFile.createNewFile();
				} catch (IOException e) {
					return;
				}
			}
			
			
			
			StringJoiner scoreString = new StringJoiner("\n");
			for (Score score : scores.get(difficulty)) {
				scoreString.add(score.toString());
			}
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(scoreFile))) {
				writer.append(scoreString.toString());
				writer.close();
			} catch (IOException e) {
				return;
			}
		}
	}
	
}
