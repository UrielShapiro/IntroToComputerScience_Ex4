package Exe.Ex4.Test;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.geo.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Polygon2DTest 
{
	public static final Point2D p1 = new Point2D(3,4);
	public static final Point2D p2 = new Point2D(6,8);
	public static final Point2D p3 = new Point2D(9,2);
	public static final Point2D p4 = new Point2D(6,8);
	public static final Point2D p5 = new Point2D(6,8);
	public static ArrayList<Point2D> points = new ArrayList<Point2D>() ;

	@Test
	void testArea() {		
		Random ran = new Random();
		for (int i = 0; i < 500; i++) {
			Point2D p12 = new Point2D(ran.nextDouble(),ran.nextDouble());
			Point2D p13 = new Point2D(ran.nextDouble(),ran.nextDouble());
			Point2D p14 = new Point2D(ran.nextDouble(),ran.nextDouble()); 
			points.clear();
			Triangle2D tri = new Triangle2D(p12,p13,p14);
			double ans = tri.area();
			points.clear();
			points.add(p12);
			points.add(p13);
			points.add(p14);
			Polygon2D pol = new Polygon2D(points);
			double ans1 = pol.area();
			assertEquals(ans, ans1, Ex4_Const.EPS);
		}
	}

	@Test
	void testPerimeter() 
	{
		Triangle2D tri = new Triangle2D(p1,p2,p3);
		Rect2D rect = new Rect2D(p1,p4);
		Segment2D seg = new Segment2D(p1,p2);
		ArrayList<Point2D> points = new ArrayList<>();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		//points.add(p4);

		Polygon2D poly = new Polygon2D(points);
		assertEquals(tri.perimeter(),poly.perimeter(), Ex4_Const.EPS);
	}

	@Test
	void testMove() {
		Random rand = new Random();
		for (int i = 0; i < rand.nextInt(5, 500); i++) {
			points.clear();
			points.add(p1);
			points.add(p2);
			points.add(p3);
			Polygon2D pol = new Polygon2D(points);
			GeoShapeable poly1 = pol.copy();
			assertEquals(poly1.area(),pol.area(), Ex4_Const.EPS);
			Point2D move = new Point2D(rand.nextInt(),rand.nextInt());
			poly1.move(move);
			Point2D[] arr1 = poly1.getPoints();
			Point2D[] arr2 = pol.getPoints();
			for (int j = 0; j < arr2.length; j++) {
				assertFalse(arr1[j]==arr2[j]);
			}

		}
	}

	@Test
	void testCopy() {
		Random rand = new Random();
		for (int i = 0; i < rand.nextInt(5, 500); i++) {
			points.clear();
			points.add(p1);
			points.add(p2);
			points.add(p3);
			Polygon2D pol = new Polygon2D(points);
			GeoShapeable poly1 = pol.copy();
			assertEquals(poly1.area(),pol.area(), Ex4_Const.EPS);
			Point2D scaleP = new Point2D(rand.nextInt(),rand.nextInt());
			poly1.scale(scaleP,rand.nextDouble());
			assertNotEquals(poly1.area(),pol.area(), Ex4_Const.EPS2);
		}
	}

	@Test
	void testScale() {
		Random rand = new Random();
		for (int i = 0; i < rand.nextInt(5, 500); i++) {
			points.clear();
			points.add(p1);
			points.add(p2);
			points.add(p3);
			Polygon2D pol = new Polygon2D(points);
			GeoShapeable poly1 = pol.copy();
			assertEquals(poly1.area(),pol.area(), Ex4_Const.EPS);
			poly1.scale(p1,rand.nextDouble());
			assertNotEquals(poly1.area(),pol.area(), Ex4_Const.EPS2);
		}
	}

	@Test
	void testRotate() {
		Random rand = new Random();
		for (int i = 0; i < rand.nextInt(5, 500); i++) {
			points.clear();
			points.add(p1);
			points.add(p2);
			points.add(p3);
			Polygon2D pol = new Polygon2D(points);
			GeoShapeable poly1 = pol.copy();
			assertEquals(poly1.area(),pol.area(), Ex4_Const.EPS);
			Point2D move = new Point2D(rand.nextInt(),rand.nextInt());
			poly1.rotate(move,rand.nextDouble()*360);
			Point2D[] arr1 = poly1.getPoints();
			Point2D[] arr2 = pol.getPoints();
			for (int j = 0; j < arr2.length; j++) {
				assertFalse(arr1[j]==arr2[j]);
			}
		}
	}

	@Test
	void testGetPoints() {
		points.clear();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		Polygon2D pol = new Polygon2D(points);
		GeoShapeable poly1 = pol.copy();
		Point2D[] arr1 = poly1.getPoints();
		Point2D[] arr2 = pol.getPoints();
		for (int j = 0; j < arr2.length; j++) {
			assertTrue(arr1[j].x()==arr2[j].x());
			assertTrue(arr1[j].y()==arr2[j].y());
		}	
	}

	@Test
	void testAddPoint() {
		points.clear();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		Polygon2D pol = new Polygon2D(points);
		assertEquals(pol.getPoints().length,3);
		Point2D p = new Point2D(7,7);
		pol.addPoint(p);
		assertEquals(pol.getPoints().length,4);
	}

	@Test
	void testGetX() {
		points.clear();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		Polygon2D pol = new Polygon2D(points);
		double[] x = pol.getX();
		for (int i = 0; i < x.length; i++) {
			assertEquals(x[i], points.get(i).x(),Ex4_Const.EPS2);
		}
	}

	@Test
	void testGetY() {
		points.clear();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		Polygon2D pol = new Polygon2D(points);
		double[] y = pol.getY();
		for (int i = 0; i < y.length; i++) {
			assertEquals(y[i], points.get(i).y(),Ex4_Const.EPS2);
		}
	}

	@Test
	void testToString() {
		points.clear();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		Polygon2D pol = new Polygon2D(points);
		assertEquals(pol.toString(),"Polygon2D,3.0,4.0,6.0,8.0,9.0,2.0");
	}

}
