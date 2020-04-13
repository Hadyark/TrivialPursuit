package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

import modele.Account;
import modele.Player;

public class PlayerNetwork implements Runnable{
	private static final long serialVersionUID = 1L;

	protected ObjectInputStream in;
	protected ObjectOutputStream out;
	protected boolean gameIsFinished = false;
	protected boolean isBuzzed;
	protected String answer;
	protected PlayerNetwork opponent;
	protected int num;
	protected boolean ready;
	protected String name;

	public PlayerNetwork() {}

	/**
	 * It allows to create a PlayerNetwork
	 * @param socket
	 * The socket used to connect
	 */
	public PlayerNetwork(Socket socket) {
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());		
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		(new Thread(this)).start();
		opponent = new PlayerNetwork();
	}
	/**
	 * It allows to create a PlayerNetwork
	 * @param socket
	 * The socket used to connect
	 * @param num
	 * The player's num
	 */
	public PlayerNetwork(Socket socket, int num) {
		this(socket);
		this.num = num;
	}

	@Override
	public void run() {
	}

	public boolean isGameIsFinished() {
		return gameIsFinished;
	}

	public void setGameIsFinished(boolean gameIsFinished) {
		this.gameIsFinished = gameIsFinished;
	}
}
