package Logica;

//Librerías:
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Datos 
{
    //Variables a los que se les pasan sus valores por el archivo de texto (El orden con el que esta escrito aquí es el orden seguido en el swicht y en el .txt):
    private int tiempo_dia;             //Tiempo que dura un día
    private int dia_despacho;           //Cant. de días entre despacho    
    private int cant_cabeza;            //Almacén de Cabeza
    private int cant_cuerpo;            //Almacén de Cuerpo
    private int cant_extremidad;        //Almacén de Extremidad    
    private int ini_prod_cabeza;         //Cant. inicial Productor de Cabeza
    private int ini_prod_cuerpo;         //Cant. inicial Productor de Cuerpo
    private int ini_prod_extremidad;     //Cant. inicial Productor de Extremidad
    private int max_prod_cabeza;         //Cant. máxima Productor de Cabeza
    private int max_prod_cuerpo;         //Cant. máxima Productor de Cuerpo
    private int max_prod_extremidad;     //Cant. máxima Productor de Extremidad
    private int ini_ensambladores;       //Cant. inicial de ensambladores
    private int max_ensambladores;       //Cant. máxima de ensambladores
    
    //Método que asigna asigna los valores por archivo de texto:
    public void LeerDatos()throws FileNotFoundException, IOException
    {
        String palabra;
        int num_linea=1;

        BufferedReader br = new BufferedReader(new FileReader("archivo.txt"));
        while(br.ready())
        {
            palabra=br.readLine();
            switch(num_linea)
            {
                case 1:
                    tiempo_dia=Integer.parseInt(palabra);
                    break;  
                case 2:
                    dia_despacho=Integer.parseInt(palabra);
                    break;
                        
                case 3:
                    cant_cabeza=Integer.parseInt(palabra);
                    break; 
                        
                case 4:
                    cant_cuerpo=Integer.parseInt(palabra);
                    break;
                        
                case 5:
                    cant_extremidad=Integer.parseInt(palabra);
                    break;
                        
                case 6:
                    ini_prod_cabeza=Integer.parseInt(palabra);
                    break;
                        
                case 7:
                    ini_prod_cuerpo=Integer.parseInt(palabra);
                    break;
    
                case 8:
                    ini_prod_extremidad=Integer.parseInt(palabra);
                    break;
    
                case 9:
                    max_prod_cabeza=Integer.parseInt(palabra);
                    break;
    
                case 10:
                    max_prod_cuerpo=Integer.parseInt(palabra);
                    break;
    
                case 11:
                    max_prod_extremidad=Integer.parseInt(palabra);
                    break;
    
                case 12:
                    ini_ensambladores=Integer.parseInt(palabra);
                    break;
    
                case 13:
                    max_ensambladores=Integer.parseInt(palabra);
                    break;
            }
            num_linea++;
        }
        br.close();
    }
    
    //Getters:
    
    public int getTiempo_dia() 
    {
        return tiempo_dia;
    }

    public int getDia_despacho() 
    {
        return dia_despacho;
    }

    public int getCant_cabeza() 
    {
        return cant_cabeza;
    }

    public int getCant_cuerpo() 
    {
        return cant_cuerpo;
    }

    public int getCant_extremidad() 
    {
        return cant_extremidad;
    }

    public int getIni_prod_cabeza() 
    {
        return ini_prod_cabeza;
    }

    public int getIni_prod_cuerpo() 
    {
        return ini_prod_cuerpo;
    }

    public int getIni_prod_extremidad() 
    {
        return ini_prod_extremidad;
    }

    public int getMax_prod_cabeza() 
    {
        return max_prod_cabeza;
    }

    public int getMax_prod_cuerpo() 
    {
        return max_prod_cuerpo;
    }

    public int getMax_prod_extremidad() 
    {
        return max_prod_extremidad;
    }

    public int getIni_ensambladores() 
    {
        return ini_ensambladores;
    }

    public int getMax_ensambladores() 
    {
        return max_ensambladores;
    }
    
    
}

