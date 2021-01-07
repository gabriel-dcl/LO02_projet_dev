package Controllers;

import Models.*;
import Vues.console.GameConsoleVue;
import enums.BoardType;
import enums.Difficulty;
import enums.GameMode;

public class GameController {

	private GameManager gameManager;
	private GameConsoleVue gameManagerVue;

	public GameController(GameConsoleVue gmv) {
		this.gameManagerVue = gmv;
	}



	public GameController() {
		this.gameManagerVue = new GameConsoleVue();
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public Card getCardFromCoordinate(Coordinate currentPoint) {
		return gameManager.getCurrentBoard().getCurrentCardsOnBoard().get(currentPoint);
	}

	public void removeFromCoordinate(Coordinate point) {
		gameManager.getCurrentBoard().getCurrentCardsOnBoard().remove(point);
	}

	public void addCardOnBoard(Card newCard, Coordinate position) {
		gameManager.getCurrentBoard().addCardOnBoard(newCard, position);
	}

	public Coordinate findEqualsCoordinate(Coordinate point) {
		return gameManager.getCurrentBoard().findEqualsCoordinate(point);
	}

	public void occure() {
		//this.setGameManager();
	//	this.gameBoardChoice();
	//	this.difficultyChoice();
	//	this.playersSetup();

		gameManagerVue.setGmc(this);
		gameManager.addObserver(gameManagerVue);
		gameManager.getCurrentBoard().addObserver(gameManagerVue);

		gameManager.run();
	}


	public void gameBoardChoice() {
		boolean valueOkay = false;
		int choix = -1;

	}

	public boolean isPlaceAvailable(Coordinate point) {
		return this.gameManager.getCurrentBoard().isPlaceAvailable(point);
	}

	public void alternateCards(Coordinate position1, Coordinate position2) {
		Card temp = this.gameManager.getCurrentBoard().getCurrentCardsOnBoard().get(position1);
		this.gameManager.getCurrentBoard().getCurrentCardsOnBoard().put(position1,
				this.gameManager.getCurrentBoard().getCurrentCardsOnBoard().get(position2));
		this.gameManager.getCurrentBoard().getCurrentCardsOnBoard().put(position2, temp);
	}

	public boolean isCoordinateCloseEnough(Coordinate point) {
		return this.gameManager.getCurrentBoard().isCoordinateCloseEnough(point);
	}

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
		gameManager.addObserver(this.gameManagerVue);

		Thread t = new Thread(gameManager);
		Thread vue = new Thread(this.gameManagerVue);
		t.start();
		vue.start();



		//if(this.gameManagerVue instanceof GameConsoleVue)
		//	gameManager.setHasPlayed(fal);

	}
}
