package Lab6;

import java.util.*;

public class ShapesExample {
    public static void printDetails(MovableShape shape) {
        System.out.println("Shape: " + shape.getClass().getSimpleName());
        System.out.println("Location: " + shape.getPosition());
        System.out.println("Area: " + shape.area());
        System.out.println("Perimeter: " + shape.perimeter());
        System.out.println();
    }

    public static void main(String[] args) {
// create some shapes, then print their details
        List<MovableShape> shapes = new ArrayList<>();
        shapes.add(new Circle(new Vec2d(100.0, 200.0), 1.0));
        shapes.add(new Rectangle(new Vec2d(200.0, 200.0), 2.0, 3.0));
        shapes.add(new Polygon(new Vec2d(300.0, 200.0),
                new ArrayList<>(List.of(
                        new Vec2d(0.0, 0.0),
                        new Vec2d(1.0, 0.0),
                        new Vec2d(0.0, 1.0)))));
        for (MovableShape shape : shapes) {
            printDetails(shape);
        }
    }
}
