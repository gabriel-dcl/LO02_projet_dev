import java.util.Collections;
import java.util.Scanner;
public class realPlayer implements Strategy {
	
	private boolean taken(Coordinate position, Board currentBoard) {
		if(currentBoard.currentCardsOnBoard.containsKey(position)) {
    		return true;
    	}
		else
			return false;
	}
	
    public Board moveCard(Board currentBoard) {
    	Scanner sc = new Scanner(System.in);
    	//Demande de réaliser cette action
    	System.out.println("Voulez vous bouger une carte ?");
    	int choix = sc.nextInt();
    	
    	if (choix==1) {
    		//Affichage du board pour choisir la carte à bouger
        	currentBoard.showBoard();
        	
        	//récupération des coordonnées de la carte à bouger 
        	boolean exists = false;
        	Card cardToMove;
        	Coordinate position = new Coordinate(-1, -1);
        	while(!exists) {
        		System.out.println("Coordonnées de la carte à bouger ? \n X ?");
        		position.setX (sc.nextInt());
    	    	
    	    	System.out.println("Y ?");
    	    	position.setY(sc.nextInt());
    	    	
    	    	
    	    	
    	    	exists=this.taken(position, currentBoard);
        	}
        	
        	//récupération de la carte à bouger
        	cardToMove = currentBoard.currentCardsOnBoard.get(position);
        	currentBoard.currentCardsOnBoard.remove(position);
        	
        	//Affichage du board pour choisir où bouger la carte
        	currentBoard.showBoard();
        	
        	//Récupération des coordonnées auxquelles bouger la carte
        	
        	
        	while(exists) {
        		System.out.println("Coordonnées où bouger la carte ? \n X ?");
        		position.setX (sc.nextInt());
    	    	
    	    	System.out.println("Y ?");
    	    	position.setY(sc.nextInt());
    	    	
    	    	exists=this.taken(position, currentBoard);
        	}
        	
        	
        	//Placement de la carte
        	currentBoard.currentCardsOnBoard.put(position, cardToMove);
        	

        	return currentBoard;
        }
    	
    	else System.out.println("Option refusée");
    	
    	return currentBoard;
    			
    	
    	
    }
    	
    	
    
    public Board placeNewCard(Card newCard, Board currentBoard) {
    	Scanner sc = new Scanner(System.in);
    	//Affichage de la nouvelle carte
    	System.out.println("Veuillez poser la carte : ");
    	System.out.println(newCard.toString());
    	
    	
    		//Affichage du board pour où placer la carte
        	currentBoard.showBoard();
        	
        	//récupération des coordonnées auxquels placer la carte 
        	boolean exists = true;
        	Coordinate position = new Coordinate(-1, -1);
        	
        	
        	
        	while(exists) {
        		System.out.println("Coordonnées où placer la carte ? \n X ?");
        		position.setX (sc.nextInt());
    	    	
    	    	System.out.println("Y ?");
    	    	position.setY(sc.nextInt());
    	    	
    	    	if(!currentBoard.currentCardsOnBoard.keySet().contains(position)) {
    	    		exists = false;
    	    		
    	    	}
    	    	else 
    	    		System.out.println("Cette place est prise");
        	}
        	
        	//Placement de la carte
        	currentBoard.currentCardsOnBoard.put(position, newCard);
        	
        	//Affichage du board après placement
        	currentBoard.showBoard();

        	return currentBoard;	
    	
    		
    	
    }
    
    public Board alternateCards(Board currentBoard) {

    return null;
    }
    public void showVictoryCard(Card victoryCard) {
    	System.out.println("Voici votre carte victoire :");
    	System.out.println(victoryCard.toString());
    }
    public Board shuffle(Board currentBoard) {
    	for (Coordinate currentCardsPosition : currentBoard.currentCardsOnBoard.keySet()) {
    		while (taken(currentCardsPosition, currentBoard)) {
    			//currentCardsPosition.setX(int(Math.random()));
    		}
    	}

    return null;
    }
    public void accept(Visitor visitor) {
    }
    public void changeVictoryCard(Card newCard) {
    }

}
