package SRC;

public class CampoDeBatalla {

    private char[][] campo;

    public CampoDeBatalla() {
        campo = new char[10][20];  // 10 filas, 20 columnas
        llenarCampoVacio();
        colocarElementos();
    }

    private void llenarCampoVacio() {
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[0].length; j++) {
                campo[i][j] = ' ';
            }
        }
    }

    private void colocarElementos() {
        // Colocar enemigo arriba al centro
        campo[0][10] = 'E';

        // Colocar nave abajo al centro
        campo[campo.length - 1][10] = 'N';

        // (Opcional) proyectiles estáticos
        campo[1][10] = '|';
        campo[2][10] = '|';
        campo[3][10] = '|';
    }

    public void mostrarCampo() {
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[0].length; j++) {
                System.out.print(campo[i][j]);
            }
            System.out.println();
        }
    }
}
