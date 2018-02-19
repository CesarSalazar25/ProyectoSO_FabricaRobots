/*
 * Proyecto I SO - Trimestre 1718-2
 * Autores: César Salazar y Loredana de Miro
 */
package Logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.util.concurrent.Semaphore;

public class Fabrica 
{   
    //Variables a usar:
    
    //Llamadas a otras clases:
    private Cronometrador cronometrador;
    private Almacen almacen;
    private Contador contador;
    private Gerente gerente;
    //private Productor Productor_Cabeza;
    //private Productor Productor_Cuerpo;
    //private Productor Productor_Extremidad;
    //private Ensamblador Ensamblador;
    
    //ArrayLists de Productor-Ensamblador:
    public ArrayList<Productor> Productor_cabeza;
    public ArrayList<Productor> Productor_cuerpo;
    public ArrayList<Productor> Productor_extremidad;
    public ArrayList<Ensamblador> Ensambladores;
    
    //Atributos Boolean para estatus de Productores-Ensambladores:
    private boolean BPCab, BPCuer, BPExtr, BPE; //(Cabeza, Cuerpo, Extremidad, Ensamblador)
    
    //Atributos int para validaciones al llenar Productores-Ensambladores:
    //private int cab=0, cuer=0, extr=0, ensam=0;
    
    //Atributos JLabel:
    public JLabel nroDiasDespacho, cantidadCabezas, cantidadCuerpos, cantidadExtremidades, cantidadProdCab, cantidadProdCuerpo, cantidadProdExtrem, diasTrici, cantidadEnsambladores, cantidadRobots, estadoGerente, estadoCrono, nroDias;
    
    //Semáforos:
    private Semaphore SE_ProductorCabeza;
    private Semaphore S_ProductorCabeza;
    private Semaphore S_EnsambladorCabeza;
    
    private Semaphore SE_ProductorCuerpo;
    private Semaphore S_ProductorCuerpo;
    private Semaphore S_EnsambladorCuerpo;
    
    private Semaphore SE_ProductorExtremidad;
    private Semaphore S_ProductorExtremidad;
    private Semaphore S_EnsambladorExtremidad;
    
    private Semaphore SE_Ensamblador;
    
    private Semaphore SE_Calendario;
    
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
    
    //Constructor que inicializa la fábrica:
    public Fabrica(JLabel nroDiasDespacho, JLabel cantidadCabezas, JLabel cantidadCuerpos, JLabel cantidadExtremidades, JLabel cantidadProdCab, JLabel cantidadProdCuerpo, JLabel cantidadProdExtrem, JLabel cantidadEnsambladores, JLabel cantidadRobots, JLabel estadoGerente, JLabel estadoCrono, JLabel nroDias) throws IOException
    {  
        //Leer el archivo de texto:
        LeerDatos();
                
       //Inicializar los labels con valores:
        this.nroDiasDespacho = nroDiasDespacho;
        nroDiasDespacho.setText(Integer.toString(dia_despacho));
        
        this.cantidadCabezas = cantidadCabezas;
        cantidadCabezas.setText(Integer.toString(0));
        
        this.cantidadCuerpos = cantidadCuerpos;
        cantidadCuerpos.setText(Integer.toString(0));
        
        this.cantidadExtremidades = cantidadExtremidades;
        cantidadExtremidades.setText(Integer.toString(0));
        
        this.cantidadProdCab = cantidadProdCab;
        cantidadProdCab.setText(Integer.toString(ini_prod_cabeza));
        
        this.cantidadProdCuerpo = cantidadProdCuerpo;
        cantidadProdCuerpo.setText(Integer.toString(ini_prod_cuerpo));
        
        this.cantidadProdExtrem = cantidadProdExtrem;
        cantidadProdExtrem.setText(Integer.toString(ini_prod_extremidad));
        
        this.cantidadEnsambladores = cantidadEnsambladores;
        cantidadEnsambladores.setText(Integer.toString(ini_ensambladores));
        
        this.cantidadRobots = cantidadRobots;
        cantidadRobots.setText(Integer.toString(0));
        
        this.estadoGerente = estadoGerente;
        estadoGerente.setText("Inactivo");
        
        this.estadoCrono = estadoCrono;
        estadoCrono.setText("Inactivo");

        this.nroDias = nroDias;
        nroDias.setText(Integer.toString(0));        
        
        //Inicializar Almacen:
        almacen= new Almacen(cant_cabeza, cant_cuerpo, cant_extremidad);
        
        //Inicializar Semaforos:
        SE_ProductorCabeza= new Semaphore(1);
        S_ProductorCabeza= new Semaphore(almacen.getTam_cabeza());
        S_EnsambladorCabeza= new Semaphore(0);
        
        SE_ProductorCuerpo= new Semaphore(1);
        S_ProductorCuerpo= new Semaphore(almacen.getTam_cuerpo());
        S_EnsambladorCuerpo=new Semaphore(0);

        SE_ProductorExtremidad= new Semaphore(1);
        S_ProductorExtremidad= new Semaphore(almacen.getTam_extremidad());
        S_EnsambladorExtremidad= new Semaphore(0);

        SE_Ensamblador= new Semaphore(1);
        SE_Calendario= new Semaphore(1);
        
        //Inicializar ArrayLists:
        Productor_cabeza= new ArrayList<Productor>(max_prod_cabeza);
        Productor_cuerpo= new ArrayList<Productor>(max_prod_cuerpo);
        Productor_extremidad= new ArrayList<Productor>(max_prod_extremidad);
        Ensambladores= new ArrayList<Ensamblador>(max_ensambladores);

    }
    
