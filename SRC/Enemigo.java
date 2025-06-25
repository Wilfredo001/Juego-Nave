package SRC;

public class Enemigo {

    // Atributos
    private String tipo;
    private int vida;
    private int daño;

    // Constructor
    public Enemigo(String tipo, int vida, int daño) {
        this.tipo = tipo;
        this.vida = vida;
        this.daño = daño;
    }

    // Método para recibir daño de la nave
    public void recibirDisparo(int cantidad) {
        vida -= cantidad;
        if (vida < 0) {
            vida = 0;
        }
        System.out.println(tipo + " recibe " + cantidad + " de daño. Vida restante: " + vida);
    }

    // Método para atacar a la nave
    public void atacar(Nave nave) {
        System.out.println(tipo + " ataca a la nave con " + daño + " de daño.");
        nave.recibirDaño(daño);
    }

    // Método para saber si sigue vivo
    public boolean estaVivo() {
        return vida > 0;
    }

    // Getter para el tipo (usado en Nave)
    public String getTipo() {
        return tipo;
    }

    // Getter para la vida (opcional)
    public int getVida() {
        return vida;
    }

    
}