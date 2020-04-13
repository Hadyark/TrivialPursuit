package network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import enumeration.Category;
import exceptions.ExceptionServerNotFound;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import modele.Account;
import modele.Deck;
import modele.Game;
import modele.Player;
import modele.Question;
import view.MainFx;

public class PlayerClient extends PlayerNetwork{

	private static final long serialVersionUID = 1L;
	private Socket socket;
	private static PlayerClient instanceClient;
	public PlayerClient() {}

	/**
	 * It allows to create a PlayerClient
	 * @param socket
	 * The socket used to connect
	 */
	public PlayerClient(Socket socket) {
		super(socket);
		name = Account.getInstanceAccount().getId();
		opponent = new PlayerClient();
	}

	/**
	 * Method to handle a message received
	 * @param message
	 * The message received 
	 */
	public void receptMessage(Message message) {
		Action action = message.getAction();
		Message mess;
		switch(action) {
		case NUM :
			num=(int) message.getObject();
			sendMessage(Action.NAME, name);
			break;	
		case NAME : 
			opponent.name = (String) message.getObject();
			Utility.setNameOpponent(opponent.name);
			Player p1= new Player(Account.getInstanceAccount().getId());
			Player p2= new Player(opponent.name);
			ArrayList<Player> players = new ArrayList<>();
			players.add(p1);
			players.add(p2);
			Game.getInstanceGame().setPlayers(players);
			Utility.setStartEnable();
			break;
		case GAME :
			Game.initiInstanceGame((Game) message.getObject());
			break;
		case START :
			Utility.startGame();
			break;
		case WHEEL : 
			Utility.setWheel((int)message.getObject());
			break;
		case TIMER_OPPONENT : 
			Utility.visibleFieldOpponent(message.getObject().toString());
			break;
		case TIMER_ME : 
			Utility.visibleFieldMe(message.getObject().toString());
			break;
		case ANSWER_CORRECT_OPPONENT : 
			Utility.correctOpponent(message.getObject().toString());
			break;
		case ANSWER_CORRECT_ME : 
			Utility.correctMe();
			break;
		case ANSWER_WRONG_OPPONENT : 
			Utility.wrongOpponent(message.getObject().toString());
			break;
		case ANSWER_WRONG_ME : 
			Utility.wrongMe();
			break;
		case READY : 
			Utility.nextLaps();
			break;
		case TIMER_QUESTION : 
			Utility.refreshTimerQuestion(message.getObject().toString());
			break;
		case TIME_PLAYER_OUT : 
			Utility.timePlayerOut();
			break;
		case TIME_QUESTION_OUT : 
			Utility.timerQuestionOut();
			break;
		}

	}
	/**
	 * Method to send a message
	 * @param action
	 * Action of the message
	 * @param object
	 * Object of the message
	 */
	public void sendMessage(Action action, Object object) {
		Message message = new Message(action, object); 

		try {
			System.out.println("Message envoyé: "+message);
			out.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Loop to receive a message
	 */
	public void run() {
		Message message;

		try {
			message = (Message) in.readObject();
			while (!gameIsFinished) {
				System.out.println("PlayerClient recoit: "+message);
				receptMessage(message);
				message = (Message) in.readObject();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println("PlayerClient is stopped");
	}

	/**
	 * Thread
	 * @param localIp
	 * The IP of the host to which you are connecting
	 */
	public void startClient(String localIp){

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				int port = 4567;

				System.out.println("Client");
				Socket socketClient = null;
				try {
					socketClient = new Socket(localIp, port);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(socketClient == null) {
					//throw new ExceptionServerNotFound();
				}
				instanceClient = new PlayerClient(socketClient);
				System.out.println(instanceClient);
			}
		});
		thread.setDaemon(true);
		thread.start();

	}

	/**
	 * @return Instance of the client
	 */
	public static PlayerClient getInstanceClient() {
		if(instanceClient == null) {
			instanceClient = new PlayerClient();
		}
		return instanceClient;
	}
	/**
	 * @return a string of the client
	 */
	@Override
	public String toString() {
		return "PlayerClient [socket=" + socket + "]";
	}


}
