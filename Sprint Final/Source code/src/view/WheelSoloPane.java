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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import modele.Game;
import modele.Question;
import util.Play;
import util.Utility;

public class WheelSoloPane extends StackPane {
	private ImageView imageViewWheel;
	Circle circleSpin;
	
	public WheelSoloPane() {

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
				getCircleSpin().setDisable(true);
				rotationWheel(getImageViewWheel());
			}
		});

		this.getChildren().add(getImageViewWheel());
		this.getChildren().add(selector);
		selector.setTranslateX(50);
		this.getChildren().add(getCircleSpin());
		//this.setId("wheelPane");
		
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
			Image image = new Image("/img/wheelExtra.png");
			imageViewWheel = new ImageView(image);
			Utility.neonEffect(imageViewWheel, Color.AQUAMARINE.toString());
		}
		return imageViewWheel;
	}
	/**
	 * Rotate the wheel, choose a question and display questionPane
	 * @param object
	 * The object to rotate
	 */
	public void rotationWheel(Node object) {
		Double angle = 0.0;
		Double minAngle = 51.42857142857143;
		int nb = Utility.nbRand(0, 7);
		int cycle = Utility.nbRand(1, 30);
		angle = (minAngle * nb) + (360 * cycle);

		RotateTransition rt = new RotateTransition(Duration.millis(4000), object);
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
				if(nb == 6 ) {
					Play.extra();
					MainFx.getStackGamePane().getGameSoloPane().getWheelPane().getCircleSpin().setDisable(false);
				}else {
					//Sort a random categ
					Category categ = Play.randCateg(nb);
					//Sort a random question
					Question currentQuestion=game.getDeck().toDraw(categ);
					Game.getInstanceGame().setCurrentQuestion(currentQuestion);
					Play.setQuestionOfPane(currentQuestion);
					
					String color = Play.pickColorQuestion(categ);
					String style ="-fx-border-color:"+ color +";";
					
					Utility.neonEffect(MainFx.getStackGamePane().getGameSoloPane().getQuestionPane().getVboxQuestion(), color);
					 
					
					
					MainFx.getStackGamePane().getGameSoloPane().getQuestionPane().getVboxQuestion().setStyle(style);
					WheelSoloPane.this.setVisible(false);
					MainFx.getStackGamePane().getGameSoloPane().getQuestionPane().setVisible(true);
					MainFx.getStackGamePane().getGameSoloPane().getQuestionPane().startTimeline();
				}
			}
		});
		rt.play();

	}

}
