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
        System.out.println("Combien de joueurs Réels avez-vous ?");
        compteur_joueur = sc.nextInt();

        for(int i = 0; i < compteur_joueur; i++)
        {
            // players[i] = new realPlayer();
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
