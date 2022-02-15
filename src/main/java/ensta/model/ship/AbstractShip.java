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
	}
	public Orientation getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	public void setOrientation(String orientation) {
		if(orientation=="NORTH" || orientation=="N")
		{
			this.orientation = Orientation.NORTH;
		}
		else if(orientation=="SOUTH" || orientation=="S")
		{
			this.orientation = Orientation.SOUTH;
		}
		else if(orientation=="EAST"|| orientation=="E")
		{
			this.orientation = Orientation.EAST;
		}
		else if(orientation=="WEST"|| orientation=="W")
		{
			this.orientation = Orientation.WEST;
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
		return length==strikeCount;
	}
	
}
