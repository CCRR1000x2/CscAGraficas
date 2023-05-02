
package micro.csvagraficas.control;

import java.awt.Container;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class ControlArchivo {
    
    private static ArrayList<Lanzamiento> lanzamientos;
    public static File archivo;
    
    private static final String TIEMPO = "TIEMPO=";
    private static final String DISTANCIA = "DISTANCIA=";
    private static final String VELOCIDAD = "VELOCIDAD=";
    private static final String ACELERACION = "ACELERACION=";

        
    public static void abrirArchivo(Container container) {
    
        JFileChooser fileChosser = new JFileChooser("./csv/");
        int seleccion = fileChosser.showOpenDialog(container);

        if (seleccion == JFileChooser.APPROVE_OPTION) {

            archivo = fileChosser.getSelectedFile();
            lanzamientos = new ArrayList<>();

            try (FileReader fr = new FileReader(archivo)) {
                
                BufferedReader br = new BufferedReader(fr);
                System.out.println("Archivo seleccionado: " + archivo.getName());
                String linea;

                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(";");
                    if (datos.length>0) {
                        Lanzamiento lanzamiento = crearLanzamiento(datos);
                        
                        if (lanzamiento!=null) {
                            lanzamientos.add(lanzamiento);
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Listo");
                
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "No se ha podido abrir el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "No se ha podido abrir el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static File guardarArchivo(String extension) {
        JFileChooser fileChosser = new JFileChooser("./Graficas Exportadas/");
        fileChosser.showSaveDialog(null);
        return new File(fileChosser.getSelectedFile() + extension);
    }
    
    public static Lanzamiento crearLanzamiento(String[] datos) {
        double d=0, t=0, v=0, a=0;
                    
        for (String dato : datos) {
            if (dato.startsWith(DISTANCIA)) {
                d = getValor(dato, DISTANCIA);
            } else if (dato.startsWith(TIEMPO)) {
                t = getValor(dato, TIEMPO);
            } else if (dato.startsWith(VELOCIDAD)) {
                v = getValor(dato, VELOCIDAD);
            } else if (dato.startsWith(ACELERACION)) {
                a = getValor(dato, ACELERACION);
            } 
        }

        if (d>0 || t>0 || v>0 || a>0) {
            return new Lanzamiento(d,t,v,a);
        }
        return null;
    }
    
    public static double getValor(String cadena, String prefijo) {
        String valor = cadena.replace(prefijo, "");
        return Double.parseDouble(valor);
    }

    public static ArrayList<Lanzamiento> getLanzamientos() {
        return lanzamientos;
    }
    
    
}
