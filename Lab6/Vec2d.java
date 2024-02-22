package Lab6;

import java.lang.Math;

public record Vec2d(double x, double y) {
    // record is the final product
    public double distance(Vec2d other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    public Vec2d add(Vec2d other) {
        return new Vec2d(x + other.x, y + other.y);
    }

    public Vec2d subtract(Vec2d other) {
        return new Vec2d(x - other.x, y - other.y);
    }

    public Vec2d multitply(double scalar) {
        return new Vec2d(x * scalar, y * scalar);
    }

    public double dot(Vec2d other) {
        return x * other.x + y * other.y;
    }

    public Vec2d prep() {
        return new Vec2d(-y, x);
    }

    public double crossProduct(Vec2d other) {
        return this.x * other.y - this.y * other.x;
    }


}




//class Average2d {
//    class Average2d {
//        public Vec2d average(ArrayList<Vec2d> points) {
//            Vec2d sum = new Vec2d (0,0);
//            for (Vec2d c : points){
//                sum = sum.add(v);
//            }
//
//            return sum.multiply(1.0/points.size());
//
//        }
//    }
//}
//
//
//class Vec2d {
//
//    private final double x;
//    private final double y;
//
//    public Vec2d(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public Vec2d add(Vec2d other) {
//        return new Vec2d(x + other.x, y + other.y);
//    }
//
//    public Vec2d multiply(double scalar) {
//        return new Vec2d(x * scalar, y * scalar);
//    }
//
//    public String toString() {
//        return String.format("(%f, %f)", x, y);
//    }
//}
//
//import java.util.ArrayList;
//
//
//class CircularList<T> extends ArrayList<T> {
//
//    @Override
//    public T get(int index) {
//        // rewrite method to return correct element
//        return super.get(0);
//    }
//}