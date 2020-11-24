import enums.Color;
import enums.Form;
import enums.State;

import java.util.HashMap;
import java.util.Map;

public class Visitor {
    private HashMap<Color, Integer> PointsByColor;
    private HashMap<Form, Integer> PointsByForm;
    private HashMap<State, Integer> PointsByState;


    public Visitor()
    {
        PointsByColor = new HashMap<Color, Integer>();
        PointsByForm = new HashMap<Form, Integer>();
        PointsByState = new HashMap<State, Integer>();

    }


    public String visit(Board board)
    {

        // LOOK BY COLOGN
        // LOOK BY LINE


        for (Map.Entry<Coordinate, Card> entry : board.getCurrentCardsOnBoard().entrySet())
        {
            entry.getKey();
        }

        return null;
    }


    public Coordinate getTopLeftCorner(Board board)
    {

        Coordinate topLeftCoordinate = new Coordinate(50, 50);
        for (Map.Entry<Coordinate, Card> entry : board.getCurrentCardsOnBoard().entrySet())
        {
           if( entry.getKey().getY() < topLeftCoordinate.getY() && entry.getKey().getX() < topLeftCoordinate.getX() )
            {
                topLeftCoordinate = entry.getKey();
            }
        }


        return topLeftCoordinate;
    }

    public void exploreBoard(Board board) {
    }

    public int getPointsTotal() {
    return 2;
    }




    private void singleton() {
    }

    public void getInstance() {
    }

}
