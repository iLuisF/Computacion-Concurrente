
/**
 * Recurso que se compartira en los diferentes hilos y se accedera a el a traves
 * de la sección critica.
 *
 * @author Flores González Luis.
 */
public class Recurso {

    /**
     * Se accede al recurso compartido, cada vez que se hace esto se imprime que
     * se accedio y que hilo lo hizo además del contador del semaforo
     * correspondiente.
     *
     * @param identificador Identificador del hilo que ingreso a la sección
     * critica y de esta manera logro acceder al recurso compartido.
     * @param contadorSemaforo El número del contador del semaforo que se uso
     * para administrar los hilos.
     */
    public void acceder(int identificador, int contadorSemaforo) {
        System.out.println("El hilo con identificador " + identificador
                + " ingreso al recurso compartido con el número del contador "
                + "actual " + contadorSemaforo + " del semaforo.");
    }

}
