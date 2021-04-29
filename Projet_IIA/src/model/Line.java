package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Component {
	public double x2;
	public double y2;

	public boolean readyToDraw = false;

	public Line(double x, double y, double x2, double y2) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
	}

	public Line() {

	}

	@Override
	public boolean inForm(double mouseX, double mouseY) {
		javafx.scene.shape.Line l = new javafx.scene.shape.Line(x, y, x2, y2);
		return l.contains(mouseX, mouseY);
	}

	@Override
	void draw(GraphicsContext c) {
		if (readyToDraw) {
			c.setFill(this.c);
			c.strokeLine(x, y, x2, y2);
		}
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setX(double x) {
		this.x = x;

	}

	@Override
	public void setY(double y) {
		this.y = y;
	}
	
	public void setX2(double x2) {
		this.x2 = x2 ;
	}
	
	public void setY2(double y2) {
		this.y2 = y2 ;
	}

	@Override
	public void updatePos(double mouseX, double mouseY) {
		
	}

}
