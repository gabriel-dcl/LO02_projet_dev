package Models;

import enums.Events;

public class GameManagerQuick extends GameManager {
    @Override
    public void game() {

		boolean isFirstTime = true;

		while (currentBoard.currentCardsOnBoard.size() < maxCards )
		{
			for (index = 0; index < players.length; index++)
			{
				this.notifyObservers(Events.ShowCurrentPlayer);
				cardOnPlay = this.currentBoard.getCard();

				this.players[index].showVictoryCard(this);

				this.players[index].shuffle(currentBoard, this);

				this.players[index].placeNewCard(cardOnPlay, this.currentBoard, this);

				cardOnPlay = this.currentBoard.getCard();
				this.players[index].placeNewCard(cardOnPlay, this.currentBoard, this);

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
