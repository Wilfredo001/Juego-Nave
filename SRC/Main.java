package SRC;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int puntosUltimaPartida = 0;

        while (true) {
            mostrarMenu();

            System.out.print("Elige una opción: ");
            String opcion = sc.nextLine();

            if (opcion.equals("1")) {
                System.out.print("Ingresa el nombre de tu nave: ");
                String nombreNave = sc.nextLine();

                // Crear objetos
                Nave jugador = new Nave(nombreNave, 35, 10);
                CampoDeBatalla campo1 = new CampoDeBatalla(nombreNave);

                // Mostrar info inicial
                System.out.println("\n ESTADÍSTICAS INICIALES:");
                System.out.println(" Nave: " + jugador.getNombre() + " | Vida: " + jugador.getVida() + " | Daño: " + jugador.getDaño());
                System.out.println(" Enemigos: 5 x Alien (Vida: 200 | Daño: 10)");
                System.out.println("\n¡Batalla iniciada!\n");

                // Bucle principal de batalla
                while (jugador.estaVivo()) {
                    campo1.mostrarCampo();
                    System.out.println(" Puntos actuales: " + campo1.getPuntos());


                    System.out.println("\nControles: [W/A/S/D] Mover | [F] Atacar | [M] Menú de pausa");

                    System.out.print("Acción: ");
                    char accion = sc.next().toLowerCase().charAt(0);
                    sc.nextLine(); // limpiar buffer

                    if (accion == 'm') {
                        while (true) {
                            System.out.println("╔════════════ PAUSA ═════════════╗");
                            System.out.println("║ 1. Continuar                   ║");
                            System.out.println("║ 2. +11 vida por -4 puntos      ║");
                            System.out.println("║ 3. Regresar al menu principal  ║");
                            System.out.println("╚════════════════════════════════╝");
                            System.out.print("Elige una opción: ");
                            String pausaOpcion = sc.nextLine();

                            if (pausaOpcion.equals("1")) {
                                System.out.println("Reanudando partida...");
                                break;
                            } else if (pausaOpcion.equals("2")) {
                                if (campo1.restarPuntos(4)) {
                                    int vidaAntes = jugador.getVida();
                                    jugador.recibirDaño(-11); // curar
                                    int vidaDespues = jugador.getVida();

                                    System.out.println("Has recibido +11 de vida.");
                                    System.out.println("Vida antes: " + vidaAntes + "  Vida actual: " + vidaDespues);
                                    System.out.println("Puntos restantes: " + campo1.getPuntos());
                                    System.out.println("Vida actual de " + jugador.getNombre() + ": " + jugador.getVida());
                                } else {
                                    System.out.println("No tienes suficientes puntos. Necesitas al menos 4.");
                                }
                            } else if (pausaOpcion.equals("3")) {
                                puntosUltimaPartida = campo1.getPuntosAcumulados();
                                System.out.println("Juego finalizado por el jugador desde pausa.");
                                System.out.println("Puntaje obtenido en esta partida: " + puntosUltimaPartida);
                                jugador.recibirDaño(9999); // forzar fin
                                break;
                            } else {
                                System.out.println("Opción inválida.");
                            }
                        }
                        continue;
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

                    if (campo1.getEnemigosMuertos() >= 8) {
                        System.out.println("\n--- ¡Ronda completada! Has derrotado a todos los enemigos. ---");
                        System.out.println("Puntos obtenidos en esta ronda: " + campo1.getPuntosAcumulados());

                        puntosUltimaPartida = campo1.getPuntosAcumulados();
                        System.out.println("Puntaje obtenido en esta partida: " + puntosUltimaPartida); // <== AGREGADO
                        break;
                    }

                    // Colisión entre nave y enemigo
                    if (campo1.hayColision()) {
                        for (Enemigo e : campo1.getEnemigos()) {
                            if (e.estaVivo() && e.getFila() == campo1.getNaveFila() && e.getColumna() == campo1.getNaveColumna()) {
                                e.colisionarConNave(jugador);
                                break; // solo colisiona con uno
                            }
                        }
                    }
                }

                    if (!jugador.estaVivo()) {
                        puntosUltimaPartida = campo1.getPuntosAcumulados(); 
                        System.out.println("Has sido derrotado. ¡Game Over!");
                        System.out.println("Puntaje obtenido en esta partida: " + puntosUltimaPartida); // <== AGREGADO
                        continue;
                    }


                continue;

            } else if (opcion.equals("2")) {
                System.out.println("Hasta luego. Gracias por jugar.");
                break;

            } else if (opcion.equals("3")) {
                System.out.println(" PUNTUACIONES:");
                System.out.println(" - Última partida: " + puntosUltimaPartida);
                System.out.println(" - Puntos acumulados globales: " + CampoDeBatalla.getPuntosAcumuladosGlobales());

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
        System.out.println("║  3. Ver puntuaciones              ║");   
        System.out.println("╚═══════════════════════════════════╝");
    }
}
