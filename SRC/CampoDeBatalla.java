package SRC;


public class CampoDeBatalla {

    private char[][] campo;
    private int navefil, navecol; 
    private int enemigofil, enemigocol;
    private String nombreNave;
    private Enemigo enemigo;
    private Proyectil[] proyectiles = new Proyectil[10]; // máximo 10 proyectiles activos
  



    public CampoDeBatalla(String nombreNave) {
        this.nombreNave = nombreNave;
        campo = new char[20][40];  // filas y columnas 
        llenarCampoVacio();
        
        //posicionar nave
        navefil = campo.length -1;
        navecol = campo[0].length /2;

        //posicionar enemigo 
        enemigofil = 0;
        enemigocol = campo[0].length/2;

        enemigo = new Enemigo("Alien", 80, 7, enemigofil, enemigocol);

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
}


    private void actualizarCampo() {
        llenarCampoVacio();
        if (enemigo.estaVivo()) {
            campo[enemigo.getFila()][enemigo.getColumna()] = 'E'; // usa la posición del enemigo real
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

    //metodo para mover la nave (con mensajes
    public void moverNave(char direccion){
        switch (direccion) {
            case 'w':
              if(navefil > 0){
                  navefil--;
                  System.out.println(nombreNave + " se mueve hacia arriba.");
              }
              break;

            case 's': 
              if (navefil < campo.length -1){
                  navefil++;
                  System.out.println(nombreNave + " se mueve hacia abajo.");
              }
              break;

            case 'a':
              if(navecol > 0){
                  navecol--;
                  System.out.println(nombreNave + " se mueve hacia la izquierda.");
              }
              break;

            case 'd':
              if(navecol < campo[0].length -1){
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
            System.out.println(" Proyectil disparado desde la nave");
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
            if (f == enemigo.getFila() && c == enemigo.getColumna()) {
                enemigo.recibirDisparo(p.getDaño());
                p.desactivar();
            }
        }
    }
}
 

    public void moverEnemigo() {
        enemigo.moverAleatoriamente(campo.length, campo[0].length);
    }

    // Getter para que el Main acceda al enemigo real
    public Enemigo getEnemigo() {
        return enemigo;
    }
    
    public boolean hayColision() {
    return navefil == enemigo.getFila() && navecol == enemigo.getColumna();
    }


}

    
