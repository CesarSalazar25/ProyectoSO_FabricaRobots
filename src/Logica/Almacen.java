package Logica;

public class Almacen 
{
    //Variables:
    private int[] almacen_cabeza;
    private int[] almacen_cuerpo;
    private int[] almacen_extremidad;
    private int tam_cabeza, tam_cuerpo, tam_extremidad, cant_cabeza, cant_cuerpo, cant_extremidad, cant_robots, s;
    private boolean i;
    
    //Constructor:
    public Almacen(int tam_cabeza, int tam_cuerpo, int tam_extremidad) 
    {
        this.tam_cabeza = tam_cabeza;
        this.tam_cuerpo = tam_cuerpo;
        this.tam_extremidad = tam_extremidad;
        almacen_cabeza= new int[tam_cabeza];
        almacen_cuerpo= new int[tam_cuerpo];
        almacen_extremidad= new int[tam_extremidad];
    }
    
    //Getters y Setters:

    public int getValor_Almacen_cabeza(int i) {
        return almacen_cabeza[i];
    }

    public int getValor_Almacen_cuerpo(int i) {
        return almacen_cuerpo[i];
    }

    public int getValor_Almacen_extremidad(int i) {
        return almacen_extremidad[i];
    }

    public int getTam_cabeza() {
        return tam_cabeza;
    }

    public void setTam_cabeza(int tam_cabeza) {
        this.tam_cabeza = tam_cabeza;
    }

    public int getTam_cuerpo() {
        return tam_cuerpo;
    }

    public void setTam_cuerpo(int tam_cuerpo) {
        this.tam_cuerpo = tam_cuerpo;
    }

    public int getTam_extremidad() {
        return tam_extremidad;
    }

    public void setTam_extremidad(int tam_extremidad) {
        this.tam_extremidad = tam_extremidad;
    }

    public int getCant_cabeza() {
        return cant_cabeza;
    }


    public int getCant_cuerpo() {
        return cant_cuerpo;
    }


    public int getCant_extremidad() {
        return cant_extremidad;
    }

    public void setCant_cabeza(int apuntador_Cab, int val) 
    {
        this.almacen_cabeza[apuntador_Cab] = val;
    }
    

    public void setCant_cuerp(int apuntador_Cu, int val) 
    {
        this.almacen_cuerpo[apuntador_Cu] = val;
    }
    
    public void setCant_extremidad(int apuntador_Ext, int val) 
    {
        this.almacen_extremidad[apuntador_Ext] = val;
    }
    
    public int getCant_robots() 
    {
        return cant_robots;
    }

    public void setCant_robots(int cant_robots) 
    {
        this.cant_robots = cant_robots;
    }    
    
    //Método que cuenta las cabezas en el almacén
    public int Contar_Cabeza()
    {
        int cabeza_aux;
        cant_cabeza = 0;
        
        for(int i=0; i<tam_cabeza; i++)
        {
            cabeza_aux = getValor_Almacen_cabeza(i);
            if (cabeza_aux == 1)
            {
               cant_cabeza++;  
            }
        }
        return cant_cabeza;
    }
    
    //Método que cuenta los cuerpos en el almacén
    public int Contar_Cuerpo()
    {
        int cuerpo_aux;
        cant_cuerpo = 0;
        
        for(int i=0; i<tam_cuerpo; i++){
            cuerpo_aux = getValor_Almacen_cuerpo(i);
            if (cuerpo_aux == 1)
            {
               cant_cuerpo++; 
            }
        }
        return cant_cuerpo;
    }
    
    ////Método que cuenta las extremidades en el almacén
    public int Contar_Extremidad()
    {
        int extremidad_aux;
        cant_cuerpo = 0;
        
        for(int i=0; i<tam_extremidad; i++){
            extremidad_aux = getValor_Almacen_extremidad(i);
            if (extremidad_aux == 1)
            {
               cant_cuerpo++; 
            }
        }
        return cant_cuerpo;
    }
   
    public void mandarbool(boolean a)
    {
        i=a;
    }
    
    public boolean flag()
    {
        return i;
    }
}
