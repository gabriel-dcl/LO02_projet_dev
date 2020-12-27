package Controllers;

import Models.GameManager;
import Models.GameManagerChaos;
import Models.GameManagerClassic;
import Models.GameManagerQuick;
import Vues.console.GameManagerVue;

public class GameManagerController {

    private GameManager gameManager;
    private GameManagerVue gameManagerVue;

    public void addBuffer(int i )
    {
        if(i == -1)
            System.out.println("ERROR");

        gameManager.setBuffer(i);
    }

    public GameManagerController(GameManagerVue gmv)
    {
        this.gameManagerVue = gmv;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void GameBoardChoice()
    {
        boolean valueOkay = false;
        int choix = -1;

        while(choix != 1 && choix != 2  && choix != 3)
        {
            choix = gameManagerVue.gameBoardChoice();
        }

        gameManager.setBoard(choix);
    }

    public void setGameManager()
    {
        int choix = gameManagerVue.gameManagerChoice();

        switch (choix)
        {
            case 1 : this.gameManager = new GameManagerClassic();
                break;
            case 2 : this.gameManager = new GameManagerQuick();
                break;
            case 3 : this.gameManager = new GameManagerChaos();
                break;
            default : this.gameManager = new GameManagerClassic();
                break;
        }

    }






}
