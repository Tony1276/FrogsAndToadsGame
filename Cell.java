import bos.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cell extends Rectangle {

    private static Random rand = new Random();
    private Stage stage;
    private Grid grid;
    Color c;
    Color brown;
    Color b;
    public boolean block;

    public Cell(int x, int y) {
        super(x, y, 35, 35);
        c = new Color(rand.nextInt(30), rand.nextInt(155)+100, rand.nextInt(30));
        brown = new Color(139,69,19);
        b = new Color(0,0,0);

    }

    public void paint(Graphics g, Boolean highlighted) {
        g.setColor(c);
        g.fillRect(x, y, 35, 35);
        //This relates to stages class
        if(block == true){
            g.setColor(brown);
            g.fillRect(x,y,35,35);
            g.setColor(b);
            for(int i = 0; i < 35; i = i + 7) {
                g.drawLine(x + i, y , x + 35, y + 35 - i);
                g.drawLine(x, y + i, x + 35 - i, y + 35);
            }
        }


        g.setColor(Color.BLACK);
        g.drawRect(x,y, 35, 35);

        if (highlighted) {
            g.setColor(Color.LIGHT_GRAY);
            for(int i = 0; i < 10; i++){
                g.drawRoundRect(x+1, y+1, 33, 33, i, i);
            }
        }
    }





    @Override
    public boolean contains(Point target){
        if (target == null)
            return false;
        return super.contains(target);
    }

    public int getGrassHeight(){
        return c.getGreen()/50;
    }
}
