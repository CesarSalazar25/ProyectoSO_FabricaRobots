package Logica;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;


public class Ensamblador extends Thread
{
    //Variables:
    private Almacen almacen;
    private Semaphore Semaforo_ProducciónCabeza, Semaforo_ConsumidorCabeza, Semaforo_ExcluyenteCabeza, Semaforo_ProducciónCuerpo, Semaforo_ConsumidorCuerpo, Semaforo_ExcluyenteCuerpo, Semaforo_ProducciónExtremidad, Semaforo_ConsumidorExtremidad, Semaforo_ExcluyenteExtremidad, Semaforo_ExcluyenteEnsamblador, Detener = new Semaphore(0);
    private int apuntador_Cabeza = 0, apuntador_Cuerpo = 0, apuntador_Extremidad = 0, tiempo_ensamblaje, robots= 0;
    private JLabel Cabezas, Cuerpos, Extremidades, Robots;
    private boolean parar, pausar=false;
    
    //Constructor vacío:

    
    //Constructor lleno:
    public Ensamblador(Almacen almacen, Semaphore Semaforo_ProducciónCabeza, Semaphore Semaforo_ConsumidorCabeza, Semaphore Semaforo_ExcluyenteCabeza, Semaphore Semaforo_ProducciónCuerpo, Semaphore Semaforo_ConsumidorCuerpo, Semaphore Semaforo_ExcluyenteCuerpo, Semaphore Semaforo_ProducciónExtremidad, Semaphore Semaforo_ConsumidorExtremidad, Semaphore Semaforo_ExcluyenteExtremidad, Semaphore Semaforo_ExcluyenteEnsamblador, int tiempo_ensamblaje, JLabel Cabezas, JLabel Cuerpos, JLabel Extremidades, JLabel Robots) 
    {
        this.almacen = almacen;
        this.Semaforo_ProducciónCabeza = Semaforo_ProducciónCabeza;
        this.Semaforo_ConsumidorCabeza = Semaforo_ConsumidorCabeza;
        this.Semaforo_ExcluyenteCabeza = Semaforo_ExcluyenteCabeza;
        this.Semaforo_ProducciónCuerpo = Semaforo_ProducciónCuerpo;
        this.Semaforo_ConsumidorCuerpo = Semaforo_ConsumidorCuerpo;
        this.Semaforo_ExcluyenteCuerpo = Semaforo_ExcluyenteCuerpo;
        this.Semaforo_ProducciónExtremidad = Semaforo_ProducciónExtremidad;
        this.Semaforo_ConsumidorExtremidad = Semaforo_ConsumidorExtremidad;
        this.Semaforo_ExcluyenteExtremidad = Semaforo_ExcluyenteExtremidad;
        this.Semaforo_ExcluyenteEnsamblador = Semaforo_ExcluyenteEnsamblador;
        this.tiempo_ensamblaje = tiempo_ensamblaje;
        this.Cabezas = Cabezas;
        this.Cuerpos = Cuerpos;
        this.Extremidades = Extremidades;
        this.Robots = Robots;
        parar=false;
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            if (parar) 
            {
                try 
                {
                    Detener.acquire();
                } 
                catch (InterruptedException ex) 
                {
                    Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
                }
                parar=false;
            }

            try 
            {
                //Variables auxiliares:
                int cabezasR = 0;
                int cuerposR = 0;
                int extremidadesR=0;
                
                //Entra en este punto si tiene las piezas necesarias para ensamblar el robot:
                if (almacen.Contar_Cabeza()>0 && almacen.Contar_Cuerpo()>=0 && almacen.Contar_Extremidad()>3) 
                {

                    while (cabezasR<1)
                    {
                          
                        if (almacen.getValor_Almacen_cabeza(apuntador_Cabeza)==1) 
                        {
                            Semaforo_ExcluyenteEnsamblador.acquire();
                            Semaforo_ConsumidorCabeza.acquire();
                            Semaforo_ExcluyenteCabeza.acquire();
                            almacen.setCant_cabeza(apuntador_Cabeza, 0);
                            Cabezas.setText(Integer.toString(almacen.Contar_Cabeza()));
                            apuntador_Cabeza = (apuntador_Cabeza+1)%almacen.getTam_cabeza();
                            cabezasR++;
                            System.out.println("#Ensamblador toma un cuerpo de robot#");
                            Semaforo_ExcluyenteCabeza.release();
                            Semaforo_ProducciónCabeza.release();
                            Semaforo_ExcluyenteEnsamblador.release();    
                        }
                        else
                        {
                            apuntador_Cabeza = (apuntador_Cabeza+1)%almacen.getTam_cabeza();
                        }
                    }
                    
                    
                    while (extremidadesR<4) 
                    {
                        
                        if (almacen.getValor_Almacen_extremidad(apuntador_Extremidad)==1) 
                        {
                            
                            Semaforo_ExcluyenteEnsamblador.acquire();
                            Semaforo_ConsumidorExtremidad.acquire();
                            Semaforo_ExcluyenteExtremidad.acquire();
                            almacen.setCant_extremidad(apuntador_Extremidad, 0);
                            Extremidades.setText(Integer.toString(almacen.Contar_Extremidad()));
                            apuntador_Extremidad = (apuntador_Extremidad+1)%almacen.getTam_extremidad();
                            extremidadesR++; 
                            System.out.println("#Ensamblador toma un cuerpo de robot#");
                            Semaforo_ExcluyenteExtremidad.release();
                            Semaforo_ProducciónExtremidad.release();
                            Semaforo_ExcluyenteEnsamblador.release();
                           
                        }
                        else
                        {
                            apuntador_Extremidad = (apuntador_Extremidad+1)%almacen.getTam_extremidad();
                        }   
                    }
                    
                    while (cuerposR<1) 
                    {
                        
                        if (almacen.getValor_Almacen_cuerpo(apuntador_Cuerpo)==1) 
                        {
                            
                            Semaforo_ExcluyenteEnsamblador.acquire();
                            Semaforo_ConsumidorCuerpo.acquire();
                            Semaforo_ExcluyenteCuerpo.acquire();
                            almacen.setCant_cuerp(apuntador_Cuerpo, 0);
                            Cuerpos.setText(Integer.toString(almacen.Contar_Cuerpo()));
                            apuntador_Cuerpo = (apuntador_Cuerpo+1)%almacen.getTam_cuerpo();
                            cuerposR++; 
                            System.out.println("#Ensamblador toma un cuerpo de robot#");
                            Semaforo_ExcluyenteCuerpo.release();
                            Semaforo_ProducciónCuerpo.release();
                            Semaforo_ExcluyenteEnsamblador.release();
                        }
                        else
                        {
                            apuntador_Cuerpo = (apuntador_Cuerpo + 1)%almacen.getTam_cuerpo();
                        }   
                    }
                    
                    sleep(1000*tiempo_ensamblaje);
                    synchronized(this)
                    { 
                        while(pausar==true)
                        {
                            wait();
                        }
                    }
                    
                    robots++;
                    System.out.println("+Ensamblador: Ensambla un robot+");
                    Robots.setText(Integer.toString(robots));
                    
                }
                else
                {
                    
                }
                
            } catch (InterruptedException ex) 
            {
                Logger.getLogger(Ensamblador.class.getName()).log(Level.SEVERE, null, ex);
            }            
            
        }
    }
    
    
    
    
    
    
    
}
