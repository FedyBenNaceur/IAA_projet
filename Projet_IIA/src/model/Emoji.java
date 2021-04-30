package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Emoji extends Component {
	public double width = 51;
	public double height = 50;
	public Image icon;

	public Emoji(double x, double y, Image img) {
		icon = img;
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean inForm(double mouseX, double mouseY) {
		if (mouseX > x && mouseX < (x + width) && mouseY > y && mouseY < (y + height))
			return true;
		return false;
	}

	@Override
	void draw(GraphicsContext gc) {	
		gc.drawImage(icon, x, y, width, height);
		gc.save();
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
	public void setX(double x) {
		this.x = x;

	}

	@Override
	public void setY(double y) {
		this.y = y;

	}

	@Override
	public void setWidth(double w) {
		this.width = w;
	}

	@Override
	public void setHeight(double h) {
		this.height = h;
	}

	@Override
	public void updatePos(double mouseX, double mouseY) {
		this.setX(mouseX);
		this.setY(mouseY);
	}

}
