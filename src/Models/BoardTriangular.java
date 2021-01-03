package Models;

import java.util.Map;

public class BoardTriangular extends Board {
    private Card cardsOnBoard;



    public boolean isCoordinateCloseEnough(Coordinate coordinate) {
        int nbrCadrsOnThisColumn = 1;
        int nbrCardsOnThisStage = 1;
        boolean adjencyCardExists = false;
        int floor= 100;
        int ceiling= 0;
        int leftWall= 100;
        int rightWall = 0;
        int adaptativeCeiling[] = {0 , 0 , 0, 0, 0};

        if(this.currentCardsOnBoard.size()==0)
        {
            if(coordinate.getX()>6 || coordinate.getY()>6)
                return false;
        }

        if(!this.isPlaceAvailable(coordinate))
            return false;




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
        if(coordinate.getX()<leftWall || coordinate.getY()<floor)
            return false;
        else if(coordinate.getX()  > rightWall )
            rightWall = coordinate.getX();
        else if(coordinate.getY()  > ceiling)
            ceiling = coordinate.getY();


        int verticalBoardSize = Math.abs(ceiling - floor);
        int horizontalBoardSize = Math.abs(rightWall - leftWall);
        for(int i=0; i<5;i++) {
            adaptativeCeiling[i]=ceiling-i;
        }

        if(adjencyCardExists)
        {
            if(horizontalBoardSize < 5)
            {
                if(verticalBoardSize < 5)
                    if (coordinate.getY()<=adaptativeCeiling[coordinate.getX()-leftWall]) {
                        return true;
                    }

            }

            if(verticalBoardSize < 5)
            {
                if(horizontalBoardSize < 5)
                    if (coordinate.getY()<=adaptativeCeiling[coordinate.getX()-leftWall]) {
                        return true;
                    }

            }
        }
        return false;
    }

    public boolean addCardOnBoard(Card card, Coordinate coordinate)
    {

        if(this.currentCardsOnBoard.size()==0) {
            if(coordinate.getX()>6 || coordinate.getY()>6)
                return false;
        }
        if(!this.isPlaceAvailable(coordinate))
            return false;

        if(!this.isCoordinateCloseEnough(coordinate))
            return false;

        currentCardsOnBoard.put(coordinate, card);
        return true;
    }

}