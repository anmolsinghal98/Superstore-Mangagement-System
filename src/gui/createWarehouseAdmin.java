package gui;

import database.WarehouseAdmin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;

public class createWarehouseAdmin extends GUIRun {

    @FXML
    TextField id;

    @FXML
    PasswordField password;

    @FXML
    TextField name;

    @FXML
    TextField mobile;

    long mobileNumber;

    @FXML
    protected void handleContinue(ActionEvent event) throws IOException {
        try {
            ArrayList<database.WarehouseAdmin> admins = Main.getSuperStore().getWarehouseAdmin_list();
            ArrayList<database.Warehouse> ware = Main.getSuperStore().getWarehouse_list();
            if(name.getText().length()>0 && id.getText().length()>0 && password.getText().length()>0 && mobile.getText().length()>0) {
                mobileNumber = Long.parseLong(mobile.getText());
                if(admins.size()>0 && ware.size()>0) {
                    if(admins.get(admins.size()-1).getMy_warehouse().equals(ware.get(ware.size()-1))) {
                        System.out.println("admin present, warehouse not present");
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createWarehouse.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));
                        Main.pop();
                        Main.push(stage);
                        stage.setOnShown(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                System.out.println("flag set");
                                createWarehouse.flag = false;
                            }
                        });
                        createWarehouse.flag = false;
                    } else {
                        System.out.println("admin present, warehouse present");
                        Main.pop();
                        Main.getSuperStore().addWarehouseAdmin(new WarehouseAdmin(name.getText(), mobileNumber, id.getText(), password.getText(), ware.get(ware.size()-1)));
                        createWarehouse.flag = true;
                    }
                } else if(ware.size()>0){
                    System.out.println("admin not present, warehouse present");
                    Main.pop();
                    Main.getSuperStore().addWarehouseAdmin(new WarehouseAdmin(name.getText(), mobileNumber, id.getText(), password.getText(), ware.get(ware.size()-1)));
                } else {
                    System.out.println("admin not present, warehouse not present");
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createWarehouse.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    Main.pop();
                    Main.push(stage);
                    createWarehouse.flag = false;
                    stage.setOnHidden(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            System.out.println("warehouse created");
                            Main.getSuperStore().addWarehouseAdmin(new WarehouseAdmin(name.getText(), mobileNumber, id.getText(), password.getText(), ware.get(ware.size()-1)));
                            System.out.println("Database entry done");
                            createWarehouse.flag = true;
                        }
                    });
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Creation Process");
                alert.setHeaderText("Invalid Information");
                alert.setContentText("Please enter the information correctly.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Creation Process");
            alert.setHeaderText("Invalid Information");
            alert.setContentText("Please enter the information correctly.");
            alert.showAndWait();
        }
    }
}
