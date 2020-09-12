package gui;

import database.ProductException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductDisplay extends GUIRun implements Initializable {
    @FXML public Label product;
    @FXML public Label quantityAvail;
    @FXML public Label price;
    @FXML public TextField quantityDesired;
    @FXML public Button addCart;
    @FXML public Label quantityDesiredLabel;

    public static String productName = "";
    public static String quantityAvailName = "";
    public static String priceName = "";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        product.setText(productName);
        quantityAvail.setText(quantityAvailName);
        price.setText(priceName);

        if(Main.endUser==null) {
            quantityDesired.setVisible(false);
            addCart.setVisible(false);
            quantityDesiredLabel.setVisible(false);
        }
    }

    @FXML
    public void HandleContinue(ActionEvent event) {
        Integer p=Integer.parseInt(quantityDesired.getText());
        try {

            database.Product pr=Main.endUser.getLinkedStore().searchProduct(productName);
            Main.endUser.getCart().addToCart(pr,p);
            productName = "";
            quantityAvailName = "";
            priceName = "";
            Main.pop();
        }
        catch (ProductException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Process Halted");
            alert.setHeaderText("Database Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}