package gui;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteProduct extends GUIRun implements Initializable {

    private database.Admin admin;

    @FXML
    private ChoiceBox<String> categories;
    ObservableList<String> listCat = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> subCategories;
    ObservableList<String> listSubCat = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> products;
    ObservableList<String> listProducts = FXCollections.observableArrayList();

    public static database.Category categoryChosen;
    public static database.SubCategory subCategoryChosen;
    public static String productChosen;

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
        subCategories.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    listProducts.removeAll(listProducts);
                    products.getItems().clear();
                    subCategoryChosen = categoryChosen.searchSubCat(subCategories.getValue());
                }
                catch(SubCategoryException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Insert Process");
                    alert.setHeaderText("Database Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                for(int j=0;j<subCategoryChosen.getProducts().size();j++){
                    listProducts.add(subCategoryChosen.getProducts().get(j).getName());
                }
                products.getItems().addAll(listProducts);
            }
        });
    }

    @FXML
    protected void HandleContinue(ActionEvent event)  {

        try {
            categoryChosen=admin.searchCategory(categories.getValue());
            subCategoryChosen = categoryChosen.searchSubCat(subCategories.getValue());
            productChosen=products.getValue();
            Main.pop();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert Process");
            alert.setHeaderText("Database Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
