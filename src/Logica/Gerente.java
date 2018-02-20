package Logica;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class Gerente extends Thread
{
    //Variables:
    private Contador cont_dia;
    private Semaphore SE_Robots;
    private Random random = new Random();
    private Almacen almacen;
    private boolean revision;
    private JLabel estadoGerente, cantRobots;
    private int tiempo_Duerme, tiempo_Dia;
    
    //Constructor:
    public Gerente(Contador cont_dia, Semaphore SE_Robots, Almacen almacen, JLabel estadoGerente, JLabel cantRobots, int tiempo_Dia) 
    {
        this.cont_dia = cont_dia;
        this.SE_Robots = SE_Robots;
        this.almacen = almacen;
        this.estadoGerente = estadoGerente;
        this.cantRobots = cantRobots;
        this.tiempo_Dia = tiempo_Dia;
    }
    
    //Getter y Setter:
    public Almacen getAlmacen() 
    {
        return almacen;
    }
    
    public void setAlmacen(Almacen almacen) 
    {
        this.almacen = almacen;
    }

    public boolean isRevision() 
    {
        return revision;
    }

    public void setRevision(boolean revision) 
    {
        this.revision = revision;
    }

    public int getTiempo_Dia() 
    {
        return tiempo_Dia;
    }

    public void setTiempo_Dia(int tiempo_Dia) 
    {
        this.tiempo_Dia = tiempo_Dia;
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            
            try 
            {
                tiempo_Duerme=(this.random.nextInt(18));

                    if(tiempo_Duerme<6)
                    {
                        sleep(((tiempo_Dia*1000)*6)/24);
                        estadoGerente.setText("Dormido");
                    }
                    else if(tiempo_Duerme>=6)
                    {
                        sleep(((tiempo_Dia*1000)*tiempo_Duerme)/24);
                        estadoGerente.setText("Dormido");                        
                    }
                
                
                if(cont_dia.getCont_dia_despacho()==0)
                {
                    SE_Robots.acquire();
                    almacen.setCant_robots(0);
                    cantRobots.setText(Integer.toString(almacen.getCant_robots()));
                    almacen.mandarbool(true);
                    try 
                    {
                        sleep (tiempo_Dia*1000);
                    } 
                    catch (InterruptedException ex) 
                    {
                        Logger.getLogger(Gerente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    estadoGerente.setText("Despachando");
                }                          
                else
                {
                    sleep(((tiempo_Dia*1000)*tiempo_Duerme)/24);
                    estadoGerente.setText("Verificando");
                }
                
            }
            catch (InterruptedException ex) 
            {
               Logger.getLogger(Gerente.class.getName()).log(Level.SEVERE, null, ex);
           }
           SE_Robots.release(); 
        }
    }        
    
    
    
    



}
