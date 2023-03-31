package otan.benfordslaw.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import otan.benfordslaw.Utils;
import otan.benfordslaw.entities.Transaction;
import otan.benfordslaw.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static otan.benfordslaw.BenfordApplication.transactions;
import static otan.benfordslaw.BenfordApplication.users;

public class BenfordController {
    @FXML
    private BorderPane benfordPane;
    private File file;
    private Button selectFileButton;
    private Button reloadButton;
    private ToolBar toolBar;
    private ToolBar userBar;
    private ChoiceBox<String> senderChoiceBox;
    private ChoiceBox<String> receiverChoiceBox;
    private ListView<Transaction> transactionView;
    private ListView<String> userView;
    private Utils utils = new Utils();

    @FXML
    public void initialize() {
        transactionView = new ListView<>();
        userView = new ListView<>();
        selectFileButton = new Button("Select File");
        reloadButton = new Button("Reload tables (klikni to po naloadění fajlu, jinak se nenačtou ty select box po tím)");
        FileChooser fileChooser = new FileChooser();
        selectFileButton.setOnAction(event -> {
            file = fileChooser.showOpenDialog(benfordPane.getScene().getWindow());
            try {
                transactions = utils.loadFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        reloadButton.setOnAction(event -> {
            reloadViews();
        });


        senderChoiceBox = new ChoiceBox<>();
        receiverChoiceBox = new ChoiceBox<>();
        senderChoiceBox.setOnAction(actionEvent -> setUsers());
        receiverChoiceBox.setOnAction(actionEvent -> setUsers());
        userBar = new ToolBar(senderChoiceBox, receiverChoiceBox);
        toolBar = new ToolBar(selectFileButton, reloadButton);
        reloadViews();
        benfordPane.setCenter(userView);
        benfordPane.setTop(new VBox(toolBar, userBar));

        benfordPane.widthProperty().addListener((observable, oldValue, newValue) -> transactionView.setPrefWidth(benfordPane.getWidth()));
        benfordPane.heightProperty().addListener((observable, oldValue, newValue) -> transactionView.setPrefHeight(benfordPane.getHeight() * 0.85));
    }

    public void reloadViews() {
        if (users != null) {
            ObservableList<String> items = FXCollections.observableArrayList();
            for (User user : users.values()) {
                items.add(user.getName());
            }
            senderChoiceBox.setItems(items);
            receiverChoiceBox.setItems(items);
        }
    }

    public void setUsers() {
        String sender = senderChoiceBox.getValue();
        String receiver = receiverChoiceBox.getValue();
        if (sender != null && receiver != null) {
            ObservableList<String> items = FXCollections.observableArrayList();
            ArrayList<Transaction> tr = utils.getTransactionsBetween(sender, receiver);
            for (int i = 1; i < 10; i++) {
                float real = ((float) utils.getTransactionsStartingWith(tr, i).size() / (float) tr.size()) * 100;
                items.add(i + ": Očekávaná hodnota: " + utils.P(i) * 100 + "% | Reálná hodnota: " + real + "%" + " | Rozdíl: " + (real - utils.P(i) * 100) + "%");
                System.out.println(utils.getTransactionsStartingWith(tr, i).size());
            }
            userView.setItems(items);
        }
    }
}