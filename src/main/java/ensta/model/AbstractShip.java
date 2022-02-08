package ensta.model;

public abstract class AbstractShip {
	public static enum Orientations{NORTH, SOUTH, EAST, WEST};
	
	private Character label;
	private String shipName;
	private int size;
	private Orientations orientation;
	public AbstractShip(String name, Character label, int size, Orientations orientation)
	{
		shipName=name;
		this.label=label;
		this.size=size;
		this.orientation=orientation;
	}
	public Orientations getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientations orientation) {
		this.orientation = orientation;
	}
	public Character getLabel() {
		return label;
	}
	public String getShipName() {
		return shipName;
	}
	public int getSize() {
		return size;
	}
	
}
