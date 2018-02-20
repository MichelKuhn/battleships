package battleships;

public class Spiel {
    private Status status;
    private Spieler spieler1, spieler2;

    public Spiel() {
        this.status = Status.MENU;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
