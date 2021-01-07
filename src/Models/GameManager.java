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

    int realPlayersAmount;
    int virtualPlayersAmount = 0;

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

    public void gameOver()
    {
        if(realPlayersAmount + virtualPlayersAmount == 3)
        {
            int pointsPlayer1 =  this.visitor.getPointsTotalRegardingVictoryCard(players[0].getVictoryCard());
            int pointsPlayer2 =  this.visitor.getPointsTotalRegardingVictoryCard(players[1].getVictoryCard());
            int pointsPlayer3 =  this.visitor.getPointsTotalRegardingVictoryCard(players[2].getVictoryCard());

            System.out.println("CARTE J1: " + players[0].getVictoryCard());
            System.out.println("CARTE J2: " + players[1].getVictoryCard());
            System.out.println("CARTE J3: " + players[2].getVictoryCard());

            if(pointsPlayer1 > pointsPlayer2 && pointsPlayer1 > pointsPlayer3)
                System.out.println("Le joueur 1 gagne avec sa carte : " + players[0].getVictoryCard());
            else if(pointsPlayer2 > pointsPlayer1 && pointsPlayer2 > pointsPlayer3)
                System.out.println("Le joueur 2 gagne avec sa carte : " + players[1].getVictoryCard());
            else if(pointsPlayer3 > pointsPlayer1 && pointsPlayer3 > pointsPlayer2)
                System.out.println("Le joueur 3 gagne avec sa carte : " + players[2].getVictoryCard());
            else
                System.out.println("Match nul !");
        }
        else
        {
            System.out.println("CARTE J1: " + players[0].getVictoryCard());
            System.out.println("CARTE J2: " + players[1].getVictoryCard());

            int pointsPlayer1 =  this.visitor.getPointsTotalRegardingVictoryCard(players[0].getVictoryCard());
            int pointsPlayer2 =  this.visitor.getPointsTotalRegardingVictoryCard(players[1].getVictoryCard());

            if(pointsPlayer1 > pointsPlayer2)
                System.out.println("Le joueur 1 gagne avec sa carte : " + players[0].getVictoryCard());
            else if((pointsPlayer2 > pointsPlayer1))
                System.out.println("Le joueur 2 gagne avec sa carte : " + players[1].getVictoryCard());
            else
                System.out.println("Match nul !");
        }


    }
    public void occure() {
    }
    public void getInstance() {
    }
    private void singleton() {
    }

}
