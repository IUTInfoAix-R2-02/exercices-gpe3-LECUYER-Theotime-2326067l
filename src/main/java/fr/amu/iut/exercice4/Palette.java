package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private Pane panneau;
    private HBox bas;

    EventHandler<MouseEvent> greenButtonClick = actionEvent -> {
        this.nbVert++;
        this.label.setText("Vert choisi " + this.nbVert + " fois");
        this.panneau.setStyle("-fx-background-color:#31bca4");
    };

    EventHandler<MouseEvent> redButtonClick = actionEvent -> {
        this.nbRouge++;
        this.label.setText("Rouge choisi " + this.nbRouge + " fois");
        this.panneau.setStyle("-fx-background-color:#dd1446");
    };

    EventHandler<MouseEvent> blueButtonClick = actionEvent -> {
        this.nbBleu++;
        this.label.setText("Bleu choisi " + this.nbBleu + " fois");
        this.panneau.setStyle("-fx-background-color:#458");
    };

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.label = new Label();
        this.label.setTextAlignment(TextAlignment.CENTER);

        this.panneau = new Pane();

        this.vert = new Button("Vert");
        this.vert.addEventHandler(MouseEvent.MOUSE_CLICKED, this.greenButtonClick);
        this.rouge = new Button("Rouge");
        this.rouge.addEventHandler(MouseEvent.MOUSE_CLICKED, this.redButtonClick);
        this.bleu = new Button("Bleu");
        this.bleu.addEventHandler(MouseEvent.MOUSE_CLICKED, this.blueButtonClick);

        this.bas = new HBox(this.vert,this.rouge,this.bleu);
        this.bas.setAlignment(Pos.CENTER);
        this.bas.getChildren().forEach(child -> HBox.setMargin(child, new Insets(5.0d)));

        this.root = new BorderPane();
        this.root.setTop(this.label);
        BorderPane.setAlignment(this.label, Pos.CENTER);
        this.root.setCenter(this.panneau);
        this.root.setBottom(this.bas);

        Scene scene = new Scene(this.root, 400,200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

