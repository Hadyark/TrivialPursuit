package view;

import java.lang.invoke.SerializedLambda;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import modele.Account;
import modele.ListAccount;
import util.Serialisation;
import util.Utility;

public class CreateAccountPane extends StackPane{

	private Label labelAccount ,labelPassword ,labelMail, labelInfo;
	private TextField fieldAccount, fieldMail;
	private PasswordField fieldPassword;
	private Button btnConfirm, btnReturn;

	public CreateAccountPane() {
		GridPane gp = new GridPane();
		gp.add(getLabelAccount(), 0, 0);
		gp.add(getFieldAccount(), 1, 0);
		gp.add(getLabelPassword(), 0, 1);
		gp.add(getfieldPassword(), 1, 1);
		gp.add(getLabelMail(), 0, 2);
		gp.add(getFieldMail(), 1, 2);
		gp.add(getBtnConfirm(), 1, 3);
		gp.add(getBtnReturn(), 0, 3);
		gp.add(getLabelInfo(), 0, 4);

		gp.setAlignment(Pos.CENTER);

		this.getChildren().add(gp);
		this.setId("LoginPane");
	}

	public Label getLabelAccount() {
		if(labelAccount == null) {
			labelAccount = new Label("Account:");
			labelAccount.setId("LabelLogin");
		}
		return labelAccount;
	}

	public TextField getFieldAccount() {
		if(fieldAccount == null) {
			fieldAccount = new TextField();
		}
		return fieldAccount;
	}

	public Label getLabelPassword() {
		if(labelPassword == null) {
			labelPassword = new Label("Password:");
			labelPassword.setId("LabelLogin");
		}
		return labelPassword;
	}

	public PasswordField getfieldPassword() {
		if(fieldPassword == null) {
			fieldPassword = new PasswordField();
		}
		return fieldPassword;
	}

	public Label getLabelMail() {
		if(labelMail == null) {
			labelMail = new Label("Mail:");
			labelMail.setId("LabelLogin");
		}
		return labelMail;
	}

	public TextField getFieldMail() {
		if(fieldMail == null) {
			fieldMail = new TextField("");
		}
		return fieldMail;
	}

	public Button getBtnConfirm() {
		if(btnConfirm == null) {
			btnConfirm = new Button("Confirm");
			btnConfirm.setStyle("-fx-font: 20 arial;-fx-base: #696969;");
			btnConfirm.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					Account account = new Account(getFieldAccount().getText(), getfieldPassword().getText(), getFieldMail().getText(), false);
					ListAccount listAccounts = (ListAccount) Serialisation.loadFile(ListAccount.class,"ListAccount_save");

					if(listAccounts.addAccount(account)){
						if(!getFieldMail().getText().equals("")) {
							getLabelInfo().setText("Wait.");
							getLabelInfo().setTextFill(Color.WHITE);
							if (Utility.sendMail(getFieldMail().getText(), account)) {
								Serialisation.saveFile("ListAccount_save", listAccounts);
								getLabelInfo().setText("Done.");
								getLabelInfo().setTextFill(Color.GREEN);
							}else {
								getLabelInfo().setText("Wrong mail.");
								getLabelInfo().setTextFill(Color.RED);
							}
						}else {
							Serialisation.saveFile("ListAccount_save", listAccounts);
							getLabelInfo().setText("Done.");
							getLabelInfo().setTextFill(Color.GREEN);
						}

					}else {
						getLabelInfo().setText("Account already exists.");
						getLabelInfo().setTextFill(Color.RED);
					}




				}
			});
		}
		return btnConfirm;
	}

	public Button getBtnReturn() {
		if(btnReturn == null) {
			btnReturn = new Button("Return");
			btnReturn.setStyle("-fx-font: 20 arial;-fx-base: #696969;");
			btnReturn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					CreateAccountPane.this.setVisible(false);
					view.MainFx.getStackLoginPane().getLoginPane().setVisible(true);
				}
			});
		}
		return btnReturn;
	}
	public Label getLabelInfo() {
		if(labelInfo == null) {
			labelInfo = new Label();
		}
		return labelInfo;
	}
}
