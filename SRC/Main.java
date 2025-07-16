package SRC;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            mostrarMenu();

            System.out.print("Elige una opción: ");
            String opcion = sc.nextLine();

            if (opcion.equals("1")) {
                System.out.print("Ingresa el nombre de tu nave: ");
                String nombreNave = sc.nextLine();

                // Crear objetos
                Nave jugador = new Nave(nombreNave, 200, 10);
                CampoDeBatalla campo1 = new CampoDeBatalla(nombreNave);

                // Mostrar info inicial
                System.out.println("\n ESTADÍSTICAS INICIALES:");
                System.out.println(" Nave: " + jugador.getNombre() + " | Vida: " + jugador.getVida() + " | Daño: " + jugador.getDaño());
                System.out.println(" Enemigos: 5 x Alien (Vida: 200 | Daño: 7)");
                System.out.println("\n¡Batalla iniciada!\n");

                // Bucle principal de batalla
                while (jugador.estaVivo()) {
                    campo1.mostrarCampo();

                    System.out.println("\nControles: [W/A/S/D] Mover | [F] Atacar | [X] Salir");
                    System.out.print("Acción: ");
                    char accion = sc.next().toLowerCase().charAt(0);
                    sc.nextLine(); // limpiar buffer

                    if (accion == 'x') {
                        System.out.println("Juego finalizado por el jugador.");
                        break;
                    }

                    if (accion == 'f') {
                        campo1.dispararDesdeNave(jugador.getDaño());
                    } else {
                        campo1.moverNave(accion);
                    }

                    campo1.moverEnemigos();
                    campo1.moverProyectiles();

                    campo1.enemigosDisparan();
                    campo1.moverProyectilesEnemigo(jugador);

                    if (campo1.hayColision()) {
                        for (Enemigo e : campo1.getEnemigos()) {
                            if (e.estaVivo() && e.getFila() == campo1.getEnemigos()[0].getFila() && e.getColumna() == campo1.getEnemigos()[0].getColumna()) {
                                e.colisionarConNave(jugador);
                            }
                        }
                    }

                    // Ataque de todos los enemigos
                    for (Enemigo e : campo1.getEnemigos()) {
                        if (e.estaVivo()) {
                          
                        }
                    }

                    // Verificar si ya ganaste
                    boolean todosMuertos = true;
                    for (Enemigo e : campo1.getEnemigos()) {
                        if (e.estaVivo()) {
                            todosMuertos = false;
                            break;
                        }
                    }

                    if (todosMuertos) {
                        System.out.println("¡Has derrotado a todos los enemigos!");
                        break;
                    }
                }

                if (!jugador.estaVivo()) {
                    System.out.println("Has sido derrotado. ¡Game Over!");
                }

                break;

            } else if (opcion.equals("2")) {
                System.out.println("Hasta luego. Gracias por jugar.");
                break;

            } else {
                System.out.println("Opción no válida. Intenta nuevamente.\n");
            }
        }

        sc.close();
    }

    public static void mostrarMenu() {
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("║           JUEGO DE BATALLA        ║");
        System.out.println("╠═══════════════════════════════════╣");
        System.out.println("║  1. Iniciar partida               ║");
        System.out.println("║  2. Salir del juego               ║");
        System.out.println("╚═══════════════════════════════════║");
    }
}