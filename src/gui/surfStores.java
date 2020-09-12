package gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class surfStores extends GUIRun implements Initializable {

    public class listObject{
        public SimpleStringProperty placeName=new SimpleStringProperty();
        public SimpleStringProperty Location=new SimpleStringProperty();
        public SimpleStringProperty placeId=new SimpleStringProperty();
        public String getId(){
            return placeId.get();
        }
        public String getName(){
            return placeName.get();
        }
        public String getLocation(){
            return Location.get();
        }
    }

    @FXML
    public TableView<listObject> table;

    @FXML
    public TableColumn id;

    @FXML
    public TableColumn name;

    @FXML
    public TableColumn loc;

    ObservableList<listObject> data;

    public static database.Store storeChosen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        name.setCellValueFactory(new PropertyValueFactory<listObject,String>("Name"));
        loc.setCellValueFactory(new PropertyValueFactory<listObject,String>("Location"));
        id.setCellValueFactory(new PropertyValueFactory<listObject,String>("Id"));

        data = FXCollections.observableArrayList();
        table.setItems(data);

        for(int i=0;i<Main.getSuperStore().getStore_list().size();i++){
            listObject l=new listObject();
            l.placeName.setValue(Main.getSuperStore().getStore_list().get(i).getDetails().getName());
            String s=""+Main.getSuperStore().getStore_list().get(i).getDetails().getId();
            l.placeId.setValue(s);
            l.Location.setValue(Main.getSuperStore().getStore_list().get(i).getDetails().getLoc());
            data.add(l);
        }
    }

    @FXML
    public void HandleContinue(ActionEvent event) throws IOException {
        listObject d=table.getSelectionModel().getSelectedItem();
        String name=d.placeName.getValue();
        Long id=Long.parseLong(d.placeId.getValue());
        try {
            storeChosen = Main.getSuperStore().searchStore(name);
            if(Main.endUser!=null && storeChosen!=null) {
                Main.endUser.setLinkedStore(storeChosen);
                Main.pop();
            } else if(Main.superUser!=null && storeChosen!=null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccessInventory.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                Main.push(stage);
            }
        } catch (database.DatabaseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Process Halted");
            alert.setHeaderText("Database Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }
}
