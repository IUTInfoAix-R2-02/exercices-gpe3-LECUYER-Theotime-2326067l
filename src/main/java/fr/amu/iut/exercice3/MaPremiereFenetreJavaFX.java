package fr.amu.iut.exercice3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MaPremiereFenetreJavaFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Création d'un conteneur VBox avec ses éléments centrés
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        // Création et ajout du label au conteneur
        Label helloLabel = new Label("Bonjour à tous !");
        vbox.getChildren().add(helloLabel);

        // Ajout d'un champ de saisi de texte de taille 180 pixels
        TextField nameField = new TextField("Veuillez saisir un nom");
        nameField.setMaxWidth(180.0d);
        nameField.setFont( Font.font("Courier", FontWeight.NORMAL, 12) );

        nameField.setOnAction( actionEvent -> new ButtonClickHandler(helloLabel, nameField).handle(actionEvent));

        vbox.getChildren().add( nameField );

        // Ajout d'un bouton avec du texte
        Button button = new Button();
        vbox.getChildren().add( button );

        // Création d'un composant avec l'image peinte à l'intérieur
        ImageView iv = new ImageView("exercice3/Bonjour.jpg");

        // Intégration de l'image dans le bouton
        button.setGraphic(iv);

        // Changement du texte après un clic sur le bouton
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonClickHandler(helloLabel, nameField));

        // Création de la scene
        Scene scene = new Scene(vbox);

        // Ajout de la scene à la fenêtre
        primaryStage.setScene(scene);

        primaryStage.setTitle("Hello application");
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);
        primaryStage.show();
    }
}
