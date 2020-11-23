import java.util.Collections;
import java.util.Map.Entry;
import java.util.Scanner;
public class realPlayer implements Strategy {

    public void moveCard(Board currentBoard)
	{
		Scanner sc = new Scanner(System.in);

    	//Demande de réaliser cette action
    	System.out.println("Voulez vous bouger une carte ? 1 | 0");
    	int choix = sc.nextInt();
    	
    	if (choix==1)
    	{
    		Card cardToMove;

			Coordinate position = askPlayerCoordinates(currentBoard, false);

			Coordinate currentCard =  currentBoard.findEqualsCoordinate(position);

        	//récupération de la carte à bouger
        	cardToMove = currentBoard.currentCardsOnBoard.get(currentCard);

        	currentBoard.currentCardsOnBoard.remove(currentCard);

			currentBoard.showBoard();

			 position = askPlayerCoordinates(currentBoard, true);
        	
        	//Placement de la carte
			currentBoard.addCardOnBoard(cardToMove, position);
        }
    	
    	else System.out.println("Option refusée");

    }
    	
    public void placeNewCard(Card newCard, Board currentBoard)
	{
    	Scanner sc = new Scanner(System.in);
    	//Affichage de la nouvelle carte
    	System.out.println("Voici la carte a poser : /n" + newCard.toString());

		Coordinate position = askPlayerCoordinates(currentBoard, true);
		currentBoard.addCardOnBoard(newCard, position);
    }



    public Coordinate askPlayerCoordinates(Board currentBoard, boolean searchForFreePlace)
	{
		Scanner sc = new Scanner(System.in);

		boolean exists = true;
		Coordinate position = new Coordinate(-1, -1);

		while(exists) {
			if(searchForFreePlace)
				System.out.print("Coordonnees ou placer la carte ? \n X :");
			else
				System.out.print("Coordonnées de la carte a deplacer ? \n X :");

			position.setX (sc.nextInt());

			System.out.println("Y :");
			position.setY(sc.nextInt());

			if(searchForFreePlace)
			{
				if(!currentBoard.isPlaceAvailable(position))
					System.out.println("Cette place est prise  ! Reessayez");
				else if( !currentBoard.isCoordinateCloseEnough(position))
					System.out.println("Cette place  trop loin ! Reessayez");
				else
					exists = false;
			} else
			{
				if(currentBoard.isPlaceAvailable(position) )
					System.out.println("Cette place n'est pas occupee ! Reessayez");
				else
					exists = false;
			}

		}

		return position;

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
    	    	
    	 //   	exists=this.taken(position1, currentBoard);
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
    	    	
    	    	// exists=this.taken(position1, currentBoard);
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
    		while ( ! currentBoard.isPlaceAvailable(currentCardsPosition) ) {
    			currentCardsPosition.setX((int)(Math.random() * 12));
    			currentCardsPosition.setY((int)(Math.random() * 12));
    		}
    	}

    return currentBoard;
    }
    
    public void accept(Visitor visitor) {
    }
    
    public Card changeVictoryCard(Card ancientCard, Board currentBoard) {

    	
    	Card newCard;
    	
    	newCard = currentBoard.getCard();
    	currentBoard.remainingCards.add(ancientCard);
    	
    	
    	
    	return newCard;
    
    	
    		
    
    }
}
