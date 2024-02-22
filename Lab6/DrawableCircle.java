package Lab6;

import java.awt.Color;
import java.awt.Graphics2D;


public class DrawableCircle extends Circle implements Drawable {
    private Color color;

    public DrawableCircle(Vec2d p ,double radius, Color color) {
        super(p, radius);
        this.color = color;
    }

    public void draw(Graphics2D g) {
        double centerX = getPosition().x();
        double centerY = getPosition().y();
        // Get the position (vertices) and dimensions
        // Get the position (vertices) and dimensions
        double diameter = radius * 2;

        double x = centerX -(diameter /2);
        double y = centerY - (diameter / 2);

        // Set the drawing color
        g.setColor(color);
        // Draw the rectangle
        g.fillOval((int) x, (int) y, (int) diameter, (int) diameter);
    }


}
