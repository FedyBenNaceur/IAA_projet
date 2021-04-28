package model;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class Model {
	public ArrayList<Component> composantes;
	
	public Model(){
		composantes = new ArrayList<Component>();
	}
	
	public void drawComponents (GraphicsContext gc) {
		for (Component c : composantes) {
			c.draw(gc);
		}
	}

}
