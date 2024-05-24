package Exe.Ex4.geo;

import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * 
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable
{
	private ArrayList<Point2D> _points;

	public Polygon2D(ArrayList<Point2D> p) 
	{
		_points = (ArrayList<Point2D>) p.clone();
	}
	public Polygon2D() 
	{
		_points = new ArrayList<>();
	}
	@Override
	public boolean contains(Point2D ot) 
	{
		int intersects = 0;
		for (int i = 0; i < _points.size()-1; i++) 
		{
			Point2D p1 = new Point2D(_points.get(i));
			Point2D p2 = new Point2D(_points.get(i+1));
			if(Line2D.linesIntersect(p1.x(), p1.y(), p2.x(), p2.y(), ot.x(), ot.y(), ot.x(), 0))
			{
				intersects++;
			}
		}
		Point2D p1 = new Point2D(_points.get(0));
		Point2D p2 = new Point2D(_points.get(_points.size()-1));
		if(Line2D.linesIntersect(p1.x(), p1.y(), p2.x(), p2.y(), ot.x(), ot.y(), ot.x(), 0))
		{
			intersects++;
		}
		if(intersects % 2 == 0)
		{
			return false;
		}
		return true;
	}

	@Override
	public double area() 
	{
		double sum = 0;
		for (int i = 0; i < _points.size()-1; i++)
		{
			sum +=_points.get(i).x()*_points.get(i+1).y() - _points.get(i+1).x()*_points.get(i).y();
		}
		sum += _points.get(_points.size()-1).x()*_points.get(0).y() - _points.get(0).x()*_points.get(_points.size()-1).y();
		return Math.abs(sum/2);
	}

	@Override
	public double perimeter() 
	{
		double sum=0;
		for (int i = 0; i < _points.size()-1; i++) 
		{
			sum += _points.get(i).distance(_points.get(i+1));
		}
		sum += _points.get(0).distance(_points.get(_points.size()-1));
		return sum;	
	}

	@Override
	public void move(Point2D vec) {
		for (int i = 0; i < _points.size(); i++) 
		{
			_points.get(i).move(vec);
		}
	}

	@Override
	public GeoShapeable copy() 
	{
		ArrayList<Point2D> new_arr = new ArrayList<>();
		for (int i = 0; i < _points.size(); i++) 
		{
			new_arr.add(new Point2D(_points.get(i)));
		}
		return new Polygon2D(new ArrayList<Point2D>(new_arr));
	}

	@Override
	public void scale(Point2D center, double ratio) {
		for (int i = 0; i < _points.size(); i++) 
		{
			_points.get(i).scale(center,ratio);
		}
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		for (int i = 0; i < _points.size(); i++) 
		{
			_points.get(i).rotate(center,angleDegrees);
		}
	} 

	@Override
	public Point2D[] getPoints()
	{
		return _points.toArray(new Point2D[0]);
	}
	public void addPoint(Point2D p)
	{
		_points.add(new Point2D(p));
	}
	public double[] getX()
	{
		double[] ans = new double[_points.size()];
		for (int i = 0; i < _points.size(); i++)
		{
			ans[i]=_points.get(i).x();
		}
		return ans;
	}
	public double[] getY()
	{
		double[] ans = new double[_points.size()];
		for (int i = 0; i < _points.size(); i++)
		{
			ans[i]=_points.get(i).y();
		}
		return ans;
	}
	public String toString()
	{
		String ans = "Polygon2D,";
		for (int i = 0; i < _points.size()-1; i++) 
		{
			ans += _points.get(i).toString()+",";
		}
		ans += _points.get(_points.size()-1).toString();
		return ans;
	}

}
