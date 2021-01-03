package Models;

import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class VirtualEasy implements Strategy {

    Visitor visitor;
    boolean hasChangedVictoryCard;

    public VirtualEasy()
    {
        hasChangedVictoryCard = false;
    }

    public int findBestPlacementNewCard(Board currentBoard, Card currentCard, Card victoryCard) {

    return 0;
    }
    public int findNewPlacementCardOnBoard(Board currentBoard, Card currentCard, Card victoryCard) {
   return 0;
    }

    public VirtualEasy(Visitor visitor)
    {
        this.visitor = visitor;
    }

    public void placeNewCard(Card newCard, Board currentBoard)
    {
        Coordinate currentCardsPosition = new Coordinate(-1, -1);

        do
            {
                currentCardsPosition.setX((int)(Math.random() * 12));
                currentCardsPosition.setY((int)(Math.random() * 12));
            }while (!currentBoard.isPlaceAvailable(currentCardsPosition) || !currentBoard.isCoordinateCloseEnough(currentCardsPosition) );


         currentBoard.addCardOnBoard(newCard, currentCardsPosition);
    }
    

    public boolean shuffle(Board currentBoard) {


        int randomNum = ThreadLocalRandom.current().nextInt(0, 100);

        if(randomNum > 80)
        {
            currentBoard.shuffle();

            return true;
        }
        return false;
    }

    public void accept(Visitor visitor) {
    }

    public boolean changeVictoryCard(Board currentBoard)
    {
        if(hasChangedVictoryCard)
            return false;

        int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        if(randomNum > 75)
        {
            hasChangedVictoryCard = true;
            return true;
        }

        return false;
    }

	@Override
	public void moveCard(Board currentBoard)
    {

        Card cardToMove;
        Coordinate currentCardsPosition = new Coordinate(-1, -1);

        do
        {
            currentCardsPosition.setX((int)(Math.random() * 12));
            currentCardsPosition.setY((int)(Math.random() * 12));
        }while (currentBoard.isPlaceAvailable(currentCardsPosition));



        Coordinate currentCard = currentBoard.findEqualsCoordinate(currentCardsPosition);

          //récupération de la carte à bouger
        cardToMove = currentBoard.currentCardsOnBoard.get(currentCard);
        currentBoard.currentCardsOnBoard.remove(currentCard);

        Coordinate currentCardsPosition2 = new Coordinate(-1, -1);

        do
        {
            currentCardsPosition2.setX((int)(Math.random() * 12));
            currentCardsPosition2.setY((int)(Math.random() * 12));
        }while (!currentBoard.isPlaceAvailable(currentCardsPosition2) || !currentBoard.isCoordinateCloseEnough(currentCardsPosition2) );

        //Placement de la carte
        currentBoard.addCardOnBoard(cardToMove, currentCardsPosition2);

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
        }while (currentBoard.isPlaceAvailable(currentCardsPosition2));

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
