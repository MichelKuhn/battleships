package battleships;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class KartenZeichner {
    private GraphicsContext gc;
    private Image wasser;
    private Image beschossen;

    public KartenZeichner(GraphicsContext gc) {
        this.gc = gc;
        this.wasser = new Image("battleships/images/wasser.png");
        this.beschossen = new Image("battleships/images/beschossen.png");
    }
    
    public void zeichneKarte(Spieler spieler) {
        ArrayList<Feld> felder = spieler.getFelder();
        int helper = 0;
        if (spieler.isMenschlich() == false) {
            helper = 550;
        }

        for (Feld feld : felder) {
            int x = feld.getX() * 50 + helper;
            int y = feld.getY() * 50;
            gc.drawImage(wasser, x, y);
            if (feld.isBeschossen()) {
                gc.drawImage(beschossen, x, y);
            }
        }
    }
}
