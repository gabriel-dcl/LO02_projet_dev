package Vues.console;

import Controllers.GameController;
import Controllers.SetUpController;
import Vues.Vue;
import Vues.graphiques.SetUpGraphicVue;
import enums.BoardType;
import enums.Difficulty;
import enums.GameMode;

import java.awt.*;
import java.util.Scanner;
import java.util.zip.DeflaterInputStream;

public class SetUpConsoleVue implements Vue {

    Scanner sc;
    private SetUpController ctrl;
    private boolean exit = false;

    public SetUpConsoleVue() // SetUpController ctrl
    {
        //this.ctrl = ctrl;
        sc = new Scanner(System.in);
    }

    public void gameManagerChoice()
    {
        System.out.println("\n Quel mode de jeu voulez-vous jouer : ");
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

        switch(choix)
        {
            case 1 :   ctrl.setChoixGC(GameMode.CLASSIC); break;
            case 2 :   ctrl.setChoixGC(GameMode.QUICK); break;
            case 3 :   ctrl.setChoixGC(GameMode.CHAOS); break;
        }
        this.setUpLobby();
    }

    public void gameBoardChoice()
    {
        int choix = 0;

        System.out.println("Quelle forme de plateau désirez-vous ? 1 - Rectangulaire | 2 - Triangulaire | 3 - Square");
        try {
            choix = sc.nextInt();
        }
        catch(Exception e) {
            choix = -1;
        }

        while (choix != 1 && choix != 2 && choix != 3) {
            System.out.print("Saisie invalide, merci de recommencez : ");

            try {
                choix = sc.nextInt();
            }
            catch(Exception e) {
                sc.next();
            }
        }

        switch(choix)
        {
            case 1: ctrl.setChoixBoard(BoardType.RECTANGULAR); break;
            case 2: ctrl.setChoixBoard(BoardType.TRIANGULAR); break;
            case 3: ctrl.setChoixBoard(BoardType.SQUARE); break;
        }

        this.setUpLobby();
    }

    public void welcome() {

            this.ctrl = new SetUpController(this);


                System.out.println("=============================================================== ");
                System.out.println("======================= SHAPE UP ! ============================ ");
                System.out.println("=============================================================== ");
                this.setUpLobby();


    }

    public int virtualPlayersAmountChoice() {
        int virtualPlayersAmount;

        System.out.println("Combien de bots voulez-vous avec vous ?");

        try {
            virtualPlayersAmount = sc.nextInt();
        }
        catch(Exception e) {
            sc.next();
            virtualPlayersAmount = 12;
        }

        return virtualPlayersAmount;
    }



    public int badInputVirtualPlayersAmount()
    {
        int virtualPlayersAmount;
        System.out.println("Saisie invalide ! (Attention, le nombre maximal de joueurs est de 3 et le nombre minimal est de 2) Recommencez : \t");
        try {
            virtualPlayersAmount = sc.nextInt();
        }
        catch(Exception e) {
            sc.next();
            virtualPlayersAmount = 12;
        }

        return virtualPlayersAmount;
    }

    public void playersSetup() {
		int realPlayersAmount = this.realPlayersAmountChoice();
		int virtualPlayersAmount = 0;

		if (realPlayersAmount != 3)
		{
			virtualPlayersAmount = this.virtualPlayersAmountChoice();

			while (realPlayersAmount + virtualPlayersAmount > 3 || realPlayersAmount + virtualPlayersAmount < 2) {
				virtualPlayersAmount = this.badInputVirtualPlayersAmount();
			}
		}

		ctrl.setNbBots(virtualPlayersAmount);
		ctrl.setNbPlayer(realPlayersAmount);

		this.setUpLobby();
	}

    public void difficultyChoice()
    {
        int difficulty = 0;
        System.out.print("Dans quelle difficulty voulez-vous jouer (1 ou 2): \t");

        try {
            difficulty  = sc.nextInt();
        }
        catch(Exception e) {
            sc.next();
            difficulty  = 12;
        }

        while(difficulty != 1 && difficulty != 2)
        {
            System.out.println("Saisie invalide. Recommencez.");

            try {
                difficulty = sc.nextInt();
            }
            catch(Exception e) {
                sc.next();
                difficulty = 12;
            }
        }

        if(difficulty == 1)
             ctrl.setChoixDif(Difficulty.EASY);
        else
            ctrl.setChoixDif(Difficulty.HARD);

        this.setUpLobby();
    }

    public int realPlayersAmountChoice()
    {
        int realPlayersAmount;

        System.out.println("Combien de joueurs reels avez-vous ? (3 maximum)");

        try {
            realPlayersAmount = sc.nextInt();
        }
        catch(Exception e) {
            sc.next();
            realPlayersAmount = 12;
        }

        while(realPlayersAmount < 0 || realPlayersAmount > 3)
        {
            System.out.println("Saisie invalide. Recommencez.");

            try {
                realPlayersAmount = sc.nextInt();
            }
            catch(Exception e) {
                sc.next();
                realPlayersAmount = 12;
            }
        }

        return realPlayersAmount;
    }

    public void setUpLobby()
    {
        System.out.println("1 - Changer le mode de jeu (actuel : " + ctrl.getChoixGC() + " )");
        System.out.println("2 - changer le plateau : (actuel :" + ctrl.getChoixBoard() + " )");
        System.out.println("3 - Changer la configuration des joueurs : (Joueurs réels :" + ctrl.getNbPlayer() + ", Bots : " + ctrl.getNbBots() + " )");
        System.out.println("4 - Changer la difficulté (actuel : " + ctrl.getChoixDif() + " )");
        System.out.println("5 - Passer en vue graphique");
        System.out.println("6 - Lancer la partie");
        System.out.println("7 - Quitter");

        System.out.print("Votre selection : \t");
        int choix = 12;

        try {
            choix = sc.nextInt();
        }
        catch(Exception e) {
            System.out.println("Saisie invalide. Recommencez.");
        }

        while (choix != 1 && choix != 2 && choix !=3 && choix !=4 && choix !=5 && choix !=6 && choix !=7)
        {
            System.out.print("Saisie invalide, merci de recommencez : ");

            try {
                choix = sc.nextInt();
            }
            catch(Exception e) {
                sc.next();
            }
        }

        switch(choix)
        {
            case 1 : this.gameManagerChoice(); break;
            case 2 : this.gameBoardChoice(); break;
            case 3 : this.playersSetup(); break;
            case 4 : this.difficultyChoice(); break;
            case 5 : ctrl.changeVue(); break;
            case 6 : ctrl.createGame(); break;
            case 7 : exit = true; break;
        }

    }

    @Override
    public GameController getCtrl() {
        return null;
    }

    @Override
    public void occure() {

    }
}
