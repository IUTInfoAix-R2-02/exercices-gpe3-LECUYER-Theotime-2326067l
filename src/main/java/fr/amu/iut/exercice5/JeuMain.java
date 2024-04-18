package fr.amu.iut.exercice5;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class JeuMain extends Application {

    private static ArrayList<Obstacle> obstacles;

    private Scene scene;
    private BorderPane root;

    private int mouvementsPacman;

    @Override
    public void start(Stage primaryStage) {

        mouvementsPacman = 10000000;

        root = new BorderPane();

        obstacles = new ArrayList<>();
        Obstacle obs1 = new Obstacle(20,20,20,20);
        obstacles.add(obs1);

        //Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();
        // on positionne le fantôme 20 positions vers la droite
        fantome.setLayoutX(20 * 10);
        //panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);
        jeu.getChildren().add(pacman);
        jeu.getChildren().add(fantome);
        jeu.getChildren().add(obs1);
        root.setCenter(jeu);
        //on construit une scene 640 * 480 pixels
        scene = new Scene(root);

        //Gestion du déplacement du personnage
        deplacer(pacman, fantome);

        primaryStage.setTitle("... Pac Man ...");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Permet de gérer les événements de type clavier, pression des touches
     * pour le j1(up,down, right, left), pour le j2( z,q,s,d)
     *
     * @param j1
     * @param j2
     */
    private void deplacer(Personnage j1, Personnage j2) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case LEFT:
                    mouvementsPacman--;
                    j1.deplacerAGauche();
                    if (j1.estEnCollision(obstacles)) j1.deplacerADroite(scene.getWidth());
                    break;
                case RIGHT:
                    mouvementsPacman--;
                    j1.deplacerADroite(scene.getWidth());
                    if (j1.estEnCollision(obstacles)) j1.deplacerAGauche();
                    break;
                case UP:
                    mouvementsPacman--;
                    j1.deplacerEnHaut();
                    if (j1.estEnCollision(obstacles)) j1.deplacerEnBas(scene.getHeight());
                    break;
                case DOWN:
                    mouvementsPacman--;
                    j1.deplacerEnBas(scene.getHeight());
                    if (j1.estEnCollision(obstacles)) j1.deplacerEnHaut();
                    break;
                case Z:
                    j2.deplacerEnHaut();
                    break;
                case Q:
                    j2.deplacerAGauche();
                    break;
                case S:
                    j2.deplacerEnBas(scene.getHeight());
                    break;
                case D:
                    j2.deplacerADroite(scene.getWidth());

            }
            if (j1.estEnCollision(j2)) {
                System.out.println("Collision....");
                Platform.exit();
                System.exit(0);
            }
            if (mouvementsPacman <= 1){
                Platform.exit();
                System.exit(0);
            }
        });
    }


}
