package org.mustycodified.bookui.model.response;

import javafx.beans.property.*;

public class BookResponse {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty author = new SimpleStringProperty();
    private IntegerProperty quantity = new SimpleIntegerProperty();
    private StringProperty isbn = new SimpleStringProperty();
    private StringProperty publishedDate = new SimpleStringProperty();

    public BookResponse(StringProperty title, StringProperty author, IntegerProperty quantity, StringProperty isbn, StringProperty publishedDate) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
    }

    public BookResponse() {
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

    public String getPublishedDate() {
        return publishedDate.get();
    }

    public StringProperty publishedDateProperty() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate.set(publishedDate);
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

    public Integer getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    @Override
    public String toString(){
        return "BookResponse { " +
                "id : '" + id.get() + '\'' +
                "title : '" + this.title.get() + '\'' +
                "author :" + this.author.get() + '\'' +
                "quantity : " + this.quantity.get() +'\'' +
                "isbn :" + this.isbn.get() + '\'' +
                "publishedDate : " + this.publishedDate.get()+
                "}";
    }

}
