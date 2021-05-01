package vue;

import java.io.File;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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
import javafx.scene.paint.Color;

/**
 * Class Controler : permet de g�rer les composantes de l'interface graphique
 * @author Fedy
 *
 */
public class Controler implements Initializable {
	@FXML
	private MenuItem importBtn; //Reference � la composante qui importe les images
	@FXML
	public Canvas canvas;//Reference � la composante qui permet de paindre les images
	@FXML
	private MenuItem rectChoice;//Reference � la composante qui permet de creer des rectangles
	@FXML
	private MenuItem lineChoice;// Reference � la composante qui peremet de creer des lignes 
	//Reference des images qui representent les Emojis
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
	private Button addTxt;//Reference de la composante qui permet d'ajouter du texte � l'image
	@FXML
	public ColorPicker colorP;//Permet de choisir les couleurs � utiliser pour les composante
	@FXML
	public TextField fontSize;//Permet de choisir la taille du texte � afficher
	@FXML
	public TextField height;//Permet de choisir la hauteur des composantes
	@FXML
	public TextField width;//Permet de choisir la largeur des composantes 
	@FXML
	public Button applyDim;//Refernce au bouton qui permet d'appliquer les changement aux composantes 
	@FXML
	public Button deleteComp;//Reference au bouton qui permet de supprimer des composantes
	@FXML
	public Menu filterMenu;//Reference au menu qui permet de creer les filtres
	@FXML
	public Button undoFilter;//Reference au bouton qui permet d'annuler un filtre 
	@FXML
	private MenuItem saveMenu;//Reference a la composante qui permet d'enregistrer une image

	private File img;

	private Image filteredImage;

	public Model model;

	public Component selected;//La composante s�lectionn�e

	public int selectedIndex;//L'index de la composante s�lectionn�e

	private ComponentControl cp;

	private LineCreator lp;

	private TextControler tp;
	
    //La liste des filtres disponibles
	public List<Filter> filters = Arrays.asList(new Filter("Invert", c -> c.invert()),
			new Filter("Grayscale", c -> c.grayscale()),
			new Filter("Black and White", c -> valueOf(c) < 1.5 ? Color.BLACK : Color.WHITE),
			new Filter("Red", c -> Color.color(1, c.getGreen(), c.getBlue())),
			new Filter("Green", c -> Color.color(c.getRed(), 1, c.getBlue())),
			new Filter("Blue", c -> Color.color(c.getRed(), c.getGreen(), 1)));

	public Controler() {
		this.model = new Model();
	}
	
	
    /**
     * M�thode qui permet d'importer une image et l'afficher
     * @param e
     * @throws IOException
     */
	@FXML
	private void importAction(ActionEvent e) throws IOException {
		//Initialiser un FileChooser pour choisir un fichier
		FileChooser fc = new FileChooser();
		//Definir les extensions � accepter lors de la selection
		fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File selectedFile = fc.showOpenDialog(null);
		if (selectedFile != null) {
			this.img = selectedFile;
			filteredImage = new Image(img.toURI().toString());
			//Modifier les dimensions de la canvas pour correspondre les dimensions de la photo
			this.canvas.setWidth(filteredImage.getWidth());
			this.canvas.setHeight(filteredImage.getHeight());
			//Afficher l'image
			repaint();
		} else {
			System.out.println("file not selected or invalid file chosen");
		}
	}
	
    /**
     * M�thode qui test si on a s�lectionn� une image
     * @return vrai si l'image est vide
     */
	public boolean isEmpty() {
		return this.img == null;
	}

	/**
	 * M�thode qui permet de tracer un rectangle
	 */
	@FXML
	public void createRect() {
		Component c = new Rectangle(0, 0, 50, 50);
		//Recuperer la couleur � utiliser
		c.setColor(colorP.getValue());
		//Ajouter le rectangle cr�e � la liste des composantes
		model.composantes.add(c);
		model.drawComponents(canvas.getGraphicsContext2D());
	}
	
   /*
    * M�thode qui permet de tracer un oval
    */
	@FXML
	public void createCircle() {
		Component c = new Ellipse(0, 0, 100, 50);
		//Recuperer la couleur � utiliser
		c.setColor(colorP.getValue());
		//Ajouter l'oval cr�e � la liste des composantes
		model.composantes.add(c);
		model.drawComponents(canvas.getGraphicsContext2D());
	}

	/*
	 * M�thode qui permet d'ajouter un emoji 
	 */
	public void createEmoji(String url) {
		Component c = new Emoji(100, 100, new Image(url));
		model.composantes.add(c);
		model.drawComponents(canvas.getGraphicsContext2D());
	}

	/*
	 * M�thode qui indique au controleur qu'on peut cr�er une ligne
	 */
	@FXML
	public void createLine() {
		lp.spawning = true;
	}
	
   /*
    * M�thode qui permet d'annuler les filtres
    */
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
	/*
	 * M�thode qui permet d'actualiser l'affichage
	 */
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

	/*
	 * M�thode qui permet de sauvegarder les images
	 * 	 */
	public void onSave() {
		try {
			Image snapshot = canvas.snapshot(null, null);
			Date d =new Date();
			ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("paint"+d.getTime()+".png"));
		   

		} catch (Exception e) {
			System.out.println("Failed to save");
		}

	}
	
    /*
     * M�thode qui initialise les controleurs 
     */
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
		this.undoFilter.setOnMouseClicked(e -> undoFilter());
		this.saveMenu.setOnAction(e -> onSave());
	}
    
	/*
	 * M�thode utiliser pour initialiser les controleurs
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initControls();
	}
   
	/*
	 * M�thode qui r�cupere le contenu texte
	 */
	public TextField getText() {
		return this.textF;
	}

	/*
	 * M�thode qui teste si une chaine de caractere est num�rique
	 */
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	/*
	 * Renvoie les trois valeurs standardiser rgb d'une couleur 
	 */
	private double valueOf(Color c) {
		return c.getRed() + c.getBlue() + c.getGreen();
	}

}
