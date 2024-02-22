package Lab6;

import java.util.ArrayList;

public class PuzzleModel {
    Tile box;
    ArrayList<Tile> tiles;

    public PuzzleModel(Tile box, ArrayList<Tile> tiles){
        this.box = box;
        this.tiles = tiles;
    }
    public boolean isSolved(){
        return countContains() == tiles.size()&& countOverlaps() == 0;
    }

    public String getStatusText(){
        return "n Overlaps" + countOverlaps()+ "Contains?" + countContains() + " Solved?" + isSolved();
    }

    // This method checks if given point is inside
    // any of the tiles and returns the corresponding tile.
    public Tile getTileAt(Vec2d point) {
        for (Tile tile : tiles) {
            if (tile.contains(point)) {
                return tile;
            }
        }
        return null;

    }

    //checks if tile (currentShape) overlaps with any other tile
    public boolean checkOverlaps(Tile currentShape){
        for (Tile other : tiles) {
            if (other != currentShape && currentShape.intersects(other)) {
                return true;
            }
        }
        return false;
    }

    public int countOverlaps(){
        int count = 0;
        for (Tile tile : tiles) {
            if (checkOverlaps(tile)) {
                count++;
            }
        }
        return count;
    }

    public int countContains(){
        int count = 0;
        for (Tile tile : tiles) {
            if (box.contains(tile)) {
                count++;
            }
        }
        return count;
    }
}
