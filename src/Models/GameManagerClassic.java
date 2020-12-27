package Models;

public class GameManagerClassic  extends GameManager {
   


    public void game() {

    	boolean isFirstTime = true;


    	while ( currentBoard.currentCardsOnBoard.size() < 15 )
    	{
			for (int i = 0; i < players.length; i++) {
				System.out.println("Joueur " + (i + 1));

				Card cardOnPlay = this.currentBoard.getCard();

				this.players[i].getStrategy().showVictoryCard(this.players[i].getVictoryCard());
				if (!isFirstTime)
				{
					 this.players[i].getStrategy().moveCard(this.currentBoard);
				}
					this.players[i].getStrategy().placeNewCard(cardOnPlay, this.currentBoard);

				currentBoard.showBoard();
				isFirstTime = false;

				if(currentBoard.currentCardsOnBoard.size() ==  maxCards)
				{
					break;
				}
			}

			if(currentBoard.currentCardsOnBoard.size() ==  maxCards)
			{
				break;
			}
		}


    	currentBoard.accept(visitor);
    	visitor.over();


    }
    public void getInstance() {
    } 
    private void singleton() {
    }

}
