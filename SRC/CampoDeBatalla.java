package SRC;

public class CampoDeBatalla {

    private char[][] campo;
    private int navefil, navecol; 
    private int enemigofil, enemigocol;
    private String nombreNave;

    public CampoDeBatalla(String nombreNave) {
        this.nombreNave = nombreNave;
        campo = new char[10][20];  // 10 filas, 20 columnas
        llenarCampoVacio();
        
        //posicionar nave
        navefil = campo.length -1;
        navecol = campo[0].length /2;

        //posicionar enemigo 
        enemigofil = 0;
        enemigocol = campo[0].length/2;

        actualizarCampo();
    }

    private void llenarCampoVacio() {
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[0].length; j++) {
                campo[i][j] = ' ';
            }
        }
    }

    private void actualizarCampo() {
        llenarCampoVacio();
        campo[enemigofil][enemigocol] = 'E';
        campo[navefil][navecol] = 'N';
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
}
