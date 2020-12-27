package Vues.console;

import Controllers.GameManagerController;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class GameManagerVue implements Observer {

    Scanner sc;
    private GameManagerController gmc;

    public void setGmc(GameManagerController gmc) {
        this.gmc = gmc;
    }

    public GameManagerVue()
    {
        this.sc = new Scanner(System.in);
    }

    public int gameManagerChoice()
    {
        System.out.println("=============================================================== ");
        System.out.println("======================= SHAPE UP ! ============================ ");
        System.out.println("=============================================================== ");
        System.out.println("\n \n \n Bonjour ! Quel mode de jeu voulez-vous jouer : ");
        System.out.println("1 - Classic \n2 - Quick \n3 - Chaos");
        System.out.print("Votre selection : \t");
        int choix = 12;

        try {
            choix = sc.nextInt();
        }
        catch(Exception e) {
            System.out.println("Saisie invalide. Recommencez.");
        }

        while (choix != 1 && choix != 2 && choix !=3)
        {
            System.out.print("Saisie invalide, merci de recommencez : ");

            try {
                choix = sc.nextInt();
            }
            catch(Exception e) {
                sc.next();
            }
        }

        return choix;
    }

    public int realPlayersAmount()
    {
        return 0;
    }

   public int gameBoardChoice()
   {
       int choix = 0;

           System.out.println("Quelle forme de plateau désirez-vous ? 1 - Rectangulaire | 2 - Triangulaire | 3 - Square");
           try {
               choix = sc.nextInt();
           }
           catch(Exception e) {
               return -1;
           }

        return choix;

   }

    @Override
    public void update(Observable o, Object arg) {

        if((String) arg == "BOARDCHOICE")
        {
            gmc.addBuffer(this.gameBoardChoice());
        }

    }
}
