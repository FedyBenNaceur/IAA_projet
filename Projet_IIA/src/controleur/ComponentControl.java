package controleur;

import javafx.scene.input.MouseEvent;
import model.Component;
import vue.Controler;

public class ComponentControl {
	static Component current;
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
			current.updatePos(e.getX(), e.getY());
		}
		control.repaint();

	}

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

}
