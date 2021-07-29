package pdi.tareas;

/**
 *
 * @author Eric Sanchez 314347440 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import java.net.URL;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Image;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


import javax.imageio.ImageIO;


public class Main {
  private JFrame      j;
  private JMenu       jmenu, jfiltros, jmarcaAgua;
  private JMenuBar    jbar;
  private JMenuItem   jmi, jexit,marcaAgua;
  private JPanel      container, containerWarhol, jpanel, jpanelbar, jPanelModified, jPanelWarhol;
  JLabel              image, modified;
  ImageIcon           ic;
  Image               img, test;
  BufferedImage       tmp, tmpNew;
  File destImageFile = new File("text_watermarked.png");


  /**
   * Agrega elementos al menu
   * @param menu Menu al que se le agregaran elementos
   * @param item Elemento a agregar
   */
  public static void agregaFiltrosMenu(JMenu menu, JMenuItem[] item) {
    for(int i=0; i<item.length; i++){
      menu.add(item[i]);
    }
  }

  /**
   * @constructor
   *
   */
  Main() throws Exception {
    
    j = new JFrame("Proceso-digital 2021");
    ImageIcon img = new ImageIcon("corgi.png");
    j.setIconImage(img.getImage());
    j.getContentPane().setBackground( new Color(47, 228, 247));
    j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    j.setPreferredSize(new Dimension(1200, 600));
    j.setLayout(new GridBagLayout());
 
    jpanel = new JPanel();
    jpanel.setLayout(new BorderLayout());
    image = new JLabel("");
    modified = new JLabel("");

    jPanelModified = new JPanel();

    jPanelModified.add(modified, BorderLayout.CENTER);
    jpanel.add(image, BorderLayout.CENTER);

    container = new JPanel();
    container.setLayout(new GridLayout(1,2));
    container.add(jpanel);

    j.add(container);

    jpanelbar = new JPanel();
    jpanelbar.setLayout(new GridBagLayout());
    jpanelbar.setBackground(Color.red);

    // Se crea el menú 
    jbar = new JMenuBar();
    jmenu = new JMenu("Cargar");
    jfiltros = new JMenu("Marca de Agua");
    jmarcaAgua = new JMenu("Marca de agua");

    //Agregamos el botón de abrir imagen
    jmi = new JMenuItem("Abrir");
    jmi.addActionListener((ActionEvent ae) -> {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                
                test = ImageIO.read(file);
                //Redimensionamos la imagen para que se muestre en la ventana
                Image scaledImage = test.getScaledInstance(500,500,Image.SCALE_SMOOTH);
                tmpNew = toBufferedImage(scaledImage);
                tmp = toBufferedImage(scaledImage);
                image.setIcon(new ImageIcon(scaledImage));
                modified.setIcon(new ImageIcon(scaledImage));
                
            } catch (IOException e) {
                e.printStackTrace();
                
            }
        }
    });

   //Botón para cerrar el proceso
    jexit = new JMenuItem("Salir");
    jexit.addActionListener((ActionEvent ae) -> {
        System.exit(0);
    });
    /*
    *Inicia implementación de los filtros
    */
    //
    

    
    
    /*Marca de Agua*/
     marcaAgua = new JMenuItem("Insertar Marca de agua");
    marcaAgua.addActionListener((java.awt.event.ActionEvent evt) -> {
        String input = JOptionPane.showInputDialog(j, "Introduzca el texto deseado", 0);
        int[] arr = new int[3];
        int a =  tmpNew.getHeight() / 90;
        int l = tmpNew.getWidth() / 90;
        int aux = 0;
        String s = "";
        StyleContext sc = new StyleContext();
        DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane pane = new JTextPane(doc);        
        JFrame f = new JFrame("Marca de Agua");
        Graphics2D g2d = (Graphics2D) tmpNew.getGraphics();
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
        g2d.setComposite(alphaChannel);
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 64));
        FontMetrics fontMetrics = g2d.getFontMetrics();
        Rectangle2D rect = fontMetrics.getStringBounds(input, g2d);
        // Se asingan las coordenadas donde ira la marca
        int centerX = (tmpNew.getWidth() - (int) rect.getWidth()) / 2;
        int centerY = tmpNew.getHeight() / 2;
        // Pintamos la marca en la imagen 
        g2d.drawString(input, centerX, centerY);
        try {
            ImageIO.write(tmpNew, "png",  destImageFile);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2d.dispose();
        JOptionPane.showMessageDialog(null, "Marca aplicada"+"\n"+"La imagen se guardó en la carpeta raiz de la aplicación");
                    try {
                
                test = ImageIO.read(destImageFile);
                //Redimensionamos la imagen para que se muestre en la ventana
                Image scaledImage = test.getScaledInstance(500,500,Image.SCALE_SMOOTH);
                tmpNew = toBufferedImage(scaledImage);
                tmp = toBufferedImage(scaledImage);
                image.setIcon(new ImageIcon(scaledImage));
                modified.setIcon(new ImageIcon(scaledImage));
                
            } catch (IOException e) {
                
                e.printStackTrace();
                
            }

    });

     //Declaramos el orden en el que aparecen en el menú
    JMenuItem[] jFilters = {marcaAgua};
    jmenu.add(jmi);
    jmenu.add(jexit);
    jbar.add(jmenu);
    jbar.add(jfiltros);
    agregaFiltrosMenu(jfiltros, jFilters);
    j.setJMenuBar(jbar);
    j.pack();
    j.setResizable(false);
    j.setVisible(true);
  }

  
  /**
   * Convierte una imagen de tipo Image en una imagen de tipo BufferedImage
   * @param img La imagen a ser convertida
   * @return La imagen convertida.
   */
  public static BufferedImage toBufferedImage(Image img) throws IOException {
      if (img instanceof BufferedImage) {
        return (BufferedImage) img;
      }

      BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

      Graphics2D bGr = bimage.createGraphics();
      bGr.drawImage(img, 0, 0, null);
      bGr.dispose();
      return bimage;
  }

  /**
   * Obtiene las componenetes RGB de un pixel en la posicion dada.
   *
   * @param img La imagen a ser procesada
   * @param x La posicion horizontal del pixel
   * @param y La posicion vertical del pixel
   * @return El arreglo con las componentes RGB del pixel
   */
  public static int[] getPixel(BufferedImage img, int x, int y) {
    int argb = img.getRGB(x,y);
    int rgb[] = new int[] {
      (argb >> 16) & 0xff, //red
      (argb >>  8) & 0xff, //green
      (argb      ) & 0xff  //blue
    };
    return rgb;
  }

  /**
   * Cambia los componentes RGB del pixel de una imagen en la posicion indicada.
   *
   * @param img La imagen a ser convertida
   * @param x Posicion horizontal del pixel
   * @param y Posicion vertical del pixel
   * @param red Componente roja RGB
   * @param green Componente verde RGB
   * @param blue Componente azul
   */
  public static void setPixel(BufferedImage img, int x, int y, int red, int green, int blue) {
    Color nuevo = new Color(red, green, blue);
    int rgbNuevo = nuevo.getRGB();
    img.setRGB(x, y, rgbNuevo);
  }
