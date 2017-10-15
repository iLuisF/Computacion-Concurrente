
package concurrente;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Permite o restringe a los procesos/hilos el <strong>acceso</strong> a algún
 * recurso compartido.
 *
 * @author Flores González Luis.
 */
public class SemaforoGeneral {

    //Determina si un hilo puede ingresar al recurso compartido, dependiendo
    //de la cota propuesta en el costructor.
    private int contador = 0;
    //Guarda el orden en que los hilos requerian el acceso al recurso, para
    //cuando esté este disponible el hilo se ejecute.
    private final Queue<Thread> proceso = new LinkedList<>();

    /**
     * Construye un semaforo, ya sea binario o general.
     * 
     * @param contador Si es 1, el semaforo sera binario en otro caso sera
     * general.
     */
    public SemaforoGeneral(int contador) {
        this.contador = contador;
    }

    /**
     * Bloquea el hilo/proceso si el semáforo es igual que cero y lo encola,
     * sino entonces permite al hilo/proceso continuar. Decrementa en una unidad
     * el semaforo. ´
     *
     * Además a este método se le es conocido también como wait, down o espera.
     *
     * @throws InterruptedException
     */
    public synchronized void esperar() throws InterruptedException {
        if (this.contador == 0) {
            wait();
            //Thread.currentThread().suspend();
            proceso.add(Thread.currentThread());
        } else {
            this.contador--;
        }
    }

    /**
     * El hilo que llamo a signal() permitira entregar su señal, si el
     * SemaforoGeneral ha alcanzado su limite. Incrementa el semáforo en uno y
     * si hay algún proceso esperando lo despierta. 
     * 
     * Además a este método se le es conocido también como signal, up o señal.
     *
     */
    public synchronized void signal() {
        if (!proceso.isEmpty()) {
            proceso.remove().notify();
        } else {
            this.contador++;
            //this.notify();
        }
    }

    /**
     * Regresa el conteo actual del contador.
     *
     * @return Entero que representa al conteo actual de los permismos para
     * acceder.
     */
    public int getConteo() {
        return contador;
    }
}
