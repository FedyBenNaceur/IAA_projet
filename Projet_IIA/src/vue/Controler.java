package vue;

import java.io.File;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Component;
import model.Ellipse;
import model.Emoji;
import model.Model;
import model.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controleur.ComponentControl;
import controleur.LineCreator;
import controleur.TextControler;
import javafx.scene.image.Image;


public class Controler implements Initializable {
	@FXML
	private MenuItem importBtn;
	@FXML
	public Canvas canvas;
	@FXML
	private MenuItem filterChoice;
	@FXML
	private MenuItem rectChoice;
	@FXML
	private MenuItem lineChoice;
	@FXML
	private MenuItem emoji1 ;
	@FXML
	private MenuItem emoji2 ;
	@FXML
	private MenuItem emoji3 ;
	@FXML
	private MenuItem emoji4 ;
	@FXML
	private MenuItem emoji5 ;
	@FXML
	private MenuItem emoji6 ;
	@FXML
	private TextField textF ;	 
	@FXML
	private Button addTxt;
	@FXML 
	public ColorPicker colorP;
	@FXML
	public TextField fontSize;
	@FXML
	public TextField height ;
	@FXML
	public TextField width ;
	@FXML 
	public Button applyDim ;
	@FXML 
	public Button deleteComp ;
	

	private File img;

	public Model model;
	
	public Component selected;
	
	public int selectedIndex ;

	private ComponentControl cp;
	
	private LineCreator lp ;
	
	private TextControler tp ;

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
		Component c = new Emoji(100,100,new Image(url));
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
		tp.printText(gc);

	}

	public void initControls() {
		cp = new ComponentControl(this);
		lp = new LineCreator(this);
		tp = new TextControler(this);
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
			tp.createText(Event,colorP.getValue());
			cp.select(Event);
		});
		this.addTxt.setOnMouseClicked(Event -> {
			tp.setReady();
		});
		this.applyDim.setOnMouseClicked(Event -> {
			cp.applyDimChanges();
		});
		this.deleteComp.setOnMouseClicked(Event->{
			cp.delete();
		});
		this.emoji1.setOnAction(Event->{
			createEmoji("emoji1.png");
		});
		this.emoji2.setOnAction(Event->{
			createEmoji("boss.png");
		});
		this.emoji3.setOnAction(Event->{
			createEmoji("heart.png");
		});
		this.emoji4.setOnAction(Event->{
			createEmoji("smile.png");
		});
		this.emoji5.setOnAction(Event->{
			createEmoji("angel.png");
		});
		this.emoji6.setOnAction(Event->{
			createEmoji("poop.png");
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initControls();
	}
	
    public TextField getText() {
		return this.textF ;
	}
    
    public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); 
	}

}
