import java.util.Scanner;

public abstract class GameManager {
    private int difficulty;
    private Player players[];
    private Scanner sc;

    public GameManager()
    {
        players = new Player[3];
        sc = new Scanner(System.in);
    }

    public void preGame() {
        int compteur_joueur;

        System.out.print("Dans quelle difficulty voulez-vous jouer (0 ou 1): \t");
        difficulty = sc.nextInt();

        while(difficulty != 0 && difficulty != 1)
        {
            System.out.println("Saisie invalide. Recommencez.");
            difficulty = sc.nextInt();
        }

        System.out.println("Combien de joueurs Réels avez-vous ? (3 maximum)");
        compteur_joueur = sc.nextInt();

        while(compteur_joueur < 0 && compteur_joueur > 3)
        {
            System.out.println("Saisie invalide. Recommencez.");
            compteur_joueur = sc.nextInt();
        }

        for(int i = 0; i < compteur_joueur; i++)
        {
            System.out.println(i);
            players[i] = new Player();
        }

        System.out.println("NEXT");

        for (int i = compteur_joueur; i < 3; i++) {
            System.out.println(i);
            players[i] = new Player(0);

        }
    }


    public abstract void game();

    public void gameOver() {
    }
    public void occure() {
    }
    public void getInstance() {
    }
    private void singleton() {
    }

}
