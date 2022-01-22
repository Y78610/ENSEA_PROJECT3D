package src;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.File;

public class Interface extends Application {

    double mousePosX;
    double mousePosY;
    Scene theScene;
    World world;
    PerspectiveCamera camera;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        String csvPath = new File("").getAbsolutePath();
        csvPath += "\\data\\airport-codes_no_comma.csv";
        System.out.println(csvPath);
        world = new World(csvPath);
        primaryStage.setTitle("Hello world");
        Earth earth = new Earth();
        //Pane pane = new Pane(earth);
        camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);

        theScene = new Scene(earth, 600,400,true);
        zoom();
        recupererCordonnees();
        theScene.setCamera(camera);
        primaryStage.setScene(theScene);

        primaryStage.show();
    }

    public void zoom()
    {
        theScene.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                System.out.println("Clicked on : (" + event.getSceneX() + ", "+ event.getSceneY() + ")");
                mousePosX=event.getSceneX();
                mousePosY=event.getSceneY();
            }
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                if(event.getSceneY() > mousePosY)
                {
                    mousePosX=event.getSceneX();
                    mousePosY=event.getSceneY();
                    camera.getTransforms().add(new Translate(0,0,2));
                }
                else
                {
                    mousePosX=event.getSceneX();
                    mousePosY=event.getSceneY();
                    camera.getTransforms().add(new Translate(0,0,-2));
                }
                //camera.getTransforms().add(new Rotate((event.getSceneY()-mousePosY)*0.02 ,new Point3D(0,1,0)));
            }
        });
    }

    public void recupererCordonnees()
    {
        theScene.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getButton() == MouseButton.SECONDARY && event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                PickResult pickResult = event.getPickResult();
                if (pickResult.getIntersectedNode() != null) {
                    // Code `a compl´eter : on r´ecup`ere le point d'intersection
                    Point2D cord = pickResult.getIntersectedTexCoord();
                    // Conversion en longitude et lattitude
                    double lon = 360 * (cord.getX()-0.5);
                    double lat = 2 * Math.toDegrees(Math.atan(Math.exp((0.5-cord.getY())/0.2678))) - 90; // 90 degree en radian = 1.5708
                    System.out.println("Clicked on right button : ( long : " + lon + ", lat : "+ lat +")");
                    // Recherche dans l'objet w de la classe World de l'a´eroport le plus proche.
                    Aeroport ne = world.findNearestAirport(lon,lat);
                    // Affichage dans la console
                    System.out.println(ne);
                }
            }
        });
    }

    static public void main(String args[])
    {
        launch(args);
    }
}