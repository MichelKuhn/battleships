package battleships;

import java.util.ArrayList;

public class Spieler {
    private final String name;
    private final ArrayList<Feld> felder;
    private final boolean menschlich;

    public Spieler(String name, boolean menschlich) {
        this.name = name;
        
        this.felder = new ArrayList();
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                felder.add(new Feld(i, j));
            }
        }
        
        this.menschlich = menschlich;
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
                feld.setBeschossen(true);
                if (feld.isSchiff() == true) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public boolean isMenschlich() {
        return menschlich;
    }
}
