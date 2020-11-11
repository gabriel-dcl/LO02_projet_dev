import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("aa97c1ed-718c-4892-ab59-b4dc58860644")
public abstract class Board {
    @objid ("c68ce86c-d039-4e3a-8790-de914700a821")
    public GameManager currentBoard;

    @objid ("6b1e4056-829b-4bcf-bc05-175c0d405650")
    public void calculateNbCardsOnBoard() {
    }

    @objid ("29ed7c19-638b-4d43-8344-516fc19ef6ed")
    public Card getNewCard() {
    }

    @objid ("58bca16d-1778-44c3-ae51-bcf00c3c5fbb")
    public void accept(Visitor visitor) {
    }

}
