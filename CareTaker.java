import bos.GameBoard;

import java.awt.event.KeyEvent;
public class CareTaker implements KeyObserver {
    Stage stage;
    Memento savedMemento;

    public CareTaker(Stage stage) {
        this.stage = stage;
    }


    public void notify(char c, GameBoard<Cell> gb) {
        if (c == ' ') {
            //same thing as save functionality
            Memento save = new Memento(stage.sheep.location, stage.shepherd.location, stage.wolf.location, stage.player.location);
            save = stage.CreateMementoState();
            savedMemento = save;

            System.out.println("Saved Cells:");
            System.out.println(savedMemento.sheepLoc);
            System.out.println(savedMemento.shepherdLoc);
            System.out.println(savedMemento.wolfLoc);
            System.out.println(savedMemento.playerLoc);
        }

        if (c == 'r') {
            // same thing as restore functionality
            if(savedMemento != null) {
                stage.restoreCreatedMementoState(savedMemento);
                System.out.println("Restored Cells:");
                System.out.println(savedMemento.sheepLoc);
                System.out.println(savedMemento.shepherdLoc);
                System.out.println(savedMemento.wolfLoc);
                System.out.println(savedMemento.playerLoc);
            }
        }
    }
}
