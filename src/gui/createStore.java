package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class createStore extends GUIRun {
    public static boolean flag = true;

    @FXML
    TextField name;

    @FXML
    TextField loc;

    @FXML
    TextField dPrice;

    @FXML
    TextField maxQuant;

    @FXML
    protected void handleContinue(ActionEvent event) throws IOException {

        database.Store w;

        long dprice = 0;
        float maxquant = 0;

        if(dPrice.getText().length()>0 && maxQuant.getText().length()>0) {
            try {
                dprice = Long.parseLong(dPrice.getText());
                maxquant = Long.parseLong(maxQuant.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Creation Process");
                alert.setHeaderText("Invalid Information");
                alert.setContentText("Please enter the information correctly.");
                alert.showAndWait();
            }
        }

        if(name.getText().length()>0 && loc.getText().length()>0) {
            w=new database.Store(name.getText(),loc.getText(), dprice, maxquant);
            Main.getSuperStore().addStore(w);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Creation Process");
            alert.setHeaderText("Invalid Information");
            alert.setContentText("Please enter the information correctly.");
            alert.showAndWait();
        }

        ArrayList<database.StoreAdmin> admins = Main.getSuperStore().getStoreAdmin_list();

        if(flag) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createStoreAdmin.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            Main.pop();
            Main.push(stage);
        } else {
            System.out.println("admins null");
            Main.pop();
        }
    }
}
