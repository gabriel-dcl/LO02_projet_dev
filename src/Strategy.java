public interface Strategy {

    void moveCard( Board currentBoard);
    void placeNewCard(Card newCard, Board currentBoard);
    Board alternateCards(Board currentBoard);
    void showVictoryCard(Card victoryCard);
    Board shuffle(Board currentBoard);
    void accept(Visitor visitor);
    Card changeVictoryCard(Card ancientCard, Board currentBoard);

}
