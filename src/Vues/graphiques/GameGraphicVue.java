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
import Vues.Vue;

import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class GameGraphicVue implements Vue, Observer, Runnable {

	private JFrame frame;
	private GameController ctrl;
	private boolean playerTurn = false;
	private boolean gameOver = false;
	private JButton Board[][] = new JButton[12][12];
	private JButton btnShowVCard;
	private JButton btnPlaceNewCard;
	private JButton btnMoveCard;
	private JButton btnShuffle;
	private JButton btnAlternateCards;
	private JButton btnEndTurn;
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

		btnEndTurn = new JButton("Fin du tour");
		btnEndTurn.setBackground(UIManager.getColor("Button.background"));
		
		GridBagConstraints gbc_btnEndTurn = new GridBagConstraints();
		gbc_btnEndTurn.insets = new Insets(0, 0, 5, 5);
		gbc_btnEndTurn.gridx = 1;
		gbc_btnEndTurn.gridy = 12;
		frame.getContentPane().add(btnEndTurn, gbc_btnEndTurn);
		
		drawBoard();
		
		
	}
	@Override
	public void run() {
		try {
            while(true)
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

                this.ctrl.getGameManager().setHasPlayed(true);
            }

        } catch (InterruptedException ie) {
            // handle if you like
        }
	}
	
	private void playerTurn() {
		btnShowVCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		btnPlaceNewCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.addCardOnBoard(null, null);
			}
		});
		
		btnMoveCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		
		btnShuffle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		btnAlternateCards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.alternateCards(null, null);
			}
		});
		
		btnEndTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
	}
	
	public void drawBoard()
    {
        for (int i = 0; i < 12; i++)
        {
            for (int j = 0; j < 12; j++)
            {
            	Board[i][j] = new JButton();
				GridBagConstraints gbc_label1 = new GridBagConstraints();
				gbc_label1.insets = new Insets(0, 0, 5, 5);
				gbc_label1.gridx = 3+i;
				gbc_label1.gridy = 2+j;
				frame.getContentPane().add(Board[i][j], gbc_label1);
				
				Board[i][j].setIcon(createImageIcon("Cartes/Blank.png", "Vide"));
				Board[i][j].setBackground(Color.LIGHT_GRAY);
            }
            
        }
        frame.setVisible(false);
		frame.setVisible(true);
    }
	
	public void showBoard()
    {
        for (int i = 0; i < 12; i++)
        {
            for (int j = 0; j < 12; j++)
            {
                boolean hasFound = false;

                for (Map.Entry<Coordinate, Card> entry : ctrl.getGameManager().getCurrentBoard().getCurrentCardsOnBoard().entrySet())
                {
                    if (entry.getKey().equals( new Coordinate(i, j)) )
                    {
                        
                        String nomCarte = "Cartes/"+ctrl.getGameManager().getCurrentBoard().getCardByCoordinate(entry.getKey()).toStringGraphic()+".png";
                        Board[i][j].setIcon(createImageIcon(nomCarte, "Vide"));
                        hasFound = true;
                    }
                }
                if(!hasFound)
                	Board[i][j].setIcon(createImageIcon("Cartes/Blank.png", "Vide"));
            }
        }
    }
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
