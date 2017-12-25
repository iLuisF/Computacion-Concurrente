
import java.util.concurrent.Semaphore;

/**
 * Resuelve el problema de los fumadores. Es importante leer el reporte.
 * 
 * @author Flores González Luis.
 */
public class Main {

    //Todos los agentes esperaran a semaforoAgente y cada vez que semaforoAgente
    //se le entrega la señal, uno de los agentes despierta y provee ingredientes.    
    public static Semaphore semaforoAgente = new Semaphore(1);

    //Semaforos para los ingredientes que estan disponibles.
    public static Semaphore tabaco = new Semaphore(0);
    public static Semaphore papel = new Semaphore(0);
    public static Semaphore fosforo = new Semaphore(0);

    //Si los ingredientes se encuentran en la mesa.
    boolean hayTabaco = false;
    boolean hayPapel = false;
    boolean hayFosforo = false;

    //Los empujadores usaran los siguientes semaforos para entregar la señal
    //al fumador correspondiente, ejemplo: semaforoTabaco con tabaco.
    public static Semaphore semaforoTabaco = new Semaphore(0);
    public static Semaphore semaforoPapel = new Semaphore(0);
    public static Semaphore semaforoFosforo = new Semaphore(0);

    //Garantiza exclusión mutua.
    public static Semaphore mutex = new Semaphore(1);

    //Número de iteraciones    
    //3 agentes * 5 iteraciones = 15 iteraciones. (Leer reporte).
    public static int ITERACIONES = 5;

    //Tiempo que esperara el fumador.
    public static final int DECIMA_DE_SEGUNDO = 100;

