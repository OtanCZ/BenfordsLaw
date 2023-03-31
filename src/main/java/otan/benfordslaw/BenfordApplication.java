package otan.benfordslaw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import otan.benfordslaw.entities.Transaction;
import otan.benfordslaw.entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BenfordApplication extends Application {
    public static ArrayList<Transaction> transactions;
    public static HashMap<String, User> users;
    public static Utils utils = new Utils();
    @Override
    public void start(Stage stage) throws IOException {
        users = new HashMap<>();
        transactions = new ArrayList<>();
        FXMLLoader fxmlLoader = new FXMLLoader(BenfordApplication.class.getResource("benford-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Benford's Law Podvodníček Finder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}