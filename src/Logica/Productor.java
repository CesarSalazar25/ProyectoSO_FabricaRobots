package Logica;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class Productor extends Thread
{
    //Variables:
    public Almacen almacen;
    private Semaphore Semaforo_Excluyente, Semaforo_Productor, Semaforo_Ensamblador;
    private int tipo, cont_pieza, apuntador=0; //Tipos: 0->Cabeza, 1->Cuerpo, 2->Extremidad
    private JLabel label;
    private int tiempo_produccion;
    private boolean pausar=false;
    
    //Constructor:
    public Productor(Almacen almacen, Semaphore Semaforo_Excluyente, Semaphore Semaforo_Productor, Semaphore Semaforo_Ensamblador, int tipo, JLabel label, int tiempo_produccion) 
    {
        this.almacen = almacen;
        this.Semaforo_Excluyente = Semaforo_Excluyente;
        this.Semaforo_Productor = Semaforo_Productor;
        this.Semaforo_Ensamblador = Semaforo_Ensamblador;
        this.tipo = tipo;
        this.label = label;
        this.tiempo_produccion = tiempo_produccion;
    }
    
    //Getter y Setter:

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public Semaphore getSemaforo_Excluyente() {
        return Semaforo_Excluyente;
    }

    public void setSemaforo_Excluyente(Semaphore Semaforo_Excluyente) {
        this.Semaforo_Excluyente = Semaforo_Excluyente;
    }

    public Semaphore getSemaforo_Productor() {
        return Semaforo_Productor;
    }

    public void setSemaforo_Productor(Semaphore Semaforo_Productor) {
        this.Semaforo_Productor = Semaforo_Productor;
    }

    public Semaphore getSemaforo_Ensamblador() {
        return Semaforo_Ensamblador;
    }

    public void setSemaforo_Ensamblador(Semaphore Semaforo_Ensamblador) {
        this.Semaforo_Ensamblador = Semaforo_Ensamblador;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getCont_pieza() {
        return cont_pieza;
    }

    public void setCont_pieza(int cont_pieza) {
        this.cont_pieza = cont_pieza;
    }

    public int getApuntador() {
        return apuntador;
    }

    public void setApuntador(int apuntador) {
        this.apuntador = apuntador;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public int getTiempo_produccion() {
        return tiempo_produccion;
    }

    public void setTiempo_produccion(int tiempo_produccion) {
        this.tiempo_produccion = tiempo_produccion;
    }
    
    @Override
     public void run ()
     {
        while(true)
        {   
            //Pausa el proceso de producción:
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
                        Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }    
            }               
            
            //Productor de Cabeza:
            if(tipo==0)
            {
                try 
                {
                    //Comprueba espacio en el almacen:
                    Semaforo_Productor.acquire();
                    
                    //Produce:
                    
                    //Entra en el almacen:                    
                    Semaforo_Excluyente.acquire();
                    //sleep(1000*tiempo_produccion);
                    System.out.println("+Productor de Cabeza: Produce un cabeza robot+");
                    //Deja lo producido en el almacen:
                    almacen.setCant_cabeza(apuntador, 1);
                        sleep(1000*tiempo_produccion);
                    apuntador = (apuntador + 1)%almacen.getTam_cabeza();
                    label.setText(Integer.toString(almacen.Contar_Cabeza()));
                        
                    //Se sale del almacen:                          
                    Semaforo_Excluyente.release();
                    
                    //Listo para consumir                    
                    Semaforo_Ensamblador.release();

                } 
                catch (InterruptedException ex) 
                {
                    Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
            //Productor Cuerpo:
            else if(tipo==1)
            {
                try 
                {
                    Semaforo_Productor.acquire();
                    //sleep(1000*tiempo_produccion);
                    System.out.println("+ Productor de Cuerpo: Produce un cuerpo robot+");
                    Semaforo_Excluyente.acquire();
                    almacen.setCant_cuerp(apuntador, 1);
                        sleep(1000*tiempo_produccion);
                    apuntador = (apuntador + 1)%almacen.getTam_cuerpo();
                    label.setText(Integer.toString(almacen.Contar_Cuerpo()));                  
                    Semaforo_Excluyente.release();
                    Semaforo_Ensamblador.release();
  

                } 
                catch (InterruptedException ex) 
                {
                    Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
            //Productor de Extremidad:
            else
            {
                try 
                {
                    Semaforo_Productor.acquire();
                    //sleep(1000*tiempo_produccion);
                    System.out.println("+Productor de Extremidad: Produce una extremidad de robot+");
                    Semaforo_Excluyente.acquire();
                    almacen.setCant_extremidad(apuntador, 1);
                        sleep(1000*tiempo_produccion);
                    apuntador = (apuntador + 1)%almacen.getTam_extremidad();
                    label.setText(Integer.toString(almacen.Contar_Extremidad())); 
                    Semaforo_Excluyente.release();
                    Semaforo_Ensamblador.release();

                } 
                catch (InterruptedException ex) 
                {
                    Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
                }                
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
