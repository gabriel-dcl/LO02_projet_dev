
import java.util.Scanner;

public class Main {

    public static void main(String Args[])
    {
        // Variables initialisation
        GameManager manager;
        Scanner sc = new Scanner(System.in);
        boolean isPlaying = true;


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

        switch (choix)
        {
            case 1 : manager = new GameManagerClassic();
                break;
            case 2 : manager = new GameManagerQuick();
            	break;
            	case 3 : manager = new GameManagerChaos();
				break;
            default : manager = new GameManagerClassic();
                break;
        }

        manager.preGame();
        manager.game();
        manager.gameOver();
    }



}
