package Models;

import enums.Events;

public class GameManagerQuick extends GameManager {
    @Override
    public void run() {

    	maxCards = 14;

		boolean isFirstTime = true;
		cardOnPlay = this.currentBoard.getCard();

		while (currentBoard.currentCardsOnBoard.size() < maxCards )
		{
			for (index = 0; index < players.length; index++)
			{
				this.notifyObservers(Events.ShowCurrentPlayer);


				this.players[index].showVictoryCard(this);

				this.players[index].shuffle(currentBoard, this);

				this.players[index].placeNewCard(cardOnPlay, this.currentBoard, this);


				this.players[index].placeNewCard(cardOnPlay, this.currentBoard, this);

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
}

