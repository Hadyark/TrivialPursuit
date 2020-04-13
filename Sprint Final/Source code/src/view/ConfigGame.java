package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modele.Account;
import modele.Game;
import modele.Player;
import network.Action;
import network.PlayerClient;
import util.Play;


public class ConfigGame extends BorderPane {

	private Button btnMode,btnStart,btnReturn;
	private ConfigGameSolo configGameSolo;
	private ConfigGameDual configGameDual;
	private ConfigGameLocal configGameLocal;

	private VBox vbxS = new VBox();
	private VBox vbxM = new VBox();
	private VBox vbx = new VBox();

	public ConfigGame() {


		vbxM.getChildren().add(getBtnMode());
		vbxM.setAlignment(Pos.CENTER);
		vbxM.setPadding(new Insets(140,0,0,0));

		vbxS.getChildren().addAll(getBtnStart(),getBtnReturn());
		vbxS.setAlignment(Pos.CENTER);
		vbxS.setSpacing(30);
		vbxS.setPadding(new Insets(0,0,80,0));

		vbx.getChildren().add(getConfigGameSolo());
		vbx.setAlignment(Pos.CENTER);


		this.setTop(vbxM);
		this.setCenter(vbx);
		this.setBottom(vbxS);
		this.setId("Jeux");
	}

	public ConfigGameSolo getConfigGameSolo() {
		if(configGameSolo == null) {
			configGameSolo = new ConfigGameSolo();
		}
		return configGameSolo;
	}
	public ConfigGameDual getMultiOption() {
		if(configGameDual == null) {
			configGameDual = new ConfigGameDual();
		}
		return configGameDual;
	}
	public ConfigGameLocal getLocalption() {
		if(configGameLocal == null) {
			configGameLocal = new ConfigGameLocal();
		}
		return configGameLocal;
	}

	public Button getBtnMode() {
		if(btnMode == null) {
			btnMode = new Button("< Solo >");
			btnMode.setMinWidth(250);
			btnMode.setId("btnMode");

		}

		btnMode.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				if(btnMode.getText() == "< Solo >") {
					btnMode.setText("< Multiplayer >");
					vbx.getChildren().remove(getConfigGameSolo());
					vbx.getChildren().add(getMultiOption());

				}
				else if(btnMode.getText() == "< Multiplayer >") {
					btnMode.setText("< Local >");
					vbx.getChildren().remove(getMultiOption());
					vbx.getChildren().add(getLocalption());
					getBtnStart().setDisable(true);

				}
				else if(btnMode.getText() == "< Local >") {
					btnMode.setText("< Solo >");
					vbx.getChildren().remove(getLocalption());
					vbx.getChildren().add(getConfigGameSolo());
					getBtnStart().setDisable(false);
				}
			}
		});

		return btnMode;
	}


	public Button getBtnStart() {
		if(btnStart == null) {
			btnStart = new Button("START");
			btnStart.setMinWidth(250);
			btnStart.setId("btnStart");


			btnStart.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if(btnMode.getText()=="< Solo >") {
						Player p1 = new Player(Account.getInstanceAccount().getId());
						ArrayList<Player> players = new ArrayList<>();
						players.add(p1);
						Play.newGame("Solo", "Deck_save", getConfigGameSolo().getBtnLevel().getText(),players);
						Game.getInstanceGame().setArroundMax((Integer)getConfigGameSolo().getTfManches().getValue());
						Collections.shuffle(Game.getInstanceGame().getDeck().getQuestions());
						MainFx.getStackGamePane().getChildren().add(MainFx.getStackGamePane().newGameSoloPane());
						ConfigGame.this.setVisible(false);

					}
					else if(btnMode.getText()=="< Multiplayer >") {
						Player p1 = new Player(getMultiOption().getTf1().getText());
						Player p2 = new Player(getMultiOption().getTf2().getText());
						ArrayList<Player> players = new ArrayList<>();
						players.add(p1);
						players.add(p2);
						Play.newGame("Multi", "Deck_save",getMultiOption().getBtnLevel().getText(),players);
						Collections.shuffle(Game.getInstanceGame().getDeck().getQuestions());
						MainFx.getStackGamePane().getChildren().add(MainFx.getStackGamePane().newDualPane());
						ConfigGame.this.setVisible(false);

					}else if(btnMode.getText()=="< Local >") {
						network.PlayerClient.getInstanceClient().sendMessage(Action.READY_TO_PLAY, null);
					}
				}
			});

		}
		return btnStart;
	}

	public Button getBtnReturn() {
		if(btnReturn == null) {
			btnReturn = new Button("RETURN");
			btnReturn.setMinWidth(250);
			btnReturn.setId("btnStart");

			btnReturn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					ConfigGame.this.setVisible(false);
					MainFx.getStackGamePane().getMainMenu().setVisible(true);

				}

			});

		}
		return btnReturn;
	}

}

