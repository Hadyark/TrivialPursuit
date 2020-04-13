package view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.Deck;
import modele.ListAccount;
import util.Serialisation;

public class MenuAdminPane extends GridPane{
	private Button btnDeck,btnAccount, btnBack;
	private ArrayList arrayList;

	public MenuAdminPane() {
		VBox vbx = new VBox();
		vbx.setSpacing(10);
		vbx.setPadding(new Insets(15,20, 10,10));

		vbx.getChildren().add(getBtnDeck());	
		vbx.getChildren().add(getBtnAccount());
		vbx.getChildren().add(getBtnBack());

		this.setAlignment(Pos.CENTER);
		this.getChildren().add(vbx);
	}
	//Button to access the deck management
	public Button getBtnDeck() {
		if(btnDeck==null) {
			btnDeck = new Button("Deck (Admin)");
			btnDeck.setId("btnAdminMainMenu");
			btnDeck.setMaxWidth(Double.MAX_VALUE);
			btnDeck.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {						
					MainFx.getStackGamePane().getTablePaneDeck().setVisible(true);
					MainFx.getStackGamePane().getMainMenu().setVisible(false);

				}
			});
		}
		return btnDeck;
	}

	//Button to table
	public Button getBtnAccount() {
		if(btnAccount==null) {
			btnAccount = new Button("Account (Admin)");
			btnAccount.setId("btnAdminMainMenu");
			btnAccount.setMaxWidth(Double.MAX_VALUE);
			btnAccount.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					ListAccount listAccounts = (ListAccount) Serialisation.loadFile(ListAccount.class,"ListAccount_save");
					arrayList = listAccounts.getListAccount();
					MainFx.getStackGamePane().getChildren().add(MainFx.getStackGamePane().newTablePaneReflect(arrayList, listAccounts, true));
					MainFx.getStackGamePane().getMainMenu().setVisible(false);
				}
			});
		}
		return btnAccount;
	}

	//Button to back to the main menu
	public Button getBtnBack() {	
		if(btnBack==null) {
			btnBack=new Button("Back");
			btnBack.setId("btnAdminMainMenu");
			btnBack.setMaxWidth(Double.MAX_VALUE);
			btnBack.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					MenuAdminPane.this.setVisible(false);
					MainFx.getStackGamePane().getMainMenu().setVisible(true);;
				}
			});
		}
		return btnBack;
	}
}
