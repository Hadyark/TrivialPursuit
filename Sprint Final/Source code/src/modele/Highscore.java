package modele;

import java.util.ArrayList;

public class Highscore {

	private ArrayList<Score> listHighscore;

	/**
	 * It allows to create a highscore
	 */
	public Highscore() {
		listHighscore = new ArrayList<>();
	}
	/**
	 * @return a list of the highscore
	 */
	public ArrayList<Score> getListHighscore() {
		return listHighscore;
	}
	/**
	 * @param listHighscore
	 * The new list of the score
	 */
	public void setListHighscore(ArrayList<Score> listHighscore) {
		this.listHighscore = listHighscore;
	}
	/**
	 * @return a string of the highscore
	 */
	@Override
	public String toString() {
		return "Highscore [listHighscore=" + listHighscore + "]";
	}

	

	
	
	
}
