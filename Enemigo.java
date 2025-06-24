public class Enemigo {
    
    private int x, y;
    private int salud;

    public Enemigo(int x, int y) {
        this.x = x;
        this.y = y;
        this.salud = 100;
    }

    public void mover(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void recibirDaño(int daño) {
        salud -= daño;
        if (salud < 0) salud = 0;
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    @Override
    public String toString() {
        return "Enemigo en (" + x + ", " + y + ") con salud: " + salud;
    }
}
