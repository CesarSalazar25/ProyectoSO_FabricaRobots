package Logica;

import javax.swing.JLabel;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cronometrador extends Thread
{
    //Variables:
    private int tiempo;
    private JLabel estadoCrono, nroDias;
    private Contador cont_dia;
    private Semaphore SE_Calendar; 
    private boolean escritura = false;
    
    //Constructor:
    public Cronometrador(int tiempo, JLabel estadoCrono, JLabel nroDias, Contador cont_dia, Semaphore SE_Calendar) 
    {
        this.tiempo = tiempo;
        this.estadoCrono = estadoCrono;
        this.nroDias = nroDias;
        this.cont_dia = cont_dia;
        this.SE_Calendar = SE_Calendar;
    }
    
    @Override
    public void run()
    {
        while(true)
        {

            try 
            {
                
                SE_Calendar.acquire();
                estadoCrono.setText("Escribiendo");
                
                try 
                {
                  sleep((long)(((tiempo*1000)*22.8)/24));
                } 
                catch (InterruptedException ex) 
                {
                Logger.getLogger(Cronometrador.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(Cronometrador.class.getName()).log(Level.SEVERE, null, ex);
            }
               
                cont_dia.incrementarCont_Dia();
                nroDias.setText(Integer.toString(cont_dia.getCont_Dia()));
            try 
            {
                sleep((long) (((tiempo*1000)*22.8)/24));
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(Cronometrador.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            SE_Calendar.release();

            nroDias.setText(Integer.toString(cont_dia.getCont_Dia()));
            
            estadoCrono.setText("Durmiendo");    
        }
    }
}
