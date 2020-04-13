package view;

import enumeration.Category;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import modele.Game;
import modele.Player;
import modele.Question;
import network.Action;
import util.Utility;


public class GameDualPane extends BorderPane{

	private Label labelJ1, labelJ2,labelVS;

	private WheelDualPane wheel;
	private QuestionDuelPane questionPane;
	private QuestionLocalPane questionLocalPane;
	private StackPane centerPane;
	private CheesesPane cheesesPane, cheesesPane2;
	private TextField textFieldJ1, textFieldJ2;
	private Button btnConfirmJ1, btnConfirmJ2,btnStart;
	private boolean j1 = false, j2 = false;

	//Constructor
	public GameDualPane() {	

		VBox vbx1 = new VBox();
		vbx1.getChildren().addAll(getLabelJ1(),getCheesesPane());
		vbx1.setPadding(new Insets(15,20, 10,10));
		vbx1.setSpacing(30);
		vbx1.setAlignment(Pos.CENTER);
		VBox vbx2 = new VBox();
		vbx2.getChildren().addAll(getLabelJ2(),getCheesesPane2());
		vbx2.setPadding(new Insets(15,20, 10,10));
		vbx2.setSpacing(30);
		vbx2.setAlignment(Pos.CENTER);
		VBox vbxMid = new VBox();
		vbxMid.getChildren().addAll(getLabelVS());
		vbxMid.setPadding(new Insets(15,20, 10,10));
		vbxMid.setSpacing(30);
		vbxMid.setAlignment(Pos.CENTER);

		HBox hbx = new HBox();
		hbx.getChildren().addAll(vbx1,vbxMid,vbx2);
		hbx.setSpacing(10);
		hbx.setAlignment(Pos.CENTER);
		this.setTop(hbx);
		this.setCenter(getCenterPane());
		setUp();

		this.setId("duelPane");
	}

	public StackPane getCenterPane() {
		if(centerPane == null) {
			centerPane = new StackPane();	
			centerPane.getChildren().add(getWheelDualPane());
			if(Game.getInstanceGame().getMode().equals("Multi")) {
				centerPane.getChildren().add(getQuestionDualPane());
				getQuestionDualPane().setVisible(false);
			}else {
				centerPane.getChildren().add(getQuestionLocalPane());
				getQuestionLocalPane().setVisible(false);
			}

		}
		return centerPane;
	}

	public WheelDualPane getWheelDualPane() {
		if(wheel == null) {
			wheel = new WheelDualPane();
		}
		return wheel;
	}

	public CheesesPane getCheesesPane() {
		if(cheesesPane == null) {
			cheesesPane = new CheesesPane();
		}
		return cheesesPane;
	}
	public CheesesPane getCheesesPane2() {
		if(cheesesPane2 == null) {
			cheesesPane2 = new CheesesPane();
		}
		return cheesesPane2;
	}

	public QuestionDuelPane getQuestionDualPane() {
		if(questionPane == null) {
			questionPane = new QuestionDuelPane();
		}
		return questionPane;
	}
	public QuestionLocalPane getQuestionLocalPane() {
		if(questionLocalPane == null) {
			questionLocalPane = new QuestionLocalPane();
		}
		return questionLocalPane;
	}
	public Label getLabelJ1() {
		if(labelJ1==null) {
			labelJ1 = new Label("J1");

			labelJ1.setTextFill(Color.DODGERBLUE);
			labelJ1.setId("lbDuel");

		}
		return labelJ1;
	}

	public Label getLabelJ2() {
		if(labelJ2==null) {
			labelJ2 = new Label("J2");

			labelJ2.setTextFill(Color.web("#b30086"));
			labelJ2.setId("lbDuel");
		}
		return labelJ2;
	}

	public Label getLabelVS() {
		if(labelVS==null) {
			labelVS = new Label("VS");

			labelVS.setTextFill(Color.AQUAMARINE);
			Utility.neonEffect(labelVS, Color.AQUAMARINE.toString());
			labelVS.setId("lbDuel");
		}
		return labelVS;
	}


	public TextField getTextFieldJ1() {
		if(textFieldJ1 == null) {
			textFieldJ1 = new TextField();
			textFieldJ1.setPromptText("Write your name");
		}
		return textFieldJ1;
	}

	public TextField getTextFieldJ2() {
		if(textFieldJ2 == null) {
			textFieldJ2 = new TextField();
			textFieldJ2.setPromptText("Write your name");

		}
		return textFieldJ2;
	}

