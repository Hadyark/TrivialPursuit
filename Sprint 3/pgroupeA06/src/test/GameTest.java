package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import enumeration.Category;
import modele.Account;
import modele.Deck;
import modele.Game;
import modele.Player;
import modele.Question;

public class GameTest {
	private Game game;
	private String mode,difficulty;
	private int arroundCurrent,arroundMax;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Deck deck;
	private Question currentQuestion;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		mode = "Solo";
		difficulty = "Easy";
		arroundCurrent = 1;
		arroundMax = 25;
		game = Game.getInstanceGame();
		players = new ArrayList<>();
		Player p1 = new Player("Jean");
		players.add(p1);
		Question q = new Question("author", Category.CYBERSECURITY, "statement", "answer");
		deck = deck.getInstanceDeck();
		game.setDeck(deck);
		currentQuestion = new Question("current", Category.CYBERSECURITY, "current", "current");
		game.setCurrentQuestion(currentQuestion);
	}

	@After
	public void tearDown() throws Exception {
		mode = null;
		difficulty = null;
		arroundCurrent = 0;
		arroundMax = 0;
		game = null;
		players.clear();
		deck = new Deck();
		currentQuestion = null;
	}

	@Test
	public void testGetInstanceGame() {
		assertEquals("Test de GetInstanceGame:",game, game);
	}

	@Test
	public void testInitiInstanceGame() {
		Game g = new Game();
		Game.initiInstanceGame(g);
		assertEquals("Test de InitiInstanceGame:",game, g);
	}

	@Test
	public void testGetMode() {
		assertEquals("Test de GetMode:",game.getMode(), mode);
	}

	@Test
	public void testSetMode() {
		game.setMode("testmode");
		assertEquals("Test de SetMode:",game.getMode(), "testmode");
	}

	@Test
	public void testGetDifficulty() {
		assertEquals("Test de GetDifficulty:",game.getDifficulty(), difficulty);
	}

	@Test
	public void testSetDifficulty() {
		game.setDifficulty("testDif");
		assertEquals("Test de SetDifficulty:",game.getMode(), "testDif");
	}

	@Test
	public void testGetArroundCurrent() {
		assertEquals("Test de GetArroundCurrent:",game.getArroundCurrent(), arroundCurrent);
	}

	@Test
	public void testSetArroundCurrent() {
		game.setArroundCurrent(5);
		assertEquals("Test de SetArroundCurrent:",game.getArroundCurrent(), 5);
	}

	@Test
	public void testGetArroundMax() {
		assertEquals("Test de GetArroundMax:",game.getArroundMax(), arroundMax);
	}

	@Test
	public void testSetArroundMax() {
		game.setArroundMax(30);
		assertEquals("Test de SetArroundMax:",game.getArroundMax(), 30);
	}

	@Test
	public void testGetPlayers() {
		assertEquals("Test de SetArroundMax:",game.getPlayers(), players);
	}

	@Test
	public void testSetPlayers() {
		ArrayList<Player> listPlayer = new ArrayList<>();
		Player p = new Player("Marc");
		listPlayer.add(p);
		game.setPlayers(listPlayer);
		assertEquals("Test de SetArroundMax:",game.getPlayers(), listPlayer);
	}

	@Test
	public void testGetDeck() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDeck() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentQuestion() {
		assertEquals("Test de GetCurrentQuestion:",game.getCurrentQuestion(), currentQuestion);
	}

	@Test
	public void testSetCurrentQuestion() {
		Question quest = new Question("test", Category.CYBERSECURITY, "test", "test");
		game.setCurrentQuestion(quest);
		assertEquals("Test de SetCurrentQuestion:",game.getCurrentQuestion(), quest);
	}

}
