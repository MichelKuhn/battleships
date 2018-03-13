package battleships;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class KartenZeichner {
    private GraphicsContext gc;
    private Image wasser;
    private Image schiff;
    private Image beschossen;

    public KartenZeichner(GraphicsContext gc) {
        this.gc = gc;
        this.wasser = new Image("battleships/images/wasser.png");
        this.beschossen = new Image("battleships/images/beschossen.png");
        this.schiff = new Image("battleships/images/schiff.png");
    }
    
    public void zeichneKarte(Spieler spieler) {
        ArrayList<Feld> felder = spieler.getFelder();
        int helper = 0;
        if (spieler.isMenschlich() == false) {
            helper = Battleships.GEGNER_HELFER_NUMMER;
        }

        for (Feld feld : felder) {
            int x = feld.getX() * 50 + helper;
            int y = feld.getY() * 50;
            gc.drawImage(wasser, x, y);
            if (feld.getSchiff() != -1) {
                gc.drawImage(schiff, x, y);
            }
            if (feld.isBeschossen()) {
                gc.drawImage(beschossen, x, y);
            }
        }
    }
    
    public void zeichneKarten(Spiel spiel) {
        zeichneKarte(spiel.getSpieler1());
        zeichneKarte(spiel.getSpieler2());
    }
}
