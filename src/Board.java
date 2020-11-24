import enums.Color;
import enums.Form;
import enums.State;

import java.security.cert.CertificateParsingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.IntStream;

public abstract class Board {
    protected GameManager currentGameManager;
    protected HashMap<Coordinate, Card> currentCardsOnBoard;
    protected Stack<Card> remainingCards;
    protected Card[] allPossibleCard;


    public Board()
    {
        remainingCards = new Stack<Card>();
        generateRandomStack();
        currentCardsOnBoard = new HashMap<Coordinate, Card>();
        
    }

    public boolean isCoordinateCloseEnough(Coordinate coordinate) {
      return false;
    }

    public boolean isPlaceAvailable(Coordinate coordinate) {
        for (Map.Entry<Coordinate, Card> entry : currentCardsOnBoard.entrySet()) {
            if (entry.getKey().equals(coordinate))
                return false;
        }
        return true;
    }

    public Coordinate findEqualsCoordinate(Coordinate coordinate) {
        for (Map.Entry<Coordinate, Card> entry : currentCardsOnBoard.entrySet()) {
            if (entry.getKey().equals(coordinate))
                return entry.getKey();
        }
        return null;
    }

    public boolean isPlaceTaken(Coordinate coordinate) {
        return !this.isPlaceAvailable(coordinate);
    }


    public abstract boolean addCardOnBoard(Card card, Coordinate coordinate);


    public Card getNewRandomCard()      //Usefull for VictoryCard Ge    neration
    {
        int random_index =  (int) (Math.random() * 17);
        return allPossibleCard[random_index];
    }



    public Card getCard()
    {
        return remainingCards.pop();
    }

    public void calculateNbCardsOnBoard()
    {

    }


    public void accept(Visitor visitor) {
    }

    public void showBoard()
    {
        for (int i = 0; i < 12; i++)
        {
            for (int j = 0; j < 12; j++)
            {
                boolean hasFound = false;

                for (Map.Entry<Coordinate, Card> entry : currentCardsOnBoard.entrySet())
                {
                    if (entry.getKey().equals( new Coordinate(i, j)) )
                    {
                        System.out.print("A");
                        hasFound = true;
                    }
                }
                  if(!hasFound)
                      System.out.print("*");

            }
            System.out.println();
        }
    }


    public void findClosestCard(Coordinate wantedPosition)
    {


        for (int i = 0; i < 12; i++)
        {
            for (int j = 0; j < 12; j++)
            {
                for (Map.Entry<Coordinate, Card> entry : currentCardsOnBoard.entrySet())
                {
                    if (entry.getKey().equals( new Coordinate(i, j)) )
                        System.out.print("A");
                    else
                        System.out.print("*");
                }
            }
            System.out.println();
        }


    }


    /**
     *  Use tu generate a randomStack
     */
    private void generateRandomStack()
    {
        allPossibleCard = new Card[18];
        int[] values_done = new int[20];
        int i = 0;

        for(State temp_state : State.values())
        {
            for(Color color_temp : Color.values())
            {
                for(Form form_temp : Form.values())
                {
                    allPossibleCard[i] = new Card(temp_state, color_temp, form_temp);
                    i++;
                }
            }
        }

        for (int j = 0; j < 17; j++)
        {
            int current_index =  (int) (Math.random() * 17);

            while( isPresentValueInArray(values_done, current_index) )
                current_index =  (int) (Math.random() * 17);

            remainingCards.add(allPossibleCard[current_index]);
            values_done[i] = current_index;
        }
    }

    /**
     * @param array
     * @param value
     * @return
     */
    private boolean isPresentValueInArray(int[] array, int value)
    {
        for (int a: array)
        {
            if(a == value)
                return true;
        }
        return false;
    }
}

