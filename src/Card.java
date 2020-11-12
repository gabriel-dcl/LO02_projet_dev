import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.ia.Player;

@objid ("a2345e60-a587-46f9-812f-7656761a43f5")
public class Card {
    @objid ("2881c441-32ba-45fa-bb58-aa8c981b1e5d")
    private String forme;

    @objid ("fc98fb47-9b4b-4203-9f48-bd70e7bd32e0")
    private String couleur;

    @objid ("f416703c-c1e0-4e7a-a309-5eb586fd9d91")
    private String remplissage;

    @objid ("5d9c5447-7c71-4819-866d-f331415e1808")
    private int x_pos;

    @objid ("bef1b129-9574-4106-8f47-3fbf022f1663")
    private int y_pos;

    @objid ("9a6ecc10-4c5f-4625-ba0a-472dae25c10f")
    public Board ;

    @objid ("e6847ada-9190-4ea8-8419-00845f2b703a")
    public List<Player> player = new ArrayList<Player> ();

}
