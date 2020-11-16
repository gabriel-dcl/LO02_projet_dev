import java.util.ArrayList;
import java.util.List;

public class Card {

    private int x_pos;
    private int y_pos;
    private enum state {FILL, EMPTY};
    private enum color {BLUE, RED, GREEN};
    private enum form {CIRCLE, SQUARE, TRIANGLE};


    public List<Player> player = new ArrayList<Player> ();

}
