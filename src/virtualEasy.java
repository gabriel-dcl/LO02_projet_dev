public class virtualEasy implements Strategy {
 
    public int findBestPlacementNewCard(Board currentBoard, Card currentCard, Card victoryCard) {

    return 0;
    }
    public int findNewPlacementCardOnBoard(Board currentBoard, Card currentCard, Card victoryCard) {
   return 0;
    }


    public void placeNewCard(Card newCard, Board currentBoard)
    {

        Coordinate currentCardsPosition = new Coordinate(-1, -1);

        do
            {
                currentCardsPosition.setX((int)(Math.random() * 12));
                currentCardsPosition.setY((int)(Math.random() * 12));
            }while (!currentBoard.isPlaceAvailable(currentCardsPosition) || !currentBoard.isCoordinateCloseEnough(currentCardsPosition) );

            System.out.println(" ----------------- ");

            currentBoard.addCardOnBoard(newCard, currentCardsPosition);
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
        currentBoard.showBoard();

        System.out.println("J'AI BOUGE UNE CARTE");

        Card cardToMove;
        Coordinate currentCardsPosition = new Coordinate(-1, -1);

        do
        {
            currentCardsPosition.setX((int)(Math.random() * 12));
            currentCardsPosition.setY((int)(Math.random() * 12));
        }while (currentBoard.isPlaceAvailable(currentCardsPosition) );


        Coordinate currentCard = currentBoard.findEqualsCoordinate(currentCardsPosition);

        System.out.print("************" + currentCardsPosition.getX() + " " + currentCardsPosition.getY());

        //r�cup�ration de la carte � bouger
        cardToMove = currentBoard.currentCardsOnBoard.get(currentCard);
        currentBoard.currentCardsOnBoard.remove(currentCard);


        currentBoard.showBoard();

        System.out.println("\n \n \n \n");

        Coordinate currentCardsPosition2 = new Coordinate(-1, -1);


        do
        {
            currentCardsPosition2.setX((int)(Math.random() * 12));
            currentCardsPosition2.setY((int)(Math.random() * 12));
        }while (!currentBoard.isPlaceAvailable(currentCardsPosition2) || !currentBoard.isCoordinateCloseEnough(currentCardsPosition2) );


        System.out.println("BITE");


        //Placement de la carte
        currentBoard.addCardOnBoard(cardToMove, currentCardsPosition2);

        currentBoard.showBoard();
        System.out.println("\n \n \n \n");
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
