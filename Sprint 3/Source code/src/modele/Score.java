package modele;

public class Score {

	private String idPlayer, difficulty;
	private double points;
	private int laps;
	
	public Score(String idPlayer, String difficulty, double points, int laps) {
		this.idPlayer = idPlayer;
		this.difficulty = difficulty;
		this.points = points;
		this.laps = laps;
	}

	public String getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(String idPlayer) {
		this.idPlayer = idPlayer;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	@Override
	public String toString() {
		return "Score [idPlayer=" + idPlayer + ", difficulty=" + difficulty + ", points=" + points + ", laps=" + laps
				+ "]";
	}
	
	
	
}
