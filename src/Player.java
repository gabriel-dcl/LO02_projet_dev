import java.util.Scanner;
public class Player{


    //Variables initialisation

    private boolean isReal;
    private Card victoryCard;
    private int nbPoints;
    private Visitor visitor;
    private Strategy strategy;
    public GameManager manager;
    protected Scanner sc;


    public Player(Card victoryCard)
    {
            strategy = new realPlayer();
            this.victoryCard = victoryCard;
    }

    public Player(int difficulty, Card victoryCard)
    {
        if(difficulty == 0)
            strategy = new virtualEasy();
        else
            strategy = new virtualHard();

        this.victoryCard = victoryCard;
    }
    
    


	public Strategy getStrategy() {
		return strategy;
	}

	public Card getVictoryCard() {
		return victoryCard;
	}
	public void setVictoryCard(Card newCard) {
		this.victoryCard = newCard;
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
