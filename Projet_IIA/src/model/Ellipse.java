package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Classe qui represente l'oval
 * @author Fedy
 *
 */
public class Ellipse extends Component {
	double width;//La largeur de l'ellipse
	double height;//la hauteur de l'ellipse
    
	//Le constructeur de la classe 
	public Ellipse(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
	}

	/**
	 * Getteur de l'abscisse de l'oval
	 */
	@Override
	double getX() {
		return x;
	}

	/**
	 * Getteur de l'ordonnee de l'oval
	 */
	@Override
	double getY() {
		return y;
	}

	/**
	 * Setteur de l'ordonnee de l'oval
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Setteur de l'abscisse de l'oval
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Setteur de la largeur
	 */
	public void setWidth(double w) {
		this.width = w; 
	}
	
	/**
	 * Setteur de la Hauteur
	 */
	public void setHeight(double h) {
		this.height = h ;
	}

    /**
     * Méthode qui dessine un oval 
     */
	@Override
	void draw(GraphicsContext c) {
		c.setFill(this.c);
		c.fillOval(x, y, width, height);
	}

	/**
	 * Teste si le clic de la souris est sur l'oval
	 */
	@Override
	public boolean inForm(double mouseX, double mouseY) {
		javafx.scene.shape.Ellipse e = new javafx.scene.shape.Ellipse(x, y, width, height);
		return e.contains(mouseX, mouseY);
	}

	/**
	 * Methode qui permet de mettre a jour la position 
	 */
	@Override
	public void updatePos(double mouseX, double mouseY) {
		this.setX(mouseX);
		this.setY(mouseY);
	}

}
