package BlockPuzzleLab;

import java.awt.*;



public class Piece  {
    //models a Shape with pixel cords
    public Shape shape;
    //top left corner of the piece
    int x, y, cellSize;
    PieceState state = PieceState.IN_PALETTE;

    public Piece(Shape shape, int x, int y, int cellSize) {
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.cellSize = cellSize;
    }

    public boolean contains(Point point, int cellSize) {
        int pieceSize = cellSize/3;
        // Calculate the bounds of the piece based on its position (x, y) and size (pieceSize)
        int minX = x;
        int minY = y;
        int maxX = x + pieceSize;
        int maxY = y + pieceSize;

        // Check if the mouse click falls within the bounds of the piece
        return (point.x >= minX && point.x <= maxX && point.y >= minY && point.y <= maxY);
    }

    public Shape snapToGrid(int margin) {
        // Calculate the top-left corner's grid coordinates based on current pixel coordinates
        int gridX = (x - margin + cellSize / 2) / cellSize;
        int gridY = (y - margin + cellSize / 2) / cellSize;

        // Snap the piece's pixel position to these grid coordinates
        this.x = gridX * cellSize + margin;
        this.y = gridY * cellSize + margin;

        // Create a new Shape that represents the snapped piece
        Shape snappedShape = new Shape(this.shape.type);

        // Adjust the cells of the Shape to the new snapped top-left corner
        for (Cell cell : this.shape) {
            int snappedCellX = cell.x() + gridX;
            int snappedCellY = cell.y() + gridY;
            snappedShape.add(new Cell(snappedCellX, snappedCellY));
        }

        // Return the new Shape that has been snapped to the grid
        return snappedShape;
    }

    @Override
    public String toString() {
        return "Piece at (" + x + "," + y + ")";
    }


}


