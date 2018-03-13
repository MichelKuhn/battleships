package battleships;

public class Spiel {
    private Status status;
    private Spieler spieler1, spieler2;
    private int runde;

    public Spiel(String spielername) {
        this.status = Status.MENU;
        this.spieler1 = new Spieler(spielername, true);
        this.spieler2 = new Spieler("Günth3r", false);
        this.runde = 1;
    }
    
    public Spieler getSpieler1() {
        return spieler1;
    }

    public Spieler getSpieler2() {
        return spieler2;
    }

    public boolean ballern(boolean menschlich, int x, int y) {
        Spieler spieler = this.spieler1;
        if (menschlich == true) {
            spieler = this.spieler2;
        }
        for (Feld feld : spieler.getFelder()) {
            if (feld.getX() == x && feld.getY() == y) {
                feld.setBeschossen(true);
                if (feld.getSchiff() != -1) {
                    return true;
                }
            }
        }
        
        return false;
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getRunde() {
        return runde;
    }

    public void neueRunde() {
        this.runde++;
    }
}
