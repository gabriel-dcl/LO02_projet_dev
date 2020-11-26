import java.util.Scanner;

public abstract class GameManager {
    private int difficulty;
    protected Player players[];
    protected Scanner sc;
    protected Board currentBoard;
    protected Visitor visitor;
    protected int maxCards;

    public GameManager()
    {

        sc = new Scanner(System.in);
        currentBoard = new BoardRectangular();
        visitor = new Visitor();
    }

    public void preGame() {
        int compteur_joueur;
        int compteurJoueurVirtuels = 0;
        int nbJoueurs;

        System.out.println("Combien de joueurs reels avez-vous ? (3 maximum)");
        compteur_joueur = sc.nextInt();
        while(compteur_joueur < 0 && compteur_joueur > 3)
        {
            System.out.println("Saisie invalide. Recommencez.");
            compteur_joueur = sc.nextInt();
        }

        if(compteur_joueur != 3)
        {
            System.out.println("Combien de bots voulez-vous avec vous ?");

            compteurJoueurVirtuels = sc.nextInt();

            while( compteur_joueur + compteurJoueurVirtuels > 3 )
            {
                System.out.println("Le nombre de joueur maximum autorisé est de 3 ! Recommencez : \t");
                compteurJoueurVirtuels = sc.nextInt();
            }

        }

        players = new Player[compteur_joueur + compteurJoueurVirtuels];

        if(compteur_joueur + compteurJoueurVirtuels == 3)
            maxCards = 14;
        else
            maxCards = 15;

        System.out.print("Dans quelle difficulty voulez-vous jouer (0 ou 1): \t");
        difficulty = sc.nextInt();

        while(difficulty != 0 && difficulty != 1)
        {
            System.out.println("Saisie invalide. Recommencez.");
            difficulty = sc.nextInt();
        }

        int index = 0;

        for(index = 0; index < compteur_joueur; index++)
        {
            players[index] = new Player(currentBoard.getNewRandomCard());

        }

        for (int i = 0; i < compteurJoueurVirtuels; i++)
        {

            players[index + i] = new Player(difficulty, currentBoard.getNewRandomCard(), visitor);

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
