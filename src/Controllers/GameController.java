package Controllers;

import Models.*;
import Vues.console.ConsoleVue;

import java.util.Scanner;

public class GameController {

    private GameManager gameManager;
    private ConsoleVue gameManagerVue;

    public GameController(ConsoleVue gmv)
    {
        this.gameManagerVue = gmv;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public Card getCardFromCoordinate(Coordinate currentPoint)
    {
        return gameManager.getCurrentBoard().getCurrentCardsOnBoard().get(currentPoint);
    }

    public void removeFromCoordinate(Coordinate point)
    {
        gameManager.getCurrentBoard().getCurrentCardsOnBoard().remove(point);
    }

    public void addCardOnBoard(Card newCard, Coordinate position)
    {
        gameManager.getCurrentBoard().addCardOnBoard(newCard, position);
    }

    public Coordinate findEqualsCoordinate(Coordinate point)
    {
        return gameManager.getCurrentBoard().findEqualsCoordinate(point);
    }

    public void occure()
    {
        this.setGameManager();
        this.gameBoardChoice();
        this.difficultyChoice();
        this.playersSetup();

        gameManagerVue.setGmc(this);
        gameManager.addObserver(gameManagerVue);
        gameManager.getCurrentBoard().addObserver(gameManagerVue);

        gameManager.game();
    }

    public void difficultyChoice()
    {
        int difficultyChoice = -1;

        difficultyChoice = gameManagerVue.difficultyChoice();

        while(difficultyChoice != 1 && difficultyChoice !=0)
        {
            difficultyChoice = gameManagerVue.badInputDifficulty();
        }
    }


    public void playersSetup()
    {
        int realPlayersAmount = gameManagerVue.realPlayersAmountChoice();
        int virtualPlayersAmount = 0;

        if(realPlayersAmount != 3)
        {
            virtualPlayersAmount = gameManagerVue.virtualPlayersAmountChoice();

            while( realPlayersAmount + virtualPlayersAmount > 3  || realPlayersAmount + virtualPlayersAmount < 2)
            {
                virtualPlayersAmount = gameManagerVue.badInputVirtualPlayersAmount();
            }
        }

        gameManager.playersSetUp(realPlayersAmount, virtualPlayersAmount);
    }

    public void gameBoardChoice()
    {
        boolean valueOkay = false;
        int choix = -1;

        while(choix != 1 && choix != 2  && choix != 3)
        {
            choix = gameManagerVue.gameBoardChoice();
        }

        gameManager.setBoard(choix);
    }

    public boolean isPlaceAvailable(Coordinate point)
    {
        return this.gameManager.getCurrentBoard().isPlaceAvailable(point);
    }

    public void alternateCards(Coordinate position1, Coordinate position2)
    {
      Card temp = this.gameManager.getCurrentBoard().getCurrentCardsOnBoard().get(position1);
        this.gameManager.getCurrentBoard().getCurrentCardsOnBoard().put(position1, this.gameManager.getCurrentBoard().getCurrentCardsOnBoard().get(position2));
        this.gameManager.getCurrentBoard().getCurrentCardsOnBoard().put(position2, temp);
    }

    public boolean isCoordinateCloseEnough(Coordinate point)
    {
        return this.gameManager.getCurrentBoard().isCoordinateCloseEnough(point);
    }

    public void setGameManager()
    {
        int choix = gameManagerVue.gameManagerChoice();

        switch (choix)
        {
            case 1 : this.gameManager = new GameManagerClassic();
                break;
            case 2 : this.gameManager = new GameManagerQuick();
                break;
            case 3 : this.gameManager = new GameManagerChaos();
                break;
            default : this.gameManager = new GameManagerClassic();
                break;
        }
    }
}
