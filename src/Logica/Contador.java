package Logica;

public class Contador extends Thread
{
    //Variables:
    private int cont_dia_despacho;
    private int dia_original;
    private int cont_dia;

    //Constructor:
    public Contador(int despacho) 
    {
        this.cont_dia_despacho = despacho;
        this.dia_original=despacho;
        this.cont_dia=0;
    }

    //Getter y Setter
    public int getCont_dia_despacho() 
    {
        return cont_dia_despacho;
    }
        public int getCont_dia() 
    {
        return cont_dia;
    }

    public void setCont_dia_despacho(int cont_dia_despacho) 
    {
        this.cont_dia_despacho = cont_dia_despacho;
    }

    public int getDia_original() 
    {
        return dia_original;
    }
    
    //Decrementa los días de despacho:
    public void decrementarCont_dia_despacho() 
    {
        cont_dia_despacho--;
        
    }
    
    //Aumentar contador días:
    public void incrementarCont_dia()
    {
        cont_dia++;
        
        //Se reinicia:
        if(cont_dia==365)
        {
            cont_dia=0;
        }
    }

   
}
