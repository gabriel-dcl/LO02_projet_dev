package Models;

import enums.Events;

import javax.swing.tree.ExpandVetoException;
import java.util.Observable;
import java.util.Scanner;


public class Player extends Observable {


    //Variables initialisation
    private boolean isReal;
    private Card victoryCard;
    private int nbPoints;
    private Visitor visitor;
    private Strategy strategy;
    protected Scanner sc;
    private GameManager gameManager;
    boolean hasShuffled;

    public Player(Card victoryCard, GameManager gameManager)
    {
        hasShuffled = false;
            sc = new Scanner(System.in);
            this.victoryCard = victoryCard;
            this.gameManager = gameManager;
    }

    public Player(int difficulty, Card victoryCard, Visitor visitor)
    {
        if(difficulty == 0)
            this.strategy = new VirtualEasy(visitor);
        else
            this.strategy = new VirtualHard(visitor, victoryCard);

        this.victoryCard = victoryCard;
        this.visitor = visitor;
    }

	public Strategy getStrategy() {
		return strategy;
	}

	public Card getVictoryCard() {
		return victoryCard;
	}

    public boolean changeVictoryCard(Board currentBoard)
    {
        if(currentBoard.remainingCards.size() == 0)
            return false;


      boolean ask = this.strategy.changeVictoryCard(currentBoard);
            if(ask)
            {
                Card tempCard = this.victoryCard;
                this.victoryCard = currentBoard.getNewRandomCard();
                currentBoard.remainingCards.add(tempCard);
                return true;
            }
        return false;
    }

    public void moveCard(Board currentBoard)
    {

        if(this.strategy != null)
            strategy.moveCard(currentBoard);
        else
        {gameManager.notifyObservers( Events.AskForCardToMove );
        }
    }

    public void placeNewCard(Card newCard, Board currentBoard)
    {
        if(this.strategy != null)
            strategy.placeNewCard(newCard, currentBoard);
        else {
            gameManager.notifyObservers( Events.AskForPositionNewCard );
        }
    }

    public void setHasShuffled(boolean hasShuffled) {
        this.hasShuffled = hasShuffled;
    }

    public void shuffle(Board currentBoard)
    {
        if(this.hasShuffled)
            return;

        if(this.strategy != null)
            this.hasShuffled = strategy.shuffle(currentBoard);
        else
            gameManager.notifyObservers(Events.AskForShuffle);


    }

    public void alternateCards(Board currentBoard)
    {
        if(this.strategy != null)
            strategy.alternateCards(currentBoard);
        else {
            gameManager.notifyObservers(Events.AskForCardsToAlternate);
        }
    }

    public void showVictoryCard()
    {
        if(this.strategy != null)
            return;

        gameManager.notifyObservers(Events.AskToShowVictoryCard);
    }
}
