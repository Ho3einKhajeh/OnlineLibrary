package org.example.onlinelibrary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
public class firstControler {
    @FXML
    Button enterButton;
    @FXML
    TextField tex1;
    @FXML
    TextField tex2;
    @FXML
    private Stage currentStage;
    public void action_btns(ActionEvent actionEvent) {
//        if (tex1.getText()=="admin"&&tex2.getText()=="admin"){
//
//        }
        GotoAdmin();


    }
    public void GotoAdmin(){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
            Parent root = fxmlLoader.load();

            Stage newStage = new Stage();
            newStage.setTitle("2X2");
            newStage.setScene(new Scene(root, 800, 600));
            currentStage = (Stage) enterButton.getScene().getWindow();

            currentStage.close(); // Close the current stage

            newStage.show(); // Show the new stage

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}