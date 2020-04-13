package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import modele.Game;
import util.Play;

public class QuestionSoloPane extends StackPane{
	private TextField txtFieldAnswer;
	private Label labelQuestion,labelCat, labelTimer; 
	private Button btnConfirm;
	private VBox vboxQuestion;
	private Timeline timeline;
	private static final Integer STARTTIME = 15;
	private Integer timeSeconds = STARTTIME;

	public QuestionSoloPane() {
		this.setPadding(new Insets(50));
		this.getChildren().add(getVboxQuestion());
		this.setId("questionPane");

	}

	public VBox getVboxQuestion() {
		if(vboxQuestion == null) {
			vboxQuestion = new VBox();
			vboxQuestion.getChildren().addAll(getLabelCat(),getLabelQuestion(), getTxtFieldAnswer(), getBtnConfirm());
			vboxQuestion.getChildren().addAll(getLabelTimer());
			vboxQuestion.setId("questionPaneVbx");
			vboxQuestion.setAlignment(Pos.CENTER);
		}
		return vboxQuestion;
	}
	//Label with the variable question
	public Label getLabelQuestion() {
		if(labelQuestion == null) {
			labelQuestion = new Label();
			labelQuestion.setId("labelQuestion");
		}
		return labelQuestion;
	}

	//TextField to write anwser
	public TextField getTxtFieldAnswer() {
		if( txtFieldAnswer == null) {
			txtFieldAnswer = new TextField();
			txtFieldAnswer.setPromptText("Write your answer");
			txtFieldAnswer.setId("txtFieldAnswer");
			txtFieldAnswer.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
		return txtFieldAnswer;
	}

	public Label getLabelCat() {
		if(labelCat == null) {
			labelCat = new Label();
			labelCat.setId("labelCat");
		}
		return labelCat;
	}

	public Button getBtnConfirm() {
		if(btnConfirm == null) {
			btnConfirm = new Button("Confirm");
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

	public void startTimeline() {
		if (timeline != null) {
			timeline.stop();
		}
		timeSeconds = STARTTIME;

		// update timerLabel
		getLabelTimer().setText(timeSeconds.toString());
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.seconds(1),
						new EventHandler() {
					@Override
					public void handle(Event arg0) {
						// TODO Auto-generated method stub
						timeSeconds--;
						// update timerLabel
						getLabelTimer().setText(timeSeconds.toString());
						if (timeSeconds <= 0) {
							confirmAnswer();
						}
					}
				}));
		timeline.playFromStart();
	}
	public Label getLabelTimer() {
		if(labelTimer == null) {
			labelTimer = new Label();
			labelTimer.setText(timeSeconds.toString());
			labelTimer.setFont(new Font("Arial", 30));
		}
		return labelTimer;
	}

	/**
	 * Confirm the answer
	 */
	public void confirmAnswer() {
		timeline.stop();
		if(this.getTxtFieldAnswer().getText().equals("") && timeSeconds <=0) {
			view.MainFx.getStackGamePane().getAlertInformation("Time out !").show();
		}else{
			Boolean response = Play.questionTime(Game.getInstanceGame().getCurrentQuestion(), getTxtFieldAnswer().getText(), Game.getInstanceGame().getDifficulty());
			if(response == false ) {
				util.Utility.getMediaPlayer("src/son/sonF.mp3").play();
				view.MainFx.getStackGamePane().getAlertInformation("You are bad !").showAndWait();
			}if(response == true) {
				util.Utility.getMediaPlayer("src/son/sonT.mp3").play();
				view.MainFx.getStackGamePane().getAlertInformation("Congratulations !").showAndWait();
			}
			Play.calculatePoint(Game.getInstanceGame().getCurrentQuestion(), response);
			getTxtFieldAnswer().clear();
		}
		Game.getInstanceGame().setArroundCurrent(Game.getInstanceGame().getArroundCurrent()+1);;
		MainFx.getStackGamePane().getGameSoloPane().getLabelArround().setText("Arround: " +Game.getInstanceGame().getArroundCurrent()+" / "+Game.getInstanceGame().getArroundMax());
		QuestionSoloPane.this.setVisible(false);
		MainFx.getStackGamePane().getGameSoloPane().getWheelPane().setVisible(true);
		MainFx.getStackGamePane().getGameSoloPane().getWheelPane().getCircleSpin().setDisable(false);

	}
}
