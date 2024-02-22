package BlockPuzzleLab;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class View {
    private static Piece selectedPiece;
    private static Point lastMousePoint;
    private static int paletteCellSize = 90;
    private static List<Shape> poppableRegions = new ArrayList<>();

    public static void main(String[] args) {

        int cellSize = 30; // Define the cell size


        JFrame frame = new JFrame("Block Puzzle");
        frame.setSize(400, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Model model = new Model(9); // Create the model


        JPanel panel = new JPanel() {

            Shape ghostShape = null; // Ghost shape initially null
            Palette palette = new Palette();
            {addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        for (Piece piece : palette.getPieces()) {
                            if (piece.contains(e.getPoint(), paletteCellSize)) {
                                System.out.println("Clicked : " + piece);
                                selectedPiece = piece;
                                selectedPiece.state = PieceState.IN_PLAY;
                                setGhostShape();
                                lastMousePoint = e.getPoint();
                                System.out.println("Selected Piece: " + selectedPiece);
                                System.out.println(piece.toString());
                                break;
                            }
                        }
                        repaint();
                    }


                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (selectedPiece != null) {
                            Shape shape = selectedPiece.snapToGrid(0);
                            if (model.canPlace(shape)) {
                                model.placeShape(shape);
                                model.clearFullRegions();
                                selectedPiece.state = PieceState.PLACED;
                                palette.getPieces().remove(selectedPiece);

                                // Check if there are no pieces left in the palette
                                if (palette.getPieces().isEmpty()) {
                                    palette.replenish();
                                    palette.doLayout(10, 300, 30);
                                    //Game over
                                    if (model.getFullRegions().isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Game Over!");
                                        System.exit(0);
                                    }
                                }
                            } else {
                                selectedPiece.state = PieceState.IN_PALETTE;
                                palette.doLayout(10, 300 + 100, 30);
                            }
                            poppableRegions.clear();
                        }
                        selectedPiece = null;
                        ghostShape = null;
                        repaint();
                    }
                });

                addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if (selectedPiece != null && lastMousePoint != null) {
                            int dx = e.getX() - lastMousePoint.x;
                            int dy = e.getY() - lastMousePoint.y;

                            // Update the position of the selected piece while dragging
                            selectedPiece.x += dx;
                            selectedPiece.y += dy;
                            setGhostShape();
                            if(ghostShape!=null){
                                 poppableRegions = model.getWouldBeFullRegions(ghostShape);
                            }else{
                                 poppableRegions.clear();
                            }
                            lastMousePoint = e.getPoint();
                            repaint();
                        }
                    }
                });
            }

            public void setGhostShape() {
                if (selectedPiece != null) {
                    ghostShape = selectedPiece.shape;

                } else {
                    ghostShape = null;
                }
            }


            @Override
            public void paint(Graphics g) {
                super.paint(g);
                paintGrid(g);
                paintMiniGrids((Graphics2D) g);
                paintGhostShape(g, cellSize);
                // Display the score
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 16));
                g.drawString("Score: " + model.getScore(), 10, 30);
                paintShapePalette(g, cellSize);
                paintPoppableRegion(g, cellSize);

            }

            private void paintGrid(Graphics g) {

                int[][] grid = model.getGrid();
                int gridSize = model.getGridSize();

                for (int i = 0; i < gridSize; i++) {
                    for (int j = 0; j < gridSize; j++) {
                        if (grid[i][j] != 0) {
                            g.setColor(Color.GREEN); // Set color for filled cells
                        } else {
                            g.setColor(Color.WHITE); // Set color for empty cells
                        }

                        g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                        g.setColor(Color.GRAY);
                        g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    }
                }
            }


            private void paintMiniGrids(Graphics2D g) {
                g.setColor(Color.BLACK);
                g.setStroke(new BasicStroke(2));
                int newCellSize = cellSize * 3;
                int gridSize = 3;
                int gridWidth = gridSize * newCellSize;
                int gridHeight = gridSize * newCellSize;
                for (int i = 0; i <= gridSize; i++) {
                    g.drawLine(0, i * newCellSize, gridWidth, i * newCellSize);
                    g.drawLine(i * newCellSize, 0, i * newCellSize, gridHeight);
                }
            }

            private void paintPoppableRegion(Graphics g, int cellSize) {
                // Assuming you have a method in your model to get the current poppable regions
                for (Shape region : poppableRegions) {
                    for (Cell cell : region) {
                        int x = cell.x() * cellSize;
                        int y = cell.y() * cellSize;
                        g.fillRect(x, y, cellSize, cellSize);
                    }
                }
            }

            private void paintGhostShape(Graphics g, int cellSize) {
                if (ghostShape != null) {
                    g.setColor(new Color(0, 0, 255, 100)); // Blue color with some transparency (adjust alpha as needed)
                    for (Cell cell : ghostShape) {
                        int startX = selectedPiece.x + cell.x() * cellSize;
                        int startY = selectedPiece.y + cell.y() * cellSize;
                        g.fillRect(startX, startY, cellSize, cellSize);

                    }
                }
            }


            private void paintShapePalette(Graphics g, int cellSize) {
                // Creates the gray palette area
                g.setColor(Color.GRAY);
                g.fillRect(0, 300, getWidth(), getHeight() - 300);

                for (Piece piece : palette.getPieces()) {
                    int miniCellSize = piece.cellSize;
                    // Adjusting the position to draw the piece correctly
                    int startX = piece.x;
                    int startY = piece.y;

                    // For each cell in the piece's shape
                    for (Cell cell : piece.shape) {
                        // Calculate the position on the palette
                        int x = startX + cell.x() * miniCellSize;
                        int y = startY + cell.y() * miniCellSize;

                        // Draw black outline
                        g.setColor(Color.BLACK);
                        g.drawRect(x, y, miniCellSize, miniCellSize);

                        // Fill with blue shade
                        g.setColor(new Color(0, 0, 255, 100)); // Blue color with some transparency (adjust alpha as needed)
                        g.fillRect(x + 1, y + 1, miniCellSize - 1, miniCellSize - 1);
                    }
                }
            }
        };
        frame.add(panel);
        frame.setVisible(true);
    }

}

