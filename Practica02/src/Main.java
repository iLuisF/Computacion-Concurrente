
/**
 * El usuario indicara el número n de hilos a ejecutar, para luego lanzar n
 * hilos iguales que iteren 10 veces cada uno, utilizando en cada iteración el
 * semáforo implementado para administrar la entrada al recurso compartido.
 *
 * @author Flores González Luis.
 * @see Hilo, Recurso, SemaforoGeneral
 */
public class Main {

    //El usuario/programador debera cambiar el número de hilos a ejecutar.
    static final int NUM_HILOS = 2;
    //Arreglo de hilos para facilitar las operaciones de ellos.
    static final Thread[] HILOS = new Thread[NUM_HILOS];
    //Recurso compatido al que se accedera desde varios hilos desde distintas 
    //iteraciones.
    static final Recurso COMPARTIDO = new Recurso();

    /**
     * Se ejecutan n hilos, donde cada hilo accedera 10 veces al recurso
     * compartido. Es decir, n * 10 veces se intentara acceder al recurso
     * compartido.
     *
     * @param args Sin uso.
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        //Hilos que el usuario/programador decidio lanzar(solo los creamos)        .
        for (int i = 0; i < NUM_HILOS; i++) {
            String identificador = String.valueOf(i);
            HILOS[i] = new Thread(new Hilo(COMPARTIDO), identificador);
        }

        //Comenzamos cada hilo y lo ponemos a dormir una décima de segundo.
        for (int i = 0; i < NUM_HILOS; i++) {
            HILOS[i].start();
            HILOS[i].sleep(100);
        }

        //Por buena práctica de programación usamos join().
        for (int i = 0; i < NUM_HILOS; i++) {
            HILOS[i].join();
        }
        
    }
}
