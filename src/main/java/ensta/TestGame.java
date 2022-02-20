package ensta;

import ensta.ai.BattleShipsAI;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.Battleship;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.model.Hit;

public class TestGame {

	public static void main(String[] args) {
		Board board = new Board("Player");
		board.print();
		
		AbstractShip ships[] = new AbstractShip[5];
		ships[0]=new Carrier();
		ships[1]=new Battleship();
		ships[2]=new Destroyer();
		ships[3]=new Submarine();
		ships[4]=new Submarine();
		
		BattleShipsAI ai = new BattleShipsAI(board, board);
		ai.putShips(ships);
		
		int destrCpt=0;
		int i=0;
		// send 15 random hits
		do
		{
			Coords coords=new Coords();
			Hit hit = ai.sendHit(coords);
			System.out.println("Coordonnées du tir envoyé: " + coords + "\n");
			if(hit!=Hit.MISS && hit!=Hit.STRIKE)
			{
				destrCpt++;
			}
			
			board.print();
			i++;
		}while(destrCpt < 5);
		System.out.println("Navires détruits: " + destrCpt);
		System.out.println("Nombre de coups: " + i);
	}

}
