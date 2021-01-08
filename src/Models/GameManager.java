package Models;

import java.util.Observable;
import java.util.Scanner;

import enums.BoardType;

public abstract class GameManager extends Observable implements Runnable {
    private int difficulty;
    protected Player players[];
    protected Scanner sc;
    protected Board currentBoard;
    protected Visitor visitor;
    protected int maxCards;
    protected Card cardOnPlay;
    protected int index;
    protected boolean hasPlayed = false;
    int realPlayersAmount;
    int virtualPlayersAmount = 0;

    public int getDifficulty() {
        return difficulty;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public int getMaxCards() {
        return maxCards;
    }

    public void setMaxCards(int maxCards) {
        this.maxCards = maxCards;
    }

    public void setCardOnPlay(Card cardOnPlay) {
        this.cardOnPlay = cardOnPlay;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isHasPlayed() {
        return hasPlayed;
    }

    public int getRealPlayersAmount() {
        return realPlayersAmount;
    }

    public void setRealPlayersAmount(int realPlayersAmount) {
        this.realPlayersAmount = realPlayersAmount;
    }

    public int getVirtualPlayersAmount() {
        return virtualPlayersAmount;
    }

    public void setVirtualPlayersAmount(int virtualPlayersAmount) {
        this.virtualPlayersAmount = virtualPlayersAmount;
    }

    public void nextCardOnPlay()
    {
        cardOnPlay = this.currentBoard.getCard();
    }


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


    public boolean hasPlayed() {
        return hasPlayed;
    }


    public synchronized void setHasPlayed (boolean hasReceived)
    {
        this.hasPlayed = hasReceived;
        notifyAll();
    }

    public Card getCardOnPlay() {
        return cardOnPlay;
    }


    public Board getCurrentBoard() {
        return currentBoard;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public Player[] getPlayers() {

        return players;
    }

    @Override
    public void notifyObservers(Object arg) {
        this.setChanged();
        super.notifyObservers(arg);
    }

    public int getIndex() {
        return index;
    }

    public GameManager()
    {
        index = 0;
        sc = new Scanner(System.in);
        visitor = new Visitor();
        hasPlayed = false;
    }

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

    public void setDifficulty(int difficulty) {
	this.difficulty = difficulty;
}

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


    public void occure() {
    }
    public void getInstance() {
    }
    private void singleton() {
    }

}
