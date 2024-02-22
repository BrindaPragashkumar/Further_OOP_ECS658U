package Lab6;

import java.awt.*;
import java.util.ArrayList;


public class DrawablePolygon extends Polygon implements Drawable {
    private Color color;
    public DrawablePolygon(Vec2d p, ArrayList<Vec2d> vertices, Color color) {
        super(p, vertices);
        this.color = color;
    }
    public void draw(Graphics2D g) {
        ArrayList<Vec2d> verts = getVertices();
        int[] xPoints = new int[verts.size()];
        int[] yPoints = new int[verts.size()];
        for (int i = 0; i < verts.size(); i++) {
            xPoints[i] = (int) verts.get(i).x();
            yPoints[i] = (int) verts.get(i).y();
        }
        g.setColor(color);
        g.fillPolygon(xPoints, yPoints, verts.size());
    }


    // Use the fill to see if this works
    public ArrayList<Vec2d> getVertices() {
        ArrayList<Vec2d> points = new ArrayList<>();

        // Calculate the center of the polygon's bounding box
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;

        for (Vec2d v : vertices) {
            double x = v.x();
            double y = v.y();
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }

        double centerX = (minX + maxX) / 2.0;
        double centerY = (minY + maxY) / 2.0;

        // Subtract the center from each vertex
        for (Vec2d v : vertices) {
            double x = v.x() - centerX;
            double y = v.y() - centerY;
            points.add(new Vec2d(x, y).add(position));
        }

        return points;
    }
}
