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


/**
 * Classe qui represente le controleur pour creer les textes
 * @author Fedy
 *
 */
public class TextControler {
	TextField text;//Le texte qu'on est en train de créer
	Controler c;
	ArrayList<String> texts = new ArrayList<String>();//la liste de textes crées
	ArrayList<Point> positions = new ArrayList<Point>();//la positions de textes crées
	ArrayList<Font> fonts = new ArrayList<Font>();//la taille de texte créer
	public boolean readyToShow = false;//Vaut vrai si le texte est pret a etre afficher
	Color currentColor;
	public ArrayList<Color> colors = new ArrayList<Color>();//Les couleurs des textes crées
	private final int defaultFont = 15 ;

	
	/**
	 * Constructeur de la classe
	 * @param c
	 */
	public TextControler(Controler c) {
		this.c = c;
	}

	
	/**
	 * Méthode qui cree un texte
	 * @param e
	 * @param co
	 */
	public void createText(MouseEvent e, Color co) {
		if (readyToShow) {
			//On recupere le texte
			text = c.getText();
			String f = c.fontSize.getText();
			//teste si le champ pour la taille du texte est valide s'il ne l'est pas on utilise la taille par défaut
			if(Controler.isNumeric(f) && f!="") {
				fonts.add(new Font(Integer.parseInt(f)));
			}else {
				//Afficher un message d'erreur pour dire que la taille choisie n'est pas valide
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Error");
		
		        alert.setContentText("You entered an ivalid font size ,we will use the defeult value!");
		 
		        alert.showAndWait();
		        
		        fonts.add(new Font(defaultFont));
			}
            //Ajouter le texte créer
			String txt = text.getText();
			GraphicsContext gc = c.canvas.getGraphicsContext2D();
			texts.add(txt);
			positions.add(new Point((int) e.getX(), (int) e.getY()));
			colors.add(co);
			printText(gc);
			readyToShow = false;
		}
	}

	
	/**
	 * Méthode qui indique que le texte est pret a etre afficher
	 */
	public void setReady() {
		readyToShow = true;
	}
	
    /**
     * Afficher les textes crées
     * @param gc
     */
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
