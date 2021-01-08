package Models;

import enums.Events;

public class GameManagerClassic  extends GameManager {

	public void run() {
		System.out.println(this);
    	boolean isFirstTime = true;
		cardOnPlay = this.currentBoard.getCard();
    	while ( currentBoard.currentCardsOnBoard.size() < 15 )
    	{
			for (index = 0; index < players.length; index++)
			{

				this.notifyObservers(Events.ShowCurrentPlayer);

				this.players[index].showVictoryCard(this);

				if (!isFirstTime)
				{
					 this.players[index].moveCard(this.currentBoard, this);
				}
					this.players[index].placeNewCard(cardOnPlay, this.currentBoard, this);


				isFirstTime = false;


				if(players[index].getStrategy() == null)
				{
					this.notifyObservers(Events.PlayerTurn);
					this.waitForPlayerToPlay();
				}
				this.notifyObservers(Events.ShowBoard);

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
