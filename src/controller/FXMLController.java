/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.User;

/**
 * FXML Controller class
 *
 * @author luole
 */
public class FXMLController implements Initializable {

    @FXML
    TextField nameText;

    @FXML
    TextField ageText;

    @FXML
    TextField emailText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void submit() {
        try {
            String name;
            int age;
            String email;

            // Get and validate name
            name = this.nameText.getText().trim();
            age = Integer.parseInt(this.ageText.getText());
            email = this.emailText.getText();

            // validate name
            if (name.isEmpty()) {
                showErrorAlert("Name cannot be empty");
                return;
            }
            if (name.length() < 2) {
                showErrorAlert("Name must be at least 2 characters long");
                return;
            }
            if (!name.matches("[a-zA-Z\\s]+")) {
                showErrorAlert("Name can only contain letters and spaces");
                return;
            }

            //validate age
            String ageText = this.ageText.getText().trim();
            if (ageText.isEmpty()) {
                showErrorAlert("Age cannot be empty");
                return;
            }

            //Validate email
            if (email.isEmpty()) {
                showErrorAlert("Email cannot be empty");
                return;
            }
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                showErrorAlert("Please enter a valid email address");
                return;
            }

            if (age < 1 || age > 120) {
                showErrorAlert("Age must be between 1 and 120");
            }

            // If all validations pass, create User object
            User user = new User(name, age, email);

            // Show success message using toString()
            showSuccessAlert("User created successfully!\n\n" + user.toString());

        } catch (NumberFormatException e) {
            showErrorAlert("Invalid age provided, please provide valid digits only");
        }
    }

    public void showErrorAlert(String message) {
        showAlert(message, Alert.AlertType.ERROR, "Oops!");
    }

    public void showSuccessAlert(String message) {
        showAlert(message, Alert.AlertType.INFORMATION, "Success!");
    }

    public void showAlert(String message, Alert.AlertType alertType, String headerText) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.setHeaderText(headerText);
        alert.showAndWait();

    }

}
