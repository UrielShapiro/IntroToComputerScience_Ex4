package Exe.Ex4.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import Exe.Ex4.*;
import Exe.Ex4.geo.*;
import Exe.Ex4.gui.Ex4;

import org.junit.jupiter.api.Test;

class Ex4Test {


	////////////////////////////RANDOMS THAT ARE USED FOR TESTS////////////////////////////////////////////
	public static Point2D RandomPoint2D()
	{
		Random rand = new Random();
		return new Point2D(rand.nextDouble()*rand.nextInt(),rand.nextDouble()*rand.nextInt());
	}
	public static ShapeCollection RandomShapeCollection()
	{
		ShapeCollection shapes = new ShapeCollection();
		Random r = new Random();
		for (int i = 0; i < 1 + r.nextInt(50); i++)
		{
			Rect2D rect = new Rect2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
			Segment2D seg = new Segment2D(RandomPoint2D(),RandomPoint2D());
			Triangle2D tri = new Triangle2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
			Circle2D circle = new Circle2D(RandomPoint2D(), r.nextInt());
			GUI_Shapeable g1 = new GUIShape(rect, r.nextBoolean(), Color.black, 3);
			GUI_Shapeable g2 = new GUIShape(seg,  r.nextBoolean(), Color.black, 3);
			GUI_Shapeable g3 = new GUIShape(tri,  r.nextBoolean(), Color.black, 3);
			GUI_Shapeable g4 = new GUIShape(circle,  r.nextBoolean(), Color.black, 3);
			shapes.add(g1);
			shapes.add(g2);
			shapes.add(g3);
			shapes.add(g4);
		}
		return shapes;
	}
	public static GUI_Shapeable RandomGUI_Shapeable()
	{
		Random rand = new Random();
		switch (rand.nextInt(5))
		{
			case 0:
			{
				Rect2D rect = new Rect2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
				GUI_Shapeable g1 = new GUIShape(rect, rand.nextBoolean(), Color.black, 3);
				return g1;
			}
			case 2:
			{
				Segment2D seg = new Segment2D(RandomPoint2D(),RandomPoint2D());
				GUI_Shapeable g2 = new GUIShape(seg,  rand.nextBoolean(), Color.black, 3);
				return g2;
			}
			case 3:
			{
				Triangle2D tri = new Triangle2D(RandomPoint2D(),RandomPoint2D(),RandomPoint2D());
				GUI_Shapeable g3 = new GUIShape(tri,  rand.nextBoolean(), Color.black, 3);
				return g3;
			}
			case 1:
			{
				Circle2D circle = new Circle2D(RandomPoint2D(), rand.nextInt(1,Integer.MAX_VALUE));
				GUI_Shapeable g4 = new GUIShape(circle,  rand.nextBoolean(), Color.black, 3);
				return g4;
			}
			case 4:
				ArrayList<Point2D> arr = new ArrayList<>();
				for (int i = 0; i < rand.nextInt(5, 500); i++) {
					arr.add(RandomPoint2D());
				}
				Polygon2D poly = new Polygon2D(arr);
				GUI_Shapeable g5 = new GUIShape(poly,  rand.nextBoolean(), Color.black, 3);
				return g5;

		}
		return null;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	void testSelectAll() {
		Ex4 e = Ex4.getInstance();
		ShapeCollection s = (ShapeCollection) e.getShape_Collection();
		Random rand = new Random();
		for (int i = 0; i < rand.nextInt(1,1000); i++) {
			e.getShape_Collection().add(RandomGUI_Shapeable());
		}
		e.actionPerformed("All");
		for (int i = 0; i < s.size(); i++) {
			GUI_Shapeable g = s.get(i);
			assertTrue(g.isSelected());
		}
	}
	@Test
	void testSelectedColor() {
		Ex4 e = Ex4.getInstance();
		ShapeCollection s = (ShapeCollection) e.getShape_Collection();
		Random rand = new Random();
		for (int i = 0; i < rand.nextInt(1,1000); i++) {
			e.getShape_Collection().add(RandomGUI_Shapeable());
		}
		e.actionPerformed("All");
		e.actionPerformed("Blue");
		for (int i = 0; i < s.size(); i++) {
			GUI_Shapeable g = s.get(i);
			assertEquals(g.getColor(),Color.BLUE);
		}
	}
	@Test
	void testSelectNone() {
		Ex4 e = Ex4.getInstance();
		ShapeCollection s = (ShapeCollection) e.getShape_Collection();
		Random rand = new Random();
		for (int i = 0; i < rand.nextInt(1,1000); i++) {
			e.getShape_Collection().add(RandomGUI_Shapeable());
		}
		e.actionPerformed("None");
		for (int i = 0; i < s.size(); i++) {
			GUI_Shapeable g = s.get(i);
			assertFalse(g.isSelected());
		}
	}
	@Test
	void testSelectFill() {
		Ex4 e = Ex4.getInstance();
		ShapeCollection s = (ShapeCollection) e.getShape_Collection();
		Random rand = new Random();
		for (int i = 0; i < rand.nextInt(1,1000); i++) {
			e.getShape_Collection().add(RandomGUI_Shapeable());
		}
		e.actionPerformed("All");
		e.actionPerformed("Fill");
		for (int i = 0; i < s.size(); i++) {
			GUI_Shapeable g = s.get(i);
			assertTrue(g.isFilled());
		}
	}
	@Test
	void testSelectEmpty() {
		for (int j = 0; j < 20; j++) {
			Ex4 e = Ex4.getInstance();
			ShapeCollection s = (ShapeCollection) e.getShape_Collection();
			Random rand = new Random();
			for (int i = 0; i < rand.nextInt(1,1000); i++) {
				e.getShape_Collection().add(RandomGUI_Shapeable());
			}
			e.actionPerformed("All");
			e.actionPerformed("Empty");
			for (int i = 0; i < s.size(); i++) {
				GUI_Shapeable g = s.get(i);
				assertFalse(g.isFilled());
			}
		}
	}
	@Test
	void testSelectClear()
	{
		for (int j = 0; j < 50; j++) {
			Ex4 e = Ex4.getInstance();
			ShapeCollection s = (ShapeCollection) e.getShape_Collection();
			Random rand = new Random();
			for (int i = 0; i < rand.nextInt(1,1000); i++) {
				e.getShape_Collection().add(RandomGUI_Shapeable());
			}
			e.actionPerformed("Clear");
			assertEquals(s.size(),0);
		}
	}
	@Test
	void testSelectAnti()
	{
		for (int j = 0; j < 20; j++) {
			Ex4 e = Ex4.getInstance();
			ShapeCollection s = (ShapeCollection) e.getShape_Collection();
			Random rand = new Random();
			for (int i = 0; i < rand.nextInt(1,1000); i++) {
				e.getShape_Collection().add(RandomGUI_Shapeable());
			}
			for (int i = 0; i < s.size(); i++) {
				GUI_Shapeable g = s.get(i);
				g.setSelected(true);
			}
			for (int i = 0; i < s.size(); i=i+2)
			{
				GUI_Shapeable g = s.get(i);
				g.setSelected(false);
			}
			e.actionPerformed("Anti");
			for (int i = 0; i < s.size(); i++)
			{
				GUI_Shapeable g = s.get(i);
				if(i%2==0)
				{
					assertTrue(g.isSelected());
				}
				else if(i%2==1)
				{
					assertFalse(g.isSelected());
				}
			}
		}
	}

	@Test
	void testSelectRemove()
	{
		for (int j = 0; j < 20; j++) {
			Ex4 e = Ex4.getInstance();
			ShapeCollection s = (ShapeCollection) e.getShape_Collection();
			Random rand = new Random();
			for (int i = 0; i < rand.nextInt(1,1000); i++) {
				e.getShape_Collection().add(RandomGUI_Shapeable());
			}
			int size_before = s.size();
			for (int i = 0; i < s.size(); i++) {
				GUI_Shapeable g = s.get(i);
				g.setSelected(true);
			}
			for (int i = 0; i < s.size(); i=i+2)
			{
				GUI_Shapeable g = s.get(i);
				g.setSelected(false);
			}
			e.actionPerformed("Remove");
			assertEquals(size_before/2 ,s.size(),1);
		}
	}
	@Test
	void testSorts()
	{
		for (int j = 0; j < 10; j++) {
			Ex4 e = Ex4.getInstance();
			ShapeCollection s = (ShapeCollection) e.getShape_Collection();
			Random rand = new Random();
			for (int i = 0; i < rand.nextInt(1,1000); i++) {
				e.getShape_Collection().add(RandomGUI_Shapeable());
			}
			e.actionPerformed("ByArea");
			for (int i = 0; i < s.size()-1; i++) {
				GUI_Shapeable g1 = s.get(i);
				GUI_Shapeable g2 = s.get(i+1);
				GeoShapeable gs1 = g1.getShape();
				GeoShapeable gs2 = g2.getShape();
				assertTrue(gs1.area()<=gs2.area());
			}
			e.actionPerformed("ByAntiArea");
			for (int i = 0; i < s.size()-1; i++) {
				GUI_Shapeable g1 = s.get(i);
				GUI_Shapeable g2 = s.get(i+1);
				GeoShapeable gs1 = g1.getShape();
				GeoShapeable gs2 = g2.getShape();
				assertTrue(gs1.area()>=gs2.area());
			}
			e.actionPerformed("ByPerimeter");
			for (int i = 0; i < s.size()-1; i++) {
				GUI_Shapeable g1 = s.get(i);
				GUI_Shapeable g2 = s.get(i+1);
				GeoShapeable gs1 = g1.getShape();
				GeoShapeable gs2 = g2.getShape();
				assertTrue(gs1.perimeter()<=gs2.perimeter());
			}
			e.actionPerformed("ByAntiPerimeter");
			for (int i = 0; i < s.size()-1; i++) {
				GUI_Shapeable g1 = s.get(i);
				GUI_Shapeable g2 = s.get(i+1);
				GeoShapeable gs1 = g1.getShape();
				GeoShapeable gs2 = g2.getShape();
				assertTrue(gs1.perimeter()>=gs2.perimeter());
			}
			e.actionPerformed("ByToString");
			for (int i = 0; i < s.size()-1; i++) {
				GUI_Shapeable g1 = s.get(i);
				GUI_Shapeable g2 = s.get(i+1);
				GeoShapeable gs1 = g1.getShape();
				GeoShapeable gs2 = g2.getShape();
				assertTrue(gs1.toString().compareToIgnoreCase(gs2.toString())<0);
			}
			e.actionPerformed("ByAntiToString");
			for (int i = 0; i < s.size()-1; i++) {
				GUI_Shapeable g1 = s.get(i);
				GUI_Shapeable g2 = s.get(i+1);
				GeoShapeable gs1 = g1.getShape();
				GeoShapeable gs2 = g2.getShape();
				assertTrue(gs1.toString().compareToIgnoreCase(gs2.toString())>0);
			}
			e.actionPerformed("ByTag");
			for (int i = 0; i < s.size()-1; i++) {
				GUI_Shapeable g1 = s.get(i);
				GUI_Shapeable g2 = s.get(i+1);
				GeoShapeable gs1 = g1.getShape();
				GeoShapeable gs2 = g2.getShape();
				assertTrue(g1.getTag()<=g2.getTag());
			}
			e.actionPerformed("ByAntiTag");
			for (int i = 0; i < s.size()-1; i++) {
				GUI_Shapeable g1 = s.get(i);
				GUI_Shapeable g2 = s.get(i+1);
				GeoShapeable gs1 = g1.getShape();
				GeoShapeable gs2 = g2.getShape();
				assertTrue(g1.getTag()>=g2.getTag());
			}
		}
	}
	@Test
	void MouseRightClicked()
	{
		Ex4 e = Ex4.getInstance();
		ShapeCollection s = (ShapeCollection) e.getShape_Collection();
		s.removeAll();
		ArrayList<Point2D> arr = new ArrayList<>();
		Random r = new Random();
		e.actionPerformed("Polygon");;
		for (int i = 0; i < r.nextInt(5,500); i++) {
			e.mouseClicked(RandomPoint2D());
		}
		e.mouseRightClicked(RandomPoint2D());
		assertTrue(e.getShape_Collection().size()==1);
	}
	@Test
	void mouseClickedPolygon()
	{
		for (int j = 0; j < 50; j++) {
			Ex4 e = Ex4.getInstance();
			ShapeCollection s = (ShapeCollection) e.getShape_Collection();
			s.removeAll();
			Random r = new Random();
			e.actionPerformed("Polygon");
			int num = r.nextInt(5,500);
			for (int i = 0; i < num; i++) {
				e.mouseClicked(RandomPoint2D());
			}
			e.mouseRightClicked(RandomPoint2D());
			GUIShape poly = (GUIShape) s.get(0);
			Polygon2D poly1 = (Polygon2D) poly.getShape();
			assertEquals(poly1.getPoints().length,num);
		}
	}
}
