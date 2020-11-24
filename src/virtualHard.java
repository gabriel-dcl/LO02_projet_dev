public class virtualHard implements Strategy {


	public void placeNewCard(Card newCard, Board currentBoard)
	{
		Coordinate bestPosition = new Coordinate(-1, 1);
		int bestValue = 0;

/*
		Coordinate currentCardsPosition = new Coordinate(-1, -1);


			do
			{
				currentCardsPosition.setX((int)(Math.random() * 12));
				currentCardsPosition.setY((int)(Math.random() * 12));


			}while (!currentBoard.isPlaceAvailable(currentCardsPosition) || !currentBoard.isCoordinateCloseEnough(currentCardsPosition) );



			System.out.println(currentCardsPosition.getX());
			System.out.println(currentCardsPosition.getY());

			currentBoard.addCardOnBoard(newCard, currentCardsPosition);

			Coordinate currentCard = currentBoard.findEqualsCoordinate(currentCardsPosition);

			System.out.print("************" + currentCardsPosition.getX() + " " + currentCardsPosition.getY());


			//récupération de la carte à bouger
		//	cardToMove = currentBoard.currentCardsOnBoard.get(currentCard);
		//	currentBoard.currentCardsOnBoard.remove(currentCard);
*/

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
