package Models;

/**
 * Inteface correspondant au Design Pattern Strategy, permettant de d�finir le comportement des joueurs virtuels
 * @author  Gabriel Duciel
 * @version 1.0
 */
public interface Strategy {

    /**
     * Faire d�placer au joueur virutel une carte
     *
     * @param currentBoard Le plateau actuel
     */
    void moveCard( Board currentBoard);

    /**
     * Faire placer une carte au joueur virtuel une carte
     *
     * @param newCard la nouvelle carte � ajouter
     * @param currentBoard Le plateau actuel
     */
    void placeNewCard(Card newCard, Board currentBoard);

    /**
     * Faire alterner deux cartes au joueur virtuel une carte
     *
     * @param currentBoard Le plateau actuel
     */
    void alternateCards(Board currentBoard);

    /**
     * Demander au joueur virtuel s'il souhaite shuffle, et le faire le cas �chant
     *
     * @param currentBoard Le plateau actuel
     * @return True si le joueur virtual a pris la d�cison de Shuffle, false sinon
     */
    boolean shuffle(Board currentBoard);

    /**
     * Demander au joueur virtuel s'il changer de victory Card, et le faire le cas �ch�ant
     *
     * @param currentBoard Le plateau actuel
     * @return True si le joueur virtual a pris la d�cison de changer de Victory Card, false sinon
     */
    boolean changeVictoryCard(Board currentBoard);

}
