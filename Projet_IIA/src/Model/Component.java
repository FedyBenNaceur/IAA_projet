package Model;

import javafx.scene.canvas.GraphicsContext;

public abstract class Component {
	int x;
	int y;
	int height;
	int width;

	public Component(int x, int y, int height, int width) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void draw(GraphicsContext gc);

	public abstract void setX(int x);

	public abstract void setY(int y);

	public abstract void getX(int x);

	public abstract void getY(int y);

}
