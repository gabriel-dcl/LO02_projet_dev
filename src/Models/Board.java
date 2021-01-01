package Models;

import enums.Color;
import enums.Form;
import enums.State;

import java.util.*;

public abstract class Board extends Observable {
    protected GameManager currentGameManager;
    protected Visitor visitor;
    protected HashMap<Coordinate, Card> currentCardsOnBoard;
    protected Stack<Card> remainingCards;
    protected Card[] allPossibleCard;

    public Map<Coordinate, Card> getCurrentCardsOnBoard() {
        return currentCardsOnBoard;
    }

    public void setCurrentCardsOnBoard(HashMap<Coordinate, Card> currentCardsOnBoard) {
        this.currentCardsOnBoard = currentCardsOnBoard;
    }

    public void shuffle()
    {
        Stack<Card> cards = new Stack<Card>();


        Iterator entries = this.currentCardsOnBoard.entrySet().iterator();
        while (entries.hasNext())
        {
            Map.Entry thisEntry = (Map.Entry) entries.next();

            Coordinate key = (Coordinate) thisEntry.getKey();
            Object value = thisEntry.getValue();

            cards.add(this.getCardByCoordinate(key));
            entries.remove();
        }

        for (Card currentCard: cards )
        {
            Coordinate currentCardsPosition = new Coordinate(-1, -1);
            do {
                currentCardsPosition.setX((int)(Math.random() * 12));
                currentCardsPosition.setY((int)(Math.random() * 12));
            }while (!this.isPlaceAvailable(currentCardsPosition) || !this.isCoordinateCloseEnough(currentCardsPosition) );

            this.addCardOnBoard(currentCard, currentCardsPosition);
        }
    }


    public Board()
    {
        visitor = new Visitor();
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


    public Card getNewRandomCard()      //Usefull for VictoryCard Generation
    {
     //   int random_index =  (int) (Math.random() * 17);
       // return allPossibleCard[random_index];

    return remainingCards.pop();
    }

    public Card getCardByCoordinate(Coordinate coordinate)
    {
       return currentCardsOnBoard.get(this.findEqualsCoordinate(coordinate));
    }

    public Card getCard()
    {
        return remainingCards.pop();
    }

    public void calculateNbCardsOnBoard()
    {

    }

    public void forceAddCardOnBard(Card card, Coordinate coordinate)
    {
        currentCardsOnBoard.put(coordinate, card);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
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
                        Card tempCard = this.getCardByCoordinate(entry.getKey());
                        System.out.print(tempCard.toString());
                        hasFound = true;
                    }
                }
                  if(!hasFound)
                      System.out.print("  *  ");

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
        int[] values_done = new int[20];
        int i = 0;

        for(State temp_state : State.values())
        {
            for(Color color_temp : Color.values())
            {
                for(Form form_temp : Form.values())
                {
                    remainingCards.add(new Card(temp_state, color_temp, form_temp));
                    i++;
                }
            }
        }

        Collections.shuffle(remainingCards);
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

