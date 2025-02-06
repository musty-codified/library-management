package org.mustycodified.bookui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.mustycodified.bookui.model.request.Book;
import org.mustycodified.bookui.model.response.BookResponse;
import org.mustycodified.bookui.service.RestClient;

import java.util.List;

public class BookController {
    @FXML private TableView<BookResponse> bookTable;
    private ObservableList<BookResponse> books = FXCollections.observableArrayList();
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField priceField;
    @FXML private TextField quantityField;
    @FXML private TextField isbnField;

    @FXML
    private Button addButton;
    @FXML
    private Button updateButton = new Button("Update");
    @FXML
    private Button deleteButton = new Button("Delete");
    @FXML
    private Button refreshButton = new Button("Refresh");

    @FXML
    TableColumn<BookResponse, String> titleColumn;
    @FXML
    TableColumn<BookResponse, String> authorColumn;
    @FXML
    TableColumn<BookResponse, String> quantityColumn;

    public BookController() {
    }

    @FXML
    public void initialize() {
        loadBooks();
        setupTable();
        setupActions();  // Set up button actions and selection listener

    }


    private void setupTable() {
        // Define the Title column
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        // Define the Author column
        authorColumn.setMinWidth(200);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        // Define the Quantity column
        quantityColumn.setMinWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        bookTable.setItems(books);

    }

    private void setupActions() {

        bookTable.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        titleField.setText(newSelection.getTitle());
                        authorField.setText(newSelection.getAuthor());
                        quantityField.setText(newSelection.getQuantity().toString());
                    }
                });

        addButton.setOnAction(e -> addBook());
        updateButton.setOnAction(e -> updateBook());
        deleteButton.setOnAction(e -> deleteBook());
        refreshButton.setOnAction(e -> loadBooks());


    }

    //Fetch All Books
    @FXML private void loadBooks() {
        List<BookResponse> bookList = RestClient.fetchBooks();
        books.setAll(bookList);
    }

    //Add a Book
    @FXML private void addBook() {
        Book newBook = new Book(
                titleField.getText(),
                authorField.getText(),
                isbnField.getText(),
                Integer.parseInt(quantityField.getText()),
                Double.parseDouble(priceField.getText()));

        RestClient.addBook(newBook);
        loadBooks();
    }

    //Update Book details
    @FXML private void updateBook() {
        BookResponse selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            selectedBook.setTitle(titleField.getText());
            selectedBook.setAuthor(authorField.getText());
            selectedBook.setQuantity(Integer.parseInt(quantityField.getText()));

            // Convert BookResponse to Book for the backend request
            Book bookToUpdate = new Book(
                    selectedBook.getTitle(),
                    selectedBook.getAuthor(),
                    "",
                    selectedBook.getQuantity(),
                    0.0
            );
            bookToUpdate.setId(selectedBook.getId());
            RestClient.updateBook(bookToUpdate);
            loadBooks();
        }
    }

    //Delete Book
    @FXML private void deleteBook() {
        BookResponse selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            RestClient.deleteBook(selectedBook.getId());
            loadBooks();
        }
    }

}
