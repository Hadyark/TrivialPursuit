package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import enumeration.Category;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import modele.Account;
import modele.Deck;
import modele.Game;
import modele.Highscore;
import modele.Player;
import modele.Question;
import modele.Score;
import view.CheesesPane;
import view.GameDualPane;
import view.MainFx;
import view.GameSoloPane;

public class Play {

	/**
	 * Reset the game to zero
	 * @param mode
	 * Mode of the game
	 * @param fileDeck
	 * File of the deck
	 * @param difficulty
	 * Difficulty of the game
	 * @param players
	 * Players in the game
	 */
	public static void newGame(String mode, String fileDeck, String difficulty,ArrayList players) {
		//Reset variables of the game
		Deck deck = (Deck)Serialisation.loadFile(Deck.class,fileDeck);
		Game game = new Game();
		Game.getInstanceGame().setDeck(deck);
		Game.getInstanceGame().setDifficulty(difficulty);
		Game.getInstanceGame().setMode(mode);
		Game.getInstanceGame().setPlayers(players);
	}

	/**
	 * Method that manages an entire game turn
	 * @param question
	 * The current question
	 * @param response
	 * The player's current question
	 */
	public static void calculatePoint(Question question,boolean response) {
		GameSoloPane soloPane = view.MainFx.getStackGamePane().getGameSoloPane();
		Game game = Game.getInstanceGame();
		Player player=game.getPlayers().get(0);

		//Calculation of points
		if (response == true) {

			player.setChainWin(player.getChainWin()+1);

			//If 5 good answers in a row -> Multiplier +0.5
			if(player.getChainWin()%5 == 0) {
				player.setMultiplier(player.getMultiplier()+0.5);
			}

			//If player has not the cheese of the question, he win it
			if(!player.getCheeses().contains(question.getCategory())) {
				player.getCheeses().add(question.getCategory());
				soloPane.getLabelCheeses().setText(String.valueOf(player.getCheeses()));
				ImageView imageCheese = new ImageView();
				if (question.getCategory() == Category.NETWORKS) {
					imageCheese = soloPane.getCheesesPane().getImageViewNetwork();
				}else if(question.getCategory() == Category.OPERATING_SYSTEMS) {
					imageCheese = soloPane.getCheesesPane().getImageViewOS();
				}else if(question.getCategory() == Category.CYBERSECURITY) {
					imageCheese = soloPane.getCheesesPane().getImageViewCybersecurity();
				}else if(question.getCategory() == Category.PROGRAMMING_LANGUAGES) {
					imageCheese = soloPane.getCheesesPane().getImageViewProgrammingLanguage();
				}else if(question.getCategory() == Category.INTERNET) {
					imageCheese = soloPane.getCheesesPane().getImageViewInternet();
				}else if(question.getCategory() == Category.SOCIAL_NETWORKS) {
					imageCheese = soloPane.getCheesesPane().getImageViewSocialNetwork();
				}

				imageCheese.setEffect(MainFx.getStackGamePane().getGameSoloPane().getCheesesPane().getLight());

				//If player has all cheeses, win 2 points by good answer
				if (player.getCheeses().size()==6) {
					player.setPoint(2);
					soloPane.getLabelCheeses().setTextFill(Color.VIOLET);
					soloPane.getLabelCheeses().setText("You have all the cheese -> Good answer = 2pts");
					game.setArroundMax(game.getArroundMax()+5);
				}
			}
			soloPane.getLabelChainVar().setText(String.valueOf(player.getChainWin())+ " ( Bonus: +"+player.getMultiplier()+")");
			player.setScore(player.getScore() + player.getPoint() +(1 * player.getMultiplier()));
			soloPane.getLabelScoreVar().setTextFill(Color.GREEN);
			soloPane.getLabelScoreWord().setTextFill(Color.GREEN);
		}else {//Else wrong answer, reset bonus chainWin
			player.setChainWin(0);
			player.setMultiplier(0);
			soloPane.getLabelChainVar().setText("You are not in chain win");
			soloPane.getLabelScoreVar().setTextFill(Color.RED);
			soloPane.getLabelScoreWord().setTextFill(Color.RED);
		}

		//Set labels of gui
		soloPane.getLabelScoreVar().setText(String.valueOf(player.getScore()));

		//If he played 25 arround && if is not in chain win -> End game
		if(game.getArroundCurrent() > game.getArroundMax()-1 && player.getChainWin() < 5) {
			view.MainFx.getStackGamePane().getGameSoloPane().setVisible(false);
			view.MainFx.getStackGamePane().getMainMenu().setVisible(true);
			Highscore highscore= new  Highscore();
			try {
				highscore = (Highscore) Serialisation.loadFile(Highscore.class, "Highscore");

			} catch (Exception e) {
				// TODO: handle exception

			}
			Score score = new Score(Account.getInstanceAccount().getId(), Game.getInstanceGame().getDifficulty(), Game.getInstanceGame().getPlayers().get(0).getPoint(), Game.getInstanceGame().getArroundMax());
			highscore.getListHighscore().add(score);
			Serialisation.saveFile("Highscore", highscore);
		}

	}

