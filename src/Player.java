
public class Player implements Strategy {

    private boolean isReal;


    private Card VictoryCard;


    private int nbPoints;


    private Visitor visitor;


    private Strategy strategy;


    public GameManager manager;

    @Override
    public Board moveCard(Card newCard, Board currentBoard) {
        return null;
    }

    @Override
    public Board placeNewCard(Card newCard, Board currentBoard) {
        return null;
    }

    @Override
    public Board alternateCards(Card card1, Card Card2) {
        return null;
    }

    @Override
    public void showVictoryCard() {

    }

    @Override
    public Board shuffle(Board currentBoard) {
        return null;
    }

    @Override
    public void accept(Visitor visitor) {

    }

    @Override
    public void changeVictoryCard(Card newCard) {

    }




/*    public Board moveCard(Card newCard, Board currentBoard) {

        return;
    }


    public Board placeNewCard(Card newCard, Board currentBoard) {
    }


    public Board alternateCards(Card card1, Card Card2) {
    }


    public void showVictoryCard() {
    }


    public Board shuffle(Board currentBoard) {
    }


    public void accept(Visitor visitor) {
    }


    public void changeVictoryCard(Card newCard) {
    }
*/
}
