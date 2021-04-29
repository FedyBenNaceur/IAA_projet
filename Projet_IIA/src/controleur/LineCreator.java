package controleur;

import javafx.scene.input.MouseEvent;
import model.Line;
import vue.Controler;

public class LineCreator {
	public boolean spawning = false;
	static boolean setFst = false;
	private Line creatL;
	Controler control;

	public LineCreator(Controler c) {
		this.control = c;
	}

	public void spawnLine(MouseEvent e) {
		if (spawning) {
			spawning = false;
			creatL = new Line();
			creatL.setX(e.getX());
			creatL.setY(e.getY());
			creatL.setColor(control.colorP.getValue());
			setFst = true;
		} else {
			if (setFst) {
				creatL.setX2(e.getX());
				creatL.setY2(e.getY());
				creatL.readyToDraw = true;
				control.model.composantes.add(creatL);
				System.out.println(creatL.getX() + ":" + creatL.getY() + ":" + creatL.x2 + ":" + creatL.y2);
				creatL = null;
				setFst = false;
				control.repaint();
			}
		}
	}
}
