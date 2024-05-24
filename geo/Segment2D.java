package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{
	private Point2D _p1;
	private Point2D _p2;
	public static final double EPS = Ex4_Const.EPS*1000;
	
	public Segment2D(Point2D p1, Point2D p2)
	{
		this._p1 = new Point2D(p1);
		this._p2 = new Point2D(p2);
	}
	@Override
	public boolean contains(Point2D ot) {
		boolean ans = false;
		double dist = _p1.distance(_p2);
		double dist_ot1 = ot.distance(_p1);
		double dist_ot2 = ot.distance(_p2);
		if(Math.abs((dist_ot1+dist_ot2)-dist)<=EPS)
		{
			ans = true;
		}
		return ans;
	}

	@Override
	public double area() {return 0;}			//No area for a segment, as the assignment required.

	@Override
	public double perimeter() {
		return 2*_p1.distance(_p2);				//Twice the length of the segment, as the assignment required.
	}

	@Override
	public void move(Point2D vec) 
	{
		this._p1.move(vec);
		this._p2.move(vec);
	}

	@Override
	public GeoShapeable copy() 
	{
		return new Segment2D(new Point2D(_p1) , new Point2D(_p2));
	}

	@Override
	public void scale(Point2D center, double ratio) {
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees)
	{
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] arr = new Point2D[2];
		arr[0] = _p1;
		arr[1] = _p2;
		return arr;
	}
	public String toString()
	{
		return "Segment2D,"+_p1.toString()+","+_p2.toString();
	}

}