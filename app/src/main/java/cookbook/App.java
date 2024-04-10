package cookbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create an instance of LoginScene
        LoginScene loginScene = new LoginScene();
    
        // Set the LoginScene as the scene for the primary stage
        primaryStage.setScene(loginScene);
    
        primaryStage.setTitle("Cookbook Application");
        primaryStage.show();
    
        // Initialize the mysql label
        Label mysql;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cookbook?user=bnw&password=7k73KDs4cAEN@&useSSL=false");
            mysql = new Label("Driver found and connected");
    
        } catch (SQLException e) {
            mysql = new Label("An error has occurred: " + e.getMessage());
        }
    
        // Create the root VBox and add the mysql label to it
        VBox root = new VBox();
        root.setPadding(new Insets(5));
        Label title = new Label("JavaFX");
        root.getChildren().addAll(title, mysql);
    
        // Set the root VBox as the content of the scene
        primaryStage.setScene(new Scene(root, 400, 200));
    }
}
