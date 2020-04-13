package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import enumeration.Category;
import modele.Account;
import modele.Question;

public class QuestionTest {
	private Question question;
	private String author, statement, answer;
	private Category category;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		author="author";
		statement="statement";
		answer="answer";
		category=category.CYBERSECURITY;
		question = new Question(author, category, statement, answer);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAuthor() {
		assertEquals("Test de GetAuthor:",question.getAuthor(), author);
		
	}

	@Test
	public void testSetAuthor() {
		question.setAuthor("testAuthor");
		assertEquals("Test de GetAuthor:",question.getAuthor(), "testAuthor");
	}

	@Test
	public void testGetStatement() {
		assertEquals("Test de GetAuthor:",question.getStatement(), statement);
	}

	@Test
	public void testSetStatement() {
		question.setStatement("testStatement");
		assertEquals("Test de GetAuthor:",question.getStatement(), "testStatement");
	}

	@Test
	public void testGetAnswer() {
		assertEquals("Test de GetAuthor:",question.getAnswer(), answer);
	}

	@Test
	public void testSetAnswer() {
		question.setAnswer("testAnswer");
		assertEquals("Test de GetAuthor:",question.getAnswer(), "testAnswer");
	}

	@Test
	public void testGetCategory() {
		assertEquals("Test de GetAuthor:",question.getCategory(), category);
	}

	@Test
	public void testSetCategory() {
		question.setCategory(Category.INTERNET);
		assertEquals("Test de GetAuthor:",question.getCategory(), Category.INTERNET);
	}

	@Test
	public void testEqualsQuestion() {
		Question qtest = new Question(author, category, statement, answer);
		assertEquals("Test de EqualsId:",question.equalsQuestion(qtest), true);
		
	}

}
