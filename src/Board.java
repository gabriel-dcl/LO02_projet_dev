import enums.Color;
import enums.Form;
import enums.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.IntStream;

public abstract class Board {
    private GameManager currentGameManager;
    private HashMap<Coordinate, Card> currentCardsOnBoard;
    private Stack<Card> remainingCards;
    private Card[] allPossibleCard;


    public Board()
    {
        State test;
        remainingCards = new Stack<Card>();
        generateRandomStack();
        currentCardsOnBoard = new HashMap<Coordinate, Card>();
    }

    public boolean addCardOnBoard(Card card, Coordinate coordinate)
    {
        for (Map.Entry<Coordinate, Card> entry : currentCardsOnBoard.entrySet()) {
            if (entry.getKey().equals(coordinate))
                return false;
        }
        currentCardsOnBoard.put(coordinate, card);

        return true;
    }

    public Card getNewRandomCard()
    {
        int random_index =  (int) (Math.random() * 17);
        return allPossibleCard[random_index];
    }

    public void calculateNbCardsOnBoard()
    {
    }


    public void accept(Visitor visitor) {
    }


    /**
     *
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