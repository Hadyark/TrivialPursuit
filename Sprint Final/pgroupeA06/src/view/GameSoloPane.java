package view;

import enumeration.Category;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import modele.Game;
import modele.Player;
import util.Utility;


public class GameSoloPane extends BorderPane{

	private Label labelArround,labelScoreWord,labelScoreVar ; 
	private Label labelChainWord, labelChainVar, labelCheeses;
	private WheelSoloPane wheel;
	private QuestionSoloPane questionPane;
	private StackPane centerPane;
	private CheesesPane cheesesPane;

	//Constructor
	public GameSoloPane() {													 	            

		BorderPane gpTop = new BorderPane();
		VBox vbx1 = new VBox();
		vbx1.getChildren().addAll(getLabelScoreWord(),getLabelScoreVar());
		VBox vbx2 = new VBox();
		vbx2.getChildren().addAll(getLabelChainWord(),getLabelChainVar());


		gpTop.setLeft(vbx1);
		gpTop.setCenter(vbx2);
		vbx2.setAlignment(Pos.CENTER);
		gpTop.setRight(getLabelArround());
		//gpTop.setId("soloPaneGpTop");
		this.setTop(gpTop);


		this.setCenter(getCenterPane());

		//this.setBottom(getCheesesPane());
		BorderPane gpBot = new BorderPane();
		gpBot.setCenter(getCheesesPane());
		//gpBot.setId("soloPaneGpBot");
		this.setBottom(gpBot);
		setUp();

		this.setId("soloPane");
	}

	public StackPane getCenterPane() {
		if(centerPane == null) {
			centerPane = new StackPane();
			centerPane.getChildren().addAll(getWheelPane(),getQuestionPane());
			getQuestionPane().setVisible(false);
		}
		return centerPane;
	}

	public WheelSoloPane getWheelPane() {
		if(wheel == null) {
			wheel = new WheelSoloPane();
		}
		return wheel;
	}

	public CheesesPane getCheesesPane() {
		if(cheesesPane == null) {
			cheesesPane = new CheesesPane();
		}
		return cheesesPane;
	}

	public QuestionSoloPane getQuestionPane() {
		if(questionPane == null) {
			questionPane = new QuestionSoloPane();
		}
		return questionPane;
	}
	//Label "Score"
	public Label getLabelScoreWord() {
		if(labelScoreWord == null) {
			labelScoreWord = new Label("SCORE");
			Utility.neonEffect(labelScoreWord, Color.ORANGE.toString());
			labelScoreWord.setId("labelSolo");

		}
		return labelScoreWord;
	}

	//Label with the variable score
	public Label getLabelScoreVar() {
		if(labelScoreVar == null) {
			labelScoreVar = new Label();
			Utility.neonEffect(labelScoreVar, Color.ORANGE.toString());
			labelScoreVar.setId("labelSolo");
		}
		return labelScoreVar;
	}

	//Label "Chain Win"
	public Label getLabelChainWord(){
		if(labelChainWord == null) {
			labelChainWord = new Label("Chain Win");
			Utility.neonEffect(labelChainWord, Color.ORANGE.toString());
			labelChainWord.setId("labelSolo");
		}
		return labelChainWord;
	}

	//Label with the variable Chain Win
	public Label getLabelChainVar(){
		if(labelChainVar == null) {
			labelChainVar = new Label();
			Utility.neonEffect(labelChainVar, Color.ORANGE.toString());
			labelChainVar.setId("labelSolo");
		}
		return labelChainVar;
	}

	//Label "Arround" + variable arround
	public Label getLabelArround() {
		if(labelArround == null) {
			labelArround = new Label();
			Utility.neonEffect(labelArround, Color.ORANGE.toString());
			labelArround.setId("labelSolo");
		}
		return labelArround;
	} 	  	  		       	     	  

	//Label with the variable cheese
	public Label getLabelCheeses(){
		if(labelCheeses == null) {
			labelCheeses = new Label();
			Utility.neonEffect(labelCheeses, Color.ORANGE.toString());
			labelCheeses.setId("labelSolo");
		}
		return labelCheeses;
	}

	public void setUp() {
		Player player = Game.getInstanceGame().getPlayers().get(0);
		getLabelScoreVar().setText(String.valueOf(player.getScore()));
		getLabelChainVar().setText(String.valueOf(player.getChainWin())+ " ( Bonus: +"+player.getMultiplier()+")");
		getLabelArround().setText("Arround: " +Game.getInstanceGame().getArroundCurrent()+" / "+Game.getInstanceGame().getArroundMax());

		ImageView imageCheese = new ImageView();
		if (player.getCheeses().contains(Category.NETWORKS)) {
			imageCheese = getCheesesPane().getImageViewNetwork();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}if(player.getCheeses().contains(Category.OPERATING_SYSTEMS)) {
			imageCheese = getCheesesPane().getImageViewOS();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}if(player.getCheeses().contains(Category.CYBERSECURITY)) {
			imageCheese = getCheesesPane().getImageViewCybersecurity();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}if(player.getCheeses().contains(Category.PROGRAMMING_LANGUAGES)) {
			imageCheese = getCheesesPane().getImageViewProgrammingLanguage();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}if(player.getCheeses().contains(Category.INTERNET)) {
			imageCheese = getCheesesPane().getImageViewInternet();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}if(player.getCheeses().contains(Category.SOCIAL_NETWORKS)) {
			imageCheese = getCheesesPane().getImageViewSocialNetwork();
			imageCheese.setEffect(this.getCheesesPane().getLight());

		}
	}
}