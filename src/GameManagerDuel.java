import java.util.Scanner;

public class GameManagerDuel extends GameManager {
    @Override
    public void game() {
    	Scanner sc = new Scanner(System.in);
		boolean changedVCard = false;
		boolean playedTwice = false;
		while (currentBoard.currentCardsOnBoard.size()<15) {
    		for (int i = 0; i<3; i++) {
    			System.out.println("Joueur "+(i+1) );
        		Card cardOnPlay = this.currentBoard.getCard();
        		this.players[i].getStrategy().showVictoryCard(this.players[i].getVictoryCard());
        		if(i>0) {
        			this.currentBoard = this.players[i].getStrategy().moveCard( this.currentBoard);
        		}       			
        		this.currentBoard = this.players[i].getStrategy().placeNewCard(cardOnPlay, this.currentBoard);
        		
        		System.out.println("Voulez-vous utiliser un bonus ?");
        		int choix = sc.nextInt();
        		
        		if (choix==1) {
        			System.out.println("Quel bonus voulez-vous utiliser?");
        			if(!changedVCard) {
        				System.out.println("1 : Changer de Victory Card");
        			}
        			if(!playedTwice) {
        				System.out.println("2 : Jouer une 2e fois");
        			}
        			int choixBonus = sc.nextInt();
        			if(!changedVCard && choixBonus == 1) {
        				this.players[i].setVictoryCard(this.players[i].getStrategy().changeVictoryCard(this.players[i].getVictoryCard(), currentBoard));     
        				changedVCard = true;
        			}
        			if(!playedTwice && choixBonus == 2) {
        				i--;     
        				playedTwice= true;
        			}
        			
        		}
        	}
    	}
    }
}
