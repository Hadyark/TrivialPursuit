package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ConfigGameSolo extends BorderPane{

	private Button btnLevel;

	private Label difficulty;
	private Label lapsNumber;

	private Spinner<Integer> spinnerManches;

	public ConfigGameSolo() {

		VBox vbx = new VBox();
		vbx.getChildren().addAll(getDifficulty(),getBtnLevel(),getLapsNumber(),getTfManches());
		vbx.setAlignment(Pos.CENTER);

		vbx.setSpacing(20);
		this.setCenter(vbx);
		this.setId("Solo");

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

	public Label getDifficulty() {
		if(difficulty== null) {
			difficulty = new Label("Difficulty");
			difficulty.setId("difficulty");	

		}
		return difficulty;
	}

	public Label getLapsNumber() {
		if(lapsNumber== null) {
			lapsNumber = new Label("Laps number");
			lapsNumber.setId("laps");		

		}
		return lapsNumber;
	}
	public Spinner getTfManches() {
		if(spinnerManches == null) {

			spinnerManches = new Spinner<Integer>();
			spinnerManches.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
			int initialValue = 20;

			// Value factory.
			SpinnerValueFactory<Integer> valueFactory = //
					new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, initialValue);

			spinnerManches.setValueFactory(valueFactory);

		}

		return spinnerManches;
	}

}
