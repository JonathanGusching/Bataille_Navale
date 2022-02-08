package ensta.model.ship;
import ensta.util.Orientation;

public class Submarine extends AbstractShip {
	public Submarine(Orientation orientation)
	{
		super("Submarine", 'S', 3, orientation);
	}
	public Submarine()
	{
		super("Submarine", 'S', 3, Orientation.EAST);
	}
}
