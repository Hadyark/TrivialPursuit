package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;
import modele.Deck;
import modele.Game;
import modele.Question;
import util.Play;
import util.Serialisation;
import view.MainFx;

public class ServerGame {

	private int num= 0;
	private PlayerServer [] playerServers;
	private int ready;
	private Message message;
	private int timePlayerSeconds, timeQuestionSeconds, nbAnswer;
	private Timeline timelinePlayer, timelineQuestion;
	private Question currentQuestion;
	private boolean isBuzzed = false, isTimerQuestion = false;


	public ServerGame(PlayerServer[] playerServers) {
		this.playerServers = playerServers;
		this.ready = 0;
	}

	public void addPlayer(Socket socket) {
		playerServers[num] = new PlayerServer(this, socket, num);
		num++;
		if (num == 2) {
			Game game = new Game();
			Deck deck = new Deck();
			deck.setQuestions(Deck.getInstanceDeck().getQuestions());
			game.setDeck(deck);
			game.setDifficulty("Easy");
			game.setMode("Local");
			Collections.shuffle(game.getDeck().getQuestions());
			Message message = new Message(Action.GAME, game);
			preventEverybody(message);
			sayReady();
		}
	}

	public void sayReady() {
		for (PlayerServer p : playerServers) {
			p.sayReady();
		}
	}
	public void preventOpponent(int numero, Message message){
		try {
			System.out.println("ServerGame recoit: "+message);
			playerServers[1 - numero].out.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};		
	}
	public void preventEverybody(Message message){
		System.out.println("ServerGame recoit: "+message);
		for (PlayerServer p : playerServers) {
			try {
				p.out.writeObject(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	public void preventServerReady(Message message) {
		ready++;
		if(ready == 2) {			
			preventOpponent(0, message);
			preventOpponent(1, message);
			ready=0;nbAnswer=0;
			isTimerQuestion= false;

		}
	}

	public void startTimelineQuestion(int num) {
		if(!isTimerQuestion) {
			isTimerQuestion = true;

			timeQuestionSeconds = 15;
			// update timerLabel
			message = new Message(Action.TIMER_QUESTION, timeQuestionSeconds);
			preventOpponent(1-num, message);
			preventOpponent(0+num, message);

			timelineQuestion = new Timeline();
			timelineQuestion.setCycleCount(Timeline.INDEFINITE);
			timelineQuestion.getKeyFrames().add(
					new KeyFrame(Duration.seconds(1),
							new EventHandler() {
						@Override
						public void handle(Event arg0) {
							// TODO Auto-generated method stub
							timeQuestionSeconds--;
							// update timerLabel
							message = new Message(Action.TIMER_QUESTION, timeQuestionSeconds);
							preventOpponent(1-num, message);
							preventOpponent(0+num, message);
							if (timeQuestionSeconds <= 0) {
								message = new Message(Action.TIME_QUESTION_OUT, null);
								preventEverybody(message);
								timelineQuestion.stop();
							}
						}
					}));
			timelineQuestion.playFromStart();
		}
	}

	public void buzz(int num, Question question) {
		if(!isBuzzed) {
			timelineQuestion.stop();
			isBuzzed=true;
			timePlayerSeconds = 15;
			currentQuestion = question;

			// update timerLabel
			message = new Message(Action.TIMER_ME, timePlayerSeconds);
			preventOpponent(1-num, message);
			message = new Message(Action.TIMER_OPPONENT, timePlayerSeconds);
			preventOpponent(0+num, message);

			timelinePlayer = new Timeline();
			timelinePlayer.setCycleCount(Timeline.INDEFINITE);
			timelinePlayer.getKeyFrames().add(
					new KeyFrame(Duration.seconds(1),
							new EventHandler() {
						@Override
						public void handle(Event arg0) {
							// TODO Auto-generated method stub
							timePlayerSeconds--;
							// update timerLabel
							message = new Message(Action.TIMER_ME, timePlayerSeconds);
							preventOpponent(1-num, message);
							message = new Message(Action.TIMER_OPPONENT, timePlayerSeconds);
							preventOpponent(0+num, message);
							if (timePlayerSeconds <= 0) {
								message = new Message(Action.TIME_PLAYER_OUT, null);
								preventOpponent(1-num, message);
							}
						}
					}));
			timelinePlayer.playFromStart();
		}
	}

	public void answer(int num, String answer) {
		timelinePlayer.stop();
		nbAnswer++;
		if(Play.questionTime(currentQuestion, answer, Game.getInstanceGame().getDifficulty())) {
			message = new Message(Action.ANSWER_CORRECT_ME, null);
			preventOpponent(1-num, message);
			message = new Message(Action.ANSWER_CORRECT_OPPONENT, answer);
			preventOpponent(0+num, message);
		}else {
			message = new Message(Action.ANSWER_WRONG_ME, null);
			preventOpponent(1-num, message);
			message = new Message(Action.ANSWER_WRONG_OPPONENT, answer);
			preventOpponent(0+num, message);
			if(nbAnswer < 2) {
				timelineQuestion.playFromStart();
			}

		}
		isBuzzed = false;
	}

	public static void startThreadServer() throws Exception{			
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				int port = 4567;
				System.out.println("Start server");
				ServerSocket serversocket = null;
				try {
					serversocket = new ServerSocket(port);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				PlayerServer [] playerServers = new PlayerServer [2];
				ServerGame serveur = new ServerGame(playerServers);
				for (int n = 0; n < 2; n++)
					try {
						serveur.addPlayer(serversocket.accept());
						System.out.println("Nouvelle connexion");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				new ServerGame(playerServers);

			}
		});
		thread.setDaemon(true);
		thread.start();
	}

}
