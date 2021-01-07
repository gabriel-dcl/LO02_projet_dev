package Vues.graphiques;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import Controllers.GameController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Jeu {

	private JFrame frame;
	private GameController ctrl;
	
	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
			if (imgURL != null) {
				return new ImageIcon(imgURL, description);
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
					Jeu window = new Jeu();
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

	/**
	 * Create the application.
	 */
	public Jeu() {
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

		JButton btnShowVCard = new JButton("Afficher Carte Victoire");
		btnShowVCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JButton label1 = new JButton(createImageIcon("Cartes/EBC.jpg", "Un cercle"));
				GridBagConstraints gbc_label1 = new GridBagConstraints();
				gbc_label1.insets = new Insets(0, 0, 5, 5);
				gbc_label1.gridx = 1;
				gbc_label1.gridy = 14;
				frame.getContentPane().add(label1, gbc_label1);
			}
		});
		GridBagConstraints gbc_btnShowVCard = new GridBagConstraints();
		gbc_btnShowVCard.insets = new Insets(0, 0, 5, 5);
		gbc_btnShowVCard.gridx = 1;
		gbc_btnShowVCard.gridy = 2;
		frame.getContentPane().add(btnShowVCard, gbc_btnShowVCard);

		JButton btnPlaceNewCard = new JButton("Placer la nouvelle carte");
		btnPlaceNewCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.addCardOnBoard(null, null);
			}
		});
		GridBagConstraints gbc_btnPlaceNewCard = new GridBagConstraints();
		gbc_btnPlaceNewCard.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlaceNewCard.gridx = 1;
		gbc_btnPlaceNewCard.gridy = 4;
		frame.getContentPane().add(btnPlaceNewCard, gbc_btnPlaceNewCard);

		JButton btnMoveCard = new JButton("D\u00E9placer une carte");
		btnMoveCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_btnMoveCard = new GridBagConstraints();
		gbc_btnMoveCard.insets = new Insets(0, 0, 5, 5);
		gbc_btnMoveCard.gridx = 1;
		gbc_btnMoveCard.gridy = 6;
		frame.getContentPane().add(btnMoveCard, gbc_btnMoveCard);

		JButton btnShuffle = new JButton("Shuffle !");
		btnShuffle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_btnShuffle = new GridBagConstraints();
		gbc_btnShuffle.insets = new Insets(0, 0, 5, 5);
		gbc_btnShuffle.gridx = 1;
		gbc_btnShuffle.gridy = 8;
		frame.getContentPane().add(btnShuffle, gbc_btnShuffle);

		JButton btnAlternateCards = new JButton("Echanger 2 cartes");
		btnAlternateCards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.alternateCards(null, null);
			}
		});
		GridBagConstraints gbc_btnAlternateCards = new GridBagConstraints();
		gbc_btnAlternateCards.insets = new Insets(0, 0, 5, 5);
		gbc_btnAlternateCards.gridx = 1;
		gbc_btnAlternateCards.gridy = 10;
		frame.getContentPane().add(btnAlternateCards, gbc_btnAlternateCards);

		JButton btnNewButton_5 = new JButton("Fin du tour");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 1;
		gbc_btnNewButton_5.gridy = 12;
		frame.getContentPane().add(btnNewButton_5, gbc_btnNewButton_5);
	}

}
