package Exe.Ex4.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Triangle2D;

class Triangle2DTest {
	Point2D p1 = new Point2D(9,4);
	Point2D p2 = new Point2D(14,8);
	Point2D p3 = new Point2D(2,8);
	@Test
	void testContains() {
		Point2D p1 = new Point2D(9,4);
		Point2D p2 = new Point2D(14,8);
		Point2D p3 = new Point2D(2,8);
		Triangle2D tri1 = new Triangle2D(p1,p2,p3);
		Point2D p4 = new Point2D(9,7);
		boolean ans1 = tri1.contains(p4);
		assertTrue(ans1);
	}

	@Test
	void testArea() 
	{
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(3,5);
		Point2D p3 = new Point2D(7,8);
		Triangle2D tri1 = new Triangle2D(p1,p2,p3);
		double ans1 = Math.round(tri1.area());
		assertEquals(ans1,5);
	}

	@Test
	void testPerimeter() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(3,5);
		Point2D p3 = new Point2D(7,8);
		Triangle2D tri1 = new Triangle2D(p1,p2,p3);
		double ans1 = Math.round(tri1.perimeter());
		assertEquals(ans1,19);
	}

	@Test
	void testMove() {
		Triangle2D tri = new Triangle2D(p1, p2, p3);
		tri.move(p1);	
	}

	@Test
	void testCopy() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(3,5);
		Point2D p3 = new Point2D(7,8);
		Triangle2D tri = new Triangle2D(p1,p2,p3);
		GeoShapeable ans = tri.copy();
		assertEquals(ans.area(),tri.area());
		ans.scale(p1, 0.5);
		assertNotEquals(ans.area(),tri.area());

	}

	@Test
	void testScale() {
		Point2D p3 = new Point2D(5,8);
		Point2D p4 = new Point2D(15,3);	
		Point2D p5 =new Point2D(33,56);
		Triangle2D tri = new Triangle2D(p3,p4,p5);
		Point2D[] arr = tri.getPoints();
		for (int i = 0; i < 500; i++) {
			assertTrue(testScale(arr[0]));
			assertTrue(testScale(arr[1]));
			assertTrue(testScale(arr[2]));
		}		
	}

	@Test
	void testRotate() {
		Point2D p3 = new Point2D(5,8);
		Point2D p4 = new Point2D(15,3);	
		Point2D p5 =new Point2D(33,56);
		Triangle2D tri = new Triangle2D(p3,p4,p5);
		Point2D[] arr = tri.getPoints();
		for (int i = 0; i < 500; i++) {
			assertTrue(testRotate(arr[0]));
			assertTrue(testRotate(arr[1]));
			assertTrue(testRotate(arr[2]));
		}			
	}

	@Test
	void testGetPoints() {
		Random r = new Random();
		for (int i = 0; i < r.nextInt(); i++) {
			
		
		Point2D p3 = new Point2D(r.nextInt(),r.nextInt());
		Point2D p4 = new Point2D(r.nextInt(),r.nextInt());	
		Point2D p5 =new Point2D(r.nextInt(),r.nextInt());
		Triangle2D tri = new Triangle2D(p3,p4,p5);
		Point2D[] arr = tri.getPoints();
		for (int j = 0; j < 500; j++) {
			assertTrue(arr[0].equals(p3));
			assertTrue(arr[1].equals(p4));
			assertTrue(arr[2].equals(p5));
		}	
		}
	}


	public static final Point2D ORIGIN = new Point2D(0,0);
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
	public boolean testMove(Point2D p)
	{
		boolean ans =false;
		Random rand = new Random();
		Point2D p3 = new Point2D(p);
		Point2D p5 = new Point2D(rand.nextDouble(),rand.nextDouble());
		p.move(p5);
		if(!p.equals(p3))
		{
			ans =true;
		}
		return ans;
	}
}

