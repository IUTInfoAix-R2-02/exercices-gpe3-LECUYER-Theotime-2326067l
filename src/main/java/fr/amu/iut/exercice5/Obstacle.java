package fr.amu.iut.exercice5;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Obstacle extends Rectangle {

    public Obstacle() {
        super();
    }

    public Obstacle(double width, double height) {
        super(width,height);
    }

    public Obstacle(double x, double y, double width, double height) {
        super(x,y,width,height);
    }

    public Obstacle(double width, double height, Paint fill) {
        super(width,height,fill);
    }

}
