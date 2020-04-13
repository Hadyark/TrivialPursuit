package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConfigGameDual extends BorderPane{

	private Button btnLevel;
	private Label difficulty,label1,label2;
	private TextField tf1;
	private TextField tf2;

	public ConfigGameDual() {

		HBox hbx1 = new HBox();
		hbx1.setSpacing(140);
		hbx1.setAlignment(Pos.CENTER);
		hbx1.getChildren().addAll(getLabel1(),getLabel2());

		HBox hbx2 = new HBox();
		hbx2.setSpacing(50);
		hbx2.setAlignment(Pos.CENTER);
		hbx2.getChildren().addAll(getTf1(),getTf2());


		VBox vbx = new VBox();
		vbx.getChildren().addAll(getDifficulty(),getBtnLevel(),hbx1,hbx2);
		vbx.setAlignment(Pos.CENTER);

		vbx.setSpacing(20);
		this.setCenter(vbx);
		this.setId("Multi");

	}


	public Button getBtnLevel() {
		if(btnLevel == null) {
			btnLevel = new Button("Easy");
			btnLevel.setMinWidth(250);
			btnLevel.setId("btnLevel");
		}



		btnLevel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				if(btnLevel.getText() == "Easy") {
					btnLevel.setText("Normal");
				}
				else if(btnLevel.getText() == "Normal") {
					btnLevel.setText("Hard");
				}
				else if(btnLevel.getText() == "Hard") {
					btnLevel.setText("Easy");
				}


			}
		});

		return btnLevel;
	}



	public Label getLabel1() {
		if(label1 == null) {
			label1 = new Label("Player 1");
			label1.setId("label1");

		}
		return label1;
	}

	public Label getLabel2() {
		if(label2 == null) {
			label2 = new Label("Player 2");
			label2.setId("label2");

		}
		return label2;
	}

	public Label getDifficulty() {
		if(difficulty== null) {
			difficulty = new Label("Difficulty");
			difficulty.setId("difficulty");


		}
		return difficulty;
	}


	public TextField getTf1() {
		if(tf1 == null) {
			tf1 = new TextField("");
			tf1.setMaxWidth(250);
			tf1.setId("tf1");
			tf1.setPromptText("Write your name");
		}
		return tf1;
	}

	public TextField getTf2() {
		if(tf2 == null) {
			tf2 = new TextField("");
			tf2.setMaxWidth(250);
			tf2.setId("tf2");
			tf2.setPromptText("Write your name");
		}
		return tf2;
	}
}