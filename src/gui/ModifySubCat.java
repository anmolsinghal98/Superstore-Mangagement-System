package gui;

import database.CategoryException;
import database.SubCategoryException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class ModifySubCat extends GUIRun implements Initializable {

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
    public static database.Category categoryChosen;
    public static database.SubCategory subCategoryChosen;

    @FXML
    private ChoiceBox<String> categories;
    ObservableList<String> listCat= FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> subCategories;
    ObservableList<String> listSubCat = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listCat.removeAll(listCat);
        listSubCat.removeAll(listSubCat);
        if(admin==null) {
            loadAdmin();
        }
        for (int i=0; i<Main.getSuperStore().getCategories().size(); i++) {
            listCat.add(Main.getSuperStore().getCategories().get(i).getName());
        }
        categories.getItems().addAll(listCat);

        categories.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    listSubCat.removeAll(listSubCat);
                    subCategories.getItems().clear();
                    categoryChosen = admin.searchCategory(categories.getValue());
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Insert Process");
                    alert.setHeaderText("Database Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                for (int i = 0; i< categoryChosen.getSubCategories().size(); i++) {
                    listSubCat.add(categoryChosen.getSubCategories().get(i).getName());
                }
                subCategories.getItems().addAll(listSubCat);
            }
        });
    }

    @FXML
    protected void HandleContinue(ActionEvent event) throws IOException {
        try {
            categoryChosen=admin.searchCategory(categories.getValue());
            subCategoryChosen = categoryChosen.searchSubCat(subCategories.getValue());
            if(newCatName.getText().length()<0) {
                throw new IOException("Incorrect Data");
            }
            name = newCatName.getText();
            Main.pop();
        }
        catch (CategoryException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert Process");
            alert.setHeaderText("Database Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        catch (SubCategoryException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert Process");
            alert.setHeaderText("Database Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

}
