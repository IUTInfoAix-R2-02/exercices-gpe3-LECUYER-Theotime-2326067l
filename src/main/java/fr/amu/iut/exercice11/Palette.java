package fr.amu.iut.exercice11;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private IntegerProperty nbFois = new SimpleIntegerProperty();

    private StringProperty message = new SimpleStringProperty();

    private StringProperty couleurPanneau = new SimpleStringProperty("#000000");

    private Label texteDuHaut;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Pane panneau;
    private HBox boutons;

    private Label texteDuBas;

    private void createBindings() {
//        texteDuHaut.textProperty().bind(Bindings.concat(message, " choisi ", nbFois.asString(), " fois"));
        BooleanProperty pasEncoreDeClic = new SimpleBooleanProperty(true);
        pasEncoreDeClic.bind(Bindings.equal(nbFois, 0));
        panneau.styleProperty().bind(Bindings.concat("-fx-background-color:", couleurPanneau));
        texteDuHaut.textProperty().bind(Bindings.when(pasEncoreDeClic).then("").otherwise(Bindings.concat(message, " choisi ", nbFois.asString(), " fois")));
        texteDuBas.textProperty().bind(Bindings.when(pasEncoreDeClic).then("").otherwise(Bindings.concat("Le ", message, " est une jolie couleur !")));
    }

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        texteDuHaut = new Label();
        texteDuHaut.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);

        panneau = new Pane();
        panneau.setPrefSize(400, 200);

        VBox bas = new VBox();
        boutons = new HBox(10);
        boutons.setAlignment(Pos.CENTER);
        boutons.setPadding(new Insets(10, 5, 10, 5));
        texteDuBas = new Label();
        bas.setAlignment(Pos.CENTER_RIGHT);
        bas.getChildren().addAll(boutons, texteDuBas);

        createBindings();

        EventHandler<ActionEvent> clickButton = event -> {
            String title = ((Button) event.getSource()).getText();
            message.set(title);
            switch (title) {
                case "Vert" -> {
                    nbVert++;
                    nbFois.set(nbVert);
                    couleurPanneau.set("#31bca4");
                }
                case "Rouge" -> {
                    nbRouge++;
                    nbFois.set(nbRouge);
                    couleurPanneau.set("#dd1446");
                }
                case "Bleu" -> {
                    nbBleu++;
                    nbFois.set(nbBleu);
                    couleurPanneau.set("#458");
                }
            }
            texteDuBas.setTextFill(Color.web(couleurPanneau.get()));
        };

        vert = new Button("Vert");
        vert.setOnAction(clickButton);
        rouge = new Button("Rouge");
        rouge.setOnAction(clickButton);
        bleu = new Button("Bleu");
        bleu.setOnAction(clickButton);

        /* VOTRE CODE ICI */

        boutons.getChildren().addAll(vert, rouge, bleu);

        root.setCenter(panneau);
        root.setTop(texteDuHaut);
        root.setBottom(bas);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

