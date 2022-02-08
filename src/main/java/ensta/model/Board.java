package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;

public class Board implements IBoard {

	private static final int DEFAULT_SIZE = 10;
	private String mName;
	private int size;
	private Character mShips[][];
	private boolean mHits[][];
	
	
	public Board(String name, int gridSize) {
		mName=name;
		size=gridSize;
		mShips=new Character[gridSize][gridSize];
		mHits=new boolean[gridSize][gridSize];
	}
	
	public Board(String name) {
		mName=name;
		mShips=new Character[DEFAULT_SIZE][DEFAULT_SIZE];
		mHits=new boolean[DEFAULT_SIZE][DEFAULT_SIZE];
	}
	
	public void print() {
		int length = mShips.length;
		int height = mShips[0].length;
		
		/* SHIP GRID */
		System.out.println("Navires :\n  ");
		for(char c='A' ; c < 'A'+length; c++)
		{
			System.out.println(c + " ");
		}
		System.out.println("");
		for(int i = 0; i < height; i++)
		{
			System.out.println(i + "  ");
			for(int j = 0; j< length; j++)
			{
				System.out.println(". ");
			}
		}
		/* HIT GRID */
		System.out.println("Frappes :\n  ");
		for(char c='A' ; c < 'A'+length; c++)
		{
			System.out.println(c + " ");
		}
		System.out.println("");
		for(int i = 0; i < height; i++)
		{
			System.out.println(i + "  ");
			for(int j = 0; j< length; j++)
			{
				System.out.println(". ");
			}
		}
	}
	
	public int getSize() {
		return size;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public Character[][] getShips() {
		return mShips;
	}

	public void setShips(Character[][] ships) {
		this.mShips = ships;
	}

	public boolean[][] getHits() {
		return mHits;
	}

	public void setHits(boolean[][] hits) {
		this.mHits = hits;
	}

	public boolean canPutShip(AbstractShip ship, Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if (o == Orientation.EAST) {
			if (coords.getX() + ship.getLength() >= this.size) {
				return false;
			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if (coords.getY() + ship.getLength() >= this.size) {
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
				mShips[coords.getX() + dx*cpt][coords.getY() + dy*cpt ]=ship.getLabel();
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean hasShip(Coords coords) {
		return(mShips[coords.getX()][coords.getY()] != '.');
	}

	@Override
	public void setHit(boolean hit, Coords coords) {
		if(!mHits[coords.getX()][coords.getY()])
		{
			hit=true;
			mHits[coords.getX()][coords.getY()]=true;
		}
		else
		{
			hit=false;
		}
		
	}

	@Override
	public Boolean getHit(Coords coords) {
		return(mHits[coords.getX()][coords.getY()]);
	}

	@Override
	public Hit sendHit(Coords res) {
		if(!getHit(res))
		{
			switch(mShips[res.getX()][res.getY()])
			{
				case '.':return Hit.MISS;
				default: return Hit.STRIKE;
			}
		}
		else
		{
			System.out.println("Already shot there!\n");
			return Hit.MISS;
		}
	}
}
