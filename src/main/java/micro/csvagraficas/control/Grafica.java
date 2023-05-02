
package micro.csvagraficas.control;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.*;


public class Grafica {
    
    private ArrayList<Lanzamiento> lanzamientos;
    
    public static final String DISTANCIA_TIEMPO = "Distancia";
    public static final String VELOCIDAD_TIEMPO = "Velocidad";
    public static final String ACELERACION_TIEMPO = "Aceleracion";
    
    public static final int PNG = 1;
    public static final int JPEG = 2;

    public Grafica(ArrayList<Lanzamiento> lanzamientos) {
        this.lanzamientos = lanzamientos;
    }
    
    public void crearGrafica(String categoriaGrafica) {
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(this.getSeries(categoriaGrafica));
        
        String tituloGrafica = categoriaGrafica + " vs. Tiempo";
        JFreeChart chart = ChartFactory.createXYLineChart(tituloGrafica, "Tiempo", categoriaGrafica, dataset, PlotOrientation.VERTICAL,false,false,false);

        ChartFrame frame = new ChartFrame("Grafica - "+tituloGrafica, chart);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setMenuBar(insertarMenuBar(chart));
        frame.setVisible(true);
    }
    
    public XYSeries getSeries(String categoriaGrafica) {
        
        XYSeries serie = new XYSeries("");
        
        for (int i = 0; i < lanzamientos.size(); i++) {
            double tiempo = lanzamientos.get(i).getTiempo();
            double ejeY;
            
            if(categoriaGrafica.equals(DISTANCIA_TIEMPO)){
                ejeY = lanzamientos.get(i).getDistancia();
            } else if(categoriaGrafica.equals(VELOCIDAD_TIEMPO)) {
                ejeY = lanzamientos.get(i).getVelocidad();
            } else if(categoriaGrafica.equals(ACELERACION_TIEMPO)) {
                ejeY = lanzamientos.get(i).getAceleracion();
            } else {
                ejeY = 0;
            }
            serie.add(tiempo,ejeY);
        }
        return serie;
    }
    
    public MenuBar insertarMenuBar(JFreeChart chart) {
        MenuBar menuBar = new MenuBar();
        Menu exportar = new Menu("Exportar");
        MenuItem itemPNG = new MenuItem("PNG");
        MenuItem itemJPEG = new MenuItem("JPEG");
        
        exportar.add(itemPNG);
        exportar.add(itemJPEG);
        menuBar.add(exportar);
        
        itemPNG.addActionListener((java.awt.event.ActionEvent evt) -> {
            botonExportar(evt, chart, Grafica.PNG);
        }); 
        itemJPEG.addActionListener((java.awt.event.ActionEvent evt) -> {
            botonExportar(evt, chart, Grafica.JPEG);
        }); 
        return menuBar;
    }
    
    public void botonExportar(java.awt.event.ActionEvent evt, JFreeChart chart, int formato) {
        try {
            if (formato==Grafica.PNG)
                ChartUtilities.saveChartAsPNG(ControlArchivo.guardarArchivo(".png"), chart, 800, 500);
            else if (formato==Grafica.JPEG)
                ChartUtilities.saveChartAsJPEG(ControlArchivo.guardarArchivo(".jpeg"), chart, 800, 500);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar la imagen", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}
