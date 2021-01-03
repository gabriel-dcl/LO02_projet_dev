package Models;

public interface Strategy {

    void moveCard( Board currentBoard);
    void placeNewCard(Card newCard, Board currentBoard);
    void alternateCards(Board currentBoard);
    void showVictoryCard(Card victoryCard);
    boolean shuffle(Board currentBoard);
    void accept(Visitor visitor);
    boolean changeVictoryCard(Board currentBoard);

}
