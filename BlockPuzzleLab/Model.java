package BlockPuzzleLab;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private int[][] grid;
    private final int gridSize;
   private int score = 0;

    public Model(int gridSize) {
        this.gridSize = gridSize;
        grid = new int[gridSize][gridSize];
        initialiseGrid();
    }

    // Initialise the grid to all empty cells
    private void initialiseGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = 0;
            }
        }
    }


    public boolean canPlace(Shape shape) {
        return canPlaceGrid(shape, grid);
    }

    private boolean canPlaceGrid(Shape shape, int[][] actualGrid) {
        int[][] checkGrid = copyGrid(actualGrid);

        for (Cell cell : shape) {
            int cellX = cell.x();
            int cellY = cell.y();
            if (cellX < 0 || cellX >= gridSize || cellY < 0 || cellY >= gridSize) {
                return false;
            }
            if (checkGrid[cellX][cellY] != 0) {
                return false;
            }
            checkGrid[cellX][cellY] = 1;
        }
        return true;
    }

    private int[][] copyGrid(int[][] originalGrid) {
        int[][] copy = new int[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            System.arraycopy(originalGrid[i], 0, copy[i], 0, gridSize);
        }

        return copy;
    }


    public boolean fullRegion(Shape shape) {
        return fullRegionGrid(shape, grid);
    }

    private boolean fullRegionGrid(Shape shape, int[][] grid) {
        int[][] checkGrid = copyGrid(grid);

        for (Cell cell : shape) {
            int cellX = cell.x();
            int cellY = cell.y();
            if (cellX < 0 || cellX >= gridSize || cellY < 0 || cellY >= gridSize) {
                return false;
            }
            if (checkGrid[cellX][cellY] != 0) {
                return false;
            }

            checkGrid[cellX][cellY] = 1;
        }
        return true;
    }

    private boolean fullSubsquareGrid(int startX, int startY, int[][] grid) {
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    List<Shape> getFullRegionsGrid(int[][] grid) {
        List<Shape> fullRegions = new ArrayList<>();

        // Clear full rows
        for (int i = 0; i < gridSize; i++) {
            boolean isRowFull = true;
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] == 0) {
                    isRowFull = false;
                    break;
                }
            }
            if (isRowFull) {
                for (int j = 0; j < gridSize; j++) {
                    grid[i][j] = 0; // Clear the entire row
                }
            }
        }

        // Clear full columns
        for (int j = 0; j < gridSize; j++) {
            boolean isColumnFull = true;
            for (int i = 0; i < gridSize; i++) {
                if (grid[i][j] == 0) {
                    isColumnFull = false;
                    break;
                }
            }
            if (isColumnFull) {
                for (int i = 0; i < gridSize; i++) {
                    grid[i][j] = 0; // Clear the entire column
                }
            }
        }


        // Check for full subsquares
        for (int i = 0; i <= gridSize - 3; i++) {
            for (int j = 0; j <= gridSize - 3; j++) {
                Shape subsquare = new Shape(RegionType.SUBSQUARE);
                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        subsquare.add(new Cell(k, l));
                    }
                }
                if (fullSubsquareGrid(i, j, grid)) {
                    fullRegions.add(subsquare);
                }
            }
        }

        return fullRegions;
    }


    // Additional method to calculate score
    public int calculateScore() {
      score += 10;
      return score;
    }

    // Getter for the score
    public int getScore() {
        return score;
    }
    List<Shape> getWouldBeFullRegions(Shape toPlace) {
        // Create a copy of the grid to test placement
        int[][] testGrid = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            testGrid[i] = grid[i].clone();
        }
        // Place the shape on the test grid
        placeShapeGrid(toPlace, testGrid);

        // Return the full regions that would result from placing the shape
        return getFullRegionsGrid(testGrid);
    }
    List<Shape> getFullRegions() {
        return getFullRegionsGrid(grid);
    }

    public void clearFullRegions() {
        // Get the list of full regions
        List<Shape> fullRegions = getFullRegionsGrid(grid);

        // Clear each full region
        for (Shape region : fullRegions) {
            for (Cell cell : region) {
                grid[cell.x()][cell.y()] = 0; // Clear the cell in the grid (Assuming '0' marks an empty cell)
            }
        }
        score = calculateScore();
    }


    public void placeShape(Shape shape) {
        placeShapeGrid(shape, grid);
    }

    //
    public void placeShapeGrid(Shape shape, int[][] grid) {
        for (Cell cell : shape) {
            if (cell.x() >= 0 && cell.x() < grid.length && cell.y() >= 0 && cell.y() < grid[0].length) {
                grid[cell.x()][cell.y()] = 1; // Assuming '1' marks a filled cell
            }
        }
    }

    //
    public int getGridSize() {
        return gridSize;
    }

    public int[][] getGrid() {
        return grid;
    }
}

