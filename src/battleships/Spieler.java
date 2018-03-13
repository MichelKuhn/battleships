package battleships;

import java.util.ArrayList;

public class Spieler {
    private final String name;
    private final ArrayList<Feld> felder;
    private final boolean menschlich;
    private ArrayList<Schiff> schiffe;
    
    private void schiffeFuellen() {
        this.schiffe.add(new Schiff(5, false));
        this.schiffe.add(new Schiff(4, false));
        this.schiffe.add(new Schiff(3, false));
        this.schiffe.add(new Schiff(3, false));
        this.schiffe.add(new Schiff(2, false));
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
        schiffeFuellen();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Feld> getFelder() {
        return felder;
    }
    
    public boolean isMenschlich() {
        return menschlich;
    }

    public ArrayList<Schiff> getSchiffe() {
        return schiffe;
    }
    
    public boolean setzeSchiff(int index, boolean horizontal, int x, int y) {
        Schiff schiff = this.schiffe.get(index);
        
        ArrayList<Feld> schiffAusFeldern = new ArrayList<>();
        if (horizontal == true) {
            for (int i = x; i < x + schiff.getLaenge(); i++) {
                System.out.println(i);
                for (Feld feld : this.felder) {
                    if (feld.getX() == i && feld.getY() == y) {
                        schiffAusFeldern.add(feld);
                    }
                }
            }
        } else {
            for (int i = y; i < y + schiff.getLaenge(); i++) {
                for (Feld feld : this.felder) {
                    if (feld.getX() == x && feld.getY() == i) {
                        schiffAusFeldern.add(feld);
                    }
                }
            }
        }
        
        for (Feld feld : schiffAusFeldern) {
            if (feld.getSchiff() != -1) {
                return false;
            }
        }
        
        for (Feld feld : schiffAusFeldern) {
            feld.setSchiff(index);
            System.out.println(feld.getX() + "/" + feld.getY());
        }
        
        schiff.setDa(true);
        schiff.setHorizontal(horizontal);
        schiff.setX(x);
        schiff.setY(y);
        return true;
    }
}
