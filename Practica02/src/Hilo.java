
import concurrente.SemaforoGeneral;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hilo que tendra como unica tarea acceder al recurso compartido usando un
 * semaforo general, que en este caso sera inicializado como binario.
 *
 * @author Flores González Luis.
 */
public class Hilo implements Runnable {

    //Contador del semaforo que determinara si es binario en caso de que sea 1
    //o si es general en caso de que sea mayor a 1.
    static final int CONTADOR_SEMAFORO = 1;
    //Recurso compartido que se accedera desde la sección critica.    
    static Recurso compartido;
    //Semaforo general, pero en este caso se usara como binario para evitar 
    //conflictos con el recurso compartido y así lograr exclusión mutua.
    static SemaforoGeneral semaforo = new SemaforoGeneral(CONTADOR_SEMAFORO);

    /**
     * Construye el hilo que se lanzara.
     *
     * @param compartido Recurso compartido al que se accedera.
     */
    public Hilo(Recurso compartido) {
        this.compartido = compartido;
    }

    /**
     * Cada hilo accedera al recurso compartido 10 veces, para evitar conflictos
     * se usa un semaforo general inicializado como binario en la sección
     * critica.
     */
    @Override
    public void run() {
        try {
            //Iteraciones que cada hilo intentara acceder al recurso
            //compartido.
            for (int j = 0; j < 10; j++) {
                //Inicio de sección critica.
                System.out.println("Contador del semaforo antes de ingresar a la "
                        + "sección critica: " + semaforo.getConteo());
                semaforo.esperar();
                System.out.println("El hilo ingreso a la sección "
                        + "critica: ");
                compartido.acceder(
                        Integer.parseInt(Thread.currentThread().getName()),
                        semaforo.getConteo());
                System.out.println("El hilo finalizo la sección "
                        + "critica.");
                semaforo.signal();
                //Final de sección critica.
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
