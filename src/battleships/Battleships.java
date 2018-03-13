package battleships;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Battleships extends Application {
    public final static int GEGNER_HELFER_NUMMER = 550;

    private Status gegnerZieht(Spiel spiel) {
        spiel.ballern(false, spiel.getRunde(), spiel.getRunde());
        spiel.neueRunde();
        return Status.SPIELEN;
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Battleships");
        
        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);
    
        Canvas canvas = new Canvas(1150, 600);
        root.getChildren().add(canvas);
    
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        KartenZeichner kz = new KartenZeichner(gc);
        
        Spiel spiel = new Spiel("Der mensch");
        //TODO: Menü

        theScene.setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                switch(spiel.getStatus()) {
                    case MENU:
                        //TODO: Menü
                        spiel.setStatus(Status.SCHIFFE_SETZEN);
                        kz.zeichneKarte(spiel.getSpieler1());
                        break;
                    case SCHIFFE_SETZEN:
                        for (Schiff schiff : spiel.getSpieler1().getSchiffe()) {
                            if (schiff.isDa() == false) {
                                //zeichen Schiff am Rand
                            }
                        }
                        kz.zeichneKarte(spiel.getSpieler1());
                        spiel.getSpieler1().setzeSchiff(1, true, (int)e.getX() / 50, (int)e.getY() / 50);
                        
                        if(spiel.getSpieler1().getSchiffe().isEmpty()) {
                            spiel.setStatus(Status.SPIELEN);
                        } else {
                            spiel.setStatus(Status.SCHIFFE_SETZEN);
                        }
                        kz.zeichneKarte(spiel.getSpieler1());
                        break;
                    case SPIELEN:
                        spiel.ballern(true, (int)e.getX() / 50 - 11, (int)e.getY() / 50);
                        kz.zeichneKarten(spiel);
                        spiel.setStatus(gegnerZieht(spiel));
                        kz.zeichneKarten(spiel);
                        break;
                }
            }
        });

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
