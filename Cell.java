import java.awt.*;
import java.util.*;
import java.lang.*;



public class Cell extends Rectangle {
    Random ShadesOfGreen = new Random();
    int Red = ShadesOfGreen.nextInt(10);
    int Blue = ShadesOfGreen.nextInt(20);
    int Green = ShadesOfGreen.nextInt(200 - 101) + 50;
    int x;
    int y;



    public Cell(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g, Boolean highlighted) {

        Color ShadesOfGreen = new Color(Red, Green, Blue);
        g.setColor(ShadesOfGreen);
        g.fillRect(x,y,35,35);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, 35, 35);


        if (highlighted) {
            g.setColor(Color.WHITE);
            g.drawRect(x + 2, y + 2, 31, 31);
        }

    }
    public void toolTip(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(x + 35, y + 35 , 50,20);
        g.setColor(Color.BLACK);
        g.drawString("height" + ": " + Green / 50, x + 36, y + 47);


    }



    public boolean MouseHover (Point target){

        if (target == null) {
            return false;
        }


        return target.x > x && target.x < x + 35 && target.y > y && target.y < y + 35;
    }

}