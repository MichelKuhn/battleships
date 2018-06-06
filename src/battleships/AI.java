package battleships;

import java.util.Random;

public class AI {
    private Spiel spiel;
    public AI(Spiel spiel) {
        this.spiel = spiel;
    }

    private int randomHelper(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    private void randomGuess() {
        boolean foundFeld = false;
        while (!foundFeld) {
            int x = randomHelper(1, 10);
            int y = randomHelper(1, 10);

            if (!spiel.getSpieler1().getFeldByCoordinates(x, y).isBeschossen()) {
                spiel.ballern(false, x, y);
                foundFeld = true;
            }
        }

    }

    public void macheZug() {
        this.randomGuess();
    }
}
