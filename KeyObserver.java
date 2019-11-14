import bos.GameBoard;
import java.awt.event.*;

public interface KeyObserver {
    public void notify(char c, GameBoard<Cell> gb);

}