    //Método para contratar productor de cabezas de robot:
    public void Contratar_Prod_Cabeza()
    {
        if (ini_prod_cabeza!=max_prod_cabeza) 
        {
           Productor Prod_Cabeza = new Productor(almacen, SE_ProductorCabeza, S_ProductorCabeza, S_EnsambladorCabeza, 0, cantidadCabezas, tiempo_dia);
           Productor_cabeza.add(Prod_Cabeza);
           ini_prod_cabeza++;
           cantidadProdCab.setText(Integer.toString(ini_prod_cabeza));  
           Prod_Cabeza.start();  
        } 
    }
    
    //Método para contratar productor de cuerpos de robot:
    public void Contratar_Prod_Cuerpo()
    {
        if (ini_prod_cuerpo!=max_prod_cuerpo) 
        {
           Productor Prod_Cuerpo = new Productor(almacen, SE_ProductorCuerpo, S_ProductorCuerpo, S_EnsambladorCuerpo, 1, cantidadCuerpos, tiempo_dia);
           Productor_cuerpo.add(Prod_Cuerpo);
           ini_prod_cuerpo++;
           cantidadProdCuerpo.setText(Integer.toString(ini_prod_cuerpo));  
           Prod_Cuerpo.start();  
        } 

    }    
    
    //Método para contratar productor de extremidades de robot:
    public void Contratar_Prod_Extremidad()
    {
        if (ini_prod_extremidad!=max_prod_extremidad) 
        {
           Productor Prod_Extremidad = new Productor(almacen, SE_ProductorExtremidad, S_ProductorExtremidad, S_EnsambladorExtremidad, 2, cantidadExtremidades, tiempo_dia);
           Productor_extremidad.add(Prod_Extremidad);
           ini_prod_extremidad++;
           cantidadProdExtrem.setText(Integer.toString(ini_prod_extremidad));  
           Prod_Extremidad.start();  
        } 

    }        
    
    //Método para contratar ensambladores de robot:
    public void Contratar_Ensamblador()
    {
        if (ini_ensambladores!=max_ensambladores) 
        {
           Ensamblador Ensam = new Ensamblador(almacen, S_ProductorCabeza, S_EnsambladorCabeza, SE_ProductorCabeza, S_ProductorCuerpo, S_EnsambladorCuerpo, SE_ProductorCuerpo, S_ProductorExtremidad, S_EnsambladorExtremidad, SE_ProductorExtremidad, SE_Ensamblador, 2, cantidadCabezas, cantidadCuerpos, cantidadExtremidades, cantidadRobots);
           Ensambladores.add(Ensam);
           ini_ensambladores++;
           cantidadEnsambladores.setText(Integer.toString(ini_ensambladores));  
           Ensam.start();  
        } 

    }
    
