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

        for(State tempState : State.values())
        {
            PointsByState.put(tempState, 0);
        }
        for(Color tempColor : Color.values())
        {
            PointsByColor.put(tempColor, 0);
        }
        for(Form tempForm : Form.values())
        {
            PointsByForm.put(tempForm, 0);
        }

    }


    public String visit(Board currentBoard)
    {
        // LOOK BY LINE

        Coordinate topLeftCorner = this.getTopLeftCorner(currentBoard);
        Coordinate currentCoordinate = topLeftCorner;

        // LOOK BY COLON
        boolean isLookingByColumn = true;
        Card lastCard = null, beforeLast = null;


        while(isLookingByColumn)
        {
            boolean isLookingByLine = true;
            while(isLookingByLine)
            {
                if(lastCard != null)
                {
                    if(lastCard.getCard_state() == currentBoard.getCardByCoordinate(currentCoordinate).getCard_state() )
                    {
                        PointsByState.put(lastCard.getCard_state(), PointsByState.get(lastCard.getCard_state()) +1);
                    }
                    if(beforeLast != null)
                    {
                        if(lastCard.getCard_color() == currentBoard.getCardByCoordinate(currentCoordinate).getCard_color() )
                        {
                            PointsByColor.put(lastCard.getCard_color(), PointsByColor.get(lastCard.getCard_color()) +1);
                        }

                        if(lastCard.getCard_form() == currentBoard.getCardByCoordinate(currentCoordinate).getCard_form() )
                        {
                            PointsByForm.put(lastCard.getCard_form(), PointsByForm.get(lastCard.getCard_form()) +1);
                        }
                    }
                }

                Coordinate potentielNewPoint = new Coordinate( currentCoordinate.getX() + 1, currentCoordinate.getY());

                if( ! currentBoard.isPlaceAvailable(potentielNewPoint ) )
                {
                    beforeLast = lastCard;
                    lastCard = currentBoard.getCardByCoordinate(currentCoordinate);
                    currentCoordinate = potentielNewPoint;
                } else
                {
                    isLookingByLine = false;
                }
            }

            Coordinate potentielNewPoint = new Coordinate( topLeftCorner.getX(), currentCoordinate.getY() + 1);
            if( ! currentBoard.isPlaceAvailable(potentielNewPoint ) )
            {
                lastCard = null;
                beforeLast = null;
                currentCoordinate = potentielNewPoint;
                isLookingByLine = true;
            } else
            {
                isLookingByColumn = false;
            }
        }

        System.out.println(PointsByForm.get(Form.TRIANGULAR));
        System.out.println(PointsByForm.get(Form.RECTANGULAR));
        System.out.println(PointsByForm.get(Form.CIRCLE));
        System.out.println(PointsByColor.get(Color.BLUE));
        System.out.println(PointsByColor.get(Color.GREEN));
        System.out.println(PointsByColor.get(Color.RED));
        System.out.println(PointsByState.get(State.EMPTY));
        System.out.println(PointsByState.get(State.FILL));

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
