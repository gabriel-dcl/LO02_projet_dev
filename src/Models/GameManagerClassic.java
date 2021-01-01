package Models;

public class GameManagerClassic  extends GameManager {

    public void game() {

    	boolean isFirstTime = true;


    	while ( currentBoard.currentCardsOnBoard.size() < 15 )
    	{
			for (index = 0; index < players.length; index++) {
				System.out.println("Joueur " + (index + 1));

				cardOnPlay = this.currentBoard.getCard();

				this.players[index].showVictoryCard();
				if (!isFirstTime)
				{
					 this.players[index].moveCard(this.currentBoard);
				}
					this.players[index].placeNewCard(cardOnPlay, this.currentBoard);

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
