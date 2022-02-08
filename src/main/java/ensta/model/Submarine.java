package ensta.model;

public class Submarine extends AbstractShip {
	public Submarine(Orientations orientation)
	{
		super("Submarine", 'S', 3, orientation);
	}
	public Submarine()
	{
		super("Submarine", 'S', 3, Orientations.EAST);
	}
}
