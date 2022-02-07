
package zcbmkwfinalprojectf21.FXMLControllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class UserSigninController extends MainController implements Initializable, UserLoginInterface {

    
    @FXML private JFXTextField usernameField;
    @FXML private JFXPasswordField passwordField;
    @FXML private Label incorrectUserInfo;
    
    
    
    @FXML 
    @Override
    public void handleSigninButton(ActionEvent event){
        if(!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()){
            
            try {
                File userFile = new File("src/zcbmkwFinalProjectF21/LibraryFiles/users.txt");
                Scanner userScanner = new Scanner(userFile);
                while(userScanner.hasNext()){
                    String userInfo = userScanner.nextLine().toLowerCase();
                    String username = userInfo.split(" ")[0];
                    String password = userInfo.split(" ")[1];
                    
                    if(username.equals(usernameField.getText().toLowerCase()) && stringHash(passwordField.getText()).equals(password)){
                        initializeModel(usernameField.getText());
                        changeScenes(event, "MainMenu.fxml");
                    }
                    else {
                        incorrectUserInfo.setVisible(true);
                    }
                }
                
            } catch (FileNotFoundException e) {
                System.err.println(e);
            }
        }
    }
    
    @FXML 
    @Override
    public void handleSignupButton(ActionEvent event){
        changeScenes(event, "UserSignup.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
