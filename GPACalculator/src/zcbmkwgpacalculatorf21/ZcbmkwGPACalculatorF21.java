package zcbmkwgpacalculatorf21;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ZcbmkwGPACalculatorF21 extends Application {
    
    // A method used to set the text of infoArea to an error message
    private void errorHandle(TextArea infoArea){
        infoArea.setText("ERROR, INVALID INPUT!");
    }
    
    // A method used to check if 0 <= input <= 100, if input is not within those bounds, it returns returnValue
    private boolean isValid(double input){
        if(input >= 0 && input <= 100) return true;
        return false;
    }
    
    // A method to find the average score and return the corresponding grade A-F or 'e' on error
    private void getAverageScore(TextField score1, TextField score2, TextField score3, TextField score4, TextArea infoArea){
        double avg, sum = 0, validScores[] = new double[4];
        int i = 0, count = 0;
        String line = "Your average score is: ((";
        if(score1.getText().length() != 0)
            try{
                if(isValid(Double.parseDouble(score1.getText()))){
                    validScores[count] = Double.parseDouble(score1.getText());
                    sum += validScores[count];
                    count++;
                }
                else{errorHandle(infoArea);return;}
            }catch(NumberFormatException nfe){errorHandle(infoArea);return;}
        if(score2.getText().length() != 0)
            try{
                if(isValid(Double.parseDouble(score2.getText()))){
                    validScores[count] = Double.parseDouble(score2.getText());
                    sum += validScores[count];
                    count++;
                }
                else{errorHandle(infoArea);return;}
            }catch(NumberFormatException nfe){errorHandle(infoArea);return;}
        if(score3.getText().length() != 0)
            try{
                if(isValid(Double.parseDouble(score3.getText()))){
                    validScores[count] = Double.parseDouble(score3.getText());
                    sum += validScores[count];
                    count++;
                }
                else{errorHandle(infoArea);return;}
            }catch(NumberFormatException nfe){errorHandle(infoArea);return;}   
        if(score4.getText().length() != 0)
            try{
                if(isValid(Double.parseDouble(score4.getText()))){
                    validScores[count] = Double.parseDouble(score4.getText());
                    sum += validScores[count];
                    count++;
                }
                else{errorHandle(infoArea);return;}
            }catch(NumberFormatException nfe){errorHandle(infoArea);return;}
        
        if(count == 0){
            errorHandle(infoArea);
            return;
        }
        
        avg = sum/count;
        
        while(i < count){
            line += validScores[i];
            i++;
            if(i < count) line += "+";
        }
        
        line += (")/" + sum + ") = " + avg + "\nYour GPA is: ");

        
        if(avg >= 87 && avg <= 100) line += "A";
        if(avg >= 77 && avg < 87) line += "B";
        if(avg >= 67 && avg < 77) line += "C";
        if(avg >= 60 && avg < 67) line += "D";
        if(avg >= 0 && avg < 60) line += "F";
        
        infoArea.setText(line);
    }
    
    // These methods are used to return the highest and lowest score (double) of the populated text fields
    private double getHighestScore(TextField score1, TextField score2, TextField score3, TextField score4){
        double highestValue = -1;
        if(score1.getText().length() != 0)
            try{
                if(isValid(Double.parseDouble(score1.getText())))
                    highestValue = Math.max(Double.parseDouble(score1.getText()), highestValue);
                else return -1;
            }catch(NumberFormatException nfe){return -1;}
        if(score2.getText().length() != 0)
            try{
                if(isValid(Double.parseDouble(score2.getText())))
                    highestValue = Math.max(Double.parseDouble(score2.getText()), highestValue);
                else return -1;
            }catch(NumberFormatException nfe){return -1;}
        if(score3.getText().length() != 0)
            try{
                if(isValid(Double.parseDouble(score3.getText())))
                    highestValue = Math.max(Double.parseDouble(score3.getText()), highestValue);
                else return -1;
            }catch(NumberFormatException nfe){return -1;}   
        if(score4.getText().length() != 0)
            try{
                if(isValid(Double.parseDouble(score4.getText())))
                    highestValue = Math.max(Double.parseDouble(score4.getText()), highestValue);
                else return -1;
            }catch(NumberFormatException nfe){return -1;}
        return highestValue;
    }
    private double getLowestScore(TextField score1, TextField score2, TextField score3, TextField score4){
        double lowestValue = 101;
        if(score1.getText().length() != 0)
            try{
                if(isValid(Double.parseDouble(score1.getText())))
                    lowestValue = Math.min(Double.parseDouble(score1.getText()), lowestValue);
                else return 101;
            }catch(NumberFormatException nfe){return 101;}
        if(score2.getText().length() != 0)
            try{
                if(isValid(Double.parseDouble(score2.getText())))
                    lowestValue = Math.min(Double.parseDouble(score2.getText()), lowestValue);
                else return 101;
            }catch(NumberFormatException nfe){return 101;}
        if(score3.getText().length() != 0)
            try{
               if(isValid(Double.parseDouble(score3.getText())))
                    lowestValue = Math.min(Double.parseDouble(score3.getText()), lowestValue);
               else return 101;
            }catch(NumberFormatException nfe){return 101;}   
        if(score4.getText().length() != 0)
            try{
                if(isValid(Double.parseDouble(score4.getText())))
                    lowestValue = Math.min(Double.parseDouble(score4.getText()), lowestValue);
                else return 101;
            }catch(NumberFormatException nfe){return 101;}
        return lowestValue;
    }
    
    // A method used to create and return an Alert object based on the infoArea content
    private Alert onAlert(TextArea infoArea){
        Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
        alertBox.setTitle("GPA Calculator Alert");
        alertBox.setContentText("Have a Nice Day :)");
        if(infoArea.getText().length() == 0){
            alertBox.setHeaderText("There is nothing to display.");
        }
        else alertBox.setHeaderText(infoArea.getText());
        
        return alertBox;
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        
        // Creating the GridPane Object
        GridPane root = new GridPane();
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setVgap(10);
        root.setHgap(10);
        root.setPadding(new Insets(15,15,15,15));
        
        
        // Creating and Adding the Labels to the GridPane Object
        Label courseLabel1 = new Label("Course 1 :");
        Label courseLabel2 = new Label("Course 2 :");
        Label courseLabel3 = new Label("Course 3 :");
        Label courseLabel4 = new Label("Course 4 :");
        Label informationLabel = new Label("Information :");
        
        root.add(courseLabel1, 0, 0);
        root.add(courseLabel2, 0, 1);
        root.add(courseLabel3, 0, 2);
        root.add(courseLabel4, 0, 3);
        root.add(informationLabel, 0, 4, 2, 1);
        
        
        // Creating and Adding the TextFields to the GridPane Object
        TextField score1 = new TextField();
        TextField score2 = new TextField();
        TextField score3 = new TextField();
        TextField score4 = new TextField();
        
        score1.setPrefWidth(350);
        score2.setPrefWidth(350);
        score3.setPrefWidth(350);
        score4.setPrefWidth(350);
        
        root.add(score1, 1, 0);
        root.add(score2, 1, 1);
        root.add(score3, 1, 2);
        root.add(score4, 1, 3);

        
        // Creating and Adding the TextArea to the GridPane Object
        TextArea infoArea = new TextArea();
        infoArea.setPrefRowCount(3);
        infoArea.setWrapText(true);
        infoArea.setEditable(false);
        root.add(infoArea, 0, 5, 2, 1);
        
        
        // Creating and Adding the Buttons to the GridPane Object
        Button calculateGPA = new Button("Calculate GPA Score");
        Button showStats = new Button("Show Statistics");
        Button alert = new Button("Alert");
        Button clearAll = new Button("Clear All");
        
        calculateGPA.setMaxWidth(Double.MAX_VALUE);
        showStats.setMaxWidth(Double.MAX_VALUE);
        alert.setMaxWidth(Double.MAX_VALUE);
        clearAll.setMaxWidth(Double.MAX_VALUE);
        
        calculateGPA.setOnAction((ActionEvent event) -> {
            getAverageScore(score1, score2, score3, score4, infoArea);
        });
        showStats.setOnAction((ActionEvent event) -> {
            if(!isValid(getHighestScore(score1, score2, score3, score4))){
                errorHandle(infoArea);
            }
            else if(!isValid(getLowestScore(score1, score2, score3, score4))){
                errorHandle(infoArea);
            }
            else infoArea.setText("Your highest score is: " + getHighestScore(score1, score2, score3, score4) +
                    "\nYour lowest score is: " + getLowestScore(score1, score2, score3, score4));
        });
        alert.setOnAction((ActionEvent event) -> {
            Alert alertBox = onAlert(infoArea);
            alertBox.showAndWait();
        });
        clearAll.setOnAction((ActionEvent event) -> {
            score1.setText("");
            score2.setText("");
            score3.setText("");
            score4.setText("");
            infoArea.setText("");
        });
        
        
        // Creating a VBox to house the previous Button Objects
        VBox vbox = new VBox(10, calculateGPA, showStats, alert, clearAll);
        vbox.setAlignment(Pos.CENTER);
        root.add(vbox, 0, 6, 2, 1);

        
        // Creating a scene object with the above gridpane, adding it to the stage, and presenting it to the user
        Scene scene = new Scene(root, 500, 425);
        
        primaryStage.setTitle("GPA Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
