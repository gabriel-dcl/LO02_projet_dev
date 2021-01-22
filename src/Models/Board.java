package Models;

import enums.Color;
import enums.Form;
import enums.State;

import java.util.*;

/**
 * Class abstraite Board, qui définie le moule pour l'ensemble des Board
 * @version 2.0
 * @author Nicolas Felixine, Gabriel Duciel
 * @see BoardTriangular
 * @see BoardSquare
 * @see BoardRectangular
 * @see Card
 */
public abstract class Board extends Observable {

    /**
     * Map faisant le lien entre des coordonnées et des cartes, représentant le plateau
     */
    protected Map<Coordinate, Card> currentCardsOnBoard;

    /**
     * Cartes restantes sur le plateau, rangées dans une pile (Stack)
     */
    protected Stack<Card> remainingCards;

    /**
     * Crée une instance dans la class Board, la HashMap associée pour le plateau
     * et remplit la pile remainingCards de façon aléatoire
     */
    public Board()
    {
        remainingCards = new Stack<Card>();
        generateRandomStack();
        currentCardsOnBoard = new HashMap<Coordinate, Card>();
    }

    /**
     * Gets current cards on board.
     *
     * @return the current cards on board
     */
    public Map<Coordinate, Card> getCurrentCardsOnBoard() {
        return currentCardsOnBoard;
    }

    /**
     * Sets current cards on board.
     *
     * @param currentCardsOnBoard the current cards on board
     */
    public void setCurrentCardsOnBoard(HashMap<Coordinate, Card> currentCardsOnBoard) {
        this.currentCardsOnBoard = currentCardsOnBoard;
    }

    /**
     * Shuffle, permet de modifier le plateau de façon totalement aléatoire
     */
    public void shuffle()
    {
        Stack<Card> cards = new Stack<Card>();


        Iterator entries = this.currentCardsOnBoard.entrySet().iterator();
        while (entries.hasNext())
        {
            Map.Entry thisEntry = (Map.Entry) entries.next();

            Coordinate key = (Coordinate) thisEntry.getKey();
            Object value = thisEntry.getValue();

            cards.add(this.getCardByCoordinate(key));
            entries.remove();
        }

        for (Card currentCard: cards )
        {
            Coordinate currentCardsPosition = new Coordinate(-1, -1);
            do {
                currentCardsPosition.setX((int)(Math.random() * 12));
                currentCardsPosition.setY((int)(Math.random() * 12));
            }while (!this.isPlaceAvailable(currentCardsPosition) || !this.isCoordinateCloseEnough(currentCardsPosition) );

            this.addCardOnBoard(currentCard, currentCardsPosition);
        }
    }


    /**
     * Vérifie que les coordonnées insérées en paramètre sont suffisament proches pour y ajouter une carte sur le plateau
     *
     * @see Coordinate
     * @param coordinate Les coordonnées pour lesquelles on souhaite savoir si la position est suffisamment proche
     * @return True si la position est assez proche, False sinon
     */
    public abstract boolean isCoordinateCloseEnough(Coordinate coordinate);

    /**
     * Vérifie si la place est libre ou non
     *
     * @see Coordinate
     * @param coordinate    Les coordonnéees pour lesquelles nous souhaitons savoir la disponibilité
     * @return True si la place est libre, False sinon
     *
     */
    public boolean isPlaceAvailable(Coordinate coordinate) {
        for (Map.Entry<Coordinate, Card> entry : currentCardsOnBoard.entrySet()) {
            if (entry.getKey().equals(coordinate))
                return false;
        }
        return true;
    }

    /**
     * Cherche une instance de la class Coordonnées avec les mêmes attributs que l'instance mise en paramètre
     *
     * @param coordinate    les coordonnées pour lesquelles nous voulons avoir l'instance de coordonnées actuellement sur le plateau
     * @return      L'instance de Coordinate présente actuellement dans la HashMap du plateau
     * @see Coordinate
     */
    public Coordinate findEqualsCoordinate(Coordinate coordinate) {
        for (Map.Entry<Coordinate, Card> entry : currentCardsOnBoard.entrySet()) {
            if (entry.getKey().equals(coordinate))
                return entry.getKey();
        }
        return null;
    }

    /**
     * Permet d'ajoutre une carte sur le plateau
     *
     * @see Card
     * @param card       La carte à ajouter
     * @param coordinate Les coordonnées où l'ajouter
     * @return True si la carte a bien été ajoutée, false sinon
     */
    public abstract boolean addCardOnBoard(Card card, Coordinate coordinate);


    /**
     * Permet de récupérer une carte en tête de la pile
     *
     * @return la carte en tête de la pile
     * @see Card
     */
    public Card getNewRandomCard()
    {
        return remainingCards.pop();
    }

    /**
     * Gets card by coordinate.
     *
     * @param coordinate the coordinate
     * @return the card by coordinate
     */
    public Card getCardByCoordinate(Coordinate coordinate)
    {
       return currentCardsOnBoard.get(this.findEqualsCoordinate(coordinate));
    }

    /**
     * Gets card.
     *
     * @return the card
     */
    public Card getCard()
    {
        return remainingCards.pop();
    }

    /**
     * Permet d'ajouter une carte par la force, sans se soucier de la position insérée et de sa proximité avec le plateau
     *
     * @param card          La carte à ajouter de force
     * @param coordinate    Les coordonnées où ajouter cette carte de force
     */
    public void forceAddCardOnBard(Card card, Coordinate coordinate)
    {
        currentCardsOnBoard.put(coordinate, card);
    }

    /**
     * Accept.
     *
     * @param visitor the visitor
     */
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }



    /**
     *  Permet de remplir la pile de cartes restantes de façon aléatoire.
     *
     * @see Card
     */
    private void generateRandomStack()
    {
        int[] values_done = new int[20];
        int i = 0;

        for(State temp_state : State.values())
        {
            for(Color color_temp : Color.values())
            {
                for(Form form_temp : Form.values())
                {
                    remainingCards.add(new Card(temp_state, color_temp, form_temp));
                    i++;
                }
            }
        }

        Collections.shuffle(remainingCards);
    }

}

