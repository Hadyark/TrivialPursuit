package view;

import javafx.geometry.Pos;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import util.Utility;

public class CheesesPane extends GridPane{

	private ImageView imageViewCybersecurity, imageViewInternet, imageViewNetwork;
	private ImageView imageViewOS, imageViewProgrammingLanguage, imageViewSocialNetwork;
	private double fitWidth = 50, fitHeight = 50;
	private ColorAdjust blackout, light;

	public CheesesPane(){
		this.add(getImageViewCybersecurity(), 0, 0);
		this.add(getImageViewInternet(), 1, 0);
		this.add(getImageViewNetwork(), 2, 0);
		this.add(getImageViewOS(), 3, 0);
		this.add(getImageViewProgrammingLanguage(), 4, 0);
		this.add(getImageViewSocialNetwork(), 5, 0);
		this.setAlignment(Pos.CENTER);
		this.setId("CheesesPane");
		this.setMaxSize(300, 50);
		Utility.neonEffect(this, Color.AQUAMARINE.toString());
	}

	public ImageView getImageViewCybersecurity() {
		if(imageViewCybersecurity == null) {
			Image image = new Image("/img/cheeseCybersecurity.png");
			imageViewCybersecurity = new ImageView(image);
			imageViewCybersecurity.setFitHeight(fitHeight);
			imageViewCybersecurity.setFitWidth(fitWidth);
			imageViewCybersecurity.setEffect(getBlackout());
		}
		return imageViewCybersecurity;
	}
	public ImageView getImageViewInternet() {
		if(imageViewInternet == null) {
			Image image = new Image("/img/cheeseInternet.png");
			imageViewInternet = new ImageView(image);
			imageViewInternet.setFitHeight(fitHeight);
			imageViewInternet.setFitWidth(fitWidth);
			imageViewInternet.setEffect(getBlackout());
		}
		return imageViewInternet;
	}
	public ImageView getImageViewNetwork() {
		if(imageViewNetwork == null) {
			Image image = new Image("/img/cheeseNetwork.png");
			imageViewNetwork = new ImageView(image);
			imageViewNetwork.setFitHeight(fitHeight);
			imageViewNetwork.setFitWidth(fitWidth);
			imageViewNetwork.setEffect(getBlackout());
		}
		return imageViewNetwork;
	}
	public ImageView getImageViewOS() {
		if(imageViewOS == null) {
			Image image = new Image("/img/cheeseOS.png");
			imageViewOS = new ImageView(image);
			imageViewOS.setFitHeight(fitHeight);
			imageViewOS.setFitWidth(fitWidth);
			imageViewOS.setEffect(getBlackout());
		}
		return imageViewOS;
	}
	public ImageView getImageViewProgrammingLanguage() {
		if(imageViewProgrammingLanguage == null) {
			Image image = new Image("/img/cheeseProgramming_Language.png");
			imageViewProgrammingLanguage = new ImageView(image);
			imageViewProgrammingLanguage.setFitHeight(fitHeight);
			imageViewProgrammingLanguage.setFitWidth(fitWidth);
			imageViewProgrammingLanguage.setEffect(getBlackout());
		}
		return imageViewProgrammingLanguage;
	}
	public ImageView getImageViewSocialNetwork() {
		if(imageViewSocialNetwork == null) {
			Image image = new Image("/img/cheeseSocial_Network.png");
			imageViewSocialNetwork = new ImageView(image);
			imageViewSocialNetwork.setFitHeight(fitHeight);
			imageViewSocialNetwork.setFitWidth(fitWidth);
			imageViewSocialNetwork.setEffect(getBlackout());
		}
		return imageViewSocialNetwork;
	}

	public ColorAdjust getBlackout() {
		if(blackout == null) {
			blackout = new ColorAdjust();
			blackout.setBrightness(-1.0);
		}
		return blackout;
	}
	public ColorAdjust getLight() {
		if(light == null) {
			light = new ColorAdjust();
			light.setBrightness(0.0);
		}
		return light;
	}
}
