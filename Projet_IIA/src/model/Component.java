package model;

import javafx.scene.canvas.GraphicsContext;

import vue.Controler;

public abstract class Component {
	Controler control ;
	double x, y;

	public abstract boolean inForm(double mouseX, double mouseY);

	abstract void draw(GraphicsContext c);

	abstract double getX();

	abstract double getY();

	public abstract void setX(double x);

	public abstract void setY(double y);
	
	public abstract void updatePos(double mouseX,double mouseY);
}

