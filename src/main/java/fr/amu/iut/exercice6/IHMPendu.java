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

    private int vies;

    private Label motLabel;

    private Label pendu;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setWidth(500);
        primaryStage.setHeight(550);

        vies = 7;

        Dico dico = new Dico();
        this.mot = dico.getMot();
        System.out.println(this.mot);

        VBox root = new VBox();
        root.setStyle("-fx-background-color:#dafdf7");
        root.setAlignment(Pos.CENTER);

        pendu = new Label();
        ImageView imageView = new ImageView("exercice6/pendu" + vies +".png");
        pendu.setGraphic(imageView);
        root.getChildren().add(pendu);

        Label vies = new Label("Nombre de vies :");
        root.getChildren().add(vies);

        this.motLabel = new Label("*".repeat(mot.length()));
        root.getChildren().add(this.motLabel);

        GridPane clavier = new GridPane();
        clavier.setAlignment(Pos.CENTER);
        char[] voyelles = {'a','e','i','o','u','y'};
        char[] consonnes1 = {'b','c','d','f','g','h','j','k','l','m'};
        char[] consonnes2 = {'n','p','q','r','s','t','v','w','x','z'};
        int row = 0;
        int offset = 2;
        for (char[] jeu : new char[][]{voyelles, consonnes1, consonnes2}) {
            for (int i = 0; i < jeu.length; i++) {
                Button bouton = new Button(String.valueOf(jeu[i]));
                bouton.setStyle("-fx-border-color:#d9c5aa;-fx-background-color:transparent;-fx-pref-width:50px;-fx-pref-height:50px");
                bouton.setMinWidth(20.0d);
                bouton.setMinHeight(20.0d);
                int finalI = i;
                bouton.setOnAction(actionEvent -> checkIfChar(jeu[finalI], bouton));
                GridPane.setColumnIndex(bouton, i+offset);
                GridPane.setRowIndex(bouton, row);
                clavier.getChildren().add(bouton);
            }
            row++;
            offset = 0;
        }
        root.getChildren().add(clavier);

        root.getChildren().forEach(child -> VBox.setMargin(child, new Insets(5.0d)));

        Scene scene = new Scene(root);

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
            vies--;
            ImageView imageView = new ImageView("exercice6/pendu" + vies +".png");
            this.pendu.setGraphic(imageView);
            if (vies == 0) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.exit();
                System.exit(0);
            }
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
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Platform.exit();
            System.exit(0);
        }
    }
}
