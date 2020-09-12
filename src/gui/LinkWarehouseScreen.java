package gui;

import database.DatabaseException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class LinkWarehouseScreen extends GUIRun implements Initializable {

    @FXML
    private ChoiceBox<String> stores;
    ObservableList<String> storeList= FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> warehouses;
    ObservableList<String> warehouseList= FXCollections.observableArrayList();

    private database.Store storeChosen;

    private database.Warehouse warehouseChosen;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        storeChosen=null;
        warehouseChosen=null;
        storeList.removeAll(storeList);
        warehouseList.removeAll(warehouseList);
        for (int i=0; i<Main.getSuperStore().getStore_list().size(); i++) {
            storeList.add(Main.getSuperStore().getStore_list().get(i).getDetails().getName());
        }
        stores.getItems().addAll(storeList);

        stores.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    warehouseList.removeAll(warehouseList);
                    warehouses.getItems().clear();
                    for (int i = 0; i<Main.getSuperStore().getWarehouse_list().size() ; i++) {
                        warehouseList.add(Main.getSuperStore().getWarehouse_list().get(i).getDetails().getName());
                    }
                    warehouses.getItems().addAll(warehouseList);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Insert Process");
                    alert.setHeaderText("Database Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }

            }
        });
    }

    public void HandleContinue(javafx.event.ActionEvent actionEvent) {
        try {
            storeChosen = Main.getSuperStore().searchStore(stores.getSelectionModel().getSelectedItem());
            warehouseChosen = Main.getSuperStore().searchWarehouse(warehouses.getSelectionModel().getSelectedItem());
            if(storeChosen!=null && warehouseChosen!=null){
                Main.superUser.linkWarehouse(storeChosen,warehouseChosen);
            }
            Main.pop();

        }
        catch(DatabaseException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Process Halted");
            alert.setHeaderText("Database Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
