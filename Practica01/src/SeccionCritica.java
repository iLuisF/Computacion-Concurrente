/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luis
 */
public class SeccionCritica {
    
    int suma = 0;
    
    void incrementar(){
        suma++;
    }
    
    public int get(){
        return suma;
    }
    /*
    public int getSeccionCritica(){                
        for(int i = 0; i < 1000; i++){
            suma = suma + i;
        }        
        return suma;
    }
    */
    
}
