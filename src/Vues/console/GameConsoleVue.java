package Vues.console;

import Controllers.GameController;
import Models.*;
import Vues.Vue;
import enums.Color;
import enums.Events;
import enums.Form;
import enums.State;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Vue console pour le déroulement d'une partie
 * Pour faciliter son implémentation et permettre à deux vues de fonctionner en même temps, cette vue est un thread
 *
 * @author  Gabriel Duciel, Nicolas Felixine
 * @version 1.0
 * @see Vue
 * @see Runnable
 * @see Observer
 */
public class GameConsoleVue implements Observer, Vue, Runnable {


    Scanner sc;
    /**
     * Le Game controller associe
     */

    private GameController gmc;
    private boolean playerTurn = false;

    /**
     * Divers booleans pour identifier les actions realisables pour le joueur.
     */

    private boolean canShuffle = false;
    private boolean canAlternate = false;
    private boolean canMoveCard = false;
    private boolean canChangeVictoryCard = false;
    private boolean canPlaceCard = false;
    private  boolean canPlace2ndCard = false;


    /**
     * Crée une instance de la vue, avec une instance de Scanner pour lire les entrees et une instance du GameController
     */
    public GameConsoleVue()
    {
        this.sc = new Scanner(System.in);
        gmc = new GameController(this);
    }

    /**
     * Affiche le joueur dont le tour est en cours
     */
    public void showCurrentPlayer()
    {
        System.out.println("=====> Joueur " + ( this.gmc.getGameManager().getIndex() + 1));
    }

    /**
     * Methode pour changer de VictoryCard. Verifie si cette action est utilisable.
     */
    public void changeVictoryCard()
    {
        if(!canChangeVictoryCard)
        {
            System.out.println("Vous ne pouvez pas(plus) utiliser cette commande.");
            return;
        }

        int choix = -1;
        System.out.println("Voulez vous changer de Victory Card ? 1 | 0");
        try{
            choix = sc.nextInt();
        } catch(Exception e)
        {
            choix = -1;
        }

        while(choix != 1 && choix != 0)
        {
            choix = this.badInputOneOrTwoChoice();
        }

        if(choix == 1)
        {
            gmc.getGameManager().getPlayers()[gmc.getGameManager().getIndex()].changeVictoryCard(gmc.getGameManager().getCurrentBoard());
            this.annoncePlayerChangeVictoryCard();
        }

        this.canChangeVictoryCard = false;
    }

    /**
     * Methode pour afficher le plateau
     * @see Board
     */
    public void showBoard()
    {
        for (int i = 0; i < 12; i++)
        {
            for (int j = 0; j < 12; j++)
            {
                boolean hasFound = false;

                for (Map.Entry<Coordinate, Card> entry : gmc.getGameManager().getCurrentBoard().getCurrentCardsOnBoard().entrySet())
                {
                    if (entry.getKey().equals( new Coordinate(i, j)) )
                    {
                        Card tempCard = gmc.getGameManager().getCurrentBoard().getCardByCoordinate(entry.getKey());
                        System.out.print(tempCard.toString());
                        hasFound = true;
                    }
                }
                if(!hasFound)
                    System.out.print("   *   ");
            }
            System.out.println();
        }
    }


    /**
     * Methode pour placer une nouvelle carte sur le plateau
     *
     * @param newCard      la carte à ajouter sur le plateau
     * @param currentBoard le plateau de jeu actuel
     * @see Board
     * @see Card
     */
    public void placeNewCard(Card newCard, Board currentBoard)
    {
        if(!canPlaceCard)
        {
            if(!canPlace2ndCard)
            {
                System.out.println("\n \n");
                System.out.println("Vous ne pouvez pas(plus) utiliser cette commande.");

                System.out.println("\n \n");
                return;
            }
        }



        if(canMoveCard)
        {
            System.out.println("\n \n");
            System.out.println("Vous devez déplacer une carte avant d'en ajouter une !");
            System.out.println("\n \n");
            return;
        }

        this.showBoard();

        System.out.println("\n \n");

        //Affichage de la nouvelle carte
        System.out.println("Voici la carte a poser : \n" + newCard.toString());


        Coordinate position = askPlayerCoordinates(true);

        gmc.addCardOnBoard(newCard, position, false);

        if(!canPlaceCard && canPlace2ndCard)
            canPlace2ndCard = false;

        if(canPlaceCard)
            this.canPlaceCard = false;

        System.out.println("\n \n");
    }

