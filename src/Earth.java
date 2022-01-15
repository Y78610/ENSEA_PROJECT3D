package src;

import javafx.scene.Group;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class Earth extends Group {
    private Rotate ry;
    private Sphere sph;
    private ArrayList<Sphere> yellowSphere;

    Earth()
    {
        sph = new Sphere(300);
        this.getChildren().add(sph);
    }
}
