package ensta.model;

import java.io.Serializable;
import java.util.Random;

public class Coords implements Serializable{
	public int x;
	public int y;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Coords(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	public Coords()
	{
		x=0;
		y=0;
	}
	public Coords(Coords coords)
	{
		x=coords.getX();
		y=coords.getY();
	}
	public void setCoords(Coords res) {
		x=res.getX();
		y=res.getY();
		
	}
	public static Coords randomCoords(int size) {
		Random rnd = new Random();
		Coords coords = new Coords();
		coords.x=rnd.nextInt(size);
		coords.y=rnd.nextInt(size);
		return coords;
	}
	public boolean isInBoard(int size) {
		return(x >= 0 && x<size && y >= 0 && y<size);
	}
	public String toString()
	{
		return "(" + x + "," + y +")";
	}
}
