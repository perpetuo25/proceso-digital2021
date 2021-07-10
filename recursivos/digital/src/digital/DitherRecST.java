package digital;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class DitherRecST {
    
    public Image scale(Image source, int w, int h) {
	ImageView imageView = new ImageView(source);
	imageView.setFitWidth(w);
	imageView.setFitHeight(h);
	return imageView.snapshot(null, null);
    }
    
    public Image recursivocolor(Image imagen, int areaw, int areah){
        Filtros filt = new Filtros();
        Image reducida = scale(imagen,areaw,areah);
        PixelReader pr = imagen.getPixelReader();
        WritableImage wi = new WritableImage((int)imagen.getWidth(), (int)imagen.getHeight());
        PixelWriter pw = wi.getPixelWriter();
        
        
        for(int i = 0; i < imagen.getWidth(); i = i + areaw){
            for (int j = 0; j < imagen.getHeight(); j = j + areah){
                double r = 0;
                double g = 0;
                double b = 0;
                for(int m = 0; m < areaw; m++){
		    for(int n = 0; n < areah; n++){
			if (i+m < imagen.getWidth() && j+n < imagen.getHeight() ) {
			    Color col = pr.getColor(i+m,j+n);    
			    double re = col.getRed();
			    r+= re;
			    double gr = col.getGreen();
			    g+= gr; 
			    double bl = col.getBlue();
			    b+= bl;
			}
		    }
                }
                
                int promedior = (int) ((r/(areaw*areah))*255);
                int promediog = (int) ((g/(areaw*areah))*255);
                int promediob = (int) ((b/(areaw*areah))*255);
                Image ausar = filt.selRGB(reducida, promedior,promediog, promediob);
                PixelReader prr = ausar.getPixelReader();
                for(int m = 0; m < areaw; m++){
		    for(int n = 0; n < areah; n++){
			Color col = prr.getColor(m, n);
			double nr = col.getRed();
			double ng = col.getGreen();
			double nb = col.getBlue();
			Color nucol = Color.color(nr,ng,nb);
			if (i+m < imagen.getWidth() && j+n < imagen.getHeight() ) pw.setColor(i+m,j+n,nucol);
                    }
                }
            }
        }
        return (Image) wi;
    }
    
    public Image recursivo(Image imagen, int areaw, int areah){
        Filtros filt = new Filtros();
        Image imbn = filt.grey3(imagen);
        Image reducida = scale(imbn,areaw,areah);
        PixelReader pr = imbn.getPixelReader();
        WritableImage wi = new WritableImage((int)imagen.getWidth(), (int)imagen.getHeight());
        PixelWriter pw = wi.getPixelWriter();
        
        
        for(int i = 0; i < imbn.getWidth(); i = i + areaw){
            for (int j = 0; j < imbn.getHeight(); j = j + areah){
                double r = 0;
                for(int m = 0; m < areaw; m++){
		    for(int n = 0; n < areah; n++){
			if (i+m < imbn.getWidth() && j+n < imbn.getHeight() ) {
			    Color col = pr.getColor(i+m,j+n);    
			    double re = col.getRed();
			    r+= re;
			}
		    }
                }
                
                int promedio = (int) ((r/(areaw*areah))*255);
                int aaplicar = promedio - 128;
                Image ausar = filt.bright(reducida, aaplicar);
                PixelReader prr = ausar.getPixelReader();
                for(int m = 0; m < areaw; m++){
		    for(int n = 0; n < areah; n++){
			Color col = prr.getColor(m, n);
			double nr = col.getRed();
			double ng = col.getGreen();
			double nb = col.getBlue();
			Color nucol = Color.color(nr,ng,nb);
			if (i+m < imagen.getWidth() && j+n < imagen.getHeight() ) pw.setColor(i+m,j+n,nucol);
                    }
                }
            }
        }
        return (Image) wi;
    }
        
}   
