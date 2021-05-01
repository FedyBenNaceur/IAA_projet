package model;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/***
 * Classe qui represente les rectangles
 * @author Fedy
 *
 */
public class Rectangle extends Component {
	double width;
	double height;

	//Le constructeur de la classe 
	public Rectangle(double x, double y ,double w, double h){
		width = w ; 
		height = h ;
		this.x = x ; 
		this.y = y ;
	}

	/**
	 * Getteur de l'abscisse de l'oval
	 */
	@Override
	double getX() {
		return this.x;
	}

	/**
	 * Getteur de l'ordonnee de l'oval
	 */
	@Override
	double getY() {
		return this.y;
	}

	/**
	 * Methode qui dessine un rectangle 
	 */
	@Override
	void draw(GraphicsContext c) {
		c.setFill(this.c);
		c.fillRect(x, y, width, height);
	}

	/**
	 * Teste si le clic de la souris est sur le rectangle
	 */
	@Override
	public boolean inForm(double mouseX, double mouseY) {
		if (mouseX > x && mouseX < (x + width) && mouseY > y && mouseY < (y + height))
			return true;
		return false;
	}

	/**
	 * Setteur de l'abscisse du rectangel
	 */
	@Override
	public	void setX(double x) {
		this.x = x;

	}

	/**
	 * Setteur de l'ordonnee de l'oval
	 */
	@Override
	public void setY(double y) {
		this.y = y;

	}
	
	/**
	 * Setteur de la largeur
	 */
	@Override
	public void setWidth(double w) {
		this.width = w; 
	}
	
	/**
	 * Setteur de la Hauteur
	 */
	@Override
	public void setHeight(double h) {
		this.height = h ;
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
