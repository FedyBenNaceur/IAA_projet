package vue;

import java.awt.image.BufferedImage;
import java.io.File;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Component;
import model.Ellipse;
import model.Emoji;
import model.Filter;
import model.Model;
import model.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import controleur.ComponentControl;
import controleur.LineCreator;
import controleur.TextControler;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;

public class Controler implements Initializable {
	@FXML
	private MenuItem importBtn;
	@FXML
	public Canvas canvas;
	@FXML
	private MenuItem rectChoice;
	@FXML
	private MenuItem lineChoice;
	@FXML
	private MenuItem emoji1;
	@FXML
	private MenuItem emoji2;
	@FXML
	private MenuItem emoji3;
	@FXML
	private MenuItem emoji4;
	@FXML
	private MenuItem emoji5;
	@FXML
	private MenuItem emoji6;
	@FXML
	private TextField textF;
	@FXML
	private Button addTxt;
	@FXML
	public ColorPicker colorP;
	@FXML
	public TextField fontSize;
	@FXML
	public TextField height;
	@FXML
	public TextField width;
	@FXML
	public Button applyDim;
	@FXML
	public Button deleteComp;
	@FXML
	public Menu filterMenu;
	@FXML
	public Button undoFilter ;
	@FXML
	private MenuItem saveMenu ; 

	private File img;

	private Image filteredImage;

	public Model model;

	public Component selected;

	public List<Filter> filters = Arrays.asList(
			new Filter("Invert",c->c.invert()), 
			new Filter("Grayscale",c->c.grayscale()),
			new Filter("Black and White",c ->valueOf(c)<1.5?Color.BLACK:Color.WHITE ),
			new Filter("Red",c->Color.color(1, c.getGreen(), c.getBlue())),
			new Filter("Green",c->Color.color(c.getRed(), 1, c.getBlue())),
			new Filter("Blue",c->Color.color(c.getRed(), c.getGreen(),1)));

	public int selectedIndex;

	private ComponentControl cp;

	private LineCreator lp;

	private TextControler tp;

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
			filteredImage = new Image(img.toURI().toString());
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
		c.setColor(colorP.getValue());
		model.composantes.add(c);
		model.drawComponents(canvas.getGraphicsContext2D());
	}

	@FXML
	public void createCircle() {
		Component c = new Ellipse(0, 0, 100, 50);
		c.setColor(colorP.getValue());
		model.composantes.add(c);
		model.drawComponents(canvas.getGraphicsContext2D());
	}

	public void createEmoji(String url) {
		Component c = new Emoji(100, 100, new Image(url));
		model.composantes.add(c);
		model.drawComponents(canvas.getGraphicsContext2D());
	}

	@FXML
	public void createLine() {
		lp.spawning = true;
	}

	@FXML
	public void undoFilter() {
		if (img != null) {
			filteredImage = new Image(img.toURI().toString());
			repaint();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");

			alert.setContentText("No image imported");

			alert.showAndWait();
		}
	}

	public void repaint() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		if (img != null) {
			gc.drawImage(filteredImage, 0, 0);
			gc.save();
		}
		model.drawComponents(gc);
		tp.printText(gc);

	}
	
	public void onSave() {
		try {
			
			  SnapshotParameters parameters = new SnapshotParameters();
              WritableImage wi = new WritableImage((int) canvas.getWidth(),(int)canvas.getHeight());
       

              File output = new File("snapshot" + new Date().getTime() + ".png");
              ImageIO.write(SwingFXUtils.fromFXImage(wi, null), "png", output);
			
		}catch(Exception e ) {
			System.out.println("Failed to save");
		}
		
	}

	public void initControls() {
		cp = new ComponentControl(this);
		lp = new LineCreator(this);
		tp = new TextControler(this);
		filters.forEach(filter -> {
			MenuItem mi = new MenuItem(filter.name);
			mi.setOnAction(e -> {
				if (filteredImage != null) {
					filteredImage = filter.apply(filteredImage);
					repaint();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error");

					alert.setContentText("No image imported");

					alert.showAndWait();
				}
			});
			filterMenu.getItems().add(mi);
		});
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
			tp.createText(Event, colorP.getValue());
			cp.select(Event);
		});
		this.addTxt.setOnMouseClicked(Event -> {
			tp.setReady();
		});
		this.applyDim.setOnMouseClicked(Event -> {
			cp.applyDimChanges();
		});
		this.deleteComp.setOnMouseClicked(Event -> {
			cp.delete();
		});
		this.emoji1.setOnAction(Event -> {
			createEmoji("emoji1.png");
		});
		this.emoji2.setOnAction(Event -> {
			createEmoji("boss.png");
		});
		this.emoji3.setOnAction(Event -> {
			createEmoji("heart.png");
		});
		this.emoji4.setOnAction(Event -> {
			createEmoji("smile.png");
		});
		this.emoji5.setOnAction(Event -> {
			createEmoji("angel.png");
		});
		this.emoji6.setOnAction(Event -> {
			createEmoji("poop.png");
		});
		this.undoFilter.setOnMouseClicked(e->undoFilter());
		this.saveMenu.setOnAction(e->onSave());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initControls();
	}

	public TextField getText() {
		return this.textF;
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}
	
	private double valueOf(Color c) {
		return c.getRed()+c.getBlue()+c.getGreen();
	}

}
