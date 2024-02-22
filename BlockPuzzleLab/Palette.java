package BlockPuzzleLab;

import java.util.ArrayList;
import java.util.List;

public class Palette {
    List<Shape> shapes = new ArrayList<>();
    static List<Piece> pieces = new ArrayList<>();
    int maxShapes = 6;
    public Palette() {
        shapes.addAll(new ShapeSet().getShapes());
        doLayout(10, 300, 30); // Ensure the pieces are laid out initially
    }
    public static List<Piece> getPieces() {
        return pieces;
    }

    public void doLayout(int x0, int y0, int cellSize) {
        int x = x0;
        int y = y0;
        int shapesPlaced = 0;

        pieces.clear(); // Clear the existing pieces before layout

        for (Shape shape : shapes) {
            if (shapesPlaced >= maxShapes) {
                break;
            }

            Piece piece = new Piece(shape, x, y, cellSize);
            pieces.add(piece);

            shapesPlaced++;

            // Adjust the position for the next shape
            x += cellSize * 4; // Increment x position by cellSize for next shape
            if (x >= x0 + ( 3 * cellSize * 4)) {
                x = x0; // Reset x position if it exceeds the layout width
                y += cellSize * 5; // Move to the next row
            }
        }
    }

    public void replenish() {
        shapes.clear();
        shapes.addAll(new ShapeSet().getShapes());

    }

}
