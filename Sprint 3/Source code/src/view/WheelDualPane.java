package view;

import enumeration.Category;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import modele.Game;
import modele.Question;
import network.Action;
import network.PlayerClient;
import util.Play;
import util.Utility;

public class WheelDualPane extends StackPane {
	private ImageView imageViewWheel;
	Circle circleSpin;

	public WheelDualPane() {

		Color color = Color.web("0xFFE66D",1.0);

		Polygon selector = new Polygon();
		selector.getPoints().addAll(new Double[]{
				50.0, 30.0, 
				50.0, -30.0, 
				100.0, 0.0, 
		}); 
		selector.setFill(color);
		selector.setStroke(Color.BLACK);
		selector.setStrokeWidth(1);

		getCircleSpin().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub				
				int nb = Utility.nbRand(0, 6);
				if(Game.getInstanceGame().getMode().equals("Multi")) {
					getCircleSpin().setDisable(true);
					rotationWheel(nb);
				}else if(Game.getInstanceGame().getMode().equals("Local")) {
					network.PlayerClient.getInstanceClient().sendMessage(Action.WHEEL, nb);
				}
			}
		});

		this.getChildren().add(getImageViewWheel());
		this.getChildren().add(selector);
		selector.setTranslateX(50);
		this.getChildren().add(getCircleSpin());
		//this.setId("wheelPane2");
	}

	public Circle getCircleSpin() {
		if(circleSpin == null) {
			Color color = Color.web("0xFFE66D",1.0);
			circleSpin = new Circle(0, 0, 50);
			circleSpin.setFill(color);
			circleSpin.setStroke(Color.BLACK);
			circleSpin.setStrokeWidth(1);

		}
		return circleSpin;
	}

	public ImageView getImageViewWheel() {
		if(imageViewWheel == null) {
			Image image = new Image("/img/wheel.png");
			imageViewWheel = new ImageView(image);
			Utility.neonEffect(imageViewWheel, Color.AQUAMARINE.toString());
		}
		return imageViewWheel;
	}

	public void rotationWheel(int nb) {
		Double minAngle = 60.0;
		int cycle = Utility.nbRand(1, 30);
		Double angle = (minAngle * nb) + (360 * cycle);

		RotateTransition rt = new RotateTransition(Duration.millis(4000), getImageViewWheel());
		rt.setToAngle(angle);
		rt.setAutoReverse(false);
		rt.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Game game = Game.getInstanceGame();
				//Sort a random categ
				Category categ = Play.randCateg(nb);
				//Sort a random question
				Question currentquestion=game.getDeck().toDraw(categ);
				Game.getInstanceGame().setCurrentQuestion(currentquestion);
				Play.setQuestionOfPane(currentquestion);
				
				String color = Play.pickColorQuestion(categ);
				String style ="-fx-border-color:"+ color +";";
				
				
				
				WheelDualPane.this.setVisible(false);
				if(Game.getInstanceGame().getMode().equals("Multi")) {
					Utility.neonEffect(MainFx.getStackGamePane().getGameDualPane().getQuestionDualPane().getVboxQuestion(), color);
					MainFx.getStackGamePane().getGameDualPane().getQuestionDualPane().getVboxQuestion().setStyle(style);
					MainFx.getStackGamePane().getGameDualPane().getQuestionDualPane().setVisible(true);
					MainFx.getStackGamePane().getGameDualPane().getQuestionDualPane().startTimelineQuestion();
				}else {
					Utility.neonEffect(MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getVboxQuestion(), color);
					MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getVboxQuestion().setStyle(style);
					MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().setVisible(true);
					PlayerClient.getInstanceClient().sendMessage(Action.NEED_TIMER, null);
				}

			}
		});
		rt.play();

	}

}