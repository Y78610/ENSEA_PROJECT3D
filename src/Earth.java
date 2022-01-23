package src;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.lang.Math;
import java.io.File;
import java.util.ArrayList;

public class Earth extends Group {
    final private Sphere earth;
    private ArrayList<Sphere> yellowSphere;
    final private short rayonDeTerre = 300;
    Earth()
    {
        String imgPath = new File("").getAbsolutePath();
        imgPath += "\\data\\earth_lights_4800.png";
        earth = new Sphere(300);
        PhongMaterial pm = new PhongMaterial();
        Image img = new Image(imgPath);
        pm.setDiffuseMap(img);
        earth.setMaterial(pm);
        earth.setRotationAxis(Rotate.Y_AXIS);
        this.getChildren().add(earth);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                earth.setRotate(time/50000000);
            }
        };
        animationTimer.start();
    }

    public Sphere createSphere(Aeroport a, Color color)
    {
        double latitude = a.getLongitude();
        double longitude = a.getLatitude();
        double cos = Math.cos(Math.toRadians(latitude - 13));
        double x = rayonDeTerre*(cos *Math.sin(Math.toRadians(longitude)));
        double y = -rayonDeTerre*(Math.sin(Math.toRadians(latitude-13)));
        double z = -rayonDeTerre*(cos *Math.cos(Math.toRadians(longitude)));
        Sphere sphere = new Sphere(10);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        sphere.setMaterial(material);
        sphere.rotationAxisProperty().bind(earth.rotationAxisProperty());
        sphere.getTransforms().add(new Translate(x,y,z));
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                sphere.setRotate(time/50000000);
            }
        };
        animationTimer.start();
        return sphere;
    }

    public void displayRedSphere(Aeroport a)
    {
        Sphere sp = createSphere(a, Color.RED);
        this.getChildren().add(sp);
    }

    public Sphere getEarth()
    {
        return earth;
    }
}
