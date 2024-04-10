package cookbook;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;


public class LoginScene extends Scene {

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Label errorLabel;

    public LoginScene() {
        super(new VBox(), 400, 200);

        VBox root = (VBox) getRoot();
        root.setPadding(new Insets(10));

        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");
        errorLabel = new Label();

        loginButton.setOnAction(e -> handleLogin());

        root.getChildren().addAll(new Label("Username:"), usernameField,
                                   new Label("Password:"), passwordField,
                                   loginButton, errorLabel);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Call authentication logic method
        boolean isAuthenticated = DatabaseHandler.authenticateUser(username, password);

        if (isAuthenticated) {
            // Navigate to main application screen
            // For simplicity, assume a method navigateToMainScreen() exists in App class
            // App.navigateToMainScreen();
            errorLabel.setText("Login successful"); // Display success message
        } else {
            errorLabel.setText("Invalid username or password");
        }
    }
}
