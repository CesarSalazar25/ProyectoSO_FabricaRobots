package Logica;

public class Contador extends Thread
{
    //Variables:
    private int cont_dia;

    //Constructor:
    public Contador() 
    {
        cont_dia = 0;
    }

    //Getter y Setter
    public int getCont_Dia() 
    {
        return cont_dia;
    }

    public void setCont_Dia(int cont_dia) 
    {
        this.cont_dia = cont_dia;
    }
    
    //Incrementa los días:
    public void incrementarCont_Dia() 
    {
        cont_dia++;
    }

   
}
