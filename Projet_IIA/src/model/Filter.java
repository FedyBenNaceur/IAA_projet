package model;

import java.util.function.Function;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Classe qui represente un filtre
 * @author Fedy
 *
 */
public class Filter implements Function<Image, Image> {
	public String name;// le nom du filtre
	Function<Color, Color> colorMap;//La fonction qui definit un filtre 

	//Constructeur de la classe
	public Filter(String name, Function<Color, Color> cm) {
		this.name = name;
		this.colorMap = cm;
	}

	/**
	 * Méthode qui applique un filtre à l'image
	 */
	@Override
	public Image apply(Image source) {
		//Recuperer les dimensions de l'image
		int w = (int)source.getWidth();
		int h = (int)source.getHeight();
		WritableImage image = new WritableImage(w,h);
		//Parcourir l'image pixel par pixel
		for (int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				//Recuperer la couleur de chaque pixek
				Color c1 = source.getPixelReader().getColor(j,i);
				//Recupere la nouvelle couleur
				Color c2 = colorMap.apply(c1);
				//Modifier la valeur du pixel
				image.getPixelWriter().setColor(j, i, c2);
			}
		}	
		return image;
	}

}
