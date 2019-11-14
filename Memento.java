public class Memento {
    public Cell sheepLoc;
    public Cell shepherdLoc;
    public Cell wolfLoc;
    public Cell playerLoc;

    public Memento(Cell sheepLoc, Cell shepherdLoc, Cell wolfLoc, Cell playerLoc) {
        this.sheepLoc = sheepLoc;
        this.shepherdLoc = shepherdLoc;
        this.wolfLoc = wolfLoc;
        this.playerLoc = playerLoc;
    }

}