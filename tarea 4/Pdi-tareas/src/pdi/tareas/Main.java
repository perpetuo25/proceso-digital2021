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
  private JMenuItem   jmi, jexit, unaLetraM, unaLetraGrises, letrasBlancoNegro, coloresLetrasTonosGris, 
                      tonosGrisLetrasGris, coloresTexto,marcaAgua, naipes, dominoBlancas, dominoNegras;
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
    jfiltros = new JMenu("Aplicar Filtro");
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
    
    // 1 Filtro una letra en color 
    unaLetraM  = new JMenuItem("Una sola letra");
    unaLetraM.addActionListener((java.awt.event.ActionEvent evt) -> {
        int[] arr = new int[3];
        int a =  tmpNew.getHeight() / 90;
        int l = tmpNew.getWidth() / 90;
        String s = "";
        if(tmpNew != null){
            for (int y = 0; y < 90; y++){
                if(y > 0) s += "\n";
                for (int x = 0 ; x < 90; x++){
                    s += "M";
                }
            }
        }

        StyleContext sc = new StyleContext();
        DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane pane = new JTextPane(doc);

        JFrame f = new JFrame("Colores sin Letras");
        try{
            doc.insertString(0, s, null);
        }catch(BadLocationException e){
            System.out.println(e);
        }
        
        
        int contador = 0;
        for (int h = 0; h < 90; h++){
            if(h > 0) contador++ ;
            for (int k = 0 ; k < 90; k++){
                Style estilo = sc.addStyle("ConstantWidth", null);
                arr = PromedioRegion(k*l,(k*l)+l,h*a,(h*a)+a,tmpNew);
                Color c = new Color(arr[0],arr[1],arr[2]);
                StyleConstants.setForeground(estilo, c);
                doc.setCharacterAttributes(contador++, 1, estilo, false);
            }
        }
        f.getContentPane().add(new JScrollPane(pane));
        f.setSize(1300, 600);
        f.setVisible(true);
    });
    
    
    // 2 Filtro una letra en tonos grises
    unaLetraGrises = new JMenuItem("Una letra en gris");
    unaLetraGrises.addActionListener((java.awt.event.ActionEvent evt) -> {
        int[] arr = new int[3];
        int a =  tmpNew.getHeight() / 90;
        int l = tmpNew.getWidth() / 90;
        String s = "";
        if(tmpNew != null){
            for (int y = 0; y < 90; y++){
                if(y > 0) s += "\n";
                for (int x = 0 ; x < 90; x++){
                    s += "M";
                }
            }
        }
        StyleContext sc = new StyleContext();
        DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane pane = new JTextPane(doc);
        JFrame f = new JFrame("Tonos de Gris");
        try{
            doc.insertString(0, s, null);
        }catch(BadLocationException e){
            System.out.println(e);
        }               
        int contador = 0;
        for (int h = 0; h < 90; h++){
            if(h > 0) contador++ ;
            for (int k = 0 ; k < 90; k++){
                Style estilo = sc.addStyle("ConstantWidth", null);
                arr = PromedioRegion(k*l,(k*l)+l,h*a,(h*a)+a,tmpNew);
                int gris = (int)(arr[0]+arr[1]+arr[2])/3;
                Color c = new Color(gris,gris,gris);
                StyleConstants.setForeground(estilo, c);
                doc.setCharacterAttributes(contador++, 1, estilo, false);
            }
        }
        f.getContentPane().add(new JScrollPane(pane));
        f.setSize(1300, 600);
        f.setVisible(true);
    });
    
    
    //3 Filtro Letras que simulan tonos de gris... usamos 16 letras para simular los 256 tonos. Cada letra es el equivalente a 16 tonos: 
    letrasBlancoNegro = new JMenuItem("Letras blanco y negro");
    letrasBlancoNegro.addActionListener((java.awt.event.ActionEvent evt) -> {
        int[] arr = new int[3];
        int a =  tmpNew.getHeight() / 90;
        int l = tmpNew.getWidth() / 90;
        String s = "";
        if(tmpNew != null){
            for (int y = 0; y < 90; y++){
                if(y > 0) s += "\n";
                for (int x = 0 ; x < 90; x++){
                    int[] prom =  PromedioRegion(x*l,(x*l)+l,y*a,(y*a)+a,tmpNew);
                    int med = (int)(prom[0]+prom[1]+prom[2])/3;
                    if (med >= 0 && med < 17){
                        s += "M";
                    }else if(med >= 17 && med < 34){
                        s += "N";
                    }else if(med >= 34 && med < 52){
                        s += "H";
                    }else if(med >= 52 && med < 62){
                        s += "#";
                    }else if(med >= 62 && med < 86){
                        s += "Q";
                    }else if(med >= 86 && med < 103){
                        s += "U";
                    }else if(med >= 103 && med < 120){
                        s += "A";
                    }else if(med >= 120 && med < 138){
                        s += "D";
                    }else if(med >= 138 && med < 155){
                        s += "0";
                    }else if(med >= 155 && med < 172){
                        s += "Y";
                    }else if(med >= 172 && med < 189){
                        s += "2";
                    }else if(med >= 189 && med < 206){
                        s += "$";
                    }else if(med >= 206 && med < 223){
                        s += "%";
                    }else if(med >= 223 && med < 240){
                        s += "+";
                    }else if(med >= 240 && med < 257){
                        s += "-";
                    }
                }
            }
        }
        StyleContext sc = new StyleContext();
        DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane pane = new JTextPane(doc);
        Font font = new Font("Courier New", Font.PLAIN, 10);
        pane.setFont(font);
        JFrame f = new JFrame("Letras Blanco y Negro");
        try{
            doc.insertString(0, s, null);
        }catch(BadLocationException e){
            System.out.println(e);
        }               
        int contador = 0;
        for (int h = 0; h < 90; h++){
            if(h > 0) contador++ ;
            for (int k = 0 ; k < 90; k++){
                Style estilo = sc.addStyle("ConstantWidth", null);
                Color c = new Color(0,0,0);
                StyleConstants.setForeground(estilo, c);
                doc.setCharacterAttributes(contador++, 1, estilo, false);
            }
        }
        f.getContentPane().add(new JScrollPane(pane));
        f.setSize(1300, 600);
        f.setVisible(true);
    });
    
    
    // 4 Filtro Combinación del inciso 1 con el 3... Letras en color usando las 16 letras en tonos de gris...
    coloresLetrasTonosGris = new JMenuItem("Colores + letras tonos de gris");
    coloresLetrasTonosGris.addActionListener((java.awt.event.ActionEvent evt) -> {
        int[] arr = new int[3];
        int a =  tmpNew.getHeight() / 90;
        int l = tmpNew.getWidth() / 90;
        String s = "";
        if(tmpNew != null){
            for (int y = 0; y < 90; y++){
                if(y > 0) s += "\n";
                for (int x = 0 ; x < 90; x++){
                    int[] prom =  PromedioRegion(x*l,(x*l)+l,y*a,(y*a)+a,tmpNew);
                    int med = (int)(prom[0]+prom[1]+prom[2])/3;
                    if (med >= 0 && med < 17){
                        s += "M";
                    }else if(med >= 17 && med < 34){
                        s += "N";
                    }else if(med >= 34 && med < 52){
                        s += "H";
                    }else if(med >= 52 && med < 62){
                        s += "#";
                    }else if(med >= 62 && med < 86){
                        s += "Q";
                    }else if(med >= 86 && med < 103){
                        s += "U";
                    }else if(med >= 103 && med < 120){
                        s += "A";
                    }else if(med >= 120 && med < 138){
                        s += "D";
                    }else if(med >= 138 && med < 155){
                        s += "0";
                    }else if(med >= 155 && med < 172){
                        s += "Y";
                    }else if(med >= 172 && med < 189){
                        s += "2";
                    }else if(med >= 189 && med < 206){
                        s += "$";
                    }else if(med >= 206 && med < 223){
                        s += "%";
                    }else if(med >= 223 && med < 240){
                        s += "+";
                    }else if(med >= 240 && med < 257){
                        s += "-";
                    }
                }
            }
        }
        StyleContext sc = new StyleContext();
        DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane pane = new JTextPane(doc);
        Font font = new Font("Courier New", Font.PLAIN, 10);    
        pane.setFont(font);
        JFrame f = new JFrame("Colores + letras tonos de Gris");
        try{
            doc.insertString(0, s, null);
        }catch(BadLocationException e){
            System.out.println(e);
        }
        int contador = 0;
        for (int h = 0; h < 90; h++){
            if(h > 0) contador++ ;
            for (int k = 0 ; k < 90; k++){
                Style estilo = sc.addStyle("ConstantWidth", null);
                arr = PromedioRegion(k*l,(k*l)+l,h*a,(h*a)+a,tmpNew);
                Color c = new Color(arr[0],arr[1],arr[2]);
                StyleConstants.setForeground(estilo, c);
                doc.setCharacterAttributes(contador++, 1, estilo, false);
            }
        }
        f.getContentPane().add(new JScrollPane(pane));
        f.setSize(1300, 600);
        f.setVisible(true);
    });

    // 5 Combinación del inciso 2 con el 3...
    tonosGrisLetrasGris = new JMenuItem("Tonos de gris + letras tonos de gris");
    tonosGrisLetrasGris.addActionListener((java.awt.event.ActionEvent evt) -> {
        int[] arr = new int[3];
        int a =  tmpNew.getHeight() / 90;
        int l = tmpNew.getWidth() / 90;
        String s = "";
        if(tmpNew != null){
            for (int y = 0; y < 90; y++){
                if(y > 0) s += "\n";
                for (int x = 0 ; x < 90; x++){
                    int[] prom =  PromedioRegion(x*l,(x*l)+l,y*a,(y*a)+a,tmpNew);
                    int med = (int)(prom[0]+prom[1]+prom[2])/3;
                    if (med >= 0 && med < 17){
                        s += "M";
                    }else if(med >= 17 && med < 34){
                        s += "N";
                    }else if(med >= 34 && med < 52){
                        s += "H";
                    }else if(med >= 52 && med < 62){
                        s += "#";
                    }else if(med >= 62 && med < 86){
                        s += "Q";
                    }else if(med >= 86 && med < 103){
                        s += "U";
                    }else if(med >= 103 && med < 120){
                        s += "A";
                    }else if(med >= 120 && med < 138){
                        s += "D";
                    }else if(med >= 138 && med < 155){
                        s += "0";
                    }else if(med >= 155 && med < 172){
                        s += "Y";
                    }else if(med >= 172 && med < 189){
                        s += "2";
                    }else if(med >= 189 && med < 206){
                        s += "$";
                    }else if(med >= 206 && med < 223){
                        s += "%";
                    }else if(med >= 223 && med < 240){
                        s += "+";
                    }else if(med >= 240 && med < 257){
                        s += "-";
                    }
                }
            }
        }
        StyleContext sc = new StyleContext();
        DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane pane = new JTextPane(doc);
        Font font = new Font("Courier New", Font.PLAIN, 10);        
        pane.setFont(font);
        JFrame f = new JFrame("Letras Blanco y Negro");
        try{
            doc.insertString(0, s, null);
        }catch(BadLocationException e){
            System.out.println(e);
        }            
        int contador = 0;
        for (int h = 0; h < 90; h++){
            if(h > 0) contador++ ;
            for (int k = 0 ; k < 90; k++){
                Style estilo = sc.addStyle("ConstantWidth", null);
                arr = PromedioRegion(k*l,(k*l)+l,h*a,(h*a)+a,tmpNew);
                int prom = (int)(arr[0]+arr[1]+arr[2])/3;
                Color c = new Color(prom,prom,prom);
                StyleConstants.setForeground(estilo, c);
                doc.setCharacterAttributes(contador++, 1, estilo, false);
            }
        }
        f.getContentPane().add(new JScrollPane(pane));
        f.setSize(1300, 600);
        f.setVisible(true);
    });

    
    // 6 Imagen hecha con un letrero...
    coloresTexto = new JMenuItem("Imagen hecha con un letrero");
    coloresTexto.addActionListener((java.awt.event.ActionEvent evt) -> {
        String input = JOptionPane.showInputDialog(j, "Introduzca el texto deseado", 0);
        int[] arr = new int[3];
        int a =  tmpNew.getHeight() / 90;
        int l = tmpNew.getWidth() / 90;
        int aux = 0;
        String s = "";
        if(tmpNew != null){
            for (int y = 0; y < 90; y++){
                if(y > 0) s += "\n";
                for (int x = 0 ; x < 90; x++){
                    if(aux == input.length()){
                        s += " ";
                        aux = 0;
                    }else{
                        s += input.substring(aux, aux+1);
                        aux++;
                    }
                }
            }
        }
        StyleContext sc = new StyleContext();
        DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane pane = new JTextPane(doc);
        Font font = new Font("Courier New", Font.PLAIN, 10);        
        pane.setFont(font);
        JFrame f = new JFrame("Colores con texto");
        try{
            doc.insertString(0, s, null);
        }catch(BadLocationException e){
            System.out.println(e);
        }                
        int contador = 0;
        for (int h = 0; h < 90; h++){
            if(h > 0) contador++ ;
            for (int k = 0 ; k < 90; k++){
                Style estilo = sc.addStyle("ConstantWidth", null);
                arr = PromedioRegion(k*l,(k*l)+l,h*a,(h*a)+a,tmpNew);
                Color c = new Color(arr[0],arr[1],arr[2]);
                StyleConstants.setForeground(estilo, c);
                doc.setCharacterAttributes(contador++, 1, estilo, false);
            }
        }
        f.getContentPane().add(new JScrollPane(pane));
        f.setSize(1300, 600);
        f.setVisible(true);
    });

    
    //7 Imagen con fichas de dominó blancas (con puntos negros)
    dominoBlancas = new JMenuItem("Domino Blancas");
    dominoBlancas.addActionListener((java.awt.event.ActionEvent evt) -> {
        int[] prom = new int[3];
        int a =  tmpNew.getHeight() / 90;
        int l = tmpNew.getWidth() / 90;
        String s = "";
        if(tmpNew != null){
            for (int y = 0; y < 90; y++){
                if(y > 0) s += "\n";
                for (int x = 0 ; x < 90; x++){
                    prom = PromedioRegion(x*l,(x*l)+l,y*a,(y*a)+a,tmpNew);
                    int med = (int)(prom[0]+prom[1]+prom[2])/3;
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
                }
            }
        }
        StyleContext sc = new StyleContext();
        DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane pane = new JTextPane(doc);
        //declaramos la fuente para dominó balnco
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Lasvwd__.ttf")).deriveFont(4f);
             GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Lasvwd__.ttf")));
            pane.setFont(font);
        } catch (FontFormatException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        JFrame f = new JFrame("Domino Blancas");
        try{
            doc.insertString(0, s, null);
        }catch(BadLocationException e){
            System.out.println(e);
        }
        int contador = 0;
        for (int h = 0; h < 90; h++){
            if(h > 0) contador++ ;
            for (int k = 0 ; k < 90; k++){
                Style estilo = sc.addStyle("ConstantWidth", null);
                Color c = new Color(0,0,0);
                StyleConstants.setForeground(estilo, c);
                doc.setCharacterAttributes(contador++, 1, estilo, false);
            }
        }
        f.getContentPane().add(new JScrollPane(pane));
        f.setSize(1300, 600);
        f.setVisible(true);
    });
    
    //8 Fichas de domino negras (con puntos blancos)...
    dominoNegras = new JMenuItem("Domino Negras");
    dominoNegras.addActionListener((java.awt.event.ActionEvent evt) -> {
        int[] prom = new int[3];
        int a =  tmpNew.getHeight() / 90;
        int l = tmpNew.getWidth() / 90;
        String s = "";
        if(tmpNew != null){
            for (int y = 0; y < 90; y++){
                if(y > 0) s += "\n";
                for (int x = 0 ; x < 90; x++){
                    prom = PromedioRegion(x*l,(x*l)+l,y*a,(y*a)+a,tmpNew);
                    int med = (int)(prom[0]+prom[1]+prom[2])/3;
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
                }
            }
        }
        StyleContext sc = new StyleContext();
        DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane pane = new JTextPane(doc);
        //Declaramos la fuente para  domino oscuro
         Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Lasvbld_.ttf")).deriveFont(4f);
             GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Lasvbld_.ttf")));
            pane.setFont(font);
        } catch (FontFormatException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        JFrame f = new JFrame("Domino Negras");

        try{
            doc.insertString(0, s, null);
        }catch(BadLocationException e){
            System.out.println(e);
        }        
        int contador = 0;
        for (int h = 0; h < 90; h++){
            if(h > 0) contador++ ;
            for (int k = 0 ; k < 90; k++){
                Style estilo = sc.addStyle("ConstantWidth", null);
                Color c = new Color(0,0,0);
                StyleConstants.setForeground(estilo, c);
                doc.setCharacterAttributes(contador++, 1, estilo, false);
            }
        }
        f.getContentPane().add(new JScrollPane(pane));
        f.setSize(1300, 600);
        f.setVisible(true);
    });
    
    //9 Fichas con naipes...
    naipes = new JMenuItem("Naipes");
    naipes.addActionListener((java.awt.event.ActionEvent evt) -> {
        int[] prom = new int[3];
        int a =  tmpNew.getHeight() / 90;
        int l = tmpNew.getWidth() / 90;
        String s = "";
        if(tmpNew != null){
            for (int y = 0; y < 90; y++){
                if(y > 0) s += "\n";
                for (int x = 0 ; x < 90; x++){
                    prom = PromedioRegion(x*l,(x*l)+l,y*a,(y*a)+a,tmpNew);
                    int med = (int)(prom[0]+prom[1]+prom[2])/3;
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
                }
            }
        }
        StyleContext sc = new StyleContext();
        DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane pane = new JTextPane(doc);
        //Declaramos la fuente para los naipes
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("PLAYCRDS.ttf")).deriveFont(4f);
             GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PLAYCRDS.ttf")));
            pane.setFont(font);
        } catch (FontFormatException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        JFrame f = new JFrame("Naipes");

        try{
            doc.insertString(0, s, null);
        }catch(BadLocationException e){
            System.out.println(e);
        }        
        int contador = 0;
        for (int h = 0; h < 90; h++){
            if(h > 0) contador++ ;
            for (int k = 0 ; k < 90; k++){
                Style estilo = sc.addStyle("ConstantWidth", null);
                Color c = new Color(0,0,0);
                StyleConstants.setForeground(estilo, c);
                doc.setCharacterAttributes(contador++, 1, estilo, false);
            }
        }
        f.getContentPane().add(new JScrollPane(pane));
        f.setSize(1300, 600);
        f.setVisible(true);
    });
    
    
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
    JMenuItem[] jFilters = {unaLetraM, unaLetraGrises, letrasBlancoNegro, 
                            coloresLetrasTonosGris, tonosGrisLetrasGris, coloresTexto, 
                             dominoBlancas, dominoNegras,naipes, marcaAgua};
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
