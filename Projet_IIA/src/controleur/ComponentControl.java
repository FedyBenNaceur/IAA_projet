package controleur;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

	public void select(MouseEvent e) {
		int i = 0 ;
		for (Component f : control.model.composantes) {
			if (f.inForm(e.getX(), e.getY())) {
				control.selected = f;
				control.selectedIndex=i; 
			}
			i++;
		}
	}

	public void applyDimChanges() {
		if (control.selected != null) {
			String h = control.height.getText();
			String w = control.width.getText();
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
