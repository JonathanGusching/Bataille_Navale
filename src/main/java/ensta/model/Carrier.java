package ensta.model;

public class Carrier extends AbstractShip {
	public Carrier(Orientations orientation)
	{
		super("Carrier", 'C', 5, orientation);
	}
	public Carrier()
	{
		super("Carrier", 'C', 5, Orientations.EAST);
	}
}
