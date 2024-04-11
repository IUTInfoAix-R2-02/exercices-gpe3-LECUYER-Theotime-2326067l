package fr.amu.iut.exercice2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) {

        Random random = new Random();

        GridPane gridPane = new GridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int nombre = random.nextInt(3);
                ImageView image = switch (nombre) {
                    case 0 -> new ImageView("exercice2/Vide.png");
                    case 1 -> new ImageView("exercice2/Rond.png");
                    default -> new ImageView("exercice2/Croix.png");
                };
                Label label = new Label();
                label.setGraphic(image);
                GridPane.setRowIndex(label,i);
                GridPane.setColumnIndex(label,j);
                gridPane.getChildren().add(label);
            }
        }

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

