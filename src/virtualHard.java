import java.awt.*;

public class virtualHard implements Strategy {
	Visitor visitor;
	Card victoryCard;

	public virtualHard(Visitor visitor, Card victoryCard)
	{
		this.visitor = visitor;
		this.victoryCard = victoryCard;
	}

	public void placeNewCard(Card newCard, Board currentBoard) {
		Coordinate bestPosition = new Coordinate(-1, 1);
		int bestValue = 0;
		Coordinate currentCardsPosition = new Coordinate(-1, -1);

		if (currentBoard.getCurrentCardsOnBoard().isEmpty()) {
			currentCardsPosition.setX((int) (Math.random() * 12));
			currentCardsPosition.setY((int) (Math.random() * 12));

			currentBoard.addCardOnBoard(newCard, currentCardsPosition);
			return;
		}

			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 12; j++) {
					currentCardsPosition.setX(i);
					currentCardsPosition.setY(j);

					if (currentBoard.isPlaceAvailable(currentCardsPosition) && currentBoard.isCoordinateCloseEnough(currentCardsPosition)) {
						int potentialMaxPoints = 0;
						currentBoard.addCardOnBoard(newCard, currentCardsPosition);
						currentBoard.accept(visitor);
						potentialMaxPoints = visitor.getPointsTotal();
						currentBoard.currentCardsOnBoard.remove(currentCardsPosition);

						if (potentialMaxPoints >= bestValue) {
							System.out.println("ON SORT");
							bestPosition.setX(currentCardsPosition.getX());
							bestPosition.setY(currentCardsPosition.getY());


							bestValue = potentialMaxPoints;
						}
					}
				}
			}

			currentBoard.addCardOnBoard(newCard, bestPosition);

	}
    
    public Board shuffle(Board currentBoard) {

    return null;
    }
    public void accept(Visitor visitor) {
    }
    public Card changeVictoryCard(Card ancientCard, Board currentBoard) {
    	return null;
    }


	@Override
	public void moveCard(Board currentBoard)
	{
		Coordinate currentCardsPosition = new Coordinate(-1, -1);
		Coordinate potentialNewCoordinate = new Coordinate(-1, -1);
		Coordinate bestPosition = new Coordinate(-1, -1);
		Card bestCardPosition;
		Coordinate positionToMove = new Coordinate(-1, -1);

		int bestValue = this.visitor.getPointsTotalRegardingVictoryCard(victoryCard);
		int currentMaxValue = bestValue;

		for (int i = 0; i < 12; i++)
		{
			for (int j = 0; j < 12; j++)
			{
				currentCardsPosition.setX(i);
				currentCardsPosition.setY(j);

				if ( ! currentBoard.isPlaceAvailable(currentCardsPosition) )
				{

					Coordinate potentialPoints = currentBoard.findEqualsCoordinate(currentCardsPosition);
					Card currentCard = currentBoard.getCardByCoordinate(potentialPoints);
					currentBoard.currentCardsOnBoard.remove(potentialPoints);

					for (int k = 0; k < 12; k++)
					{
						for (int l = 0; l < 12; l++)
						{
							potentialNewCoordinate.setX(k);
							potentialNewCoordinate.setY(l);

							if (currentBoard.isPlaceAvailable(potentialNewCoordinate) && currentBoard.isCoordinateCloseEnough(potentialNewCoordinate))
							{
								int potentialMaxPoints = 0;
								currentBoard.addCardOnBoard(currentCard, potentialNewCoordinate);
								currentBoard.accept(visitor);
								potentialMaxPoints = visitor.getPointsTotal();
								currentBoard.currentCardsOnBoard.remove(potentialNewCoordinate);

								if (potentialMaxPoints > bestValue && (potentialNewCoordinate.getX() != potentialPoints.getX()  && potentialNewCoordinate.getY() != potentialPoints.getY() ))
								{
									bestCardPosition = currentCard;
									bestPosition.setX(potentialNewCoordinate.getX());
									bestPosition.setY(potentialNewCoordinate.getY());

									positionToMove.setX( potentialPoints.getX() );
									positionToMove.setY(  potentialPoints.getY() );

									bestValue = potentialMaxPoints;

								}
							}
						}
					}

					currentBoard.addCardOnBoard(currentCard, potentialPoints);

				}
			}
		}

		if(bestValue > currentMaxValue)
		{

			System.out.println("On change");

			Coordinate toMove = currentBoard.findEqualsCoordinate(positionToMove);

			System.out.println(positionToMove.getX() + " " + positionToMove.getY());
			System.out.println("VERS : " + bestPosition.getX() + " " + bestPosition.getY());


			Card cardToMove = currentBoard.getCardByCoordinate(toMove);

			System.out.println(cardToMove.toString());

			currentBoard.getCurrentCardsOnBoard().remove(toMove);
			currentBoard.addCardOnBoard(cardToMove, bestPosition);
		}
	}


	@Override
	public Board alternateCards(Board currentBoard) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void showVictoryCard(Card victoryCard) {
		// TODO Auto-generated method stub
	}
}
