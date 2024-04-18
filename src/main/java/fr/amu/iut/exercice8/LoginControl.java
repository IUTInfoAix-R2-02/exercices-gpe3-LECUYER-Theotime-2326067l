package fr.amu.iut.exercice8;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {

    @FXML
    private TextField uid;

    @FXML
    private PasswordField pwd;

    @FXML
    private void okClicked() {
        System.out.println(uid.getText());
        System.out.println("*".repeat(pwd.getText().length()));
    }

    @FXML
    private void cancelClicked() {
        uid.setText("");
        pwd.setText("");
    }
}