package Vues.graphiques;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.SpringLayout.Constraints;

import java.awt.GridBagLayout;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import Controllers.GameController;
import Models.*;
import Vues.Vue;
import enums.Events;
import enums.Form;
import enums.State;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class GameGraphicVue implements Vue, Observer, Runnable {

	private JFrame frame;
	private GameController ctrl;
	private boolean playerTurn = false;
	private boolean gameOver = false;
	private JButton Board[][] = new JButton[12][12];
	private GridBagConstraints gbc_board[][] = new GridBagConstraints[12][12];
	private GridBagConstraints current_gbc_board;
	private JButton btnShowVCard;
	private JButton btnPlaceNewCard;
	private JButton btnMoveCard;
	private JButton btnShuffle;
	private JButton btnAlternateCards;
	private JLabel lblCarteAffichee;
	private boolean canPlaceCard = false;
	private boolean canMoveCard = false;
	private Coordinate position1 = null;
	private Coordinate position2 = null;
	private boolean canShuffle;
	private boolean canAlternate;
	private boolean canChangeVictoryCard;
	private JButton btnEndTurn;
	private JButton btnChangeVCard;
	private JLabel lblMessage;
	private boolean askToPlayCard;
	private boolean askToAlternateCard;
	private boolean askToMoveCard;
	private boolean exit;
	private boolean canPlace2ndTime = false;
	private boolean firstClickShowVictoryCard = true;
	
	private Card carteAJouer;
	private boolean cardToMoveIdentified = false;
	


	private boolean hasSetUp = false;

	protected ImageIcon createImageIcon(String path, String description) {

		if (path != null) {
			return new ImageIcon(path, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGraphicVue window = new GameGraphicVue();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GameController getCtrl() {
		return ctrl;
	}

	@Override
	public void occure() {

	}

	/**
	 * Create the application.
	 */
	public GameGraphicVue() {
		initialize();
		this.frame.setVisible(true);
		ctrl = new GameController(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1653, 811);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 76, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		lblMessage = new JLabel("Bienvenue !");
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.insets = new Insets(0, 0, 5, 5);
		gbc_lblMessage.gridx = 1;
		gbc_lblMessage.gridy = 0;
		frame.getContentPane().add(lblMessage, gbc_lblMessage);

		btnShowVCard = new JButton("Afficher Carte Victoire");

		GridBagConstraints gbc_btnShowVCard = new GridBagConstraints();
		gbc_btnShowVCard.insets = new Insets(0, 0, 5, 5);
		gbc_btnShowVCard.gridx = 1;
		gbc_btnShowVCard.gridy = 2;
		frame.getContentPane().add(btnShowVCard, gbc_btnShowVCard);

		btnPlaceNewCard = new JButton("Placer la nouvelle carte");

		GridBagConstraints gbc_btnPlaceNewCard = new GridBagConstraints();
		gbc_btnPlaceNewCard.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlaceNewCard.gridx = 1;
		gbc_btnPlaceNewCard.gridy = 4;
		frame.getContentPane().add(btnPlaceNewCard, gbc_btnPlaceNewCard);

		btnMoveCard = new JButton("D\u00E9placer une carte");
		GridBagConstraints gbc_btnMoveCard = new GridBagConstraints();
		gbc_btnMoveCard.insets = new Insets(0, 0, 5, 5);
		gbc_btnMoveCard.gridx = 1;
		gbc_btnMoveCard.gridy = 6;
		frame.getContentPane().add(btnMoveCard, gbc_btnMoveCard);

		btnShuffle = new JButton("Shuffle !");

		GridBagConstraints gbc_btnShuffle = new GridBagConstraints();
		gbc_btnShuffle.insets = new Insets(0, 0, 5, 5);
		gbc_btnShuffle.gridx = 1;
		gbc_btnShuffle.gridy = 8;
		frame.getContentPane().add(btnShuffle, gbc_btnShuffle);

		btnAlternateCards = new JButton("Echanger 2 cartes");

		GridBagConstraints gbc_btnAlternateCards = new GridBagConstraints();
		gbc_btnAlternateCards.insets = new Insets(0, 0, 5, 5);
		gbc_btnAlternateCards.gridx = 1;
		gbc_btnAlternateCards.gridy = 10;
		frame.getContentPane().add(btnAlternateCards, gbc_btnAlternateCards);

		btnChangeVCard = new JButton("Changer de Victory Card");
		
		GridBagConstraints gbc_btnChangeVCard = new GridBagConstraints();
		gbc_btnChangeVCard.insets = new Insets(0, 0, 5, 5);
		gbc_btnChangeVCard.gridx = 1;
		gbc_btnChangeVCard.gridy = 12;
		frame.getContentPane().add(btnChangeVCard, gbc_btnChangeVCard);

		btnEndTurn = new JButton("Fin du tour");
		btnEndTurn.setBackground(SystemColor.menu);
		GridBagConstraints gbc_btnEndTurn = new GridBagConstraints();
		gbc_btnEndTurn.insets = new Insets(0, 0, 5, 5);
		gbc_btnEndTurn.gridx = 1;
		gbc_btnEndTurn.gridy = 14;
		frame.getContentPane().add(btnEndTurn, gbc_btnEndTurn);
		
		
		ImageIcon icon = createImageIcon("Cartes/Blank.png", "carte victoire");
		lblCarteAffichee = new JLabel(icon);
		GridBagConstraints gbc_carteAffichee = new GridBagConstraints();
		gbc_carteAffichee.insets = new Insets(0, 0, 5, 5);
		gbc_carteAffichee.gridx = 1;
		gbc_carteAffichee.gridy = 16;
		frame.getContentPane().add(lblCarteAffichee, gbc_carteAffichee);

		btnEndTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				if(canMoveCard || canPlaceCard)
				{
					lblMessage.setText("Vous devez jouer votre tour.");
				}
				else
				{
					exit = true;
				}

			}
		});

		btnShowVCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showVictoryCard(ctrl.getGameManager().getPlayers()[ctrl.getGameManager().getIndex()].getVictoryCard());
				}
		});

		btnMoveCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveCard();
			}
		});

		btnPlaceNewCard.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				enableBoard();
				String nomCarte = "Cartes/" + ctrl.getGameManager().getCardOnPlay().toStringGraphic() + ".png";
				ImageIcon icon = createImageIcon(nomCarte, "carte à jouer");
				JLabel carteActuelle = new JLabel(icon);
				GridBagConstraints gbc_carteActuelle = new GridBagConstraints();
				gbc_carteActuelle.insets = new Insets(0, 0, 5, 5);
				gbc_carteActuelle.gridx = 1;
				gbc_carteActuelle.gridy = 16;
				frame.getContentPane().add(carteActuelle, gbc_carteActuelle);
				placeNewCard();
			}
		});

		btnShuffle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shuffle(ctrl.getGameManager().getCurrentBoard(),ctrl.getGameManager().getPlayers()[ctrl.getGameManager().getIndex()] );
				showBoard();
				
			}
		});

		btnAlternateCards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alternateCards();
			}
		});

		btnChangeVCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeVictoryCard();
			}
		});
	
		drawBoard();

	}

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(200);

				if (exit) {
					this.hasSetUp = false;
					exit = false;
					ctrl.getGameManager().setHasPlayed(true);
				}

				if (playerTurn)
				{
					this.playerTurn();
					Thread.sleep(200);
				}


				if (gameOver) {
					ctrl.getGameManager().setHasPlayed(true);
					this.hasSetUp = false;
					break;
				}


			}

		} catch (InterruptedException ie) {
			// handle if you like
		}
	}

	public void enableBoard() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				Board[i][j].setEnabled(true);
			}

		}

	}

	public void disableBoard() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				Board[i][j].setEnabled(false);
			}

		}

	}

	public void gameOver()
	{
		int realPlayersAmount = ctrl.getGameManager().getRealPlayersAmount();
		int virtualPlayersAmount = ctrl.getGameManager().getVirtualPlayersAmount();
		Visitor visitor = ctrl.getGameManager().getVisitor();
		Player[] players = ctrl.getGameManager().getPlayers();

		String message = "============================ GAME OVER ======================== \n" +
				"Points Triangular " +  ctrl.getGameManager().getVisitor().getPointsByForm().get(Form.TRIANGULAR) +
				"\nPoints Rectangular " + ctrl.getGameManager().getVisitor().getPointsByForm().get(Form.RECTANGULAR) +
				"\nPoints Circle " +  ctrl.getGameManager().getVisitor().getPointsByForm().get(Form.CIRCLE) +
				"\nPoints Blue " + ctrl.getGameManager().getVisitor().getPointsByColor().get(enums.Color.BLUE) +
				"\nPoints Green " + ctrl.getGameManager().getVisitor().getPointsByColor().get(enums.Color.GREEN) +
				"\nPoints Red " + ctrl.getGameManager().getVisitor().getPointsByColor().get(enums.Color.RED) +
				"\nPoints Empty " + ctrl.getGameManager().getVisitor().getPointsByState().get(State.EMPTY) +
				"\nPoints Fill " + ctrl.getGameManager().getVisitor().getPointsByState().get(State.FILL);


		if(realPlayersAmount + virtualPlayersAmount == 3)
		{
			int pointsPlayer1 =  visitor.getPointsTotalRegardingVictoryCard(players[0].getVictoryCard());
			int pointsPlayer2 =  visitor.getPointsTotalRegardingVictoryCard(players[1].getVictoryCard());
			int pointsPlayer3 =  visitor.getPointsTotalRegardingVictoryCard(players[2].getVictoryCard());

			message += "\n\n ======================\nCARTE J1:" + players[0].getVictoryCard() +
					"\nCARTE J2: " + players[1].getVictoryCard() +
					"\nCARTE J3: " + players[2].getVictoryCard();

			if(pointsPlayer1 > pointsPlayer2 && pointsPlayer1 > pointsPlayer3)
				message += "\nLe joueur 1 gagne avec sa carte : " + players[0].getVictoryCard();
			else if(pointsPlayer2 > pointsPlayer1 && pointsPlayer2 > pointsPlayer3)
				message += "\nLe joueur 2 gagne avec sa carte : " + players[1].getVictoryCard();
			else if(pointsPlayer3 > pointsPlayer1 && pointsPlayer3 > pointsPlayer2)
				message +="\nLe joueur 3 gagne avec sa carte : " + players[2].getVictoryCard();
			else
				message += "\nMatch nul !";
		}else
		{
			message += "\n\n ======================\nCARTE J1:" + players[0].getVictoryCard() +
					"\nCARTE J2: " + players[1].getVictoryCard();

			int pointsPlayer1 =  visitor.getPointsTotalRegardingVictoryCard(players[0].getVictoryCard());
			int pointsPlayer2 =  visitor.getPointsTotalRegardingVictoryCard(players[1].getVictoryCard());

			if(pointsPlayer1 > pointsPlayer2)
				message += "Le joueur 1 gagne avec sa carte : " + players[0].getVictoryCard();
			else if((pointsPlayer2 > pointsPlayer1))
				message += "Le joueur 2 gagne avec sa carte : " + players[1].getVictoryCard();
			else
				message += "Match nul !";

		}
		JOptionPane.showMessageDialog(null,
				message,
				"Game Over", 1);
		this.frame.setVisible(false); //you can't see me!
		this.frame.dispose(); //Destroy the JFrame object
	/*




*/
	}

	public void drawBoard() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {

				Board[i][j] = new JButton();
				gbc_board[i][j] = new GridBagConstraints();
				gbc_board[i][j].insets = new Insets(0, 0, 5, 5);
				gbc_board[i][j].gridx = 3 + i;
				gbc_board[i][j].gridy = 2 + j;
				current_gbc_board = gbc_board[i][j];
				frame.getContentPane().add(Board[i][j], gbc_board[i][j]);

				Board[i][j].setIcon(createImageIcon("Cartes/Blank.png", "Vide"));
				Board[i][j].setBackground(Color.LIGHT_GRAY);
				Board[i][j].setEnabled(false);

				Board[i][j].addActionListener(new ActionListener() {
					int x  = current_gbc_board.gridy - 2;
					int y  = current_gbc_board.gridx - 3;

					public void actionPerformed(ActionEvent arg0) {
						Coordinate coord = new Coordinate(-1, -1);

						coord.setX(x);
						coord.setY(y);

						if (askToPlayCard && canPlaceCard)
						{
							if (ctrl.isPlaceAvailable(coord))
							{
								if (ctrl.isCoordinateCloseEnough(coord))
								{
									askToPlayCard = false;

									if(canPlace2ndTime)
										canPlace2ndTime = false;
									else
									canPlaceCard = false;

									ctrl.addCardOnBoard(carteAJouer, coord, false);
									showBoard();

									disableBoard();
								}
								else
									lblMessage.setText("Trop loin ! Réessayez.");
								
							}
							else
								lblMessage.setText("Place occupée! Réessayez.");

						}
						if (askToMoveCard) {
							System.out.println("MOVECARD");
							if (position1 != null) {
								if (ctrl.isPlaceAvailable(coord) && ctrl.isCoordinateCloseEnough(coord))
								{
									position2 = coord;
									Card cardToMove;
									cardToMove = ctrl.getCardFromCoordinate(ctrl.findEqualsCoordinate(position1));
									ctrl.removeFromCoordinate(ctrl.findEqualsCoordinate(position1));
									ctrl.addCardOnBoard(cardToMove, position2, true);
									askToMoveCard = false;
									canMoveCard = false;
									showBoard();
									position1 = null;
									position2 = null;
									disableBoard();

								} else
									lblMessage.setText("Place déjà occupée ou trop loin! Réessayez");

							} else {
								if (ctrl.isPlaceAvailable(coord)) {
									lblMessage.setText("Aucune carte ici");
								} else
									position1 = coord;
									System.out.println("Position1 selected " + position1.getX() + "     " + position1.getY());

							}

						}
						if (askToAlternateCard) {
							if (position1 != null) {
								if (!ctrl.isPlaceAvailable(coord) && position1 != coord)
								{
									System.out.print("SupposedTOaLTER");

									position2 = coord;
									ctrl.alternateCards(position1, position2);
									askToAlternateCard = false;
									canAlternate = false;
									showBoard();
									position1 = null;
									position2 = null;
									disableBoard();
								} else
								{
									lblMessage.setText("Position invalide");
								}

							} else {

								if (!ctrl.isPlaceAvailable(coord))
								{
									position1 = coord;
								} else
								{
									lblMessage.setText("Position invalide");
								}
							}

						}
					}
				});
			}

		}
		frame.setVisible(false);
		frame.setVisible(true);

	}

	///////////////////////////////////////////////////////////

	private void showCurrentPlayer() {

		lblMessage.setText("=====> Joueur " + ( ctrl.getGameManager().getIndex() + 1));
	}

	public void changeVictoryCard() {
		if (!canChangeVictoryCard) {
			lblMessage.setText("Vous ne pouvez pas(plus) utiliser cette commande.");
			
			return;
		}

		ctrl.getGameManager().getPlayers()[ctrl.getGameManager().getIndex()].changeVictoryCard(ctrl.getGameManager().getCurrentBoard());
		this.annoncePlayerChangeVictoryCard();
		this.canChangeVictoryCard = false;

	}

	public void showBoard() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				boolean hasFound = false;

				for (Map.Entry<Coordinate, Card> entry : ctrl.getGameManager().getCurrentBoard().getCurrentCardsOnBoard().entrySet()) {
					if (entry.getKey().equals(new Coordinate(j, i))) {

						String nomCarte = "Cartes/" + ctrl.getGameManager().getCurrentBoard().getCardByCoordinate(entry.getKey()).toStringGraphic() + ".png";
						Board[i][j].setIcon(createImageIcon(nomCarte, "Vide"));
						hasFound = true;
					}
				}
				if (!hasFound)
					Board[i][j].setIcon(createImageIcon("Cartes/Blank.png", "Vide"));
			}
		}
		frame.setVisible(false);
		frame.setVisible(true);
	}

	public void placeNewCard() {
		if (!canPlaceCard) {
			lblMessage.setText("Vous ne pouvez pas(plus) utiliser cette commande.");
			return;
		}

		if (canMoveCard) {
			lblMessage.setText("Vous devez déplacer une carte avant d'en ajouter une !");
			return;
		}
		lblMessage.setText("Voici la carte à placer, où la placer ?");
		carteAJouer = ctrl.getGameManager().getCardOnPlay();
		String nomCarte = "Cartes/" + carteAJouer.toStringGraphic() + ".png";
		ImageIcon icon = createImageIcon(nomCarte, "carte a jouer");
		ImageIcon white = createImageIcon("Cartes/White.png", "Du blanc");
		lblCarteAffichee.setIcon(white);
		frame.setVisible(false);
		frame.setVisible(true);
		lblCarteAffichee.setIcon(icon);
		frame.show();
		askToPlayCard = true;
		enableBoard();
		
	}

	public void moveCard() {
		if (!canMoveCard) {

			lblMessage.setText("Vous ne pouvez pas(plus) utiliser cette commande.");
			return;
		}

		askToMoveCard = true;
		enableBoard();

	}

	public void shuffle(Board currentBoard, Player currentPlayer) {
		if(!canShuffle)
	       {

	           lblMessage.setText("Vous ne pouvez pas(plus) utiliser cette commande.");
	           
	           return;
	       }
		this.ctrl.getGameManager().getCurrentBoard().shuffle();
		currentPlayer.setHasShuffled(true);
		canShuffle = false;
	}

	public void showVictoryCard(Card victoryCard) {
		
			String nomCarte = "Cartes/" + ctrl.getGameManager().getPlayers()[ctrl.getGameManager().getIndex()]
					.getVictoryCard().toStringGraphic() + ".png";
			ImageIcon icon = createImageIcon(nomCarte, "carte victoire");
			ImageIcon white = createImageIcon("Cartes/White.png", "Du blanc");
			lblCarteAffichee.setIcon(white);
			frame.setVisible(false);
			frame.setVisible(true);
			lblCarteAffichee.setIcon(icon);
			frame.setVisible(false);
			frame.setVisible(true);
			
		
	}

	public void alternateCards() {

		System.out.print("eesfef" + canAlternate);
		if (!canAlternate) {
			lblMessage.setText("Vous ne pouvez pas(plus) utiliser cette commande.");
			return;
		}
		askToAlternateCard = true;
		enableBoard();

	}
	
	public void annoncePlayerChangeVictoryCard() {
		lblMessage.setText("!!! Le joueur " + (ctrl.getGameManager().getIndex()  + 1)  + " a change de Victory Card" );

	}

	private void over() {
		// TODO Auto-generated method stub

	}

	private void playerTurn() {
			exit = false;

			if(canAlternate)
				btnAlternateCards.setEnabled(true);
			else
				btnAlternateCards.setEnabled(false);
			if(canChangeVictoryCard)
				btnChangeVCard.setEnabled(true);
			else
				btnChangeVCard.setEnabled(false);
			if(canMoveCard)
				btnMoveCard.setEnabled(true);
			else
				btnMoveCard.setEnabled(false);
			if(canPlaceCard)
				btnPlaceNewCard.setEnabled(true);
			else
				btnPlaceNewCard.setEnabled(false);
			if(canShuffle)
				btnShuffle.setEnabled(true);
			else
				btnShuffle.setEnabled(false);

			hasSetUp = true;


	}

	
	public void update(Observable o, Object arg) {
		if (arg instanceof Events && o instanceof GameManager) {
			switch ((Events) arg) {
			case AskForPositionNewCard:
				if(canPlaceCard)
					canPlace2ndTime = true;

				canPlaceCard = true;
				break;
			case AskForCardToMove:
				canMoveCard = true;
				break;
			case AskForShuffle:
				canShuffle = true;
				break;
			case AskForCardsToAlternate:
				canAlternate = true;
				break;
			case ShowBoard:
				this.showBoard();
				break;
			case ShowCurrentPlayer:
				this.showCurrentPlayer();
				break;
			case AskToChangeVictoryCard:
				canChangeVictoryCard = true;
				break;
			case AnnoncePlayerChangeVictoryCard:
				this.annoncePlayerChangeVictoryCard();
				break;
			case GameOver:
				this.gameOver();
				this.gameOver = true;
				this.over();
				break;

			case PlayerTurn:
				this.playerTurn = true;
				break;
			}
		}
	}



}
