package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistancePositiveValues() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(10, 4);
        Assert.assertEquals(Point.distance(p1, p2), 8.06225774829855);
    }

    @Test
    public void testDistanceNegativeValues() {
        Point p1 = new Point(-6, -11);
        Point p2 = new Point(-5, -1);
        Assert.assertEquals(Point.distance(p1, p2), 10.04987562112089);
    }

    @Test
    public void testDistanceZeroValue() {
        Point p1 = new Point(0, 3);
        Point p2 = new Point(-5, 0);
        Assert.assertEquals(Point.distance(p1, p2), 5.830951894845301);
    }
}
