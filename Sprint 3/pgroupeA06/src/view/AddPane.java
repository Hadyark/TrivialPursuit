package view;

import enumeration.Category;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import modele.Deck;
import modele.Question;

public class AddPane extends GridPane{

	private TextField fieldAnswer, fieldStatement, fieldAuthor;
	private Button btnAdd;
	private ComboBox comboCateg;

	//Constructor
	public AddPane() {
		Label labelAnswer = new Label("Answer");
		Label labelStatement = new Label("Statement");
		Label labelCategory = new Label("Category");
		Label labelAuthor = new Label("Author");
		this.add(labelAuthor, 0, 0);
		this.setHalignment(labelAuthor, HPos.CENTER);
		this.add(getFieldAuthor(), 0, 1);
		this.add(labelCategory,1, 0);
		this.setHalignment(labelCategory, HPos.CENTER);
		this.add(getComboCateg(), 1, 1);
		this.add(labelStatement, 2, 0);
		this.setHalignment(labelStatement, HPos.CENTER);
		this.add(getFieldStatement(), 2, 1);
		this.add(labelAnswer, 3, 0);
		this.setHalignment(labelAnswer, HPos.CENTER);
		this.add(getFieldAnswer(), 3, 1);
		this.add(getBtnAdd(), 4, 1);

		this.setId("AddPane");

	}
	//Field asking the answer of the question to be added
	public TextField getFieldAnswer() {
		if(fieldAnswer == null) {
			fieldAnswer = new TextField();
		}
		return fieldAnswer;
	}
	//Field asking the statement of the question to be added
	public TextField getFieldStatement() {
		if(fieldStatement == null) {
			fieldStatement = new TextField();
		}
		return fieldStatement;
	}
	//Field asking the author of the question to be added
	public TextField getFieldAuthor() {
		if(fieldAuthor == null) {
			fieldAuthor = new TextField();
		}
		return fieldAuthor;
	}
	//ComboBox asking the category of the question to be added
	public ComboBox getComboCateg(){
		if(comboCateg==null) {
			comboCateg = new ComboBox();
			comboCateg.setItems( FXCollections.observableArrayList(Category.values()));
		}
		return comboCateg;
	}
	//Add the question in the deck
	public Button getBtnAdd() {
		if(btnAdd == null) {
			btnAdd = new Button("Add");
			btnAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub

					Category categ =(Category) getComboCateg().getValue();	
					Question q = new Question(getFieldAuthor().getText(), categ, getFieldStatement().getText(), getFieldAnswer().getText());
					if(Deck.getInstanceDeck().addQuestion(q)) {
						MainFx.getStackGamePane().getTablePaneDeck().getTableView().getItems().add(q);
						getFieldAuthor().clear();
						getComboCateg().getSelectionModel().clearSelection();
						getFieldStatement().clear();
						getFieldAnswer().clear();
					}else {
						MainFx.getStackGamePane().getAlertInformation("The question already exists").show();
					}

				}
			});
		}
		return btnAdd;
	}

}
