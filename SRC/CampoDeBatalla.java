package SRC;

public class CampoDeBatalla {

    private char[][] campo;
    private int navefil, navecol;
    private String nombreNave;

    private Enemigo[] enemigos = new Enemigo[3];
    private Proyectil[] proyectiles = new Proyectil[10];           // proyectiles de la nave
    private Proyectil[] proyectilesEnemigo = new Proyectil[20];    // proyectiles de todos los enemigos

    public CampoDeBatalla(String nombreNave) {
        this.nombreNave = nombreNave;
        campo = new char[20][40];

        llenarCampoVacio();

        navefil = campo.length - 1;
        navecol = campo[0].length / 2;

        // Crear los 5 enemigos en fila superior separados horizontalmente
        for (int i = 0; i < enemigos.length; i++) {
            int col = 5 + i * 12; // separa mejor a 3 enemigos en campo de 40 columnas
            enemigos[i] = new Enemigo("Alien", 80, 7, 0, col);
        }

        actualizarCampo();
    }

    private void llenarCampoVacio() {
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[0].length; j++) {
                campo[i][j] = ' ';
            }
        }
    }

    private void dibujarProyectiles() {
        for (Proyectil p : proyectiles) {
            if (p != null && p.estaActivo()) {
                int f = p.getFila();
                int c = p.getColumna();
                if (f >= 0 && f < campo.length && c >= 0 && c < campo[0].length) {
                    campo[f][c] = '*';
                }
            }
        }

        for (Proyectil p : proyectilesEnemigo) {
            if (p != null && p.estaActivo()) {
                int f = p.getFila();
                int c = p.getColumna();
                if (f >= 0 && f < campo.length && c >= 0 && c < campo[0].length) {
                    campo[f][c] = '!';
                }
            }
        }
    }

    private void actualizarCampo() {
        llenarCampoVacio();
        for (Enemigo e : enemigos) {
            if (e.estaVivo()) {
                campo[e.getFila()][e.getColumna()] = 'E';
            }
        }
        campo[navefil][navecol] = 'N';
        dibujarProyectiles();
    }

    public void mostrarCampo() {
        actualizarCampo();
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[0].length; j++) {
                System.out.print(campo[i][j]);
            }
            System.out.println();
        }
    }

    public void moverNave(char direccion) {
        switch (direccion) {
            case 'w':
                if (navefil > 0) {
                    navefil--;
                    System.out.println(nombreNave + " se mueve hacia arriba.");
                }
                break;
            case 's':
                if (navefil < campo.length - 1) {
                    navefil++;
                    System.out.println(nombreNave + " se mueve hacia abajo.");
                }
                break;
            case 'a':
                if (navecol > 0) {
                    navecol--;
                    System.out.println(nombreNave + " se mueve hacia la izquierda.");
                }
                break;
            case 'd':
                if (navecol < campo[0].length - 1) {
                    navecol++;
                    System.out.println(nombreNave + " se mueve hacia la derecha.");
                }
                break;
            default:
                System.out.println("Usa solo las teclas [W, A, S, D] para moverte.");
                break;
        }
    }

    public void dispararDesdeNave(int daño) {
        for (int i = 0; i < proyectiles.length; i++) {
            if (proyectiles[i] == null || !proyectiles[i].estaActivo()) {
                proyectiles[i] = new Proyectil(navefil - 1, navecol, daño, -1);
                System.out.println("Proyectil disparado desde la nave");
                break;
            }
        }
    }

    public void dispararDesdeEnemigo(int fila, int columna, int daño) {
        for (int i = 0; i < proyectilesEnemigo.length; i++) {
            if (proyectilesEnemigo[i] == null || !proyectilesEnemigo[i].estaActivo()) {
                proyectilesEnemigo[i] = new Proyectil(fila + 1, columna, daño, +1);
                System.out.println(" Un enemigo ha disparado un proyectil");
                break;
            }
        }
    }

    public void moverProyectiles() {
        for (Proyectil p : proyectiles) {
            if (p != null && p.estaActivo()) {
                p.mover();
                int f = p.getFila();
                int c = p.getColumna();

                for (Enemigo e : enemigos) {
                    if (e.estaVivo() && f == e.getFila() && c == e.getColumna()) {
                        e.recibirDisparo(p.getDaño());
                        System.out.println(" Proyectil impactó al enemigo en (" + f + ", " + c + ")");
                        p.desactivar();
                    }
                }
            }
        }
    }

   public void moverProyectilesEnemigo(Nave nave) {
    for (Proyectil p : proyectilesEnemigo) {
        if (p != null && p.estaActivo()) {
            p.mover();
            int f = p.getFila();
            int c = p.getColumna();

            //  impacto de bala 
            
            if (f == navefil && c == navecol) {
                System.out.println(" ¡Proyectil enemigo impactó en la nave!");
                nave.recibirDaño(p.getDaño());
                p.desactivar();
            }
        }
    }
}


    public void moverEnemigos() {
        for (Enemigo e : enemigos) {
            if (e.estaVivo()) {
                e.moverAleatoriamente(campo.length / 2, campo[0].length); // solo hasta mitad del campo
            }
        }
    }

    public void enemigosDisparan() {
        for (Enemigo e : enemigos) {
            if (e.estaVivo() && Math.random() < 0.3) {
                dispararDesdeEnemigo(e.getFila(), e.getColumna(), e.getDaño());
            }
        }
    }

    public Enemigo[] getEnemigos() {
        return enemigos;
    }

    public boolean hayColision() {
        for (Enemigo e : enemigos) {
            if (e.estaVivo() && navefil == e.getFila() && navecol == e.getColumna()) {
                return true;
            }
        }
        return false;
    }
    
}
