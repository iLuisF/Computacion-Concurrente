
/**
 * Se utilizan los tres algoritmos de exclusión mutua para dos procesos vistos
 * en clase: Dekker, Peterson y Kessels. Es importante aclarar que el
 * identificador de cada hilo depende del algoritmo, es decir, el identificador
 * es un entero(0 o 1) junto al nombre del algoritmo. Ejemplo: Hilo 0 algoritmo
 * de dekker, Hilo 0 algoritmo de Peterson. Estos son dos hilos diferentes.
 *  
 * @author Flores González Luis.
 */
public class Main {
        
    static Dekker dekker = new Dekker();
    static Peterson peterson = new Peterson();
    static Kessels kessels = new Kessels();
    static SeccionCritica seccionCriticaDekker = new SeccionCritica();
    static SeccionCritica seccionCriticaPeterson = new SeccionCritica();
    static SeccionCritica seccionCriticaKessels = new SeccionCritica();

    public static void main(String[] args) throws InterruptedException {

        //Hilos aplicando exclución mutua con el algoritmo de Dekker.
        Thread hilo1 = new Thread(new Runnable() {
            @Override
            public void run() {                
                dekker.comenzar(0, seccionCriticaDekker);
            }
        });
        hilo1.start();
        Thread hilo2 = new Thread(new Runnable() {
            @Override
            public void run() {                
                dekker.comenzar(1, seccionCriticaDekker);
            }
        });
        hilo2.start();
        
        hilo1.join();
        hilo2.join();
        System.out.println("Suma de los primeros 1000 números naturales de 20 "
                + "iteraciones por cada hilo: " + seccionCriticaDekker.getSuma());
        
        //Hilos aplicando exclución mutua con el algoritmo de Peterson.
        Thread hilo3 = new Thread(new Runnable() {
            @Override
            public void run() {                
                peterson.comenzar(0, seccionCriticaPeterson);
            }
        });
        hilo3.start();
        Thread hilo4 = new Thread(new Runnable() {
            @Override
            public void run() {                
                peterson.comenzar(1, seccionCriticaPeterson);
            }
        });
        hilo4.start();
        
        hilo3.join();
        hilo4.join();
        System.out.println("Suma de los primeros 1000 números naturales de 20 "
                + "iteraciones por cada hilo: " + seccionCriticaPeterson.getSuma());

        //Hilos aplicando exclución mutua con el algoritmo de Kessels.
        Thread hilo5 = new Thread(new Runnable() {
            @Override
            public void run() {                
                kessels.comenzar(0, seccionCriticaKessels);
            }
        });
        hilo5.start();
        Thread hilo6 = new Thread(new Runnable() {
            @Override
            public void run() {                
                kessels.comenzar(1, seccionCriticaKessels);
            }
        });
        hilo6.start();
        
        hilo5.join();
        hilo6.join();
        System.out.println("Suma de los primeros 1000 números naturales de 20 "
                + "iteraciones por cada hilo: " + seccionCriticaKessels.getSuma());

    }

}