    /**
     * Se inician los 3 empujadores. Se usan tres hilos de ayuda llamados
     * "empujadores" que respondes a las señales del agente, mantienen la cinta
     * de los ingredientes disponible y avisa al fumador apropiado.
     *
     */
    public void iniciarEmpujadores() {
        Thread empujadorA = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < ITERACIONES * 2) {
                    try {
                        tabaco.acquire();
                        System.out.println("Empujador A para tabaco esta activo.");
                        mutex.acquire();
                        try {
                            if (hayPapel) {
                                hayPapel = false;
                                semaforoFosforo.release();
                            } else if (hayFosforo) {
                                hayFosforo = false;
                                semaforoPapel.release();
                            } else {
                                hayTabaco = true;
                            }
                        } finally {
                            mutex.release();
                        }
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                    i++;
                }
            }
        ;
        };
  Thread empujadorB = new Thread() {
      @Override
            public void run() {
                int i = 0;
                while (i < ITERACIONES * 2) {
                    try {
                        papel.acquire();
                        System.out.println("Empujador B para papel esta activo.");
                        mutex.acquire();
                        try {
                            if (hayTabaco) {
                                hayTabaco = false;
                                semaforoFosforo.release();
                            } else if (hayFosforo) {
                                hayFosforo = false;
                                semaforoTabaco.release();
                            } else {
                                hayPapel = true;
                            }
                        } finally {
                            mutex.release();
                        }
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                    i++;
                }
            }
        ;
        };
  Thread empujadorC = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < ITERACIONES * 2) {
                    try {
                        fosforo.acquire();
                        System.out.println("Empujador C para fosforo esta activo.");
                        mutex.acquire();
                        try {
                            if (hayPapel) {
                                hayPapel = false;
                                semaforoTabaco.release();
                            } else if (hayTabaco) {
                                hayTabaco = false;
                                semaforoPapel.release();
                            } else {
                                hayFosforo = true;
                            }
                        } finally {
                            mutex.release();
                        }
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                    i++;
                }
            }
        ;

        };
        empujadorA.start();
        empujadorB.start();
        empujadorC.start();
    }

    /**
     * Iniciara los tres fumadores, de tal manera que cada uno realizara:
     * 
     * 1.Intentara adquirir el semaforo ingrediente asi que el fumador puede
     * empezar solo cuando los ingredientes necesarios estan presentes. Estos
     * seran señalados por los empujadores. 
     * 2. Preparan el cigarro. 
     * 3. Lanzan el semaforo semaforoAgente asi que el agente puede colocar los 
     * ingredientes de nuevo en la mesa. 
     * 4. Empieza a fumar.
     */
    public void iniciarFumadores() {
        Thread fumadorTabaco = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < ITERACIONES) {
                    try {
                        semaforoTabaco.acquire();
                        prepararCigarro();                        
                        fumar();
                        semaforoAgente.release();
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                    i++;
                }
            }

            public void prepararCigarro() {
                System.out.println("fumadorTabaco esta preparando el cigarro.");     
                try {
                    sleep(DECIMA_DE_SEGUNDO);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
                System.out.println("fumadorTabaco acabo de preparar el cigarro.");
            }

            public void fumar() {
                System.out.println("fumadorTabaco esta fumando.");
                try {
                    sleep(DECIMA_DE_SEGUNDO);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
                System.out.println("fumadorTabaco termino de fumar.");
            }
        };

        Thread fumadorFosforo = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < ITERACIONES) {
                    try {
                        semaforoFosforo.acquire();
                        prepararCigarro();                        
                        fumar();
                        semaforoAgente.release();
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                    i++;
                }
            }

            public void prepararCigarro() {
                System.out.println("fumadorFosforo esta preparando el cigarro.");
                 try {
                    sleep(DECIMA_DE_SEGUNDO);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
                System.out.println("fumadorFosforo acabo de preparar el cigarro.");
            }

            public void fumar() {
                System.out.println("fumadorFosforo esta fumando.");
                 try {
                    sleep(DECIMA_DE_SEGUNDO);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
                System.out.println("fumadorFosforo termino de fumar.");
            }
        };

        Thread fumadorPapel = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < ITERACIONES) {
                    try {
                        semaforoPapel.acquire();
                        prepararCigarro();                       
                        fumar();
                        semaforoAgente.release();
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                    i++;
                }
            }

            public void prepararCigarro() {
                System.out.println("fumadorPapel esta preparando el cigarro.");
                 try {
                    sleep(DECIMA_DE_SEGUNDO);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
                System.out.println("fumadorPapel acabo de preparar el cigarro.");
            }

            public void fumar() {
                System.out.println("fumadorPapel esta fumando.");
                 try {
                    sleep(DECIMA_DE_SEGUNDO);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
                System.out.println("fumadorPapel termino de fumar.");
            }
        };

        fumadorTabaco.start();
        fumadorFosforo.start();
        fumadorPapel.start();
    }

    /**
     * Inicia los tres agentes. Estos realizaran la siguiente tarea.
     *
     * 1. Intentara adquirir semaforoAgente asi que ellos lanzaran los
     * ingredientes. 
     * 2. Colocaran los ingredientes en la mesa.
     */
    public void iniciarAgentes() {
        Thread agenteA = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < ITERACIONES) {
                    try {
                        semaforoAgente.acquire();
                        System.out.println("AgenteA esta activo y proveera "
                                + "tabaco y papel en la iteración " + i);
                        tabaco.release();
                        papel.release();
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                    i++;
                }
            }
        };
        Thread agenteB = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < ITERACIONES) {
                    try {
                        semaforoAgente.acquire();
                        System.out.println("AgenteB esta activo y proveera "
                                + "fosforo y papel en la iteración " + i);
                        fosforo.release();
                        papel.release();
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                    i++;
                }
            }
        };
        Thread agenteC = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < ITERACIONES) {
                    try {
                        semaforoAgente.acquire();
                        System.out.println("Agente C esta activo y proveera "
                                + "tabaco y fosforo en la iteración " + i);
                        tabaco.release();
                        fosforo.release();
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                    i++;
                }
            }
        };
        agenteA.start();
        agenteB.start();
        agenteC.start();
    }

    /**
     * Prueba el problema de los fumadores. Es importante leer el reporte.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        Main prueba = new Main();
        prueba.iniciarAgentes();
        prueba.iniciarEmpujadores();
        prueba.iniciarFumadores();
    }
}
