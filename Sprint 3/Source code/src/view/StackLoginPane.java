package view;

import javafx.scene.layout.StackPane;

public class StackLoginPane extends StackPane{
	
	private LoginPane login;
	private CreateAccountPane createAccountPane;
	
	public LoginPane getLoginPane() {
		if(login==null) {
			login=new LoginPane();
		}
		return login;
	}
	
	public CreateAccountPane getCreateAccountPane() {
		if(createAccountPane==null) {
			createAccountPane=new CreateAccountPane();
		}
		return createAccountPane;
	}
	
	public StackLoginPane() {
		this.getChildren().addAll(getLoginPane(), getCreateAccountPane());
		getCreateAccountPane().setVisible(false);
		}
}
