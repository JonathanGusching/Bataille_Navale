package ensta.ai;
import java.util.List;

import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.Player;
import ensta.model.ship.AbstractShip;
import ensta.view.InputHelper;

public class PlayerAI extends Player {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* **
     * Attribut
     */
    private BattleShipsAI ai;

    /* **
     * Constructeur
     */
    public PlayerAI(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    @Override
    public void putShips() {
		ai.putShips(ships);
	}
    
    @Override
	public Hit sendHit(Coords coords) {
		Hit hit = ai.sendHit(coords);
		return hit;
	}
}
