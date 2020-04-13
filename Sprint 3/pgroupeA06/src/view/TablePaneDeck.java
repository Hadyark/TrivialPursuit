package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import modele.Deck;
import modele.Question;
import util.Serialisation;

public class TablePaneDeck extends BorderPane{
	private Button btnSave,btnDelete, btnBack, btnUndo;
	private AddPane addPane;
	private TableView<Question> table;
	private ObservableList<Question> listQuestObser;
	
	//Constructor
	public TablePaneDeck() {
		HBox hbx = new HBox();
		hbx.getChildren().addAll(getBtnSave(), getBtnDelete(),getBtnBack(), getBtnUndo());
		hbx.setAlignment(Pos.CENTER);
		getAddPane().setAlignment(Pos.CENTER);
		
		this.setCenter(getTableView());
		this.setBottom(hbx);
		this.setTop(getAddPane());
		
	}

	public AddPane getAddPane() {
		if(addPane == null) {
			addPane = new AddPane();
		}
		return addPane;
	}
	
	//Button to save the deck
	public Button getBtnSave() {	
		if(btnSave==null) {
			btnSave=new Button();
			btnSave.setText("Save");
			btnSave.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Deck.getInstanceDeck().setQuestions(table.getItems());
					Deck deck = new Deck();
					deck.setQuestions(table.getItems());
					Serialisation.saveFile("Deck_save", deck);
				}
			});
		}
		return btnSave;
	}
	
	//Button to delete a question
	public Button getBtnDelete() {
		if(btnDelete == null) {
			btnDelete = new Button("Delete");
			btnDelete.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
					saveMemento();
				}

			});
		}
		return btnDelete;
	}
	
	public Button getBtnUndo() {	
		if(btnUndo==null) {
			btnUndo=new Button();
			btnUndo.setText("Undo");
			btnUndo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Deck.getInstanceDeck().undo();
					listQuestObser.removeAll(listQuestObser);
					
					for (Question question : Deck.getInstanceDeck().getQuestions()) {
						listQuestObser.add(question);
					}
				}
			});
		}
		return btnUndo;
	}
	//Button to back to the main menu
	public Button getBtnBack() {	
		if(btnBack==null) {
			btnBack=new Button();
			btnBack.setText("Back");
			btnBack.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					TablePaneDeck.this.setVisible(false);
					TablePaneDeck.this.getParent().setVisible(true);
					
				}
			});
		}
		return btnBack;
	}

	//Table
	public TableView<Question> getTableView(){
		if(table == null) {
			table = new TableView<Question>();
			table.setEditable(true);
			table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
			//Create column
			TableColumn<Question, String> authorCol = new TableColumn<Question, String>("Author");
			TableColumn<Question, String> categCol = new TableColumn<Question, String>("Category");
			TableColumn<Question, String> statementCol = new TableColumn<Question, String>("Statement");
			TableColumn<Question, String> answerCol = new TableColumn<Question, String>("Answer");
			statementCol.prefWidthProperty().bind(table.widthProperty().multiply(0.6));
			//editable
			authorCol.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
			authorCol.setOnEditCommit((CellEditEvent<Question, String> event) -> {
				TablePosition<Question, String> pos = event.getTablePosition();

				String author = event.getNewValue();

				int row = pos.getRow();
				Question question = event.getTableView().getItems().get(row);

				question.setAuthor(author);
				saveMemento();
			});
			//Faut fixer ca. Ca crash
			//categCol.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
			statementCol.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
			statementCol.setOnEditCommit((CellEditEvent<Question, String> event) -> {
				TablePosition<Question, String> pos = event.getTablePosition();

				String statement = event.getNewValue();

				int row = pos.getRow();
				Question question = event.getTableView().getItems().get(row);

				question.setStatement(statement);
				saveMemento();
			});
			answerCol.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
			answerCol.setOnEditCommit((CellEditEvent<Question, String> event) -> {
				TablePosition<Question, String> pos = event.getTablePosition();

				String answer = event.getNewValue();

				int row = pos.getRow();
				Question question = event.getTableView().getItems().get(row);

				question.setAnswer(answer);
				saveMemento();
				
			});

			//Get value
			authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
			categCol.setCellValueFactory(new PropertyValueFactory<>("category"));
			statementCol.setCellValueFactory(new PropertyValueFactory<>("statement"));
			answerCol.setCellValueFactory(new PropertyValueFactory<>("answer"));

			//Display row
			//list = FXCollections.observableArrayList(Deck.getInstanceDeck().getQuestions());
			listQuestObser = FXCollections.observableArrayList();
			for (Question question : Deck.getInstanceDeck().getQuestions()) {
				Question q = new Question(question.getAuthor(), question.getCategory(), question.getStatement(), question.getAnswer());
				listQuestObser.add(q);
			}
			table.setItems(listQuestObser);
			table.getColumns().addAll(authorCol, categCol, statementCol, answerCol);
		}
		return table;
	}
	
	public void saveMemento() {
		List<Question> listQuest = new ArrayList<>();
		
		for (Question question : listQuestObser) {
			Question q = new Question(question.getAuthor(), question.getCategory(), question.getStatement(), question.getAnswer());
			listQuest.add(q);
		}
		Deck.getInstanceDeck().setQuestions(listQuest);
		Deck.getInstanceDeck().saveStateAndAdd();
	}
}
