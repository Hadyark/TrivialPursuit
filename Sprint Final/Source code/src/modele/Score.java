package modele;

public class Score {

	private String idPlayer, difficulty;
	private double points;
	private int laps;
	/**
	 * It allows to create an score
	 * @param idPlayer
	 * The player's id 
	 * @param difficulty
	 * The difficulty of the game
	 * @param points
	 * The points of the game
	 * @param laps
	 * The laps of the game
	 */
	public Score(String idPlayer, String difficulty, double points, int laps) {
		this.idPlayer = idPlayer;
		this.difficulty = difficulty;
		this.points = points;
		this.laps = laps;
	}
	/**
	 * @return The player's id
	 */
	public String getIdPlayer() {
		return idPlayer;
	}
	/**
	 * @param idPlayer
	 * The new player's id
	 */
	public void setIdPlayer(String idPlayer) {
		this.idPlayer = idPlayer;
	}
	/**
	 * @return The difficulty of the score
	 */
	public String getDifficulty() {
		return difficulty;
	}
	/**
	 * @param difficulty
	 * The new difficulty of the score
	 */
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	/**
	 * @return the point of the score
	 */
	public double getPoints() {
		return points;
	}
	/**
	 * @param points
	 * The new points of the score
	 */
	public void setPoints(double points) {
		this.points = points;
	}
	/**
	 * @return the laps of the score
	 */
	public int getLaps() {
		return laps;
	}
	/**
	 * @param laps
	 * The new laps of the score
	 */
	public void setLaps(int laps) {
		this.laps = laps;
	}
	/**
	 * @return a string of the score
	 */
	@Override
	public String toString() {
		return "Score [idPlayer=" + idPlayer + ", difficulty=" + difficulty + ", points=" + points + ", laps=" + laps
				+ "]";
	}
	
	
	
}
