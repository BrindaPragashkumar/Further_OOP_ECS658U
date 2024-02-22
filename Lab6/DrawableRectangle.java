package Lab6;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class DrawableRectangle extends Rectangle implements Drawable {

    private Color color;

    public DrawableRectangle(Vec2d p, double width, double height, Color color) {
        super(p, width, height);
        this.color = color;
    }

    public void draw(Graphics2D g) {
        double centerX = getPosition().x();
        double centerY = getPosition().y();
        // Get the position (vertices) and dimensions
        // Get the position (vertices) and dimensions
        double recWidth = width;
        double recHeight = height;
        double x = centerX - (width / 2);
        double y = centerY - (height / 2);

        // Set the drawing color
        g.setColor(color);
        // Draw the rectangle
        g.fillRect((int) x, (int) y, (int) recWidth, (int) recHeight);

    }


}