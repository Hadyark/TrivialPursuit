package util;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import modele.Account;
import modele.ListAccount;
import view.MenuGameOption;

public class Utility {

	public static boolean authentification(String id, String password) {
		ListAccount listAccounts = (ListAccount) Serialisation.loadFile(ListAccount.class,"ListAccount_save");

		for (Account a : listAccounts.getListAccount()) {
			if(id.equals(a.getId()) && password.equals(a.getPassword())) {
				Account.setInstanceAccount(a);
				view.MainFx.secondaryStage();
				return true;
			}
		}
		return false;
	}

	public static int nbRand(int min, int max) {
		Random r = new Random();
		int nb = min + r.nextInt(max - min);
		return nb;
	}

	public static void initialiser(Object object) {
		object = null;

	}

	public static String resizeString(String string) {
		String newString ="";
		int pos, size = 30;

		if(string.length() <= size) {
			newString = string;
		}else {
			do {
				pos =string.indexOf(" ");
				newString += string.substring(0,pos+1);
				string = string.substring(pos+1, string.length());
				if(newString.length() >= size) {
					newString += "\n";
					size = size *2;
				}
			} while (string.length() >= 30);
			newString += string.substring(0,string.length());
		}
		return newString;
	}

	public static void callMethod(Object o, String nameMethod, Object[] param, Class c) {
		Class<?> cl = o.getClass();

		try {
			Method method = cl.getDeclaredMethod(nameMethod, c);
			method.invoke(o, param[0]);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Method createMethod(Object o, String nameMethod, Class c) {
		Class<?> cl = o.getClass();
		Method method = null;
		try {
			method= cl.getDeclaredMethod(nameMethod, c);

		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return method;
	}

	public static boolean sendMail(String sendTo, Account account) {

		boolean result = false;
		final String username = "helha.trivialpursuit@gmail.com";
		final String password = "helhaTrivial44";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});



		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(username));

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(sendTo));
			message.setSubject("TrivialPursuit - Your account is created");
			message.setText("Dear new user,"
					+ "\n\n Your account is now created"
					+ "\n\n Id: "+account.getId()
					+ "\n Password: "+account.getPassword());

			Transport.send(message);

			result = true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			result = false;
		}
		return result;
	}

	public static MediaPlayer getMediaPlayer(String file) {
		Media media=new Media(new File(file).toURI().toString());
		MediaPlayer sound=new MediaPlayer(media);
		if(MenuGameOption.getSoundsNull().isSelected()) {
			sound.setVolume(0);
		}else {
			sound.setVolume(15);
		}
		return sound;
	}


	public static void neonEffect(Node node,String color) {
		int depth = 80;

		DropShadow borderGlow= new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(Color.web(color));
		borderGlow.setWidth(depth);
		borderGlow.setHeight(depth);

		node.setEffect(borderGlow);
	}

}
