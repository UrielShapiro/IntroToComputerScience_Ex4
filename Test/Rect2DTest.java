package Exe.Ex4.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;

class Rect2DTest {
	public static Point2D p1 = new Point2D(1,3);
	public static Point2D p2 = new Point2D(2,5);
	@Test
	void testContains() {
		Point2D p3 = new Point2D(5,8);
		Point2D p4 = new Point2D(15,3);	
		Rect2D rect1 = new Rect2D(p3,p4);
		Point2D p5 = new Point2D(11,5);	
		assertTrue(rect1.contains(p5));
		Point2D p6 = new Point2D(13,0);	
		assertFalse(rect1.contains(p6));

	}

	@Test
	void testArea() {
		Rect2D rect1 = new Rect2D(p1,p2);
		double area1 = rect1.area();
		assertEquals(area1 , 2);
	}

	@Test
	void testPerimeter() {
		Rect2D rect1 = new Rect2D(p1,p2);
		double perem1 = rect1.perimeter();
		assertEquals(perem1 , 6);

	}

	@Test
	void testMove() {
		Rect2D rect = new Rect2D(p1, p2);
		rect.move(p1);
		Point2D ans = new Point2D(2,6);
		assertEquals(ans, rect.getAllPoints()[0]);
	}


	@Test
	void testCopy() {
		Rect2D rect = new Rect2D(p1, p2);
		GeoShapeable copy = rect.copy();
		double ans = copy.area();
		assertEquals(2, ans);
	}

	@Test
	void testScale() {
		Point2D p3 = new Point2D(5,8);
		Point2D p4 = new Point2D(15,3);	
		Rect2D rect1 = new Rect2D(p3,p4);
		Point2D[] arr = rect1.getAllPoints();
		for (int i = 0; i < 500; i++) {
			assertTrue(testScale(arr[0]));
			assertTrue(testScale(arr[1]));
			assertTrue(testScale(arr[2]));
			assertTrue(testScale(arr[3]));
		}	
	}

	@Test
	void testRotate() {
		Point2D p3 = new Point2D(5,8);
		Point2D p4 = new Point2D(15,3);	
		Rect2D rect1 = new Rect2D(p3,p4);
		Point2D[] arr = rect1.getAllPoints();
		for (int i = 0; i < 500; i++) {
			assertTrue(testRotate(arr[0]));
			assertTrue(testRotate(arr[1]));
			assertTrue(testRotate(arr[2]));
			assertTrue(testRotate(arr[3]));
		}
	}

	@Test
	void testGetPoints() {
		Point2D p3 = new Point2D(5,8);
		Point2D p4 = new Point2D(15,3);	
		Rect2D rect1 = new Rect2D(p3,p4);
		Point2D[] arr = rect1.getPoints();
		assertTrue(arr[0].equals(p3));
		assertTrue(arr[1].equals(p4));

	}
	static final Point2D ORIGIN = new Point2D(0,0);
	public boolean testRotate(Point2D p)
	{
		boolean ans =false;
		Random rand = new Random();
		double deg = rand.nextDouble()*360;
		Point2D p3 = new Point2D(p);
		p.rotate(ORIGIN, deg);
		if(!p.equals(p3))
		{
			ans =true;
		}
		return ans;
	}
	public boolean testScale(Point2D p)
	{
		boolean ans =false;
		Random rand = new Random();
		Point2D p3 = new Point2D(p);
		p.scale(ORIGIN, rand.nextDouble());
		if(!p.equals(p3))
		{
			ans =true;
		}
		return ans;
	}
}
