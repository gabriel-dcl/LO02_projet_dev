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

    public void moveCard(Board currentBoard) {
    	Scanner sc = new Scanner(System.in);
    	//Demande de r�aliser cette action
    	System.out.println("Voulez vous bouger une carte ? (1 | 0) ");
    	int choix = sc.nextInt();

    	if(choix ==0)
    		return;

    	if (choix==1) {
    		//Affichage du board pour choisir la carte � bouger
        	currentBoard.showBoard();
        	
        	//r�cup�ration des coordonn�es de la carte � bouger
        	boolean exists = false;
        	Card cardToMove;
        	Coordinate position = new Coordinate(-1, -1);
        	while(!exists) {
        		System.out.println("Coordonn�es de la carte � bouger ? \n X ?");
        		position.setX (sc.nextInt());
    	    	
    	    	System.out.println("Y ?");
    	    	position.setY(sc.nextInt());
    	    	
    	    	

    	    	exists=this.taken(position, currentBoard);
        	}
        	
        	//r�cup�ration de la carte � bouger
        	cardToMove = currentBoard.currentCardsOnBoard.get(position);
        	currentBoard.currentCardsOnBoard.remove(position);
        	
        	//Affichage du board pour choisir o� bouger la carte
        	currentBoard.showBoard();
        	
        	//R�cup�ration des coordonn�es auxquelles bouger la carte
        	
        	
        	while(exists) {
        		System.out.println("Coordonn�es o� bouger la carte ? \n X ?");
        		position.setX (sc.nextInt());
    	    	
    	    	System.out.println("Y ?");
    	    	position.setY(sc.nextInt());
    	    	
    	    	exists=this.taken(position, currentBoard);
        	}
        	
        	
        	//Placement de la carte
        	currentBoard.currentCardsOnBoard.put(position, cardToMove);
        	

        	// return currentBoard;
        }
    	
    	else System.out.println("Option refus�e");
    	
    //	return currentBoard;
    			
    	
    	
    }

    public void placeNewCard(Card newCard, Board currentBoard, boolean firsTime)
	{
		Scanner sc = new Scanner(System.in);
		//Affichage de la nouvelle carte
		System.out.println("Veuillez poser la carte : ");
		System.out.println(newCard.toString());

		//Affichage du board pour o� placer la carte
		currentBoard.showBoard();

		//r�cup�ration des coordonn�es auxquels placer la carte
		boolean exists = true;
		Coordinate position = new Coordinate(-1, -1);


		while (exists) {
			System.out.print("Coordonnees ou placer la carte ? \nX : ");
			position.setX(sc.nextInt());

			System.out.print("Y : ");
			position.setY(sc.nextInt());

			if (currentBoard.addCardOnBoard(newCard, position, firsTime))
				exists = false;
			else
				System.out.println("Cette place est prise");

			//Affichage du board apr�s placement

		}
		currentBoard.showBoard();
		// return currentBoard; NO NEED TO RETURN, PASSAGE PAR ADRESSE
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
