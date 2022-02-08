package ensta.model;

public class Destroyer extends AbstractShip {
	public Destroyer(Orientations orientation)
	{
		super("Destroyer", 'D', 2, orientation);
	}
	public Destroyer()
	{
		super("Destroyer", 'D', 2, Orientations.EAST);
	}
}
