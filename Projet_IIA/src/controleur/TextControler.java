package controleur;

import java.awt.Point;
import java.util.ArrayList;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import vue.Controler;

public class TextControler {
	TextField text;
	Controler c;
	ArrayList<String> texts = new ArrayList<String>();
	ArrayList<Point> positions = new ArrayList<Point>();
	ArrayList<Font> fonts = new ArrayList<Font>();
	public boolean readyToShow = false;
	Color currentColor;
	public ArrayList<Color> colors = new ArrayList<Color>();

	public TextControler(Controler c) {
		this.c = c;
	}

	public void createText(MouseEvent e, Color co) {
		if (readyToShow) {
			text = c.getText();
			String f = c.fontSize.getText();
			if(Controler.isNumeric(f) && f!="") {
				fonts.add(new Font(Integer.parseInt(f)));
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Error");
		
		        alert.setContentText("You entered an ivalid font size ,we will use the defeult value!");
		 
		        alert.showAndWait();
		        
		        fonts.add(new Font(15));
			}

			String txt = text.getText();
			GraphicsContext gc = c.canvas.getGraphicsContext2D();
			texts.add(txt);
			positions.add(new Point((int) e.getX(), (int) e.getY()));
			colors.add(co);
			printText(gc);
			readyToShow = false;
		}
	}

	public void setReady() {
		readyToShow = true;
	}

	public void printText(GraphicsContext gc) {
		for (int i = 0; i < texts.size(); i++) {
			gc.setFill(colors.get(i));
			gc.setFont(fonts.get(i));
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setTextBaseline(VPos.CENTER);
			gc.fillText(texts.get(i), positions.get(i).x, positions.get(i).y);

		}
	}

	
}
