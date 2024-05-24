package Exe.Ex4.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;
import java.util.Random;

import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;

class GUIShapeTest 
{



	@Test
	void testGetShape() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(2,3);
		Point2D p4 = new Point2D(1,3);
		Rect2D testRect = new Rect2D(p1, p2);
		GUIShape _g = new GUIShape(testRect, true, Color.blue, 5);
		GUI_Shapeable r = (GUI_Shapeable) _g;
		Point2D[] points = r.getShape().getPoints();
		Point2D[] test_points = {p1,p2};
		Segment2D testSeg = new Segment2D(p1,p2);
		_g = new GUIShape(testSeg, true, Color.blue, 5);
		r = (GUI_Shapeable) _g;
		points = r.getShape().getPoints();
		assertArrayEquals(test_points,points);

		Triangle2D testTri = new Triangle2D(p1,p2,p3);
		_g = new GUIShape(testTri, true, Color.blue, 5);
		r = (GUI_Shapeable) _g;
		points = r.getShape().getPoints();
		Point2D[] test_points1 = {p1,p2,p3};
		assertArrayEquals(test_points1,points);
	}

	@Test
	void testIsFilled() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Rect2D testRect = new Rect2D(p1, p2);
		Random rnd = new Random();
		for (int i = 0; i < 500; i++) 
		{
			boolean randombool = rnd.nextBoolean();
			GUIShape _g = new GUIShape(testRect, randombool, Color.blue, 5);
			GUI_Shapeable r = (GUI_Shapeable) _g;
			assertEquals(r.isFilled(),randombool);
		}

	}

	@Test
	void testSetFilled() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Rect2D testRect = new Rect2D(p1, p2);
		Random rnd = new Random();
		for (int i = 0; i < 500; i++) 
		{
			boolean randombool = rnd.nextBoolean();
			GUIShape _g = new GUIShape(testRect, true, Color.blue, 5);
			GUI_Shapeable r = (GUI_Shapeable) _g;
			r.setFilled(randombool);
			assertEquals(r.isFilled(),randombool);
		}
	}

	@Test
	void testGetColor() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Rect2D testRect = new Rect2D(p1, p2);
		Random rand = new Random();
		for (int i = 0; i < 500; i++) 
		{
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			Color col = new Color(r, g, b);
			GUIShape _g = new GUIShape(testRect, true, col, 5);
			GUI_Shapeable r1 = (GUI_Shapeable) _g;
			assertEquals(r1.getColor(),col);
		}
	}

	@Test
	void testSetColor() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Rect2D testRect = new Rect2D(p1, p2);
		Random rand = new Random();
		for (int i = 0; i < 500; i++) 
		{
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			Color col = new Color(r, g, b);
			GUIShape _g = new GUIShape(testRect, true, Color.black, 5);
			GUI_Shapeable r1 = (GUI_Shapeable) _g;
			r1.setColor(col);
			assertEquals(r1.getColor(),col);
		}
	}

	@Test
	void testGetTag() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Rect2D testRect = new Rect2D(p1, p2);
		Random rand = new Random();
		for (int i = 0; i < 500; i++) 
		{
			int tag = rand.nextInt();
			GUIShape _g = new GUIShape(testRect, true, Color.black, tag);
			GUI_Shapeable r1 = (GUI_Shapeable) _g;
			assertEquals(r1.getTag(),tag);
		}
	}

	@Test
	void testSetTag() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Rect2D testRect = new Rect2D(p1, p2);
		Random rand = new Random();
		for (int i = 0; i < 500; i++) 
		{
			int tag = rand.nextInt();
			GUIShape _g = new GUIShape(testRect, true, Color.black, 1);
			GUI_Shapeable r1 = (GUI_Shapeable) _g;
			r1.setTag(tag);
			assertEquals(r1.getTag(),tag);
		}
	}

	@Test
	void testCopy() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Rect2D testRect = new Rect2D(p1, p2);		
		GUIShape _g = new GUIShape(testRect, true, Color.black, 1);
		GUI_Shapeable r = (GUI_Shapeable) _g;
		GUI_Shapeable u = r.copy();
		r.setFilled(false);
		assertNotEquals(r.isFilled(), u.isFilled());
		r.setColor(Color.blue);
		assertNotEquals(r.getColor(), u.getColor());
		r.setTag(5);
		assertNotEquals(r.getTag(), u.getTag());
	}

	@Test
	void testToString() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(5,9);

		Rect2D testRect = new Rect2D(p1, p2);		
		GUIShape _g = new GUIShape(testRect, true, Color.black, 1);
		GUI_Shapeable r = (GUI_Shapeable) _g;
		assertEquals(r.toString(),("Rect2D,2.0,1.0,1.0,2.0,2.0,2.0,1.0,1.0"));
		Segment2D seg = new Segment2D(p1,p2);
		_g = new GUIShape(seg, true, Color.black, 1);
		r = (GUI_Shapeable) _g;
		assertEquals(r.toString(),("Segment2D,1.0,1.0,2.0,2.0"));
		Triangle2D tri = new Triangle2D(p1,p2,p3);
		_g = new GUIShape(tri, true, Color.black, 1);
		r = (GUI_Shapeable) _g;
		assertEquals(r.toString(),("Triangle2D,1.0,1.0,2.0,2.0,5.0,9.0"));
	}

	@Test
	void testIsSelected() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Rect2D testRect = new Rect2D(p1, p2);
		Random rnd = new Random();
		for (int i = 0; i < 500; i++) 
		{
			boolean randombool = rnd.nextBoolean();
			GUIShape _g = new GUIShape(testRect, true, Color.blue, 5);
			_g.setSelected(randombool);
			assertEquals(_g.isSelected(),randombool);
		}	
	}

	@Test
	void testSetSelected() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Rect2D testRect = new Rect2D(p1, p2);
		Random rnd = new Random();
		for (int i = 0; i < 500; i++) 
		{
			boolean randombool = rnd.nextBoolean();
			GUIShape _g = new GUIShape(testRect, true, Color.blue, 5);
			_g.setSelected(randombool);
			assertEquals(_g.isSelected(),randombool);
		}
	}

	@Test
	void testSetShape() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(2,3);
		Point2D p4 = new Point2D(1,3);
		Rect2D testRect = new Rect2D(p1, p2);
		GUIShape _g = new GUIShape(testRect, true, Color.blue, 5);
		GUI_Shapeable r =new GUIShape(_g);
		r.setShape(testRect);
		Point2D[] points = r.getShape().getPoints();
		Point2D[] test_points = {p1,p2};
		Segment2D testSeg = new Segment2D(p1,p2);
		_g = new GUIShape(testRect, true, Color.blue, 5);
		r =new GUIShape(_g);
		r.setShape(testSeg);
		points = r.getShape().getPoints();
		assertArrayEquals(test_points,points);

		Triangle2D testTri = new Triangle2D(p1,p2,p3);
		_g = new GUIShape(testRect, true, Color.blue, 5);
		r =new GUIShape(_g);
		r.setShape(testTri);
		points = r.getShape().getPoints();
		Point2D[] test_points1 = {p1,p2,p3};
		assertArrayEquals(test_points1,points);
	}

}

