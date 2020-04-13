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
	/**
	 * It allows to create an account
	 * @param name
	 * Player's name
	 */
	public Player(String name) {
		this.name = name;
		this.cheeses = new ArrayList();
		this.chainWin = 0;
		this.score = 0;
		this.multiplier = 1;
		this.point = 0;
	}
	/**
	 * @return player's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 * The new player's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return The player's list of cheeses
	 */
	public List<Category> getCheeses() {
		return cheeses;
	}
	/**
	 * @param cheeses
	 * The new player's list of cheeses
	 */
	public void setCheeses(List<Category> cheeses) {
		this.cheeses = cheeses;
	}
	/**
	 * @return the player's chain win
	 */
	public int getChainWin() {
		return chainWin;
	}
	/**
	 * @param chainWin
	 * The new player's chain win
	 */
	public void setChainWin(int chainWin) {
		this.chainWin = chainWin;
	}
	/**
	 * @return The player's score
	 */
	public double getScore() {
		return score;
	}
	/**
	 * @param score
	 * The new player's score
	 */
	public void setScore(double score) {
		this.score = score;
	}
	/**
	 * @return the player's multiplier of point
	 */
	public double getMultiplier() {
		return multiplier;
	}
	/**
	 * @param multiplier
	 * The new player's multiplier of point
	 */
	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}
	/**
	 * @return The player's points
	 */
	public double getPoint() {
		return point;
	}
	/**
	 * @param point
	 * The new player's point
	 */
	public void setPoint(double point) {
		this.point = point;
	}
	/**
	 * @return a string of the player
	 */
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
