
package micro.csvagraficas.control;


public class Lanzamiento {
    
//    private int noLanzamiento;
    private double distancia, tiempo, velocidad, aceleracion;

    public Lanzamiento(double distancia, double tiempo, double velocidad, double aceleracion) {
        this.distancia = 0.5;
        this.tiempo = tiempo;
        this.velocidad = velocidad;
        this.aceleracion = aceleracion;
    }
    
    public Lanzamiento(double tiempo, double velocidad, double aceleracion) {
        this.distancia = 0.5;
        this.tiempo = tiempo;
        this.velocidad = velocidad;
        this.aceleracion = aceleracion;
    }

    public Object[] getArray() {
        Object[] array = {this.distancia, this.tiempo, this.velocidad, this.aceleracion};
        return array;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getAceleracion() {
        return aceleracion;
    }

    public void setAceleracion(double aceleracion) {
        this.aceleracion = aceleracion;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Lanzamiento{" + "distancia=" + distancia + ", tiempo=" + tiempo + ", velocidad=" + velocidad + ", aceleracion=" + aceleracion + '}';
    }
    
    
}
