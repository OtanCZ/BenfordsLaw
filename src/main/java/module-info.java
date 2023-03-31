module otan.benford.benfordslaw {
    requires javafx.controls;
    requires javafx.fxml;


    opens otan.benfordslaw to javafx.fxml;
    exports otan.benfordslaw;
    exports otan.benfordslaw.controllers;
    opens otan.benfordslaw.controllers to javafx.fxml;
}