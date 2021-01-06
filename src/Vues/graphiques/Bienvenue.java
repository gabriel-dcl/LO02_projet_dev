package Vues.graphiques;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JMenuBar;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;

import Controllers.SetUpController;
import enums.BoardType;
import enums.Difficulty;
import enums.GameMode;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

public class Bienvenue implements Observer {

	private JFrame frame;
	private SetUpController ctrl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bienvenue window = new Bienvenue();
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
	public Bienvenue() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		/*Barre de menu*/
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(25, 54, 673, 51);
		frame.getContentPane().add(menuBar);
			/*Menu de Forme de Plateau*/
		JMenu mnBoardForm = new JMenu("Choix Forme Plateau");
		menuBar.add(mnBoardForm);
		ButtonGroup choixPlateau = new ButtonGroup();
				/*Choix plateau rectangulaire*/
		JRadioButtonMenuItem rdbtnmnitmRectangular = new JRadioButtonMenuItem("Rectangulaire");
		choixPlateau.add(rdbtnmnitmRectangular);
		mnBoardForm.add(rdbtnmnitmRectangular);
				/*Choix plateau triangulaire*/
		JRadioButtonMenuItem rdbtnmnitmTriangular = new JRadioButtonMenuItem("Triangulaire");
		choixPlateau.add(rdbtnmnitmTriangular);
		mnBoardForm.add(rdbtnmnitmTriangular);
			/*Menu de choix de mode de jeu*/
		JMenu mnGameMode = new JMenu("Choix Mode de Jeu");
		menuBar.add(mnGameMode);
		ButtonGroup choixModeJeu = new ButtonGroup();
				/**/
		JRadioButtonMenuItem rdbtnmnitmClassic = new JRadioButtonMenuItem("Classique");
		choixModeJeu.add(rdbtnmnitmClassic);
		mnGameMode.add(rdbtnmnitmClassic);
				/**/
		JRadioButtonMenuItem rdbtnmnitmChaos = new JRadioButtonMenuItem("Chaos");
		choixModeJeu.add(rdbtnmnitmChaos);
		mnGameMode.add(rdbtnmnitmChaos);
				/**/
		JRadioButtonMenuItem rdbtnmnitmQuick = new JRadioButtonMenuItem("Rapide");
		choixModeJeu.add(rdbtnmnitmQuick);
		mnGameMode.add(rdbtnmnitmQuick);
			/**/
		JMenu mnDifficulty = new JMenu("Choix Difficult\u00E9");
		menuBar.add(mnDifficulty);
		ButtonGroup choixDiff = new ButtonGroup();
				/**/
		JRadioButtonMenuItem rdbtnmnitmFacile = new JRadioButtonMenuItem("Facile");
		choixDiff.add(rdbtnmnitmFacile);
		mnDifficulty.add(rdbtnmnitmFacile);
				/**/
		JRadioButtonMenuItem rdbtnmnitmDifficile = new JRadioButtonMenuItem("Difficile");
		choixDiff.add(rdbtnmnitmDifficile);
		mnDifficulty.add(rdbtnmnitmDifficile);
			/**/
		JMenu mnNbBots = new JMenu("Nombre de bots");
		menuBar.add(mnNbBots);
		ButtonGroup choixNbBots = new ButtonGroup();
				/**/
		JRadioButtonMenuItem rdbtnmnitm0bots = new JRadioButtonMenuItem("0");
		rdbtnmnitm0bots.setEnabled(false);
		choixNbBots.add(rdbtnmnitm0bots);
		mnNbBots.add(rdbtnmnitm0bots);
				/**/
		JRadioButtonMenuItem rdbtnmnitm1bots = new JRadioButtonMenuItem("1");
		choixNbBots.add(rdbtnmnitm1bots);
		mnNbBots.add(rdbtnmnitm1bots);
				/**/
		JRadioButtonMenuItem rdbtnmnitm2bots = new JRadioButtonMenuItem("2");
		choixNbBots.add(rdbtnmnitm2bots);
		mnNbBots.add(rdbtnmnitm2bots);
				/**/
		JRadioButtonMenuItem rdbtnmnitm3bots = new JRadioButtonMenuItem("3");
		choixNbBots.add(rdbtnmnitm3bots);
		mnNbBots.add(rdbtnmnitm3bots);
			/**/
		JMenu mnNbRealPlayers = new JMenu("Nombre de joueurs r\u00E9els");
		menuBar.add(mnNbRealPlayers);
		ButtonGroup choixNbPlayers = new ButtonGroup();
				/**/
		JRadioButtonMenuItem rdbtnmnitm0players_1 = new JRadioButtonMenuItem("0");
		choixNbPlayers.add(rdbtnmnitm0players_1);
		mnNbRealPlayers.add(rdbtnmnitm0players_1);
				/**/
		JRadioButtonMenuItem rdbtnmnitm1players_1 = new JRadioButtonMenuItem("1");
		choixNbPlayers.add(rdbtnmnitm1players_1);
		mnNbRealPlayers.add(rdbtnmnitm1players_1);
				/**/
		JRadioButtonMenuItem rdbtnmnitm2players_1 = new JRadioButtonMenuItem("2");
		choixNbPlayers.add(rdbtnmnitm2players_1);
		mnNbRealPlayers.add(rdbtnmnitm2players_1);
				/**/
		JRadioButtonMenuItem rdbtnmnitm3players_1 = new JRadioButtonMenuItem("3");
		choixNbPlayers.add(rdbtnmnitm3players_1);
		mnNbRealPlayers.add(rdbtnmnitm3players_1);
			/**/
		JButton btnPlay = new JButton("Lancer la partie");

