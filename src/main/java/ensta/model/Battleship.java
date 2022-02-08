package ensta.model;

public class Battleship extends AbstractShip {
	public Battleship(Orientations orientation)
	{
		super("Battleship", 'B', 4, orientation);
	}
	public Battleship()
	{
		super("Battleship", 'B', 4, Orientations.EAST);
	}
}
