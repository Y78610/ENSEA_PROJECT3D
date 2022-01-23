package src;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.File;

public class Interface extends Application {

    double mousePosY;
    Scene theScene;
    World world;
    Earth earth;
    PerspectiveCamera camera;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        String csvPath = new File("").getAbsolutePath();
        csvPath += "\\data\\airport-codes_no_comma.csv";
        world = new World(csvPath);
        primaryStage.setTitle("Hello world");
        earth = new Earth();
        camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);

        theScene = new Scene(earth, 600,400,true);
        zoom();
        recoverCoordinates();
        theScene.setCamera(camera);
        primaryStage.setScene(theScene);

        primaryStage.show();
    }

    public void zoom()
    {
        theScene.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                System.out.println("Clicked on : (" + event.getSceneX() + ", "+ event.getSceneY() + ")");
                mousePosY=event.getSceneY();
            }
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                if(event.getSceneY() > mousePosY)
                {
                    mousePosY=event.getSceneY();
                    camera.getTransforms().add(new Translate(0,0,2));
                }
                else
                {
                    mousePosY=event.getSceneY();
                    camera.getTransforms().add(new Translate(0,0,-2));
                }
                //camera.getTransforms().add(new Rotate((event.getSceneY()-mousePosY)*0.02 ,new Point3D(0,1,0)));
            }
        });
    }

    public void recoverCoordinates()
    {
        theScene.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getButton() == MouseButton.SECONDARY && event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                PickResult pickResult = event.getPickResult();
                if (pickResult.getIntersectedNode() != null) {
                    Point2D cord = pickResult.getIntersectedTexCoord();
                    double lon = 360 * (cord.getX()-0.5);
                    double lat = 2 * Math.toDegrees(Math.atan(Math.exp((0.5-cord.getY())/0.2678))) - 90;
                    Aeroport ne = world.findNearestAirport(lon,lat);
                    System.out.println(ne);
                    earth.displayRedSphere(ne);
                }
            }
        });
    }

    static public void main(String args[])
    {
        launch(args);
    }
}