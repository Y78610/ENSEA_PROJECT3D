package src;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Interface extends Application {

    double mousePosX;
    double mousePosY;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Hello world");
        Earth earth = new Earth();
        Pane pane = new Pane(earth);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);

        Scene theScene = new Scene(pane, 600,400,true);
        theScene.setCamera(camera);
        primaryStage.setScene(theScene);

        primaryStage.show();
    }
    static public void main(String args[])
    {
        launch(args);
    }
}