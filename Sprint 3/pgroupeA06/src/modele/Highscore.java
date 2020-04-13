package modele;

import java.util.ArrayList;

public class Highscore {

	private ArrayList<Score> listHighscore;

	public Highscore() {
		listHighscore = new ArrayList<>();
	}

	public ArrayList<Score> getListHighscore() {
		return listHighscore;
	}

	public void setListHighscore(ArrayList<Score> listHighscore) {
		this.listHighscore = listHighscore;
	}

	@Override
	public String toString() {
		return "Highscore [listHighscore=" + listHighscore + "]";
	}

	

	
	
	
}
