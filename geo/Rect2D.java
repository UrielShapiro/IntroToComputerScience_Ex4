package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {
	private Point2D UpperRight = null;
	private Point2D LowerLeft = null;
	private Point2D UpperLeft = null;
	private Point2D LowerRight = null;

	/**
	 * Constructor for the rectangle
	 * Gets 2 Point2D points and creates a rectangle using these 2 points
	 * The constructor builds 2 additional points of the other 2 corners.
	 * @param p1
	 * @param p2
	 */
	public Rect2D(Point2D p1,Point2D p2)
	{ 
		UpperRight = new Point2D(p1);
		LowerLeft = new Point2D(p2);
		if(p1.y()<p2.y())
		{
			Point2D temp = new Point2D(p2);
			p2 = new Point2D(p1);
			p1 = new Point2D(temp);
		}
		LowerRight = new Point2D(UpperRight.x(),LowerLeft.y());
		UpperLeft = new Point2D(LowerLeft.x(),UpperRight.y());
	}
	public Rect2D(Point2D p1,Point2D p2, Point2D p3, Point2D p4)
	{
		//		UpperRight = new Point2D(p3);				//FIX for the import of your files, doesn't work on our files.
		//		LowerLeft = new Point2D(p2);
		//		LowerRight = new Point2D(p1);
		//		UpperLeft = new Point2D(p4);

		UpperRight = new Point2D(p3);
		UpperLeft = new Point2D(p2);
		LowerRight = new Point2D(p1);
		LowerLeft = new Point2D(p4);
	}

	public Rect2D(Rect2D rect) {
		Point2D[] points = rect.getPoints();
		Rect2D _rect = new Rect2D(points[0],points[1]);
	}
	/**
	 * This function checks if a given point is inside the rectangle by checking if the area of the 4 triangles that are built using the given point and the corner of the rectangle are equal to the area of the rectangle.
	 * @return true if the the area of the triangles is not higher/lower than epsilon from the rectangle area, and false if that condition does not meet.
	 */
	@Override
	public boolean contains(Point2D ot)
	{
		Triangle2D tri1 = new Triangle2D(UpperRight,LowerRight,ot);
		double tri1Area = tri1.area();
		Triangle2D tri2 = new Triangle2D(UpperLeft,LowerLeft,ot);
		double tri2Area = tri2.area();
		Triangle2D tri3 = new Triangle2D(UpperRight,UpperLeft,ot);
		double tri3Area = tri3.area();
		Triangle2D tri4 = new Triangle2D(LowerLeft,LowerRight,ot);
		double tri4Area = tri4.area();
		double ans = tri1Area + tri2Area + tri3Area + tri4Area;
		return Math.abs(this.area()-ans)<Ex4_Const.EPS;
	}
	/**
	 * This function returns the center point of a given rectangle.
	 * is used to draw the rectangle
	 * @param Rect2D rectangle
	 * @return Point2D center
	 */
	public Point2D getCenter(Rect2D rect)
	{
		Point2D[] points = rect.getPoints();
		double x_center = (points[0].x()+points[1].x())/2;
		double y_center = (points[0].y()+points[1].y())/2;
		return new Point2D(x_center,y_center);
	}
	/**
	 * This function computes the area of the rectangle by multiplying one side with the other (the sides that are not equal)
	 * @return The area of the rectangle
	 */
	@Override
	public double area() 
	{
		double dy = Math.abs(UpperRight.y()-LowerLeft.y());
		double dx = Math.abs(UpperRight.x()-LowerLeft.x());
		return dy*dx;
	}

	@Override
	public double perimeter() {
		double dy = Math.abs(UpperRight.y()-LowerLeft.y());
		double dx = Math.abs(UpperRight.x()-LowerLeft.x());
		return 2*dy + 2*dx;
	}
	@Override
	public void move(Point2D vec) 
	{
		UpperRight.move(vec);
		LowerRight.move(vec);
		UpperLeft.move(vec);
		LowerLeft.move(vec);
	}
	@Override
	public GeoShapeable copy() {
		return new Rect2D(new Point2D(UpperRight),new Point2D(LowerLeft),new Point2D(UpperLeft),new Point2D(LowerRight)); 
	}

	@Override
	public void scale(Point2D center, double ratio) 
	{
		UpperRight.scale(center, ratio);
		LowerLeft.scale(center, ratio);
		UpperLeft.scale(center, ratio);
		LowerRight.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) 
	{
		UpperRight.rotate(center, angleDegrees);
		LowerLeft.rotate(center, angleDegrees);
		UpperLeft.rotate(center, angleDegrees);
		LowerRight.rotate(center, angleDegrees);
	}
	@Override
	public Point2D[] getPoints() 
	{
		Point2D[] arr = new Point2D[2];
		arr[0] = new Point2D(UpperRight);
		arr[1] = new Point2D(LowerLeft);
		return arr;
	}
	public Point2D[] getAllPoints()
	{
		Point2D[] points_arr = new Point2D[4];
		points_arr[0] = new Point2D(UpperRight);
		points_arr[1] = new Point2D(LowerLeft);
		points_arr[2] = new Point2D(UpperLeft);
		points_arr[3] = new Point2D(LowerRight);
		return points_arr;	
	}
	public String toString()
	{
		String ans = "Rect2D,";
		Point2D[] arr = new Point2D[4];
		arr = this.getAllPoints();
		for (int i = 0; i < arr.length-1; i++) 
		{
			ans += arr[i].toString()+",";
		}
		ans += arr[3].toString();
		return ans;
	}
}
