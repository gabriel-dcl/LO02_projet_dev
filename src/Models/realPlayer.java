package Models;

import java.util.*;
import java.util.Map.Entry;

public class realPlayer implements Strategy {

	boolean hasShuffled;
	boolean hasChangedVictoryCard;

	public  realPlayer()
	{
		hasShuffled = false;
		hasChangedVictoryCard = false;
	}

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



			 position = askPlayerCoordinates(currentBoard, true);
        	
        	//Placement de la carte
			currentBoard.addCardOnBoard(cardToMove, position);
        }
    	
    	else System.out.println("Option refusée");

    }
    	
    public void placeNewCard(Card newCard, Board currentBoard)
	{
		System.out.println();
		currentBoard.showBoard();

    	Scanner sc = new Scanner(System.in);
    	//Affichage de la nouvelle carte
		System.out.println("_____");
    	System.out.println("Voici la carte a poser : \n " + newCard.toString());

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
					System.out.print("Cette place est prise  ! Reessayez");
				else if( !currentBoard.isCoordinateCloseEnough(position))
					System.out.print("Cette place  trop loin ! Reessayez");
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

    public void alternateCards(Board currentBoard) {
    	Scanner sc = new Scanner(System.in);
    	//Demande de réaliser cette action

    		//Affichage du board pour savoir où placer la carte
        	currentBoard.showBoard();

        	boolean exists = true;
        	Coordinate position1 = new Coordinate(-1, -1);
        	Coordinate position2 = new Coordinate(-1, 1);


		System.out.println("Choisissez la première carte à alterner");
        	position1 = askPlayerCoordinates(currentBoard, false);

		System.out.println("Choisissez la seconde carte à alterner");
			position2 = askPlayerCoordinates(currentBoard, false);


		Card temp;
        	//Choix de la première carte

           	//Alternance des carte
		//
			Coordinate coordinateCardToMove1 = currentBoard.findEqualsCoordinate(position1);
			Card	cardToMove = currentBoard.getCardByCoordinate(coordinateCardToMove1);
				currentBoard.currentCardsOnBoard.remove(coordinateCardToMove1);

		Coordinate coordinateCardToMove2 = currentBoard.findEqualsCoordinate(position2);
	Card	cardToMove2 = currentBoard.getCardByCoordinate(coordinateCardToMove2);
		currentBoard.currentCardsOnBoard.remove(coordinateCardToMove2);

		currentBoard.forceAddCardOnBard(cardToMove, coordinateCardToMove2);
		currentBoard.forceAddCardOnBard(cardToMove2, coordinateCardToMove1);

    }
    
    public void showVictoryCard(Card victoryCard) {
    	System.out.println("Voici votre carte victoire :");
    	System.out.println(victoryCard.toString());
    }
    
    public void shuffle(Board currentBoard)
	{
		if(hasShuffled)
			return;


		Scanner sc = new Scanner(System.in);

		System.out.println("!!! Voulez-vous shuffle ? : 1|0");
		int choix;
		try {
			choix  = sc.nextInt();
		}
		catch(Exception e) {
			sc.next();
			choix  = 12;
		}


		while(choix != 1 && choix != 0)
		{
			System.out.println("Saisie incorrecte. Recommencez : \t");

			try {
				choix  = sc.nextInt();
			}
			catch(Exception e) {
				sc.next();
				choix  = 12;
			}
		}

		if(choix == 1)
		{
			Stack<Card> cards = new Stack<Card>();


			Iterator entries = currentBoard.currentCardsOnBoard.entrySet().iterator();
			while (entries.hasNext())
			{
				Entry thisEntry = (Entry) entries.next();

				Coordinate key = (Coordinate) thisEntry.getKey();
				Object value = thisEntry.getValue();

				cards.add(currentBoard.getCardByCoordinate(key));
				entries.remove();
			}


			for (Card currentCard: cards )
			{
				Coordinate currentCardsPosition = new Coordinate(-1, -1);
				do {
					currentCardsPosition.setX((int)(Math.random() * 12));
					currentCardsPosition.setY((int)(Math.random() * 12));
				}while (!currentBoard.isPlaceAvailable(currentCardsPosition) || !currentBoard.isCoordinateCloseEnough(currentCardsPosition) );

				currentBoard.addCardOnBoard(currentCard, currentCardsPosition);
			}

			currentBoard.showBoard();
		} else
			return;

	}
    
    public void accept(Visitor visitor) {
    }
    
    public boolean changeVictoryCard(Board currentBoard) {

		if(hasChangedVictoryCard)
			return true;

	Scanner sc = new Scanner(System.in);

	System.out.println("Voulez-vous Changer de Victory Models.Card ? 1 | 0 ");
		int choix;

		try {
			choix  = sc.nextInt();
		}
		catch(Exception e) {
			sc.next();
			choix  = 12;
		}

		while(choix != 1 && choix != 0)
		{
			System.out.println("Saisie incorrecte. Recommencez : \t");

			try {
				choix  = sc.nextInt();
			}
			catch(Exception e) {
				sc.next();
				choix  = 12;
			}
		}

	if(choix == 1)
	{
		hasChangedVictoryCard = true;
		return true;
	}

		return false;
    }
}