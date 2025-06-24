import java.util.ArrayList;
import java.util.List;

public class Enemigo {
    class Misil {
    private int x, y;
    private int velocidad;

    public Misil(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocidad = 10;
    }

    public void mover() {
        y += velocidad; // Suponiendo que el misil se mueve hacia abajo
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public String toString() {
        return "Misil en (" + x + ", " + y + ")";
    }
}
    private int x, y;
    private int salud;
    private List<Misil> misiles;

    public Enemigo(int x, int y) {
        this.x = x;
        this.y = y;
        this.salud = 100;
        this.misiles = new ArrayList<>();
    }

    public void mover(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void disparar() {
        Misil nuevoMisil = new Misil(x, y);
        misiles.add(nuevoMisil);
        System.out.println("¡Enemigo lanzó un misil desde (" + x + ", " + y + ")!");
    }

    public void actualizarMisiles() {
        for (Misil m : misiles) {
            m.mover();
        }
    }

    public void recibirDaño(int daño) {
        salud -= daño;
        if (salud < 0) salud = 0;
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    public List<Misil> getMisiles() {
        return misiles;
    }

    @Override
    public String toString() {
        return "Enemigo en (" + x + ", " + y + ") con salud: " + salud;
    }
}