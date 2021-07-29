package digital;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Filtrosletras {
    
    public TextFlow filtroUnaLetraColor(Image imagen, String let){
        int w = (int) imagen.getWidth()/100;
        int h = (int) imagen.getHeight()/100;
        String s = "";
        if(imagen != null){
	    for (int y = 0; y < 99; y++){
		if(y > 0) s += "\n";
		for (int x = 0 ; x < 99; x++){
		    s += let;
		}
	    }
        }
        
        PixelReader pr = imagen.getPixelReader();
        TextFlow flow = new TextFlow();
        int contador = 0;
        
        for(int j = 0; j < 100; j ++){
            for (int i = 0; i < 100; i ++){
                double r = 0;
                double g = 0;
                double b = 0;
                for(int m = 0; m < w; m++){
		    for(int n = 0; n < h; n++){
			if ((i*w)+m < imagen.getWidth() && (j*h)+n < imagen.getHeight() ) {
			    Color col = pr.getColor((i*w)+m,(j*h)+n);    
			    double re = col.getRed();
			    r+= re;
			    double gr = col.getGreen();
			    g+= gr; 
			    double bl = col.getBlue();
			    b+= bl;
			}
		    }
                }
                
                if(contador < s.length()){
		    Text t = new Text();
		    String letra = Character.toString(s.charAt(contador));
		    String hex = String.format("#%02X%02X%02X", (int) ((r*255)/(h*w)), (int) ((g*255)/(h*w)), (int) ((b*255)/(h*w)));
		    t.setStyle("-fx-fill: "+hex+";-fx-font-weight:normal;");
		    t.setFont(Font.font(6.0));
		    t.setText(letra);
		    flow.getChildren().add(t);
		    contador += 1;
                }
                
                
                
            }
        }
        
        return flow;
    }
    
    public TextFlow filtroUnaLetraBN(Image img, String letra){
	Filtros filt = new Filtros();
	return filtroUnaLetraColor(filt.grey1(img), letra);
    }          
    
    public TextFlow filtroMultLetras(Image img){
	TextFlow flow = new TextFlow();
	int contador = 0;
	int h =  (int)img.getHeight() / 90;
	int w = (int)img.getWidth() / 90;
	
	String s = "";
	if(img != null){
	    for (int y = 0; y < 90; y++){
		if(y > 0) s += "\n";
		for (int x = 0 ; x < 90; x++){
		    int med= promedioRegion(x*w,(x*w)+w,y*h,(y*h)+h,img);
		    if (med >= 0 && med < 16){
			s += "M";
		    } else if(med >= 16 && med < 32){
			s += "N";
		    }else if(med >= 32 && med < 48){
			s += "H";
		    }else if(med >= 48 && med < 64){
			s += "#";
		    }else if(med >= 64 && med < 80){
			s += "Q";
		    }else if(med >= 80 && med < 96){
			s += "U";
		    }else if(med >= 96 && med < 112){
			s += "A";
		    }else if(med >= 112 && med < 128){
			s += "D";
		    }else if(med >= 128 && med < 144){
			s += "O";
		    }else if(med >= 144 && med < 160){
			s += "Y";
		    }else if(med >= 160 && med < 176){
			s += "2";
		    }else if(med >= 176 && med < 192){
			s += "$";
		    }else if(med >= 192 && med < 208){
			s += "%";
		    }else if(med >= 208 && med < 224){
			s += "+";
		    }else if(med >= 224 && med < 240){
			s += "_";
		    }else if(med >= 240 && med < 256){
			s += " ";
		    } 
		    
		    if(contador < s.length()){
			Text t = new Text();
			String letra = Character.toString(s.charAt(contador));
			t.setFill(Color.BLACK);
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
			contador += 1;
		    }
		}
	    }
	}
	
	return flow;
	
    }
    
    public TextFlow filtroMultLetrasColor(Image img){
	TextFlow flow = new TextFlow();
	int h =  (int)img.getHeight() / 100;
	int w = (int)img.getWidth() / 100;
	
	String s = "";
	if(img != null){
	    for (int j = 0; j < 99; j++){
		if(j > 0) s += "\n";
		Text te = new Text();
		String salto = "\n";
		te.setFont(Font.font("Consolas",7.5));
		te.setText(salto);
		flow.getChildren().add(te);
		for (int i = 0 ; i < 99; i++){
		    PixelReader pr = img.getPixelReader();
		    double r = 0;
		    double g = 0;
		    double b = 0;
		    for(int m = 0; m < w; m++){
			for(int n = 0; n < h; n++){
			    if ((i*w)+m < img.getWidth() && (j*h)+n < img.getHeight() ) {
				Color col = pr.getColor((i*w)+m,(j*h)+n);    
				double re = col.getRed();
				r+= re;
				double gr = col.getGreen();
				g+= gr; 
				double bl = col.getBlue();
				b+= bl;
			    }
			}
		    }
		    
		    
		    int med = (int)(((r/(h*w)) + (g/(h*w)) + (b/(h*w)))*255/3);
		    
		    if (med >= 0 && med < 16){
			s += "M";
			Text t = new Text();
			String letra = Character.toString('M');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
		    } else if(med >= 16 && med < 32){
			Text t = new Text();
			String letra = Character.toString('N');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
			s += "N";
		    }else if(med >= 32 && med < 48){
			s += "H";
			Text t = new Text();
			String letra = Character.toString('H');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
		    }else if(med >= 48 && med < 64){             
			s += "#";
			Text t = new Text();
			String letra = Character.toString('#');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
		    }else if(med >= 64 && med < 80){
			s += "Q";
			Text t = new Text();
			String letra = Character.toString('Q');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
		    }else if(med >= 80 && med < 96){
			s += "U";
			Text t = new Text();
			String letra = Character.toString('U');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
		    }else if(med >= 96 && med < 112){
			s += "A";
			Text t = new Text();
			String letra = Character.toString('A');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
		    }else if(med >= 112 && med < 128){
			s += "D";
			Text t = new Text();
			String letra = Character.toString('D');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
		    }else if(med >= 128 && med < 144){
			s += "O";
			Text t = new Text();
			String letra = Character.toString('O');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
		    }else if(med >= 144 && med < 160){
			s += "Y";
			Text t = new Text();
			String letra = Character.toString('Y');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
		    }else if(med >= 160 && med < 176){
			s += "2";
			Text t = new Text();
			String letra = Character.toString('2');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
		    }else if(med >= 176 && med < 192){
			s += "$";
			Text t = new Text();
			String letra = Character.toString('$');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
		    }else if(med >= 192 && med < 208){
			s += "%";
			Text t = new Text();
			String letra = Character.toString('%');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
		    }else if(med >= 208 && med < 224){
			s += "+";
			Text t = new Text();
			String letra = Character.toString('+');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
		    }else if(med >= 224 && med < 240){
			s += "_";
			Text t = new Text();
			String letra = Character.toString('_');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
		    }else if(med >= 240 && med < 256){
			s += " ";
			Text t = new Text();
			String letra = Character.toString(' ');
			t.setFill(Color.color(r/(h*w),g/(h*w),b/(h*w)));
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
		    } 
		}
	    }
	}
	
	return flow;
	
    }
    
    public TextFlow filtroMultLetrasBN(Image img){
	Filtros filt = new Filtros();
	return filtroMultLetrasColor(filt.grey1(img));
    }
    
    public TextFlow domino(Image img){
	TextFlow flow = new TextFlow();
	int contador = 0;
	int h =  (int)img.getHeight() / 90;
	int w = (int)img.getWidth() / 90;
	
	String s = "";
	if(img != null){
	    for (int y = 0; y < 90; y++){
		if(y > 0) s += "\n";
		for (int x = 0 ; x < 90; x++){
		    int med= promedioRegion(x*w,(x*w)+w,y*h,(y*h)+h,img);
		    if (med >= 0 && med < 37){
			s += "6";
		    } else if(med >= 37 && med < 74){
			s += "5";
		    }else if(med >= 74 && med < 111){
			s += "4";
		    }else if(med >= 111 && med < 148){
			s += "3";
		    }else if(med >= 148 && med < 185){
			s += "2";
		    }else if(med >= 185 && med < 222){
			s += "1";
		    }else if(med >= 222 && med < 256){
			s += "0";
		    }
		    
		    if(contador < s.length()){
			Text t = new Text();
			String letra = Character.toString(s.charAt(contador));
			t.setFill(Color.BLACK);
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
			contador += 1;
		    }
		}
	    }
	}
	
	return flow;
	
    }
    
    public TextFlow naipes(Image img){
	TextFlow flow = new TextFlow();
	int contador = 0;
	int h =  (int)img.getHeight() / 90;
	int w = (int)img.getWidth() / 90;
	
	String s = "";
	if(img != null){
	    for (int y = 0; y < 90; y++){
		if(y > 0) s += "\n";
		for (int x = 0 ; x < 90; x++){
		    int med= promedioRegion(x*w,(x*w)+w,y*h,(y*h)+h,img);
		    if (med >= 0 && med < 23){
			s += "Z";
		    } else if(med >= 23 && med < 46){
			s += "W";
		    }else if(med >= 46 && med < 69){
			s += "V";
		    }else if(med >= 69 && med < 92){
			s += "U";
		    }else if(med >= 92 && med < 115){
			s += "T";
		    }else if(med >= 115 && med < 138){
			s += "S";
		    }else if(med >= 138 && med < 161){
			s += "R";
		    }else if(med >= 161 && med < 184){
			s += "Q";
		    }else if(med >= 184 && med < 207){
			s += "P";
		    }else if(med >= 207 && med < 230){
			s += "O";
		    }else if(med >= 230 && med < 256){
			s += "N";
		    }
		    
		    if(contador < s.length()){
			Text t = new Text();
			String letra = Character.toString(s.charAt(contador));
			t.setFill(Color.BLACK);
			t.setFont(Font.font("Consolas",7.5));
			t.setText(letra);
			flow.getChildren().add(t);
			contador += 1;
		    }
		}
	    }
	}
	
	return flow;
	
    }
    
    public int promedioRegion(int inicioa, int fina,int iniciol, int finl, Image img){
        PixelReader pr = img.getPixelReader();
        int rp =0;
        int gp =0;
        int bp =0;
        int cantidad = (int) ((img.getHeight() / 90)*(img.getWidth() /90)) ;
	
        for(int a = inicioa ; a < fina;a++){
            for(int b= iniciol;b< finl;b++){
		if(a<img.getWidth()){
		    if(b<img.getHeight()){
			Color col = pr.getColor(a,b); 
			rp+= (int) (col.getRed()*255);    
			gp+= (int) (col.getGreen()*255);
			bp+= (int) (col.getBlue()*255);
		    }
		}
            }
        }
	
        rp = (int)(rp/cantidad);
        gp = (int)(gp/cantidad);
        bp = (int)(bp/cantidad);
        int promedio = (int)((rp + gp + bp)/3);
	
        return promedio;
    }
}
