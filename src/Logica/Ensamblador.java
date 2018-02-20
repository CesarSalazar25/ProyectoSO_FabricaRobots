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
    private int apuntador_Cabeza = 0, apuntador_Cuerpo = 0, apuntador_Extremidad = 0, tiempo_ensamblaje, robots=0;
    private JLabel Cabezas, Cuerpos, Extremidades, Robots;
    private boolean pausar=false;
    
    
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
    }
    
    @Override
    public void run()
    {
        while (true)
        {
                if(almacen.flag())
                {
                    robots=0;
                    almacen.mandarbool(false);
                }
            
            //Pausa el proceso de ensamblaje:
            synchronized(this)
            {
                while(pausar==true)
                {
                    try 
                    {
                        wait();
                    } 
                    catch (InterruptedException ex) 
                    {
                        Logger.getLogger(Ensamblador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }    
            }  

            try 
            {
  
                    if (almacen.getValor_Almacen_cabeza(apuntador_Cabeza)==1) 
                    {
                            //Entra un ensamblador:
                            Semaforo_ExcluyenteEnsamblador.acquire();
                            //Quiero "n" cabezas de robot:
                            Semaforo_ConsumidorCabeza.acquire(1);
                            //Entra en el almacen:
                            Semaforo_ExcluyenteCabeza.acquire();
                            almacen.setCant_cabeza(apuntador_Cabeza, 0);
                            Cabezas.setText(Integer.toString(almacen.Contar_Cabeza()));
                            apuntador_Cabeza = (apuntador_Cabeza+1)%almacen.getTam_cabeza();
                            System.out.println("#Ensamblador toma un cabeza de robot#");
                            //Sale del almacen:
                            Semaforo_ExcluyenteCabeza.release();
                            //Ya otro ensamblador puede agarrar cabezas:
                            Semaforo_ProducciónCabeza.release(1);
                            //Sale el ensamblador:
                            Semaforo_ExcluyenteEnsamblador.release();    
                    }
                    else
                    {
                            apuntador_Cabeza = (apuntador_Cabeza+1)%almacen.getTam_cabeza();
                    }
                    

                        
                    if (almacen.getValor_Almacen_extremidad(apuntador_Extremidad)==1) 
                    {
                            
                            Semaforo_ExcluyenteEnsamblador.acquire();
                            Semaforo_ConsumidorExtremidad.acquire(4);
                            Semaforo_ExcluyenteExtremidad.acquire();
                            almacen.setCant_extremidad(apuntador_Extremidad, 0);
                            Extremidades.setText(Integer.toString(almacen.Contar_Extremidad()));
                            apuntador_Extremidad = (apuntador_Extremidad+1)%almacen.getTam_extremidad();
                            System.out.println("#Ensamblador toma una extremidad de robot#");
                            Semaforo_ExcluyenteExtremidad.release();
                            Semaforo_ProducciónExtremidad.release(4);
                            Semaforo_ExcluyenteEnsamblador.release();
                           
                    }
                    else
                    {
                            apuntador_Extremidad = (apuntador_Extremidad+1)%almacen.getTam_extremidad();
                    }                      
                        
                        if (almacen.getValor_Almacen_cuerpo(apuntador_Cuerpo)==1) 
                        {
                            
                            Semaforo_ExcluyenteEnsamblador.acquire();
                            Semaforo_ConsumidorCuerpo.acquire(1);
                            Semaforo_ExcluyenteCuerpo.acquire();
                            almacen.setCant_cuerp(apuntador_Cuerpo, 0);
                            Cuerpos.setText(Integer.toString(almacen.Contar_Cuerpo()));
                            apuntador_Cuerpo = (apuntador_Cuerpo+1)%almacen.getTam_cuerpo(); 
                            System.out.println("#Ensamblador toma una extremidad de robot#");
                            Semaforo_ExcluyenteCuerpo.release();
                            Semaforo_ProducciónCuerpo.release(1);
                            Semaforo_ExcluyenteEnsamblador.release();
                        }
                        else
                        {
                            apuntador_Cuerpo = (apuntador_Cuerpo + 1)%almacen.getTam_cuerpo();
                        }   
                    
                    sleep(1000*tiempo_ensamblaje);
                
                    System.out.println("+Ensamblador: Ensambla un robot+");
                    robots++;
                    almacen.setCant_robots(robots);
                    Robots.setText(Integer.toString(robots));
                                    
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(Ensamblador.class.getName()).log(Level.SEVERE, null, ex);
            }            
    }
    }  
        
    
    //Método para pausar:
    public void pausar() 
    {
        this.pausar=true;
    }
    
    //Método para continuar:
    synchronized void reanudar()
    {
        this.pausar=false;
        notify();
    }

    
    
}
