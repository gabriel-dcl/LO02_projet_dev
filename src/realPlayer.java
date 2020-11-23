import java.util.Collections;
import java.util.Map.Entry;
import java.util.Scanner;
public class realPlayer implements Strategy {
	
	private boolean taken(Coordinate position, Board currentBoard) {
		/*if(currentBoard.currentCardsOnBoard.containsKey(position)) {
    		return true;
    	}
		else
			return false;*/
		for (Entry<Coordinate, Card> entry : currentBoard.currentCardsOnBoard.entrySet())
        {
            if (entry.getKey().equals(position))
                return true;
            	break;
        }
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
    	    	if(!exists)
    	    		System.out.println("Il n'y a pas de carte ici, réessayez");
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
    	    	if (exists)
    	    		System.out.println("Cette place est prise ! Réessayez");
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
    	
    	
    		//Affichage du board pour savoir où placer la carte
        	currentBoard.showBoard();
        	
        	//récupération des coordonnées auxquels placer la carte 
        	boolean exists = true;
        	Coordinate position = new Coordinate(-1, -1);
        	
        	
        	
        	while(exists) {
        		System.out.println("Coordonnées où placer la carte ? \n X ?");
        		position.setX (sc.nextInt());
    	    	
    	    	System.out.println("Y ?");
    	    	position.setY(sc.nextInt());
    	    	
    	    	exists=this.taken(position, currentBoard);
    	    	if(exists)
    	    		System.out.println("Cette place est prise ! Réessayez");
        	}
        	
        	//Placement de la carte
        	currentBoard.currentCardsOnBoard.put(position, newCard);
        	
        	//Affichage du board après placement
        	currentBoard.showBoard();

        	return currentBoard;	
    	
    		
    	
    }
    
    public Board alternateCards(Board currentBoard) {
    	Scanner sc = new Scanner(System.in);
    	//Demande de réaliser cette action
    	
    	
    	
    		//Affichage du board pour savoir où placer la carte
        	currentBoard.showBoard();
        	
        	
        	boolean exists = true;
        	Coordinate position1 = new Coordinate(-1, -1);
        	Coordinate position2 = new Coordinate(-1, 1);
        	Card temp;
        	//Choix de la première carte
        	System.out.println("Choisissez la première carte à alterner");
        	while(!exists) {
        		System.out.println("Coordonnées de la carte ? \n X ?");
        		position1.setX (sc.nextInt());
    	    	
    	    	System.out.println("Y ?");
    	    	position1.setY(sc.nextInt());
    	    	
    	    	exists=this.taken(position1, currentBoard);
    	    	if(!exists)
    	    		System.out.println("Il n'y a pas de carte ici ! Réessayez");
        	}
        	
        	//Choix de la 2e carte
        	System.out.println("Choisissez la seconde carte à alterner");
        	while(!exists) {
        		System.out.println("Coordonnées de la carte ? \n X ?");
        		position1.setX (sc.nextInt());
    	    	
    	    	System.out.println("Y ?");
    	    	position1.setY(sc.nextInt());
    	    	
    	    	exists=this.taken(position1, currentBoard);
    	    	if(!exists)
    	    		System.out.println("Il n'y a pas de carte ici ! Réessayez");
        	}
           	//Alternance des cartes
        	temp = currentBoard.currentCardsOnBoard.get(position1);
        	currentBoard.currentCardsOnBoard.put(position1, currentBoard.currentCardsOnBoard.get(position2));
        	currentBoard.currentCardsOnBoard.put(position2, temp);
        	
        	
    	
    
    	
    

    return currentBoard;
    }
    
    public void showVictoryCard(Card victoryCard) {
    	System.out.println("Voici votre carte victoire :");
    	System.out.println(victoryCard.toString());
    }
    
    public Board shuffle(Board currentBoard) {
    	for (Coordinate currentCardsPosition : currentBoard.currentCardsOnBoard.keySet()) {
    		while (taken(currentCardsPosition, currentBoard)) {
    			currentCardsPosition.setX((int)(Math.random() * 12));
    			currentCardsPosition.setY((int)(Math.random() * 12));
    		}
    	}

    return currentBoard;
    }
    
    public void accept(Visitor visitor) {
    }
    
    public Card changeVictoryCard(Card ancientCard, Board currentBoard) {
    	Scanner sc = new Scanner(System.in);
    	Card newCard;
    	
    	newCard = currentBoard.getCard();
    	currentBoard.remainingCards.add(ancientCard);
    	
    	
    	
    	return newCard;
    
    	
    		
    
    }
}
