package Exe.Ex4.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.geo.Point2D;

class Point2DTest {
	static final Point2D p1 = new Point2D(1,3);
	static final Point2D p2 = new Point2D(1.5,4.3);
	static final Point2D ORIGIN = new Point2D(0,0);
	@Test
	void testPoint2DString() {
		assertEquals(p1.toString(),"1.0,3.0");
	}

	@Test
	void testX() {
		assertEquals(p1.x(),1.0);
	}

	@Test
	void testY() {
		assertEquals(p1.y(),3.0);
	}

	@Test
	void testIx() {
		assertEquals(p2.ix(),1);
	}

	@Test
	void testIy() {
		assertEquals(p2.iy(),4);
	}

	@Test
	void testAdd() {
		Point2D a = new Point2D(1,1);
		Point2D b = p1.add(a);
		Point2D c = new Point2D(2,4);
		assertEquals(b.x(),c.x());
		assertEquals(b.y(),c.y());
	}

	@Test
	void testToString() {
		assertEquals(p1.toString(),"1.0,3.0");
		assertEquals(p2.toString(),"1.5,4.3");
	}

	@Test
	void testDistance() {
		assertEquals(Math.sqrt(10),p1.distance());
		assertEquals(Math.sqrt(20.74),p2.distance());


	}

	@Test
	void testDistancePoint2D() {
		assertEquals(Math.sqrt(1.9399999999999995),p1.distance(p2));
	}

	@Test
	void testEqualsObject() {
		Point2D p = null;
		Point2D p3 = new Point2D(1.5,4.3);
		Point2D p4 = new Point2D(0,4.3);
		Object p5 = new Object();
		assertFalse(p1.equals(p));
		assertFalse(p1.equals(p2));
		assertTrue(p2.equals(p3));
		assertFalse(p2.equals(p4));
		assertFalse(p1.equals(p5));
	}
	@Test
	void testClose2equalsPoint2D() {
		Point2D p3 = new Point2D(p2.x()-Ex4_Const.EPS2,p2.y());
		Point2D p4 = new Point2D(p2.x(),p2.y()-Ex4_Const.EPS2*Ex4_Const.EPS);
		Point2D p5 = new Point2D(p2.x()-Ex4_Const.EPS2*0.1,p2.y()-Ex4_Const.EPS2*0.1);
		Point2D p6 = new Point2D(p2.x()+1,p2.y()+1);
		assertTrue(p3.close2equals(p2));	
		assertTrue(p4.close2equals(p2));
		assertTrue(p5.close2equals(p2));
		assertFalse(p6.close2equals(p2));
		Random rand = new Random();
		for (int i = 0; i < 500; i++) 
		{
			boolean lowerthanEPS = false;
			float r = rand.nextFloat();
			Point2D p = new Point2D(p1.x()-r,p2.x()-r);
			Point2D q = new Point2D(p1.x()+r,p2.x()+r);

			if(r<Ex4_Const.EPS)
			{
				lowerthanEPS=true;
				assertTrue(p1.close2equals(q));
				assertTrue(p1.close2equals(p));
			}
			else
			{
				assertFalse(p1.close2equals(q));
				assertFalse(p1.close2equals(p));
			}
		}	
	}

	@Test
	void testVector() {
		Point2D test1 = ORIGIN.vector(p1);
		Point2D test2 = p1.vector(p2);
		Point2D vec2 = new Point2D(1.5-1,4.3-3);
		assertTrue(p1.equals(test1));
		assertTrue(test2.equals(vec2));
	}

	@Test
	void testMove() {
		Random rand = new Random();
		for (int i = 0; i < 500; i++) {
			float f = rand.nextFloat();
			Point2D vec = new Point2D(f,f);
			Point2D p3 = new Point2D(3,3);
			Point2D p4 = new Point2D(p3);
			p3.move(vec);
			if(f<Ex4_Const.EPS)
			{
				assertTrue(p3.close2equals(p4));
			}
			else
			{
				assertFalse(p3.close2equals(p4));
			}
		}
	}

	@Test
	void testScale() {
		Random rand = new Random();
		for (int i = 0; i < 5000; i++) 
		{
			Point2D p = new Point2D(rand.nextInt(),rand.nextInt());
			double ratio_minus = rand.nextDouble();
			double ratio_plus = rand.nextDouble()+1;
			Point2D p3 = new Point2D(p);
			Point2D p4 = new Point2D(p);
			assertTrue(p.equals(p3));
			assertTrue(p.equals(p4));
			p.scale(ORIGIN, ratio_minus);
			assertFalse(p.equals(p3));
			p.scale(ORIGIN, ratio_plus);
			assertFalse(p.equals(p4));
		}
	}

	@Test
	void testRotate() {
		Random rand = new Random();
		for (int i = 0; i < 5000; i++) 
		{
			Point2D p = new Point2D(rand.nextInt(),rand.nextInt());
			double deg = rand.nextDouble()*360;
			Point2D p3 = new Point2D(p);
			assertTrue(p.equals(p3));
			p.rotate(ORIGIN, deg);
			assertFalse(p.equals(p3));
		}
	}
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
}
