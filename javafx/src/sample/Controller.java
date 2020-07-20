package sample;


import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.*;


public class Controller {

    private Scene stage;

    public Scene getStage() {
        return stage;
    }

    public void setStage(Scene stage) {
        this.stage = stage;
    }

    public void connect() {
        TextField username = (TextField) stage.lookup("#username");
        TextField password = (TextField) stage.lookup("#password");
        Label status = (Label) stage.lookup("#status");

        User u = MySQLConnect.loggin(username.getText(), password.getText());
        if (u != null) {
            status.setText("Status:Connected...");
            AnchorPane pane;
            pane.getScene().getRoot().
            TableView<Message> gp = (TableView<Message>) stage.lookup("#messages");
            ObservableList<Message> ll = FXCollections.observableArrayList();
            gp.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("Id"));
            gp.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("From"));
            gp.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("Message"));
            List<Message> message_list = MySQLConnect.getMessages(username.getText());
            ll.addAll(message_list);
            gp.setItems(ll);
        } else {
            status.setText("Status:Not Connected...");
        }


    }
}