/**
 * Obtienen el promedio de color de una región dada
 * 
 * @param inicioa
 * @param fina
 * @param iniciol
 * @param finl
 * @param img
 * @return el color promedio de cierta región
 */
  public int[] PromedioRegion(int inicioa, int fina, int iniciol, int finl, BufferedImage img){
    int rp =0;
    int gp =0;
    int bp =0;
    int cantidad = (img.getHeight() / 90)*(img.getWidth() /90) ;
    int[] rgbArray = new int[3];
    Color nuevo;
    for(int a = inicioa ; a < fina;a++){
        for(int b= iniciol;b< finl;b++){
         if(a<img.getWidth()){
           if(b<img.getHeight()){
              //Color c1=new Color(img.getRGB(a, b));
              int argb = img.getRGB(a,b);
              int rgb[] = new int[] {
                (argb >> 16) & 0xff, //red
                (argb >>  8) & 0xff, //green
                (argb      ) & 0xff  //blue
              };

              rp+= rgb[0];
              gp+= rgb[1];
              bp+= rgb[2];

           }
         }
        }
    }
    rp = (int)(rp/cantidad);
    gp = (int)(gp/cantidad);
    bp = (int)(bp/cantidad);
    rgbArray[0] = rp;
    rgbArray[1] = gp;
    rgbArray[2] = bp;
    nuevo = new Color(rp,gp,bp);

    int promedio = (int)((rp + gp + bp)/3);
    return rgbArray;
  }
  /**
   * Realiza la ejecución
   * @param args 
   */
  public static void main(String[] args) throws Exception {
    
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
          try {
              Main main = new Main();
          } catch (Exception ex) {
              Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    });
  }
}
