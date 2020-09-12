package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GuestUserAccount extends GUIRun implements Initializable {

    @FXML
    private ChoiceBox<String> options;
    ObservableList<String> list= FXCollections.observableArrayList();

    @FXML
    private Label balance;

    @FXML
    private ListView<String> transactions;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.removeAll(list);
        list.addAll("Account","Contact Us","Sign Out");
        options.getItems().addAll(list);
        options.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (options.getValue().equals("Account")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GuestUserAccount.fxml"));
                    Parent root1 = null;
                    try {
                        root1 = (Parent) fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    Main.push(stage);
                } else if (options.getValue().equals("Contact Us")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ContactUs.fxml"));
                    Parent root1 = null;
                    try {
                        root1 = (Parent) fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    Main.push(stage);
                } else if (options.getValue().equals("Sign Out")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignOut.fxml"));
                    Parent root1 = null;
                    try {
                        root1 = (Parent) fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    Main.push(stage);
                }
            }
        });

        balance.setText(""+Main.endUser.getFunds());

        for(int i=0;i<Main.endUser.getTransactions().size();i++){
            transactions.getItems().add(Main.endUser.getTransactions().get(i).toString());
        }

    }

    @FXML
    protected void returnHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("guestScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
    }


}