    /**
     * Methode pour deplacer une carte du plateau
     *
     * @param currentBoard le plateau de jeu actuel
     * @see Board
     * @see Card
     */
    public void moveCard(Board currentBoard)
    {
        if(!canMoveCard)
        {

            System.out.println("\n \n");
            System.out.println("Vous ne pouvez pas(plus) utiliser cette commande.");
            System.out.println("\n \n");
            return;
        }
            this.showBoard();
            System.out.println("\n \n");
            Card cardToMove;

            Coordinate position = askPlayerCoordinates(false);

            Coordinate currentCard =  gmc.findEqualsCoordinate(position);
            //récupération de la carte à bouger

            cardToMove = gmc.getCardFromCoordinate(currentCard);

            gmc.removeFromCoordinate(currentCard);

            position = askPlayerCoordinates(true);

            //Placement de la carte
            gmc.addCardOnBoard(cardToMove, position, true);

        this.canMoveCard = false;

        this.showBoard();
        System.out.println("\n \n");
    }

    /**
     * Demande au joueur une coordonnée pour déplacer ou placer une carte, et verifie si la position est valide.
     *
     * @param searchForFreePlace    boolean pour savoir si on cherche une place libre ou occupee, permet d'optimiser la fonction
     * @return Coordinate       La coordonnee valide choisie
     * @see    Coordinate
     */
    public Coordinate askPlayerCoordinates(boolean searchForFreePlace)
    {
        Scanner sc = new Scanner(System.in);

        boolean exists = true;
        Coordinate position = new Coordinate(-1, -1);

        while(exists) {
            if(searchForFreePlace)
                System.out.print("Coordonnees ou placer la carte ? \n X :");
            else
                System.out.print("Coordonnées de la carte a deplacer ? \n X :");

            position.setX (sc.nextInt());

            System.out.println("Y :");
            position.setY(sc.nextInt());

            if(searchForFreePlace)
            {
                if(!gmc.isPlaceAvailable(position))
                    System.out.println("Cette place est prise  ! Reessayez");
                else if( !gmc.isCoordinateCloseEnough(position))
                    System.out.println("Cette place  trop loin ! Reessayez");
                else
                    exists = false;
            } else
            {
                if(gmc.isPlaceAvailable(position) )
                    System.out.println("Cette place n'est pas occupee ! Reessayez");
                else
                    exists = false;
            }
        }
        return position;
    }



    public void setGmc(GameController gmc) {
        this.gmc = gmc;
    }

    public GameController getCtrl() {
        return gmc;
    }

    @Override
    public void occure() {
    }

    /**
     * Permet de mélanger le plateau de jeu si le joueur en a la possibilite
     *
     * @param currentPlayer Le joueur actuel
     */
    public void shuffle(Player currentPlayer)
   {
       if(!canShuffle)
       {

           System.out.println("\n \n");
           System.out.println("Vous ne pouvez pas(plus) utiliser cette commande.");
           System.out.println("\n \n");
           return;
       }

       System.out.println("Voulez-vous Shuffle ? 1 | 0");
       int choix;
       try {
           choix = sc.nextInt();
       }
       catch(Exception e) {
           choix = -1;
       }

       this.gmc.getGameManager().getCurrentBoard().shuffle();

       currentPlayer.setHasShuffled(true);

       this.canShuffle = false;
       System.out.println("\n \n");
   }

    /**
     * Affiche la carte de victoire du joueur
     *
     * @param victoryCard la carte de victoire en question
     * @see Card
     */
    public void showVictoryCard(Card victoryCard) {
            System.out.println("\n \n");
            System.out.println("Voici votre carte victoire :");
            System.out.println(victoryCard.toString() + "\n \n");
    }


    /**
     * Permet de factoriser les cas recurrents ou une entree n'est pas bonne et que le resultat attendu est 1 ou 0
     *
     * @return le choix, 1 ou 2
     */
    private int badInputOneOrTwoChoice()
    {
        int choix = -1;
        System.out.println("Saisie incorrecte, recommencez : ");
        try {
            choix = sc.nextInt();
        }
        catch(Exception e) {
            choix = -1;
        }

        return choix;
    }

