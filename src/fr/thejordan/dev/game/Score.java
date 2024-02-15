package fr.thejordan.dev.game;

import fr.thejordan.dev.helper.Utils;

public class Score {
	
	public final String name;
	public final Long elapsed;
	public final Integer tries;
	
	public Score(String name, Long elapsed, Integer tries) {
		this.name = name.replaceAll("\\|-\\|", " ");
		this.elapsed = elapsed;
		this.tries = tries;
	}
	
	public String format() {
		return name + " : " + Utils.formatTime(elapsed) + " : "+tries;
	}
	
	@Override
	public String toString() {
		return name+" |-| "+elapsed+" |-| "+tries;
	}

}