package battleships;

import javafx.application.Application;
import javafx.stage.Stage;

public class Battleships extends Application {    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Battleships");
        Spiel spiel = new Spiel();
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
        }
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Spieler spieler = new Spieler("Horst");
        for (Feld feld : spieler.getFelder()) {
            System.out.println(feld.getX() + "/" + feld.getY());
        }
        launch(args);
    }
}
