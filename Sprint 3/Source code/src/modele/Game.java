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

	public Game() {
		this.mode = mode;
		this.difficulty = difficulty;
		this.arroundCurrent = arroundCurrent;
		this.arroundMax = arroundMax;
		this.players = players;
		this.deck = deck;
		this.currentQuestion = null;
	}
	public static Game getInstanceGame() {
		if(instanceGame==null) {
			instanceGame = new Game();
		}
		return instanceGame;
	}
	public static void initiInstanceGame(Game game) {
		instanceGame = game;
	}


	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public int getArroundCurrent() {
		return arroundCurrent;
	}
	public void setArroundCurrent(int arroundCurrent) {
		this.arroundCurrent = arroundCurrent;
	}
	public int getArroundMax() {
		return arroundMax;
	}
	public void setArroundMax(int arroundMax) {
		this.arroundMax = arroundMax;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public Question getCurrentQuestion() {
		return currentQuestion;
	}
	public void setCurrentQuestion(Question currentQuestion) {
		this.currentQuestion = currentQuestion;
	}
	@Override
	public String toString() {
		return "Game [mode=" + mode + ", difficulty=" + difficulty + ", arroundCurrent=" + arroundCurrent
				+ ", arroundMax=" + arroundMax + ", players=" + players + ", deck=" + deck + ", currentQuestion="
				+ currentQuestion + "]";
	}

}
