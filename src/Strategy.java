import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("f9018589-8f84-4317-adfa-3214865bb347")
public interface Strategy {
    @objid ("8765ad3a-39f3-4709-948e-e72da0ef3687")
    Board moveCard(Card newCard, Board currentBoard);

    @objid ("26124460-61a3-4577-9d09-b5b3541ca5fc")
    Board placeNewCard(Card newCard, Board currentBoard);

    @objid ("6190e26e-ec33-4e50-9330-c299db5a04ac")
    Board alternateCards(Card card1, Card Card2);

    @objid ("bd9ebfc2-0ea6-442a-a6d7-c36dcb6397d1")
    void showVictoryCard();

    @objid ("ff66499f-3dab-4226-92a4-ae853428ed32")
    Board shuffle(Board currentBoard);

    @objid ("2c3a6119-f137-4108-b208-a894302d9b54")
    void accept(Visitor visitor);

    @objid ("87a42590-291b-4645-b84f-2db5b6e13b89")
    void changeVictoryCard(Card newCard);

}