    /**
     * Permet d'alternet deux cartes sur le plateau
     *
     * @param currentBoard le plateau de jeu
     */
    public void alternateCards(Board currentBoard)
    {
        this.showBoard();
        if(!canAlternate)
        {

            System.out.println("\n \n");
            System.out.println("Vous ne pouvez pas(plus) utiliser cette commande.");
            System.out.println("\n \n");
            return;
        }


        //Affichage du board pour savoir où placer la carte
        this.showBoard();

        boolean exists = true;
        Coordinate position1 = new Coordinate(-1, -1);
        Coordinate position2 = new Coordinate(-1, 1);
        Card temp;
        //Choix de la première carte
        System.out.println("Choisissez la première carte à alterner");

        while(!exists) {
            System.out.println("Coordonnées de la carte ? \n X ?");
            position1.setX (sc.nextInt());

            System.out.println("Y ?");
            position1.setY(sc.nextInt());

            //   	exists=this.taken(position1, currentBoard);
            if(!exists)
                System.out.println("Il n'y a pas de carte ici ! Réessayez");
        }

        //Choix de la 2e carte
        System.out.println("Choisissez la seconde carte à alterner");
        while(!exists) {
            System.out.println("Coordonnées de la carte ? \n X ?");
            position1.setX (sc.nextInt());

            System.out.println("Y ?");
            position1.setY(sc.nextInt());

            if(!exists)
                System.out.println("Il n'y a pas de carte ici ! Réessayez");
        }
        //Alternance des cartes
        gmc.alternateCards(position1, position2);

        this.canAlternate = false;
        System.out.println("\n \n");
    }

    /**
     * Annonce qu'un joueur a changé de carte victoire
     */
    public void annoncePlayerChangeVictoryCard()
    {
        System.out.println("!!! Le joueur " + (gmc.getGameManager().getIndex()  + 1)  + " a change de Victory Card" );
    }

    /**
     * Message de fin de parties et affichage des scores
     */
    public void over()
    {
        System.out.println("============================ GAME OVER ========================");
        System.out.println("Points Triangular " +  gmc.getGameManager().getVisitor().getPointsByForm().get(Form.TRIANGULAR));
        System.out.println("Points Rectangular " + gmc.getGameManager().getVisitor().getPointsByForm().get(Form.RECTANGULAR));
        System.out.println("Points Circle " +  gmc.getGameManager().getVisitor().getPointsByForm().get(Form.CIRCLE));
        System.out.println("Points Blue " + gmc.getGameManager().getVisitor().getPointsByColor().get(Color.BLUE));
        System.out.println("Points Green " + gmc.getGameManager().getVisitor().getPointsByColor().get(Color.GREEN));
        System.out.println("Points Red " + gmc.getGameManager().getVisitor().getPointsByColor().get(Color.RED));
        System.out.println("Points Empty " + gmc.getGameManager().getVisitor().getPointsByState().get(State.EMPTY));
        System.out.println("Points Fill " + gmc.getGameManager().getVisitor().getPointsByState().get(State.FILL));

        int realPlayersAmount = gmc.getGameManager().getRealPlayersAmount();
        int virtualPlayersAmount = gmc.getGameManager().getVirtualPlayersAmount();
        Visitor visitor = gmc.getGameManager().getVisitor();
        Player[] players = gmc.getGameManager().getPlayers();


            if(realPlayersAmount + virtualPlayersAmount == 3)
            {
                int pointsPlayer1 =  visitor.getPointsTotalRegardingVictoryCard(players[0].getVictoryCard());
                int pointsPlayer2 =  visitor.getPointsTotalRegardingVictoryCard(players[1].getVictoryCard());
                int pointsPlayer3 =  visitor.getPointsTotalRegardingVictoryCard(players[2].getVictoryCard());

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

                int pointsPlayer1 =  visitor.getPointsTotalRegardingVictoryCard(players[0].getVictoryCard());
                int pointsPlayer2 =  visitor.getPointsTotalRegardingVictoryCard(players[1].getVictoryCard());

                if(pointsPlayer1 > pointsPlayer2)
                    System.out.println("Le joueur 1 gagne avec sa carte : " + players[0].getVictoryCard());
                else if((pointsPlayer2 > pointsPlayer1))
                    System.out.println("Le joueur 2 gagne avec sa carte : " + players[1].getVictoryCard());
                else
                    System.out.println("Match nul !");

        }
            System.out.println("Appuyez sur une touche pour continuer");
    }


