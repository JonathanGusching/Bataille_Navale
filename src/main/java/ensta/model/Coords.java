package ensta.model;

public class Coords {
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
	public Coords(Coords coords)
	{
		x=coords.getX();
		y=coords.getY();
	}
	public void setCoords(Coords res) {
		x=res.getX();
		y=res.getY();
		
	}
}
