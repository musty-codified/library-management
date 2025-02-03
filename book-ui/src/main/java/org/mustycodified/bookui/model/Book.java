package org.mustycodified.bookui.model;

import javafx.beans.property.*;


public class Book {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty author = new SimpleStringProperty();
    private DoubleProperty price = new SimpleDoubleProperty();

    public Book() {
    }

    public Book(String title, String author, double price) {
        this.title.set(title);
        this.author.set(author);
        this.price.set(price);
    }

    public Long getId() {
        return id.get();
    }

    public String getTitle() {
        return title.get();
    }

    public String getAuthor() {
        return author.get();
    }

    public Double getPrice() {
        return price.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty authorProperty() {
        return author;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

}
