package Lab6;

import java.util.ArrayList;

public class Polygon extends MovableShape{
    ArrayList<Vec2d> vertices ;

    public Polygon(Vec2d p, ArrayList<Vec2d> vertices){
        super(p);
        this.vertices = vertices;
    }

    public double area(){
        int n = vertices.size();
        double area = 0.0;


        for(int i = 0; i < n; i++){
            Vec2d currentVertex = vertices.get(i);
            Vec2d nextVertex =  vertices.get((i + 1) % n );
            area += currentVertex.crossProduct(nextVertex);
        }

        return area / 2.0 ;
    }

    public double perimeter(){
        int n = vertices.size();
        double perimeter = 0;


        for(int i = 0; i < n; i++){
            Vec2d currentVertex = vertices.get(i);
            Vec2d nextVertex =  vertices.get((i + 1) % n );
            perimeter += currentVertex.distance(nextVertex);
        }

        return perimeter;
    }
}



