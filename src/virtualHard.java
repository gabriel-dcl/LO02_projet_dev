public class virtualHard implements Strategy {
	Visitor visitor;
	Card victoryCard;

	public virtualHard(Visitor visitor, Card victoryCard)
	{
		this.visitor = visitor;
		this.victoryCard = victoryCard;
	}

	public void placeNewCard(Card newCard, Board currentBoard)
	{
		Coordinate bestPosition = new Coordinate(-1, 1);
		int bestValue = 0;
		Coordinate currentCardsPosition = new Coordinate(-1, -1);

		if(currentBoard.getCurrentCardsOnBoard().isEmpty())
		{
			currentCardsPosition.setX((int)(Math.random() * 12));
			currentCardsPosition.setY((int)(Math.random() * 12));

			currentBoard.addCardOnBoard(newCard, currentCardsPosition);
			return;
		}



		for (int i = 0; i < 12; i++)
		{
			for (int j = 0; j < 12; j++)
			{
				currentCardsPosition.setX(i);
				currentCardsPosition.setY(j);

				if(currentBoard.isPlaceAvailable(currentCardsPosition) && currentBoard.isCoordinateCloseEnough(currentCardsPosition))
				{
					int potentialMaxPoints = 0;
					currentBoard.addCardOnBoard(newCard, currentCardsPosition);
					currentBoard.accept(visitor);
					potentialMaxPoints = visitor.getPointsTotal();
					currentBoard.currentCardsOnBoard.remove(currentCardsPosition);

					if(potentialMaxPoints >= bestValue)
					{
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
	public void moveCard(Board currentBoard) {
		// TODO Auto-generated method stub
		return;
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
