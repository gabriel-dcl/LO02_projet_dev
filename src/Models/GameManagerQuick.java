package Models;

import enums.Events;

/**
 * Class d�finissant une partie de Type Rapide (Quick), h�ritant de la class GameManager
 *
 * @author Gabriel Duciel
 * @see GameManager
 * @see Board
 * @version 3.0
 */
public class GameManagerQuick extends GameManager {

	/**
	 * m�thode run d'un Thread correspondant au d�roulement d'une partie
	 *
	 * @see Board
	 * @see Player
	 * @since 3.0
	 */
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
						} else
			{
				this.waitFew();
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

