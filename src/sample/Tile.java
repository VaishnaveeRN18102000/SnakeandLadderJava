package sample;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Tile extends Rectangle
{
    public Tile(int x, int y)
    {
        setWidth(Main.T_size);
        setHeight(Main.T_size);
        setFill(Color.PINK);
        setStroke(Color.BLACK);
    }
}
