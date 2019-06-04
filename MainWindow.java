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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
		window.setTitle("ABC Order Machine");
		
		//Build the Table View
		buildColumn();		//Build the Column of Words
		setUpTable();		//Set up the table to contain the Column of Words
		System.out.println("setUpTable");
		//Build the Input Functions
		buildInputFunctions();
		
		
		//Put table and input functions(hBox) altogether
		vBox = new VBox();
		vBox.getChildren().addAll(table, hBox);		//Put the Table into the vBox
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
	}
	
	private void buildInputFunctions() {
		addW = new TextField();
		addW.setPromptText("Add Word Here");
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
	}

	private void clear() {
		table.getItems().clear();
	}

	private void doDel() {
		ObservableList<Word> wordsSelected, allWords;
		allWords = table.getItems();
		wordsSelected = table.getSelectionModel().getSelectedItems();
		
		wordsSelected.forEach(allWords::remove);
	}

	private void doAdd() {
		Word newWord = new Word(addW.getText());
		table.getItems().add(newWord);
		addW.clear();
	}

	private void setUpTable() {
		table = new TableView<>();
		table.setItems(getWords());
		table.getColumns().addAll(wordsColumn, valueColumn);	
	}

	private void buildColumn() {
		wordsColumn = new TableColumn<>("Word");
		wordsColumn.setMinWidth(200);
		wordsColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
		

		valueColumn = new TableColumn<>("Value");
		valueColumn.setMinWidth(100);
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
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
