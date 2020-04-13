package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.Account;
import modele.Deck;
import modele.Highscore;
import modele.ListAccount;
import util.Serialisation;
import util.Utility;

public class MenuGameOption extends GridPane {
	private Button resetNull,btnBack;
	private static CheckBox soundsNull;

	public MenuGameOption() {
		VBox vbx = new VBox();
		vbx.setSpacing(10);
		vbx.setPadding(new Insets(15,20, 10,10));

		vbx.getChildren().add(getResetNull());
		vbx.getChildren().add(getSoundsNull());
		vbx.getChildren().add(getBtnBack());

		this.setId("MainMenu");
		this.setAlignment(Pos.CENTER);
		this.getChildren().add(vbx);
	}
	public Button getResetNull() {		
		if(resetNull==null) {
			resetNull = new Button("Reset the game");
			resetNull.setMaxWidth(Double.MAX_VALUE);
			resetNull.setId("btnMainMenu");
			resetNull.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					Serialisation.reset("ListAccount_reset", "ListAccount_save", ListAccount.class);
					Serialisation.reset("Deck_reset", "Deck_save", Deck.class);
					Serialisation.reset("Highscore_reset", "Highscore_save", Highscore.class);
				}});
		}
		return resetNull;
	}
	public static CheckBox getSoundsNull() {
		soundsNull= new CheckBox("Mute the sounds");
		soundsNull.setId("label1");
		return soundsNull;
	}
	public Button getBtnBack() {	
		if(btnBack==null) {
			btnBack=new Button("Back");
			btnBack.setMaxWidth(Double.MAX_VALUE);
			btnBack.setId("btnMainMenu");
			btnBack.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					MenuGameOption.this.setVisible(false);
					MainFx.getStackGamePane().getMainMenu().setVisible(true);;
				}
			});
		}
		return btnBack;
	}
}
