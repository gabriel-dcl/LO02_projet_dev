public class BoardDisk extends Board {
    private Card cardsOnBoard;

    @Override
    public boolean addCardOnBoard(Card card, Coordinate coordinate) {
        return false;
    }
}
