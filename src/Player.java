import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d520295f-ba54-4fc0-a340-bac5226dc941")
public class Player implements Strategy {
    @objid ("22c9533f-2f70-47c8-93f1-ea2c1ec5953d")
    private boolean isReal;

    @objid ("bc9b79a4-13a1-48e8-be79-3168969f2ab8")
    private Card VictoryCard;

    @objid ("465e525e-361d-4ccc-97aa-af7aeb77ec05")
    private int nbPoints;

    @objid ("52add2f9-0c07-4a58-a4b3-d0a395ec5e3c")
    private Visitor visitor;

    @objid ("9671b4c2-d1f2-465c-b2d7-d6a0f82d7e19")
    private Strategy strategy;

    @objid ("e35e9e24-4360-4d26-9e13-cf8a99136728")
    public GameManager ;

    @objid ("84c0c507-1466-49e5-bdd8-31942de317ca")
    public Board moveCard(Card newCard, Board currentBoard) {
    }

    @objid ("de5fc9ad-a514-4a53-ab8f-33a33b830d19")
    public Board placeNewCard(Card newCard, Board currentBoard) {
    }

    @objid ("6848dcab-2f3f-4d44-8281-a0e514a018a8")
    public Board alternateCards(Card card1, Card Card2) {
    }

    @objid ("8afe6e11-fa12-4a77-acdb-df180be379b2")
    public void showVictoryCard() {
    }

    @objid ("d01d06c1-9098-437f-8a01-72145611ecdc")
    public Board shuffle(Board currentBoard) {
    }

    @objid ("d53f122c-39ef-4d7e-ae45-213cc42f1247")
    public void accept(Visitor visitor) {
    }

    @objid ("e62dc0f3-f5b1-4542-94cc-d21716dca567")
    public void changeVictoryCard(Card newCard) {
    }

}
