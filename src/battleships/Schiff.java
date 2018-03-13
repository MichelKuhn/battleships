package battleships;

public class Schiff {
    private int laenge;
    private boolean horizontal;
    private boolean da;
    private int x;
    private int y;

    public Schiff(int laenge, boolean da) {
        this.laenge = laenge;
        this.da = da;
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

    public boolean isDa() {
        return da;
    }

    public void setDa(boolean da) {
        this.da = da;
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
}
