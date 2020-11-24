import java.util.Map;

public class BoardTriangular extends Board {
    private Card cardsOnBoard;


    
    public boolean isCoordinateCloseEnough(Coordinate coordinate) {
        int nbrCadrsOnThisColumn = 0;
        int nbrCardsOnThisStage = 0;
        boolean adjencyCardExists = false;
        int nbrCardsOnStageBellow = 0;
        int nbrCardsOnStageAbove = 0;
        int nbrCardsOnColumnAtLeft = 0;
        int nbrCardsOnColumnAtRight = 0;

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
            	nbrCardsOnThisStage++;

            if(entry.getKey().getY()  == coordinate.getY() )
            	nbrCadrsOnThisColumn++;
            if(entry.getKey().getX()  == coordinate.getX()+1 )
                nbrCardsOnColumnAtRight++;
            if(entry.getKey().getX()  == coordinate.getX()-1 )
                nbrCardsOnColumnAtLeft++;
            if(entry.getKey().getY()  == coordinate.getY()+1 )
                nbrCardsOnStageAbove++;
            if(entry.getKey().getY()  == coordinate.getY()-1 )
                nbrCardsOnStageBellow++;
        }

        if(adjencyCardExists && nbrCardsOnThisStage < 5 && nbrCadrsOnThisColumn < 5 &&  nbrCadrsOnThisColumn<nbrCardsOnColumnAtRight && nbrCadrsOnThisColumn>nbrCardsOnColumnAtLeft && nbrCardsOnThisStage<nbrCardsOnStageBellow && nbrCardsOnThisStage > nbrCardsOnStageAbove )
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
    

