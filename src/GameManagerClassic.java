public class GameManagerClassic  extends GameManager {
    private int difficulty;


    public void game() {



        for (int i = 0; i < 12; i++) {

            Card cardOnPlay = currentBoard.getCard();

            System.out.print("Votre carte :");
            System.out.println(cardOnPlay.toString());

            System.out.print("Position X: ");
            int x = sc.nextInt();
            System.out.print("Position Y: ");
            int y = sc.nextInt();
            if(i == 0)
              currentBoard.addCardOnBoard(cardOnPlay, new Coordinate(x, y), true);
            else
                currentBoard.addCardOnBoard(cardOnPlay, new Coordinate(x, y), false);

            System.out.println("");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            currentBoard.showBoard();
        }
    }
    public void getInstance() {
    }
    private void singleton() {
    }

}
