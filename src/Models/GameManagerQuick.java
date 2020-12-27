package Models;

public class GameManagerQuick extends GameManager {
    @Override
    public void game() {

		boolean isFirstTime = true;

		while (currentBoard.currentCardsOnBoard.size() < maxCards )
		{
			for (int i = 0; i < players.length; i++) {
				System.out.println("Joueur " + (i + 1));
				Card cardOnPlay = this.currentBoard.getCard();

				this.players[i].getStrategy().showVictoryCard(this.players[i].getVictoryCard());
				this.players[i].getStrategy().shuffle(currentBoard);
				this.players[i].getStrategy().placeNewCard(cardOnPlay, this.currentBoard);

				cardOnPlay = this.currentBoard.getCard();
				this.players[i].getStrategy().placeNewCard(cardOnPlay, this.currentBoard);

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

