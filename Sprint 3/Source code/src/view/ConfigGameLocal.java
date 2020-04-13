package view;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import modele.Account;
import network.PlayerClient;

public class ConfigGameLocal extends BorderPane{

	private Button btnHost,btnJoin;
	private CheckBox cbServer;
	private Button btnLevel,btnSearch;
	private Label lbVarYou, lbVarOpponent, lbMyIp;
	private VBox vbxHost, vbxJoin;
	private TextField txtIp;


	public ConfigGameLocal() {
		Label lbYou = new Label("You:");
		lbYou.setId("lbsimple");
		Label lbOpponent = new Label("Opponent:");
		lbOpponent.setId("lbsimple");

		vbxHost = new VBox();
		vbxHost.getChildren().addAll(getBtnLevel(),getCbServer(),getLbMyIp());
		vbxHost.setAlignment(Pos.CENTER);
		vbxHost.setVisible(false);

		vbxJoin = new VBox();
		vbxJoin.getChildren().addAll(getTxtIp(), getBtnSearch());
		vbxJoin.setSpacing(10);
		vbxJoin.setAlignment(Pos.CENTER);
		vbxJoin.setVisible(false);

		StackPane stack = new StackPane();
		stack.getChildren().addAll(vbxHost, vbxJoin);

		GridPane gp = new GridPane();
		gp.add(lbYou, 0, 0);
		gp.add(getLbVarYou(), 0, 1);
		gp.add(lbOpponent, 1, 0);
		gp.add(getLbVarOpponent(), 1, 1);
		gp.add(getBtnHost(), 0, 2);
		gp.add(getBtnJoin(), 1, 2);
		gp.setAlignment(Pos.CENTER);

		this.setCenter(gp);
		this.setBottom(stack);
	}

	public Label getLbMyIp() {
		if(lbMyIp == null) {
			String myIp = null;
			try {
				myIp = InetAddress.getLocalHost().getHostAddress().toString();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lbMyIp = new Label(myIp);
			lbMyIp.setId("lbsimple");
		}
		return lbMyIp;
	}
	public Button getBtnHost() {
		if(btnHost == null) {
			btnHost = new Button("Host");
			btnHost.setId("btnHost");
			btnHost.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					vbxHost.setVisible(true);
					vbxJoin.setVisible(false);
				}
			});
		}
		return btnHost;
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

	public CheckBox getCbServer() {
		if(cbServer == null) {
			cbServer = new CheckBox("Start server");
			cbServer.setId("lbsimple");
			cbServer.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if(cbServer.isSelected()) {
						System.out.println("start");

						try {
							network.ServerGame.startThreadServer();
							network.PlayerClient.getInstanceClient().startClient("localhost");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}else {

						try {
							System.out.println("stop");
							network.PlayerClient.getInstanceClient().setGameIsFinished(true);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
		}
		return cbServer;
	}
	public Button getBtnJoin() {
		if(btnJoin == null) {
			btnJoin = new Button("Join");
			btnJoin.setId("btnJoin");
			btnJoin.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					vbxHost.setVisible(false);
					vbxJoin.setVisible(true);
				}
			});
		}
		return btnJoin;
	}

	public TextField getTxtIp() {
		if(txtIp == null) {
			txtIp = new TextField();
			txtIp.setPromptText("Write the ip of the server");
			txtIp.setMaxWidth(300);
			txtIp.setId("btn");
		}
		return txtIp;
	}

	public Button getBtnSearch() {
		if(btnSearch == null) {
			btnSearch = new Button("Search");
			btnSearch.setId("btn");
			btnSearch.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						network.PlayerClient.getInstanceClient().startClient(getTxtIp().getText());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		return btnSearch;
	}
	public Label getLbVarYou() {
		if(lbVarYou == null) {
			lbVarYou = new Label(Account.getInstanceAccount().getId());

			lbVarYou.setId("lbLocal");
			lbVarYou.setTextFill(Color.LAWNGREEN);
		}
		return lbVarYou;
	}
	public Label getLbVarOpponent() {
		if(lbVarOpponent == null) {
			lbVarOpponent = new Label("Nobody");

			lbVarOpponent.setId("lbLocal");
			lbVarOpponent.setTextFill(Color.MEDIUMVIOLETRED);
		}
		return lbVarOpponent;	
	}

}
