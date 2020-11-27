import java.util.Scanner;

public class  GameManagerChaos extends GameManager {
	public void game() {

		boolean isFirstTime = true;
		boolean isSecondTime = true;

		while (currentBoard.currentCardsOnBoard.size() < maxCards )
		{
			for (int i = 0; i < players.length; i++) {

				System.out.println("Joueur " + (i + 1));
				Card cardOnPlay = this.currentBoard.getCard();

				if (this.players[i].changeVictoryCard(currentBoard))
					System.out.println("Joueur " + (i + 1) + "A change de Victory Card ! ");

				if(!isSecondTime)
				{
					this.players[i].getStrategy().alternateCards(currentBoard);
				}

				if (!isFirstTime)
				{
					this.players[i].getStrategy().moveCard(this.currentBoard);
					isSecondTime = false;
				}

				this.players[i].getStrategy().showVictoryCard(this.players[i].getVictoryCard());
				this.players[i].getStrategy().shuffle(currentBoard);

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
