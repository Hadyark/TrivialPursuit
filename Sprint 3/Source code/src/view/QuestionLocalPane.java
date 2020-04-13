package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import modele.Game;
import network.Action;
import network.PlayerClient;

public class QuestionLocalPane extends StackPane implements EventHandler<KeyEvent>{
	private TextField txtFieldAnswerJ1,txtFieldAnswerJ2;
	private Label labelQuestion,labelCat, labelTimerQuestion,labelJ1,labelJ2, labelTimerP1, labelTimerP2; 
	private Button btnConfirm,btnBuzzJ1,btnBuzzJ2;
	private VBox vboxQuestion;
	private Timeline timelineQuestion, timelinePlayer;
	private static final Integer STARTTIMEQUESTION = 15, STARTTIMEPLAYER = 10;
	private Integer timeQuestionSeconds = STARTTIMEQUESTION, timePlayerSeconds = STARTTIMEPLAYER;
	private boolean first = false;

	public QuestionLocalPane() {
		this.setPadding(new Insets(50));
		this.getChildren().add(getVboxQuestion());
		this.setId("questionPane");
		this.setOnKeyPressed(this);

	}
	public VBox getVboxQuestion() {
		if(vboxQuestion == null) {

			GridPane gp = new GridPane();

			//Player1
			gp.add(getLabelJ1(), 0, 0);
			gp.add(getBtnBuzzJ1(), 0, 1);
			gp.add(getTxtFieldAnswerJ1(), 0, 2);
			gp.add(getLabelTimerP1(), 0, 3);

			GridPane.setHalignment(getLabelJ1(), HPos.CENTER);
			GridPane.setHalignment(getBtnBuzzJ1(), HPos.CENTER);
			GridPane.setHalignment(getTxtFieldAnswerJ1(), HPos.CENTER);
			GridPane.setHalignment(getLabelTimerP1(), HPos.CENTER);

			//Player2
			gp.add(getLabelJ2(), 2, 0);
			gp.add(getBtnBuzzJ2(), 2, 1);
			gp.add(getTxtFieldAnswerJ2(), 2, 2);
			gp.add(getLabelTimerP2(), 2, 3);

			GridPane.setHalignment(getLabelJ2(), HPos.CENTER);
			GridPane.setHalignment(getBtnBuzzJ2(), HPos.CENTER);
			GridPane.setHalignment(getTxtFieldAnswerJ2(), HPos.CENTER);
			GridPane.setHalignment(getLabelTimerP2(), HPos.CENTER);

			//BtnConfirm
			gp.add(getBtnConfirm(), 1, 3);

			gp.setAlignment(Pos.CENTER);

			txtFieldAnswerJ1.setVisible(false);
			labelTimerP1.setVisible(false);
			txtFieldAnswerJ2.setVisible(false);
			labelTimerP2.setVisible(false);
			btnConfirm.setVisible(false);

			vboxQuestion=new VBox();
			vboxQuestion.getChildren().add(getLabelQuestion());
			vboxQuestion.getChildren().add(getLabelTimerQuestion());
			vboxQuestion.getChildren().add(gp);	

			vboxQuestion.setAlignment(Pos.CENTER);

			vboxQuestion.setId("questionPaneVbx");
		}
		return vboxQuestion;
	}
	public Label getLabelCat() {
		if(labelCat == null) {
			labelCat = new Label();
			labelCat.setId("labelCat");
		}
		return labelCat;
	}

	//Label with the variable question
	public Label getLabelQuestion() {
		if(labelQuestion == null) {
			labelQuestion = new Label();
			labelQuestion.setId("labelQuestion");
		}
		return labelQuestion;
	}

	public Button getBtnBuzzJ1() {
		if(btnBuzzJ1 == null) {
			btnBuzzJ1 = new Button("Press SHIFT");
			btnBuzzJ1.setStyle("-fx-font: 30 arial;");
			btnBuzzJ1.setTextFill(Color.BLUE);

		}
		return btnBuzzJ1;
	}


