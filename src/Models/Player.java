package Models;

import enums.Events;

import javax.swing.tree.ExpandVetoException;
import java.util.Observable;
import java.util.Scanner;


/**
 * Class définissant un joueur. Le joueur est observable
 * @author Nicolas Felixine, Gabriel Duciel
 * @version 3.0
 */
public class Player extends Observable {


    //Variables initialisation
    private Card victoryCard;
    private Visitor visitor;
    private Strategy strategy;
    /**
     * The Sc.
     */
    protected Scanner sc;
    /**
     * The Has shuffled.
     */
    boolean hasShuffled;

    /**
     * Instantiates a new Player.
     *
     * @param victoryCard the victory card
     * @param gameManager the game manager
     */
    public Player(Card victoryCard, GameManager gameManager)
    {
        hasShuffled = false;
            sc = new Scanner(System.in);
            this.victoryCard = victoryCard;
    }

    /**
     * Instantiates a new Player.
     *
     * @param difficulty  the difficulty
     * @param victoryCard the victory card
     * @param visitor     the visitor
     */
    public Player(int difficulty, Card victoryCard, Visitor visitor)
    {
        if(difficulty == 0)
            this.strategy = new VirtualEasy(visitor);
        else
            this.strategy = new VirtualHard(visitor, victoryCard);

        this.victoryCard = victoryCard;
        this.visitor = visitor;
    }

    /**
     * Gets strategy.
     *
     * @return the strategy
     */
    public Strategy getStrategy() {
		return strategy;
	}

    /**
     * Gets victory card.
     *
     * @return the victory card
     */
    public Card getVictoryCard() {
		return victoryCard;
	}

    /**
     * Change victory card.
     *
     * @param currentBoard the current board
     */
    public void changeVictoryCard(Board currentBoard)
    {
        Card tempCard = this.victoryCard;
        this.victoryCard = currentBoard.getNewRandomCard();
        currentBoard.remainingCards.add(tempCard);
    }

    /**
     * Cette méthode indique à la vue qu'il faudra demander au joueur s'il souhaite changer de carte de victoire
     *
     * @param currentBoard le plateau de jeu actuel
     * @param gameManager  la partie actuelle
     */
    public void askToChangeVictoryCard(Board currentBoard, GameManager gameManager)
    {
        if(currentBoard.remainingCards.size() == 0)
            return ;

        if(this.strategy != null)
        {
            boolean ask = this.strategy.changeVictoryCard(currentBoard);
            if(ask)
            {
                this.changeVictoryCard(currentBoard);
                gameManager.notifyObservers(Events.AnnoncePlayerChangeVictoryCard);
                return ;
            }
        } else
        {
            gameManager.notifyObservers(Events.AskToChangeVictoryCard);
        }
        return ;
    }

    /**
     * Cette méthode indique à la vue qu'il faudra demander au joueur de déplacer une carte
     *
     * @param currentBoard le plateau de jeu actuel
     * @param gameManager  la partie actuelle
     */
    public void moveCard(Board currentBoard, GameManager gameManager)
    {
        if(this.strategy != null)
            strategy.moveCard(currentBoard);
        else
        {
            gameManager.notifyObservers( Events.AskForCardToMove );
        }
    }

    /**
     * Cette méthode indique à la vue qu'il faudra demander au joueur de déplacer une carte
     *
     * @param newCard  La nouvelle carte à ajouter au plateau
     * @param currentBoard le plateau de jeu actuel
     * @param gameManager  la partie actuelle
     */
    public void placeNewCard(Card newCard, Board currentBoard, GameManager gameManager)
    {
        if(this.strategy != null)
        {
            strategy.placeNewCard(newCard, currentBoard);
            gameManager.nextCardOnPlay();
        }
        else {
            gameManager.notifyObservers( Events.AskForPositionNewCard );
        }
    }


    public void setHasShuffled(boolean hasShuffled) {
        this.hasShuffled = hasShuffled;
    }

    /**
     * Cette méthode indique à la vue qu'il faudra demander au joueur s'il souhaite shuffle
     *
     * @param currentBoard le plateau de jeu actuel
     * @param gameManager  la partie actuelle
     */
    public void shuffle(Board currentBoard, GameManager gameManager)
    {
        if(this.hasShuffled)
            return;

        if(this.strategy != null)
            this.hasShuffled = strategy.shuffle(currentBoard);
        else
            gameManager.notifyObservers(Events.AskForShuffle);


    }

    /**
     * Cette méthode indique à la vue qu'il faudra demander au joueur d'alterner deux cartes
     *
     * @param currentBoard le plateau de jeu actuel
     * @param gameManager  la partie actuelle
     */
    public void alternateCards(Board currentBoard, GameManager gameManager)
    {
        if(this.strategy != null)
            strategy.alternateCards(currentBoard);
        else {
            gameManager.notifyObservers(Events.AskForCardsToAlternate);
        }
    }

    /**
     * Cette méthode indique à la vue qu'il faudra demander au joueur s'il souhaite voir sa carte victoire
     *
     * @param gameManager  la partie actuelle
     */
    public void showVictoryCard(GameManager gameManager)
    {
        if(this.strategy != null)
            return;

        gameManager.notifyObservers(Events.AskToShowVictoryCard);
    }
}
