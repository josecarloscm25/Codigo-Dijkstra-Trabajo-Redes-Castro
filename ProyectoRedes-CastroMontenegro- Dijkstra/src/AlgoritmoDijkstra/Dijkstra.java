/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmoDijkstra;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author chelo
 */
public class Dijkstra 
{
    public static int[][] IngresarArchivo(String direccion,int aristas)
    {
        // Lee el archivo ingresado
          int[][] data = new int[aristas][3];

        try
        {
            int num = 0;
            BufferedReader bf = new BufferedReader (new FileReader(direccion));
            String Read;
            while((Read = bf.readLine()) != null)
            {
                int j = 0;
                String linea = Read;
                String [] campos = linea.split("/");            
                
                
                while(j<campos.length)
                {
                    data[num][j]=Integer.valueOf(campos[j]);
                    j++;
                }                
                num++;
            }
            
        }
        catch(Exception e)
        {
            System.out.println("Error de lectura");
        }


        return data;   
    } 
    public static void VerMatriz (int[][] matriz,int vectores)
    {   
        //Imprime la matriz de datos 
        for(int i=0;i<vectores;i++)
        {
            for(int j=0;j<3;j++)
            {
                System.out.print("  "  + matriz[i][j] + "  ");        
            }
            System.out.println("");
        } 
    }
    public static int BuscarValores(int[][] matriz,int a, int b, int vectores)
    {   
        //Retorna la distancia que hay entre el nodo a y el b buscandolo en al matriz
        for(int i=0;i<vectores;i++)
        {
            
                if(matriz[i][0]==a)
                {
                    if(matriz[i][1]==b)
                    {
                       return matriz[i][2]; 
                        
                    }
                }
            
        }
        return 999;
    }
    public static int[] Dijkstra(int[][]matriz, int n,int origen,int vectores)
    {
        //Algoritmo de Dijkstra utilizando arreglos  para guardar las distancias, estados del los nodos, etc  
        int[] flag= new int[n+1];
        int[] dist = new int[n+1];
        int i;
        int k;
        int vuelta=1;
        int mini;
        int mp =1;
        
        for(i=0; i<n;i++)
        {
            flag[i]=0;
            dist[i]=BuscarValores(matriz,origen,i,vectores);
            if(i==origen)
            {
                dist[i]=0;
            }
        }

        while(n>=vuelta)
        {
            mini=100;
            for(k=0;k<n;k++)
            {
                if(dist[k] < mini && flag[k] !=1)
                {
                    mini = dist[i];
                    mp =k;
                }
            }
            flag[mp] = 1;
            vuelta++;
            for(k=0;k<n;k++)
            {
                if(dist[mp]+BuscarValores(matriz,mp,k,vectores) < dist[k] && flag[k]!=1)
                {
                    dist[k] = dist[mp]+ BuscarValores(matriz,mp,k,vectores);
                }
            }
            
        }
        return dist;
    }
    
    
    public static void main(String[] args) 
    {
        //-----------------------------------
        
        // Datos para ingresar ------------- También se puede utilizar Scanner para ingresar datos
        // DATOS PARA CADA ESCENARIO: 
        //ESCENARIO 1:									ESCENARIO 2: 
        //int nodos = 5;								int nodos = 7;
        //int vectores = 19;								int vectores = 35;
        //int nprincipal = (aquí ya es el que usted decida como nodo principal);	int nprincipal = (aquí ya es el que usted decida como nodo principal);
 
        //ESCENARIO 3
        //int nodos = 12;
        //int vectores = 68;
        //int nprincipal = (aquí ya es el que usted decida como nodo principal);
        
        //------------------------------------
        
        int nodos = 12;
        int vectores = 68;
        int nprincipal = 0;
        
        // Ingresar archivo texto -----------------------------------
        // Para abrir el escenario, en el método IngresarArchivo, tiene que copiar la ruta del archivo .txt donde lo tenga guardado.
        int[][] matriz = new int[vectores][3];
        matriz = IngresarArchivo("C:\\Users\\chelo\\OneDrive\\Escritorio\\ULIMA\\CICLO VI\\RED. COMPU\\Trabajo Redes\\Escenarios Propuestos\\Escenario3.txt",vectores);
        //En el archivo están todas las aristas con sus respectivos datos
        VerMatriz(matriz,vectores);
        long startTime = System.nanoTime();
        int[] hola = Dijkstra(matriz,nodos,nprincipal,vectores);
        System.out.println("");
        System.out.println("-----------------------------------------");
        System.out.println("");
        System.out.println("Para el nodo " + nprincipal + ":");
        System.out.println("");
        for(int i=0;i<hola.length-1;i++)
        {
            System.out.println("Hasta el nodo: " + i + " la distancia mínima es: " +hola[i]);
        }
        long endTime = System.nanoTime() - startTime;
        Runtime runtime = Runtime.getRuntime();
        System.out.println("");
        System.out.println("Memoria usada: " + ( runtime.totalMemory() - runtime.freeMemory()) + " bytes.");
        System.out.println("Tiempo transcurrido: " + endTime/1e6 + " ms");
    } 
    
}