	/**
	 * Choose a category randomly
	 * @param nb
	 * A random number
	 * @return nothing
	 */
	public static Category randCateg(int nb) {
		switch (nb) {
		case 0:
			return Category.CYBERSECURITY;
		case 1:
			return Category.OPERATING_SYSTEMS;
		case 2:
			return Category.SOCIAL_NETWORKS;
		case 3:
			return Category.INTERNET;
		case 4:
			return Category.NETWORKS;
		case 5:
			return Category.PROGRAMMING_LANGUAGES;
		default:
			return null;
		}
	}

	/**
	 * Method that randomly chooses a bonus or penalty
	 */
	public static void extra() {
		int nb1= Utility.nbRand(0, 6);
		Player player = Game.getInstanceGame().getPlayers().get(0);

		switch (nb1) {
		case 0:
			player.setPoint(player.getMultiplier()+0.25);
			MainFx.getStackGamePane().getGameSoloPane().getLabelChainVar().setText(String.valueOf(player.getChainWin())+ " ( Bonus: +"+player.getMultiplier()+")");
			MainFx.getStackGamePane().getAlertInformation("Bonus : pts by good answer +0.25").show();
			break;
		case 1: //+ 5 laps
			Game.getInstanceGame().setArroundMax(Game.getInstanceGame().getArroundMax()+5);
			MainFx.getStackGamePane().getGameSoloPane().getLabelArround().setText("Arround: " +Game.getInstanceGame().getArroundCurrent()+" / "+Game.getInstanceGame().getArroundMax());
			MainFx.getStackGamePane().getAlertInformation("Bonus : +5 laps").show();
			break;
		case 2:
			player.setScore(player.getScore()+5);
			MainFx.getStackGamePane().getGameSoloPane().getLabelScoreVar().setText(String.valueOf(player.getScore()));
			MainFx.getStackGamePane().getAlertInformation("Bonus : +5 pts").show();
			break;

		case 3:
			player.setPoint(player.getMultiplier()-0.25);
			MainFx.getStackGamePane().getGameSoloPane().getLabelChainVar().setText(String.valueOf(player.getChainWin())+ " ( Bonus: +"+player.getMultiplier()+")");
			MainFx.getStackGamePane().getAlertInformation("Penalty : pts by good answer -0.25").show();
			break;
		case 4:
			Game.getInstanceGame().setArroundMax(Game.getInstanceGame().getArroundMax()-5);
			MainFx.getStackGamePane().getGameSoloPane().getLabelArround().setText("Arround: " +Game.getInstanceGame().getArroundCurrent()+" / "+Game.getInstanceGame().getArroundMax());
			MainFx.getStackGamePane().getAlertInformation("Penalty : -5 laps").show();
			break;
		case 5:
			player.setScore(player.getScore()-5);
			MainFx.getStackGamePane().getGameSoloPane().getLabelScoreVar().setText(String.valueOf(player.getScore()));
			MainFx.getStackGamePane().getAlertInformation("Penalty : -5 pts").show();
			break;
		}
		Game.getInstanceGame().setArroundCurrent(Game.getInstanceGame().getArroundCurrent()+1);
		MainFx.getStackGamePane().getGameSoloPane().getLabelArround().setText("Arround: " +Game.getInstanceGame().getArroundCurrent()+" / "+Game.getInstanceGame().getArroundMax());
	}
	/**
	 * Refresh the question pane with the current question
	 * @param question
	 * The current question
	 */
	public static void setQuestionOfPane(Question question) {
		GameSoloPane soloPane;
		GameDualPane duelPane;
		String stringQuestion = Utility.resizeString(question.getStatement()) +"\n (Author: "+ question.getAuthor()+" / Answer: " + question.getAnswer()+")";

		if(Game.getInstanceGame().getMode().equals("Solo")){
			soloPane = view.MainFx.getStackGamePane().getGameSoloPane();
			soloPane.getQuestionPane().getLabelCat().setText(String.valueOf(question.getCategory()));
			soloPane.getQuestionPane().getLabelQuestion().setText(stringQuestion);
		}else if(Game.getInstanceGame().getMode().equals("Multi")){
			duelPane = view.MainFx.getStackGamePane().getGameDualPane();
			duelPane.getQuestionDualPane().getLabelCat().setText(String.valueOf(question.getCategory()));
			duelPane.getQuestionDualPane().getLabelQuestion().setText(stringQuestion);
		}else {
			duelPane = view.MainFx.getStackGamePane().getGameDualPane();
			duelPane.getQuestionLocalPane().getLabelCat().setText(String.valueOf(question.getCategory()));
			duelPane.getQuestionLocalPane().getLabelQuestion().setText(stringQuestion);
		}



	}

