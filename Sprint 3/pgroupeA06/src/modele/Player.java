package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import enumeration.Category;

public class Player implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public String name;
	private List<Category> cheeses;
	public int chainWin;
	private double score, multiplier, point;
	
	public Player() {}
	
	public Player(String name) {
		this.name = name;
		this.cheeses = new ArrayList();
		this.chainWin = 0;
		this.score = 0;
		this.multiplier = 1;
		this.point = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Category> getCheeses() {
		return cheeses;
	}

	public void setCheeses(List<Category> cheeses) {
		this.cheeses = cheeses;
	}

	public int getChainWin() {
		return chainWin;
	}

	public void setChainWin(int chainWin) {
		this.chainWin = chainWin;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", cheeses=" + cheeses + ", chainWin=" + chainWin + ", score=" + score
				+ ", multiplier=" + multiplier + ", point=" + point + "]";
	}
	
	public boolean addCheeses(Category categ) {
		if(!this.cheeses.contains(categ)) {
			this.cheeses.add(categ);
			return true;
		}
		return false;
	}
}
