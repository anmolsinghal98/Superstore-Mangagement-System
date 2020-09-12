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

public class createWarehouse extends GUIRun {

    public static boolean flag = true;

    @FXML
    TextField name;

    @FXML
    TextField loc;

    @FXML
    protected void handleContinue(ActionEvent event) throws IOException {

        database.Warehouse w;

        if(name.getText().length()>0 && loc.getText().length()>0) {
            w=new database.Warehouse(name.getText(),loc.getText());
            Main.getSuperStore().addWarehouse(w);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Creation Process");
            alert.setHeaderText("Invalid Information");
            alert.setContentText("Please enter the information correctly.");
            alert.showAndWait();
        }

        ArrayList<database.WarehouseAdmin> admins = Main.getSuperStore().getWarehouseAdmin_list();

        if(flag) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createWarehouseAdmin.fxml"));
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
