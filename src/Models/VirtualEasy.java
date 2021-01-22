package Models;

import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class d�finissant le comportement d'un joueur virtuel "facile", utilisant le Design Pattern Strategy
 *
 * @author Gabriel Duciel
 * @version 1.0
 */
public class VirtualEasy implements Strategy {

    /**
     * The Visitor.
     */
    Visitor visitor;
    /**
     * The Has changed victory card.
     */
    boolean hasChangedVictoryCard;

    /**
     * Instantiates a new Virtual easy.
     *
     * @param visitor the visitor
     */
    public VirtualEasy(Visitor visitor)
    {
        this.visitor = visitor;
    }

    /**
     * Faire placer une carte au joueur virtuel
     *
     * @param newCard la nouvelle carte � ajouter
     * @param currentBoard Le plateau actuel
     */
    public void placeNewCard(Card newCard, Board currentBoard)
    {
        Coordinate currentCardsPosition = new Coordinate(-1, -1);

        do
            {
                currentCardsPosition.setX((int)(Math.random() * 12));
                currentCardsPosition.setY((int)(Math.random() * 12));
            }while (!currentBoard.isPlaceAvailable(currentCardsPosition) || !currentBoard.isCoordinateCloseEnough(currentCardsPosition) );


         currentBoard.addCardOnBoard(newCard, currentCardsPosition);
    }

    /**
     * Le joueur virtuel g�n�re un nombre al�atoire, et Shuffle si ce nombre est sup�rieur � 80
     *
     * @param currentBoard Le plateau actuel
     * @return True si le joueur virtual a pris la d�cison de Shuffle, false sinon
     */
    public boolean shuffle(Board currentBoard) {

        int randomNum = ThreadLocalRandom.current().nextInt(0, 100);

        if(randomNum > 80)
        {
            currentBoard.shuffle();

            return true;
        }
        return false;
    }

    /**
     * Si le joueur virtuelle a deja chang� de carte, il ne peut pas le refaire.
     * Sinon, il genere un nombre al�atoire, et change sa carte victoire dans le cas o� ce nombre est sup�rieur � 75
     *
     * @param currentBoard Le plateau actuel
     * @return True si le joueur virtual a pris la d�cison de changer de Victory Card, false sinon
     */
    public boolean changeVictoryCard(Board currentBoard)
    {
        if(hasChangedVictoryCard)
            return false;

        int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        if(randomNum > 75)
        {
            hasChangedVictoryCard = true;
            return true;
        }

        return false;
    }

    /**
     * Le joueur d�place al�atoirement une carte du plateau a une position al�atoire.
     *
     * @param currentBoard Le plateau actuel
     */
	public void moveCard(Board currentBoard)
    {

        Card cardToMove;
        Coordinate currentCardsPosition = new Coordinate(-1, -1);

        do
        {
            currentCardsPosition.setX((int)(Math.random() * 12));
            currentCardsPosition.setY((int)(Math.random() * 12));
        }while (currentBoard.isPlaceAvailable(currentCardsPosition));



        Coordinate currentCard = currentBoard.findEqualsCoordinate(currentCardsPosition);

          //r�cup�ration de la carte � bouger
        cardToMove = currentBoard.currentCardsOnBoard.get(currentCard);
        currentBoard.currentCardsOnBoard.remove(currentCard);

        Coordinate currentCardsPosition2 = new Coordinate(-1, -1);

        do
        {
            currentCardsPosition2.setX((int)(Math.random() * 12));
            currentCardsPosition2.setY((int)(Math.random() * 12));
        }while (!currentBoard.isPlaceAvailable(currentCardsPosition2) || !currentBoard.isCoordinateCloseEnough(currentCardsPosition2) );

        //Placement de la carte
        currentBoard.addCardOnBoard(cardToMove, currentCardsPosition2);

    }

    /**
     * Le joueur alterne al�atoirement deux cartes du plateau
     *
     * @param currentBoard Le plateau actuel
     */
	public void alternateCards(Board currentBoard) {

        Card cardToMove;
        Coordinate currentCardsPosition = new Coordinate(-1, -1);

        do
        {
            currentCardsPosition.setX((int)(Math.random() * 12));
            currentCardsPosition.setY((int)(Math.random() * 12));
        }while (currentBoard.isPlaceAvailable(currentCardsPosition));

        Coordinate coordinateCardToMove1 = currentBoard.findEqualsCoordinate(currentCardsPosition);
        cardToMove = currentBoard.getCardByCoordinate(coordinateCardToMove1);
        currentBoard.currentCardsOnBoard.remove(coordinateCardToMove1);

        Card cardToMove2;
        Coordinate currentCardsPosition2 = new Coordinate(-1, -1);

        do
        {
            currentCardsPosition2.setX((int)(Math.random() * 12));
            currentCardsPosition2.setY((int)(Math.random() * 12));
        }while (currentBoard.isPlaceAvailable(currentCardsPosition2));

        Coordinate coordinateCardToMove2 = currentBoard.findEqualsCoordinate(currentCardsPosition2);
        cardToMove2 = currentBoard.getCardByCoordinate(coordinateCardToMove2);
        currentBoard.currentCardsOnBoard.remove(coordinateCardToMove2);

        currentBoard.forceAddCardOnBard(cardToMove, coordinateCardToMove2);
        currentBoard.forceAddCardOnBard(cardToMove2, coordinateCardToMove1);


	}


}
