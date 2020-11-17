import enums.Color;
import enums.Form;
import enums.State;

import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.IntStream;

public abstract class Board {
    private GameManager currentGameManager;
    private ArrayList<ArrayList<Card>> currentBoard;
    private Stack<Card> remainingCards;

    public Board()
    {
        State test;
        remainingCards = new Stack<Card>();
        generateRandomStack();
    }

    public void calculateNbCardsOnBoard()
    {

    }

    public Card getNewCard() {
        return null;
    }

    public void accept(Visitor visitor) {
    }

    private void generateRandomStack()
    {
        Card[] allPossibleCard = new Card[18];
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

        int[] values_done = new int[20];

        for (int j = 0; j < 17; j++)
        {
            int current_index =  (int) (Math.random() * 17);

            while( isPresentValueInArray(values_done, current_index) )
                current_index =  (int) (Math.random() * 17);

            remainingCards.add(allPossibleCard[current_index]);
            values_done[i] = current_index;
            System.out.println(allPossibleCard[current_index].toString());
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