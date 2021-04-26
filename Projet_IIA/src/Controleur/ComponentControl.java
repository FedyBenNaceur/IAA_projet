package Controleur;

import Model.Component;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class ComponentControl implements EventHandler<MouseEvent>{
	Component c;
	GraphicsContext gc ;
	boolean clicked ; 

	ComponentControl(Component c,GraphicsContext gc) {
		this.c = c;
		this.gc = gc ; 
	}

	@Override
	public void handle(MouseEvent e) {
		double x = e.getX() ; 
		double y = e.getY();
	}
}
