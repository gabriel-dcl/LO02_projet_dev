package Models;

import java.util.Map;

/**
 * Class qui correspond à un plateau carré, héritant de la class Plateau
 * @version 1.0
 * @author Nicolas Felixine
 * @see Card
 */
public class BoardSquare extends Board {

    public BoardSquare() {
        super();
    }

    /**
     * Regarde si les coordonnées entrées en paramètre sont suffisament proche pour qu'on puisse y insérer une carte.
     *
     * @param coordinate Les coordonnées à tester
     * @return True si les coordonnées sont bien placées, False sinon
     */
    public boolean isCoordinateCloseEnough(Coordinate coordinate) {
        int verticalBoardSize = 0;
        int horizontalBoardSize = 0;
        boolean adjencyCardExists = false;
        int floor= 20;
        int ceiling= 0;
        int leftWall= 50;
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


        verticalBoardSize = Math.abs(ceiling - floor);
        horizontalBoardSize = Math.abs(rightWall - leftWall);


        if(adjencyCardExists)
        {
            if(horizontalBoardSize < 4)
            {
                if(verticalBoardSize < 4)
                    return true;
            }

            if(verticalBoardSize < 4)
            {
                if(horizontalBoardSize < 4)
                    return true;
            }
        }

        return false;
    }

    /**
     * Permet d'insérer une carte au plateau en accord avec les restrictions posées par le type de plateau actuel.
     *
     * @param card       La carte à ajouter
     * @param coordinate Les coordonnées où l'ajouter
     * @return True si l'ajout s'est fait sans encombre, False sinon
     */
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