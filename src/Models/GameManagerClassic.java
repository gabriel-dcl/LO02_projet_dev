package Models;

import enums.Events;

public class GameManagerClassic  extends GameManager {

	public void run() {
		System.out.println(this);
    	boolean isFirstTime = true;

    	while ( currentBoard.currentCardsOnBoard.size() < 15 )
    	{
			for (index = 0; index < players.length; index++)
			{

				this.notifyObservers(Events.ShowCurrentPlayer);
				cardOnPlay = this.currentBoard.getCard();

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

    public void getInstance() {
    } 
    private void singleton() {
    }

}
