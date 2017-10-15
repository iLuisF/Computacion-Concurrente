
/**
 * El usuario indicara el número n de hilos a ejecutar, para luego lanzar n
 * hilos iguales que iteren 10 veces cada uno, utilizando en cada iteración el 
 * semáforo implementado para administrar la entrada a la sección critica.
 *
 * @author Flores González Luis.
 */
public class Main {

    //El usuario/programador debera cambiar el número de hilos a ejecutar.
    static final int NUM_HILOS = 2;

    public static void main(String[] args) throws InterruptedException {

        //Hilos que el usuario/programador decidio lanzar.
        for (int i = 0; i < NUM_HILOS; i++) {            
            Thread hilo = new Thread(new Hilo(String.valueOf(i)));
            hilo.start();
            hilo.sleep(1000);
            //hilo.join();
        }
    }

}
