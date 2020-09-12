package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class InsertCat extends GUIRun {

    @FXML
    private TextField CategoryName;

    public static String categoryName;

    @FXML
    protected void HandleContinue(ActionEvent event) throws IOException {
        categoryName = CategoryName.getText();
        Main.pop();
    }


}
