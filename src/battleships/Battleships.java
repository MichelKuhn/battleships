package battleships;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static battleships.Status.NIEDERLAGE;
import static battleships.Status.SIEG;
import static battleships.Status.SPIELEN;

public class Battleships extends Application {
    public final static int GEGNER_HELFER_NUMMER = 550;

    private void zeichenSchiffeZuSetzen(Spiel spiel, KartenZeichner kz) {
        int nummer = 1;
        for (Schiff schiff : spiel.getSpieler1().getSetzSchiffe()) {
            kz.zeichneSchiffZuSetzen(schiff, nummer);
            nummer += 1;
        }
        kz.zeichneKarte(spiel.getSpieler1());
        kz.zeichneHorizontalAnzeige(spiel.getSpieler1());
    }

    private void zeichneSpiel(Spiel spiel, KartenZeichner kz) {
        kz.zeichneKarten(spiel);
    }

    private void setzeGegnerSchiffe(Spiel spiel) {
        Spieler gegner = spiel.getSpieler2();
        gegner.setzeSchiff(2, 2);
        gegner.setzeSchiff(3, 2);
        gegner.setzeSchiff(5, 2);
        gegner.setzeSchiff(6, 2);
        gegner.setzeSchiff(8, 2);
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
        setzeGegnerSchiffe(spiel);

        AI ai = new AI(spiel);

        gc.drawImage(new Image("battleships/images/menu.png"), 0, 0);

        theScene.setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                switch(spiel.getStatus()) {
                    case MENU:
                        spiel.setStatus(Status.SCHIFFE_SETZEN);
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        zeichenSchiffeZuSetzen(spiel, kz);
                        break;
                    case SCHIFFE_SETZEN:
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        if (e.getButton() == MouseButton.PRIMARY) {
                            spiel.getSpieler1().setzeSchiff((int)e.getX() / 50, (int)e.getY() / 50);
                            if(spiel.getSpieler1().getSetzSchiffe().isEmpty()) {
                                spiel.setStatus(SPIELEN);
                                zeichneSpiel(spiel, kz);
                                break;
                            } else {
                                spiel.setStatus(Status.SCHIFFE_SETZEN);
                            }
                        } else if (e.getButton() == MouseButton.SECONDARY) {
                            spiel.getSpieler1().setHorizontal(!spiel.getSpieler1().isHorizontal());
                        }

                        zeichenSchiffeZuSetzen(spiel, kz);
                        break;
                    case SPIELEN:
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                        if (e.getButton() == MouseButton.PRIMARY) {
                            spiel.ballern(true, (int)e.getX() / 50 - 11, (int)e.getY() / 50);
                            if (spiel.getSpieler2().hatVerloren()) {
                                spiel.setStatus(SIEG);
                                gc.drawImage(new Image("battleships/images/sieg.png"), 0, 0);
                                break;
                            }
                            ai.macheZug();
                            if (spiel.getSpieler1().hatVerloren()) {
                                gc.drawImage(new Image("battleships/images/verloren.png"), 0, 0);
                                break;
                            }
                        }

                        zeichneSpiel(spiel, kz);
                        break;

                    case SIEG:
                        gc.drawImage(new Image("battleships/images/sieg.png"), 0, 0);
                        break;

                    case NIEDERLAGE:
                        gc.drawImage(new Image("battleships/images/verloren.png"), 0, 0);
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
