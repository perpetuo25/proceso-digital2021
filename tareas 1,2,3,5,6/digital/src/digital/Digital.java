package digital;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.PopupWindow;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;


public class Digital extends Application {
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
	
        Text desc = new Text("Editando: ");
        BorderPane border = new BorderPane();
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setPadding(new Insets(10,10,10,10));
        border.setTop(addMenu(primaryStage,border,grid));
        
        
        Group root = new Group(border);
        
        Scene scene = new Scene(root, 1070, 800);
        scene.setFill(Color.CYAN);
        primaryStage.setTitle("Proceso Digital de Imagenes");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    
    File fi;
    
    public HBox addMenu(Stage primaryStage, BorderPane bp, GridPane grid){
	HBox hbox = new HBox();
	
	Menu mfile = new Menu("Archivo");
	MenuItem nim = new MenuItem("Seleccionar Imagen");
        nim.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent t) {
		    final File f = selImagen(primaryStage);
		    fi = f;
		    //GridPane gp = addGridPane(grid);
		    try {
			if (f != null) {
			    if (!grid.getChildren().isEmpty()) {
				grid.getChildren().remove(1);
				grid.getChildren().remove(0);
			    }
			    Image im = addImages(new FileInputStream(f),grid);
			    bp.setCenter(grid);    
			}
		    } catch(FileNotFoundException fe){}
		}
	    });        
	mfile.getItems().add(nim);
	
	Filtros filter = new Filtros();
	
	Menu mfilt = new Menu("Filtros");
	MenuItem ms = new MenuItem("Mosaico");
        ms.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
		    setMosaicPopUp(primaryStage,grid);
		}
	    });    
	MenuItem tg1 = new MenuItem("Tono de Gris (Promedio)");
        tg1.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(filter.grey1(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem tg2 = new MenuItem("Tono de Gris (R,R,R)");
        tg2.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(filter.grey2(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem tg3 = new MenuItem("Tono de Gris (G,G,G)");
        tg3.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(filter.grey3(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem tg4 = new MenuItem("Tono de Gris (B,B,B)");
        tg4.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(filter.grey4(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    }); 
	MenuItem tg5 = new MenuItem("Tono de Gris (Constante)");
        tg5.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(filter.grey5(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
        
	MenuItem in = new MenuItem("Inverso");
        in.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(filter.inverse(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem ct = new MenuItem("Auto Contraste");
        ct.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(filter.contrast(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem br = new MenuItem("Brillo");
	br.setOnAction(
		       new EventHandler<ActionEvent>() {
			   @Override
			   public void handle(ActionEvent event) {
			       setBrightPopUp(primaryStage,grid);
			   }
         });
	MenuItem crgb = new MenuItem("Componente RGB");
        crgb.setOnAction(
			 new EventHandler<ActionEvent>() {
			     @Override
			     public void handle(ActionEvent event) {
				 setRGBPopUp(primaryStage,grid);
			     }
			 });
	MenuItem r = new MenuItem("Rojo");
        r.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(filter.red(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem v = new MenuItem("Verde");
        v.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(filter.green(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem a = new MenuItem("Azul");
        a.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(filter.blue(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	
	mfilt.getItems().addAll(ms,tg1,tg2,tg3,tg4,tg5,in,ct,br,crgb,r,v,a);
	
	Convolucion conv = new Convolucion();
	
	Menu convm = new Menu("Convolucion");
	
	MenuItem bl3 = new MenuItem("Blur Matrix 3x3");
	bl3.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(conv.blur3(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem bl5 = new MenuItem("Blur Matrix 5x5");
	bl5.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(conv.blur5(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem mb3 = new MenuItem("Motion Blur 3x3");
	mb3.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(conv.mblur3(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem mb9 = new MenuItem("Motion Blur matrix 9x9");
	mb9.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(conv.mblur9(im,9));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem ebv = new MenuItem("Find Edges (vertical)");
	ebv.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(conv.edgesv(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem ebh = new MenuItem("Find Edges (horizontal)");
	ebh.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(conv.edgesh(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem eb45 = new MenuItem("Find Edges (45??)");
	eb45.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(conv.edges45(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem ebe = new MenuItem("Find Edges (All)");
	ebe.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(conv.edgese(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem sh3 = new MenuItem("Sharpen 3x3");
	sh3.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(conv.sharpen3(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem sh5 = new MenuItem("Sharpen 5x5");
	sh5.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(conv.sharpen5(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem r3 = new MenuItem("Emboss 3x3");
	r3.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(conv.relieve3(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	MenuItem r5 = new MenuItem("Emboss 5x5");
	r5.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(conv.relieve5(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	
	convm.getItems().addAll(bl3,bl5,mb3,mb9,ebv,ebh,eb45,ebe,sh3,sh5,r3,r5);
	
	
	Menu flm = new Menu("Letras");
	
	Filtrosletras letras = new Filtrosletras();
	
	MenuItem filtromc = new MenuItem("Filtro M color");
	filtromc.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    TextFlow iv = letras.filtroUnaLetraColor(im, "M");
		    
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	
	MenuItem filtroac = new MenuItem("Filtro @ color");
	filtroac.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    TextFlow iv = letras.filtroUnaLetraColor(im, "@");
		    
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	
	MenuItem filtrombn = new MenuItem("Filtro M ByN");
	filtrombn.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    TextFlow iv = letras.filtroUnaLetraBN(im, "M");
		    
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	
	MenuItem filtroabn = new MenuItem("Filtro @ ByN");
	filtroabn.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    TextFlow iv = letras.filtroUnaLetraBN(im, "@");
		    
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	
	MenuItem filtroml = new MenuItem("Filtro 16 letras");
	filtroml.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    TextFlow iv = letras.filtroMultLetras(im);
		    
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	
	MenuItem filtromlc = new MenuItem("Filtro 16 letras color");
	filtromlc.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    TextFlow iv = letras.filtroMultLetrasColor(im);
		    
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	
	MenuItem filtromlbn = new MenuItem("Filtro 16 letras BN");
	filtromlbn.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    TextFlow iv = letras.filtroMultLetrasBN(im);
		    
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	
	MenuItem filtrod = new MenuItem("Domino");
	filtrod.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    TextFlow iv = letras.domino(im);
		    
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	
	MenuItem filtron = new MenuItem("Naipes");
	filtron.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    TextFlow iv = letras.naipes(im);
		    
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	
	flm.getItems().addAll(filtromc,filtroac,filtrombn,filtroabn, filtroml,filtromlc,filtromlbn,filtrod,filtron);
	
	
	Menu efe2 = new Menu("Semitonos");
	
	Recursive drs = new Recursive();
	
	MenuItem filtroht = new MenuItem("Filtro Semitono");
	filtroht.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(drs.halftone(im));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	
	efe2.getItems().addAll(filtroht);
	
	
	
	
	
	
	
	Menu efe3 = new Menu("Recursivo");
	
	Recursive drs2 = new Recursive();
	
	MenuItem filtrorc = new MenuItem("Recursivo Color");
	filtrorc.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(drs2.recursivocolor(im, 20,20));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	
	MenuItem filtrorbn = new MenuItem("Filtro Recursivo BN");
	filtrorbn.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) grid.getChildren().get(0);
		    Image im = imv.getImage();
		    ImageView iv = new ImageView(drs2.recursivo(im, 20,20));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    grid.getChildren().remove(1);
		    grid.add(iv,1,0);
		}
	    });
	
	efe3.getItems().addAll(filtrorc,filtrorbn);
	
	


	MenuBar mbar = new MenuBar();
	mbar.getMenus().addAll(mfile,mfilt,convm,flm,efe2,efe3);
	hbox.getChildren().add(mbar);
	return hbox;
    }
    
    
    
    public void setLNPopUp(Stage primaryStage, GridPane biggrid){
	Stage dialog = new Stage();
	dialog.setTitle("Intensidad.");
	dialog.initModality(Modality.APPLICATION_MODAL);
	dialog.initOwner(primaryStage);
	
	GridPane grid = new GridPane();
	grid.setAlignment(Pos.CENTER);
	grid.setHgap(10);
	grid.setVgap(10);
	grid.setPadding(new Insets(25, 25, 25, 25));
	
	Text spec = new Text("Intensidad a aplicar (entre 1 y 7)");
	Label b = new Label("Profundidad:");
	grid.add(spec, 0,0,1,1);
	grid.add(b,0,1);
	
	TextField br = new TextField();
	grid.add(br,1,1);
	
	
	
	
	Scene scene = new Scene(grid, 350, 200);
	dialog.setScene(scene);
	dialog.show();
	
    }
    
    public void setMosaicPopUp(Stage primaryStage, GridPane biggrid){
	
	Stage dialog = new Stage();
	dialog.setTitle("Tama??o mosaico");
	dialog.initModality(Modality.APPLICATION_MODAL);
	dialog.initOwner(primaryStage);
	
	GridPane grid = new GridPane();
	grid.setAlignment(Pos.CENTER);
	grid.setHgap(10);
	grid.setVgap(10);
	grid.setPadding(new Insets(25, 25, 25, 25));
	
	Text spec = new Text("Tamano del ??rea");
	Label w = new Label("Ancho:");
	Label h = new Label("Alto:");
	grid.add(spec, 0,0,2,1);
	grid.add(w,0,1);
	grid.add(h,0,2);
	
	TextField wi = new TextField();
	grid.add(wi,1,1);
	TextField he = new TextField();
	grid.add(he,1,2);
	
	Filtros filter = new Filtros();
	Button btn = new Button("Aplicar");
	btn.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) biggrid.getChildren().get(0);
		    Image im = imv.getImage();
		    int width = Integer.parseInt(wi.getText());
		    int height = Integer.parseInt(he.getText());
		    ImageView iv = new ImageView(filter.mosaic(im,width,height));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    biggrid.getChildren().remove(1);
		    biggrid.add(iv,1,0);
		    dialog.hide();
		}
	    });
	HBox hbBtn = new HBox(10);
	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	hbBtn.getChildren().add(btn);
	grid.add(hbBtn, 1, 4);
	
	
	Scene scene = new Scene(grid, 350, 200);
	dialog.setScene(scene);
	dialog.show();
    }
    
    public void setBrightPopUp(Stage primaryStage, GridPane biggrid){
	
	Stage dialog = new Stage();
	dialog.setTitle("Constante de brillo.");
	dialog.initModality(Modality.APPLICATION_MODAL);
	dialog.initOwner(primaryStage);
	
	GridPane grid = new GridPane();
	grid.setAlignment(Pos.CENTER);
	grid.setHgap(10);
	grid.setVgap(10);
	grid.setPadding(new Insets(25, 25, 25, 25));
	
	Text spec = new Text("Constante a aplicar");
	Label b = new Label("Brillo:");
	grid.add(spec, 0,0,1,1);
	grid.add(b,0,1);
	
	TextField br = new TextField();
	grid.add(br,1,1);
	
	
	Filtros filter = new Filtros();
	Button btn = new Button("Aplicar");
	btn.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) biggrid.getChildren().get(0);
		    Image im = imv.getImage();
		    int bri = Integer.parseInt(br.getText());
		    ImageView iv = new ImageView(filter.bright(im,bri));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    biggrid.getChildren().remove(1);
		    biggrid.add(iv,1,0);
		    dialog.hide();
		}
	    });
	HBox hbBtn = new HBox(10);
	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	hbBtn.getChildren().add(btn);
	grid.add(hbBtn, 1, 4);
	
	
	Scene scene = new Scene(grid, 350, 200);
	dialog.setScene(scene);
	dialog.show();
    }
    
    public void setRGBPopUp(Stage primaryStage, GridPane biggrid){
	
	Stage dialog = new Stage();
	dialog.setTitle("RGB");
	dialog.initModality(Modality.APPLICATION_MODAL);
	dialog.initOwner(primaryStage);
	
	GridPane grid = new GridPane();
	grid.setAlignment(Pos.CENTER);
	grid.setHgap(10);
	grid.setVgap(10);
	grid.setPadding(new Insets(25, 25, 25, 25));
	
	Text spec = new Text("Valores RGB");
	Label rl = new Label("R:");
	Label gl = new Label("G:");
	Label bl = new Label("B:");
	grid.add(spec, 0,0);
	grid.add(rl,0,1);
	grid.add(gl,0,2);
	grid.add(bl,0,3);
	
	TextField rt = new TextField();
	grid.add(rt,1,1);
	TextField gt = new TextField();
	grid.add(gt,1,2);
	TextField bt = new TextField();
	grid.add(bt,1,3);
	
	Filtros filter = new Filtros();
	Button btn = new Button("Aplicar");
	btn.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent t) {
		    ImageView imv = (ImageView) biggrid.getChildren().get(0);
		    Image im = imv.getImage();
		    int red = Integer.parseInt(rt.getText());
		    int green = Integer.parseInt(gt.getText());
		    int blue = Integer.parseInt(bt.getText());
		    
		    
		    ImageView iv = new ImageView(filter.selRGB(im,red,green,blue));
		    iv.setFitWidth(500);
		    iv.setPreserveRatio(true);
		    biggrid.getChildren().remove(1);
		    biggrid.add(iv,1,0);
		    dialog.hide();
		}
	    });
	HBox hbBtn = new HBox(10);
	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	hbBtn.getChildren().add(btn);
	grid.add(hbBtn, 1, 4);
	
	
	Scene scene = new Scene(grid, 300, 275);
	dialog.setScene(scene);
	dialog.show();
    }
    
    public File selImagen(Stage primaryStage){
	FileChooser fc = new FileChooser();
	configureFileChooser(fc);
	File file = fc.showOpenDialog(primaryStage);
	return file;
    }
    
    
    private static void configureFileChooser(final FileChooser fileChooser){                           
        fileChooser.setTitle("Seleccionar foto");
        fileChooser.setInitialDirectory(
					new File(System.getProperty("user.home"))
					); 
    }
    
    public File selDirectory(Stage primaryStage){
	DirectoryChooser dc = new DirectoryChooser();
	configureDirChooser(dc);
	File directory = dc.showDialog(primaryStage);
	return directory;
    }
    
    private static void configureDirChooser(DirectoryChooser dirchooser){
	dirchooser.setTitle("View Dirs");
	dirchooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }
    
    public GridPane addGridPane(GridPane grid){
	
	grid.setAlignment(Pos.CENTER);
	grid.setGridLinesVisible(true);
	grid.setHgap(20.0);
	grid.setVgap(20.0);
	grid.setPadding(new Insets(25,25,25,25));
	
	return grid;
    }
    
    public Image addImages(FileInputStream fis, GridPane grid){
	Image image1 = new Image(fis);
	ImageView iv1 = new ImageView(image1);
	iv1.setFitWidth(500);
	iv1.setPreserveRatio(true);
	ImageView iv2 = new ImageView(image1);
	iv2.setFitWidth(500);
	iv2.setPreserveRatio(true);
	
	grid.add(iv1,0,0);
	grid.add(iv2,1,0);
	
	return image1;
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
