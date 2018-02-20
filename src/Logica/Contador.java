package Logica;

public class Contador extends Thread
{
    //Variables:
    private int cont_dia;
    private int dia_original;

    //Constructor:
    public Contador(int despacho) 
    {
        this.cont_dia = despacho;
        this.dia_original=despacho;
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

    public int getDia_Original() 
    {
        return dia_original;
    }
    
    //Decrementa los días de despacho:
    public void decrementarCont_Dia() 
    {
        cont_dia--;
        
    }

   
}
