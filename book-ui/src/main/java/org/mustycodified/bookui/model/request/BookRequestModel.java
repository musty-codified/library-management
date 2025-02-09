package org.mustycodified.bookui.model.request;

import javafx.beans.property.*;


public class BookRequestModel {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty author = new SimpleStringProperty();
    private StringProperty isbn = new SimpleStringProperty();
    private IntegerProperty quantity = new SimpleIntegerProperty();
    private DoubleProperty price = new SimpleDoubleProperty();


    public BookRequestModel(String title, String author, String isbn, Integer quantity, Double price) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setIsbn(isbn);
        this.setQuantity(quantity);
        this.setPrice(price);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public Double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }
}
