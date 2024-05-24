package Exe.Ex4.gui;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Triangle2D;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 *@ID1: 314779745
 *@ID2: 325723203
 */
public class Ex4 implements Ex4_GUI{
	private static  ShapeCollectionable _shapes = new ShapeCollection();
	private  GUI_Shapeable _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private  Point2D _p1 = null;
	private  Point2D _p2 = null;
	public static ArrayList<Point2D> polygon_points = new ArrayList<Point2D>();
	public static ShapeCollectionable _sh = _shapes.copy();

	private  static Ex4 _winEx4 = null;

	private Ex4() 
	{
		init(null);
	}
	public void init(ShapeCollectionable s)
	{
		if(s==null) 
		{
			_shapes = new ShapeCollection();
		}
		else 
		{
			_shapes = s.copy();
		}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		Point2D _p1 = null;
	}
	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}
	public static Ex4 getInstance()
	{
		if(_winEx4 ==null) 
		{
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}
	public void drawShapes() 
	{
		StdDraw_Ex4.clear();
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable shape = _shapes.get(i);		//Getting the shape for each cell in the array list.
			//Setting tags for each shape
			if(shape.getShape() instanceof Polygon2D)
			{
				shape.setTag(1);
			}
			if(shape.getShape() instanceof Segment2D)
			{
				shape.setTag(2);
			}
			if(shape.getShape() instanceof Rect2D)
			{
				shape.setTag(3);
			}
			if(shape.getShape() instanceof Circle2D)
			{
				shape.setTag(4);
			}
			if(shape.getShape() instanceof Triangle2D)
			{
				shape.setTag(5);
			}
			drawShape(shape);
		}
		if(_gs!=null)
		{
			drawShape(_gs);
		}
		StdDraw_Ex4.show();
	}
	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if(g.isSelected()) 
		{
			StdDraw_Ex4.setPenColor(Color.gray);
		}
		//The program will check which GUI_Shapeable is received, get its filled status
		//For each shape the program will convert it to the specified shape and draw it
		GeoShapeable gs = g.getShape();
		boolean isFill = g.isFilled();
		if(gs instanceof Polygon2D)
		{
			Polygon2D polygon = (Polygon2D)gs;
			if(isFill)
			{
				StdDraw_Ex4.filledPolygon(polygon.getX(), polygon.getY());
			}
			else 
			{
				StdDraw_Ex4.polygon(polygon.getX(), polygon.getY());
			}
		}
		if(gs instanceof Triangle2D)
		{
			Triangle2D triangle = (Triangle2D)gs;
			Point2D[] points = triangle.getPoints();
			double[] x_arr = new double[3];
			x_arr[0] = points[0].x();
			x_arr[1] = points[1].x();
			x_arr[2] = points[2].x();
			double[] y_arr = new double[3];
			y_arr[0] = points[0].y();
			y_arr[1] = points[1].y();
			y_arr[2] = points[2].y();
			if(isFill)
			{
				StdDraw_Ex4.filledPolygon(x_arr, y_arr);
			}
			else 
			{
				StdDraw_Ex4.polygon(x_arr, y_arr);
			}
		}
		if(gs instanceof Rect2D)				//if gs is a rectangle, the program will paint it as a polygon, that way the rectangle is showed correctly after rotating it.
		{
			Rect2D rect = (Rect2D)gs;

			Point2D[] points = rect.getAllPoints();
			double[] x_arr = new double[4];
			x_arr[0] = points[0].x();
			x_arr[1] = points[2].x();
			x_arr[2] = points[1].x();
			x_arr[3] = points[3].x();
			double[] y_arr = new double[4];
			y_arr[0] = points[0].y();
			y_arr[1] = points[2].y();
			y_arr[2] = points[1].y();
			y_arr[3] = points[3].y();
			if(isFill)
			{
				StdDraw_Ex4.filledPolygon(x_arr, y_arr);
			}
			else 
			{
				StdDraw_Ex4.polygon(x_arr, y_arr);
			}
		}
		if(gs instanceof Segment2D)
		{
			Segment2D seg = (Segment2D)gs;
			Point2D[] points = seg.getPoints();
			StdDraw_Ex4.line(points[0].x(), points[0].y(), points[1].x(), points[1].y());  
		}
		if(gs instanceof Circle2D)
		{
			Circle2D c = (Circle2D)gs;
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			if(isFill) 
			{
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			}
			else 
			{ 
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
	}
	private void setColor(Color c) 
	{
		for(int i=0;i<_shapes.size();i++)
		{
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) 
			{
				s.setColor(c);
			}
		}
	}
	private void setFill()
	{
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}
	public void actionPerformed(String p) 
	{
		_mode = p;
		//Select a color for the shapes.
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		
		//Select if the "fill" status of the shape
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		
		//Removes all of the shapes from the shape array, therefore they are not painted anymore.
		if(p.equals("Clear")){_shapes.removeAll();}
		//Prints all of the shapes and their points, using the toString of each shape.
		if(p.equals("Info")){ System.out.print(getInfo());}

		//Calling different types of sorts by their names.
		//The comperators will be redirected to ShapeComp program.
		if(p.equals("ByArea")){_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Area));}
		if(p.equals("ByAntiArea")){_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Area));}
		if(p.equals("ByPerimeter")){_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Perimeter));}
		if(p.equals("ByAntiPerimeter")){_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter));}
		if(p.equals("ByToString")){_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_toString));}
		if(p.equals("ByAntiToString")){_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_toString));}
		if(p.equals("ByTag")){_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Tag));}
		if(p.equals("ByAntiTag")){_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Tag));}
		//Selects all of the shapes that are drawn
		if(p.equals("All"))
		{
			for(int i=0;i<_shapes.size();i++)
			{
				_shapes.get(i).setSelected(true);
			}
		}
		//"Unselect" all of the selected shapes.
		if(p.equals("None"))
		{
			for(int i=0;i<_shapes.size();i++)
			{
				_shapes.get(i).setSelected(false);
			}
		}
		//Selects all of the shapes that were not selected. redirects to a function below.
		if(p.equals("Anti")) 
		{
			anti();
		}
		//Remove a selected shape from the array - that way it is not painted.
		if(p.equals("Remove"))
		{
			for(int i= 0; i<_shapes.size();i++)
			{
				GUI_Shapeable s = _shapes.get(i);
				if(s.isSelected())
				{
					_shapes.removeElementAt(i);
				}
			}
		}
		//Opens a directory to save the file to.
		//This function redirects to a program in the ShapeCollection program.
		if(p.equals("Save")) 
		{
			FileDialog file_choose = new FileDialog(new JFrame(), "Choose a directory", FileDialog.SAVE);
			file_choose.setVisible(true);
			String filename = file_choose.getFile();
			if (filename != null) 
			{
				String path = file_choose.getDirectory() + filename;
				_shapes.save(path);
			}
		}
		//Opens a directory and give the ability to select a file and import it.
		if(p.equals("Load")) 
		{
			FileDialog chooser = new FileDialog(new JFrame(), "Choose a text file to load", FileDialog.LOAD);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			if (filename != null) 
			{
				String path = chooser.getDirectory() + filename;
				_shapes.load(path);
			}
		}
		drawShapes();
	}
	public void mouseClicked(Point2D p) 
	{
		System.out.println("Mode: "+_mode+"  "+p);
		//Circle, Segment and Rectangle all need 2 points in order to create the shape.
		if(_mode.equals("Circle") || _mode.equals("Segment") || _mode.equals("Rect")) 
		{
			if(_gs==null)
			{
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		if(_mode.equals("Triangle"))
		{
			if(_gs==null)
			{
				_p1 = new Point2D(p);
			}
			else if(_p2 == null)
			{
				_p2 = new Point2D(p);
			}
			else 
			{
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				_p2 = null;
			}
		}
		//Adding each selected point to the ArrayList that will eventually be exported to the polygon constructor.
		if(_mode.equals("Polygon"))
		{
			if(_gs==null)
			{
				polygon_points.add(p);
				_p1 = new Point2D(p);
			}
			else
			{
				polygon_points.add(p);
			}
		}
		//Use the move function when a second click was recieved.
		//Move the Point2D to the 2nd selected dot.
		if(_mode.equals("Move"))
		{
			if(_p1==null) 
			{
				_p1 = new Point2D(p);
			}
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				move();
				_p1 = null;
			}
		}
		//Copy the shape so that we could paste it in the next click. 
		//This function creates another shape without removing the original one.
		if(_mode.equals("Copy"))
		{
			if(_p1==null) 
			{
				_p1 = new Point2D(p);
			}
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				copy();
				_p1 = null;
			}
		}
		//Select the given point.
		if(_mode.equals("Point")) 
		{
			select(p);
		}
		//Scale the given GUIShape by 90% (in reference to the clicked point)
		if(_mode.equals("Scale_90%"))
		{
			scale(p,0.9);
		}
		//Scale the given GUIShape by 110% (in reference to the clicked point)
		if(_mode.equals("Scale_110%"))
		{
			scale(p,1.1);
		}
		//Rotate the given shape with the degrees that the second clicked point created (in reference to the "1" circle when the first click is the center of the circle).
		if(_mode.equals("Rotate"))
		{
			if(_p1==null)
			{
				_p1 = new Point2D(p);
			}
			else
			{
				_p2 = new Point2D(p);
				Point2D vec = _p1.vector(_p2);
				double deg = Math.atan2(vec.y(), vec.x());
				rotate(_p1,deg);
				_p1 = null;
				_p2 = null;
			}
		}
		
		/////////////////////////// TEST BOUNDING BOX //////////////////////
//		if(_shapes.size() != 0)
//		{
//			Rect2D rect = _shapes.getBoundingBox();
//			GUIShape a = new GUIShape(rect, false, Color.green, Integer.MAX_VALUE);
//			_shapes.add(a);
//			a=null;
//		}
		////////////////////////////////////////////////////////////////////
		drawShapes();
	} 
	//If the selected point is inside the shape, the shape will be selected.
	private void select(Point2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	//Use the scale function on all of the selected shapes.
	private void scale(Point2D p, double ratio)
	{
		for(int i=0;i<_shapes.size();i++) 
		{
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected())
			{
				g.scale(p, ratio);
			}
		}
	}
	//Use the rotate function on all of the selected shapes.
	private void rotate(Point2D center,double deg)
	{
		for(int i=0;i<_shapes.size();i++) 
		{
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null)
			{
				g.rotate(center, deg);
			}
		}
	}
	//deselect all of the selected shapes and selects all of the unselected shapes.
	private void anti()
	{
		for(int i=0;i<_shapes.size();i++) 
		{
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected() && s!=null)
			{
				s.setSelected(false);
			}
			else
			{
				s.setSelected(true);
			}
		}
	}
	//Use the move function on all of the selected shapes.
	private void move() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.move(_p1);
			}
		}
	}
	//Copy the selected shape and add a copy of it to the array of shapes.
	private void copy()
	{
		int size = _shapes.size();
		for(int i=0;i<size;i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				GUI_Shapeable newShape = s.copy();
				GeoShapeable newGeoShape = newShape.getShape();
				newGeoShape.move(_p1);
				_shapes.add(newShape);
			}
		}
	}
	//MouseEvent of right click
	//Is used to stop receiving clicks for the polygon.
	public void mouseRightClicked(Point2D p)
	{
		if(_mode.equals("Polygon") && _p1 != null)
		{
			Polygon2D poly = new Polygon2D(polygon_points);
			_gs = new GUIShape(poly,_fill, _color, 0);			
			_gs.setColor(_color);
			_gs.setFilled(_fill);
			_shapes.add(_gs);
			_gs = null;
			_p1 = null;
			polygon_points.clear();
			drawShapes();
		}
	}

	public void mouseMoved(MouseEvent e) {
		if(_p1!=null) 
		{
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShapeable gs = null;
			//	System.out.println("M: "+x1+","+y1);
			Point2D p = new Point2D(x1,y1);
			if(_mode.equals("Circle"))
			{
				double r = _p1.distance(p);
				gs = new Circle2D(_p1,r);
			}
			if(_mode.equals("Segment"))
			{
				gs = new Segment2D(_p1,p);
			}
			if(_mode.equals("Rect"))
			{
				gs = new Rect2D(_p1,p);
			}
			if(_mode.equals("Triangle"))
			{
				if(_p2 == null)
				{
					gs=new Segment2D(_p1,p);
				}
				else
				{
					gs = new Triangle2D(_p1,p,_p2);
				}
			}
			if(_mode.equals("Polygon"))
			{
				Polygon2D poly = new Polygon2D(polygon_points);
				poly.addPoint(p);
				gs = poly;
			}
			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
	}

	@Override
	public ShapeCollectionable getShape_Collection() {
		return this._shapes;
	}
	@Override
	public void show() 
	{
		show(Ex4_Const.DIM_SIZE);
	}
	@Override
	public String getInfo() 
	{
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
}
