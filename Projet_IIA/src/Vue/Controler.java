package Vue;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

import Model.Component;
import Model.Model;
import Model.Polygone;
import javafx.scene.image.Image;

public class Controler {
	@FXML
	private MenuItem importBtn;
	@FXML
	private ImageView imageV;
	@FXML
	private Button rotateB;
	@FXML
	private Canvas canvas;
	@FXML
	private MenuItem filterChoice;
	@FXML
	private MenuItem rectChoice;

	private Image img;

	private Model model;

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
		    imageV.setImage(image);
			/*GraphicsContext gc = canvas.getGraphicsContext2D();
			this.img = image;
			gc.drawImage(image, 0, 0);
			gc.save();
			*/

		} else {
			System.out.println("file not selected or invalid file chosen");
		}
	}

	public boolean isEmpty() {
		return this.img == null || this.img.isError();
	}

	@FXML
	public void rotate() {
		if (!isEmpty()) {
			imageV.setRotate(imageV.getRotate() + 10);
		}
	}

	@FXML
	public void createRect() {
		Component c = new Polygone(0, 0, 100, 100);
		model.composantes.add(c);
		model.drawComponents(canvas.getGraphicsContext2D());
		System.out.println("Rectangle created");

	}

}
