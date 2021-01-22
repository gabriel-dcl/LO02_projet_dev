package Controllers;

import Models.GameManager;
import Vues.Vue;
import Vues.console.GameConsoleVue;
import Vues.console.SetUpConsoleVue;
import Vues.graphiques.GameGraphicVue;
import Vues.graphiques.SetUpGraphicVue;
import enums.BoardType;
import enums.Difficulty;
import enums.GameMode;
//import jdk.jfr.internal.PrivateAccess;

/**
 * Controller pour la mise en place de la partie, lié aux Set-ups vues
 * @version 1.0
 * @author Nicolas Felixine, Gabriel Duciel
 * @see SetUpController
 * @see SetUpGraphicVue
 */
public class SetUpController {

	private int nbPlayer;
	private int nbBots ;
	private Vue currentVue;
	private Vue gameWindow;


	/**
	 * Enumerations concernant l'option choisi, utilisées par la suite pour créer le bon GameManager
	 */

	private GameManager gm;
	private GameMode choixGC;
	private Difficulty choixDif;
	private BoardType choixBoard;



	/**
	 * Crée le controller avec une configuration initiale pour gagner du temps
	 *
	 * @param currentVue Vue qui correspond au controller
	 * @see SetUpGraphicVue
	 * @see SetUpConsoleVue
	 */
	public SetUpController(Vue currentVue)
	{
		choixGC = GameMode.CLASSIC;
		choixDif = Difficulty.EASY;
		choixBoard = BoardType.RECTANGULAR;
		nbPlayer = 0;
		nbBots = 3;

		this.currentVue = currentVue;
	}

	/**
	 * Methode qui permet changer de vue (de console à graphique et vice-versa)
	 */
	public void changeVue()
	{
		if(currentVue instanceof SetUpConsoleVue)
			currentVue = new SetUpGraphicVue();
		else
		{
			currentVue = new SetUpConsoleVue();

		}
		currentVue.occure();
	}

	/**
	 * Sets choix gc.
	 *
	 * @param choixGC the choix gc
	 */
	public void setChoixGC(GameMode choixGC) {
		this.choixGC = choixGC;
	}

	/**
	 * Sets choix dif.
	 *
	 * @param choixDif the choix dif
	 */
	public void setChoixDif(Difficulty choixDif) {
		this.choixDif = choixDif;
	}

	/**
	 * Sets choix board.
	 *
	 * @param choixBoard the choix board
	 */
	public void setChoixBoard(BoardType choixBoard) {
		this.choixBoard = choixBoard;
	}

	/**
	 * Sets nb player.
	 *
	 * @param nbPlayer the nb player
	 */
	public void setNbPlayer(int nbPlayer) {
		this.nbPlayer = nbPlayer;
	}

	/**
	 * Sets nb bots.
	 *
	 * @param nbBots the nb bots
	 */
	public void setNbBots(int nbBots) {
		this.nbBots = nbBots;
	}

	/**
	 * Gets gm.
	 *
	 * @return the gm
	 */
	public GameManager getGm() {
		return gm;
	}

	/**
	 * Sets gm.
	 *
	 * @param gm the gm
	 */
	public void setGm(GameManager gm) {
		this.gm = gm;
	}

	/**
	 * Gets choix gc.
	 *
	 * @return the choix gc
	 */
	public GameMode getChoixGC() {
		return choixGC;
	}

	/**
	 * Gets choix dif.
	 *
	 * @return the choix dif
	 */
	public Difficulty getChoixDif() {
		return choixDif;
	}

	/**
	 * Gets choix board.
	 *
	 * @return the choix board
	 */
	public BoardType getChoixBoard() {
		return choixBoard;
	}

	/**
	 * Gets nb player.
	 *
	 * @return the nb player
	 */
	public int getNbPlayer() {
		return nbPlayer;
	}

	/**
	 * Gets nb bots.
	 *
	 * @return the nb bots
	 */
	public int getNbBots() {
		return nbBots;
	}


	/**
	 * Sets game window.
	 *
	 * @param gameWindow the game window
	 */
	public void setGameWindow(GameGraphicVue gameWindow) {
		this.gameWindow = gameWindow;
	}

	/**
	 * Cree une vue pour la partie en accord avec la vue actuelle, puis le GameManager associé aux options demandées.
	 */
	public void createGame()
	{

		if(currentVue instanceof SetUpConsoleVue)
			gameWindow = new GameConsoleVue();
	else
			gameWindow = new GameGraphicVue();

		gameWindow.getCtrl().setGameManager(choixGC, choixDif, choixBoard,nbBots, nbPlayer );

	}
}
