package gui;

import database.DatabaseException;
import database.ProductException;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class cartDisplayScreen extends GUIRun implements Initializable {

    public class listObject {
        public SimpleStringProperty name = new SimpleStringProperty();
        public SimpleStringProperty quantity = new SimpleStringProperty();
        public SimpleStringProperty price = new SimpleStringProperty();

        public String getName() {
            return name.get();
        }

        public String getQuantity() {
            return quantity.get();
        }

        public String getPrice() {
            return price.get();
        }
    }

    @FXML
    private ChoiceBox<String> options;
    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    public TableView<listObject> table;

    @FXML
    public TableColumn quantity;

    @FXML
    public TableColumn name;

    @FXML
    public TableColumn price;

    ObservableList<listObject> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.removeAll(list);
        list.addAll("Account", "Contact Us", "Sign Out");
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

        name.setCellValueFactory(new PropertyValueFactory<surfStores.listObject, String>("Name"));
        quantity.setCellValueFactory(new PropertyValueFactory<surfStores.listObject, String>("Quantity"));
        price.setCellValueFactory(new PropertyValueFactory<surfStores.listObject, String>("Price"));

        data = FXCollections.observableArrayList();
        table.setItems(data);

        for (int i = 0; i < Main.endUser.getCart().getProducts().size(); i++) {
            listObject l = new listObject();
            l.name.set(Main.endUser.getCart().getProducts().get(i).getName());
            l.quantity.set((Main.endUser.getCart().getQuantity().get(i).toString()));
            l.price.set("" + Main.endUser.getCart().getProducts().get(i).getfPrice());
            data.add(l);
        }

    }

    @FXML
    protected void CheckOutScreen(ActionEvent event) throws IOException {
        try {
            Main.endUser.getCart().checkout();
            Main.pop();
        } catch (DatabaseException e) {
            Main.pop();
        } catch (ProductException e) {
            Main.pop();
        }
    }

    @FXML
    protected void HandleContinue(ActionEvent event) {

        table.getItems().remove(table.getSelectionModel().getSelectedItem());
        try {
            Main.endUser.getCart().removeFromCart(table.getSelectionModel().getSelectedItem().getName());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Remove Process");
            alert.setHeaderText("Can't remove element");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}

