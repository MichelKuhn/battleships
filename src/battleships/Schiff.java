package battleships;

public class Schiff {
    private int laenge;
    private boolean horizontal;
    private int id;
    private int x;
    private int y;
    private boolean versenkt;

    public Schiff(int laenge, int id) {
        this.laenge = laenge;
        this.id = id;
        this.versenkt = false;
    }

    public int getLaenge() {
        return laenge;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public boolean isVersenkt() {
        return versenkt;
    }

    public void setVersenkt(boolean versenkt) {
        this.versenkt = versenkt;
    }
}
