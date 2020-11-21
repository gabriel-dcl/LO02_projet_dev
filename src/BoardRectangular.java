import java.util.Map;

public class BoardRectangular extends Board {


    public BoardRectangular()
    {
        super();
    }

    public boolean addCardOnBoard(Card card, Coordinate coordinate, boolean firstCardPlaces)
    {
        if(firstCardPlaces)
        {
            currentCardsOnBoard.put(coordinate, card);
            return true;
        }

        int verticalBoardSize = 0;
        int horizontalBoardSize = 0;
        boolean adjencyCardExists = false;

        for (Map.Entry<Coordinate, Card> entry : currentCardsOnBoard.entrySet())
        {
            if (entry.getKey().equals(coordinate))
                return false;

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

        if(adjencyCardExists && horizontalBoardSize < 5 && verticalBoardSize < 3)
        {
            currentCardsOnBoard.put(coordinate, card);
            return true;
        }
        return false;

    }

}



