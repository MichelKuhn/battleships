package battleships;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Battleships extends Application {    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Battleships");
        /* Spiel spiel = new Spiel();
        while (spiel.getStatus() != Status.ENDE) {
            switch (spiel.getStatus()) {
                case MENU:
                    break;
                case SCHIFFE_SETZEN:
                    break;
                case SPIELEN:
                    break;
            }
            spiel.setStatus(Status.ENDE);
        } */
        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);
    
        Canvas canvas = new Canvas(1150, 600);
        root.getChildren().add( canvas );
    
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Spieler spieler1 = new Spieler("H0r5t", true);
        Spieler spieler2 = new Spieler("Günth3r", false);
        
        spieler1.ballern(spieler2, 2, 2);
        KartenZeichner kz = new KartenZeichner(gc);
        kz.zeichneKarte(spieler1);
        kz.zeichneKarte(spieler2);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
