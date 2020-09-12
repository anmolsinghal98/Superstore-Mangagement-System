package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class GUIRun {

    @FXML
    public void backPressed(ActionEvent event) throws IOException {
        Main.pop();
    }
}
