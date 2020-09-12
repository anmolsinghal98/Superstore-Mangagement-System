package gui;

import database.CategoryException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCat extends GUIRun implements Initializable {

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
    private TextField newCatName;

    public static String name;
    public static database.Category category;

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

    @FXML
    protected void HandleContinue(ActionEvent event) throws IOException {

        name = newCatName.getText();
        String s=categories.getValue();
        try {
            category=admin.searchCategory(s);
            Main.pop();
        }
        catch (CategoryException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert Process");
            alert.setHeaderText("Database Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


}
