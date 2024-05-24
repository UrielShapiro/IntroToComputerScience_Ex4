package Exe.Ex4.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.gui.Ex4;

class Segment2DTest {
	public static Point2D p1 = new Point2D(2,3);
	public static Point2D p2 = new Point2D(2,5);

	@Test
	void testContains() {
		Random r = new Random();
		for (int i = 0; i < r.nextInt(50, 1000); i++) {
			Point2D p1 = new Point2D(r.nextInt(),r.nextInt());
			Point2D p2 = new Point2D(r.nextInt(),r.nextInt());
			Segment2D seg = new Segment2D(p1,p2);
			Point2D p = new Point2D(r.nextInt(),r.nextInt());
			boolean ans = false;
			double dist = p.distance(p1)+p.distance(p2);
			if(Math.abs(dist - p1.distance(p2)) < Ex4_Const.EPS)
			{
				ans = true;
				assertTrue(ans);
			}
			else
			{
				assertFalse(ans);
			}
		}	
	}

	@Test
	void testArea() {
		Segment2D seg1 = new Segment2D(p1,p2);
		double area1 = seg1.area();
		assertEquals(0,area1,Ex4_Const.EPS2);
	}

	@Test
	void testPerimeter() {
		Segment2D seg1 = new Segment2D(p1,p2);
		double ans1 = seg1.perimeter();
		assertEquals(4,ans1,Ex4_Const.EPS2);

	}

	@Test
	void testMove() {
		Point2D p3 = new Point2D(5,8);
		Point2D p4 = new Point2D(15,3);	
		Segment2D seg = new Segment2D(p3,p4);
		Point2D[] arr = seg.getPoints();
		for (int i = 0; i < 500; i++) {
			assertTrue(testMove(arr[0]));
			assertTrue(testMove(arr[1]));
		}		
	}

	@Test
	void testCopy() {
		Random rand = new Random();
		Point2D p3 = new Point2D(5,8);
		Point2D p4 = new Point2D(15,3);	
		Segment2D seg = new Segment2D(p3,p4);
		for (int i = 0; i < rand.nextInt(5, 500); i++) {
			GeoShapeable copyseg = (Segment2D) seg.copy();
			Point2D scale = new Point2D(rand.nextInt(),rand.nextInt());
			assertEquals(copyseg.area(),seg.area(), Ex4_Const.EPS);
			copyseg.scale(scale, rand.nextDouble());
			assertNotEquals(copyseg.perimeter(),seg.perimeter(), Ex4_Const.EPS2);
		}

	}

	@Test
	void testScale() {
		Point2D p3 = new Point2D(5,8);
		Point2D p4 = new Point2D(15,3);	
		Segment2D seg = new Segment2D(p3,p4);
		Point2D[] arr = seg.getPoints();
		for (int i = 0; i < 500; i++) {
			assertTrue(testScale(arr[0]));
			assertTrue(testScale(arr[1]));
		}	
	}

	@Test
	void testRotate() {
		Point2D p3 = new Point2D(5,8);
		Point2D p4 = new Point2D(15,3);	
		Segment2D seg = new Segment2D(p3,p4);
		Point2D[] arr = seg.getPoints();
		for (int i = 0; i < 500; i++) {
			assertTrue(testRotate(arr[0]));
			assertTrue(testRotate(arr[1]));
		}		
	}

	@Test
	void testGetPoints() {
		Random r = new Random();
		for (int i = 0; i < r.nextInt(50, 1000); i++) {
			Point2D p1 = new Point2D(r.nextInt(),r.nextInt());
			Point2D p2 = new Point2D(r.nextInt(),r.nextInt());
			Segment2D seg = new Segment2D(p1,p2);
			Point2D[] arr = seg.getPoints();
			assertTrue(arr[0].equals(p1));
			assertTrue(arr[1].equals(p2));
		}
	}

	@Test
	void testToString() 
	{
		Segment2D seg1 = new Segment2D(p1,p2);
		assertEquals(seg1.toString(),"Segment2D,2.0,3.0,2.0,5.0");
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

