import java.util.List; 
import java.util.Arrays; 
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set; 
import java.util.SortedSet; 
import java.util.TreeSet; 
import java.util.Collection;

public class PruebaSet44 { 

    private static final String colores[] = 
    {"rojo", "blanco", "azul", "verde", "gris", "naranja", "carne", "blanco", "cyan", "gris", "naranja","rojo","ROJO","magenta","Rojo"}; 

    public PruebaSet44() { 

        List<String> lista = Arrays.asList(colores); 

        System.out.printf("ArrayList: %s \n", lista); 

        String fixedArray[] = new String[lista.size()]; //declaro un arreglo nuevo con el tamaño que tenga la lista
        int i = 0; //indice

        for(String cStr: lista) {
            fixedArray[i] = cStr.toLowerCase(); //agrego al arreglo cada String de la lista convertido en minusculas
            i++; 
        }

        lista = Arrays.asList(fixedArray); //almaceno el arreglo corregido en la lista

        imprimirSinDuplicados(lista); //ahora manda llamar el método con los datos corregidos

    } 

  

    private void imprimirSinDuplicados(Collection<String> coleccion) { 

        Set<String> conjunto0 = new HashSet<String>(coleccion); 
        
        Set<String> conjunto1 = new LinkedHashSet<String>(coleccion); 

        SortedSet<String> conjunto2 = new TreeSet<String>(coleccion);  //SortedSet extends from Set y agrega metodos para retornar first and last elem from the set and subset

        System.out.println("\nLos valores sin duplicados son: ");

        System.out.println("\nHashSet: Ordenamiento aleatorio"); 
        for (String s: conjunto0) { 
            s.toLowerCase();
            System.out.printf ("%s  ", s); 

        } 

        System.out.println("\nLinkedHashSet: Ordenamiento encadenado tipo cola (conforme van añadidos o añadiéndose)"); 
        for (String s: conjunto1) { 
            s.toLowerCase();
            System.out.printf ("%s  ", s); 

        } 

        System.out.println("\nTreeSet: Ordenamiento lógico");
        for (String s: conjunto2) { 
            s.toLowerCase();
            System.out.printf ("%s  ", s); 

        }

        System.out.println(); 

    } 

  

    public static void main(String args[]) { 

        new PruebaSet44(); 

    } 

} 