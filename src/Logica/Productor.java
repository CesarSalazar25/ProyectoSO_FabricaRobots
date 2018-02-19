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
    private Semaphore Semaforo_Excluyente, Semaforo_Productor, Semaforo_Ensamblador, Detener= new Semaphore(0);
    private int tipo, cont_pieza, apuntador=0; //Tipos: 0->Cabeza, 1->Cuerpo, 2->Extremidad
    private JLabel label;
    private int tiempo_produccion;
    private boolean pausar=false, parar;
    
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
        parar=false;
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

    public boolean isParar() {
        return parar;
    }

    public void setParar(boolean parar) {
        this.parar = parar;
    }

    public Semaphore getDetener() {
        return Detener;
    }

    public void setDetener(Semaphore Detener) {
        this.Detener = Detener;
    }
    
    @Override
     public void run ()
     {
        while(true)
        {
            //Parar proceso de producción:
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
            
            //Productor de Cabeza
            if(tipo==0)
            {
                try 
                {

                    Semaforo_Productor.acquire();
                    Semaforo_Excluyente.acquire();
                    almacen.setCant_cabeza(apuntador, 1);
                    sleep(1000*tiempo_produccion);
                    apuntador = (apuntador + 1)%almacen.getTam_cabeza();
                    System.out.println("+Productor de Cabeza: Produce un cabeza robot+");
                    Semaforo_Excluyente.release();
                    Semaforo_Ensamblador.release();
                    label.setText(Integer.toString(almacen.Contar_Cabeza()));
                    
                    synchronized(this)
                    {
                       while(pausar==true)
                       {
                            wait();
                       }    
                    }
                } 
                catch (InterruptedException ex) 
                {
                    Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
            else if(tipo==1)
            {
                try 
                {

                    Semaforo_Productor.acquire();
                    Semaforo_Excluyente.acquire();
                    almacen.setCant_cuerp(apuntador, 1);
                    sleep(1000*tiempo_produccion);
                    apuntador = (apuntador + 1)%almacen.getTam_cuerpo();
                    System.out.println("+ Productor de Cuerpo: Produce un cuerpo robot+");
                    Semaforo_Excluyente.release();
                    Semaforo_Ensamblador.release();
                    label.setText(Integer.toString(almacen.Contar_Cuerpo()));
                    
                    synchronized(this)
                    {
                       while(pausar==true)
                       {
                            wait();
                       }    
                    }

                } 
                catch (InterruptedException ex) 
                {
                    Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
            else
            {
                try 
                {

                    Semaforo_Productor.acquire();
                    Semaforo_Excluyente.acquire();
                    almacen.setCant_extremidad(apuntador, 1);
                    sleep(1000*tiempo_produccion);
                    apuntador = (apuntador + 1)%almacen.getTam_extremidad();
                    System.out.println("+Productor de Extremidad: Produce una extremidad de robot+");
                    Semaforo_Excluyente.release();
                    Semaforo_Ensamblador.release();
                    label.setText(Integer.toString(almacen.Contar_Extremidad()));
                    synchronized(this)
                    {
                       while(pausar==true)
                       {
                            wait();
                       }    
                    }
                } 
                catch (InterruptedException ex) 
                {
                    Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
        }    
    } 
    
    public void pausar() 
    {
     this.pausar=true;
    }
    
    synchronized void reanudar()
    {
     this.pausar=false;
     notify();
    }    

}
