package Models;

import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class virtualHard implements Strategy {
	Visitor visitor;
	Card victoryCard;
	boolean hasShuffled;
	boolean hasChangedVictoryCard;

	public virtualHard(Visitor visitor, Card victoryCard)
	{
		this.visitor = visitor;
		this.victoryCard = victoryCard;
		hasShuffled = false;
		hasChangedVictoryCard = false;
	}

	public void placeNewCard(Card newCard, Board currentBoard) {
		Coordinate bestPosition = new Coordinate(-1, 1);
		int bestValue = 0;
		Coordinate currentCardsPosition = new Coordinate(-1, -1);

		if (currentBoard.getCurrentCardsOnBoard().isEmpty())
		{
			do{
				currentCardsPosition.setX((int) (Math.random() * 12));
				currentCardsPosition.setY((int) (Math.random() * 12));
			} while(  currentCardsPosition.getX() > 6 || currentCardsPosition.getY() > 6  );

			currentBoard.forceAddCardOnBard(newCard, currentCardsPosition);

			return;
		}

			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 12; j++) {
					currentCardsPosition.setX(i);
					currentCardsPosition.setY(j);

					if (currentBoard.isPlaceAvailable(currentCardsPosition) && currentBoard.isCoordinateCloseEnough(currentCardsPosition))
					{
						int potentialMaxPoints = 0;
						currentBoard.addCardOnBoard(newCard, currentCardsPosition);
						currentBoard.accept(visitor);
						potentialMaxPoints = visitor.getPointsTotal();
						currentBoard.currentCardsOnBoard.remove(currentCardsPosition);

						if (potentialMaxPoints >= bestValue) {
							bestPosition.setX(currentCardsPosition.getX());
							bestPosition.setY(currentCardsPosition.getY());


							bestValue = potentialMaxPoints;
						}
					}
				}
			}

			currentBoard.addCardOnBoard(newCard, bestPosition);

	}

	public void shuffle(Board currentBoard) {
		if(hasShuffled)
			return;

		if(currentBoard.currentCardsOnBoard.size() < 7)
			return;

		if(this.visitor.getPointsTotalRegardingVictoryCard(victoryCard) > 8)
			return;

		int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
		if(randomNum > 60)
		{
			Stack<Card> cards = new Stack<Card>();


			Iterator entries = currentBoard.currentCardsOnBoard.entrySet().iterator();
			while (entries.hasNext())
			{
				Map.Entry thisEntry = (Map.Entry) entries.next();

				Coordinate key = (Coordinate) thisEntry.getKey();
				Object value = thisEntry.getValue();

				cards.add(currentBoard.getCardByCoordinate(key));
				entries.remove();
			}


			for (Card currentCard: cards )
			{
				Coordinate currentCardsPosition = new Coordinate(-1, -1);
				do {
					currentCardsPosition.setX((int)(Math.random() * 12));
					currentCardsPosition.setY((int)(Math.random() * 12));
				}while (!currentBoard.isPlaceAvailable(currentCardsPosition) || !currentBoard.isCoordinateCloseEnough(currentCardsPosition) );

				currentBoard.addCardOnBoard(currentCard, currentCardsPosition);
			}

			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! SHUFFLE");
			hasShuffled = true;
		}

	}


    public void accept(Visitor visitor) {
    }

	public boolean changeVictoryCard(Board currentBoard)
	{

		if(currentBoard.currentCardsOnBoard.size() < 7)
			return false;

		if(hasChangedVictoryCard)
			return false;


		int currentMaxValue = this.visitor.getPointsTotalRegardingVictoryCard(victoryCard);
		int potentialMaxValue = this.visitor.getPointsTotalRegardingVictoryCard(currentBoard.remainingCards.peek());

		if(potentialMaxValue < currentMaxValue)
		{
			hasChangedVictoryCard = true;
			return true;
		}

		return false;
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

					currentBoard.forceAddCardOnBard(currentCard, potentialPoints);

				}
			}
		}

		if(bestValue > currentMaxValue)
		{

			Coordinate toMove = currentBoard.findEqualsCoordinate(positionToMove);
			Card cardToMove = currentBoard.getCardByCoordinate(toMove);
			currentBoard.getCurrentCardsOnBoard().remove(toMove);
			currentBoard.addCardOnBoard(cardToMove, bestPosition);
		}
	}



	@Override
	public void alternateCards(Board currentBoard) {

		Card cardToMove;
		Coordinate currentCardsPosition = new Coordinate(-1, -1);

		do
		{
			currentCardsPosition.setX((int)(Math.random() * 12));
			currentCardsPosition.setY((int)(Math.random() * 12));
		}while (currentBoard.isPlaceAvailable(currentCardsPosition));

		Coordinate coordinateCardToMove1 = currentBoard.findEqualsCoordinate(currentCardsPosition);
		cardToMove = currentBoard.getCardByCoordinate(coordinateCardToMove1);
		currentBoard.currentCardsOnBoard.remove(coordinateCardToMove1);

		Card cardToMove2;
		Coordinate currentCardsPosition2 = new Coordinate(-1, -1);

		do
		{
			currentCardsPosition2.setX((int)(Math.random() * 12));
			currentCardsPosition2.setY((int)(Math.random() * 12));

			while(currentCardsPosition2.getX() == currentCardsPosition.getX() && currentCardsPosition2.getY() == currentCardsPosition.getY())
			{
				currentCardsPosition2.setX((int)(Math.random() * 12));
				currentCardsPosition2.setY((int)(Math.random() * 12));
			}

		}while (currentBoard.isPlaceAvailable(currentCardsPosition2) );

		Coordinate coordinateCardToMove2 = currentBoard.findEqualsCoordinate(currentCardsPosition2);
		cardToMove2 = currentBoard.getCardByCoordinate(coordinateCardToMove2);
		currentBoard.currentCardsOnBoard.remove(coordinateCardToMove2);

		currentBoard.forceAddCardOnBard(cardToMove, coordinateCardToMove2);
		currentBoard.forceAddCardOnBard(cardToMove2, coordinateCardToMove1);
	}

	@Override
	public void showVictoryCard(Card victoryCard) {

	}
}
