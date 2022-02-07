
package zcbmkwfinalprojectf21.FXMLControllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import zcbmkwfinalprojectf21.Book;
import zcbmkwfinalprojectf21.Model;

public class MainMenuController extends MainController implements Initializable {
    
    
    
    private Model model;
    private Book selectedBook;
    private ArrayList<Book> selectedCollection;
    
    @FXML private StackPane returnBookPane;
    @FXML private StackPane addToCartPane;
    @FXML private HBox cartManipulationHBox;
    @FXML private JFXListView listView;
    @FXML private JFXTextField searchBar;
    
    @FXML private VBox listContainer;
    @FXML private StackPane aboutContainer;
    
    @FXML private JFXTextField donationTitle;
    @FXML private JFXTextField donationAuthor;
    @FXML private JFXTextField donationDate;
    @FXML private StackPane donationContainer;
    
  
    
    
    @FXML
    //Open a list of the contents of the library
    private void handleBrowseButtonAction(ActionEvent event) {
        changeListView(model.getLibrary());
        changeVisibility(false, true, false, true, false, false);
        sourceListView(selectedCollection);
    }
    
    @FXML
    //Open a list of books held by the user
    private void handleReturnBookMenuButtonAction(ActionEvent event) {
        changeListView(model.getCheckedOutBooks());
        changeVisibility(false, true, false, false, true, false);
        sourceListView(selectedCollection);
    }
    
    @FXML
    //Open a list of books in the users cart
    private void handleViewCartButtonAction(ActionEvent event) {
        changeListView(model.getCart());
        changeVisibility(false, true, false, false, false, true);
        sourceListView(selectedCollection);
    }
    
    @FXML
    //Open a text box for the user to input a book
    //We will then add this to the library
    private void handleDonateBookButtonAction(ActionEvent event) {
        changeListView(null);
        changeVisibility(true, false, false, false, false, false);
    }
    
    @FXML
    //Open an about page on the right side of the screen
    private void handleAboutButtonAction(ActionEvent event) {
        changeListView(null);
        changeVisibility(false, false, true, false, false, false);
    }
    
    @FXML
    private void handleExitApplicationButtonAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Library");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure you would like to exit?");
        alert.setResizable(false);
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == ButtonType.OK){
            Platform.exit();
            System.exit(0);
        }
    }
    
    
    
    @FXML
    private void handleCheckOutButtonAction(ActionEvent event) {
        if(!model.getCart().isEmpty()){
            searchBar.setText("");
            model.checkOut();
        }
        model.saveBooks();
        sourceListView(model.getCart());
    }
    
    @FXML
    private void handleRemoveFromCartButtonAction(ActionEvent event) {
        if(selectedBook != null) {
            model.removeFromCart(selectedBook);
            searchBar.setText("");
            selectedBook = null;
        }
        model.saveBooks();
        sourceListView(model.getCart());
    }
    
    @FXML
    private void handleReturnBookButtonAction(ActionEvent event) {
        if(selectedBook != null) {
            model.returnBook(selectedBook);
            searchBar.setText("");
            selectedBook = null;
        }
        model.saveBooks();
        sourceListView(model.getCheckedOutBooks());
    }
    
    @FXML
    private void handleAddToCartButtonAction(ActionEvent event) {
        if(selectedBook != null){
            model.addToCart(selectedBook);
            searchBar.setText("");
            selectedBook = null;
        }
        model.saveBooks();
        sourceListView(model.getLibrary());
    }
    
    @FXML
    private void handleCloseListButtonAction(ActionEvent event) {
        changeVisibility(false, false, false, false, false, false);
        for (Book book : model.getLibrary()) {
            Label label = new Label(book.getReadableLabel());
            listView.getItems().add(label);
        }
    }
    
    
    //Donation Methods
    @FXML
    private void handleSubmitDonationButtonAction(ActionEvent event){
        Book newBook = new Book(donationTitle.getText(), donationAuthor.getText(), donationDate.getText());
        model.getLibrary().add(newBook);
        model.saveBooks();
        changeVisibility(false, false, false, false, false, false);
    }
    
    
    
    
    
    
    
    
    
    private void changeVisibility(boolean donation, boolean list, boolean about, boolean add, boolean bookReturn, boolean cart){
        donationContainer.setVisible(donation);
        listContainer.setVisible(list);
        aboutContainer.setVisible(about);
        addToCartPane.setVisible(add);
        returnBookPane.setVisible(bookReturn);
        cartManipulationHBox.setVisible(cart);
        
    }
    
    private void changeListView(ArrayList<Book> collection){
        selectedBook = null;
        selectedCollection = collection;
        listView.getItems().clear();
    }
    
    private void sourceListView(ArrayList<Book> collection){
        listView.getItems().clear();
        for (Book book : collection) {
            Label label = new Label(book.getReadableLabel());
            listView.getItems().add(label);
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new Model();
        selectedBook = null;
        selectedCollection = null;
        
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Label>() {
            @Override
            public void changed(ObservableValue<? extends Label> observable, Label oldValue, Label newValue) {
                String str;
                if(!listView.getItems().isEmpty()){
                    str = ((Label)listView.getSelectionModel().getSelectedItem()).getText();
                    if(str != null){
                        if(model.searchLibrary(str) != null)
                            selectedBook = model.searchLibrary(str);
                        else
                            selectedBook = null;
                    }
                }
            }
        });
        
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                listView.getItems().clear();
                for(int i = 0; i < selectedCollection.size(); i++){
                    if(selectedCollection.get(i).getReadableLabel().toLowerCase().contains(newValue.toLowerCase()))
                        listView.getItems().add(new Label(selectedCollection.get(i).getReadableLabel()));
                }
            }
        });
    }
                
}
