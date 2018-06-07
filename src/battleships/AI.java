package battleships;

import java.util.ArrayList;
import java.util.Random;

public class AI {
    private Spiel spiel;
    private boolean active;
    private ArrayList<Feld> alleFelder;
    private ArrayList<Feld> ideen;
    private ArrayList<Feld> treffer;

    public AI(Spiel spiel) {
        this.spiel = spiel;
        this.alleFelder = new ArrayList<>();
        this.ideen = new ArrayList<>();
        this.treffer = new ArrayList<>();
    }

    public void setMode(boolean active) {
        this.active = active;

        if (active) {
            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 11; j++) {
                    if ((i % 2 == 0 && j % 2 != 0) || ((i % 2 != 0 && j % 2 == 0))) {
                        alleFelder.add(new Feld(i, j));
                    }
                }
            }
        } else {
            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 11; j++) {
                    alleFelder.add(new Feld(i, j));
                }
            }
        }
    }

    public void setzeSchiffe(Spiel spiel) {
        Spieler ai = spiel.getSpieler2();
        while (!ai.getSetzSchiffe().isEmpty()) {
            if(randomHelper(0, 1) == 1) {
                ai.setHorizontal(true);
            } else {
                ai.setHorizontal(false);
            }
            ai.setzeSchiff(randomHelper(1, 10), randomHelper(1, 10));
        }
    }

    private boolean validFeld(Feld dasFeld) {
        return dasFeld.getX() < 11 && dasFeld.getY() < 11 && dasFeld.getX() > 0 && dasFeld.getY() > 0;
    }

    private void entferneFeldMitKoordinaten(int x, int y, ArrayList<Feld> arrayList) {
        for (Feld feld : arrayList) {
            if (feld.getX() == x && feld.getY() == y) {
                arrayList.remove(feld);
                return;
            }
        }
    }

    private int randomHelper(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    private ArrayList<Feld> getNachbarn(int x, int y) {
        ArrayList<Feld> nachbarn = new ArrayList<>();
        nachbarn.add(new Feld(x - 1, y));
        nachbarn.add(new Feld(x + 1, y));
        nachbarn.add(new Feld(x, y + 1));
        nachbarn.add(new Feld(x, y -1));

        return nachbarn;
    }

    private void addIdeaFeld(Feld feld) {
        if (validFeld(feld)) {
            if (!spiel.getSpieler1().getFeldByCoordinates(feld.getX(), feld.getY()).isBeschossen()) {
                ideen.add(feld);
            }
        }
    }

    private Feld hunterGuess() {
        Feld schussFeld = ideen.get(randomHelper(0, ideen.size() - 1));
        if (!spiel.getSpieler1().getFeldByCoordinates(schussFeld.getX(), schussFeld.getY()).isBeschossen()) {
            return schussFeld;
        } else {
            return hunterGuess();
        }
    }

    private Feld randomGuess() {
        return alleFelder.get(randomHelper(0, alleFelder.size() - 1));
    }

    private boolean istSchiffVersenkt (int x, int y) {
        Schiff schiff = spiel.getSpieler1().getSchiffById(spiel.getSpieler1().getFeldByCoordinates(x, y).getSchiffId());

        return schiff.isVersenkt();
    }

    public boolean isActive() {
        return active;
    }

    public void macheZug() {
        Feld schussFeld;
        if (active && !ideen.isEmpty()) {
            schussFeld = hunterGuess();
        } else {
            schussFeld = this.randomGuess();
        }

        entferneFeldMitKoordinaten(schussFeld.getX(), schussFeld.getY(), alleFelder);
        entferneFeldMitKoordinaten(schussFeld.getX(), schussFeld.getY(), ideen);

        if (spiel.ballern(false, schussFeld.getX(), schussFeld.getY())) {
            treffer.add(schussFeld);

            if (istSchiffVersenkt(schussFeld.getX(), schussFeld.getY())) {
                for (Feld getroffenesFeld : treffer) {
                    if (spiel.getSpieler1().getFeldByCoordinates(schussFeld.getX(), schussFeld.getY()).getSchiffId() == spiel.getSpieler1().getFeldByCoordinates(getroffenesFeld.getX(), getroffenesFeld.getY()).getSchiffId()) {
                        for (Feld feld : getNachbarn(getroffenesFeld.getX(), getroffenesFeld.getY())) {
                            entferneFeldMitKoordinaten(feld.getX(), feld.getY(), ideen);
                            entferneFeldMitKoordinaten(feld.getX(), feld.getY(), alleFelder);
                        }
                    }
                }
            } else {
                for (Feld feld : getNachbarn(schussFeld.getX(), schussFeld.getY())) {
                    addIdeaFeld(feld);
                }
            }
        }

    }
}
