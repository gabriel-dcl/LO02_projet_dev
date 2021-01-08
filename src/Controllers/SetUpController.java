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

public class SetUpController {
	
	private GameManager gm;
	
	
	private GameMode choixGC;
	private Difficulty choixDif;
	private BoardType choixBoard;
	private int nbPlayer;
	private int nbBots ;
	private Vue currentVue;
	private Vue gameWindow;

	public SetUpController(Vue currentVue)
	{
		choixGC = GameMode.CLASSIC;
		choixDif = Difficulty.EASY;
		choixBoard = BoardType.RECTANGULAR;
		nbPlayer = 0;
		nbBots = 3;

		this.currentVue = currentVue;
	}

	public void changeVue()
	{
		if(currentVue instanceof SetUpConsoleVue)
			currentVue = new SetUpGraphicVue();
		else
			currentVue = new SetUpConsoleVue();

		currentVue.occure();
	}

	public void setChoixGC(GameMode choixGC) {
		this.choixGC = choixGC;
	}

	public void setChoixDif(Difficulty choixDif) {
		this.choixDif = choixDif;
	}

	public void setChoixBoard(BoardType choixBoard) {
		this.choixBoard = choixBoard;
	}

	public void setNbPlayer(int nbPlayer) {
		this.nbPlayer = nbPlayer;
	}

	public void setNbBots(int nbBots) {
		this.nbBots = nbBots;
	}

	public GameManager getGm() {
		return gm;
	}

	public void setGm(GameManager gm) {
		this.gm = gm;
	}

	public GameMode getChoixGC() {
		return choixGC;
	}

	public Difficulty getChoixDif() {
		return choixDif;
	}

	public BoardType getChoixBoard() {
		return choixBoard;
	}

	public int getNbPlayer() {
		return nbPlayer;
	}

	public int getNbBots() {
		return nbBots;
	}


	public void setGameWindow(GameGraphicVue gameWindow) {
		this.gameWindow = gameWindow;
	}

	public void createGame()
	{

		//

		if(currentVue instanceof SetUpConsoleVue)
			gameWindow = new GameConsoleVue();
	else
			gameWindow = new GameGraphicVue();

		gameWindow.getCtrl().setGameManager(choixGC, choixDif, choixBoard,nbBots, nbPlayer );

	}
}
