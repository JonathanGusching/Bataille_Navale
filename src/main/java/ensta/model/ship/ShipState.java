package ensta.model.ship;

import java.io.Serializable;

import ensta.util.ColorUtil;

public class ShipState implements Serializable {
	private AbstractShip ship;
	private boolean struck;
	public ShipState()
	{
		ship=null;
		struck=false;
	}
	public ShipState(AbstractShip ship)
	{
		this.ship=ship;
		struck=false;
	}
	public void addStrike() 
	{
		if(!struck)
		{
			ship.addStrike();
			struck=true;
		}
		else
		{
			System.out.println("Already shot there\n");
		}
	}
	public boolean isStruck()
	{
		return struck;
	}
	public String toString() {
		if(struck)
		{
			return ColorUtil.colorize(ship.getLabel(), ColorUtil.Color.RED);
		}
		else
		{
			return ship.getLabel().toString();
		}
		
	}
	public AbstractShip getShip() {
		return ship;
	}
	public boolean isSunk()
	{
		return ship.isSunk();
	}
	public int getStrike() {
		return ship.getStrike();
	}
}
