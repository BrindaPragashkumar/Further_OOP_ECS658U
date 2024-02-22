package Lab6;
import java.util.ArrayList;
public class TestGeometry {
    public static void main(String[] args) {
        ArrayList<Vec2d> polygonA = new ArrayList<>();
        polygonA.add(new Vec2d(0, 0));
        polygonA.add(new Vec2d(1, 0));
        polygonA.add(new Vec2d(1, 10));
        polygonA.add(new Vec2d(0, 10));
        ArrayList<Vec2d> polygonB = new ArrayList<>();
        polygonB.add(new Vec2d(-5, 5));
        polygonB.add(new Vec2d(5, 5));
        polygonB.add(new Vec2d(5, 6));
        polygonB.add(new Vec2d(-5, 6));
        ArrayList<Vec2d> polygonC = new ArrayList<>();
        polygonC.add(new Vec2d(20, 20));
        polygonC.add(new Vec2d(21, 20));
        polygonC.add(new Vec2d(21, 30));
        polygonC.add(new Vec2d(20, 30));
        System.out.println(Geometry.polygonsOverlap(polygonA, polygonB));
        System.out.println(Geometry.polygonsOverlap(polygonA, polygonC));
        System.out.println(Geometry.contains(polygonA, new Vec2d(0.5, 0.5)));
        System.out.println(Geometry.contains(polygonA, new Vec2d(1.5, 5)));
    }
}
