package battleships;

import javafx.application.Application;
import javafx.stage.Stage;

public class Battleships extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Battleships");
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
