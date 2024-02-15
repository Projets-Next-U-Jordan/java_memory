package fr.thejordan.dev.helper;

public class GameConstants {

	public static final int IMAGE_WIDTH = 100;
	public static final int IMAGE_HEIGHT = 100;

	public static final String GAME_TIMER_LABEL(String time) { return "Timer: "+time;}
	public static final String GAME_TRIES_LABEL(int tries) { return "Essais: "+tries;}

	public static final String GAME_OVER_FINISHED_MESSAGE(String time) { return "Bravo ! Vous avez terminé en "+time+" !\nRecommencer ?"; };
	public static final String GAME_OVER_TIME_UP_MESSAGE = "Temps écoulé !\nRecommencer ?";
	public static final String GAME_OVER_NO_MORE_TRIES_MESSAGE = "Plus d'essais !\nRecommencer ?";

}
