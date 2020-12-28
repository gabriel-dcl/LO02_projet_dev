import Controllers.GameManagerController;
import Models.GameManager;
import Models.GameManagerChaos;
import Models.GameManagerClassic;
import Models.GameManagerQuick;
import Vues.console.GameManagerVue;

import java.util.Scanner;

public class Main {

    public static void main(String Args[])
    {
        // Variables initialisation
     //   Scanner sc = new Scanner(System.in);
      //  boolean isPlaying = true;

        GameManagerVue gmv = new GameManagerVue();
        GameManagerController gmc = new GameManagerController(gmv);

        gmc.setGameManager();

        GameManagerClassic manager = (GameManagerClassic)gmc.getGameManager();

        gmv.setGmc(gmc);

        manager.addObserver(gmv);

        gmc.gameBoardChoice();
        gmc.playersSetup();


      //  manager.preGame();

       // manager.game();
        // manager.gameOver();
    }



}
