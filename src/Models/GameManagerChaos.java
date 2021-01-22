package Models;

import enums.Events;

/**
 * Class définissant une partie de Type chaos, héritant de la class GameManager
 *
 * @author Gabriel Duciel
 * @see GameManager
 * @see Board
 * @version 3.0
 */
public class  GameManagerChaos extends GameManager {

	/**
	 * méthode run d'un Thread correspondant au déroulement d'une partie
	 *
	 * @see Board
	 * @see Player
	 * @since 3.0
	 */
	public void run() {

		maxCards = 14;

		boolean isFirstTime = true;
		boolean isSecondTime = true;
		cardOnPlay = this.currentBoard.getCard();

		while (currentBoard.currentCardsOnBoard.size() < maxCards )
		{
			for (index = 0; index < players.length; index++) {

				this.notifyObservers(Events.ShowCurrentPlayer);



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

				if(players[index].getStrategy() == null)
				{
					this.notifyObservers(Events.PlayerTurn);
					this.waitForPlayerToPlay();
				} else
				{
					this.waitFew();
				}
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
