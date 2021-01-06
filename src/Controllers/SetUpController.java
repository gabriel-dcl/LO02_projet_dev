package Controllers;

import Models.GameManager;
import Models.GameManagerChaos;
import Models.GameManagerClassic;
import Models.GameManagerQuick;
import Vues.graphiques.Bienvenue;
import enums.BoardType;
import enums.Difficulty;
import enums.GameMode;
//import jdk.jfr.internal.PrivateAccess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;
public class SetUpController {
	
	private GameManager gm;
	private Bienvenue vue;
	
	private GameMode choixGC;
	private Difficulty choixDif;
	private BoardType choixBoard;
	private int nbPlayer;
	private int nbBots ;

	public void setChoixGC(GameMode choixGC) {
		this.choixGC = choixGC;
	}

	public void setChoixDif(Difficulty choixDif) {
		this.choixDif = choixDif;
	}

	public void setChoixBoard(BoardType choixBoard) {
		this.choixBoard = choixBoard;
	}

	public void setNbPlayer(int nbPlayer) {
		this.nbPlayer = nbPlayer;
	}

	public void setNbBots(int nbBots) {
		this.nbBots = nbBots;
	}

	public SetUpController(Bienvenue vue) {
		super();
		
		this.vue = vue;
	}
	public void createGame() {
		
		
	}
	
	

}