module org.mustycodified.bookui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires spring.web;
    requires spring.beans;
    requires com.fasterxml.jackson.databind;
    requires spring.jcl;
    requires java.sql;


    opens org.mustycodified.bookui to javafx.fxml;
    exports org.mustycodified.bookui;
    exports org.mustycodified.bookui.model;
    opens org.mustycodified.bookui.model to javafx.fxml;
    exports org.mustycodified.bookui.service;
    opens org.mustycodified.bookui.service to javafx.fxml;
    exports org.mustycodified.bookui.controller;
    opens org.mustycodified.bookui.controller to javafx.fxml;
    exports org.mustycodified.bookui.model.request;
    opens org.mustycodified.bookui.model.request to javafx.fxml;
}