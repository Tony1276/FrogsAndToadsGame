import javax.swing.*;
import java.awt.*;

public class main extends JFrame implements Runnable {

    private class Canvas extends JPanel {

        private Grid grid;

        public Canvas() {
            setPreferredSize(new Dimension(1280, 720));

            grid = new Grid(10, 10);
        }

        @Override
        public void paint(Graphics g) {
            grid.paint(g, getMousePosition());
        }
    }

    public static void main(String[] args) {


        main window = new main();
        window.run();
    }

    private main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Canvas());
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void run() {
        while (true) {
            this.repaint();
        }
    }

}