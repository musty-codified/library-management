package org.mustycodified.bookui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.mustycodified.bookui.model.request.Book;
import org.mustycodified.bookui.service.RestClient;

import java.util.List;

public class BookController {
    @FXML private TableView<Book> bookTable;
    private ObservableList<Book> books = FXCollections.observableArrayList();
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

    TableColumn<Book, String> titleColumn;
    TableColumn<Book, String> authorColumn;
    TableColumn<Book, String> priceColumn;
    TableColumn<Book, String> quantityColumn;
    TableColumn<Book, String> isbnColumn;
    public BookController() {
    }

    @FXML
    public void initialize() {
        setupTable();
        setupActions();
        loadBooks();
    }

    private void setupTable() {
        titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        authorColumn.setMinWidth(200);

        priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString());
        priceColumn.setMinWidth(200);

        quantityColumn = new TableColumn<>("Quantity");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString());
        priceColumn.setMinWidth(200);

        isbnColumn = new TableColumn<>("ISBN");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString());
        priceColumn.setMinWidth(200);

        bookTable.getColumns().addAll(titleColumn, authorColumn, priceColumn, quantityColumn, isbnColumn);
        bookTable.setItems(books);
    }

    private void setupActions() {
        addButton.setOnAction(e -> addBook());
        updateButton.setOnAction(e -> updateBook());
        deleteButton.setOnAction(e -> deleteBook());
        refreshButton.setOnAction(e -> loadBooks());

        bookTable.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                titleField.setText(newSelection.getTitle());
                authorField.setText(newSelection.getAuthor());
                priceField.setText(newSelection.getPrice().toString());
                quantityField.setText(newSelection.getQuantity().toString());
                quantityField.setText(newSelection.getIsbn());
            }
        });
    }

    //Fetch All Books
    @FXML private void loadBooks() {
        List<Book> bookList = RestClient.fetchBooks();
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
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            selectedBook.setTitle(titleField.getText());
            selectedBook.setAuthor(authorField.getText());
            selectedBook.setPrice(Double.parseDouble(priceField.getText()));
            RestClient.updateBook(selectedBook);
            loadBooks();
        }
    }

    //Delete Book
    @FXML private void deleteBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            RestClient.deleteBook(selectedBook.getId());
            loadBooks();
        }
    }

}
