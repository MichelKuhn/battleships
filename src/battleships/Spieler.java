package battleships;

import java.util.ArrayList;

public class Spieler {
    private final String name;
    private final ArrayList<Feld> felder;
    private final boolean menschlich;
    private ArrayList<Schiff> setzSchiffe;
    private ArrayList<Schiff> schiffe;
    private boolean horizontal;
    
    private void schiffeFuellen() {
        this.setzSchiffe.add(new Schiff(5, 1));
        this.setzSchiffe.add(new Schiff(4, 2));
        this.setzSchiffe.add(new Schiff(3, 3));
        this.setzSchiffe.add(new Schiff(3, 4));
        this.setzSchiffe.add(new Schiff(2, 5));
    }

    public Spieler(String name, boolean menschlich) {
        this.name = name;
        
        this.felder = new ArrayList();
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                felder.add(new Feld(i, j));
            }
        }
        
        this.menschlich = menschlich;
        
        this.schiffe = new ArrayList<>();
        this.setzSchiffe = new ArrayList<>();
        schiffeFuellen();
        this.horizontal = false;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Feld> getFelder() {
        return felder;
    }

    public Feld getFeldByCoordinates(int x, int y) {
        for (Feld feld : felder) {
            if (feld.getX() == x && feld.getY() == y) {
                return feld;
            }
        }

        return null;
    }
    
    public boolean isMenschlich() {
        return menschlich;
    }

    public ArrayList<Schiff> getSchiffe() {
        return schiffe;
    }

    public boolean setzeSchiff(int x, int y) {
        Schiff setzSchiff = this.setzSchiffe.get(0);
        ArrayList<Feld> schiffAusFeldern = new ArrayList<>();

        if (horizontal) {
            for (int i = x; i < x + setzSchiff.getLaenge(); i++) {
                if (i > 10) {
                    return false;
                }
                for (Feld feld : this.felder) {
                    if (feld.getX() == i && feld.getY() == y) {
                        schiffAusFeldern.add(feld);
                    }
                }
            }
        } else {
            for (int i = y; i < y + setzSchiff.getLaenge(); i++) {
                if (i > 10) {
                    return false;
                }
                for (Feld feld : this.felder) {
                    if (feld.getX() == x && feld.getY() == i) {
                        schiffAusFeldern.add(feld);
                    }
                }
            }
        }
        
        for (Feld feld : schiffAusFeldern) {
            if (feld.getSchiffId() != -1) {
                return false;
            }
        }
        
        Schiff schiff = new Schiff(setzSchiff.getLaenge(), setzSchiff.getId());
        schiff.setX(x);
        schiff.setY(y);
        schiff.setHorizontal(horizontal);
        this.schiffe.add(schiff);

        for (Feld feld : schiffAusFeldern) {
            feld.setSchiffId(schiff.getId());
        }

        this.setzSchiffe.remove(setzSchiff);
        return true;
    }

    public ArrayList<Schiff> getSetzSchiffe() {
        return setzSchiffe;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public Schiff getSchiffById (int id) {
        for (Schiff schiff : this.schiffe) {
            if (schiff.getId() == id) {
                return schiff;
            }
        }

        return null;
    }

    public boolean hatVerloren() {
        for (Schiff schiff : schiffe) {
            if (!schiff.isVersenkt()) {
                return false;
            }
        }

        return true;
    }
}
