package controleur;

import javafx.scene.input.MouseEvent;
import model.Line;
import vue.Controler;

/**
 * Classe qui represente le controleur de la ligne 
 * @author Fedy
 *
 */

public class LineCreator {
	public boolean spawning = false;//Vaut vrai si l'utilisateur a choisi de creer une ligne 
	static boolean setFst = false;// Vaut vrai vrai si l'utilisateur a choisi le premier point de la ligne
	private Line creatL;//La ligne qu'on est en train de creer
	Controler control;
    
	/**
	 * Constructeur de la classe 
	 * @param c
	 */
	public LineCreator(Controler c) {
		this.control = c;
	}

	/**
	 * Méthode qui cree une ligne
	 * @param e
	 */
	public void spawnLine(MouseEvent e) {
		if (spawning) {
			//Placer le premier point
			spawning = false;
			creatL = new Line();
			//Récuperer les coordonnees du premier point a partir du clic de la souris
			creatL.setX(e.getX());
			creatL.setY(e.getY());
			//Récuprer la couleur
			creatL.setColor(control.colorP.getValue());
			setFst = true;
		} else {
			//Si le premier point a ete creer on cree le deuxieme
			if (setFst) {
				//Recuperer les coordonnees du deuxieme point
				creatL.setX2(e.getX());
				creatL.setY2(e.getY());
				creatL.readyToDraw = true;
				control.model.composantes.add(creatL);
				creatL = null;
				setFst = false;
				control.repaint();
			}
		}
	}
}
