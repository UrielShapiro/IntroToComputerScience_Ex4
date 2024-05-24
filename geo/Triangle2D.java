package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable
{
	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;

	public Triangle2D(Point2D p1, Point2D p2, Point2D p3) 
	{
		this._p1 = new Point2D(p1);
		this._p2 = new Point2D(p2);
		this._p3 = new Point2D(p3);
	}

	@Override
	public boolean contains(Point2D ot) 
	{
		Triangle2D tri1 = new Triangle2D(_p1,_p2,ot);
		Triangle2D tri2 = new Triangle2D(_p1,_p3,ot);
		Triangle2D tri3 = new Triangle2D(_p3,_p2,ot);
		double sumArea = tri1.area()+tri2.area()+tri3.area();
		boolean isContained = false;
		if(Math.abs(sumArea-this.area())<Ex4_Const.EPS)
		{
			isContained = true;
		}
		return isContained;
	}

	@Override
	public double area() 
	{
		double a = _p1.distance(_p2);
		double b = _p2.distance(_p3);
		double c = _p1.distance(_p3);
		double s = 0.5*(a+b+c);
		return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}

	@Override
	public double perimeter() {
		double a = _p1.distance(_p2);
		double b = _p2.distance(_p3);
		double c = _p1.distance(_p3);
		double per = a+b+c;
		return per;
	}

	@Override
	public void move(Point2D vec) {
		this._p1.move(vec);
		this._p2.move(vec);
		this._p3.move(vec);
	}

	@Override
	public GeoShapeable copy() 
	{
		return new Triangle2D(new Point2D(_p1),new Point2D(_p2),new Point2D(_p3));
	}

	@Override
	public void scale(Point2D center, double ratio) 
	{
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
		_p3.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) 
	{
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);
		_p3.rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] points = new Point2D[3];
		points[0] = _p1;
		points[1] = _p2;
		points[2] = _p3;
		return points;
	}
	public String toString()
	{
		return "Triangle2D,"+_p1.toString()+","+_p2.toString()+ ","+_p3.toString();

	}

}
