package Controllers;

import Models.*;
import Vues.console.GameConsoleVue;
import Vues.graphiques.GameGraphicVue;
import enums.BoardType;
import enums.Difficulty;
import enums.GameMode;

/**
 * Controller pour le d�roulement d'une partie.
 * @author  Gabriel Duciel
 * @version 1.0
 * @see GameManager
 * @see GameConsoleVue
 * @see GameGraphicVue
 */
public class GameController {

	private GameManager gameManager;
	private GameConsoleVue gameManagerVue;
	private GameGraphicVue graphicVue;

	/**
	 * Cr�e une instance de la class avec comme param�tre la vue correspondante dans le cas d'une vue console
	 *
	 * @param gmv 	Vue de jeu actuelle (console)
	 */
	public GameController(GameConsoleVue gmv) {
		this.gameManagerVue = gmv;
	}

	/**
	 * Cr�e une instance de la class avec comme param�tre la vue correspondante dans le cas d'une vue console
	 *
	 * @param gv 	Vue de jeu actuelle (graphique)
	 */
	public GameController(GameGraphicVue gv) {
		this.graphicVue = gv;
	}


	/**
	 * Gets game manager.
	 *
	 * @return the game manager
	 */
	public GameManager getGameManager() {
		return gameManager;
	}

	/**
	 * Permet de trouver l'instance de la carte associ�e aux coordonn�es entr�es en param�tre
	 *
	 * @param currentPoint    Point, coordonn�es dont on souhaite avoir la carte
	 * @return l'instance de la carte correspondante
	 */
	public Card getCardFromCoordinate(Coordinate currentPoint) {
		return gameManager.getCurrentBoard().getCurrentCardsOnBoard().get(currentPoint);
	}

	/**
	 * Permet de supprimer du plateau l'instance d'une carte associ�e aux coordonn�es entr�es en param�tre
	 *
	 * @param point Point, coordonn�es dont on souhaite avoir la carte
	 */
	public void removeFromCoordinate(Coordinate point) {
		gameManager.getCurrentBoard().getCurrentCardsOnBoard().remove(point);
	}

	/**
	 * Permet d'ajouter une carte sur le plateau, indif�remment que cela soit une nouvelle carte o� une ancienne carte
	 * pour laquelle nous changeons sa position
	 *
	 * @param newCard  La nouvelle carte � ins�rer
	 * @param position Les coordonn�es trouv�es pour la ouvelle carte
	 * @param isMoving Si on est entrain de d�placer une carte, il ne faut pas changer la carte en main.
	 */
	public void addCardOnBoard(Card newCard, Coordinate position, boolean isMoving) {
		gameManager.getCurrentBoard().addCardOnBoard(newCard, position);
		if(!isMoving)
			gameManager.nextCardOnPlay();
		gameManagerVue.showBoard();
	}


	/**
	 * Trouver l'instance de la class Coordinate qui correspond au point ins�r� en param�tre
	 *
	 * @param point 	Coordonnees dont on souhaite conna�tre l'instance ad�quate
	 * @return L'instance des coordonn�es associ�es
	 */
	public Coordinate findEqualsCoordinate(Coordinate point) {
		return gameManager.getCurrentBoard().findEqualsCoordinate(point);
	}



	/**
	 * Permet de savoir si une place est prise ou non sur le plateau
	 *
	 * @param point Point pour lequel on souhaite conna�tre la disponibilit�
	 * @return True si la place est disponible, False sinon
	 */
	public boolean isPlaceAvailable(Coordinate point) {
		return this.gameManager.getCurrentBoard().isPlaceAvailable(point);
	}

	/**
	 * Permet d'alterner la position sur le plateau des deux cartes pass�es en param�tre
	 *
	 * @param position1 Les coordonn�es de la premi�re carte
	 * @param position2 Les coordonn�es de la seconde carte
	 */
	public void alternateCards(Coordinate position1, Coordinate position2) {
		Card temp = this.gameManager.getCurrentBoard().getCurrentCardsOnBoard().get(this.findEqualsCoordinate(position1) );

		this.gameManager.getCurrentBoard().getCurrentCardsOnBoard().put(this.findEqualsCoordinate(position1),
				this.gameManager.getCurrentBoard().getCurrentCardsOnBoard().get(this.findEqualsCoordinate(position2)));


		this.gameManager.getCurrentBoard().getCurrentCardsOnBoard().put(this.findEqualsCoordinate(position2), temp);
	}

	/**
	 * V�rifie que les coordonn�es entr�es en param�tre sont suffisament proches du reste du plateau pour �tre
	 * utilis�es pour y positionner une carte.
	 *
	 * @param point les coordonn�es � essayer
	 * @return True si les coordon�nes sont assez proches, False sinon
	 * @see BoardRectangular
	 * @see BoardSquare
	 * @see BoardTriangular
	 */
	public boolean isCoordinateCloseEnough(Coordinate point) {
		return this.gameManager.getCurrentBoard().isCoordinateCloseEnough(point);
	}

	/**
	 * Sets game manager.
	 *
	 * @param gamemode   the gamemode
	 * @param difficulty the difficulty
	 * @param board      the board
	 * @param nbBots     the nb bots
	 * @param nbPlayers  the nb players
	 */
	public void setGameManager(GameMode gamemode, Difficulty difficulty, BoardType board, int nbBots, int nbPlayers) {

		switch (gamemode) {
		case CHAOS:
			gameManager = new GameManagerChaos();
			break;
		case QUICK:
			gameManager = new GameManagerQuick();
			break;
		default:
			gameManager = new GameManagerClassic();
			break;
		}
		switch (difficulty) {
		case EASY : gameManager.setDifficulty(0);
			break;
		case HARD : gameManager.setDifficulty(1);
			break; 
		default: gameManager.setDifficulty(0);
			break;
		}
		
		gameManager.setBoard(board);
		gameManager.playersSetUp(nbPlayers, nbBots);

		gameManagerVue = new GameConsoleVue();
		gameManagerVue.setGmc(this);
		gameManager.addObserver(this.gameManagerVue);

		if(this.graphicVue != null) {
			gameManager.addObserver(this.graphicVue);
		}

		

		Thread t = new Thread(gameManager);
		Thread vue = new Thread(this.gameManagerVue);
		Thread vue2 = new Thread(this.graphicVue);
		t.start();
		vue.start();
		vue2.start();



		//     if(this.gameManagerVue instanceof GameConsoleVue)
		// 	     gameManager.setHasPlayed(fal);

	}
}
