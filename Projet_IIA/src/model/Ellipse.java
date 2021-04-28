package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse extends Component {
	double width;
	double height;

	public Ellipse(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
	}

	@Override
	double getX() {
		return x;
	}

	@Override
	double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	@Override
	void draw(GraphicsContext c) {
		c.setFill(Color.RED);
		c.fillOval(x, y, width, height);
	}

	@Override
	public boolean inForm(double mouseX, double mouseY) {
		javafx.scene.shape.Ellipse e = new javafx.scene.shape.Ellipse(x, y, width, height);
		return e.contains(mouseX, mouseY);
	}

	@Override
	public void updatePos(double mouseX, double mouseY) {
		this.setX(mouseX);
		this.setY(mouseY);
	}

}
