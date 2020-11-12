public interface Strategy {

    Board moveCard(Card newCard, Board currentBoard);
    Board placeNewCard(Card newCard, Board currentBoard);
    Board alternateCards(Card card1, Card Card2);
    void showVictoryCard();
    Board shuffle(Board currentBoard);
    void accept(Visitor visitor);
    void changeVictoryCard(Card newCard);

}
