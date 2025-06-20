 package SRC;

public class Nave {

    //Atributos 

    private String nombre;
    private int vida;
    private int daño;

    //constructor 

    public Nave(String nombre, int vida, int daño){
        this.nombre = nombre;
        this.vida = vida;
        this.daño = daño;
    }
    
    //metodos atacar enemigo 

   /*  FALTA CLASE ENEMIGO PARA QUE ESTE NO DE ERROR
   public void atacar(Enemigo enemigo){

        System.out.println(nombre+"ataca al enemigo"+ enemigo.getTipo());
        enemigo.recibirDisparo(daño);

    }*/

    //metodo recibir daño 

    public void recibirDaño(int cantidad){
        vida -= cantidad;

        if(vida < 0 ){
            vida = 0 ;
        }
        System.out.println(nombre +" recive "+ cantidad +"de daño. Vida restante "+ vida);
    }

    //metodo seguir vivo 

    public boolean estaVivo() {
        return vida > 0 ; //se usa en main

    }
}