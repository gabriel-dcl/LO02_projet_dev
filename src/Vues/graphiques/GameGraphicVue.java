package Vues.graphiques;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout.Constraints;

import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import Controllers.GameController;
import Models.Card;
import Models.Coordinate;
import Models.GameManager;
import Vues.Vue;
import enums.Events;

import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
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
	private boolean canPlaceCard = false;
	private boolean canMoveCard = false;
	private boolean canAlternateCard = false;
	private Coordinate position1 = null;
	private Coordinate position2 = null;
	private boolean canShuffle;
	private boolean canAlternate;
	private boolean canChangeVictoryCard;
	private JButton btnEndTurn;
	private JButton btnChangeVCard;
	private JLabel lblMessage;

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
		ctrl = new GameController();
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

		drawBoard();

	}

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(200);

				if (playerTurn) {
					this.playerTurn();
				}

				if (gameOver) {
					break;
				}

				this.ctrl.getGameManager().setHasPlayed(true);
			}

		} catch (InterruptedException ie) {
			// handle if you like
		}
	}

	private void playerTurn() {

		btnShowVCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomCarte = "Cartes/" + ctrl.getGameManager().getPlayers()[ctrl.getGameManager().getIndex()].getVictoryCard().toStringGraphic() + ".png" ;
				ImageIcon icon = createImageIcon(nomCarte, "carte victoire");
				JLabel carteVictoire = new JLabel(icon);
				GridBagConstraints gbc_carteVictoire = new GridBagConstraints();
				gbc_carteVictoire.insets = new Insets(0, 0, 5, 5);
				gbc_carteVictoire.gridx = 1;
				gbc_carteVictoire.gridy = 16;
				frame.getContentPane().add(carteVictoire, gbc_carteVictoire);
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
				canPlaceCard = true;
			}
		});

		btnMoveCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		btnShuffle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.getGameManager().getCurrentBoard().shuffle();
				showBoard();
			}
		});

		btnAlternateCards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		btnEndTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

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
					public void actionPerformed(ActionEvent arg0) {
						Coordinate coord = new Coordinate(-1, -1);
						coord.setX(current_gbc_board.gridx);
						coord.setY(current_gbc_board.gridy);
						if (canPlaceCard) {
							ctrl.addCardOnBoard(ctrl.getGameManager().getCardOnPlay(), coord);
							showBoard();
							disableBoard();
						}
						if (canMoveCard) {
							if (position1 != null) {
								position2 = coord;
								Card cardToMove;
								cardToMove = ctrl.getCardFromCoordinate(position1);
								ctrl.removeFromCoordinate(position1);
								ctrl.addCardOnBoard(cardToMove, position2);
								canMoveCard = false;
								showBoard();
								position1 = null;
								position2 = null;
								disableBoard();

							} else
								position1 = coord;
						}
						if (canAlternateCard) {
							if (position1 != null) {
								position2 = coord;
								ctrl.alternateCards(position1, position2);
								canAlternateCard = false;
								showBoard();
								position1 = null;
								position2 = null;
								disableBoard();
							} else
								position1 = coord;

						}

					}
				});
			}

		}
		frame.setVisible(false);
		frame.setVisible(true);

	}

	public void showBoard() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				boolean hasFound = false;

				for (Map.Entry<Coordinate, Card> entry : ctrl.getGameManager().getCurrentBoard()
						.getCurrentCardsOnBoard().entrySet()) {
					if (entry.getKey().equals(new Coordinate(i, j))) {

						String nomCarte = "Cartes/" + ctrl.getGameManager().getCurrentBoard()
								.getCardByCoordinate(entry.getKey()).toStringGraphic() + ".png";
						Board[i][j].setIcon(createImageIcon(nomCarte, "Vide"));
						hasFound = true;
					}
				}
				if (!hasFound)
					Board[i][j].setIcon(createImageIcon("Cartes/Blank.png", "Vide"));
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
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

	private void over() {
		// TODO Auto-generated method stub
		
	}

	private void annoncePlayerChangeVictoryCard() {
		// TODO Auto-generated method stub
		
	}

	private void showCurrentPlayer() {
		// TODO Auto-generated method stub
		
	}

}
