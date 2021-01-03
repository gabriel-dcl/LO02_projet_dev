package Models;

import enums.Events;

public class  GameManagerChaos extends GameManager {


	public void game() {

		boolean isFirstTime = true;
		boolean isSecondTime = true;

		while (currentBoard.currentCardsOnBoard.size() < maxCards )
		{
			for (index = 0; index < players.length; index++) {

				this.notifyObservers(Events.ShowCurrentPlayer);
				cardOnPlay = this.currentBoard.getCard();


				this.players[index].showVictoryCard(this);
				this.players[index].askToChangeVictoryCard(currentBoard, this);

				if(!isSecondTime)
				{
					this.players[index].alternateCards(currentBoard, this);
				}


				if (!isFirstTime)
				{
					this.players[index].moveCard(this.currentBoard, this);
					isSecondTime = false;
				}


				this.players[index].shuffle(currentBoard, this);

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
	
}
