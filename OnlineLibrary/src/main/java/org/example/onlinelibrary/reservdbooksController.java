package org.example.onlinelibrary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class reservdbooksController {


    public class bookController implements Initializable {

        Connection con = null;
        PreparedStatement st = null;

        @FXML
        private int id;
        @FXML
        private Button btn_Exit;
        ResultSet rs = null;

        @FXML
        private TextField txt_SrchBookTitle;


        @FXML
        private TextField txt_AddBookTitle;
        @FXML
        private TextField txt_AddBookSubject;
        @FXML
        private TextField txt_AddBookAuthor;
        @FXML
        private TableColumn<books, Integer> colID;

        @FXML
        private TableView tbl_books;


        public static int chek;
        @FXML

        private TableColumn<books, String> colSubject;
        @FXML

        private TableColumn<books, String> colTitle;
        @FXML

        private TableColumn<books, String> colIs;

        @FXML

        private TableColumn<books, String> colAuthor;
        @FXML
        public static boolean chektable = true;
        @FXML
        private TableColumn<books, String> IsAvailable;


        @FXML
        private TextField txt_showMessage;


        @FXML
        private Button btn_returnAddBook;


        @FXML
        private TableView tbl_ReservedBooks;

        @FXML
        public void showReservedBooks() {
            MainController mc = new MainController();
            int memberId = mc.getUserId(); // Get the ID of the currently logged-in user

            ObservableList<books> reservedBooks = getReservedBooks(memberId);

            if (chektable) {
                tbl_ReservedBooks.setItems(reservedBooks);
                if (chektable) {
                    ObservableList<books> list = getBooks1();
                    tbl_reservdBooks.setItems(list);
                    colID.setCellValueFactory(new PropertyValueFactory<books, Integer>("id"));
                    colSubject.setCellValueFactory((new PropertyValueFactory<books, String>("subject")));
                    colTitle.setCellValueFactory((new PropertyValueFactory<books, String>("title")));
                    colAuthor.setCellValueFactory((new PropertyValueFactory<books, String>("author")));
                    colIs.setCellValueFactory((new PropertyValueFactory<books, String>("IsReservd")));
                }
            }
        }

        public ObservableList<books> getReservedBooks(int memberId) {
            ObservableList<books> reservedBooks = FXCollections.observableArrayList();
            String query = "SELECT b.* FROM books b INNER JOIN reserved_books rb ON b.Id = rb.bookId WHERE rb.memberId = ?";

            try (Connection con = DBcon.getCon();
                 PreparedStatement st = con.prepareStatement(query)) {
                st.setInt(1, memberId);
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    books b = new books();
                    b.setId(rs.getInt("Id"));
                    b.setSubject(rs.getString(("subject")));
                    b.setTitle(rs.getString(("title")));
                    b.setAuthor(rs.getString(("author")));
                    b.setIsReservd(rs.getString(("IsReservd")));

                    reservedBooks.add(b);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return reservedBooks;
        }


        @FXML
        public ObservableList<books> getBooks1() {
            ObservableList<books> books = FXCollections.observableArrayList();
            String query = "SELECT * FROM books WHERE IsReservd = '1'";

            con = DBcon.getCon();
            try {
                st = con.prepareStatement(query);
                rs = st.executeQuery();
                while (rs.next()) {
                    books b = new books();
                    b.setId(rs.getInt("Id"));
                    b.setSubject(rs.getString(("subject")));
                    b.setTitle(rs.getString(("title")));
                    b.setAuthor(rs.getString(("author")));
                    b.setIsReservd(rs.getString(("IsReservd")));

                    books.add(b);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return books;
        }


        @FXML
        public ObservableList<books> getBooks() {
            ObservableList<books> books = FXCollections.observableArrayList();
            String query = "select * from books";

            con = DBcon.getCon();
            try {
                st = con.prepareStatement(query);
                rs = st.executeQuery();
                while (rs.next()) {

                    books b = new books();
                    b.setId(rs.getInt("Id"));
                    b.setSubject(rs.getString(("subject")));
                    b.setTitle(rs.getString(("title")));
                    b.setAuthor(rs.getString(("author")));
                    b.setIsReservd(rs.getString(("IsReservd")));

                    books.add(b);
                }
            } catch (SQLException e) {

                throw new RuntimeException(e);
            }
            return books;
        }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            if (chek == 1) {
                showBooks1();
            } else {
                showBooks();
            }


        }

        @FXML
        private TableView tbl_reservdBooks;

        @FXML
        public void showBooks1() {
            if (MainController.userId != 1 && chek == 1) {
                if (chektable) {
                    ObservableList<books> list = getBooks1();
                    tbl_reservdBooks.setItems(list);
                    colID.setCellValueFactory(new PropertyValueFactory<books, Integer>("id"));
                    colSubject.setCellValueFactory((new PropertyValueFactory<books, String>("subject")));
                    colTitle.setCellValueFactory((new PropertyValueFactory<books, String>("title")));
                    colAuthor.setCellValueFactory((new PropertyValueFactory<books, String>("author")));
                    colIs.setCellValueFactory((new PropertyValueFactory<books, String>("IsReservd")));
                }
            }
        }

        @FXML
        public void showBooks() {
            if (MainController.userId != 1) {
                if (chektable) {
                    ObservableList<books> list = getBooks1();
                    tbl_books.setItems(list);
                    colID.setCellValueFactory(new PropertyValueFactory<books, Integer>("id"));
                    colSubject.setCellValueFactory((new PropertyValueFactory<books, String>("subject")));
                    colTitle.setCellValueFactory((new PropertyValueFactory<books, String>("title")));
                    colAuthor.setCellValueFactory((new PropertyValueFactory<books, String>("author")));
                    colIs.setCellValueFactory((new PropertyValueFactory<books, String>("IsReservd")));
                }
            } else {
                if (chektable) {
                    ObservableList<books> list = getBooks();
                    tbl_books.setItems(list);
                    colID.setCellValueFactory(new PropertyValueFactory<books, Integer>("id"));
                    colSubject.setCellValueFactory((new PropertyValueFactory<books, String>("subject")));
                    colTitle.setCellValueFactory((new PropertyValueFactory<books, String>("title")));
                    colAuthor.setCellValueFactory((new PropertyValueFactory<books, String>("author")));
                    colIs.setCellValueFactory((new PropertyValueFactory<books, String>("IsReservd")));
                }


            }
        }

        public void getData(javafx.scene.input.MouseEvent mouseEvent) {
            books book = (books) tbl_books.getSelectionModel().getSelectedItem();
            id = book.getId();


        }


        @FXML
        private Button btn_return1;

        @FXML

        private void return1() {
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example.onlinelibrary/memberPage.fxml"));
                Parent root = fxmlLoader.load();

                Stage newStage = new Stage();
                newStage.setTitle("2X2");
                newStage.setScene(new Scene(root, 800, 700));
                Stage currentStage = (Stage) btn_return1.getScene().getWindow();

                currentStage.close(); // Close the current stage

                newStage.show(); // Show the new stage

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        @FXML
        private Button btn_return2;


        @FXML
        private void return2() {
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example.onlinelibrary/memberPage.fxml"));
                Parent root = fxmlLoader.load();

                Stage newStage = new Stage();
                newStage.setTitle("2X2");
                newStage.setScene(new Scene(root, 800, 700));
                Stage currentStage = (Stage) btn_return2.getScene().getWindow();

                currentStage.close(); // Close the current stage

                newStage.show(); // Show the new stage

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        private int bookId;

        public int getBookId() {
            return bookId;
        }


    }
}