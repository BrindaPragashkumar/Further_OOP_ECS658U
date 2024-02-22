package BlockPuzzleLab;

import java.util.ArrayList;
import java.util.List;

public class ShapeSet {
    public List<Shape> getShapes() {
        List<Shape> shapes = new ArrayList<>();

        // Define different shapes and add them to the list
        shapes.add(createPiece1(RegionType.PIECE));
        shapes.add(createPiece2(RegionType.PIECE));
        shapes.add(createPiece3(RegionType.PIECE));
        shapes.add(createPiece4(RegionType.PIECE));
        shapes.add(createPiece5(RegionType.PIECE));
        shapes.add(createPiece6(RegionType.PIECE));
        shapes.add(createPiece7(RegionType.PIECE));
        shapes.add(createPiece8(RegionType.PIECE));


        // Add more pieces as needed...

        return shapes;
    }

    private Shape createPiece8(RegionType type) {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(0, 0));
        cells.add(new Cell(0, 1));
        cells.add(new Cell(1, 0));
        cells.add(new Cell(1, 1));
        return new Shape(type,cells);
    }

    private Shape createPiece7(RegionType type) {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(0, 0));
        cells.add(new Cell(0, 1));
        cells.add(new Cell(0, 2));
        cells.add(new Cell(1, 0));
        return new Shape(type,cells);
    }

    private Shape createPiece6(RegionType type) {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(0, 0));
        cells.add(new Cell(0, 1));
        cells.add(new Cell(0, 2));
        cells.add(new Cell(0, 3));
        return new Shape(type,cells);
    }

    private Shape createPiece5(RegionType type) {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(0, 0));
        cells.add(new Cell(0, 1));
        cells.add(new Cell(0, 2));
        cells.add(new Cell(1, 2));
        return new Shape(type,cells);
    }

    private Shape createPiece4(RegionType type) {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(0, 0));
        cells.add(new Cell(0, 1));
        cells.add(new Cell(1, 1));
        cells.add(new Cell(1, 2));
        return new Shape(type,cells);
    }

    private Shape createPiece3(RegionType type) {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(0, 0));
        return new Shape(type, cells);
    }
    private Shape createPiece2(RegionType type) {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(0, 0));
        cells.add(new Cell(0, 1));
        return new Shape(type, cells);
    }

    private Shape createPiece1(RegionType type) {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(0, 0));
        cells.add(new Cell(0, 1));
        cells.add(new Cell(0, 2));
        return new Shape(type, cells);
    }

    // Define more methods to create different-sized pieces if required
}