	/**
	 * Check the answer
	 * @param question
	 * The current question
	 * @param answer
	 * The player's answer
	 * @param difficulty
	 * The difficulty of the game
	 * @return the resultat
	 */
	public static boolean questionTime(Question question,String answer, String difficulty) {
		int concordance,margin = 0;
		GameSoloPane soloPane = view.MainFx.getStackGamePane().getGameSoloPane();		

		//UpperCase
		answer = answer.toUpperCase();
		String quest = question.getAnswer().toString().toUpperCase();

		//Check the match between the answer of the player and the answer of the question
		concordance=levenshtein(quest, answer);
		if(question.getAnswer().length() > 4) {
			switch (difficulty) {
			case "Easy":
				margin=2;
				break;
			case "Normal":
				margin=1;
				break;
			case "Hard":
				margin=0;
				break;
			default:
				break;
			}
		}else {
			margin = 0;
		}

		if (concordance <= margin && !answer.equals("")) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Method to compare 2 string
	 * @param str1
	 * The first string
	 * @param str2
	 * The second string
	 * @return The difference between the strings
	 */ 
	public static int levenshtein(String str1,  String str2) {
		int[][] distance = new int[str1.length() + 1][str2.length() + 1];        
		int substitution,i,j;
		for (i = 0; i <= str1.length(); i++)                                 
			distance[i][0] = i;                                                  
		for (j = 1; j <= str2.length(); j++)                                 
			distance[0][j] = j;                                                  

		for (i = 1; i <= str1.length(); i++) {                                 
			for (j = 1; j <= str2.length(); j++) {  
				if (str1.charAt(i-1) == str2.charAt(j-1)) {
					substitution = 0;
				}else {
					substitution = 1;
				}
				distance[i][j] = minimum(distance[i - 1][j] + 1, distance[i][j - 1] + 1,  distance[i - 1][j - 1] + substitution);
			}
		}
		return distance[str1.length()][str2.length()];                             
	}
	/**
	 * Method levenshtein need it
	 */
	private static int minimum(int a, int b, int c) {                            
		return Math.min(Math.min(a, b), c);                                      
	}  
	/**
	 * Refresh the color of the question pane with the color of the category
	 * @param categ
	 * Category of the current question
	 * @return The hexa color
	 */
	public static String pickColorQuestion(Category categ) {
		Color color = null;
		switch (categ) {
		case CYBERSECURITY:
			color = Color.LIMEGREEN;
			break;
		case INTERNET:
			color = Color.DEEPSKYBLUE;
			break;
		case SOCIAL_NETWORKS:
			color = Color.HOTPINK;
			break;
		case NETWORKS:
			color = Color.ORANGE;
			break;
		case PROGRAMMING_LANGUAGES:
			color = Color.RED;
			break;
		case OPERATING_SYSTEMS:
			color = Color.GREY;
			break;
		}
		return String.format( "#%02X%02X%02X",
				(int)( color.getRed() * 255 ),
				(int)( color.getGreen() * 255 ),
				(int)( color.getBlue() * 255 ) );
	}
}
