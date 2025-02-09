package org.mustycodified.bookui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mustycodified.bookui.config.SceneManager;
import org.mustycodified.bookui.model.ApiResponse;
import org.mustycodified.bookui.model.request.BookRequestModel;
import org.mustycodified.bookui.model.response.BookResponse;
import org.mustycodified.bookui.service.Consumer;
import org.mustycodified.bookui.service.RestClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookController {
    private static final Log log = LogFactory.getLog(BookController.class);
    @FXML
    private TableView<BookResponse> bookTable;
    private ObservableList<BookResponse> books = FXCollections.observableArrayList();
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField isbnField;
    @FXML
    private TextField searchField;

    @FXML
    private Button addButton;
    @FXML
    private Button updateButton = new Button("Update");
    @FXML
    private Button deleteButton = new Button("Delete");
    @FXML
    private Button refreshButton = new Button("Refresh");
    @FXML
    private Button searchButton = new Button("Search");

    @FXML private Button logoutButton;


    @FXML
    TableColumn<BookResponse, String> titleColumn;
    @FXML
    TableColumn<BookResponse, String> authorColumn;
    @FXML
    TableColumn<BookResponse, String> quantityColumn;
    @FXML
    TableColumn<BookResponse, String> isbnColumn;
    @FXML
    TableColumn<BookResponse, String> publishedDateColumn;

    @FXML
    private Label pageLabel;
    @FXML
    private Button prevButton = new Button("PrevPage");
    @FXML
    private Button nextButton = new Button("NextButton");
    private int currentPage = 1;
    private int totalPages = 1;
    private int pageSize = 10;

    @FXML
    private void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            loadBooks();
        }
    }

    @FXML
    private void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            loadBooks();
        }
    }

    public BookController() {
    }

    @FXML
    public void initialize() {
        loadBooks();
        setupTable();
        setupActions();

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

        // Define the ISBN column
        isbnColumn.setMinWidth(200);
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        // Define the PublishedDate column
        publishedDateColumn.setMinWidth(200);
        publishedDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
        publishedDateColumn.setCellFactory(column -> new TableCell<>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    // Format the date
                    LocalDateTime date = LocalDateTime.parse(item);
                    setText(date.format(formatter));
                }
            }
        });
        bookTable.setItems(books);
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


    }

    private void setupActions() {
        bookTable.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        titleField.setText(newSelection.getTitle());
                        authorField.setText(newSelection.getAuthor());
                        quantityField.setText(newSelection.getQuantity().toString());
                        isbnField.setText(newSelection.getIsbn());
                    }
                });

        addButton.setOnAction(e -> addBook());
        updateButton.setOnAction(e -> updateBook());
        deleteButton.setOnAction(e -> deleteBook());
        refreshButton.setOnAction(e -> loadBooks());
        logoutButton.setOnAction(e-> logout());

        // Search functionality
        searchButton.setOnAction(e -> {
            String searchText = searchField.getText();
            searchBooks(searchText, currentPage, totalPages);
        });

    }

    //Search Books with pagination
    @FXML
    public void searchBooks(String searchText, int currentPage, int pageSize) {
        ApiResponse.Wrapper<List<BookResponse>> bookList = RestClient.searchBooks(searchText, currentPage, pageSize);
        List<BookResponse> bookContent = bookList.getContent();
        books.setAll(bookContent);
    }

    //Fetch All Books
    @FXML
    private void loadBooks() {
        String searchText = searchField.getText().trim().isEmpty() ? null : searchField.getText();
        ApiResponse.Wrapper<List<BookResponse>> bookList = RestClient.searchBooks(searchText, currentPage, pageSize);
        List<BookResponse> bookContent = bookList.getContent();
        books.setAll(bookContent);
        totalPages = bookList.getTotalPages();
        currentPage = bookList.getCurrentPage();
        pageLabel.setText("Page " + currentPage + " of " + totalPages);
        prevButton.setDisable(currentPage <= 1);
        nextButton.setDisable(currentPage >= totalPages);

    }

    //Add a Book
    @FXML
    private void addBook() {
        BookRequestModel newBook = new BookRequestModel(
                titleField.getText(),
                authorField.getText(),
                isbnField.getText(),
                Integer.parseInt(quantityField.getText()),
                Double.parseDouble(priceField.getText()));
        clearBookField();
        RestClient.addBook(newBook);
        loadBooks();
    }

    //Update Book details
    @FXML
    private void updateBook() {
        BookResponse selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            selectedBook.setTitle(titleField.getText());
            selectedBook.setAuthor(authorField.getText());
            selectedBook.setQuantity(Integer.parseInt(quantityField.getText()));
            selectedBook.setIsbn(isbnField.getText());

            // Convert BookResponseDto to Book for the backend request
            BookRequestModel bookToUpdate = new BookRequestModel(
                    selectedBook.getTitle(),
                    selectedBook.getAuthor(),
                    selectedBook.getIsbn(),
                    selectedBook.getQuantity(),
                    Double.parseDouble(priceField.getText())
            );
            bookToUpdate.setId(selectedBook.getId());
            clearBookField();
            RestClient.updateBook(bookToUpdate);
            loadBooks();
        }
    }

    //Delete Book
    @FXML
    private void deleteBook() {
        BookResponse selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            RestClient.deleteBook(selectedBook.getId());
            loadBooks();
        }
    }

    public void searchBooks(ActionEvent actionEvent) {
        String searchText = searchField.getText();
        searchBooks(searchText, currentPage, totalPages);
    }

    private void clearBookField() {
        titleField.clear();
        authorField.clear();
        isbnField.clear();
        quantityField.clear();
        priceField.clear();
    }

    @FXML
    private void logout() {
        Consumer.clearAuthToken();
        SceneManager.logout();
    }
}
