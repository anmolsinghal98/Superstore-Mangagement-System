package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUp extends GUIRun {

    @FXML
    public TextField name;
    @FXML
    public TextField mobile;
    @FXML
    public TextField username;
    @FXML
    public PasswordField pass;
    @FXML
    public TextField passHint;
    @FXML
    public PasswordField rePass;

    long mobileNumber;

    @FXML
    protected void signUp(ActionEvent event) throws IOException {
        try {
            mobileNumber = Long.parseLong(mobile.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign Up Process");
            alert.setHeaderText("Incorrect Mobile Number");
            alert.setContentText("Please enter mobile number correctly");
            alert.showAndWait();
            mobile.setText("");
        }
        if(!pass.getText().equals(rePass.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign Up Process");
            alert.setHeaderText("Passwords didn't match");
            alert.setContentText("Please enter password correctly");
            alert.showAndWait();
            pass.setText("");
            rePass.setText("");
        }
        else{
            database.EndUser newUser = new database.EndUser(name.getText().toString(), mobileNumber, username.getText().toString(), pass.getText().toString(),passHint.getText().toString());
            Main.getSuperStore().addEnduser(newUser);
            name.setText("");
            mobile.setText("");
            username.setText("");
            pass.setText("");
            rePass.setText("");
            passHint.setText("");
            Main.pop();
        }
    }

}
