package gui;

import database.CategoryException;
import database.ProductException;
import database.SubCategoryException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class StoreAdminScreen extends GUIRun {

    // TODO received orders and contact warehouse

    @FXML
    protected void insertCategory(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InsertCat.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                String s = InsertCat.categoryName;
                if(s.length()>0){
                    database.Category c = new database.Category(s);
                    try {
                        Main.storeAdmin.addCategory(c);
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Insert Process");
                        alert.setHeaderText("Database Error");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                }
            }
        });

    }

    @FXML
    protected void insertSubCategory(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InsertSubCat.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                String s = InsertSubCat.name;
                if(s.length()>0 && InsertSubCat.category!=null) {
                    database.SubCategory c = new database.SubCategory(s, InsertSubCat.category);
                    try {
                        Main.storeAdmin.addSubCategory(c);
                    }
                    catch (SubCategoryException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Insert Process");
                        alert.setHeaderText("Database Error");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                }

            }
        });
    }

    @FXML
    protected void insertProduct(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InsertProduct.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    if(InsertProduct.prod!=null && InsertProduct.categoryChosen!=null && InsertProduct.subCategoryChosen!=null){
                        Main.storeAdmin.addProduct(InsertProduct.prod,InsertProduct.categoryChosen,InsertProduct.subCategoryChosen);
                    }
                    }
                 catch (SubCategoryException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Insert Process");
                    alert.setHeaderText("Database Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                catch(CategoryException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Insert Process");
                    alert.setHeaderText("Database Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                catch(ProductException e){
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
    protected void modifyCategory(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifyCat.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                String s = ModifyCat.name;
                if(s.length()>0 && ModifyCat.category!=null){
                    try {
                        Main.storeAdmin.modifyCategory(ModifyCat.category,s);
                    } catch (CategoryException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Insert Process");
                        alert.setHeaderText("Database Error");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                }
            }
        });
    }

    @FXML
    protected void modifySubCategory(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifySubCat.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                String s = ModifySubCat.name;
                if(s.length()>0 && ModifySubCat.categoryChosen!=null && ModifySubCat.subCategoryChosen!=null){
                    try {
                        Main.storeAdmin.modifySubCategory(ModifySubCat.categoryChosen,ModifySubCat.subCategoryChosen,s);
                    } catch (SubCategoryException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Insert Process");
                        alert.setHeaderText("Database Error");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                    catch(CategoryException e){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Insert Process");
                        alert.setHeaderText("Database Error");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                }

            }
        });
    }

    @FXML
    protected void modifyProduct(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    if(ModifyProduct.prod!=null && ModifyProduct.categoryChosen!=null && ModifyProduct.subCategoryChosen!=null){
                        Main.storeAdmin.modifyProduct(ModifyProduct.categoryChosen.getName(),ModifyProduct.subCategoryChosen.getName(),ModifyProduct.productChosen,ModifyProduct.prod);
                    }
                } catch (SubCategoryException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Insert Process");
                    alert.setHeaderText("Database Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                catch(CategoryException e){
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
    protected void deleteCategory(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteCat.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                String s = DeleteCat.categoryName;
                if(s.length()>0){
                    try {
                        Main.storeAdmin.deleteCategory(s);
                    } catch (CategoryException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Insert Process");
                        alert.setHeaderText("Database Error");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                }

            }
        });

    }

    @FXML
    protected void deleteSubCategory(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteSubCat.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    if(DeleteSubCat.categoryChosen!=null && DeleteSubCat.name.length()>0){
                        Main.storeAdmin.deleteSubCategory(DeleteSubCat.categoryChosen.getName(),DeleteSubCat.name);
                    }
                }
                catch (CategoryException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Delete Process");
                    alert.setHeaderText("Database Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                catch(SubCategoryException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Delete Process");
                    alert.setHeaderText("Database Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }

            }
        });
    }

    @FXML
    protected void deleteProduct(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteProduct.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    if(DeleteProduct.categoryChosen!=null && DeleteProduct.subCategoryChosen!=null && DeleteProduct.productChosen!=null){
                        Main.storeAdmin.deleteProduct(DeleteProduct.categoryChosen.getName(),DeleteProduct.subCategoryChosen.getName(),DeleteProduct.productChosen);
                    }
                } catch (SubCategoryException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Insert Process");
                    alert.setHeaderText("Database Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                catch(CategoryException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Insert Process");
                    alert.setHeaderText("Database Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                catch(ProductException e){
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
    protected void receivedOrders(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReceivedOrders.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
    }

    @FXML
    protected void accessInventory(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccessInventory.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
        
    }

    @FXML
    protected void signOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignOut.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
        
    }
}
