package digital;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;


public class digital extends Application {
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        BorderPane border = new BorderPane();     
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setPadding(new Insets(10,10,10,10));
        border.setTop(addMenu(primaryStage,border,grid));
        Group root = new Group(border);
        Scene scene = new Scene(root, 1070, 800);
        scene.setFill(Color.BLACK);
        primaryStage.setTitle("Proceso Digital de Imagenes");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    
    File file;
    
    public HBox addMenu(Stage primaryStage, BorderPane bp, GridPane grid){
	HBox hbox = new HBox();
	
	Menu mfile = new Menu("Archivo");
	MenuItem nim = new MenuItem("Seleccionar Imagen");
        nim.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent t) {
		    final File f = selImagen(primaryStage);
		    file = f;
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
	
				
	Menu flm = new Menu("Letras");
	
	Filtrosletras letras = new Filtrosletras();
	
	MenuItem filtromc = new MenuItem("Filtro M color");
	filtromc.setOnAction((ActionEvent t) -> {
            ImageView imv = (ImageView) grid.getChildren().get(0);
            Image im = imv.getImage();
            TextFlow iv = letras.filtroUnaLetraColor(im, "M");
            
            grid.getChildren().remove(1);
            grid.add(iv,1,0);
        });
	
	MenuItem filtroac = new MenuItem("Filtro @ color");
	filtroac.setOnAction((ActionEvent t) -> {
            ImageView imv = (ImageView) grid.getChildren().get(0);
            Image im = imv.getImage();
            TextFlow iv = letras.filtroUnaLetraColor(im, "@");
            
            grid.getChildren().remove(1);
            grid.add(iv,1,0);
        });
	
	MenuItem filtrombn = new MenuItem("Filtro M ByN");
	filtrombn.setOnAction((ActionEvent t) -> {
            ImageView imv = (ImageView) grid.getChildren().get(0);
            Image im = imv.getImage();
            TextFlow iv = letras.filtroUnaLetraBN(im, "M");
            
            grid.getChildren().remove(1);
            grid.add(iv,1,0);
        });
	
	MenuItem filtroabn = new MenuItem("Filtro @ ByN");
	filtroabn.setOnAction((ActionEvent t) -> {
            ImageView imv = (ImageView) grid.getChildren().get(0);
            Image im = imv.getImage();
            TextFlow iv = letras.filtroUnaLetraBN(im, "@");
            
            grid.getChildren().remove(1);
            grid.add(iv,1,0);
        });
	
	MenuItem filtroml = new MenuItem("Filtro 16 letras");
	filtroml.setOnAction((ActionEvent t) -> {
            ImageView imv = (ImageView) grid.getChildren().get(0);
            Image im = imv.getImage();
            TextFlow iv = letras.filtroMultLetras(im);
            
            grid.getChildren().remove(1);
            grid.add(iv,1,0);
        });
	
	MenuItem filtromlc = new MenuItem("Filtro 16 letras color");
	filtromlc.setOnAction((ActionEvent t) -> {
            ImageView imv = (ImageView) grid.getChildren().get(0);
            Image im = imv.getImage();
            TextFlow iv = letras.filtroMultLetrasColor(im);
            
            grid.getChildren().remove(1);
            grid.add(iv,1,0);
        });
	
	MenuItem filtromlbn = new MenuItem("Filtro 16 letras BN");
	filtromlbn.setOnAction((ActionEvent t) -> {
            ImageView imv = (ImageView) grid.getChildren().get(0);
            Image im = imv.getImage();
            TextFlow iv = letras.filtroMultLetrasBN(im);
            
            grid.getChildren().remove(1);
            grid.add(iv,1,0);
        });
	
	MenuItem filtrod = new MenuItem("Domino");
	filtrod.setOnAction((ActionEvent t) -> {
            ImageView imv = (ImageView) grid.getChildren().get(0);
            Image im = imv.getImage();
            TextFlow iv = letras.domino(im);
            
            grid.getChildren().remove(1);
            grid.add(iv,1,0);
        });
	
	MenuItem filtron = new MenuItem("Naipes");
	filtron.setOnAction((ActionEvent t) -> {
            ImageView imv = (ImageView) grid.getChildren().get(0);
            Image im = imv.getImage();
            TextFlow iv = letras.naipes(im);
            
            grid.getChildren().remove(1);
            grid.add(iv,1,0);
        });
	
	flm.getItems().addAll(filtromc,filtroac,filtrombn,filtroabn, filtroml,filtromlc,filtromlbn,filtrod,filtron);
	
	Menu efe3 = new Menu("Recursivo");
	
	DitherRecST drs2 = new DitherRecST();
	
	MenuItem filtrorc = new MenuItem("Recursivo Color");
	filtrorc.setOnAction((ActionEvent t) -> {
            ImageView imv = (ImageView) grid.getChildren().get(0);
            Image im = imv.getImage();
            ImageView iv = new ImageView(drs2.recursivocolor(im, 20,20));
            iv.setFitWidth(500);
            iv.setPreserveRatio(true);
            grid.getChildren().remove(1);
            grid.add(iv,1,0);
        });
	
	MenuItem filtrorbn = new MenuItem("Filtro Recursivo BN");
	filtrorbn.setOnAction((ActionEvent t) -> {
            ImageView imv = (ImageView) grid.getChildren().get(0);
            Image im = imv.getImage();
            ImageView iv = new ImageView(drs2.recursivo(im, 20,20));
            iv.setFitWidth(500);
            iv.setPreserveRatio(true);
            grid.getChildren().remove(1);
            grid.add(iv,1,0);
        });
	
	efe3.getItems().addAll(filtrorc,filtrorbn);			           


	MenuBar mbar = new MenuBar();
	mbar.getMenus().addAll(mfile,flm,efe3);
	hbox.getChildren().add(mbar);
	return hbox;
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
