import java.util.Map;

public class BoardRectangular extends Board {


    public BoardRectangular()
    {
        super();
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


}
