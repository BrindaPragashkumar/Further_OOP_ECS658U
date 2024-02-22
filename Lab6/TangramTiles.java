package Lab6;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class TangramTiles {
    static ArrayList<Tile> getTiles() {
        ArrayList<Tile> list = new ArrayList<>();
        Tile largeTriangle1 = new Tile(new Vec2d(200, 0), new
                ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(200, 0),
                new Vec2d(0, 200)
        )), Color.MAGENTA);
// Large Triangle 2 (Green, bottom-right)
        Tile largeTriangle2 = new Tile(new Vec2d(200, 200), new
                ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(-200, 0),
                new Vec2d(0, -200)
        )), Color.GREEN);
// Square (Blue, center)
        Tile square = new Tile(new Vec2d(100, 100), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(100, 0),
                new Vec2d(100, 100),
                new Vec2d(0, 100)
        )), Color.BLUE);
// Parallelogram (Orange, bottom-left)
        Tile parallelogram = new Tile(new Vec2d(0, 100), new
                ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(100, 50),
                new Vec2d(50, 150),
                new Vec2d(-50, 100)
        )), Color.ORANGE);
// Small Triangle 1 (Red, top of the square)
        Tile smallTriangle1 = new Tile(new Vec2d(100, 0), new
                ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(100, 100),
                new Vec2d(0, 100)
        )), Color.RED);
// Small Triangle 2 (Yellow, right side of the square)
        Tile smallTriangle2 = new Tile(new Vec2d(200, 100), new
                ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 100),
                new Vec2d(-100, 100)
        )), Color.YELLOW);
// Medium Triangle (Cyan, top-right)
        Tile mediumTriangle = new Tile(new Vec2d(200, 0), new
                ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(100, 100),
                new Vec2d(0, 100)
        )), Color.CYAN);
        list.add(largeTriangle1);
        list.add(largeTriangle2);
        list.add(square);
        list.add(parallelogram);
        list.add(smallTriangle1);
        list.add(smallTriangle2);
        list.add(mediumTriangle);
        return list;
    }
    public static Tile getBox() {
        return new Tile(new Vec2d(350, 350), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(400, 0),
                new Vec2d(400, 400),
                new Vec2d(0, 400)
        )), Color.black);
    }
}