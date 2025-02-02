module org.mustycodified.bookui {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.mustycodified.bookui to javafx.fxml;
    exports org.mustycodified.bookui;
}