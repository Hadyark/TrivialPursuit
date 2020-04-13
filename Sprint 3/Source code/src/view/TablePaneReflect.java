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

	public TablePaneReflect(ArrayList arrayList, Object object, Boolean editable) {
		data = arrayList;
		objectParent = object;
		isEditable = editable;
		HBox hbx = new HBox();
		
		if(Account.getInstanceAccount().isAdmin() == true) {
			hbx.getChildren().addAll(getBtnSave(), getBtnDelete());	
		}
		
		hbx.getChildren().add(getBtnBack());
		hbx.setAlignment(Pos.CENTER);

		this.setCenter(getTableView());
		this.setBottom(hbx);

	}

	//Table
	public TableView<?> getTableView(){
		if(table == null) {

			observableList = FXCollections.observableArrayList(data);

			table = new TableView(observableList);

			table.setEditable(isEditable);
			table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			Class c = data.get(0).getClass();
			for (Method method : c.getMethods()) {
				String nameMethod = method.getName();


				if (!nameMethod.startsWith("getClass") && !nameMethod.startsWith("getInstance")) {
					String columnName = null;
					if (nameMethod.startsWith("is")) {
						columnName = nameMethod.replace("is", "");
						String nameSetMethod = nameMethod.replace("is", "set");
						addColumn(columnName, data.get(0));
						TableColumn <Object,Boolean> column = (TableColumn) table.getColumns().get(i);
						column.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
						column.setOnEditCommit((CellEditEvent<Object,Boolean> event) -> {

							TablePosition<Object,Boolean> pos = event.getTablePosition();

							Boolean newValue = event.getNewValue();

							int row = pos.getRow();
							Object objTable = event.getTableView().getItems().get(row);

							objTable = data.get(0).getClass().cast(objTable);

							Object[] param = {newValue};

							Utility.callMethod(objTable, nameSetMethod, param, boolean.class);
						});
						i++;
					}if (nameMethod.startsWith("get")) {
						columnName = nameMethod.replace("get", "");
						String nameSetMethod = nameMethod.replace("get", "set");
						addColumn(columnName, data.get(0));
						if(method.getGenericReturnType() == String.class) {
							TableColumn <Object,String> column = (TableColumn) table.getColumns().get(i);
							column.setCellFactory(TextFieldTableCell.forTableColumn());
							column.setOnEditCommit((CellEditEvent<Object,String> event) -> {

								TablePosition<Object,String> pos = event.getTablePosition();

								String newValue = event.getNewValue();

								int row = pos.getRow();
								Object objTable = event.getTableView().getItems().get(row);

								objTable = data.get(0).getClass().cast(objTable);

								Object[] param = {newValue};

								Utility.callMethod(objTable, nameSetMethod, param, String.class);
							});
						}else if(method.getGenericReturnType() == double.class) {
							TableColumn <Object,Double> column = (TableColumn) table.getColumns().get(i);
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
						}else if(method.getGenericReturnType() == int.class) {
							TableColumn <Object,Integer> column = (TableColumn) table.getColumns().get(i);
							column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
							column.setOnEditCommit((CellEditEvent<Object,Integer> event) -> {

								TablePosition<Object,Integer> pos = event.getTablePosition();

								Integer newValue = event.getNewValue();

								int row = pos.getRow();
								Object objTable = event.getTableView().getItems().get(row);

								objTable = data.get(0).getClass().cast(objTable);

								Object[] param = {newValue};

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