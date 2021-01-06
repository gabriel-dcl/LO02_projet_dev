package Controllers;

import Models.*;
import Vues.console.ConsoleVue;
import enums.BoardType;
import enums.Difficulty;
import enums.GameMode;

import java.util.Scanner;

public class GameController {

	private GameManager gameManager;
	private ConsoleVue gameManagerVue;

	public GameController(ConsoleVue gmv) {
		this.gameManagerVue = gmv;

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
		this.gameBoardChoice();
		this.difficultyChoice();
		this.playersSetup();

		gameManagerVue.setGmc(this);
		gameManager.addObserver(gameManagerVue);
		gameManager.getCurrentBoard().addObserver(gameManagerVue);

		gameManager.game();
	}

	public void difficultyChoice() {
		int difficultyChoice = -1;

		difficultyChoice = gameManagerVue.difficultyChoice();

		while (difficultyChoice != 1 && difficultyChoice != 0) {
			// difficultyChoice = gameManagerVue.badInputDifficulty();
		}
	}

	public void playersSetup() {
		int realPlayersAmount = gameManagerVue.realPlayersAmountChoice();
		int virtualPlayersAmount = 0;

		if (realPlayersAmount != 3) {
			virtualPlayersAmount = gameManagerVue.virtualPlayersAmountChoice();

			while (realPlayersAmount + virtualPlayersAmount > 3 || realPlayersAmount + virtualPlayersAmount < 2) {
				virtualPlayersAmount = gameManagerVue.badInputVirtualPlayersAmount();
			}
		}

		gameManager.playersSetUp(realPlayersAmount, virtualPlayersAmount);
	}

	public void gameBoardChoice() {
		boolean valueOkay = false;
		int choix = -1;

		while (choix != 1 && choix != 2 && choix != 3) {
			choix = gameManagerVue.gameBoardChoice();
		}
		switch (choix) {
		case 1 : gameManager.setBoard(BoardType.RECTANGULAR);
			break;
		case 2 : gameManager.setBoard(BoardType.TRIANGULAR);
			break; 
		case 3 : gameManager.setBoard(BoardType.SQUARE);
			break;
		default: gameManager.setBoard(BoardType.RECTANGULAR);
			break;
		}
		
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
		case CLASSIC:
			gameManager = new GameManagerClassic();
			break;
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

	}
}
