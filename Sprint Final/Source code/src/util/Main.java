package util;

import java.lang.reflect.Method;
import java.security.KeyStore.ProtectionParameter;
import java.util.ArrayList;

import enumeration.Category;
import javafx.beans.property.SimpleStringProperty;
import modele.Account;
import modele.Deck;
import modele.ListAccount;
import modele.Question;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deck deck = new Deck();
		Question q =new Question("author", Category.CYBERSECURITY, "statement", "answer");
		System.out.println(q);
		deck.getQuestions().add(q);
		System.out.println(deck);
		q.setAnswer("aaaa");
		System.out.println(deck);
		System.out.println(q);
		
/*
		int num = 3;
		Object o = num;
		System.out.println(o);
		int a = (int) o;
		*/
		/*
		ArrayList questions = new ArrayList<>();
		
		Question q =new Question("author", Category.CYBERSECURITY, "statement", "answer");
		questions.add(q);
		Deck.getInstanceDeck().setQuestions(questions);
		System.out.println(Deck.getInstanceDeck());

		System.out.println("\nAdd2:");
		Question q2 =new Question("aut", Category.CYBERSECURITY, "stat", "anr");
		questions.add(q2);
		Deck.getInstanceDeck().setQuestions(questions);
		System.out.println(Deck.getInstanceDeck());
		
		System.out.println("\nAdd3:");
		Question q3 =new Question("a", Category.CYBERSECURITY, "s", "a");
		questions.add(q3);
		Deck.getInstanceDeck().setQuestions(questions);
		System.out.println(Deck.getInstanceDeck());

		Deck.getInstanceDeck().undo();
		System.out.println("\nUndo: "+Deck.getInstanceDeck());
		
		Deck.getInstanceDeck().undo();
		System.out.println("\nUndo: "+Deck.getInstanceDeck());

		/*
		
		/*
		Account admin = new Account("Admin", "helha", null, true);
		Account user = new Account("user", null, null, false);

		ArrayList listAccount = new ArrayList<>();
		listAccount.add(admin);
		listAccount.add(user);

		ListAccount listAccounts = new ListAccount();
		listAccounts.setAccounts(listAccount);
		Serialisation.saveFile(ListAccount.class, "save", listAccounts);
		 */

		/* Test tableview + introspection
		Method[] methods = admin.getClass().getMethods();
		Method method = methods[3];
		String nameMethod = method.getName();
		Class<?>[] classParamMethod = method.getParameterTypes();

		System.out.println(method);
		System.out.println(nameMethod);
		System.out.println(classParamMethod[0]);

		Object[] param = {"true"};
		Utility.callMethod(admin, nameMethod, param, classParamMethod[0]);
		System.out.println(admin);
		 */



	}
}
