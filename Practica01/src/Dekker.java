
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *
 * @author Flores González Luis.
 */
public class Dekker {

    private volatile int turno;
    private final AtomicIntegerArray flag = new AtomicIntegerArray(2);
            
    public Dekker(){
        this.turno = 0;               
        this.flag.set(0, 0);//(indice, valor)
        this.flag.set(1, 1);        
    }
    
    public void comenzar(int id, SeccionCritica seccionCritica){                
        //Sección no critica.
        int otro = (id+1) % 2;        
        this.flag.set(id, 1);        
        while(this.flag.get(otro) == 1){
            if(this.turno == otro){                
                this.flag.set(id, 0);
                while(this.turno != id){
                    //Esperar
                }                
                this.flag.set(id, 1);
            }           
        }                
        //Inicio sección critica.
        for(int i = 0; i < 500; i++){
            seccionCritica.incrementar();
        }        
        //Fin sección critica.        
        this.turno = otro;        
        this.flag.set(id, 0);
        //Sección no critica.
    }        
}
