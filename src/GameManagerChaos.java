import java.util.Scanner;

public abstract class  GameManagerChaos extends GameManager {
private int difficulty;
	
	
	public void game() {
		Scanner sc = new Scanner(System.in);
		boolean alternated = false;
		boolean shuffled = false;
		while (currentBoard.currentCardsOnBoard.size()<15) {
    		for (int i = 0; i<3; i++) {
    			System.out.println("Joueur "+(i+1) );
        		Card cardOnPlay = this.currentBoard.getCard();
        		this.players[i].getStrategy().showVictoryCard(this.players[i].getVictoryCard());
        		if(i>0) {
        			this.players[i].getStrategy().moveCard( this.currentBoard);
        		}       			
        		this.players[i].getStrategy().placeNewCard(cardOnPlay, this.currentBoard);
        		
        		System.out.println("Voulez-vous utiliser un bonus ?");
        		int choix = sc.nextInt();
        		
        		if (choix==1) {
        			System.out.println("Quel bonus voulez-vous utiliser?");
        			if(!shuffled) {
        				System.out.println("1 : Shuffle");
        			}
        			if(!alternated) {
        				System.out.println("2 : Echanger 2 cartes");
        			}
        			int choixBonus = sc.nextInt();
        			if(!shuffled && choixBonus == 1) {
        				this.currentBoard = this.players[i].getStrategy().shuffle(currentBoard);       
        				shuffled = true;
        			}
        			if(!alternated && choixBonus == 2) {
        				this.currentBoard = this.players[i].getStrategy().alternateCards(currentBoard);    
        				alternated = true;
        			}
        			
        		}
        	}
    	}
	}

	
	public void gameOver() {
		
		super.gameOver();
	}

	@Override
	public void occure() {
		
		super.occure();
	}

	@Override
	public void getInstance() {
		
		super.getInstance();
	}
	
}
