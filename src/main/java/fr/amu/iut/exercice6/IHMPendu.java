package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class IHMPendu extends Application {

    private String mot;

    private int viesCpt;

    private Label viesLabel;

    private Label motLabel;

    private Label pendu;

    private GridPane clavier;

    private VBox root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setWidth(500);
        primaryStage.setHeight(550);

        this.viesCpt = 7;

        Dico dico = new Dico();
        this.mot = dico.getMot();
        System.out.println(this.mot);

        this.root = new VBox();
        this.root.setStyle("-fx-background-color:#dafdf7");
        this.root.setAlignment(Pos.CENTER);

        pendu = new Label();
        ImageView imageView = new ImageView("exercice6/pendu" + this.viesCpt +".png");
        pendu.setGraphic(imageView);
        this.root.getChildren().add(pendu);

        this.viesLabel = new Label("Nombre de vies : " + this.viesCpt);
        viesLabel.setStyle("-fx-font-weight:bold");
        this.root.getChildren().add(viesLabel);

        this.motLabel = new Label("*".repeat(this.mot.length()));
        this.motLabel.setStyle("-fx-font-weight:bold;-fx-font-size:40px");
        this.root.getChildren().add(this.motLabel);

        this.clavier = new GridPane();
        this.clavier.setAlignment(Pos.CENTER);
        char[] voyelles = {'a','e','i','o','u','y'};
        char[] consonnes1 = {'b','c','d','f','g','h','j','k','l','m'};
        char[] consonnes2 = {'n','p','q','r','s','t','v','w','x','z'};
        int row = 0;
        int offset = 2;
        for (char[] jeu : new char[][]{voyelles, consonnes1, consonnes2}) {
            for (int i = 0; i < jeu.length; i++) {
                Button bouton = new Button(String.valueOf(jeu[i]));
                bouton.setStyle("-fx-border-radius:10px;-fx-border-color:#f3bda1;-fx-background-color:transparent;-fx-text-fill:#62cab3;-fx-font-weight:bold;");
                bouton.setMinWidth(45.0d);
                bouton.setMinHeight(45.0d);
                int finalI = i;
                bouton.setOnAction(actionEvent -> checkIfChar(jeu[finalI], bouton));
                GridPane.setColumnIndex(bouton, i+offset);
                GridPane.setRowIndex(bouton, row);
                clavier.getChildren().add(bouton);
            }
            row++;
            offset = 0;
        }
        this.root.getChildren().add(this.clavier);

        this.root.getChildren().forEach(child -> VBox.setMargin(child, new Insets(5.0d)));

        Scene scene = new Scene(this.root);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void checkIfChar(char lettre, Button node){
        Dico dico = new Dico();
        ArrayList<Integer> positions = dico.getPositions(lettre,this.mot);
        if (positions.size() >= 1){
            discoverLetter(positions, lettre);
        } else {
            viesCpt--;
            this.viesLabel.setText("Nombre de vies : " + this.viesCpt);
            ImageView imageView = new ImageView("exercice6/pendu" + viesCpt +".png");
            this.pendu.setGraphic(imageView);
            if (viesCpt == 0) endGame();
        }
        node.setDisable(true);
    }

    public void discoverLetter(ArrayList<Integer> positions, char lettre) {
        String current = this.motLabel.getText();
        for (Integer index : positions) {
            current = current.substring(0,index) + lettre + current.substring(index + 1);
        }
        this.motLabel.setText(current);
        if (this.mot.equalsIgnoreCase(this.motLabel.getText())) {
            ImageView imageView = new ImageView("exercice6/penduWin.png");
            this.pendu.setGraphic(imageView);
            endGame();
        }
    }

    public void endGame() {
        this.clavier.getChildren().forEach(child -> child.setDisable(true));
        Button restart = new Button("Rejouer");
        restart.setStyle("-fx-border-radius:50%;-fx-background-color:transparent;-fx-border-color:#62cab3;-fx-text-fill:#f3bda1;-fx-font-weight:bold;");
        restart.setOnAction(actionEvent -> restartGame(restart));
        this.root.getChildren().add(restart);
    }

    public void restartGame(Button restartButton) {
        this.mot = (new Dico()).getMot();
        System.out.println(this.mot);
        this.motLabel.setText("*".repeat(this.mot.length()));
        this.viesCpt = 7;
        this.viesLabel.setText("Nombre de vies : " + this.viesCpt);
        ImageView imageView = new ImageView("exercice6/pendu" + this.viesCpt +".png");
        this.pendu.setGraphic(imageView);
        this.root.getChildren().remove(restartButton);
        this.clavier.getChildren().forEach(child -> child.setDisable(false));
    }
}

