package org.mustycodified.bookui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.mustycodified.bookui.model.Book;
import org.mustycodified.bookui.service.RestClient;


public class BookController {
    private TableView<Book> tableView = new TableView<>();
    private ObservableList<Book> books = FXCollections.observableArrayList();
    private TextField titleField = new TextField();
    private TextField authorField = new TextField();
    private TextField priceField = new TextField();
    @FXML
    private Button addButton = new Button("Add");
    private Button updateButton = new Button("Update");
    private Button deleteButton = new Button("Delete");
    private Button refreshButton = new Button("Refresh");

    public BookController() {
        setupTable();
        setupActions();
//        loadBooks();
    }

    private void setupTable() {
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        authorColumn.setMinWidth(200);

        TableColumn<Book, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString());
        priceColumn.setMinWidth(200);

        tableView.getColumns().addAll(titleColumn, authorColumn, priceColumn);
        tableView.setItems(books);
    }

    private void setupActions() {
        addButton.setOnAction(e -> addBook());
        updateButton.setOnAction(e -> updateBook());
        deleteButton.setOnAction(e -> deleteBook());
//        refreshButton.setOnAction(e -> loadBooks());
            titleField.setPromptText("Title");
            titleField.setMinWidth(100);
            priceField.setPromptText("Price");
            priceField.setMinWidth(100);
            authorField.setPromptText("Author");
            authorField.setMinWidth(100);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                titleField.setText(newSelection.getTitle());
                authorField.setText(newSelection.getAuthor());
                priceField.setText(newSelection.getPrice().toString());
            }
        });
    }

//    private void loadBooks() {
//        List<Book> bookList = RestClient.fetchBooks();
//        books.setAll(bookList);
//    }

    private void addBook() {
        Book newBook = new Book(titleField.getText(), authorField.getText(), Double.parseDouble(priceField.getText()));
        RestClient.addBook(newBook);
        titleField.clear();
        authorField.clear();
        priceField.clear();
//        loadBooks();
    }

    private void updateBook() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            selectedBook.setTitle(titleField.getText());
            selectedBook.setAuthor(authorField.getText());
            selectedBook.setPrice(Double.parseDouble(priceField.getText()));
            RestClient.updateBook(selectedBook);
            titleField.clear();
            authorField.clear();
            priceField.clear();
//            loadBooks();
        }
    }

    private void deleteBook() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            RestClient.deleteBook(selectedBook.getId());
//            loadBooks();
        }
    }

    public TableView<Book> getTableView() {
        return tableView;
    }

    public HBox getForm() {
        return new HBox(titleField, authorField, priceField, addButton, updateButton, deleteButton, refreshButton);
    }


}
