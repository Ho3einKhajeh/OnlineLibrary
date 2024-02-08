package org.example.onlinelibrary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_password;


    @FXML
    private Button btn_modiriatBook;

@FXML
    private Button btn_deleteMember;


    @FXML
    private Button btn_returnAddBook;


    Connection con =DBcon.getCon();
    PreparedStatement st=null;
    ResultSet rs=null;
    @Override
    public  void initialize(URL url, ResourceBundle resourceBundle){

    }

    public void Login() {
        try {
            MainController mc=new MainController();
            st = con.prepareStatement("SELECT * FROM members WHERE username=? AND password=?");
            st.setString(1, txt_username.getText());
            st.setString(2, txt_password.getText());
            rs = st.executeQuery();

            if (rs.next()) {
                // If username and password match an admin account
                if (txt_username.getText().equals("admin") && txt_password.getText().equals("admin")) {
                    mc.setUserId(1);
                    memberController.chektable1=2;
                    GoToAdminPage();
                } else {
                    memberController.chektable1=1;
                    int userIdFromDatabase = rs.getInt("id");
                    mc.setUserId(userIdFromDatabase); // Set common user ID from the database
                    GoToMemberPage();

                    System.out.println(mc.getUserId())  ;
                }
            } else {
                // If no matching account found
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Login Error", ButtonType.OK);
                alert.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Button enterButton;

    @FXML
    private Stage currentStage;
    @FXML
    private void GoToAdminPage() {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example.onlinelibrary/AdminPage.fxml"));
            Parent root = fxmlLoader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Admin page");
            newStage.setScene(new Scene(root, 800, 700));
            currentStage = (Stage) enterButton.getScene().getWindow();

            currentStage.close(); // Close the current stage

            newStage.show(); // Show the new stage

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void GoToMemberPage() {

        memberController.chektable1=1;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example.onlinelibrary/memberPage.fxml"));
            Parent root = fxmlLoader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Member page");
            newStage.setScene(new Scene(root, 800, 700));
            currentStage = (Stage) enterButton.getScene().getWindow();

            currentStage.close(); // Close the current stage

            newStage.show(); // Show the new stage

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void GoTODeleteMember() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example.onlinelibrary/deleteMember.fxml"));
            Parent root = fxmlLoader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Delete member");
            newStage.setScene(new Scene(root, 800, 700));
            currentStage = (Stage) btn_deleteMember.getScene().getWindow();

            currentStage.close(); // Close the current stage

            newStage.show(); // Show the new stage

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void GoTOAddBook() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example.onlinelibrary/AddBook.fxml"));
            Parent root = fxmlLoader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Add books");
            newStage.setScene(new Scene(root, 800, 700));
            currentStage = (Stage) btn_modiriatBook.getScene().getWindow();

            currentStage.close(); // Close the current stage

            newStage.show(); // Show the new stage

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void ReturnAdminPage() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example.onlinelibrary/AdminPage.fxml"));
            Parent root = fxmlLoader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Admin page");
            newStage.setScene(new Scene(root, 800, 700));
            currentStage = (Stage) btn_returnAddBook.getScene().getWindow();

            currentStage.close(); // Close the current stage

            newStage.show(); // Show the new stage

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private Button btn_Exit;

    @FXML

    private void Exit() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example.onlinelibrary/login page.fxml"));
            Parent root = fxmlLoader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Login page");
            newStage.setScene(new Scene(root, 800, 700));
            Stage currentStage = (Stage) btn_Exit.getScene().getWindow();

            currentStage.close(); // Close the current stage

            newStage.show(); // Show the new stage

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private Button btn_addmember;

    @FXML

    private void GotoAddMember() {
        memberController.chektable1=0;

        try {


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example.onlinelibrary/add member page.fxml"));
            Parent root = fxmlLoader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Sign up");
            newStage.setScene(new Scene(root, 500, 700));
            Stage currentStage = (Stage) btn_addmember.getScene().getWindow();

            currentStage.close(); // Close the current stage

            newStage.show(); // Show the new stage

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}