
import java.util.Scanner;

public class Main {

    public static void main(String Args[])
    {
        // Variables initialisation
        GameManager manager;
        Scanner sc = new Scanner(System.in);
        boolean isPlaying = true;

        System.out.println("=============================================================== ");
        System.out.println("======================= SHAPE UP ! ============================");
        System.out.println("=============================================================== ");
        System.out.println("\n \n \n Bonjour ! Quel mode de jeu voulez-vous jouer : ");
        System.out.println("1 - Classic");
        System.out.print("Votre slection : \t");
        int choix = sc.nextInt();

        while (choix != 1 && choix != 2 && choix !=3)
        {
            System.out.print("Saisie invalide, merci de recommencez : ");
            choix = sc.nextInt();
        }

        switch (choix)
        {
            case 1 : manager = new GameManagerClassic();
                break;
            default : manager = new GameManagerClassic();
                break;

        }


        manager.preGame();
    }





}
