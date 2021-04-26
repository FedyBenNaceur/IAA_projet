package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Polygone extends Component {

	public Polygone(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void draw(GraphicsContext c) {
		c.setFill(Color.FORESTGREEN);
		c.fillRect(x, y, width, height);
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getX(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getY(int y) {
		// TODO Auto-generated method stub

	}

}
