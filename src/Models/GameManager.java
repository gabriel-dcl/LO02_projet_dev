package Models;

import java.util.Observable;
import java.util.Scanner;

import enums.BoardType;

/**
 * Class abstraite commune à tous les GameManagers, définissant une partie et sa gestion
 * C'est un thread afin de permettre l'exclusion mutuelle de ressources avec les autres Threads, et également de tourner en parallèle de la vue.
 *
 * @author Gabriel Duciel, Nicolas Felixine
 * @version 3.0
 * @see Board
 * @see Player
 */
public abstract class GameManager extends Observable implements Runnable {
    private int difficulty;
    /**
     * The Players.
     * @see Player
     */
    protected Player players[];
    /**
     * The Sc.
     */
    protected Scanner sc;
    /**
     * The Current board.
     */
    protected Board currentBoard;
    /**
     * The Visitor.
     */
    protected Visitor visitor;
    /**
     * The Max cards.
     */
    protected int maxCards;
    /**
     * The Card on play.
     */
    protected Card cardOnPlay;
    /**
     * The Index.
     */
    protected int index;
    /**
     * The Has played.
     */
    protected boolean hasPlayed = false;
    /**
     * The Real players amount.
     */
    int realPlayersAmount;
    /**
     * The Virtual players amount.
     */
    int virtualPlayersAmount = 0;

    /**
     * Gets difficulty.
     *
     * @return the difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Sets players.
     *
     * @param players the players
     */
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * Gets sc.
     *
     * @return the sc
     */
    public Scanner getSc() {
        return sc;
    }

    /**
     * Sets sc.
     *
     * @param sc the sc
     */
    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Sets current board.
     *
     * @param currentBoard the current board
     */
    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }

    /**
     * Sets visitor.
     *
     * @param visitor the visitor
     */
    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }


    /**
     * Gets max cards.
     *
     * @return the max cards
     */
    public int getMaxCards() {
        return maxCards;
    }

    /**
     * Sets max cards.
     *
     * @param maxCards the max cards
     */
    public void setMaxCards(int maxCards) {
        this.maxCards = maxCards;
    }

    /**
     * Sets card on play.
     *
     * @param cardOnPlay the card on play
     */
    public void setCardOnPlay(Card cardOnPlay) {
        this.cardOnPlay = cardOnPlay;
    }

    /**
     * Sets index.
     *
     * @param index the index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Is has played boolean.
     *
     * @return the boolean
     */
    public boolean isHasPlayed() {
        return hasPlayed;
    }

    /**
     * Gets real players amount.
     *
     * @return the real players amount
     */
    public int getRealPlayersAmount() {
        return realPlayersAmount;
    }

    /**
     * Sets real players amount.
     *
     * @param realPlayersAmount the real players amount
     */
    public void setRealPlayersAmount(int realPlayersAmount) {
        this.realPlayersAmount = realPlayersAmount;
    }

    /**
     * Gets virtual players amount.
     *
     * @return the virtual players amount
     */
    public int getVirtualPlayersAmount() {
        return virtualPlayersAmount;
    }

    /**
     * Sets virtual players amount.
     *
     * @param virtualPlayersAmount the virtual players amount
     */
    public void setVirtualPlayersAmount(int virtualPlayersAmount) {
        this.virtualPlayersAmount = virtualPlayersAmount;
    }

    /**
     * Next card on play.
     */
    public void nextCardOnPlay()
    {
        cardOnPlay = this.currentBoard.getCard();
    }


    /**
     * Simple méthode pour mettre en pause ponctuellement le Thread
     * @since 3.0
     */
    public synchronized void waitFew()  {
        try {
           Thread.sleep(500);
        } catch (InterruptedException e)  {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Ici, on attend que le player joue. Si le joueur n'a pas fini son tour, on attend
     * @since 3.0
     */
    public synchronized void waitForPlayerToPlay()
    {

        while(!hasPlayed)
        {
            try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
            }
        }

        hasPlayed = false;
    }




    /**
     *
     *
     * @param hasReceived the has received
     */
    public synchronized void setHasPlayed (boolean hasReceived)
    {
        this.hasPlayed = hasReceived;
        notifyAll();
    }

    /**
     * Gets card on play.
     *
     * @return the card on play
     */
    public Card getCardOnPlay() {
        return cardOnPlay;
    }


    /**
     * Gets current board.
     *
     * @return the current board
     */
    public Board getCurrentBoard() {
        return currentBoard;
    }

    /**
     * Gets visitor.
     *
     * @return the visitor
     */
    public Visitor getVisitor() {
        return visitor;
    }

    /**
     * Get players player [ ].
     *
     * @return the player [ ]
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Permet de notifier les observateurs du GameManager, en accord avec l'interface Observer
     * La méthode est ici rédéfinie pour ajouter à chaque appel de notifyObservers l'appel à setChanged.
     * param  Events supplémentaires à communiquer aux Observateurs
     * @since 3.0
     */
    public void notifyObservers(Object arg) {
        this.setChanged();
        super.notifyObservers(arg);
    }

    /**
     * Gets index.
     *
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Instantiates a new Game manager.
     */
    public GameManager()
    {
        index = 0;
        sc = new Scanner(System.in);
        visitor = new Visitor();
        hasPlayed = false;
    }

    /**
     * Méthode qui permet de créer le bon nombre de joueur de chaque type
     *
     * @param realPlayersAmount    le nombre de joueurs réels
     * @param virtualPlayersAmount le nombre de joueurs virtuels
     */
    public void playersSetUp(int realPlayersAmount, int virtualPlayersAmount)
   {
       this.realPlayersAmount = realPlayersAmount;
       this.virtualPlayersAmount = virtualPlayersAmount;

       players = new Player[this.realPlayersAmount + this.virtualPlayersAmount];

       int index = 0;

       for(index = 0; index < realPlayersAmount; index++)
       {
           players[index] = new Player(currentBoard.getNewRandomCard(), this);

       }

       for (int i = 0; i < this.virtualPlayersAmount; i++)
       {

           players[index + i] = new Player(difficulty, currentBoard.getNewRandomCard(), visitor);

       }

       if(this.realPlayersAmount + this.virtualPlayersAmount == 3)
           this.maxCards = 14;
       else
           this.maxCards = 15;
   }

    /**
     * Sets difficulty.
     *
     * @param difficulty the difficulty
     */
    public void setDifficulty(int difficulty) {
	this.difficulty = difficulty;
}

    /**
     * Sets board.
     *
     * @param choix the choix
     */
    public void setBoard(BoardType choix) {

        switch (choix) {
            case RECTANGULAR:
                currentBoard = new BoardRectangular();
                break;
            case TRIANGULAR:
                currentBoard = new BoardTriangular();
                break;
            case SQUARE:
                currentBoard = new BoardSquare();
                break;
            default: currentBoard = new BoardRectangular();
            	break;

        }
    }

    public abstract void run();


    /**
     * Occure.
     */
    public void occure() {
    }

    /**
     * Gets instance.
     */
    public void getInstance() {
    }
    private void singleton() {
    }

}
