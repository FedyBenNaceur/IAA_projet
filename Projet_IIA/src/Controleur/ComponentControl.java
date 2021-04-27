package Controleur;

import Model.Component;
import Vue.Controler;

import javafx.scene.input.MouseEvent;

public class ComponentControl {
	static Component current;
	boolean clicked = false;
	static boolean grabbed = false;
	Controler control;

	public ComponentControl(Controler co) {
		this.control = co;
	}

	public void deposer() {
		grabbed = false;
		current = null;
		control.repaint();
	}

	public void deplacer(MouseEvent e) {
		if (grabbed && current != null) {
			current.setX(e.getX());
			current.setY(e.getY());
		}
		control.repaint();

	}

	public void attraper(MouseEvent e) {
		if (!grabbed) {
			for (Component f : control.model.composantes) {
				if (f.inForm(e.getX(), e.getSceneY())) {
					grabbed = true;
					current = f;
					System.out.print("noice");
				}
			}
		}
		control.repaint();

	}

}
