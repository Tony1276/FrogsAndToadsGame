import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.time.*;
import java.util.List;

import bos.GameBoard;
import bos.MoveUp;
import bos.RelativeMove;

public class Stage extends KeyObservable {
    protected Grid grid;
    protected Cell cell;
    protected Character sheep;
    protected Character shepherd;
    protected Character wolf;
    private List<Character> allCharacters;
    protected Player player;
    private Instant timeOfLastMove = Instant.now();
    protected List<bos.Pair<Integer,Integer>> blockCells = new ArrayList<>();
    protected List<bos.Pair<Integer,Integer>> checkBlockedCells = new ArrayList<>();

    public Stage() {
        SAWReader sr = new SAWReader("data/stage1.saw");
        grid = new Grid(10, 10);
        shepherd = new Shepherd(grid.cellAtRowCol(sr.getShepherdLoc().first, sr.getShepherdLoc().second), new StandStill());
        sheep = new Sheep(grid.cellAtRowCol(sr.getSheepLoc().first, sr.getSheepLoc().second), new MoveTowards(shepherd));
        wolf = new Wolf(grid.cellAtRowCol(sr.getWolfLoc().first, sr.getWolfLoc().second), new MoveTowards(sheep));

        // This relates to the cell class
        blockCells = sr.getBlocLoc();
        for(bos.Pair<Integer,Integer> cell: blockCells){
            grid.cellAtRowCol(cell.first,cell.second).block = true;
        }

        player = new Player(grid.cellAtRowCol(sr.getPlayerLoc().first, sr.getPlayerLoc().second));
        this.register(player);


        allCharacters = new ArrayList<Character>();
        allCharacters.add(sheep);
        allCharacters.add(shepherd);
        allCharacters.add(wolf);
    }

    public Memento CreateMementoState(){
        //created a new Memento object using sheep, shepherd, wolf and player in stage class.
        Memento memento = new Memento(sheep.location, shepherd.location, wolf.location, player.location);
        return memento;
    }

    public void restoreCreatedMementoState(Memento save) {
            sheep.location = save.sheepLoc;
            shepherd.location = save.shepherdLoc;
            wolf.location = save.wolfLoc;
            player.location = save.playerLoc;
    }

    public void update(){
        if (!player.inMove()) {
            if (sheep.location == shepherd.location) {
                System.out.println("The sheep is safe :)");
                System.exit(0);
            } else if (sheep.location == wolf.location) {
                System.out.println("The sheep is dead :(");
                System.exit(1);
            } else {
                if (sheep.location.x == sheep.location.y) {
                    shepherd.setBehaviour(new MoveTowards(sheep));
                }
                allCharacters.forEach((c) -> {
                    c.aiMove(this).perform();
                    if(c.location.block == true){
                        c.aiMove(this).oppositeMove().perform();
                    }
                });


//                if(sheep.location.block == true){
//                    System.out.println("sheep can't move");
//                    sheep.aiMove(this).oppositeMove().perform();
//                    sheep.aiMove(this).perpendicularMoves();
//                    //sheep.aiMove(this).oppositeMove().perform();
//                }
//
//                if(shepherd.location.block == true){
//                    System.out.println("Shepherd can't move");
//                    shepherd.aiMove(this).oppositeMove().perform();
//                    shepherd.aiMove(this).perpendicularMoves();
//                }
//
//                if(wolf.location.block == true){
//                    System.out.println("Wolf can't move");
//                    wolf.aiMove(this).oppositeMove().perform();
//                    wolf.aiMove(this).perpendicularMoves();
//                }

                player.startMove();
                timeOfLastMove = Instant.now();

            }
        }
    }


    public void paint(Graphics g, Point mouseLocation) {
        grid.paint(g, mouseLocation);
        sheep.paint(g);
        shepherd.paint(g);
        wolf.paint(g);
        player.paint(g);
    }
}