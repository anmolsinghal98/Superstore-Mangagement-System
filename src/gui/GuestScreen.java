package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GuestScreen extends GUIRun implements Initializable {

    // TODO bonus: Today's deals

    @FXML
    private ChoiceBox<String> options;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        options.getItems().addAll("Account","Contact Us","Sign Out");
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
    }

    @FXML
    protected void addFundsScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddFunds.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Main.endUser.setFunds(AddFunds.customerFunds);
            }
        });
    }

    @FXML
    protected void ShopByCategory(ActionEvent event) throws IOException {
        if(Main.endUser.getLinkedStore()==null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("surfStores.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            Main.push(stage);
            stage.setOnHidden(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccessInventory.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));
                        Main.push(stage);
                    } catch (Exception e) {}
                }
            });
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccessInventory.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            Main.push(stage);
        }
    }

    @FXML
    protected void surfStores(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("surfStores.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
    }

    @FXML
    protected void DisplayCart(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cartDisplayScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
    }
}



