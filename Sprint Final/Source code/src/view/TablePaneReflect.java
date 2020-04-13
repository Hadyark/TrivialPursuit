package view;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import enumeration.Category;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import modele.Account;
import modele.Deck;
import modele.Player;
import modele.Question;
import util.Serialisation;
import util.Utility;

public class TablePaneReflect extends BorderPane {
	private Button btnSave,btnDelete, btnBack;
	private AddPane addPane;
	private TableView table;
	private ArrayList<?> data = new ArrayList<>();
	private ObservableList<?> observableList ;
	private int i=0;
	private Object objectParent;
	private boolean isEditable;

	/**
	 * It allows to create a view with the table
	 * @param arrayList 
	 * is the list of object in the table
	 * @param object 
	 * is the object in which the varibale arraylist is declared
	 * @param editable 
	 * defined if the table is editable
	 */
	public TablePaneReflect(ArrayList arrayList, Object object, Boolean editable) {
		data = arrayList;
		objectParent = object;
		isEditable = editable;
		HBox hbx = new HBox();
		
		//If the talbe is editable. Add the button "Save" and "Delete"
		if(Account.getInstanceAccount().isAdmin() == true) {
			hbx.getChildren().addAll(getBtnSave(), getBtnDelete());	
		}

		hbx.getChildren().add(getBtnBack());
		hbx.setAlignment(Pos.CENTER);

		this.setCenter(getTableView());
		this.setBottom(hbx);
	}

	/**
	 * Method TableView
	 * @return a table containing an list of objects
	 */
	public TableView<?> getTableView(){
		if(table == null) {
			//Creates an observable list from the original list 
			observableList = FXCollections.observableArrayList(data);

			table = new TableView(observableList);

			//Set the table on editable if parameter is true
			table.setEditable(isEditable);
			
			//Set the table to select multiple objects at the same time
			table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			//Get the class of an object in the list
			Class c = data.get(0).getClass();
			
			//For each methods of the object of the list
			for (Method method : c.getMethods()) {
				
				//Get the name of the method
				String nameMethod = method.getName();

				//If this name start by "getClass" or "getInstance" -> ignore this method
				if (!nameMethod.startsWith("getClass") && !nameMethod.startsWith("getInstance")) {
					String columnName = null;
					if (nameMethod.startsWith("is")) {
						columnName = nameMethod.replace("is", "");
						String nameSetMethod = nameMethod.replace("is", "set");

						//Create the column and add it in the table
						addColumn(columnName, data.get(0));
						
						//Get this column
						TableColumn <Object,Boolean> column = (TableColumn) table.getColumns().get(i);
						
						//Convert the column to boolean (Because to edit. The method created have to return a boolean)
						column.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
						
						//Allow to edit a table cell  
						column.setOnEditCommit((CellEditEvent<Object,Boolean> event) -> {

							TablePosition<Object,Boolean> pos = event.getTablePosition();

							Boolean newValue = event.getNewValue();

							int row = pos.getRow();
							Object objTable = event.getTableView().getItems().get(row);

							objTable = data.get(0).getClass().cast(objTable);

							Object[] param = {newValue};
							
							//Create a method that will edit the object to be modified
							Utility.callMethod(objTable, nameSetMethod, param, boolean.class);
						});
						i++;
						
						//Same thing but if the method is a get 
					}if (nameMethod.startsWith("get")) {
						columnName = nameMethod.replace("get", "");
						String nameSetMethod = nameMethod.replace("get", "set");
						addColumn(columnName, data.get(0));
						
						//If the method return a "String"
						if(method.getGenericReturnType() == String.class) {
							TableColumn <Object,String> column = (TableColumn) table.getColumns().get(i);
							
							//No need to convert because the colum is already a "String"
							column.setCellFactory(TextFieldTableCell.forTableColumn());
							column.setOnEditCommit((CellEditEvent<Object,String> event) -> {

								TablePosition<Object,String> pos = event.getTablePosition();

								String newValue = event.getNewValue();

								int row = pos.getRow();
								Object objTable = event.getTableView().getItems().get(row);

								objTable = data.get(0).getClass().cast(objTable);

								Object[] param = {newValue};
							
								//Create a method that will edit the object to be modified
								Utility.callMethod(objTable, nameSetMethod, param, String.class);
							});
							
							//If the method return a "Double"
						}else if(method.getGenericReturnType() == double.class) {
							TableColumn <Object,Double> column = (TableColumn) table.getColumns().get(i);
							
							//Convert the column to "double" (Because to edit. The method created have to return a "double")
							column.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
							column.setOnEditCommit((CellEditEvent<Object,Double> event) -> {

								TablePosition<Object,Double> pos = event.getTablePosition();

								Double newValue = event.getNewValue();

								int row = pos.getRow();
								Object objTable = event.getTableView().getItems().get(row);

								objTable = data.get(0).getClass().cast(objTable);

								Object[] param = {newValue};

								Utility.callMethod(objTable, nameSetMethod, param, double.class);
							});
							
							//If the method return a "Int"
						}else if(method.getGenericReturnType() == int.class) {
							TableColumn <Object,Integer> column = (TableColumn) table.getColumns().get(i);
							
							//Convert the column to "int" (Because to edit. The method created have to return a "int")
							column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
							column.setOnEditCommit((CellEditEvent<Object,Integer> event) -> {

								TablePosition<Object,Integer> pos = event.getTablePosition();

								Integer newValue = event.getNewValue();

								int row = pos.getRow();
								Object objTable = event.getTableView().getItems().get(row);

								objTable = data.get(0).getClass().cast(objTable);

								Object[] param = {newValue};
								
								//Create a method that will edit the object to be modified
								Utility.callMethod(objTable, nameSetMethod, param, int.class);
							});
						}
						i++;
					}

				}
			}
		}
		return table;
	}
	
	/**
	 * Method to create a column and and it in the table
	 * @param columnName
	 * Name of the column
	 * @param obj
	 * Object containing the list
	 */
	public void addColumn(String columnName, Object obj) {

		TableColumn<Object,Object> column = new TableColumn(columnName);
		column.setCellValueFactory(new PropertyValueFactory<>(columnName));

		table.getColumns().add(column);
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
					ArrayList newList = new ArrayList();
					for( Object o : observableList) {
						newList.add(o);
					}
					Object[] obj = {newList};

					for (Method method : objectParent.getClass().getMethods()) {
						String nameMethod = method.getName();							
						if (nameMethod.startsWith("setList")) {
							Utility.callMethod(objectParent, nameMethod, obj, ArrayList.class);

						}

					}
					Serialisation.saveFile(objectParent.getClass().getSimpleName()+"_save", objectParent);

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
				}

			});
		}
		return btnDelete;
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
					MainFx.getStackGamePane().hideAll();
					MainFx.getStackGamePane().getMainMenu().setVisible(true);
				}
			});
		}
		return btnBack;
	}
}