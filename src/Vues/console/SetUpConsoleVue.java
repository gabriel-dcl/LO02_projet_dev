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

/**
 * La Vue Set-up en console, qui permet de configurer une partie avec la vue console
 * @author Gabriel Duciel
 * @version 1.0
 * @see SetUpController
 */
public class SetUpConsoleVue implements Vue {

    Scanner sc;
    /**
     * Le controlleur associé à la vue
     */
    private SetUpController ctrl;

    /**
     * Le boolean associé à la fin de la partie (true pour terminer la partie)
     */
    private boolean exit = false;

    /**
     * Crée une instance de la class SetUpConsoleVue avec une instance de la class Scanner
     */
    public SetUpConsoleVue()
    {
        sc = new Scanner(System.in);
    }

    /**
     * Methode pour la sélection du mode de jeu (GameManager)
     */
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

    /**
     * Méthode pour le choix du plateau de jeu.
     */
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

    /**
     * Message de lancement du jeu, qui s'affiche une unique fois au lancement du programme.
     * La méthode est isolée de la class pour afficher le message de lancement une unique fois.
     * Création de l'instance du SetUpController
     * @see SetUpController
     */
    public void welcome() {

            this.ctrl = new SetUpController(this);


                System.out.println("=============================================================== ");
                System.out.println("======================= SHAPE UP ! ============================ ");
                System.out.println("=============================================================== ");
                this.setUpLobby();
    }

    /**
     * Methode pour identifier le nombre de joueurs virtuels dans la partie
     *
     * @return le nombre de joueurs virtuels
     * @see Models.Player
     */
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


    /**
     * Méthode pour gérer le cas ou le nombre de joueur total entré n'est pas valide.
     *
     * @return virtualPlayersAmount, le nouveau nombre de joueurs virtuels
     */
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

    /**
     * Méthode pour sélectionner le nombre de joueurs dans la partie. (Virtual et real).
     */
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

    /**
     * Methode pour choisir la difficulte de la partie
     */
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

    /**
     * Methode pour choisir le nombre de joueurs réels.
     *
     * @return realPlayersAmount, le nombre de joueurs réels
     */
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

    /**
     * Methode de menu principal pour la vue. Elle est lancee après le message de bienvenue et permet de selectionner quelles options modifiees.
     * Elle permet egalement de lancer une partie, de quitter le jeu ou de passer en vue graphique.
     */
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

    /**
     * Methode de l'interface Vue, pas utile dans ce contexte mais necessaire pour realiser du polymorphisme.
     */
    @Override
    public void occure() {

    }
}
