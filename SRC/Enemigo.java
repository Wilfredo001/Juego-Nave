package SRC;

import java.util.Random;

public class Enemigo {

    // atributos
    private String tipo;
    private int vida;
    private int daño;
    private int fila;
    private int columna;

    // constructor
    public Enemigo(String tipo, int vida, int daño, int fila, int columna) {
        this.tipo = tipo;
        this.vida = vida;
        this.daño = daño;
        this.fila = fila;
        this.columna = columna;
    }

    // método para recibir daño de la nave
    public void recibirDisparo(int cantidad) {
        vida -= cantidad;
        if (vida < 0) {
            vida = 0;
        }
        System.out.println(tipo + " recibe " + cantidad + " de daño. Vida restante: " + vida);
    }

    // método para atacar a la nave
    public void atacar(Nave nave) {
        System.out.println(tipo + " ataca a la nave con " + daño + " de daño.");
        nave.recibirDaño(daño);
    }

    // método para saber si sigue vivo
    public boolean estaVivo() {
        return vida > 0;
    }

    // getter para el tipo (usado en Nave)
    public String getTipo() {
        return tipo;
    }

    // getter para la vida (opcional)
    public int getVida() {
        return vida;
    }

    public int getFila() {
    return fila;
}

public int getColumna() {
    return columna;
}
public int getDaño(){
    return daño;
}



private Random random = new Random();

public void moverAleatoriamente(int maxFilas, int maxColumnas) {
    int direccion = random.nextInt(4); // 0 = arriba, 1 = abajo, 2 = izquierda, 3 = derecha

    switch (direccion) {
        case 0: // arriba
            if (fila > 0) fila--;
            break;
        case 1: // abajo
            if (fila < maxFilas - 1) fila++;
            break;
        case 2: // izquierda
            if (columna > 0) columna--;
            break;
        case 3: // derecha
            if (columna < maxColumnas - 1) columna++;
            break;
    }

    System.out.println(tipo + " se mueve a (" + fila + ", " + columna + ")");
}

public void explotar(Nave nave) {
    int dañoExplosion = 10;
    System.out.println("#" + tipo + " explota causando " + dañoExplosion + " de daño a la nave.");
    nave.recibirDaño(dañoExplosion);
}

public void colisionarConNave(Nave nave) {
    int dañoColision = 5;
    System.out.println("¡Colisión detectada! Ambos pierden " + dañoColision + " de vida.");
    this.recibirDisparo(dañoColision);
    nave.recibirDaño(dañoColision);

    if (!estaVivo()) {
        explotar(nave);
    }
}

}