package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.Account;
import modele.Highscore;
import modele.Score;

public class HighscoreTest {
	private Highscore highscore;
	private ArrayList<Score> scores;
	private Score score;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		highscore = new Highscore();
		scores=(ArrayList<Score>) Explorateur.getField(highscore, "listHighscore");
		score = new Score("Jean", "Easy", 4, 20);
		scores.add(score);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetListHighscore() {
		assertEquals("Test de GetListHighscore:",highscore.getListHighscore(), scores);
	}

	@Test
	public void testSetListHighscore() {
		ArrayList<Score> testscores = new ArrayList<>();
		Score testscore = new Score("idPlayer", "difficulty", 0, 10);
		testscores.add(testscore);
		highscore.setListHighscore(testscores);
		assertEquals("Test de GetListHighscore:",highscore.getListHighscore(), testscores);
	}

}
