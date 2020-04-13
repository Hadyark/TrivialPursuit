package view;

import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import modele.Account;
import modele.Game;
import util.Serialisation;
import util.Utility;

public class StackGamePane extends StackPane{


	private TablePaneDeck tablePaneDeck;
	private MenuMain mainMenu;
	private GameSoloPane gameSoloPane;
	private GameDualPane dualPane;
	private LoginPane login;
	private TablePaneReflect tablePane;
	private Alert alertOption, alertInformation;
	private ConfigGame configGame;
	private MenuAdminPane menuAdminPane;
	private MenuGameOption menuGameOption;

	public MenuAdminPane getMenuAdminPane() {
		if(menuAdminPane == null) {
			menuAdminPane = new MenuAdminPane();
		}
		return menuAdminPane;
	}
	public ConfigGame getConfigGame() {
		if(configGame == null) {
			configGame = new ConfigGame();
		}
		return configGame;
	}

	public ConfigGame newConfigGame() {
		configGame = new ConfigGame();
		return configGame;
	}

	public LoginPane getLoginPane() {
		if(login == null) {
			login = new LoginPane();
		}
		return login;	
	}

	public TablePaneDeck getTablePaneDeck() {
		if(tablePaneDeck == null) {
			tablePaneDeck = new TablePaneDeck();
		}
		return tablePaneDeck;
	}


	public MenuMain getMainMenu() {
		if(mainMenu == null) {
			mainMenu = new MenuMain();
			mainMenu.setId("mainMenu");
		}
		return mainMenu;
	}
	public MenuGameOption getMenuGameOption() {
		if(menuGameOption == null) {
			menuGameOption = new MenuGameOption();
		}
		return menuGameOption;
	}
	public GameSoloPane getGameSoloPane() {
		if(gameSoloPane == null) {
			gameSoloPane = new GameSoloPane();
		}
		return gameSoloPane;
	}
	public GameSoloPane newGameSoloPane() {
		gameSoloPane = new GameSoloPane();
		return gameSoloPane;
	}
	public GameDualPane getGameDualPane() {
		if(dualPane == null) {
			dualPane = new GameDualPane();
		}
		return dualPane;
	}
	public GameDualPane newDualPane() {
		dualPane = new GameDualPane();
		return dualPane;
	}
	public TablePaneReflect getTablePaneReflect() {
		return tablePane;
	}
	public TablePaneReflect newTablePaneReflect(ArrayList list, Object object, boolean isEditable) {
		tablePane = new TablePaneReflect(list, object, isEditable);
		return tablePane;
	}
	public Alert getAlertOption() {
		//if(alertOption == null) {
		alertOption = new Alert(AlertType.NONE);
		alertOption.setTitle("Option");
		alertOption.setHeaderText("Choose your option.");

		ButtonType buttonSave = new ButtonType("Save");
		ButtonType buttonMenu = new ButtonType("Return menu");
		ButtonType buttonExit = new ButtonType("Exit");
		ButtonType buttonClose = new ButtonType("Close", ButtonData.CANCEL_CLOSE);

		alertOption.getButtonTypes().setAll(buttonSave,buttonMenu, buttonExit, buttonClose);

		Optional<ButtonType> result = alertOption.showAndWait();
		if (result.get() == buttonSave) {
			// ... user chose "Save"
			String file = "Game_lastGame_"+Account.getInstanceAccount().getId();
			Serialisation.serializeObject(file, Game.getInstanceGame());
		} else if (result.get() == buttonMenu) {
			// ... user chose "Return the menu"
			hideAll();
			getMainMenu().setVisible(true);
		}else if (result.get() == buttonExit) {
			// ... user chose "Exit the game"
			System.exit(0);
		}
		else {
			// ... user chose Close or closed the dialog
			alertOption.close();
		}
		//}

		return alertOption;
	}

	public Alert getAlertInformation(String text) {
		alertInformation= new Alert (AlertType.INFORMATION);
		alertInformation.setTitle("Information");
		alertInformation.setHeaderText(null);
		alertInformation.setContentText(text);
		return alertInformation;

	}

	public StackGamePane() {
		this.getChildren().addAll(getMainMenu(),getMenuAdminPane(),getTablePaneDeck(), getMenuGameOption());
		hideAll();
		this.getMainMenu().setId("MainMenu");
		this.setId("MenuStackPane");
		this.getMainMenu().setVisible(true);

	}
	public void hideAll() {
		for(Node n: this.getChildren()) {
			n.setVisible(false);
		}
	}
}
