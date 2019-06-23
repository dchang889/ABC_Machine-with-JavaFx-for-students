import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*; 
import javafx.stage.Stage;

public class MainWindow extends Application{
	Stage window;
	BorderPane pane;
	VBox vBox;
	HBox hBox;
	CheckBox checkBox;
	TableView<Word> table;
	TableColumn<Word, String> wordsColumn;
	TableColumn<Word, Integer> valueColumn;
	TextField addW;
	Button addButton, delButton, clearButton;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception{

		window = primaryStage;
		window.setTitle("ABC Order");

		//Build Table and Word and Value Columns

		table = new TableView<>();
		table.setItems(getWords());

		wordsColumn = new TableColumn<>("Order by ABC Order");
		wordsColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.6));
		wordsColumn.setCellValueFactory(new PropertyValueFactory<>("word"));

		valueColumn = new TableColumn<>("Order by Word Value");
		valueColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

		table.getColumns().add(wordsColumn);
		table.getColumns().add(valueColumn);	

		//Build TextField for user inputs and Add, Delete, Clear Buttons

		addW = new TextField();
		addW.setPromptText("Add Word Here");
		addW.setOnAction(e->doAdd());
		addW.setMinWidth(100);

		addButton = new Button("Add Word");
		addButton.setOnAction(e-> doAdd());

		delButton = new Button("Delete Button");
		delButton.setOnAction(e-> doDel());

		clearButton = new Button("Clear");
		clearButton.setOnAction(e-> clear());

		hBox = new HBox();
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(addW, addButton, delButton, clearButton);

		vBox = new VBox();
		vBox.getChildren().addAll(table, hBox);	

		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
	}


	private void doAdd() {
		String newString = addW.getText();

		if( newString.matches("^\\b[a-zA-Z]+\\b$") ) {
			Word newWord = new Word(newString);
			table.getItems().add(newWord);
			addW.clear();
		}
		else {
			System.out.println("Bad word, "+newString+" could not be entered.");
		}
	}

	private void doDel() {
		ObservableList<Word> wordsSelected, allWords;
		allWords = table.getItems();
		wordsSelected = table.getSelectionModel().getSelectedItems();
		wordsSelected.forEach(allWords::remove);
	}

	private void clear() {
		int selectedOption = JOptionPane.showConfirmDialog(null, 
				"Are you sure you want to clear the table?", 
				"Message", 
				JOptionPane.YES_NO_OPTION); 
		if (selectedOption == JOptionPane.YES_OPTION) {
			table.getItems().clear();
		}
	}

	public ObservableList<Word> getWords(){
		ObservableList<Word> words = FXCollections.observableArrayList();
		words.add(new Word("Hello"));
		words.addAll(new Word("Your") , 
				new Word("words"), 
				new Word("will"),
				new Word("appear"),
				new Word("here"));
		return words;
	}
}
