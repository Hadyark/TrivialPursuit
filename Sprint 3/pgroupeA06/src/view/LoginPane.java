package view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.Utility;

public class LoginPane extends StackPane{


	private TextField tfUser;
	private PasswordField passwordField;
	private Label labelUser, labelPassword, labelCreateAccount, labelVerdict;
	private Button btnGuest, btnLog;

	public LoginPane() {
		GridPane gp = new GridPane();
		gp.add(getLabelUser(), 0, 0);
		gp.add(getTFUser(), 1, 0);
		gp.add(getLabelPassword(), 0, 1);
		gp.add(getPasswordField(), 1, 1);
		gp.add(getLabelCreateAccount(), 1, 2);
		gp.add(getBtnGuest(), 0, 3);
		gp.add(getBtnLog(), 1, 3);

		gp.setAlignment(Pos.CENTER);

		this.getChildren().add(gp);

		this.setId("LoginPane");

	}

	public TextField getTFUser() {
		if(tfUser==null) {
			tfUser = new TextField();
		}
		return tfUser;
	}

	public PasswordField getPasswordField() {
		if(passwordField==null) {
			passwordField = new PasswordField();
		}
		return passwordField;
	}

	public Label getLabelUser() {
		if(labelUser==null) {
			labelUser = new Label("Username :");
			labelUser.setId("LabelLogin");
		}
		return labelUser;
	}

	public Label getLabelPassword() {
		if(labelPassword==null) {
			labelPassword = new Label("Password :");
			labelPassword.setId("LabelLogin");
		}
		return labelPassword;
	}

	public Label getLabelCreateAccount() {
		if(labelCreateAccount == null) {
			labelCreateAccount = new Label("Create new account");
			labelCreateAccount.setId("HyperLink");
			labelCreateAccount.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					LoginPane.this.setVisible(false);
					MainFx.getStackLoginPane().getCreateAccountPane().setVisible(true);
				}
			});
		}
		return labelCreateAccount;
	}

	public Button getBtnGuest() {
		if(btnGuest==null) {
			btnGuest = new Button("Play as guest");
			btnGuest.setStyle("-fx-font: 20 arial;-fx-base: #696969;");

			btnGuest.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub

					if(!Utility.authentification("guest","")) {
						getLabelVerdict().setVisible(true);
					}
					getTFUser().clear();
					getPasswordField().clear();

				}
			});

		}
		return btnGuest;
	}

	public Button getBtnLog() {
		if(btnLog==null) {
			btnLog = new Button("Log in");
			btnLog.setStyle("-fx-font: 20 arial;-fx-base: #696969;");

			btnLog.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub

					if(!Utility.authentification(getTFUser().getText(),getPasswordField().getText())) {
						getLabelVerdict().setVisible(true);
					}
					getTFUser().clear();
					getPasswordField().clear();

				}
			});

		}
		return btnLog;
	}

	public Label getLabelVerdict() {
		if(labelVerdict == null) {
			labelVerdict = new Label("Wrong account or password");
			labelVerdict.setTextFill(Color.RED);
			labelVerdict.setVisible(false);;

		}
		return labelVerdict;
	}
}
