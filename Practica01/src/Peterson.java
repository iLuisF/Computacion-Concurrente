
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Algoritmo de Peterson para dos hilos. Donde en cada hilo se iteran 20 veces,
 * compitiendo en cada iteración para entrar a su sección critica y utilizando
 * el algoritmo de Peterson para manejar la exclusión mutua.
 *
 * @author Flores González Luis.
 */
public class Peterson {

    //1 es true, 0 es false
    private final AtomicIntegerArray flag = new AtomicIntegerArray(2);
    private volatile int turno;

    /**
     * Inicializa los valores requeridos para el funcionamiento del algoritmo de
     * Peterson.
     */
    public Peterson() {
        this.flag.set(0, 0);
        this.flag.set(0, 0);
    }

    /**
     * Comienza la ejecución del algoritmo de Peterson.
     *
     * @param id Identificador del hilo con el que se ejecutara el algoritmo,
     * este solo puede ser 0 o 1.
     * @param seccionCritica Sección critica.
     */
    public void comenzar(int id, SeccionCritica seccionCritica) {
        this.flag.set(id, 1);
        this.turno = (id + 1) % 2;
        while (this.flag.get((id + 1) % 2) == 1 && this.turno == (id + 1) % 2) {
            //Esperar.
        }
        //Inicio sección critica.
        System.out.println("Comenzo sección critica del algoritmo de Peterson "
                + "del hilo: " + id);
        for (int i = 0; i < 20; i++) {
            seccionCritica.ingresar();
        }
        System.out.println("Finalizo sección critica del algoritmo de Peterson "
                + "del hilo: " + id);
        //Fin sección critica. 
        this.flag.set(id, 0);
    }
}
