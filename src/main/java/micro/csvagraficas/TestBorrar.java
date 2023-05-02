/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package micro.csvagraficas;

import java.util.ArrayList;
import micro.csvagraficas.control.ControlArchivo;
import static micro.csvagraficas.control.ControlArchivo.getValor;
import micro.csvagraficas.control.Lanzamiento;

/**
 *
 * @author CIROSS
 */
public class TestBorrar {

    private static ArrayList<Lanzamiento> lanzamientos = new ArrayList<>();
    private static final String TIEMPO = "TIEMPO=";
    private static final String DISTANCIA = "DISTANCIA=";
    private static final String VELOCIDAD = "VELOCIDAD=";
    private static final String ACELERACION = "ACELERACION=";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] linea = {"holi;wosh;ACELERACION=0.25;VELOCIDAD=1.32;DISTANCIA=50;;;","holi;wosh;ACELERACION=0.25;VELOCIDAD=1.32;DISTANCIA=50;;;","holi;wosh;ACELERACION=0.25;VELOCIDAD=1.32;DISTANCIA=50;;;"};
        for (int j = 0; j < linea.length; j++) {
            String[] datos = linea[j].split(";");
            double d=0, t=0, v=0, a=0;
            System.out.println(datos.length);

            for (int i = 0; i < datos.length; i++) {
                System.out.println(datos[i]);
                System.out.println(datos[i].length());
            }

            for (int i = 0; i < datos.length; i++) {

                if (datos[i].startsWith(DISTANCIA)) {
                    d = getValor(datos[i], DISTANCIA);

                } else if (datos[i].startsWith(TIEMPO)) {
                    t = getValor(datos[i], TIEMPO);

                } else if (datos[i].startsWith(VELOCIDAD)) {
                    v = getValor(datos[i], VELOCIDAD);

                } else if (datos[i].startsWith(ACELERACION)) {
                    a = getValor(datos[i], ACELERACION);

                } 
            }

            if (d>0 || t>0 || v>0 || a>0) {
                Lanzamiento lanzamiento = new Lanzamiento(d,t,v,a);
                lanzamientos.add(lanzamiento);
            }
        }
        
        System.out.println("total="+lanzamientos.size());
        for (int i = 0; i < lanzamientos.size(); i++) {
            System.out.println(lanzamientos.get(i));
        }
                    
    }
    
    public static double getValor(String cadena, String prefijo) {
        String valor = cadena.replace(prefijo, "");
        return Double.parseDouble(valor);
    }
    
}