    //Método para despedir a productor de cabezas de robot:
    public void Despedir_Prod_Cabeza()
    {
        Productor_cabeza.remove(Productor_cabeza.size()-1);
        ini_prod_cabeza--;
        cantidadProdCab.setText(Integer.toString(ini_prod_cabeza)); 
        
        
    }         
    
    //Método para despedir a productor de cuerpos de robot:
    public void Despedir_Prod_Cuerpo()
    {   
        Productor_cuerpo.remove(Productor_cuerpo.size()-1);
        ini_prod_cuerpo--;
        cantidadProdCuerpo.setText(Integer.toString(ini_prod_cuerpo)); 

    }
    
    //Método para despedir a productor de extremidades de robot:
    public void Despedir_Prod_Extremidad()
    {
        Productor_extremidad.remove(Productor_extremidad.size()-1);
        ini_prod_extremidad--;
        cantidadProdExtrem.setText(Integer.toString(ini_prod_extremidad));        
    }
    
    //Método para despedir a ensamblador de robot:
    public void Despedir_Ensamblador()
    {
        Ensambladores.remove(Ensambladores.size()-1);
        ini_ensambladores--;
        cantidadEnsambladores.setText(Integer.toString(ini_ensambladores)); 
    }
     
    //Método para empezar la producción-ensamblaje:
    public void Producir_Ensamblar()
    {
        for (Object prod_cab: Productor_cabeza) 
        {
          ((Productor)prod_cab).start(); 
        }
       
        for (Object prod_cue: Productor_cuerpo) 
        {
          ((Productor)prod_cue).start();
        }
        
        for (Object prod_ext: Productor_extremidad) 
        {
          ((Productor)prod_ext).start();
        }
       
        for (Object ens: Ensambladores) 
        {
          ((Ensamblador)ens).start();
        }
        
        contador = new Contador();
        cronometrador = new Cronometrador (tiempo_dia, estadoCrono, nroDias, contador, SE_Calendario);
        gerente = new Gerente (contador, SE_Ensamblador, SE_Calendario, almacen, estadoGerente, cantidadRobots, tiempo_dia);
        cronometrador.start();
        gerente.start();
    }
   
    //Método que inicializa y actualiza los ArrayLists con los números iniciales dados por el archivo de texto:
    public void Inicializar_ArrayLists()
    {
        //Variables auxiliares para validaciones:
        int aux1=ini_prod_cabeza;
        int aux2=ini_prod_cuerpo;
        int aux3=ini_prod_extremidad;
        int aux4=ini_ensambladores;        
        
      while (aux1!=0)
      { 
        Productor_cabeza.add(new Productor(almacen, SE_ProductorCabeza, S_ProductorCabeza, S_EnsambladorCabeza, 0, cantidadCabezas, tiempo_dia));
        aux1--;
      }
      
      while (aux2!=0)
      {
        Productor_cuerpo.add(new Productor(almacen, SE_ProductorCabeza, S_ProductorCabeza, S_EnsambladorCabeza, 1, cantidadCuerpos, tiempo_dia));
        aux2--;
      }
      
      while (aux3!=0)
      {
        Productor_extremidad.add(new Productor(almacen, SE_ProductorCabeza, S_ProductorCabeza, S_EnsambladorCabeza, 2, cantidadExtremidades, tiempo_dia));
        aux3--;
      }
       
      while (aux4!=0)
      {
        Ensambladores.add(new Ensamblador(almacen, S_ProductorCabeza, S_EnsambladorCabeza, SE_ProductorCabeza, S_ProductorCuerpo, S_EnsambladorCuerpo, SE_ProductorCuerpo, S_ProductorExtremidad, S_EnsambladorExtremidad, SE_ProductorExtremidad, SE_Ensamblador, 2, cantidadCabezas, cantidadCuerpos, cantidadExtremidades, cantidadRobots));
        aux4--;
      }
    }
    
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
    
}
