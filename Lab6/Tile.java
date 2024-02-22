package Lab6;
import java.awt.*;
import java.util.ArrayList;



public class Tile extends DrawablePolygon {
    public Tile(Vec2d p, ArrayList<Vec2d> vertices, Color color) {
        super(p, vertices, color);
    }

    public boolean contains(Vec2d point) {
        boolean isInside = false;
        int n = getVertices().size();

        for (int i = 0, j = n - 1; i < n; j = i++) {
            Vec2d vi = getVertices().get(i);
            Vec2d vj = getVertices().get(j);
            if ((vi.y() > point.y()) != (vj.y() > point.y()) &&
                    (point.x() < (vj.x() - vi.x()) * (point.y() - vi.y()) / (vj.y() - vi.y()) + vi.x())) {
                isInside = true;
            }
        }
        return isInside;

    }

    public boolean contains(Tile other) {
        for (Vec2d vertex : other.getVertices()) {
            if (!this.contains(vertex)) {
                return false;
            }
        }
        return true;
    }

    public boolean intersects(Tile other) {
        for (Vec2d vertex : other.getVertices()) {
            if (Geometry.polygonsOverlap(this.getVertices(), other.getVertices())) {
                return true;
            }
        }
        return false;
    }
}