package vue;

import java.io.File;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.MenuItem;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Component;
import model.Ellipse;
import model.Line;
import model.Model;
import model.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controleur.ComponentControl;
import controleur.LineCreator;
import javafx.scene.image.Image;

public class Controler implements Initializable {
	@FXML
	private MenuItem importBtn;
	@FXML
	private Canvas canvas;
	@FXML
	private MenuItem filterChoice;
	@FXML
	private MenuItem rectChoice;
	@FXML
	private MenuItem lineChoice;

	private File img;

	public Model model;

	private ComponentControl cp;
	
	private LineCreator lp ;

	public Controler() {
		this.model = new Model();
	}

	@FXML
	private void importAction(ActionEvent e) throws IOException {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File selectedFile = fc.showOpenDialog(null);
		if (selectedFile != null) {
			this.img = selectedFile;
			repaint();
		} else {
			System.out.println("file not selected or invalid file chosen");
		}
	}

	public boolean isEmpty() {
		return this.img == null;
	}

	@FXML
	public void createRect() {
		Component c = new Rectangle(0, 0, 50, 50);
		model.composantes.add(c);
		model.drawComponents(canvas.getGraphicsContext2D());
	}

	@FXML
	public void createCircle() {
		Component c = new Ellipse(0, 0, 100, 50);
		model.composantes.add(c);
		model.drawComponents(canvas.getGraphicsContext2D());
	}

	@FXML
	public void createLine() {
		lp.spawning = true;
	}

	public void repaint() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		if (img != null) {
			Image image = new Image(img.toURI().toString());
			gc.drawImage(image, 0, 0);
			gc.save();
		}
		model.drawComponents(gc);

	}

	public void initControls() {
		cp = new ComponentControl(this);
		lp = new LineCreator(this);
		this.canvas.setOnMousePressed(Event -> {
			cp.attraper(Event);
		});
		this.canvas.setOnMouseDragged(Event -> {
			cp.deplacer(Event);
		});
		this.canvas.setOnMouseReleased(Event -> {
			cp.deposer();
		});
		this.canvas.setOnMouseClicked(Event -> {
			lp.spawnLine(Event);
		});

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initControls();
	}

}