    /**
     * Menu principal qui s'affiche lors du tour du joueur pour lui demander quelles actions réalisées
     * Il empeche au joueur de finir son tour s'il n'a pas realise les actions obligatoires
     */
    public void playerTurn()
    {
        boolean exit = false;
        int choix = 12;
        while(!exit)
        {
        System.out.println("--------------- A votre tour de jouer ! ");
        System.out.println("1 - voir sa VictoryCard");
        if(canMoveCard)
          System.out.println("2 - Bouger une carte");
        if(canPlaceCard || canPlace2ndCard)
            System.out.println("3 - Poser une carte");
        if(canShuffle)
            System.out.println("4 - Shuffle");
        if(canAlternate)
            System.out.println("5 - Alterner deux cartes");
        if(canChangeVictoryCard)
            System.out.println("6 - Changer de Victory Card");
        System.out.println("0 - Terminer son tour");
        System.out.print("Que faire : \t");


            try{
                choix = sc.nextInt();
            } catch(Exception e)
            {

            }
            while(choix != 1 && choix != 2 && choix != 3 && choix != 4 && choix != 5 && choix != 6 && choix != 0)
            {
                System.out.print("Saisie incorrecte, recommencez : \t");
                try{
                    choix = sc.nextInt();
                } catch(Exception e)
                {
                }
            }

            switch(choix)
            {
                case 0 :
                    if(canPlaceCard || canMoveCard || canPlace2ndCard || canAlternate)
                    {
                        System.out.println("Vous devez déplacer une carte et poser la votre");
                        break;
                    }
                    playerTurn = false;
                exit = true;
                break;
                case 1 : this.showVictoryCard( this.gmc.getGameManager().getPlayers()[this.gmc.getGameManager().getIndex()].getVictoryCard() ); break;
                case 2 : this.moveCard(this.gmc.getGameManager().getCurrentBoard()); break;
                case 3 : this.placeNewCard(this.gmc.getGameManager().getCardOnPlay(), this.gmc.getGameManager().getCurrentBoard() ); break;
                case 4 : this.shuffle(this.gmc.getGameManager().getPlayers()[this.gmc.getGameManager().getIndex()] ); break;
                case 5 : this.alternateCards(this.gmc.getGameManager().getCurrentBoard()); break;
                case 6 : this.changeVictoryCard(); break;
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        if(arg instanceof Events && o instanceof GameManager)
        {
            switch ((Events) arg)
            {
                case AskForPositionNewCard:
                    if(canPlaceCard)
                    {
                        canPlace2ndCard = true;
                        System.out.println("2ndCard");
                    }

                    else
                    canPlaceCard = true;

                    break;
                case AskForCardToMove: canMoveCard = true; break;
                case AskForShuffle: canShuffle = true; break;
                case AskForCardsToAlternate: canAlternate = true; break;
                case ShowBoard: this.showBoard(); break;
                case ShowCurrentPlayer: this.showCurrentPlayer(); break;
                case AskToChangeVictoryCard: canChangeVictoryCard = true; break;
                case AnnoncePlayerChangeVictoryCard: this.annoncePlayerChangeVictoryCard(); break;
                case GameOver: this.gameOver = true; this.over(); break;

                case PlayerTurn: this.playerTurn = true; break;
            }
        }
    }

    private boolean gameOver = false;

    /**
     * Processus qui permet d'avoir nos vues qui fonctionnent en parallèle. Dès lors qu'il s'agit du tour du joueur, il affiche les actions à effectuer
     */
    public void run() {
        try {
            while(!gameOver)
            {

                Thread.sleep(200);

                if(playerTurn)
                {
                    this.playerTurn();
                }

                if(gameOver)
                {
                    break;
                }

                this.gmc.getGameManager().setHasPlayed(true);
            }

        } catch (InterruptedException ie) {
            ie.getMessage();
        }
    }
}