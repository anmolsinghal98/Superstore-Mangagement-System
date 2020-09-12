package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ReceivedOrders extends GUIRun implements Initializable {
    public Label revenue;

    database.Admin admin;

    private void loadAdmin() {
        if(Main.storeAdmin!=null) {
            admin = Main.storeAdmin;
        } else if(Main.warehouseAdmin!=null) {
            admin = Main.warehouseAdmin;
        } else {
            admin = null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(admin==null){
            loadAdmin();
        }
        revenue.setText(""+admin.getDetails().getRevenue());
    }




}
