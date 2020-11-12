package fr.ia;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("3cf87720-bb15-45d6-a1ac-3a2be22a1ee5")
public @interface realPlayer  implements Strategy {
    @objid ("d2007146-5595-4228-8c93-ddbde6fe33a8")
    public int findBestPlacementNewCard(Board currentBoard, Card currentCard, Card victoryCard) {
    }

    @objid ("bf0a0ce3-3d10-4cce-96fc-c84a21c24917")
    public int findNewPlacementCardOnBoard(Board currentBoard, Card currentCard, Card victoryCard) {
    }

    @objid ("461e883f-5e9f-4b41-8222-05321201bc15")
    public Board moveCard(Card newCard, Board currentBoard) {
    }

    @objid ("add5708f-c856-4446-bc72-a7ccf23e9512")
    public Board placeNewCard(Card newCard, Board currentBoard) {
    }

    @objid ("ab4ea6c7-22ca-4a61-86b2-17014348165d")
    public Board alternateCards(Card card1, Card Card2) {
    }

    @objid ("2e5820eb-51f3-4da8-9bf6-1069ab0429c5")
    public void showVictoryCard() {
    }

    @objid ("f8de2586-3dcd-4780-9fde-185e4dcd06bb")
    public Board shuffle(Board currentBoard) {
    }

    @objid ("9fd36200-69f1-49ac-9151-e82bec33ce0c")
    public void accept(Visitor visitor) {
    }

    @objid ("e277e69c-0e7c-41f1-9b48-b85304d7a6c0")
    public void changeVictoryCard(Card newCard) {
    }

}
