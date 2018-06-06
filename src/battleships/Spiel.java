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

    public void checkVersenkt(Schiff schiff, Spieler spieler) {
        int counter = 0;
        for (Feld feld:spieler.getFelder()) {
            if (feld.getSchiffId() == schiff.getId() && feld.isBeschossen()) {
                counter++;
            }
        }

        if (counter == schiff.getLaenge()) {
            schiff.setVersenkt(true);
        } else {
            schiff.setVersenkt(false);
        }
    }

    public boolean ballern(boolean menschlich, int x, int y) {
        Spieler gegner = this.spieler1;
        if (menschlich) {
            gegner = this.spieler2;
        }
        for (Feld feld : gegner.getFelder()) {
            if (x > 10 || y > 10) {
                return false;
            }

            if (feld.getX() == x && feld.getY() == y) {
                feld.setBeschossen(true);
                if (feld.getSchiffId() != -1) {
                    for (Schiff schiff : gegner.getSchiffe()) {
                        checkVersenkt(schiff, gegner);
                    }

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
