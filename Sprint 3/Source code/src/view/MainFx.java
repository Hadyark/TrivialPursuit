package view;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modele.Deck;
import util.Serialisation;
import util.Utility;

public class MainFx extends Application {

	private static StackGamePane stackGamePane;
	private static StackLoginPane stackLoginPane;
	private static Stage primaryStage;

	public static StackGamePane getStackGamePane() {
		if(stackGamePane==null) {
			stackGamePane=new StackGamePane();
		}
		return stackGamePane;
	}

	public static StackLoginPane getStackLoginPane() {
		if(stackLoginPane==null) {
			stackLoginPane=new StackLoginPane();
		}
		return stackLoginPane;
	}

	public static Stage getPrimaryStage() {
		if(primaryStage==null) {
			primaryStage=new Stage();
		}
		return primaryStage;
	}
	public static void secondaryStage() {
		Stage stage = new Stage();
		Scene scene = new Scene(view.MainFx.getStackGamePane(),800,800);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {
				// TODO Auto-generated method stub
				if(keyEvent.getCode() == KeyCode.ESCAPE && (view.MainFx.getStackGamePane().getGameSoloPane().isVisible() || view.MainFx.getStackGamePane().getGameDualPane().isVisible())) {
					view.MainFx.getStackGamePane().getAlertOption();
				}
			}
		});
		scene.getStylesheets().add("view/application.css");
		stage.setScene(scene);
		stage.setTitle("Trivial Pursuit");
		getPrimaryStage().close();
		stage.show();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Scene scene = new Scene(getStackLoginPane(),400.,400.);

			scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
			getPrimaryStage().setScene(scene);
			getPrimaryStage().setTitle("Trivial Pursuit : The World of Computing ");
			getPrimaryStage().show();			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
