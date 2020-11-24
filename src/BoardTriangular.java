import java.util.Map;

public class BoardTriangular extends Board {
    private Card cardsOnBoard;


    
    public boolean isCoordinateCloseEnough(Coordinate coordinate) {
        int verticalBoardSize = 0;
        int horizontalBoardSize = 0;
        boolean adjencyCardExists = false;

        if(currentCardsOnBoard.entrySet().isEmpty())
            return true;

        for (Map.Entry<Coordinate, Card> entry : currentCardsOnBoard.entrySet())
        {
            if(!adjencyCardExists)
            {
                if( ( entry.getKey().getX() == (coordinate.getX() -1) && entry.getKey().getY() == coordinate.getY() )
                        || ( entry.getKey().getX() == (coordinate.getX() +1) && entry.getKey().getY() == coordinate.getY() )
                        || ( entry.getKey().getX() == (coordinate.getX()) && entry.getKey().getY() == coordinate.getY() + 1 )
                        || ( entry.getKey().getX() == (coordinate.getX()) && entry.getKey().getY() == coordinate.getY() - 1 )
                )
                {
                    adjencyCardExists = true;
                }
            }

            if(entry.getKey().getX()  == coordinate.getX() )
                horizontalBoardSize++;

            if(entry.getKey().getY()  == coordinate.getY() )
                verticalBoardSize++;
        }

        if(adjencyCardExists && horizontalBoardSize < 5 && verticalBoardSize < 5 && coordinate.getX()+coordinate.getY() <5)
        {
            return true;
        }

        return false;
    }


    public boolean addCardOnBoard(Card card, Coordinate coordinate)
    {

        if(!this.isPlaceAvailable(coordinate))
                return false;

        if(!this.isCoordinateCloseEnough(coordinate))
                return false;

        currentCardsOnBoard.put(coordinate, card);
        return true;
    }

}
    

