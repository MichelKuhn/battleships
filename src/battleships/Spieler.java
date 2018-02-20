package battleships;

import java.util.ArrayList;

public class Spieler {
    private String name;
    private ArrayList<Feld> felder;

    public Spieler(String name) {
        this.name = name;
        
        this.felder = new ArrayList();
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                felder.add(new Feld(i, j));
            }
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Feld> getFelder() {
        return felder;
    }
    
    public boolean ballern (Spieler spieler, int x, int y) {
        for (Feld feld : spieler.getFelder()) {
            if (feld.getX() == x && feld.getY() == y) {
                if (feld.isSchiff() == true) {
                    feld.setBeschossen(true);
                    return true;
                }
            }
        }
        
        return false;
    }
}
