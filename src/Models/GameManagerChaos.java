package Models;

public class  GameManagerChaos extends GameManager {
	public void game() {

		boolean isFirstTime = true;
		boolean isSecondTime = true;

		while (currentBoard.currentCardsOnBoard.size() < maxCards )
		{
			for (index = 0; index < players.length; index++) {

				System.out.println("Joueur " + (index + 1));
				cardOnPlay = this.currentBoard.getCard();

				if (this.players[index].changeVictoryCard(currentBoard))
					System.out.println("Joueur " + (index + 1) + "A change de Victory Models.Card ! ");


				if(!isSecondTime)
				{
					this.players[index].alternateCards(currentBoard);
				}


				if (!isFirstTime)
				{
					this.players[index].moveCard(this.currentBoard);
					isSecondTime = false;
				}

				this.players[index].showVictoryCard();

				this.players[index].shuffle(currentBoard);

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
