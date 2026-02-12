package task2;

import task2.Triangle.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var Triangles = new ArrayList<Triangle>();
        Triangles.add(new Triangle(3, 4, 5));
        Triangles.add(new IsoscelesTriangle(5, 5, 6));
        Triangles.add(new IsoscelesTriangle(5, 5, 7));
        Triangles.add(new Triangle(5, 3, 5));
        Triangles.add(new Triangle(3, 4, 5));
        Triangles.sort(new TriangleComparator());

        for(Triangle t : Triangles){
            System.out.println(t.toString());
        }
    }
}

