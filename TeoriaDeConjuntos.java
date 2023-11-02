import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class TeoriaDeConjuntos {
    
    public TeoriaDeConjuntos() {

        int[] numbers = new int[201];
        
        ArrayList<Integer> lista = new ArrayList<Integer>();

        for (int i = 0; i < 201; i++) {
            numbers[i] = i;
            lista.add(numbers[i]);
        }
        
        imprimirConjuntos(lista);
        
    }

    public static boolean esPrimo(int n) {
        //Divisible entre él mismo y 1 solamente
        if (n <= 1) {
            return false;
        }
        else if (n == 2) {
            return true;
        }
        else if (n == 0) {
            return false;
        }
        else {
            for (int i = 2; i < n; i++) 
            {
                if (n % i == 0) 
                {
                    return false;
                }
            }
            return true;
        }

    }

    public static void borrarPorBusqueda(Set<Integer> conjunto, int numero) {
        conjunto.removeIf(n -> n == numero );
        System.out.println(conjunto);
    }

    public void imprimirConjuntos(Collection<Integer> coleccion) {

        Set<Integer> conjuntoPares = new TreeSet<Integer>(); 
        
        Set<Integer> conjuntoImpares = new TreeSet<Integer>(); 
    
        Set<Integer> conjuntoPrimos = new TreeSet<Integer>();  

        Set<Integer> unionPrimosImpares = new TreeSet<Integer>(); 

        Set<Integer> unionPrimosPares = new TreeSet<Integer>(); 

        Set<Integer> interseccionImparesConPrimos = new TreeSet<Integer>();

        Set<Integer> diferenciaImparesConPrimos = new TreeSet<Integer>();

        Set<Integer> diferenciaParesConPrimos = new TreeSet<Integer>();

        Set<Integer> complementoImparesConPrimos = new TreeSet<Integer>();

        for (int n : coleccion) {
            if (n % 2 == 0) {
                conjuntoPares.add(n);
            }else{
                conjuntoImpares.add(n);
            }
        }
        
        for (int n : coleccion) {
            if (esPrimo(n)){
                conjuntoPrimos.add(n);
            }
        }
        
        unionPrimosImpares.addAll(conjuntoPrimos);
        unionPrimosImpares.addAll(conjuntoImpares);

        unionPrimosPares.addAll(conjuntoPrimos);
        unionPrimosPares.addAll(conjuntoPares);

        diferenciaImparesConPrimos.addAll(conjuntoImpares);
        diferenciaImparesConPrimos.removeAll(conjuntoPrimos);

        diferenciaParesConPrimos.addAll(conjuntoPares);
        diferenciaParesConPrimos.removeAll(conjuntoPrimos);

        complementoImparesConPrimos.addAll(conjuntoImpares);
        complementoImparesConPrimos.removeAll(conjuntoPrimos);

        interseccionImparesConPrimos.addAll(conjuntoImpares);
        interseccionImparesConPrimos.retainAll(conjuntoPrimos);
        
        /*
        for (int n : conjuntoPrimos) {
            if (conjuntoImpares.contains(n)) {
                //interseccionImparesConPrimos.add(n); //Los objetos que dos conjuntos tienen en común. puede ser interseccion.addAll(conjuntoA); interseccion.retainAll(conjuntoB);
                diferenciaImparesConPrimos.remove(n); //Si el conjunto B tiene un objeto que ya existe en conjunto A la diferencia es A sin los objetos en común de B
            } 
            else if (conjuntoPares.contains(n)) {
                diferenciaParesConPrimos.remove(n);
            }
        }
        */

        System.out.println("Pares "+conjuntoPares);
        System.out.println("\nImpares "+conjuntoImpares);
        System.out.println("\nPrimos "+conjuntoPrimos);
        System.out.println("\nUnion primos e impares "+unionPrimosImpares);
        System.out.println("\nUnion primos y pares "+unionPrimosPares);
        System.out.println("\nInterescción de Impares con Primos " + interseccionImparesConPrimos);
        System.out.println("\nDiferencia Impares con Primos " + diferenciaImparesConPrimos);
        System.out.println("\nDiferencia Pares con Primos " + diferenciaParesConPrimos);
        System.out.println("\nComplemento de Impares con Primos" + complementoImparesConPrimos);

        borrarPorBusqueda(conjuntoPares, 0);
        borrarPorBusqueda(conjuntoPares, 1);
        borrarPorBusqueda(conjuntoPares, 40);
    }

    public static void main(String [] args) {

        new TeoriaDeConjuntos();

    }
    
}
