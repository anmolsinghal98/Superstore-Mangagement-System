package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InsertProduct extends GUIRun implements Initializable {

    @FXML
    private ChoiceBox<String> categories;
    ObservableList<String> listCat = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> subCategories;
    ObservableList<String> listSubCat = FXCollections.observableArrayList();

    @FXML
    private TextField name;

    @FXML
    private TextField quantity;

    @FXML
    private TextField costPerQuarter;

    @FXML
    private TextField carryCostPerQuarter;

    @FXML
    private TextField demand;

    private database.Admin admin;

    public static String nameVar;
    public static long quantityVar;
    public static float fixedCost;
    public static float carryCost;
    public static long demandAmt;
    public static database.Category categoryChosen;
    public static database.SubCategory subCategoryChosen;
    public static database.Product prod;


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
                    for (int i = 0; i< categoryChosen.getSubCategories().size(); i++) {
                        listSubCat.add(categoryChosen.getSubCategories().get(i).getName());
                    }
                    subCategories.getItems().addAll(listSubCat);
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

    @FXML
    protected void HandleContinue(ActionEvent event) throws IOException {

        try {
            categoryChosen=admin.searchCategory(categories.getValue());
            subCategoryChosen = categoryChosen.searchSubCat(subCategories.getValue());
            if(name.getText().length()<0 || quantity.getText().length()<0 || costPerQuarter.getText().length()<0 || carryCostPerQuarter.getText().length()<0 || demand.getText().length()<0) {
                throw new IOException("Incorrect Data");
            }
            nameVar = name.getText();
            quantityVar = Long.parseLong(quantity.getText());
            fixedCost = Float.parseFloat(costPerQuarter.getText());
            carryCost = Float.parseFloat(carryCostPerQuarter.getText());
            demandAmt = Long.parseLong(demand.getText());
            prod = new database.Product(nameVar, quantityVar, fixedCost, carryCost,demandAmt,subCategoryChosen);
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
