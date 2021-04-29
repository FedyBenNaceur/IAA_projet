package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import vue.Controler;

public abstract class Component {
	Controler control ;
	double x, y;
	Color c ; 

	public abstract boolean inForm(double mouseX, double mouseY);

	abstract void draw(GraphicsContext c);

	abstract double getX();

	abstract double getY();

	public abstract void setX(double x);

	public abstract void setY(double y);
	
	public abstract void setWidth(double w);
	
	public abstract void setHeight(double h);
	
	public abstract void updatePos(double mouseX,double mouseY);
	
	public void setColor(Color c) {
		this.c = c ;
	}

}

