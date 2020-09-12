package gui;

import database.SuperStore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class Main extends Application {

    public static Stack<Stage> screenStack;

    private static database.SuperStore superStore;

    public static int userType;

    public static database.SuperUser superUser;
    public static database.WarehouseAdmin warehouseAdmin;
    public static database.StoreAdmin storeAdmin;
    public static database.EndUser endUser;
    public static database.User user;

    public static SuperStore getSuperStore() {
        if(superStore == null) {
            superStore = new database.SuperStore("admin", 9876540);
        }
        return superStore;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        screenStack = new Stack<>();
        Parent root = FXMLLoader.load(getClass().getResource("chooseRole.fxml"));
        primaryStage.setTitle("Le Marché");
        primaryStage.setScene(new Scene(root));
        screenStack.push(primaryStage);
        primaryStage.show();
    }

    public static void pop() {
        if(screenStack.size()>0) {
            Stage check = screenStack.pop();
            check.hide();
            check = screenStack.peek();
            check.show();
        }
    }

    public static void push(Stage s) {
        Stage check = screenStack.peek();
        check.hide();
        screenStack.push(s);
        s.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
