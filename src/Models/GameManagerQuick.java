package Models;

public class GameManagerQuick extends GameManager {
    @Override
    public void game() {

		boolean isFirstTime = true;

		while (currentBoard.currentCardsOnBoard.size() < maxCards )
		{
			for (index = 0; index < players.length; index++) {
				System.out.println("Joueur " + (index + 1));
				cardOnPlay = this.currentBoard.getCard();

				this.players[index].showVictoryCard();

				this.players[index].shuffle(currentBoard);


				this.players[index].placeNewCard(cardOnPlay, this.currentBoard);

				cardOnPlay = this.currentBoard.getCard();
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
}