		btnPlay.setBounds(25, 133, 146, 21);
		frame.getContentPane().add(btnPlay);

		JTextArea txtrBienvenueDansShape = new JTextArea();
		txtrBienvenueDansShape.setBackground(UIManager.getColor("Button.background"));
		txtrBienvenueDansShape.setText("Bienvenue dans Shape Up ! Veuillez param\u00E9trer votre partie et la lancer");
		txtrBienvenueDansShape.setBounds(25, 10, 572, 22);
		frame.getContentPane().add(txtrBienvenueDansShape);

		rdbtnmnitmRectangular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setChoixBoard(BoardType.RECTANGULAR);
			}
		});
		rdbtnmnitmTriangular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setChoixBoard(BoardType.TRIANGULAR);
			}
		});
		rdbtnmnitmClassic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setChoixGC(GameMode.CLASSIC);
			}
		});
		rdbtnmnitmChaos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setChoixGC(GameMode.CHAOS);
			}
		});
		rdbtnmnitmQuick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setChoixGC(GameMode.QUICK);
			}
		});

		rdbtnmnitmFacile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setChoixDif(Difficulty.EASY);
			}
		});

		rdbtnmnitmDifficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setChoixDif(Difficulty.HARD);
			}
		});
		rdbtnmnitm0bots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setNbBots(0);
			}
		});
		rdbtnmnitm1bots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setNbBots(1);
				rdbtnmnitm3players_1.setEnabled(false);
			}
		});
		rdbtnmnitm2bots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setNbBots(2);
				rdbtnmnitm3players_1.setEnabled(false);
				rdbtnmnitm2players_1.setEnabled(false);
			}
		});
		rdbtnmnitm3bots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setNbBots(3);
				rdbtnmnitm3players_1.setEnabled(false);
				rdbtnmnitm2players_1.setEnabled(false);
				rdbtnmnitm1players_1.setEnabled(false);
			}
		});

		rdbtnmnitm1players_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setNbPlayer(1);
			}
		});

		rdbtnmnitm2players_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setNbPlayer(2);
			}
		});
		rdbtnmnitm3players_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setNbPlayer(3);
			}
		});

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
