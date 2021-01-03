package Models;

import enums.Events;

public class GameManagerClassic  extends GameManager {

    public void game() {

    	boolean isFirstTime = true;


    	while ( currentBoard.currentCardsOnBoard.size() < 15 )
    	{
			for (index = 0; index < players.length; index++) {

				this.notifyObservers(Events.ShowCurrentPlayer);
				cardOnPlay = this.currentBoard.getCard();

				this.players[index].showVictoryCard(this);
				if (!isFirstTime)
				{
					 this.players[index].moveCard(this.currentBoard, this);
				}
					this.players[index].placeNewCard(cardOnPlay, this.currentBoard, this);

				this.notifyObservers(Events.ShowBoard);
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
    	this.notifyObservers(Events.GameOver);


    }
    public void getInstance() {
    } 
    private void singleton() {
    }

}
