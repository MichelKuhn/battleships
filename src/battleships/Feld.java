package battleships;

public class Feld {
    private int x;
    private int y;
    private int schiffId;
    private boolean beschossen;

    public Feld(int x, int y) {
        this.x = x;
        this.y = y;
        this.schiffId = -1;
        this.beschossen = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isBeschossen() {
        return beschossen;
    }

    public void setBeschossen(boolean beschossen) {
        this.beschossen = beschossen;
    }

    public int getSchiffId() {
        return schiffId;
    }

    public void setSchiffId(int schiffId) {
        this.schiffId = schiffId;
    }
}
