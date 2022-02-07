
package zcbmkwfinalprojectf21.FXMLControllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import zcbmkwfinalprojectf21.*;

public class MainController {
    
    protected final static String CURRENT_DIRECTORY = "src/zcbmkwFinalProjectF21/FXMLDocuments/";
    
    
    protected void initializeModel(String username){
        Model.setUserID(username);
    }
    
    protected void changeScenes(ActionEvent event, String fileName){
        try{
            URL url = new File(CURRENT_DIRECTORY + fileName).toURI().toURL();
            Parent newParent = FXMLLoader.load(url);
            Scene newScene = new Scene(newParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.setResizable(false);
            window.show();
        } catch(IOException e){
            System.err.println(e);
        }
    }
    
    protected String stringHash(String rawString){
        StringBuilder buf = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(rawString.getBytes());
            for(int i = 0; i < bytes.length; i++){
                buf.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return buf.toString();
    }
}