	public Button getBtnConfirmJ1() {
		if(btnConfirmJ1 == null) {
			btnConfirmJ1 = new Button("Confirm your name");

			btnConfirmJ1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if(!textFieldJ1.getText().equals("")) {
						labelJ1.setText(getTextFieldJ1().getText());
						textFieldJ1.setVisible(false);
						btnConfirmJ1.setVisible(false);
						j1 = true;

						Player p1 = new Player(getLabelJ1().getText());
						Game.getInstanceGame().getPlayers().add(p1);
					}
				}

			});
		}
		return btnConfirmJ1;

	}

	public Button getBtnConfirmJ2() {
		if(btnConfirmJ2 == null) {
			btnConfirmJ2 = new Button("Confirm your name");

			btnConfirmJ2.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if(!textFieldJ2.getText().equals("")) {
						labelJ2.setText(getTextFieldJ2().getText());
						textFieldJ2.setVisible(false);
						btnConfirmJ2.setVisible(false);
						j2 = true;

						Player p2 = new Player(getLabelJ2().getText());
						Game.getInstanceGame().getPlayers().add(p2);
					}
				}

			});
		}
		return btnConfirmJ2;

	}

	public void setCheesesPane(Question question, int playerIndex, boolean response) {
		CheesesPane cheesesPane;
		Player player = Game.getInstanceGame().getPlayers().get(playerIndex);

		if(playerIndex == 0) {
			cheesesPane = getCheesesPane();
		}else {
			cheesesPane = getCheesesPane2();
		}
		if(response == true) {
			if(player.addCheeses(question.getCategory()));

			ImageView imageCheese = new ImageView();
			if (question.getCategory() == Category.NETWORKS) {
				imageCheese = cheesesPane.getImageViewNetwork();
			}else if(question.getCategory() == Category.OPERATING_SYSTEMS) {
				imageCheese = cheesesPane.getImageViewOS();
			}else if(question.getCategory() == Category.CYBERSECURITY) {
				imageCheese = cheesesPane.getImageViewCybersecurity();
			}else if(question.getCategory() == Category.PROGRAMMING_LANGUAGES) {
				imageCheese = cheesesPane.getImageViewProgrammingLanguage();
			}else if(question.getCategory() == Category.INTERNET) {
				imageCheese = cheesesPane.getImageViewInternet();
			}else if(question.getCategory() == Category.SOCIAL_NETWORKS) {
				imageCheese = cheesesPane.getImageViewSocialNetwork();
			}

			imageCheese.setEffect(MainFx.getStackGamePane().getGameDualPane().getCheesesPane().getLight());
			if(player.getCheeses().size() == 6) {
				MainFx.getStackGamePane().getAlertInformation(player.getName()+" win!").showAndWait();
				view.MainFx.getStackGamePane().getMainMenu().setVisible(true);
				view.MainFx.getStackGamePane().getGameDualPane().setVisible(false);
				if(Game.getInstanceGame().getMode().equals("Local")) {
					network.PlayerClient.getInstanceClient().sendMessage(Action.GAME_IS_FINISHED, null);
				}
			}
		}
	}

	public void setUp() {
		Player p1 = Game.getInstanceGame().getPlayers().get(0);
		Player p2 = Game.getInstanceGame().getPlayers().get(1);
		getLabelJ1().setText(p1.getName());
		getLabelJ2().setText(p2.getName());

		ImageView imageCheese = new ImageView();
		if (p1.getCheeses().contains(Category.NETWORKS)) {
			imageCheese = getCheesesPane().getImageViewNetwork();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}if(p1.getCheeses().contains(Category.OPERATING_SYSTEMS)) {
			imageCheese = getCheesesPane().getImageViewOS();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}if(p1.getCheeses().contains(Category.CYBERSECURITY)) {
			imageCheese = getCheesesPane().getImageViewCybersecurity();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}if(p1.getCheeses().contains(Category.PROGRAMMING_LANGUAGES)) {
			imageCheese = getCheesesPane().getImageViewProgrammingLanguage();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}if(p1.getCheeses().contains(Category.INTERNET)) {
			imageCheese = getCheesesPane().getImageViewInternet();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}if(p1.getCheeses().contains(Category.SOCIAL_NETWORKS)) {
			imageCheese = getCheesesPane().getImageViewSocialNetwork();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}

		if (p2.getCheeses().contains(Category.NETWORKS)) {
			imageCheese = getCheesesPane2().getImageViewNetwork();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}if(p2.getCheeses().contains(Category.OPERATING_SYSTEMS)) {
			imageCheese = getCheesesPane2().getImageViewOS();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}if(p2.getCheeses().contains(Category.CYBERSECURITY)) {
			imageCheese = getCheesesPane2().getImageViewCybersecurity();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}if(p2.getCheeses().contains(Category.PROGRAMMING_LANGUAGES)) {
			imageCheese = getCheesesPane2().getImageViewProgrammingLanguage();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}if(p2.getCheeses().contains(Category.INTERNET)) {
			imageCheese = getCheesesPane2().getImageViewInternet();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}if(p2.getCheeses().contains(Category.SOCIAL_NETWORKS)) {
			imageCheese = getCheesesPane2().getImageViewSocialNetwork();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}
	}
}