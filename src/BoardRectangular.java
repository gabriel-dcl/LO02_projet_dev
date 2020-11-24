import java.util.Map;

public class BoardRectangular extends Board {


    public BoardRectangular()
    {
        super();
    }


    public boolean isCoordinateCloseEnough(Coordinate coordinate) {
        int verticalBoardSize = 0;
        int horizontalBoardSize = 0;
        boolean adjencyCardExists = false;
        int floor= 0;
        int ceiling= 0;
        int leftWall= 0;
        int rightWall = 0;

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

            if(entry.getKey().getX()  > rightWall )
                rightWall = entry.getKey().getX();
            if(entry.getKey().getX()  < leftWall )
                leftWall = entry.getKey().getX();

            if(entry.getKey().getY()  > ceiling)
                ceiling = entry.getKey().getY() ;
            if(entry.getKey().getY()  < floor)
                floor = entry.getKey().getY() ;
        }
        
        if(coordinate.getX()  > rightWall )
            rightWall = coordinate.getX();
        if(coordinate.getX()  < leftWall )
            leftWall = coordinate.getX();

        if(coordinate.getY()  > ceiling)
            ceiling = coordinate.getY();
        if(coordinate.getY()  < floor)
            floor = coordinate.getY() ;
        
        
        verticalBoardSize = ceiling-floor;
        horizontalBoardSize = rightWall - leftWall;

        if(adjencyCardExists && horizontalBoardSize < 5 && verticalBoardSize < 3)
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



