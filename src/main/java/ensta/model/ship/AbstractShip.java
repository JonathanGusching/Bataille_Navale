package ensta.model.ship;
import ensta.util.Orientation;

public abstract class AbstractShip {
	private Character label;
	private String shipName;
	private int length;
	private Orientation orientation;
	private int strikeCount;
	public AbstractShip(String name, Character label, int size, Orientation orientation)
	{
		shipName=name;
		this.label=label;
		this.length=size;
		this.orientation=orientation;
		strikeCount=0;
	}
	public Orientation getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	public void setOrientation(String orientation) {
		if(orientation.equals("NORTH") || orientation.equals("N"))
		{
			this.orientation = Orientation.NORTH;
		}
		else if(orientation.equals("SOUTH") || orientation.equals("S"))
		{
			this.orientation = Orientation.SOUTH;
		}
		else if(orientation.equals("EAST")|| orientation.equals("E"))
		{
			this.orientation = Orientation.EAST;
		}
		else if(orientation.equals("WEST")|| orientation.equals("W"))
		{
			this.orientation = Orientation.WEST;
		}
		else
		{
			System.out.println("ORIENTATION ISSUE");
		}
		
	}
	public Character getLabel() {
		return label;
	}
	public String getName() {
		return shipName;
	}
	public int getLength() {
		return length;
	}
	public int getStrikeCount()
	{
		return strikeCount;
	}
	public void addStrike()
	{
		strikeCount++;
	}
	public boolean isSunk()
	{
		return (length==strikeCount);
	}
	public int getStrike()
	{
		return strikeCount;
	}
	public String toString()
	{
		return shipName;
	}
	
}
