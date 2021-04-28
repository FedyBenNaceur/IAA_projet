package model;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Rectangle extends Component {
	double width;
	double height;

	public Rectangle(double x, double y ,double w, double h){
		width = w ; 
		height = h ;
		this.x = x ; 
		this.y = y ;
	}

	@Override
	double getX() {
		return this.x;
	}

	@Override
	double getY() {
		return this.y;
	}

	@Override
	void draw(GraphicsContext c) {
		c.setFill(Color.FORESTGREEN);
		c.fillRect(x, y, width, height);
	}

	@Override
	public boolean inForm(double mouseX, double mouseY) {
		if (mouseX > x && mouseX < (x + width) && mouseY > y && mouseY < (y + height))
			return true;
		return false;
	}

	@Override
	public
	void setX(double x) {
		this.x = x;

	}

	@Override
	public void setY(double y) {
		this.y = y;

	}

	@Override
	public void updatePos(double mouseX, double mouseY) {
		this.setX(mouseX);
		this.setY(mouseY);	
	}

}
