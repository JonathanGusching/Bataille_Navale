package ensta.model.ship;
import ensta.util.Orientation;

public abstract class AbstractShip {
	private Character label;
	private String shipName;
	private int length;
	private Orientation orientation;
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
	public Character getLabel() {
		return label;
	}
	public String getShipName() {
		return shipName;
	}
	public int getLength() {
		return length;
	}
	
}
