package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignIn extends GUIRun {
    @FXML
    private TextField username;

    @FXML
    private TextField pass;

    @FXML
    protected void handleSignIn(ActionEvent event) throws IOException {

        Main.superUser = null;
        Main.warehouseAdmin = null;
        Main.storeAdmin = null;
        Main.endUser = null;

        switch (Main.userType) {
            case 1:
                this.load_SuperUser();
                break;
            case 2:
                this.load_WAdmin();
                break;
            case 3:
                this.load_SAdmin();
                break;
            case 4:
                this.load_GuestUser();
        }
    }

    @FXML
    protected void handleSignUp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signUp.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Main.push(stage);
    }

    @FXML
    protected void forgotPass(ActionEvent event) {

        if(username.getText().toString().length()==0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign In Process");
            alert.setHeaderText("Password Hint");
            alert.setContentText("Please enter a username first");
            alert.showAndWait();
        } else {
            database.User myUser;
            myUser = Main.getSuperStore().searchStoreAdmin(username.getText());
            if(myUser==null) {
                myUser = Main.getSuperStore().searchWarehouseAdmin(username.getText());
            }
            if(myUser==null) {
                myUser = Main.getSuperStore().searchEndUser(username.getText());
            }
            if(myUser==null) {
                myUser = Main.getSuperStore().getSuperUser();
            }

            if(myUser!=null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sign In Process");
                alert.setHeaderText("Password Hint");
                alert.setContentText("Password Hint: " + myUser.getCredentials().getPassHint());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Sign In Process");
                alert.setHeaderText("Password Hint");
                alert.setContentText("Please enter the credentials correctly");
                alert.showAndWait();
                username.setText("");
                pass.setText("");
            }

        }
    }

    protected void load_SuperUser() throws IOException {

        database.Credentials c = new database.Credentials(username.getText(), pass.getText());

        try {
            if (Main.getSuperStore().getSuperUser().getCredentials().equals(c)) {

                Main.superUser = Main.getSuperStore().getSuperUser();
                Main.user = Main.superUser;

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SuperUser.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                Main.push(stage);
            } else {
                throw new database.AuthException("Invalid Credentials Entered!");
            }
        } catch (database.AuthException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign In Process");
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Please enter the credentials correctly");
            alert.showAndWait();
            username.setText("");
            pass.setText("");
        }


    }

    protected void load_WAdmin() throws IOException {

        database.Credentials c = new database.Credentials(username.getText().toString(), pass.getText().toString());

        try {
            int flag = 0;
            for (int i = 0; i < Main.getSuperStore().getWarehouseAdmin_list().size(); i++) {
                if (Main.getSuperStore().getWarehouseAdmin_list().get(i).getCredentials().equals(c)) {

                    Main.warehouseAdmin = Main.getSuperStore().getWarehouseAdmin_list().get(i);
                    Main.user = Main.warehouseAdmin;

                    flag++;
                    break;
                }
            }
            if (flag != 0) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WarehouseAdminScreen.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                Main.push(stage);
            } else {
                throw new database.AuthException("Invalid Credentials Entered!");
            }
        } catch (database.AuthException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign In Process");
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Please enter the credentials correctly");
            alert.showAndWait();
            username.setText("");
            pass.setText("");
        }

    }

    protected void load_SAdmin() throws IOException {

        database.Credentials c = new database.Credentials(username.getText().toString(), pass.getText().toString());

        try {
            int flag = 0;
            for (int i = 0; i < Main.getSuperStore().getStoreAdmin_list().size(); i++) {
                if (Main.getSuperStore().getStoreAdmin_list().get(i).getCredentials().equals(c)) {

                    Main.storeAdmin = Main.getSuperStore().getStoreAdmin_list().get(i);
                    Main.user = Main.storeAdmin;

                    flag++;
                    break;
                }
            }
            if (flag != 0) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StoreAdminScreen.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                Main.push(stage);
            } else {
                throw new database.AuthException("Invalid Credentials Entered!");
            }
        } catch (database.AuthException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign In Process");
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Please enter the credentials correctly");
            alert.showAndWait();
            username.setText("");
            pass.setText("");
        }

    }

    protected void load_GuestUser() throws IOException {

        database.Credentials c = new database.Credentials(username.getText().toString(), pass.getText().toString());

        try {
            int flag = 0;
            for (int i = 0; i < Main.getSuperStore().getEnduser_list().size(); i++) {
                if (Main.getSuperStore().getEnduser_list().get(i).getCredentials().equals(c)) {

                    Main.endUser = Main.getSuperStore().getEnduser_list().get(i);
                    Main.user = Main.endUser;

                    flag++;
                    break;
                }
            }
            if (flag != 0) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("guestScreen.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                Main.push(stage);
            } else {
                throw new database.AuthException("Invalid Credentials Entered!");
            }
        } catch (database.AuthException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign In Process");
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Please enter the credentials correctly");
            alert.showAndWait();
            username.setText("");
            pass.setText("");
        }

    }

}