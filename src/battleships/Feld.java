package battleships;

public class Feld {
    private int x;
    private int y;
    private int schiff;
    private boolean beschossen;

    public Feld(int x, int y) {
        this.x = x;
        this.y = y;
        this.schiff = -1;
        this.beschossen = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getSchiff() {
        return schiff;
    }

    public void setSchiff(int schiff) {
        this.schiff = schiff;
    }

    public boolean isBeschossen() {
        return beschossen;
    }

    public void setBeschossen(boolean beschossen) {
        this.beschossen = beschossen;
    }
}
