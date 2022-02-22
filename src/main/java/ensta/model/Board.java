package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.model.ship.ShipState;
import ensta.util.ColorUtil;
import ensta.util.Orientation;

public class Board implements IBoard {

	private static final int DEFAULT_SIZE = 10;
	private String name;
	private int size;
	private ShipState ships[][];
	private Boolean hits[][];
	
	
	public Board(String name, int gridSize) {
		this.name=name;
		size=gridSize;
		ships=new ShipState[gridSize][gridSize];
		hits=new Boolean[gridSize][gridSize];
	}
	
	public Board(String name) {
		this.name=name;
		size=DEFAULT_SIZE;
		ships=new ShipState[DEFAULT_SIZE][DEFAULT_SIZE];
		hits=new Boolean[DEFAULT_SIZE][DEFAULT_SIZE];
	}
	
	public void print() {
		int length = ships.length;
		int height = ships[0].length;
		
		String str="   ";
		/* SHIP GRID */
		System.out.println("Navires :\n  ");
		for(char c='A' ; c < 'A'+length; c++)
		{
			str+=c + " ";
		}
		System.out.println(str);
		
		str="";
		for(int i = 1; i <= height; i++)
		{
			if(i<10)
				str+=i + "  ";
			else
			{
				str+=i + " ";
			}
			for(int j = 0; j< length; j++)
			{
				if(ships[j][i-1]!=null)
					str+=(ships[j][i-1].getShip().getLabel() + " ");
				else
				{
					str+=(". ");

				}
			}
			str+=("\n");
		}
		
		System.out.println(str);
		/* HIT GRID */
		str="   ";
		System.out.println("Frappes :\n  ");
		for(char c='A' ; c < 'A'+length; c++)
		{
			str+=(c + " ");
		}
		str+=("\n");
		for(int i = 1; i <= height; i++)
		{
			if(i<10)
				str+=i + "  ";
			else
			{
				str+=i + " "; // numbers between 10 and 99 use 2 characters, so we have to align the grid.
							  // We suppose there won't be grids bigger than 100
			}
			for(int j = 0; j< length; j++)
			{
				if(hits[j][i-1] == null)
					str+=(". ");
				else if(hits[j][i-1] == false)
					str+=(ColorUtil.colorize("X ", ColorUtil.Color.WHITE));
				else
					str+=(ColorUtil.colorize("X ", ColorUtil.Color.RED));
			}
			str+=("\n");
		}
		System.out.println(str);
	}
	
	public int getSize() {
		return size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ShipState[][] getShips() {
		return ships;
	}

	public void setShips(ShipState[][] ships) {
		this.ships = ships;
	}

	public Boolean[][] getHits() {
		return hits;
	}

	public void setHits(Boolean[][] hits) {
		this.hits = hits;
	}

	public boolean canPutShip(AbstractShip ship, Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if (o == Orientation.EAST) {
			if (coords.getX() + ship.getLength()  > this.size) {
				return false;
			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if (coords.getY() + ship.getLength() > this.size) {
				return false;
			}
			dy = 1;
		} else if (o == Orientation.NORTH) {
			if (coords.getY() + 1 - ship.getLength() < 0) {
				return false;
			}
			dy = -1;
		} else if (o == Orientation.WEST) {
			if (coords.getX() + 1 - ship.getLength() < 0) {
				return false;
			}
			dx = -1;
		}

		Coords iCoords = new Coords(coords);

		for (int i = 0; i < ship.getLength(); ++i) {
			if (this.hasShip(iCoords)) {
				return false;
			}
			iCoords.setX(iCoords.getX() + dx);
			iCoords.setY(iCoords.getY() + dy);
		}

		return true;
	}

	@Override
	public boolean putShip(AbstractShip ship, Coords coords) {
		try
		{
			if(canPutShip(ship, coords))
			{
				int dx=0;
				int dy=0;
				switch(ship.getOrientation())
				{
					case EAST:dx=1;break;
					case SOUTH:dy=1;break;
					case WEST:dx=-1;break;
					case NORTH:dy=-1;break;
					default: return false;
				}
				for(int cpt=0; cpt<ship.getLength(); cpt++)
				{
					ships[coords.getX() + dx*cpt][coords.getY() + dy*cpt ]=new ShipState(ship);
				}
				return true;
			}
		}
		catch(Exception e)
		{
			System.out.println("Couldn't put ship " + e.getMessage());
		}
		return false;
	}

	// We return a boolean. false=no ship, true = ship not sunk, null = otherwise, ship but sunk
	@Override
	public Boolean hasShip(Coords coords) {
		if(ships[coords.getX()][coords.getY()]==null)
		{
			return false;
		}
		else if(ships[coords.getX()][coords.getY()].isSunk())
		{
			return null;
		}
		else
		{
			return true;
		}
	}

	@Override
	public void setHit(boolean hit, Coords coords) {
		/*
		if(hits[coords.getX()][coords.getY()] == null)
		{
			hits[coords.getX()][coords.getY()]=hit;
		}
		else if(!hits[coords.getX()][coords.getY()])
		{
			
			hit=true;
			hits[coords.getX()][coords.getY()]=true;
		}
		else
		{
			hit=false;
		}
		*/
		hits[coords.getX()][coords.getY()]=hit;
	}

	@Override
	public Boolean getHit(Coords coords) {
		return(hits[coords.getX()][coords.getY()]);
	}

	@Override
	public Hit sendHit(Coords res) {
		if(true)
		{
			if(ships[res.getX()][res.getY()]==null)
			{
				return Hit.MISS;
			}
			else
			{
				ships[res.getX()][res.getY()].addStrike();
				if(ships[res.getX()][res.getY()].isSunk())
				{
					System.out.println(ships[res.getX()][res.getY()].getShip() + " coulé!\n");
					return Hit.fromInt(ships[res.getX()][res.getY()].getShip().getLength());
				}
				else
				{
					return Hit.STRIKE;
				}
			}
		}
		else
		{
			System.out.println("Already shot there!\n"); // On prévient quand même le joueur
			return Hit.MISS; // on retourne MISS, en considérant qu'il s'agirait, comme dans le vrai jeu, d'un joueur qui se trompe, tant pis
		}

		
	}
}
