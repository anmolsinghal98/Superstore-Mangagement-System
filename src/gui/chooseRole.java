package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class chooseRole extends GUIRun {

    @FXML
    protected void SuperUser(ActionEvent event) throws IOException  {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signIn.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("SuperUser Signin");
        stage.setScene(new Scene(root1));
        Main.userType = 1;
        Main.push(stage);
    }

    @FXML
    protected void Customer(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signIn.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Customer Signin");
        stage.setScene(new Scene(root1));
        Main.userType = 4;
        Main.push(stage);
    }

    @FXML
    protected void WAdmin(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signIn.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Warehouse Admin Signin");
        stage.setScene(new Scene(root1));
        Main.userType = 2;
        Main.push(stage);
    }

    @FXML
    protected void SAdmin(ActionEvent event) throws IOException{
        System.out.println("hi");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signIn.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Store Admin Signin");
        stage.setScene(new Scene(root1));
        Main.userType = 3;
        Main.push(stage);
    }

    @FXML
    protected void contactUs(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ContactUs.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Contact Us");
        stage.setScene(new Scene(root1));
        Main.push(stage);
    }
}
