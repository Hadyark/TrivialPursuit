package network;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import modele.Account;
import modele.Player;
import modele.Question;

public class PlayerServer extends PlayerNetwork {
	private static final long serialVersionUID = 1L;

	private ServerGame server;

	public PlayerServer(ServerGame serveur, Socket socket, int num) {
		super(socket, num);
		this.server = serveur;
	}

	public void sayReady(){
		Message message = new Message(Action.NUM, num);
		try {
			out.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void receptMessage(Message message) {
		Action action = message.getAction();

		switch (action) {
		case NAME : 
			server.preventOpponent(num, message);	
			break;
		case READY_TO_PLAY : 
			message = new Message(Action.START, null);
			server.preventServerReady(message);
			break;
		case WHEEL : 
			server.preventEverybody(message);
			break;
		case BUZZ : 
			server.buzz(num, (Question) message.getObject());
			break;
		case ANSWER : 
			server.answer(num,message.getObject().toString());
			break;
		case READY :
			message = new Message(Action.READY, null);
			server.preventServerReady(message);
			break;
		case NEED_TIMER : 
			server.startTimelineQuestion(num);
			break;
		case GAME_IS_FINISHED : 
			gameIsFinished = true;
			break;
		}
	}

	public void run() {
		Message message;

		try {
			message = (Message) in.readObject();
			while (!gameIsFinished) {
				System.out.println("PlayerServer recoit: "+message);
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
		System.out.println("PlayerServer is stopped");
	}
}
