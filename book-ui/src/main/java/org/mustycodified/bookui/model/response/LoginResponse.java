package org.mustycodified.bookui.model.response;


import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginResponse {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private StringProperty accessToken = new SimpleStringProperty();
    private LongProperty expiresIn = new SimpleLongProperty();


    public LoginResponse(LongProperty id, StringProperty firstName, StringProperty lastName, StringProperty accessToken, LongProperty expiresIn) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
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

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getAccessToken() {
        return accessToken.get();
    }

    public StringProperty accessTokenProperty() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken.set(accessToken);
    }

    public long getExpiresIn() {
        return expiresIn.get();
    }

    public LongProperty expiresInProperty() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn.set(expiresIn);
    }
}
