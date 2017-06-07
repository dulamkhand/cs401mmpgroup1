/**
 * Maharishi University Of Management, MSCS, April 2017 Entry
 * MPP Course, Professor Shafqat, Group 1:
 * Bek
 * Khandaa
 * Rafael
 *
 * Vendor Management System
 * June of 2017
 */
package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Vendor Management System.
 *
 * @author Group 1.
 */
public class Main extends Application {

    // static member will be used in RootLayoutController
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        Main.primaryStage = stage;
        Main.primaryStage.setTitle("Vendor Management System");

        // load login page
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/Login.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
