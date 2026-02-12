package task2.Triangle;

import java.util.Comparator;

public class TriangleComparator implements Comparator<Triangle> {
    public int compare(Triangle a, Triangle b){
        if (a.area() > b.area()){
            return 1;
        }
        else if(a.area() < b.area()){
            return -1;
        }
        else{
            return 0;
        }
    }
}
