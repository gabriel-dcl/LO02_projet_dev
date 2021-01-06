package Vues.graphiques;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class Jeu {

	private JFrame frame;
	private JTextField textFieldCoordinates;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jeu window = new Jeu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Jeu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 840, 811);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 321, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JButton btnShowVCard = new JButton("Afficher Carte Victoire");
		GridBagConstraints gbc_btnShowVCard = new GridBagConstraints();
		gbc_btnShowVCard.insets = new Insets(0, 0, 5, 5);
		gbc_btnShowVCard.gridx = 1;
		gbc_btnShowVCard.gridy = 2;
		frame.getContentPane().add(btnShowVCard, gbc_btnShowVCard);
		
		JButton btnPlaceNewCard = new JButton("Placer la nouvelle carte");
		GridBagConstraints gbc_btnPlaceNewCard = new GridBagConstraints();
		gbc_btnPlaceNewCard.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlaceNewCard.gridx = 1;
		gbc_btnPlaceNewCard.gridy = 4;
		frame.getContentPane().add(btnPlaceNewCard, gbc_btnPlaceNewCard);
		
		JButton btnMoveCard = new JButton("D\u00E9placer une carte");
		GridBagConstraints gbc_btnMoveCard = new GridBagConstraints();
		gbc_btnMoveCard.insets = new Insets(0, 0, 5, 5);
		gbc_btnMoveCard.gridx = 1;
		gbc_btnMoveCard.gridy = 6;
		frame.getContentPane().add(btnMoveCard, gbc_btnMoveCard);
		
		JButton btnShuffle = new JButton("Shuffle !");
		GridBagConstraints gbc_btnShuffle = new GridBagConstraints();
		gbc_btnShuffle.insets = new Insets(0, 0, 5, 5);
		gbc_btnShuffle.gridx = 1;
		gbc_btnShuffle.gridy = 8;
		frame.getContentPane().add(btnShuffle, gbc_btnShuffle);
		
		JButton btnAlternateCards = new JButton("Echanger 2 cartes");
		GridBagConstraints gbc_btnAlternateCards = new GridBagConstraints();
		gbc_btnAlternateCards.insets = new Insets(0, 0, 5, 5);
		gbc_btnAlternateCards.gridx = 1;
		gbc_btnAlternateCards.gridy = 10;
		frame.getContentPane().add(btnAlternateCards, gbc_btnAlternateCards);
		
		JButton btnNewButton_5 = new JButton("Fin du tour");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 1;
		gbc_btnNewButton_5.gridy = 12;
		frame.getContentPane().add(btnNewButton_5, gbc_btnNewButton_5);
		
		textFieldCoordinates = new JTextField();
		GridBagConstraints gbc_textFieldCoordinates = new GridBagConstraints();
		gbc_textFieldCoordinates.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCoordinates.gridx = 1;
		gbc_textFieldCoordinates.gridy = 15;
		frame.getContentPane().add(textFieldCoordinates, gbc_textFieldCoordinates);
		textFieldCoordinates.setColumns(10);
	}

}
