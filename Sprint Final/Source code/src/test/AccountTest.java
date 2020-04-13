package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.Account;
import modele.Deck;

public class AccountTest {
	private Account account;
	private String id,password,mail;
	private boolean admin;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		id = "id";
		password = "password";
		mail = "mail";
		admin = true;
		account = Account.getInstanceAccount();
		account.setAdmin(admin);
		account.setId(id);
		account.setMail(mail);
		account.setPassword(password);
		
	}

	@After
	public void tearDown() throws Exception {
		id = null;
		password = null;
		mail = null;
		account = null;
	}

	@Test
	public void testGetInstanceAccount() {
		assertEquals("Test de GetInstanceAccount:",Account.getInstanceAccount(), account);
	}

	@Test
	public void testGetId() {
		assertEquals("Test de GetId:",Account.getInstanceAccount().getId(), id);
	}

	@Test
	public void testSetId() {
		account.setId("testId");
		assertEquals("Test de GetId:",Account.getInstanceAccount().getId(), "testId");
	}

	@Test
	public void testGetPassword() {
		assertEquals("Test de GetId:",Account.getInstanceAccount().getPassword(), password);
	}

	@Test
	public void testSetPassword() {
		account.setPassword("testPassword");
		assertEquals("Test de GetId:",Account.getInstanceAccount().getPassword(), "testPassword");
	}

	@Test
	public void testGetMail() {
		assertEquals("Test de GetId:",Account.getInstanceAccount().getMail(), mail);
	}

	@Test
	public void testSetMail() {
		account.setMail("testMail");
		assertEquals("Test de GetId:",Account.getInstanceAccount().getMail(), "testMail");
	}

	@Test
	public void testIsAdmin() {
		assertEquals("Test de GetId:",Account.getInstanceAccount().isAdmin(), admin);
	}

	@Test
	public void testSetAdmin() {
		account.setAdmin(false);
		assertEquals("Test de GetId:",Account.getInstanceAccount().isAdmin(), false);
	}

	@Test
	public void testSetInstanceAccount() {
		Account testAccount = new Account("testA", "testP", "testM", false);
		account.setInstanceAccount(testAccount);
		assertEquals("Test de SetInstanceAccount:",Account.getInstanceAccount(), testAccount);
	}


	@Test
	public void testEqualsId() {
		Account a = new Account("id", "a", "a", true);
		assertEquals("Test de EqualsId:",account.equalsId(a), true);
		
	}

}
