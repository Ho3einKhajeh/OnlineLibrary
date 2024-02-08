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
import java.util.Arrays;
import java.util.ResourceBundle;

public class bookController implements Initializable {

    Connection con =null;
    PreparedStatement st=null;

    @FXML
    private  int id;
    @FXML
    private Button btn_Exit;
    ResultSet rs=null;

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



    public  static  int chek;
    @FXML

    private TableColumn<books,String> colSubject;
    @FXML

    private TableColumn<books,String> colTitle;
    @FXML

    private TableColumn<books,String> colIs;

    @FXML

    private TableColumn<books,String> colAuthor;
    @FXML
    public static  boolean chektable=true;
@FXML
    private TableColumn<books,String> IsAvailable;


@FXML
private TextField txt_showMessage;


    @FXML
    void createBook(ActionEvent event){

        String insert="insert into books(title,subject,author,IsReservd) values(?,?,?,?)";
        con=DBcon.getCon();
        try{

            st=con.prepareStatement(insert);
            st.setString(1,txt_AddBookTitle.getText());
            st.setString(2,txt_AddBookSubject.getText());
            st.setString(3,txt_AddBookAuthor.getText());
            st.setString(4,"1");
            st.executeUpdate();

            showBooks();

            txt_showMessage.setText("کتاب با موفقیت افزوده شد");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }


    @FXML
    private Button btn_returnAddBook;


    @FXML
    private void ReturnAdminPage() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example.onlinelibrary/AdminPage.fxml"));
            Parent root = fxmlLoader.load();

            Stage newStage = new Stage();
            newStage.setTitle("2X2");
            newStage.setScene(new Scene(root, 800, 700));
            Stage currentStage = (Stage) btn_returnAddBook.getScene().getWindow();

            currentStage.close(); // Close the current stage

            newStage.show(); // Show the new stage

        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public ObservableList<books> getBooks(){
        ObservableList<books> books= FXCollections.observableArrayList();
        String query="select * from books";

        con= DBcon.getCon();
        try {
            st=con.prepareStatement(query);
            rs=st.executeQuery();
            while (rs.next()){

                books b=new books();
                b.setId(rs.getInt("Id"));
                b.setSubject(rs.getString(("subject")));
                b.setTitle(rs.getString(("title")));
                b.setAuthor(rs.getString(("author")));
                b.setIsReservd(rs.getString(("IsReservd")));

                books.add(b);
            }
        }catch (SQLException e){

            throw new RuntimeException(e);
        }
        return books;
    }


    @Override
    public  void initialize(URL url, ResourceBundle resourceBundle){
    if (chek==1){showBooks1();}
    else if (chek==0){
        showBooks();
    }


    }

    @FXML
    private TableView tbl_reservdBooks;


    ///////////////////
//    @FXML
//    public void showReservedBooks() {
//        MainController mc = new MainController();
//        int memberId = mc.getUserId(); // Get the ID of the currently logged-in user
//
//        ObservableList<books> reservedBooks = getReservedBooks(memberId);
//
//        if (chektable) {
//            tbl_ReservedBooks.setItems(reservedBooks);
//            // Set up your TableColumn bindings here (similar to your showBooks method)
//            // For example:
//            // colID.setCellValueFactory(new PropertyValueFactory<books, Integer>("id"));
//            // colTitle.setCellValueFactory(new PropertyValueFactory<books, String>("title"));
//            // ...
//        }
//    }

    public ObservableList<books> getBooks2() {
        memberId=MainController.userId;
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















    /////////////////////////

    @FXML
    public void showBooks1() {
        if (MainController.userId != 1 && chek==1) {
            if (chektable) {
                ObservableList<books> list = getBooks2();
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
    public void showBooks(){
        if(MainController.userId!=1){  if (chektable){ObservableList<books>list=getBooks1();
            tbl_books.setItems(list);
            colID.setCellValueFactory(new PropertyValueFactory<books,Integer>("id"));
            colSubject.setCellValueFactory((new  PropertyValueFactory<books,String>("subject")));
            colTitle.setCellValueFactory((new  PropertyValueFactory<books,String>("title")));
            colAuthor.setCellValueFactory((new  PropertyValueFactory<books,String>("author")));
            colIs.setCellValueFactory((new  PropertyValueFactory<books,String>("IsReservd")));
        }}
        else {if (chektable){ObservableList<books>list=getBooks();
            tbl_books.setItems(list);
            colID.setCellValueFactory(new PropertyValueFactory<books,Integer>("id"));
            colSubject.setCellValueFactory((new  PropertyValueFactory<books,String>("subject")));
            colTitle.setCellValueFactory((new  PropertyValueFactory<books,String>("title")));
            colAuthor.setCellValueFactory((new  PropertyValueFactory<books,String>("author")));
            colIs.setCellValueFactory((new  PropertyValueFactory<books,String>("IsReservd")));}


}
}
    public void getData(javafx.scene.input.MouseEvent mouseEvent) {
        books book= (books) tbl_books.getSelectionModel().getSelectedItem();
        id=book.getId();


    }
    @FXML
    private  int indres;

    public void getData1(javafx.scene.input.MouseEvent mouseEvent) {
        books book= (books) tbl_reservdBooks.getSelectionModel().getSelectedItem();
        indres=book.getId();


    }

    @FXML
    void deleteBook(ActionEvent event){
        String delete="delete from books where id=?";
        con=DBcon.getCon();
        try {
            st=con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();

        }
        catch (SQLException e){

            throw new RuntimeException(e);
        }

        showBooks();


    }
     private  int bookId;



    private ReservedBooks getReservedBook(int bookId) {
        String query = "SELECT * FROM reserved_books WHERE bookId = ?";
        try (Connection con = DBcon.getCon();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, bookId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                ReservedBooks reservedBook = new ReservedBooks();
                reservedBook.setId(rs.getInt("Id"));
                reservedBook.setMemberId(rs.getInt("memberId"));
                reservedBook.setBookId(rs.getInt("bookId"));

                return reservedBook;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @FXML
    void deleteBook1(ActionEvent event) {
        // Assuming you have a method to get the reserved book information
        ReservedBooks reservedBook = getReservedBook(indres);

        if (reservedBook != null) {
            // Step 1: Remove entry from reserved_books table
            deleteReservedBook(ReservedBooks.getId());

            // Step 2: Update IsReservd in books table
            updateIsReservedStatus(indres, "1");

            showAlert("Book Return Success", "Book returned successfully.", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "Selected book is not reserved.", Alert.AlertType.ERROR);
        }

        showBooks1(); // Refresh the books table
    }



    private void deleteReservedBook(int reservedId) {
        String deleteQuery = "DELETE FROM reserved_books WHERE Id = ?";
        try (Connection con = DBcon.getCon();
             PreparedStatement st = con.prepareStatement(deleteQuery)) {
            st.setInt(1, reservedId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateIsReservedStatus(int bookId, String isReserved) {

        String updateQuery = "UPDATE books SET IsReservd = ? WHERE Id = ?";
        try (Connection con = DBcon.getCon();
             PreparedStatement st = con.prepareStatement(updateQuery)) {
            st.setString(1, isReserved);
            st.setInt(2, bookId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    @FXML
    private void GoToHomePage(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login page.fxml"));
            Parent root = fxmlLoader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Login page");
            newStage.setScene(new Scene(root, 750, 700));

            // Get the current stage from the event source
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            currentStage.close(); // Close the current stage
            newStage.show(); // Show the new stage
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            Stage currentStage = (Stage)  btn_return1.getScene().getWindow();

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


    @FXML
    private void searchBook() {
        ObservableList<books> allBooks = getBooks();

        FilteredList<books> filteredData = new FilteredList<>(allBooks, b -> true);

        txt_SrchBookTitle.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(book -> {
                if (newValue == null || newValue.isEmpty()) {
                    // Show all books when the search field is empty
                    return book.getIsReservd().equals("1");
                }

                // Convert both the book title and search text to lowercase for case-insensitive search
                String lowerCaseFilter = newValue.toLowerCase();

                return book.getTitle().toLowerCase().contains(lowerCaseFilter) &&
                        book.getIsReservd().equals("1");
            });
        });

        // Wrap the filtered data in a SortedList
        SortedList<books> sortedData = new SortedList<>(filteredData);

        // Bind the SortedList to the TableView
        sortedData.comparatorProperty().bind(tbl_books.comparatorProperty());
        tbl_books.setItems(sortedData);
    }



    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    int memberId;
    @FXML
    private void reserveBook(ActionEvent event) {
        MainController mc=new MainController();
        books selectedBook = (books) tbl_books.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
             setBookId(selectedBook.getId());

            // Update the 'IsReserved' column in the 'books' table
            updateIsReservedStatus(bookId);

            // Add a new row to the 'reserved_books' table
            memberId = mc.getUserId();
            addReservedBookToDatabase(memberId, bookId);
            // Show a confirmation message or perform any other actions
            showAlert("Reservation Success", "Book reserved successfully.", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Selection Error", "Please select a book to reserve.", Alert.AlertType.ERROR);
        }
    }
    private void updateIsReservedStatus(int bookId) {

        String updateQuery = "UPDATE books SET IsReservd = '0' WHERE Id = ?";
        try (Connection con = DBcon.getCon();
             PreparedStatement st = con.prepareStatement(updateQuery)) {
            st.setInt(1, bookId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
@FXML
    private void addReservedBookToDatabase(int memberId, int bookId) {

        System.out.println(MainController.userId);
        String insertQuery = "INSERT INTO reserved_books(memberId, bookId) VALUES (?, ?)";
        try (Connection con = DBcon.getCon();
             PreparedStatement st = con.prepareStatement(insertQuery)) {
            st.setInt(1,MainController.userId);
            st.setInt(2, bookId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType, content);
        alert.setTitle(title);
        alert.show();
    }

}
