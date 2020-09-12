package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccessInventory extends GUIRun implements Initializable {

    database.Admin admin;

    @FXML
    private ListView<String> categories;
    ObservableList<String> listCat = FXCollections.observableArrayList();

    @FXML
    private ListView<String> subCategories;
    ObservableList<String> listSubCat = FXCollections.observableArrayList();

    @FXML
    private ListView<String> products;
    ObservableList<String> listProducts = FXCollections.observableArrayList();

    @FXML
    private TextField search;

    public static database.Category categoryChosen;
    public static database.SubCategory subCategoryChosen;
    public static String productChosen;


    private void loadAdmin() {
        if (Main.storeAdmin != null) {
            admin = Main.storeAdmin;
        } else if (Main.warehouseAdmin != null) {
            admin = Main.warehouseAdmin;
        } else {
            admin = null;
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search.setVisible(false);
        listCat.removeAll(listCat);
        listSubCat.removeAll(listSubCat);
        if (admin == null) {
            loadAdmin();
        }
        for (int i = 0; i < Main.getSuperStore().getCategories().size(); i++) {
            listCat.add(Main.getSuperStore().getCategories().get(i).getName());
        }
        categories.getItems().addAll(listCat);
        categories.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    listSubCat.removeAll(listSubCat);
                    listProducts.removeAll(listProducts);
                    subCategories.getItems().clear();
                    products.getItems().clear();
                    categoryChosen = Main.getSuperStore().searchCategory(categories.getSelectionModel().getSelectedItem());

                    if (categoryChosen != null) {
                        for (int i = 0; i < categoryChosen.getSubCategories().size(); i++) {
                            listSubCat.add(categoryChosen.getSubCategories().get(i).getName());
                        }
                        subCategories.getItems().addAll(listSubCat);
                    }
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Process Halted");
                    alert.setHeaderText("Database Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
        });
        subCategories.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    listProducts.removeAll(listProducts);
                    products.getItems().clear();
                    subCategoryChosen = categoryChosen.searchSubCat(subCategories.getSelectionModel().getSelectedItem());
                    search.setVisible(true);
                    if (subCategoryChosen != null) {

                        for (int j = 0; j < subCategoryChosen.getProducts().size(); j++) {
                            boolean flag;
                            try {
                                if (Main.endUser != null) {
                                    Main.endUser.getLinkedStore().searchProduct(subCategoryChosen.getProducts().get(j).getName());
                                } else if (Main.warehouseAdmin != null) {
                                    if (surfWarehouse.warehouseChosen != null) {
                                        surfWarehouse.warehouseChosen.searchProduct(subCategoryChosen.getProducts().get(j).getName());
                                    } else {
                                        Main.warehouseAdmin.getMy_warehouse().searchProduct(subCategoryChosen.getProducts().get(j).getName());
                                    }
                                } else if (Main.storeAdmin != null) {
                                    Main.storeAdmin.getMy_store().searchProduct(subCategoryChosen.getProducts().get(j).getName());
                                } else if (Main.superUser != null) {
                                    if (surfWarehouse.warehouseChosen != null) {
                                        surfWarehouse.warehouseChosen.searchProduct(subCategoryChosen.getProducts().get(j).getName());
                                    } else if (surfStores.storeChosen != null) {
                                        surfStores.storeChosen.searchProduct(subCategoryChosen.getProducts().get(j).getName());
                                    }
                                }
                                flag = true;
                            } catch (database.ProductException e) {
                                flag = false;
                            }
                            if (flag) {
                                listProducts.add(subCategoryChosen.getProducts().get(j).getName());
                            }

                        }
                        products.getItems().addAll(listProducts);
                        surfWarehouse.warehouseChosen = null;
                        surfStores.storeChosen = null;
                    }
//                    search.setOnInputMethodTextChanged(new EventHandler<InputMethodEvent>() {
//                        @Override
//                        public void handle(InputMethodEvent event) {
//                        }
//                    });

                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Process Halted");
                    alert.setHeaderText("Database Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
        });
    }


    @FXML
    protected void KMPSearch(ActionEvent event) throws IOException {
        products.getItems().clear();
        for (int j = 0; j < subCategoryChosen.getProducts().size(); j++) {
            if(database.KMPSearchAlgo.KMPSearch(search.getText().toString(), subCategoryChosen.getProducts().get(j).getName().toString())) {
                boolean flag;
                try {
                    if (Main.endUser != null) {
                        Main.endUser.getLinkedStore().searchProduct(subCategoryChosen.getProducts().get(j).getName());
                    } else if (Main.warehouseAdmin != null) {
                        if (surfWarehouse.warehouseChosen != null) {
                            surfWarehouse.warehouseChosen.searchProduct(subCategoryChosen.getProducts().get(j).getName());
                        } else {
                            Main.warehouseAdmin.getMy_warehouse().searchProduct(subCategoryChosen.getProducts().get(j).getName());
                        }
                    } else if (Main.storeAdmin != null) {
                        Main.storeAdmin.getMy_store().searchProduct(subCategoryChosen.getProducts().get(j).getName());
                    } else if (Main.superUser != null) {
                        if (surfWarehouse.warehouseChosen != null) {
                            surfWarehouse.warehouseChosen.searchProduct(subCategoryChosen.getProducts().get(j).getName());
                        } else if (surfStores.storeChosen != null) {
                            surfStores.storeChosen.searchProduct(subCategoryChosen.getProducts().get(j).getName());
                        }
                    }
                    flag = true;
                } catch (database.ProductException e) {
                    flag = false;
                }
                if (flag) {
                    listProducts.add(subCategoryChosen.getProducts().get(j).getName());
                }
            }
        }

        }

    @FXML
    protected void HandleContinue(ActionEvent event) throws IOException {
        productChosen = products.getSelectionModel().getSelectedItem();
        if (Main.endUser != null) {
            ProductDisplay.productName = productChosen;
            database.Product product = null;
            try {
                product = subCategoryChosen.searchProduct(productChosen);
                ProductDisplay.priceName = product.getfPrice() + "";
                ProductDisplay.quantityAvailName = product.getQuantity() + "";
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Process Halted");
                alert.setHeaderText("Database Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductDisplay.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            Main.push(stage);
        }
//        Main.pop();
    }


}
