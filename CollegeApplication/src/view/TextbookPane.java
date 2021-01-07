package view;

import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Helper;
import model.Name;
import model.Textbook;
import model.TextbookBag;

public class TextbookPane extends ButtonPane{
	
	private TextbookBag textbookBag;
	private Textbook textbook;
	private Name[] nameArray;
	private Name[] copyOfNameArray;
	private Name name;
	private int count = 0;
	
	private TextField bookTitleField;
	private TextField isbnField;
	private TextField priceField;
	
	private TextField firstNameField;
	private TextField lastNameField;

	private Button insertNameBtn;
	
	private VBox root;
	private HBox textFieldBox;
	private HBox buttonBox;
	private HBox titleBox;
		
	public TextbookPane(TextbookBag textbookBag) {
		nameArray = new Name[4];
		this.textbookBag = textbookBag;
		buildTextFields();
		buildNameButton();
		buildHBoxes();
		buildVBox();		
		handleEvent();
		
	}
	
	public void buildVBox() {
		root = new VBox(20);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(titleBox, textFieldBox, buttonBox, ButtonPane.getButtonBox());
	}
	
	public void handleEvent() {
		insertNameBtn.setOnAction(e ->{
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			name = new Name(firstName, lastName);
			if(count < 4) {
				nameArray[count++] = name;
			} else if (count == 4) {
				count = 0;
				nameArray = new Name[4];
				nameArray[count++] = name;
			}
			firstNameField.clear();
			lastNameField.clear();
			firstNameField.setPromptText("Name Array Count:" + count);
			lastNameField.setPromptText("Name Array Count:" + count);
			
		});
		
		getInsertBtn().setOnAction(e ->{
			 String bookTitle = bookTitleField.getText();
			 String isbn = isbnField.getText();
			
			 try {
			 double price = Double.parseDouble(priceField.getText());
			textbook = new Textbook(bookTitle,isbn, nameArray, price);
			textbookBag.insert(textbook);
			bookTitleField.clear();
			isbnField.clear();
			priceField.clear();
			firstNameField.clear();
			lastNameField.clear();
			 } catch(NumberFormatException e2){
				 Alerts.getPriceAlert();
			 }
			
		});
		
		getSearchBtn().setOnAction(e -> {
			 String isbn = isbnField.getText();
			 textbook = (Textbook) textbookBag.findById(isbn);
			if(textbook != null) {
			bookTitleField.setText(textbook.getBookTitle());
			isbnField.setText(textbook.getIsbn());
			String price = "" + textbook.getPrice();
			priceField.setText(price);
			
			String firstName = "";
			String lastName = "";
			Name[] names = textbook.getAuthor(); 
			for(int i = 0; i < textbook.getAuthor().length; i++) {
				if(names[i] == null) {
					break;
				} else if(i == (textbook.getAuthor().length - 1)) {
					firstName += names[i].getFirstName();
					lastName += names[i].getLastName();
				} else {
					firstName += names[i].getFirstName() + ", ";
					lastName += names[i].getLastName() + ", ";
				}
			}
			firstNameField.setText(firstName);
			lastNameField.setText(lastName);
			} else {
				Alerts.getTextbookSearchAlert();
			}
		});
		
		getRemoveBtn().setOnAction(e -> {
			String isbn = isbnField.getText();
			textbookBag.removeById(isbn);
			
			bookTitleField.clear();
			isbnField.clear();
			firstNameField.clear();
			lastNameField.clear();
			priceField.clear();
		}); 
		
		getUpdateBtn().setOnAction(e -> {
			String isbn = isbnField.getText();
			textbook = (Textbook) textbookBag.findById(isbn);
			
			String bookTitle = bookTitleField.getText();
			double price = Double.parseDouble(priceField.getText());
	
			textbook.setAuthor(nameArray);
			textbook.setPrice(price);
			textbook.setBookTitle(bookTitle);
			bookTitleField.clear();
			isbnField.clear();
			firstNameField.clear();
			lastNameField.clear();
			priceField.clear();
		});
	}

	
	
	public void buildHBoxes() {
		Label myLabel = new Label("Edit Textbook Info.");
		

		titleBox = new HBox(50);
		textFieldBox = new HBox(10);
		buttonBox = new HBox(10);
		
		textFieldBox.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);
		titleBox.setAlignment(Pos.TOP_CENTER);
		
		titleBox.getChildren().add(myLabel);
		textFieldBox.getChildren().addAll(bookTitleField, isbnField, priceField, firstNameField, lastNameField);
		buttonBox.getChildren().addAll(insertNameBtn);
	}
	
	public void buildTextFields() {
		bookTitleField = new TextField();
		isbnField = new TextField();
		firstNameField = new TextField();
		lastNameField = new TextField();
		
		firstNameField.setPromptText("FIRST NAME");
		lastNameField.setPromptText("LAST NAME");
		bookTitleField.setPromptText("BOOK TITLE");
		isbnField.setPromptText("ISBN");
		priceField = new TextField();
		priceField.setPromptText("price");
	}
	
	public void buildNameButton() {
		insertNameBtn = new Button("INSERT NAME");
	}
	
	
	public Pane getPane() {
		return root; 
	} 
}
