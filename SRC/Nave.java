package SRC;

public class Nave {

    // Atributos 
    private String nombre;
    private int vida;
    private int daño;

    // Constructor 
    public Nave(String nombre, int vida, int daño){
        this.nombre = nombre;
        this.vida = vida;
        this.daño = daño;
    }

    // Método atacar enemigo 
    public void atacar(Enemigo enemigo){
        System.out.println(nombre + " ataca al enemigo " + enemigo.getTipo());
        enemigo.recibirDisparo(daño);
    }

    // Método recibir daño 
public void recibirDaño(int cantidad) {
    if (cantidad > 0) {
        vida -= cantidad;
        System.out.println(nombre + " recibe " + cantidad + " de daño. Vida restante: " + vida);
    } else {
        vida -= cantidad; // negativo, así que suma
        System.out.println(nombre + " recupera " + (-cantidad) + " de vida. Vida restante: " + vida);
    }
}


    // Método seguir vivo 
    public boolean estaVivo() {
        return vida > 0;
    }

    // Getters
    public int getVida() {
        return vida;
    }

    public String getNombre(){
        return nombre;
    }

    public int getDaño(){
        return daño;
    }
}