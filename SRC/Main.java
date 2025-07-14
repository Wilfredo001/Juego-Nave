package SRC;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            mostrarMenu();

            System.out.print("\nSelecciona una opción [1 = Iniciar | 2 = Salir]: ");
            String opcion = sc.nextLine();

            if (opcion.equals("1")) {
                System.out.print("Ingresa el nombre de tu nave: ");
                String nombreNave = sc.nextLine();

                // Crear objetos
                Nave jugador = new Nave(nombreNave, 100, 10);
                Enemigo enemigo = new Enemigo("Alien", 80, 7, 0, 10);
                CampoDeBatalla campo1 = new CampoDeBatalla(nombreNave);

                // Mostrar info inicial
                System.out.println("\n ESTADÍSTICAS INICIALES:");
                System.out.println(" Nave: " + jugador.getNombre() + " | Vida: " + jugador.getVida() + " | Daño: " + jugador.getDaño());
                System.out.println(" Enemigo: " + enemigo.getTipo() + " | Vida: " + enemigo.getVida() + " | Daño: " + enemigo.getDaño());
                System.out.println("\n¡Batalla iniciada!\n");

                // Bucle principal de batalla
                while (jugador.estaVivo() && enemigo.estaVivo()) {
                    campo1.mostrarCampo();

                    System.out.println("\nControles: [W/A/S/D] Mover | [F] Atacar | [X] Salir");
                    System.out.print("Acción: ");
                    char accion = sc.next().toLowerCase().charAt(0);

                    if (accion == 'x') {
                        System.out.println(" Juego finalizado por el jugador.");
                        break;
                    }

                    if (accion == 'f') {
                        jugador.atacar(enemigo);
                    } else {
                        campo1.moverNave(accion);
                    }

                    campo1.moverEnemigo();

                   /*  if (campo1.hayColision()) {
                       campo1.getEnemigo().colisionarConNave(jugador);
                    } */

                    if (campo1.getEnemigo().estaVivo()) {
                        campo1.getEnemigo().atacar(jugador);
                    }
                }

                if (!jugador.estaVivo()) {
                    System.out.println(" Has sido derrotado. ¡Game Over!");
                } else if (!campo1.getEnemigo().estaVivo()) {
                    System.out.println(" ¡Has derrotado al enemigo!");
                }

                break; // termina juego después de la batalla
            } else if (opcion.equals("2")) {
                System.out.println(" Hasta luego. Gracias por jugar.");
                break;
            } else {
                System.out.println(" Opción no válida. Intenta nuevamente.");
            }
        }

        sc.close();
    }

    public static void mostrarMenu() {
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("║          JUEGO DE BATALLA         ║");
        System.out.println("╠═══════════════════════════════════╣");
        System.out.println("║  1. Iniciar partida               ║");
        System.out.println("║  2. Salir del juego               ║");
        System.out.println("╚═══════════════════════════════════╝");
    }
}
