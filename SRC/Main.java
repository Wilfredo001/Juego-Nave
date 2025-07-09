package SRC;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresa el nombre de tu nave: ");
        String nombreNave = sc.nextLine();

        Nave jugador = new Nave(nombreNave, 100, 10);
        Enemigo enemigo = new Enemigo("Alien", 80, 7);
        CampoDeBatalla campo1 = new CampoDeBatalla(nombreNave);

        System.out.println("\n¡Batalla iniciada!");
        System.out.println("Jugador: " + jugador.getNombre() + " | Vida: " + jugador.getVida());
        System.out.println("Enemigo: " + enemigo.getTipo() + " | Vida: " + enemigo.getVida());

        while (jugador.estaVivo() && enemigo.estaVivo()) {
            campo1.mostrarCampo();

            System.out.println("\nUsa [w, a, s, d] para moverte | f = disparar | x = salir");
            System.out.print("Acción: ");
            char accion = sc.next().toLowerCase().charAt(0);

            if(accion == 'x'){
                System.out.println("Saliendo del juego...");
                break;
            }

            if(accion == 'f'){
                jugador.atacar(enemigo);
            } else {
                campo1.moverNave(accion); // ahora muestra el mensaje con el nombre ingresado
            }
        }

        if (!enemigo.estaVivo()) {
            System.out.println("¡Has derrotado al enemigo!");
        }

        sc.close();
    }
}
