
/**
 *
 * @author Luis
 */
public class Main {

    static int i = 0;
    static Dekker dekker = new Dekker();
    static SeccionCritica seccionCritica = new SeccionCritica();

    public static void main(String[] args) throws InterruptedException {

        Thread hilo1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Corriendo: Hilo 1");
                dekker.comenzar(0, seccionCritica);
            }
        });
        hilo1.start();
        Thread hilo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Corriendo: Hilo 2");
                dekker.comenzar(1, seccionCritica);
            }
        });
        hilo2.start();

        hilo1.join();
        hilo2.join();

        System.out.println("Valor final: " + seccionCritica.get());
    }

}
