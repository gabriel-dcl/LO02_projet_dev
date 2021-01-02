package Vues.console;

import Controllers.GameController;
import Models.*;
import enums.Color;
import enums.Events;
import enums.Form;
import enums.State;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class ConsoleVue implements Observer {

    Scanner sc;
    private GameController gmc;

    public void showCurrentPlayer()
    {
        System.out.println("=====> Joueur " + ( this.gmc.getGameManager().getIndex() + 1));
    }

    public void changeVictoryCard()
    {
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
            System.out.println("Saisie incorrecte, recommencez : ");
            try{
                choix = sc.nextInt();
            } catch(Exception e)
            {
                choix = -1;
            }
        }

        if(choix == 1)
        {
            gmc.getGameManager().getPlayers()[gmc.getGameManager().getIndex()].changeVictoryCard(gmc.getGameManager().getCurrentBoard());
            this.annoncePlayerChangeVictoryCard();
        }
    }

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

    public void placeNewCard(Card newCard, Board currentBoard)
    {
        Scanner sc = new Scanner(System.in);
        //Affichage de la nouvelle carte
        System.out.println("Voici la carte a poser : /n" + newCard.toString());

        Coordinate position = askPlayerCoordinates(currentBoard, true);
        currentBoard.addCardOnBoard(newCard, position);
    }

    public void moveCard(Board currentBoard)
    {
        Scanner sc = new Scanner(System.in);

        //Demande de réaliser cette action
        System.out.println("Voulez vous bouger une carte ? 1 | 0");
        int choix = sc.nextInt();

        if (choix==1)
        {
            Card cardToMove;

            Coordinate position = askPlayerCoordinates(currentBoard, false);

            Coordinate currentCard =  currentBoard.findEqualsCoordinate(position);
            //récupération de la carte à bouger

            cardToMove = currentBoard.getCurrentCardsOnBoard().get(currentCard);

            currentBoard.getCurrentCardsOnBoard().remove(currentCard);

            position = askPlayerCoordinates(currentBoard, true);

            //Placement de la carte
            currentBoard.addCardOnBoard(cardToMove, position);
        }

        else System.out.println("Option refusée");

    }

    public Coordinate askPlayerCoordinates(Board currentBoard, boolean searchForFreePlace)
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
                if(!currentBoard.isPlaceAvailable(position))
                    System.out.println("Cette place est prise  ! Reessayez");
                else if( !currentBoard.isCoordinateCloseEnough(position))
                    System.out.println("Cette place  trop loin ! Reessayez");
                else
                    exists = false;
            } else
            {
                if(currentBoard.isPlaceAvailable(position) )
                    System.out.println("Cette place n'est pas occupee ! Reessayez");
                else
                    exists = false;
            }
        }
        return position;
    }


    public int difficultyChoice()
    {
        int difficulty = 0;
        System.out.print("Dans quelle difficulty voulez-vous jouer (0 ou 1): \t");

        try {
            difficulty  = sc.nextInt();
        }
        catch(Exception e) {
            sc.next();
            difficulty  = 12;
        }

        return difficulty;
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

    public int badInputDifficulty()
    {
        int difficulty = 0;
        System.out.print("Saisie incorrecte, recommencez. 1 | 0 \t");

        try {
            difficulty  = sc.nextInt();
        }
        catch(Exception e) {
            sc.next();
            difficulty  = 12;
        }

        return difficulty;
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

    public void setGmc(GameController gmc) {
        this.gmc = gmc;
    }

    public ConsoleVue()
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

   public void shuffle(Board currentBoard, Player currentPlayer)
   {
       System.out.println("Voulez-vous Shuffle ? 1 | 0");
       int choix;
       try {
           choix = sc.nextInt();
       }
       catch(Exception e) {
           choix = -1;
       }

       currentBoard.shuffle();
       currentPlayer.setHasShuffled(true);
   }

    public void showVictoryCard(Card victoryCard) {

        System.out.println("Voulez-vous voir votre carte victoire ? 1 | 0");
        int choix;
        try {
            choix = sc.nextInt();
        }
        catch(Exception e) {
            choix = -1;
        }

        while(choix != 1 && choix != 0)
        {
            System.out.println("Saisie incorrecte, recommencez : ");
            try {
                choix = sc.nextInt();
            }
            catch(Exception e) {
                choix = -1;
            }
        }

        if(choix == 1)
        {

            System.out.println("Voici votre carte victoire :");
            System.out.println(victoryCard.toString());
        }
    }

    public void alternateCards(Board currentBoard)
    {
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
        temp = currentBoard.getCurrentCardsOnBoard().get(position1);
        currentBoard.getCurrentCardsOnBoard().put(position1, currentBoard.getCurrentCardsOnBoard().get(position2));
        currentBoard.getCurrentCardsOnBoard().put(position2, temp);
    }

    public void annoncePlayerChangeVictoryCard()
    {
        System.out.println("!!! Le joueur " + (gmc.getGameManager().getIndex()  + 1)  + " a change de Victory Card" );
    }



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
    }



    @Override
    public void update(Observable o, Object arg) {

        if(arg instanceof Events && o instanceof GameManager)
        {
            switch ((Events) arg)
            {
                case AskForPositionNewCard: this.placeNewCard(((GameManager) o).getCardOnPlay(), ((GameManager) o).getCurrentBoard() ); break;
                case AskForCardToMove: this.moveCard(((GameManager) o).getCurrentBoard() ); break;
                case AskToShowVictoryCard: this.showVictoryCard( ((GameManager) o).getPlayers()[((GameManager) o).getIndex()].getVictoryCard() );
                case AskForShuffle: this.shuffle(((GameManager) o).getCurrentBoard(), ((GameManager) o).getPlayers()[((GameManager) o).getIndex()] );
                case AskForCardsToAlternate: this.alternateCards(((GameManager) o).getCurrentBoard()); break;
                case ShowBoard: this.showBoard(); break;
                case ShowCurrentPlayer: this.showCurrentPlayer(); break;
                case AskToChangeVictoryCard: this.changeVictoryCard(); break;
                case AnnoncePlayerChangeVictoryCard: this.annoncePlayerChangeVictoryCard(); break;
                case GameOver: this.over(); break;
            }
        }
    }
}
