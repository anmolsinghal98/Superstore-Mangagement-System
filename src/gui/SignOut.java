package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignOut extends GUIRun{

    @FXML
    protected void signOut(ActionEvent event) throws IOException {
        Main.superUser = null;
        Main.warehouseAdmin = null;
        Main.storeAdmin = null;
        Main.endUser = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chooseRole.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
    }
}
