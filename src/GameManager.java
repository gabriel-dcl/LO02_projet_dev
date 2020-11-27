import java.util.Scanner;

public abstract class GameManager {
    private int difficulty;
    protected Player players[];
    protected Scanner sc;
    protected Board currentBoard;
    protected Visitor visitor;
    protected int maxCards;
    int compteur_joueur;
    int compteurJoueurVirtuels = 0;

    public GameManager()
    {

        sc = new Scanner(System.in);
        visitor = new Visitor();
    }

    public void preGame() {

        int choix = 0;
        while (choix != 1 && choix !=2 && choix !=3 ) {
            System.out.println("Quelle forme de plateau d�sirez-vous ? 1 - Rectangulaire | 2 - Triangulaire | 3 - Square");
            choix = sc.nextInt();

            switch (choix) {
                case 1:
                    currentBoard = new BoardRectangular();
                    break;
                case 2:
                    currentBoard = new BoardTriangular();
                    break;
                case 3:
                    currentBoard = new BoardSquare();
                    break;
                default:
                    currentBoard = new BoardRectangular();
            }
        }

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
                System.out.println("Le nombre de joueur maximum autoris� est de 3 ! Recommencez : \t");
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

    public void gameOver()
    {
        if(compteur_joueur + compteurJoueurVirtuels == 3)
        {
            int pointsPlayer1 =  this.visitor.getPointsTotalRegardingVictoryCard(players[0].getVictoryCard());
            int pointsPlayer2 =  this.visitor.getPointsTotalRegardingVictoryCard(players[1].getVictoryCard());
            int pointsPlayer3 =  this.visitor.getPointsTotalRegardingVictoryCard(players[2].getVictoryCard());

            System.out.println("CARTE J1: " + players[0].getVictoryCard());
            System.out.println("CARTE J2: " + players[1].getVictoryCard());
            System.out.println("CARTE J3: " + players[2].getVictoryCard());

            if(pointsPlayer1 > pointsPlayer2 && pointsPlayer1 > pointsPlayer3)
                System.out.println("Le joueur 1 gagne avec sa carte : " + players[0].getVictoryCard());
            else if(pointsPlayer2 > pointsPlayer1 && pointsPlayer2 > pointsPlayer3)
                System.out.println("Le joueur 2 gagne avec sa carte : " + players[1].getVictoryCard());
            else if(pointsPlayer3 > pointsPlayer1 && pointsPlayer3 > pointsPlayer2)
                System.out.println("Le joueur 3 gagne avec sa carte : " + players[2].getVictoryCard());
            else
                System.out.println("Match nul !");
        }
        else
        {
            System.out.println("CARTE J1: " + players[0].getVictoryCard());
            System.out.println("CARTE J2: " + players[1].getVictoryCard());

            int pointsPlayer1 =  this.visitor.getPointsTotalRegardingVictoryCard(players[0].getVictoryCard());
            int pointsPlayer2 =  this.visitor.getPointsTotalRegardingVictoryCard(players[1].getVictoryCard());

            if(pointsPlayer1 > pointsPlayer2)
                System.out.println("Le joueur 1 gagne avec sa carte : " + players[0].getVictoryCard());
            else if((pointsPlayer2 > pointsPlayer1))
                System.out.println("Le joueur 2 gagne avec sa carte : " + players[1].getVictoryCard());
            else
                System.out.println("Match nul !");
        }


    }
    public void occure() {
    }
    public void getInstance() {
    }
    private void singleton() {
    }

}
