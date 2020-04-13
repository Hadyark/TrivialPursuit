package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	private String mode,difficulty;
	private int arroundCurrent,arroundMax;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Deck deck;
	private Question currentQuestion;
	private static Game instanceGame = null;

	/**
	 * It allows to create a game
	 */
	public Game() {
		this.mode = mode;
		this.difficulty = difficulty;
		this.arroundCurrent = arroundCurrent;
		this.arroundMax = arroundMax;
		this.players = players;
		this.deck = deck;
		this.currentQuestion = null;
	}
	/**
	 * @return an instance of the deck
	 */
	public static Game getInstanceGame() {
		if(instanceGame==null) {
			instanceGame = new Game();
		}
		return instanceGame;
	}
	public static void initiInstanceGame(Game game) {
		instanceGame = game;
	}
	/**
	 * @return the mode of the game
	 */
	public String getMode() {
		return mode;
	}
	/**
	 * @param mode
	 * Hte new mode of the game
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	/**
	 * @return the difficulty of the game
	 */
	public String getDifficulty() {
		return difficulty;
	}
	/**
	 * @param difficulty
	 * The new difficulty of the game
	 */
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	/**
	 * @return the current arround of the game
	 */
	public int getArroundCurrent() {
		return arroundCurrent;
	}
	/**
	 * @param arroundCurrent
	 * The new current arround of the game
	 */
	public void setArroundCurrent(int arroundCurrent) {
		this.arroundCurrent = arroundCurrent;
	}
	/**
	 * @return the max arround of the game
	 */
	public int getArroundMax() {
		return arroundMax;
	}
	/**
	 * @param arroundMax
	 * the new max arround of the game
	 */
	public void setArroundMax(int arroundMax) {
		this.arroundMax = arroundMax;
	}
	/**
	 * @return the list of the players in the game
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}
	/**
	 * @param players
	 * The new list of the players in the game
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	/**
	 * @return the deck of the game
	 */
	public Deck getDeck() {
		return deck;
	}
	/**
	 * @param deck
	 * The new deck of the game
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	/**
	 * @return the current question of the game
	 */
	public Question getCurrentQuestion() {
		return currentQuestion;
	}
	/**
	 * @param currentQuestion
	 * the new current question of the game
	 */
	public void setCurrentQuestion(Question currentQuestion) {
		this.currentQuestion = currentQuestion;
	}
	/**
	 * @return a string of the game
	 */
	@Override
	public String toString() {
		return "Game [mode=" + mode + ", difficulty=" + difficulty + ", arroundCurrent=" + arroundCurrent
				+ ", arroundMax=" + arroundMax + ", players=" + players + ", deck=" + deck + ", currentQuestion="
				+ currentQuestion + "]";
	}

}
