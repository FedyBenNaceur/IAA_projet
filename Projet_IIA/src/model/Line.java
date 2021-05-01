package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Component {
	public double x2;//les coordonnées du deuxième point de la ligne
	public double y2;

	public boolean readyToDraw = false;

	/**
	 * Constructeur de la classe Line
	 * @param x abscisse du premier point
	 * @param y ordonnée du premier point
	 * @param x2 abscisse du deuxieme point
	 * @param y2 ordonnée du deuxieme point
	 */
	public Line(double x, double y, double x2, double y2) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
	}

	public Line() {

	}

	/**
	 * Méthode qui teste si un click de souris est sur la ligne
	 */
	@Override
	public boolean inForm(double mouseX, double mouseY) {
		javafx.scene.shape.Line l = new javafx.scene.shape.Line(x, y, x2, y2);
		return l.contains(mouseX, mouseY);
	}
 
	/**
	 * Méthode qui dessine un ligne 
	 */
	@Override
	void draw(GraphicsContext c) {
		if (readyToDraw) {
			c.setFill(this.c);
			c.strokeLine(x, y, x2, y2);
		}
	}

	
	/**
	 * Getteur de l'abscisse du premier point
	 */
	@Override
	public double getX() {
		return x;
	}

	/**
	 * Getteur de l'ordonée du premier point
	 */
	@Override
	public double getY() {
		return y;
	}

	/**
	 * Setteur de l'abscisse du premier point 
	 */
	@Override
	public void setX(double x) {
		this.x = x;

	}

	/**
	 * Setteur de l'ordonnee du premier point
	 */
	@Override
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Setteur de l'abscisse du premier point 
	 */
	public void setX2(double x2) {
		this.x2 = x2 ;
	}
	
	/**
	 * Setteur de l'ordonnee du premier point
	 */
	public void setY2(double y2) {
		this.y2 = y2 ;
	}

	@Override
	public void updatePos(double mouseX, double mouseY) {
		
	}
    
	
	@Override
	public void setWidth(double w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight(double h) {
		// TODO Auto-generated method stub
		
	}

}