	public Button getBtnBuzzJ2() {
		if(btnBuzzJ2 == null) {
			btnBuzzJ2 = new Button("Press UP");
			btnBuzzJ2.setStyle("-fx-font: 30 arial;");
			btnBuzzJ2.setTextFill(Color.RED);

		}
		return btnBuzzJ2;
	}

	public Label getLabelJ1() {
		if(labelJ1 == null) {
			labelJ1 = new Label();
			labelJ1.setStyle("-fx-font: 40 arial;");
			labelJ1.setTextFill(Color.BLUE);

		}
		return labelJ1;
	}

	public Label getLabelJ2() {
		if(labelJ2 == null) {
			labelJ2 = new Label();
			labelJ2.setStyle("-fx-font: 40 arial;");
			labelJ2.setTextFill(Color.RED);

		}
		return labelJ2;
	}

	public Button getBtnConfirm() {
		if(btnConfirm == null) {
			btnConfirm = new Button("Confirm");
			btnConfirm.setStyle("-fx-font: 20 arial;-fx-base: #696969;");

			btnConfirm.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					confirmAnswer();
				}
			});
		}
		return btnConfirm;
	}

	//TextField to write anwser
	public TextField getTxtFieldAnswerJ1() {
		if( txtFieldAnswerJ1 == null) {
			txtFieldAnswerJ1 = new TextField();
			txtFieldAnswerJ1.setPromptText("Write your answer");
			txtFieldAnswerJ1.setId("txtFieldAnswer");
			txtFieldAnswerJ1.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					// TODO Auto-generated method stub
					if (event.getCode().equals(KeyCode.ENTER))
					{
						confirmAnswer();
					}
				}
			});
		}
		return txtFieldAnswerJ1;
	}

	public TextField getTxtFieldAnswerJ2() {
		if( txtFieldAnswerJ2 == null) {
			txtFieldAnswerJ2 = new TextField();
			txtFieldAnswerJ2.setPromptText("Write your answer");
			txtFieldAnswerJ2.setId("txtFieldAnswer");
			txtFieldAnswerJ2.setDisable(true);
			txtFieldAnswerJ2.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					// TODO Auto-generated method stub
					if (event.getCode().equals(KeyCode.ENTER))
					{
						confirmAnswer();
					}
				}
			});
		}
		return txtFieldAnswerJ2;
	}

	public Label getLabelTimerQuestion() {
		if(labelTimerQuestion == null) {
			labelTimerQuestion = new Label();
			labelTimerQuestion.setText(timeQuestionSeconds.toString());
			labelTimerQuestion.setFont(new Font("Arial", 30));
		}
		return labelTimerQuestion;
	}

	public Label getLabelTimerP1() {
		if(labelTimerP1 == null) {
			labelTimerP1 = new Label();
			labelTimerP1.setText(timePlayerSeconds.toString());
			labelTimerP1.setFont(new Font("Arial", 20));
		}
		return labelTimerP1;
	}

	public Label getLabelTimerP2() {
		if(labelTimerP2 == null) {
			labelTimerP2 = new Label();
			labelTimerP2.setText(timePlayerSeconds.toString());
			labelTimerP2.setFont(new Font("Arial", 20));
		}
		return labelTimerP2;
	}
	private void confirmAnswer() {
		// TODO Auto-generated method stub
		PlayerClient.getInstanceClient().sendMessage(Action.ANSWER, getTxtFieldAnswerJ1().getText());
	}
	@Override
	public void handle(KeyEvent ke) {
		// TODO Auto-generated method stub
		KeyCode keyCode = ke.getCode();
		if(first == false  && keyCode.equals(KeyCode.SHIFT)) {{			
		}
		PlayerClient.getInstanceClient().sendMessage(Action.BUZZ, Game.getInstanceGame().getCurrentQuestion());
		}
	}
}
