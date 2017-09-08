
/**
 * Sección critica que suma iterativamente los primeros mil números naturales
 * cada vez que se ingresa a esta.
 *
 * Si se ingresa dos veces o más a la misma instancia entonces la suma se va
 * acumalando, es decir, no se reinicia la variable suma.
 *
 * @author Flores González Luis.
 */
public class SeccionCritica {

    int suma = 0;

    /**
     * Ingresa a la sección critica y suma los primeros mil números naturales.
     */
    void ingresar() {
        for (int i = 0; i < 1000; i++) {
            suma = suma + i;
        }
    }

    /**
     * Devuelve la suma de los primeros mil números naturales.
     *
     * @return Suma de los primeros mil números naturales.
     */
    public int getSuma() {
        return suma;
    }
}
