package org.mustycodified.bookui.model.response;

import javafx.beans.property.*;


public class BookResponse {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty author = new SimpleStringProperty();
    private IntegerProperty quantity = new SimpleIntegerProperty();

    public BookResponse(String title, String author, Integer quantity) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setQuantity(quantity);
    }

    public BookResponse() {
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



    // private LongProperty id = new SimpleLongProperty();
    //    private StringProperty title = new SimpleStringProperty();
    //    private StringProperty author = new SimpleStringProperty();
    //    private IntegerProperty quantity = new SimpleIntegerProperty();

    @Override
    public String toString(){
        return "BookResponse { " +
                "id : '" + id.get() + '\'' +
                "title : '" + this.title.get() + '\'' +
                "author :" + this.author.get() + '\'' +
                 "quantity : " + this.quantity.get() +
                "}";
    }

}
