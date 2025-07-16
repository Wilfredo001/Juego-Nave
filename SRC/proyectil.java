package SRC;

public class Proyectil { 
    private int fila;
    private int columna;
    private int daño;
    private int direccion; // -1 para arriba (nave), +1 para abajo (enemigo)
    private boolean activo;

    public Proyectil(int fila, int columna, int daño, int direccion) {
        this.fila = fila;
        this.columna = columna;
        this.daño = daño;
        this.direccion = direccion;
        this.activo = true;
    }

    public void mover() {
        fila += direccion;
        if (fila < 0 || fila >= 20) { // Fuera del campo
            activo = false;
        }
    }

    public boolean estaActivo() {
        return activo;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getDaño() {
        return daño;
    }

    public void desactivar() {
        activo = false;
    }
    
}