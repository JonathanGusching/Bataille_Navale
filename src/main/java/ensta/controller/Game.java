package ensta.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ensta.ai.PlayerAI;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.Player;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.Battleship;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.ColorUtil;

public class Game {

	/*
	 * *** Constante
	 */
	public static final File SAVE_FILE = new File("savegame.dat");

	/*
	 * *** Attributs
	 */
	private Player player1;
	private Player player2;
	private Scanner sin;
	private Boolean singlePlayer;

	/*
	 * *** Constructeurs
	 */
	public Game() {
	}

	public Game init(boolean singlePlayer) {
		if (!loadSave()) {
			sin = new Scanner(System.in);
			List<AbstractShip> ships = createDefaultShips();
			List<AbstractShip> aiShips = createDefaultShips();
			
			Board playerBoard = new Board("Player Board");
			Board aiBoard = new Board("AI Board");

			player1 = new Player(playerBoard, aiBoard, ships);
			// The boolean allows the player to choose single vs multiplayer
			this.singlePlayer=singlePlayer;
			if(singlePlayer)
				player2 = new PlayerAI(aiBoard, playerBoard, aiShips);
			else
			{
				player2 = new Player(aiBoard, playerBoard, aiShips);

			}
			player1.putShips();
			player2.putShips();
		}
		return this;
	}

	/*
	 * *** Méthodes
	 */
	public void run() {
		Coords coords = new Coords();
		Board b1 = player1.getBoard();
		Board b2 = player2.getBoard(); // multiplayer
		Hit hit;

		// main loop
		b1.print();
		boolean done;
		boolean strike;
		do {
			save(); // On sauvegarde avant que le joueur 1 joue son tour actuel pour éviter de skip le tour du joueur 2
			
			System.out.println("===Player 1===");
			b1.print(); // On affiche la grille avant de jouer
			hit=player1.sendHit(coords);
			strike = (hit != Hit.MISS) && (hit!= Hit.ALREADY_SHOT); // TODO set this hit on his board (b1)
			
			if(hit!=Hit.ALREADY_SHOT)
			{
				b1.setHit(strike, coords);
			}
			done = updateScore();
			System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));


			if (!done && !strike) {
				do {
					if(!singlePlayer)
					{
						System.out.println("===Player 2===");
						b2.print();
					}
					
					hit=player2.sendHit(coords);

					strike = hit != Hit.MISS && hit!= Hit.ALREADY_SHOT;
					
					//il ne faut pas que le joueur 2 puisse voir 1
					if (singlePlayer && strike) {
						b1.print();
					}
					
					System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
					done = updateScore();
					if(!singlePlayer)
					{
						if(hit!=Hit.ALREADY_SHOT)
							b2.setHit(strike, coords);
						b2.print();
					}

					//if (!done) {
						//save();
					//}
				} while (strike && !done);
			}

		} while (!done);

		SAVE_FILE.delete();
		System.out.println(String.format("joueur %d gagne", player1.isLose() ? 2 : 1));
		if(sin!=null)
		{
			sin.close();
		}
	}

	private void save() {
		try {
			if (!SAVE_FILE.exists()) {
				SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
			}

			ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(SAVE_FILE)) ;
			 // sérialization de l'objet
			oos.writeObject(singlePlayer);
			oos.writeObject(player1) ;
			oos.writeObject(player2) ;
			oos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean loadSave() {
		if (SAVE_FILE.exists()) {
				ObjectInputStream ois;
				try {
					ois = new ObjectInputStream(new FileInputStream(SAVE_FILE));
					singlePlayer=(Boolean)ois.readObject();
					player1 = (Player)ois.readObject() ;
					if(singlePlayer)
						player2 = (PlayerAI)ois.readObject();
					else
						player2 = (Player)ois.readObject();
					ois.close();
					
					return true;
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
					System.out.println("Could not open savefile, maybe it was empty?");
				}
		}
		return false;
	}

	private boolean updateScore() {
		for (Player player : new Player[] { player1, player2 }) {
			int destroyed = 0;
			for (AbstractShip ship : player.getShips()) {
				if (ship.isSunk()) {
					destroyed++;
				}
			}

			player.setDestroyedCount(destroyed);
			player.setLose(destroyed == player.getShips().length);
			if (player.isLose()) {
				return true;
			}
		}
		return false;
	}

	private String makeHitMessage(boolean incoming, Coords coords, Hit hit) {
		String msg;
		ColorUtil.Color color = ColorUtil.Color.RESET;
		switch (hit) {
		case MISS:
			msg = hit.toString();
			break;
		case STRIKE:
			msg = hit.toString();
			color = ColorUtil.Color.RED;
			break;
		case ALREADY_SHOT:
			msg=hit.toString();
			break;
		default:
			msg = hit.toString() + " coulé";
			color = ColorUtil.Color.RED;
		}
		msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>", ((char) ('A' + coords.getX())),
				(coords.getY() + 1), msg);
		return ColorUtil.colorize(msg, color);
	}

	private static List<AbstractShip> createDefaultShips() {
		return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new Battleship(),
				new Carrier() });
	}
}
