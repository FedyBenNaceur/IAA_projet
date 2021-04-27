package Vue;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controleur.ComponentControl;
import Model.Component;
import Model.Model;
import Model.Polygone;
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

	private Image img;

	public Model model;

	public Controler() {
		this.model = new Model();	
	}

	@FXML
	private void importAction(ActionEvent e) throws IOException {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File selectedFile = fc.showOpenDialog(null);
		if (selectedFile != null) {
			Image image = new Image(selectedFile.toURI().toString());
			GraphicsContext gc = canvas.getGraphicsContext2D();
			this.img = image;
			gc.drawImage(image, 0, 0);
			gc.save();
		} else {
			System.out.println("file not selected or invalid file chosen");
		}
	}

	public boolean isEmpty() {
		return this.img == null || this.img.isError();
	}

	@FXML
	public void createRect() {
		Component c = new Polygone(0, 0,50, 50);
		model.composantes.add(c);

	
		model.drawComponents(canvas.getGraphicsContext2D());
		System.out.println("Rectangle created");
	}

	public void repaint() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		model.drawComponents(gc);

	}
	
	public void initControls() {
		ComponentControl cp = new ComponentControl(this);
		this.canvas.setOnMousePressed(Event -> {
			cp.attraper(Event);
		});
		this.canvas.setOnMouseDragged(Event -> {
			cp.deplacer(Event);
		});
		this.canvas.setOnMouseReleased(Event -> {
			cp.deposer();
		});
			
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initControls();	
	}

}
