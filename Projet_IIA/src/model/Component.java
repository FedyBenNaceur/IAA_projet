package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import vue.Controler;


/**
 *  Classe mère qui represente les composantes à ajouter sur la photo
 *  @author Fedy
 *
 */
public abstract class Component {
	Controler control ;//Le controleur FXML de l'application
	double x, y;//Les coordonnées de la composante
	Color c;//La couleur de la composante

	public abstract boolean inForm(double mouseX, double mouseY);

	abstract void draw(GraphicsContext c);

	abstract double getX();

	abstract double getY();

	public abstract void setX(double x);

	public abstract void setY(double y);
	
	public abstract void setWidth(double w);
	
	public abstract void setHeight(double h);
	
	public abstract void updatePos(double mouseX,double mouseY);
	
	//Méthode qui affecte la couleur de la composante
	public void setColor(Color c) {
		this.c = c ;
	}

}

