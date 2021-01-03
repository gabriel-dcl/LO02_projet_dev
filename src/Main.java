import Controllers.GameController;
import Vues.console.ConsoleVue;

public class Main {

    public static void main(String Args[])
    {

        ConsoleVue gmv = new ConsoleVue();
        GameController gmc = new GameController(gmv);

        gmc.occure();

    }



}
