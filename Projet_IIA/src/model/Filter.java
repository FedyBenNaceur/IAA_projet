package model;

import java.util.function.Function;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Filter implements Function<Image, Image> {
	public String name;
	Function<Color, Color> colorMap;

	public Filter(String name, Function<Color, Color> cm) {
		this.name = name;
		this.colorMap = cm;
	}

	@Override
	public Image apply(Image source) {
		int w = (int)source.getWidth();
		int h = (int)source.getHeight();
		WritableImage image = new WritableImage(w,h);
		for (int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				Color c1 = source.getPixelReader().getColor(j,i);
				Color c2 = colorMap.apply(c1);
				
				image.getPixelWriter().setColor(j, i, c2);
			}
		}
		
		
		return image;
	}

}
