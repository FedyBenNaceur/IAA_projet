package controleur;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import model.Component;
import vue.Controler;

/**
 * Classe qui represente le controleur des composantes
 * 
 * @author Fedy
 *
 */
public class ComponentControl {
	static Component current;
	static boolean grabbed = false;
	Controler control;

	/**
	 * Constructeur de la classe
	 * 
	 * @param co
	 */
	public ComponentControl(Controler co) {
		this.control = co;
	}

	/**
	 * Méthode qui permet de déposer une composante
	 */
	public void deposer() {
		grabbed = false;
		current = null;
		control.repaint();
	}

	/**
	 * Méthode qui deplace une composante en suivant les mouvement de la souris
	 * 
	 * @param e
	 */
	public void deplacer(MouseEvent e) {
		if (grabbed && current != null) {
			current.updatePos(e.getX(), e.getY());
		}
		control.repaint();

	}

	/**
	 * Méthode qui permet d'attraper une composante
	 * 
	 * @param e
	 */
	public void attraper(MouseEvent e) {
		if (!grabbed) {
			for (Component f : control.model.composantes) {
				if (f.inForm(e.getX(), e.getY())) {
					grabbed = true;
					current = f;
				}
			}
		}
		control.repaint();

	}

	/**
	 * Méthode qui sélectionne une composante
	 * 
	 * @param e
	 */
	public void select(MouseEvent e) {
		int i = 0;
		for (Component f : control.model.composantes) {
			if (f.inForm(e.getX(), e.getY())) {
				control.selected = f;
				control.selectedIndex = i;
			}
			i++;
		}
	}

	/**
	 * Méthde qui applique les nouvelles dimensions à une composante
	 */
	public void applyDimChanges() {
		if (control.selected != null) {
			// Recuperer les nouvelles dimensions des textField
			String h = control.height.getText();
			String w = control.width.getText();
			// Teste si les valeurs sont numeriques et valide
			if (Controler.isNumeric(w) && Controler.isNumeric(h)) {
				control.selected.setWidth(Integer.parseInt(w));
				control.selected.setHeight(Integer.parseInt(h));
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");

				alert.setContentText("You entered an ivalid value for width or height, no changes will be applied!");

				alert.showAndWait();
			}
			control.selected = null;
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");

			alert.setContentText("You didn't select!");

			alert.showAndWait();
		}
		control.repaint();
	}
    
	/**
	 * Méthode qui supprime une composante 
	 */
	public void delete() {
		if (control.selected != null) {
			control.model.composantes.remove(control.selectedIndex);

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");

			alert.setContentText("You didn't select!");

			alert.showAndWait();
		}
		control.repaint();
	}

}
