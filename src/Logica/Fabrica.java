/*
 * Proyecto I SO - Trimestre 1718-2
 * Autores: César Salazar y Loredana de Miro
 */
package Logica;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.util.concurrent.Semaphore;

public class Fabrica 
{   
    //Variables a usar:
    
    //Llamadas a otras clases:
    private Datos datos;
    private Cronometrador cronometrador;
    private Almacen almacen;
    private Contador contador;
    private Gerente gerente;
    
    //ArrayLists de Productor-Ensamblador:
    private ArrayList<Productor> Productor_cabeza;
    private ArrayList<Productor> Productor_cuerpo;
    private ArrayList<Productor> Productor_extremidad;
    private ArrayList<Ensamblador> Ensambladores;
    
    //Atributos Boolean para estatus de Productores-Ensambladores:
    private boolean BPCab, BPCuer, BPExtr, BPE; //(Cabeza, Cuerpo, Extremidad, Ensamblador)
    
    //Atributos int para validaciones al llenar Productores-Ensambladores:
    private int cab=0, cuer=0, extr=0, ensam=0;
    
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
    
    //Variable para validar los archivos del txt:
    
    
    //Constructor que inicializa la fábrica:
    public Fabrica(JLabel nroDiasDespacho, JLabel cantidadCabezas, JLabel cantidadCuerpos, JLabel cantidadExtremidades, JLabel cantidadProdCab, JLabel cantidadProdCuerpo, JLabel cantidadProdExtrem, JLabel cantidadEnsambladores, JLabel cantidadRobots, JLabel estadoGerente, JLabel estadoCrono, JLabel nroDias) throws IOException
    {
        //Inicializa la clase Datos:
        datos= new Datos();
        
        //Leer el archivo de texto:
        datos.LeerDatos();
        
       //Inicializar los labels con valores:
        this.nroDiasDespacho = nroDiasDespacho;
        nroDiasDespacho.setText(String.valueOf(datos.getDia_despacho()));
        
        this.cantidadCabezas = cantidadCabezas;
        cantidadCabezas.setText(String.valueOf(0));
        
        this.cantidadCuerpos = cantidadCuerpos;
        cantidadCuerpos.setText(String.valueOf(0));
        
        this.cantidadExtremidades = cantidadExtremidades;
        cantidadExtremidades.setText(String.valueOf(0));
        
        this.cantidadProdCab = cantidadProdCab;
        cantidadProdCab.setText(String.valueOf(datos.getIni_prod_cabeza()));
        
        this.cantidadProdCuerpo = cantidadProdCuerpo;
        cantidadProdCuerpo.setText(String.valueOf(datos.getIni_prod_cuerpo()));
        
        this.cantidadProdExtrem = cantidadProdExtrem;
        cantidadProdExtrem.setText(String.valueOf(datos.getIni_prod_extremidad()));
        
        this.cantidadEnsambladores = cantidadEnsambladores;
        cantidadEnsambladores.setText(String.valueOf(datos.getIni_ensambladores()));
        
        this.cantidadRobots = cantidadRobots;
        cantidadRobots.setText(String.valueOf(0));
        
        this.estadoGerente = estadoGerente;
        estadoGerente.setText("Inactivo");
        
        this.estadoCrono = estadoCrono;
        estadoCrono.setText("Inactivo");

        this.nroDias = nroDias;
        nroDias.setText(Integer.toString(0));        
        
    }
    
    //Método para contratar productor de cabezas de robot:
    public void Contratar_Prod_Cabeza()
    {
        //Productor_cabeza.add(0, new Productor(almacen,0,datos.getTiempo_dia(),2));
        //cantidadProdCab.setText(String.valueOf(Productor_cabeza.size()));

    }
    
    //Método para contratar productor de cuerpos de robot:
    public void Contratar_Prod_Cuerpo()
    {
        

    }    
    
    //Método para contratar productor de extremidades de robot:
    public void Contratar_Prod_Extremidad()
    {
        

    }        
    
    //Método para contratar ensambladores de robot:
    public void Contratar_Ensamblador()
    {
        

    }
    
    //Método para despedir a productor de cabezas de robot:
    public void Despedir_Prod_Cabeza()
    {
        

    }         
    
    //Método para despedir a productor de cuerpos de robot:
    public void Despedir_Prod_Cuerpo()
    {
        

    }
    
    //Método para despedir a productor de extremidades de robot:
    public void Despedir_Prod_Extremidad()
    {
        

    }
    
    //Método para despedir a ensamblador de robot:
    public void Despedir_Ensamblador()
    {
        

    }
    
    //Método para pausar:
    public void Pausar_Todo()
    {
        
    }
    
    //Método para reanudar:
    public void Reanudar()
    {
        
    }

    //Método para producir:
    public void Producir()
    {
        
    }
    
    //Método para Ensamblar:
    public void Ensamblar()
    {
        
    }
    
    //Método para producir cabezas de robot:
    public void Producir_Cabeza()
    {
        

    }    
    
    //Método para producir cuerpos de robot:
    public void Producir_Cuerpo()
    {
        

    }       
    
    //Método para producir extremidades de robot:
    public void Producir_Extremidad()
    {
        

    }
    
    //Método que inicia la aplicación:
    public void Iniciar()
    {
        
    }
    
}
