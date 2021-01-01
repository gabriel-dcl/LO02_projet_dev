package Models;

import java.util.Observable;
import java.util.Scanner;

public abstract class GameManager extends Observable {
    private int difficulty;
    protected Player players[];
    protected Scanner sc;
    protected Board currentBoard;
    protected Visitor visitor;
    protected int maxCards;

    int realPlayersAmount;
    int virtualPlayersAmount = 0;
    boolean test;

    public GameManager()
    {

        sc = new Scanner(System.in);
        visitor = new Visitor();
    }

   public void playersSetUp(int realPlayersAmount, int virtualPlayersAmount)
   {
       this.realPlayersAmount = realPlayersAmount;
       this.virtualPlayersAmount = virtualPlayersAmount;

       players = new Player[this.realPlayersAmount + this.virtualPlayersAmount];

       int index = 0;

       for(index = 0; index < realPlayersAmount; index++)
       {
           players[index] = new Player(currentBoard.getNewRandomCard());

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

    public void setBoard(int choix) {

        switch (choix) {
            case 1:
                currentBoard = new BoardRectangular();
                break;
            case 2:
                currentBoard = new BoardTriangular();
                break;
            case 3:
                currentBoard = new BoardSquare();
                break;
            default:

        }
    }

    public abstract void game();

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
