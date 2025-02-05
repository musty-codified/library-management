package org.mustycodified.bookui.model.request;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Login {
    private StringProperty email = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

    public Login() {
    }

    public Login( String email, String password) {

        this.setEmail(email);
        this.setPassword(password);
    }


    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

}
