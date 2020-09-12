package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteCat extends GUIRun implements Initializable {

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

    @FXML
    private ChoiceBox<String> categories;
    ObservableList<String> list= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.removeAll(list);
        if(admin==null) {
            loadAdmin();
        }
        for (int i=0; i<Main.getSuperStore().getCategories().size(); i++) {
            list.add(Main.getSuperStore().getCategories().get(i).getName());
        }
        categories.getItems().addAll(list);
    }



    public static String categoryName;

    @FXML
    protected void HandleContinue(ActionEvent event) throws IOException {
        categoryName = categories.getValue();
        Main.pop();
    }

}
