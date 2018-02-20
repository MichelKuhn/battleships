package battleships;

public class Feld {
    private int x;
    private int y;
    private boolean schiff;
    private boolean beschossen;

    public Feld(int x, int y) {
        this.x = x;
        this.y = y;
        this.schiff = false;
        this.beschossen = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public boolean isSchiff() {
        return schiff;
    }

    public void setSchiff(boolean schiff) {
        this.schiff = schiff;
    }

    public boolean isBeschossen() {
        return beschossen;
    }

    public void setBeschossen(boolean beschossen) {
        this.beschossen = beschossen;
    }
}
