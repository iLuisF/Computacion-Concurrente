
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Algoritmo de Kessels para dos hilos.
 *
 * @author Flores González Luis.
 */
public class Kessels {

    //1 es true, 0 es false;
    private final AtomicIntegerArray turn = new AtomicIntegerArray(2);
    private final AtomicIntegerArray b = new AtomicIntegerArray(2);

    /**
     * Inicializa los valores requeridos para el funcionamiento del algoritmo de
     * Kessels.
     */
    public Kessels() {
        //(indice, valor)
        this.b.set(0, 0);
        this.b.set(1, 0);
        this.turn.set(0, 0);
        this.turn.set(1, 0);
    }

    /**
     * Comienza la ejecución del algoritmo de Kessels.
     *
     * @param id Identificador del hilo con el que se ejecutara el algoritmo, 
     * este solo puede ser 0 o 1.
     * @param seccionCritica Sección critica.
     */
    public void comenzar(int id, SeccionCritica seccionCritica) {
        b.set(id, 1);
        AtomicIntegerArray local = new AtomicIntegerArray(2);
        if (id == 0) {
            local.set(id, turn.get(1));
        }
        if (id == 1) {
            local.set(id, 1 - turn.get(0));
        }
        turn.set(id, local.get(id));
        while (b.get(id) == 0 || local.get(id) != (turn.get(1 - id) + id) % 2) {
            //esperar
        }
        //Inicio sección critica.
        for (int i = 0; i < 500; i++) {
            seccionCritica.incrementar();
        }
        //Fin sección critica.      
        b.set(id, 0);
    }

}
