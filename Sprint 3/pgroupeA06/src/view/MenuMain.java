package view;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.ExceptionFileNotFound;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.Account;
import modele.Deck;
import modele.Game;
import modele.Highscore;
import modele.ListAccount;
import modele.Player;
import util.Play;
import util.Serialisation;

public class MenuMain extends GridPane{
	private Button btnSolo, btnQuit, btnLoad,btnPlay,btnMenuAdmin,btnScoreBoard,btnOption;

	//Constructor
	public MenuMain() {

		VBox vbx = new VBox();
		vbx.setSpacing(10);
		vbx.setPadding(new Insets(15,20, 10,10));

		vbx.getChildren().add(getBtnPlay());	
		vbx.getChildren().add(getBtnLoad());
		vbx.getChildren().add(getBtnScoreboard());
		vbx.getChildren().add(getBtnMenuOption());
		if(Account.getInstanceAccount().isAdmin() == true) {
			vbx.getChildren().add(getBtnMenuAdminPane());
		}
		vbx.getChildren().add(getBtnQuit());

		this.setAlignment(Pos.CENTER);
		this.getChildren().add(vbx);

		Deck deck = (Deck) Serialisation.loadFile(Deck.class, "Deck_save");
		Deck.getInstanceDeck().setQuestions(deck.getQuestions());
		Deck.getInstanceDeck().saveStateAndAdd();
	}


	//Button to play
	public Button getBtnPlay() {
		if(btnPlay == null) {
			btnPlay = new Button("Play");
			btnPlay.setMaxWidth(Double.MAX_VALUE);
			btnPlay.setId("btnMainMenu");

			btnPlay.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					MainFx.getStackGamePane().getChildren().add(MainFx.getStackGamePane().newConfigGame());
					MenuMain.this.setVisible(false);
					MainFx.getStackGamePane().getConfigGame().setVisible(true);

				}
			});
		}
		return btnPlay;
	}

	public Button getBtnLoad() {
		if(btnLoad == null) {
			btnLoad = new Button("Load last game");
			btnLoad.setId("btnMainMenu");
			btnLoad.setMaxWidth(Double.MAX_VALUE);
			btnLoad.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					String file = "Game_lastGame_"+Account.getInstanceAccount().getId();
					Game game = null;
					try {
						game = (Game) Serialisation.deserializeObject(file);
						Game.initiInstanceGame(game);
						if(Game.getInstanceGame().getMode().equals("Solo")){
							MainFx.getStackGamePane().getChildren().add(MainFx.getStackGamePane().newGameSoloPane());
							MainFx.getStackGamePane().getGameSoloPane().setUp();
							MenuMain.this.setVisible(false);
						}else if(Game.getInstanceGame().getMode().equals("Multi")) {
							MainFx.getStackGamePane().getChildren().add(MainFx.getStackGamePane().newDualPane());
							MenuMain.this.setVisible(false);
						}			


					}catch (ExceptionFileNotFound e) {
						// TODO Auto-generated catch block
						MainFx.getStackGamePane().getAlertInformation(e.getMessage()).showAndWait();
					}		
				}
			});
		}
		return btnLoad;
	}
	//Button to table
	public Button getBtnScoreboard() {
		if(btnScoreBoard==null) {
			btnScoreBoard = new Button("Scoreboard");
			btnScoreBoard.setId("btnMainMenu");
			btnScoreBoard.setMaxWidth(Double.MAX_VALUE);
			btnScoreBoard.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					Highscore highscore = (Highscore) Serialisation.loadFile(Highscore.class,"Highscore_save");
					ArrayList arrayList = highscore.getListHighscore();
					MainFx.getStackGamePane().getChildren().add(MainFx.getStackGamePane().newTablePaneReflect(arrayList, highscore, false));
					MenuMain.this.setVisible(false);
				}
			});
		}
		return btnScoreBoard;
	}
	public Button getBtnMenuOption() {
		if(btnOption == null) {
			btnOption=new Button("Option");
			btnOption.setId("btnMainMenu");
			btnOption.setMaxWidth(Double.MAX_VALUE);
			btnOption.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					MenuMain.this.setVisible(false);
					MainFx.getStackGamePane().getMenuGameOption().setVisible(true);
				}
			});
		}
		return btnOption;
	}
	public Button getBtnMenuAdminPane() {
		if(btnMenuAdmin == null) {
			btnMenuAdmin=new Button("Admin");
			btnMenuAdmin.setId("btnMainMenu");
			btnMenuAdmin.setMaxWidth(Double.MAX_VALUE);
			btnMenuAdmin.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					MenuMain.this.setVisible(false);
					MainFx.getStackGamePane().getMenuAdminPane().setVisible(true);
				}
			});
		}
		return btnMenuAdmin;
	}
	//Button to quit the game
	public Button getBtnQuit() {	
		if(btnQuit==null) {
			btnQuit=new Button();
			btnQuit.setText("Quit");
			btnQuit.setId("btnMainMenu");
			btnQuit.setMaxWidth(Double.MAX_VALUE);
			btnQuit.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					System.exit(0);
				}
			});
		}
		return btnQuit;
	}

}
