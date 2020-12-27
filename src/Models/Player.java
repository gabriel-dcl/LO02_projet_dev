package Models;

import java.util.Scanner;
public class Player{


    //Variables initialisation

    private boolean isReal;
    private Card victoryCard;
    private int nbPoints;
    private Visitor visitor;
    private Strategy strategy;
    protected Scanner sc;


    public Player(Card victoryCard)
    {
            sc = new Scanner(System.in);
            strategy = new realPlayer();
            this.victoryCard = victoryCard;
    }

    public Player(int difficulty, Card victoryCard, Visitor visitor)
    {
        if(difficulty == 0)
            strategy = new virtualEasy(visitor);
        else
            strategy = new virtualHard(visitor, victoryCard);

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


/*    public Models.Board moveCard(Models.Card newCard, Models.Board currentBoard) {

        return;
    }

    public Models.Board placeNewCard(Models.Card newCard, Models.Board currentBoard) {
    }

    public Models.Board alternateCards(Models.Card card1, Models.Card Card2) {
    }

    public void showVictoryCard() {
    }


    public void accept(Models.Visitor visitor) {
    }

    public void changeVictoryCard(Models.Card newCard) {
    }
*/
}
