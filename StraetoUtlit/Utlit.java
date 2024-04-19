package hi.reiknirit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.web.*;
import javafx.stage.Stage;

public class Utlit extends Application {

    // launch the application
    public void start(Stage stage)
    {

        try {

            // set title for the stage
            stage.setTitle("Pane");

            // create a label
            Label label = new Label("this is Pane example");

            // create a Pane
            Pane pane = new Pane(label);

            // create a scene
            Scene scene = new Scene(pane, 400, 300);

            // set the scene
            stage.setScene(scene);

            stage.show();
        }

        catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    // Main Method
    public static void main(String args[])
    {

        // launch the application
        launch(args);
    }
}