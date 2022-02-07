
package zcbmkwfinalprojectf21.FXMLControllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class UserSignupController extends MainController implements Initializable, UserLoginInterface {

    @FXML private JFXTextField usernameField;
    @FXML private JFXPasswordField passwordField;
    @FXML private JFXTextField firstnameField;
    @FXML private JFXTextField lastnameField;
    @FXML private Label userAlreadyExists;
    
    
    
    @FXML
    @Override
    public void handleSignupButton(ActionEvent event){
        if(!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty() && !firstnameField.getText().isEmpty() && !lastnameField.getText().isEmpty()){
            String newUserInfo = usernameField.getText() + " " + stringHash(passwordField.getText()) + " " + firstnameField.getText() + " " +lastnameField.getText() + "\n";
            
            if(!userExists()){
                if(createUser(newUserInfo)){
                    initializeModel(usernameField.getText());
                    changeScenes(event, "MainMenu.fxml");
                }
            }
            else{
                userAlreadyExists.setVisible(true);
            }
            
        }
    }
    
    @FXML
    @Override
    public void handleSigninButton(ActionEvent event){
        changeScenes(event, "UserSignin.fxml");
    }
    
    
    
    private boolean userExists(){
        try (Scanner scan = new Scanner(new File("src/zcbmkwFinalProjectF21/LibraryFiles/users.txt"))) {
            while(scan.hasNext()){
                String line = scan.nextLine().toLowerCase().split(" ")[0];
                if(line.equals(usernameField.getText().toLowerCase())){
                    return true;
                }
            }
        } catch (FileNotFoundException e){
            System.err.println(e);
            return true;
        }
        return false;
    }
    
    private boolean createUser(String newUserInfo){
        try (FileOutputStream fos = new FileOutputStream("src/zcbmkwFinalProjectF21/LibraryFiles/users.txt", true)) {
            fos.write(newUserInfo.getBytes());
            File bookFile = new File("src/zcbmkwFinalProjectF21/UserBooks/" + usernameField.getText() + ".txt");
            File cartFile = new File("src/zcbmkwFinalProjectF21/UserCarts/" + usernameField.getText() + ".txt");
            if(userExists() && bookFile.createNewFile() && cartFile.createNewFile())
                return true;
        } catch (IOException e){
            System.err.println(e);
            return false;
        }
        return false;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
