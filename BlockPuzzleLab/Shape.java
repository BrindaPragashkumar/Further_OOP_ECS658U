package BlockPuzzleLab;

import java.util.*;


public class Shape extends ArrayList<Cell> {
    // list of cells and type enum
    RegionType type;

    public Shape(RegionType type) {
        this.type = type;
    }

    Shape(RegionType type, List<Cell> cells) {
        super(cells);
        this.type = type;
    }



}


