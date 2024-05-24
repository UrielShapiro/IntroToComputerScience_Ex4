package Exe.Ex4;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;


/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable
{
	private ArrayList<GUI_Shapeable> _shapes;
	//ShapeCollection Constructors
	public ShapeCollection() 
	{
		_shapes = new ArrayList<GUI_Shapeable>();
	}
	public ShapeCollection(ArrayList<GUI_Shapeable> arr)
	{
		for (int i = 0; i < arr.size(); i++) 
		{
			_shapes.add(arr.get(i).copy());
		}
	}
	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}
	@Override
	public int size() {
		if(_shapes != null)
		{
			return _shapes.size();
		}
		else
		{
			return 0;
		}
	}
	@Override
	public GUI_Shapeable removeElementAt(int i) 
	{
		return _shapes.remove(i);
	}
	@Override
	public void addAt(GUI_Shapeable s, int i) 
	{
		try
		{
			_shapes.add(i,s);
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new IndexOutOfBoundsException("Index exceed the array bounds");
		}
	}
	@Override
	public void add(GUI_Shapeable s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public ShapeCollectionable copy() 
	{
		ArrayList<GUI_Shapeable> sh = new ArrayList<GUI_Shapeable>();
		for (int i = 0; i < _shapes.size(); i++) {
			sh.add(new GUIShape((GUIShape)_shapes.get(i)));
		}
		return new ShapeCollection(new ArrayList<GUI_Shapeable>(sh));
	}

	@Override
	public void sort(Comparator<GUI_Shapeable> comp)
	{
		_shapes.sort(comp);
	}

	@Override
	public void removeAll() 
	{
		_shapes.clear();
	}
	/**
	 * This function recieves the name of the file and write all of the shapes from the array to the file (using the toString function)
	 */
	@Override
	public void save(String file) {
		try
		{
			FileWriter fileWriter = new FileWriter(file);
			for (int i = 0; i < _shapes.size(); i++)
			{
				GUIShape gs = (GUIShape) _shapes.get(i);
				fileWriter.write((gs.toStringSave()+ "\n"));
			}
			fileWriter.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * This function receives the name of the file to import the shapes from.
	 * The function checks the name of the shape, then convert all of the strings that represent the coordinates of the points to double.
	 * In the end the function adds the shape to the shapes array.
	 */
	@Override
	public void load(String file) 
	{
		this._shapes.clear();
		File f = new File(file);
		Scanner sc;
		try {
			sc = new Scanner(f);
			while (sc.hasNext()) 
			{
				String file_line = sc.nextLine();
				String[] string_arr = file_line.split(",");
				Color col = Color.decode(string_arr[1]);
				if(string_arr[4].equals("Rect2D"))
				{	
					Point2D p1 = new Point2D(Double.valueOf(string_arr[5]),Double.valueOf(string_arr[6]));
					Point2D p2 = new Point2D(Double.valueOf(string_arr[7]),Double.valueOf(string_arr[8]));
					Point2D p3 = new Point2D(Double.valueOf(string_arr[9]),Double.valueOf(string_arr[10]));
					Point2D p4 = new Point2D(Double.valueOf(string_arr[11]),Double.valueOf(string_arr[12]));
					Rect2D rect = new Rect2D(p1,p2,p3,p4);
					GUI_Shapeable gs = new GUIShape(rect, Boolean.valueOf(string_arr[2]), col, Integer.valueOf(string_arr[3]));
					_shapes.add(gs);
				}
				if(string_arr[4].equals("Segment2D"))
				{	
					Point2D p1 = new Point2D(Double.valueOf(string_arr[5]),Double.valueOf(string_arr[6]));
					Point2D p2 = new Point2D(Double.valueOf(string_arr[7]),Double.valueOf(string_arr[8]));
					Segment2D seg = new Segment2D(p1,p2);
					GUI_Shapeable gs = new GUIShape(seg, Boolean.valueOf(string_arr[2]), col, Integer.valueOf(string_arr[3]));
					_shapes.add(gs);
				}
				if(string_arr[4].equals("Polygon2D"))
				{
					ArrayList<Point2D> points = new ArrayList<>();
					for (int i = 5; i < string_arr.length-1; i+=2) 
					{
						Point2D p = new Point2D(Double.valueOf(string_arr[i]),Double.valueOf(string_arr[i+1]));
						points.add(p);
					}
					Polygon2D poly = new Polygon2D(points);
					GUI_Shapeable gs = new GUIShape(poly, Boolean.valueOf(string_arr[2]), col, Integer.valueOf(string_arr[3]));
					_shapes.add(gs);
				}
				if(string_arr[4].equals("Triangle2D"))
				{	
					Point2D p1 = new Point2D(Double.valueOf(string_arr[5]),Double.valueOf(string_arr[6]));
					Point2D p2 = new Point2D(Double.valueOf(string_arr[7]),Double.valueOf(string_arr[8]));
					Point2D p3 = new Point2D(Double.valueOf(string_arr[9]),Double.valueOf(string_arr[10]));
					Triangle2D tri = new Triangle2D(p1,p2,p3);
					GUI_Shapeable gs = new GUIShape(tri, Boolean.valueOf(string_arr[2]), col, Integer.valueOf(string_arr[3]));
					_shapes.add(gs);
				}
				if(string_arr[4].equals("Circle2D"))
				{	
					Point2D p = new Point2D(Double.valueOf(string_arr[5]),Double.valueOf(string_arr[6]));
					double rad = Double.valueOf(string_arr[7]);
					Circle2D circle = new Circle2D(p,rad);
					GUI_Shapeable gs = new GUIShape(circle, Boolean.valueOf(string_arr[2]), col, Integer.valueOf(string_arr[3]));
					_shapes.add(gs);
				}
			}
			sc.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File Was not Found");
			e.printStackTrace();
		}
	}
	/**
	 * This function returns the rectangle that blocks all of the shapes that are drawn.
	 * The function does so by checking all of the points of each shape and compare it to the other points.
	 * The function gets the highest point (highest x and highest y) and the lowest point and returns a rectangle that is made from these points.
	 */
	@Override
	public Rect2D getBoundingBox()
	{
		if(_shapes.isEmpty())
		{
			return null;
		}
		double max_y = Double.MIN_VALUE;
		double min_y = Double.MAX_VALUE;
		double max_x = Double.MIN_VALUE;
		double min_x = Double.MAX_VALUE;
		for(GUI_Shapeable gs : _shapes)
		{
			GeoShapeable g = gs.getShape();
			if(g instanceof Circle2D)
			{
				Point2D[] p = g.getPoints();
				Circle2D circle = (Circle2D) g;
				Point2D[] points = new Point2D[4];
				points[0] = new Point2D(p[0].x()+circle.getRadius(),p[0].y());
				points[1] = new Point2D(p[0].x()-circle.getRadius(),p[0].y());
				points[2] = new Point2D(p[0].x(),p[0].y()-circle.getRadius());
				points[3] = new Point2D(p[0].x(),p[0].y()+circle.getRadius());
				for(int i = 0; i<points.length;i++)
				{
					if(points[i].y()>max_y)
					{
						max_y = points[i].y();
					}
					if(points[i].y()<min_y)
					{
						min_y = points[i].y();
					}
					if(points[i].x()>max_x)
					{
						max_x = points[i].x();
					}
					if(points[i].x()<min_x)
					{
						min_x = points[i].x();
					}
				}
			}
			else
			{
				GeoShapeable shape = gs.getShape();
				Point2D[] points = shape.getPoints();
				for(int i = 0; i<points.length;i++)
				{
					if(points[i].y()>max_y)
					{
						max_y = points[i].y();
					}
					if(points[i].y()<min_y)
					{
						min_y = points[i].y();
					}
					if(points[i].x()>max_x)
					{
						max_x = points[i].x();
					}
					if(points[i].x()<min_x)
					{
						min_x = points[i].x();
					}
				}
			}
		}
		Point2D UpperRight = new Point2D(max_x, max_y);
		Point2D LowerLeft = new Point2D(min_x, min_y);
		return new Rect2D(LowerLeft,UpperRight);
	}
	@Override
	public String toString() 
	{
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
}
