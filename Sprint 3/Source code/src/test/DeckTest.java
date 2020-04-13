package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import enumeration.Category;
import modele.Deck;
import modele.Question;

public class DeckTest {

	private Deck deck;
	private List<Question> questions;
	private Question q;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		deck = deck.getInstanceDeck();
		questions=(List<Question>) Explorateur.getField(deck, "questions");
		q = new Question("autho", Category.CYBERSECURITY, "statement", "answer");	
		questions.add(q);
	}

	@After
	public void tearDown() throws Exception {
		questions.clear();
		deck = null;
		questions=null;
		q=null;
	}

	@Test
	public void testGetInstanceDeck() {
		assertEquals("Test de GetInstanceDeck:",Deck.getInstanceDeck(), deck);
	}

	@Test
	public void testAddQuestion() {
		Question q = new Question("autho2", Category.CYBERSECURITY, "statement2", "answer2");
		deck.addQuestion(q);assertEquals("Test de addQuestion()", deck.getQuestions().get(1), q);
		assertEquals("Test de AddQuestion()", deck.getQuestions().size(), 2);
	}

	@Test
	public void testGetQuestions() {
		assertEquals("Test de GetListQuestion()",deck.getQuestions(), questions);
	}

	@Test
	public void testSetQuestions() {
		Question q=new Question("author3", Category.INTERNET, "statement3", "answer3");
		List listQuestion = new ArrayList<>();
		listQuestion.add(q);
		deck.setQuestions(listQuestion);
		assertEquals("Test de SetListQuestion()",deck.getQuestions(),listQuestion);
	}

	@Test
	public void testToDraw() {
		Category categ = Category.CYBERSECURITY;
		Question quest = deck.toDraw(categ);
		assertEquals("Test de ToDraw()",quest.getCategory(),categ);
	}

}
