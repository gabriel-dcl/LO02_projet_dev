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

      //  GameManager manager = gmc.getGameManager();

        gmc.occure();


      //  manager.preGame();
       // manager.game();
        // manager.gameOver();
    }



}
