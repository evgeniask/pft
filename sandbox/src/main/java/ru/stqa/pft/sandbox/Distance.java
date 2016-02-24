package ru.stqa.pft.sandbox;

public class Distance {

    public static void main(String[] args) {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(-5, -1);

        System.out.println("Distance between two points with coordinates " + "(" + p1.x + ", " + p1.y + ") and " + "(" + p2.x + ", " + p2.y + ") equals to " + distance(p1, p2));
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
    }
}