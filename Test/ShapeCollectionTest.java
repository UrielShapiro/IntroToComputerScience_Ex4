package Exe.Ex4.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.Comparator;
import java.util.Random;

import org.junit.jupiter.api.Test;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Triangle2D;

class ShapeCollectionTest {

	public static Point2D RandomPoint2D()
	{
		Random rand = new Random();
		return new Point2D(rand.nextDouble()*10,rand.nextDouble()*10);
	}

	@Test
	void testGet() {
		ShapeCollection shapes = new ShapeCollection();
		Segment2D seg = new Segment2D(RandomPoint2D(),RandomPoint2D());
		GUI_Shapeable g = new GUIShape(seg, false, Color.black, 3);
		shapes.add(g);
		assertEquals(shapes.get(0).getColor(),Color.black);
		assertEquals(shapes.get(0).getTag(),3);
		assertEquals(shapes.get(0).isFilled(),false);
	}

	@Test
	void testSize() {
		ShapeCollection shapes = new ShapeCollection();
		Segment2D seg = new Segment2D(RandomPoint2D(),RandomPoint2D());
		Rect2D rect = new Rect2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
		Triangle2D tri = new Triangle2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
		GUI_Shapeable g1 = new GUIShape(seg, false, Color.black, 3);
		GUI_Shapeable g2 = new GUIShape(rect, false, Color.black, 3);
		GUI_Shapeable g3 = new GUIShape(tri, false, Color.black, 3);
		shapes.add(g1);
		shapes.add(g2);
		shapes.add(g3);
		assertEquals(shapes.size(),3);
	}

	@Test
	void testRemoveElementAt() {
		ShapeCollection shapes = new ShapeCollection();
		Segment2D seg = new Segment2D(RandomPoint2D(),RandomPoint2D());
		Rect2D rect = new Rect2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
		Triangle2D tri = new Triangle2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
		GUI_Shapeable g1 = new GUIShape(seg, false, Color.black, 3);
		GUI_Shapeable g2 = new GUIShape(rect, true, Color.black, 3);
		GUI_Shapeable g3 = new GUIShape(tri, false, Color.black, 3);
		shapes.add(g1);
		shapes.add(g2);
		shapes.add(g3);
		assertEquals(shapes.size(),3);
		shapes.removeElementAt(0);
		assertEquals(shapes.size(),2);
		assertEquals(shapes.get(0).isFilled(),g2.isFilled());
		assertEquals(shapes.get(1).isFilled(),g3.isFilled());
	}

	@Test
	void testAddAt() {
		ShapeCollection shapes = new ShapeCollection();
		Segment2D seg = new Segment2D(RandomPoint2D(),RandomPoint2D());
		Rect2D rect = new Rect2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
		Triangle2D tri = new Triangle2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
		GUI_Shapeable g2 = new GUIShape(rect, true, Color.black, 3);
		GUI_Shapeable g3 = new GUIShape(tri, false, Color.black, 3);
		shapes.add(g2);
		shapes.add(g3);
		assertEquals(shapes.size(),2);
		GUI_Shapeable g1 = new GUIShape(seg, false, Color.black, 3);
		shapes.addAt(g1, 0);
		assertEquals(shapes.size(),3);
		assertEquals(shapes.get(0).isFilled(),g1.isFilled());
		assertEquals(shapes.get(0).getColor(),g1.getColor());
	}

	@Test
	void testAdd() {
		ShapeCollection shapes = new ShapeCollection();
		Segment2D seg = new Segment2D(RandomPoint2D(),RandomPoint2D());
		Rect2D rect = new Rect2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
		Triangle2D tri = new Triangle2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
		GUI_Shapeable g1 = new GUIShape(seg, false, Color.black, 3);
		GUI_Shapeable g2 = new GUIShape(rect, true, Color.black, 3);
		GUI_Shapeable g3 = new GUIShape(tri, false, Color.black, 3);
		shapes.add(g1);
		shapes.add(g2);
		shapes.add(g3);
		assertEquals(shapes.size(),3);
	}


	@Test
	void testSort() {
		ShapeCollection shapes = new ShapeCollection();
		Segment2D seg = new Segment2D(RandomPoint2D(),RandomPoint2D());
		Rect2D rect = new Rect2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
		Triangle2D tri = new Triangle2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
		GUI_Shapeable g1 = new GUIShape(seg, false, Color.yellow, 3);
		GUI_Shapeable g2 = new GUIShape(rect, true, Color.black, 3);
		GUI_Shapeable g3 = new GUIShape(tri, false, Color.green, 3);
		shapes.add(g2);
		shapes.add(g1);
		shapes.add(g3);
		Comparator<GUI_Shapeable> Sort_By_Area = new ShapeComp(Ex4_Const.Sort_By_Area);
		assertEquals(shapes.get(0).getColor(),Color.black);
		shapes.sort(Sort_By_Area);
		assertEquals(shapes.get(0).getColor(),Color.yellow);
		Comparator<GUI_Shapeable> Sort_By_Anti_Area = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
		shapes.sort(Sort_By_Anti_Area);
		assertEquals(shapes.get(2).getColor(),Color.yellow);
	}
	@Test
	void testRemoveAll() {
		ShapeCollection shapes = new ShapeCollection();
		Segment2D seg = new Segment2D(RandomPoint2D(),RandomPoint2D());
		Rect2D rect = new Rect2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
		Triangle2D tri = new Triangle2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
		GUI_Shapeable g1 = new GUIShape(seg, false, Color.yellow, 3);
		GUI_Shapeable g2 = new GUIShape(rect, true, Color.black, 3);
		GUI_Shapeable g3 = new GUIShape(tri, false, Color.green, 3);
		shapes.add(g2);
		shapes.add(g1);
		shapes.add(g3);
		assertEquals(shapes.size(),3);
		shapes.removeAll();
		assertEquals(shapes.size(),0);
	}
	@Test
	void testGetBoundingBox() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(5,5);
		Point2D p3 = new Point2D(1,5);
		Point2D p4 = new Point2D(5,1);
		Rect2D rect1 = new Rect2D(p1,p2,p3,p4);
		Segment2D seg = new Segment2D(p1,p2);
		GUI_Shapeable g1 = new GUIShape(rect1, true, Color.black, 3);
		ShapeCollection sha = new ShapeCollection();
		sha.add(g1);
		assertEquals(sha.getBoundingBox().getAllPoints()[0],p1);
		assertEquals(sha.getBoundingBox().getAllPoints()[1],p2);
	}

	@Test
	void testToString() 
	{
		ShapeCollection shapes = new ShapeCollection();
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(5,5);
		Point2D p3 = new Point2D(1,5);
		Point2D p4 = new Point2D(5,1);
		Rect2D rect1 = new Rect2D(p1,p2,p3,p4);
		GUI_Shapeable g1 = new GUIShape(rect1, true, Color.black, 3);
		shapes.add(g1);
		assertEquals(shapes.toString(),"Rect2D,5.0,5.0,1.0,1.0,5.0,1.0,1.0,5.0");
	}
}