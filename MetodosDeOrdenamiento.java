import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

public class MetodosDeOrdenamiento {
    static int nextPos, currentPos;
    static long mov, com;

    public static int[] bubbleSort(int A[]) {
        for (int i = 0; i < A.length; i++) 
        {
            for(int j = 0; j < A.length - 1; j++) 
            {
                currentPos = A[j];
                nextPos = A[j+1];
                com++;

                if (currentPos > nextPos) 
                {
                    A[j] = nextPos;
                    A[j+1] = currentPos;
                    mov++;
                }
            }
        }
        return A;
    }

    public static int[] insertionSort(int A[]) {
        for(int i = 1; i < A.length; i++) {
            currentPos = A[i];
            int j = i-1;
            while((j >= 0) && (currentPos < A[j])) {
                A[j + 1] = A[j];
                j--;
                mov++;
                com++;
            }
            A[j + 1] = currentPos;
            com++;
            mov++;
        }
        return A;
    }

    public static int[] selectionSort(int A[]) {
        for (int i = 0; i < A.length - 1; i++) {
            int men = i;
            for (int j = i + 1; j < A.length; j++) {
                if (A[j] < A[men]) {
                    men = j;
                }
                com++;
            }
            int aux = A[i];
            A[i] = A[men];
            A[men] = aux;
            mov++;
        }
        return A;
    }

    public static int[] quickSort(int[] A, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(A, left, right);
            quickSort(A, left, pivotIndex - 1);
            quickSort(A, pivotIndex + 1, right);
        }
        return A;
    }

    public static int partition(int A[], int left, int right) {
        int pivot = A[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            com++;
            if (A[j] <= pivot) {
                i++;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                mov+=3;
            }
        }

        int temp = A[i + 1];
        A[i + 1] = A[right];
        A[right] = temp;
        mov+=3;

        return i + 1;
    }

    public static float[] binSort(float A[], int size) {
        @SuppressWarnings("unchecked")
        Vector<Float>[] bins = new Vector[size];

        for (int i = 0; i < size; i++) {
            bins[i] = new Vector<Float>();
        }

        // 2) Put array elements in different bins
        for (int i = 0; i < size; i++) {
            float idx = A[i] * size;
            bins[(int)idx].add(A[i]);
        }

        // 3) Concatenate all bins into arr[]
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (bins[i] != null) {
                //Collections.sort(bins[i]);
                for (int j = 0; j < bins[i].size(); j++) {
                    A[index++] = bins[i].get(j);
                }
                com += bins[i].size() - 1;
                mov += bins[i].size() - 1;
            }
        }
        return A;
    }

    public static int[] mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
        return arr;
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(arr, temp, left, middle);
            mergeSort(arr, temp, middle + 1, right);
            merge(arr, temp, left, middle, right);
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int middle, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left;
        int j = middle + 1;
        int k = left;

        while (i <= middle && j <= right) {
            com++;
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                mov++;
                i++;
            } else {
                arr[k] = temp[j];
                mov++;
                j++;
            }
            k++;
        }

        while (i <= middle) {
            arr[k] = temp[i];
            mov++;
            k++;
            i++;
        }
    }

    public static int[] radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
        return arr;
    }

    private static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            com++;
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            mov++;
            count[(arr[i] / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
            mov++;
        }
    }

    public static int[] heapSort(int[] arr) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(x -> x));
        for (int value : arr) {
            minHeap.add(value);
            mov++;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = minHeap.poll();
            mov++;
        }
        return arr;
    }

    public static int[] binaryTreeSort(int[] arr) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int value : arr) {
            treeSet.add(value);
            mov++;
        }

        int index = 0;
        for (int value : treeSet) {
            arr[index] = value;
            mov++;
            index++;
        }
        return arr;
    }

    public static int[] shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                com++;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                    mov++;
                }
                arr[j] = temp;
                mov++;
            }
        }
        return arr;
    }

    public static String getMovement() {
        return "\nMovimientos: " + mov;
    }

    public static String getComparison() {
        return "\nComparaciones: " + com;
    }

    public static void writeFile(String fileName, int[] arr) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (int number : arr) {
                String linea = String.format("%d\n", number);
                writer.write(linea);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String fileName, float[] arr) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (float number : arr) {
                String linea = String.format("%d\n", number);
                writer.write(linea);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void busquedaSecuencial(int[] A, int dato) {
        int i = 0;
        boolean flag = false;

        while (i < A.length && flag == false) {
            if (A[i] == dato) {
                flag = true;
                com++;
            }
            i++;
        }
        if (flag == true) {
            JOptionPane.showMessageDialog(null, "El elemento fue encontrado en la posición: " + (i-1));
        }else{
            JOptionPane.showMessageDialog(null,  "Elemento no encontrado");
            return;
        }
    }

    public static void displaySearchMenu(int[] A) {

        String menu = "MENU DE BUSQUEDAS\n";
        menu+="1.- BUSQUEDA SECUENCIAL\n";
        menu+="2.- BUSQUEDA INDEXADA\n";
        menu+="3.- BUSQUEDA BINARIA\n";

        int dato = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el valor que deseas buscar"));

        int option;

        do {
            option = Integer.parseInt(JOptionPane.showInputDialog(menu));

            if(option==1) {
                busquedaSecuencial(A, dato);
                return;
            } else if(option==2) {
                //busquedaSecuencial(A, dato);
            } else if(option==3) {
                //busquedaSecuencial(A, dato);
            }

        } while (option != 0);

    }

    public static void main(String []args) {
        int size = 500000;
        int arreglo[] = new int[size];
        int A[] = {1,2,5,32,36,7,8,1,25,7};

        float arr[] = new float[size];

        Random rand = new Random();

        for (int i = 0; i < size; i++) { //utilizar para binSort y radixSort
            arr[i] = (float) rand.nextFloat();
        }

        for (int i = 0; i < size; i++) {
            arreglo[i] = (int) rand.nextInt(size*size);
        }

        //leer el archivo de texto creado
        try { 
            try (BufferedReader br = new BufferedReader(new FileReader("numeros.txt"))) {
                String line;
                final int[] INT_ARRAY = new int[size];

                int index = 0;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(" ");
                    for (String value : values) {
                        try {
                            int intValue = Integer.parseInt(value);
                            INT_ARRAY[index] = intValue;
                            index++;
                        } catch (NumberFormatException e) {
                            // Si el valor no es un entero válido, ignóralo
                            System.err.println("Ignorando valor no válido: " + value);
                        }
                    }
                }

                String menu = "MENU DE OPCIONES\n";
                menu+="1.- INSERCION DIRECTA\n";
                menu+="2.- SELECCION\n";
                menu+="3.- BUBBLE SORT\n";
                menu+="4.- QUICK SORT\n";
                menu+="5.- BIN SORT\n";
                menu+="6.- RADIX SORT\n";
                menu+="7.- MERGE SORT\n";
                menu+="8.- SHELL SORT\n";
                menu+="9.- ARBOL BINARIO SORT\n";
                menu+="10.- HEAP SORT\n";
                menu+="0.- SALIR";
   
                int op;
   
                do {
   
                    op=Integer.parseInt(JOptionPane.showInputDialog(null, menu));
   
                    if (op==1) {
                        long startingTime = System.currentTimeMillis();
                        int auxArr[] = insertionSort(A);
                        long endingTime = System.currentTimeMillis();
                        JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovement() + getComparison());
                        writeFile("insertionSort.txt", auxArr);
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        JOptionPane.showConfirmDialog (null, "Quieres hacer una búsqueda?","SEARCH", dialogButton);
                            if(dialogButton == JOptionPane.YES_OPTION) 
                            {
                                int dialogButton2 = JOptionPane.YES_NO_OPTION;
                                JOptionPane.showConfirmDialog (null, "Quieres hacerla en el arreglo ordenado?"+"\nde lo contrario se realizará la busqueda en el arreglo desordenado","WARNING", dialogButton2);
                                if (dialogButton2 == JOptionPane.YES_OPTION) {
                                    displaySearchMenu(auxArr);
                                } else {
                                    displaySearchMenu(A);
                                }
                            if(dialogButton == JOptionPane.NO_OPTION) 
                            {
                                System.exit(0);
                            }
              }
                        displaySearchMenu(auxArr);
                    } else if (op==2) {
                        long startingTime = System.currentTimeMillis();
                        int auxArr[] = selectionSort(INT_ARRAY);
                        long endingTime = System.currentTimeMillis();
                        JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovement() + getComparison());
                        writeFile("selectionSort.txt", auxArr);
                    } else if (op==3) {
                        long startingTime = System.currentTimeMillis();
                        int auxArr[] = bubbleSort(INT_ARRAY);
                        long endingTime = System.currentTimeMillis();
                        JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovement() + getComparison());
                        writeFile("bubbleSort.txt", auxArr);
                    } else if (op==4) {
                        long startingTime = System.currentTimeMillis();
                        int auxArr[] = quickSort(INT_ARRAY, 0, size-1);
                        long endingTime = System.currentTimeMillis();
                        JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovement() + getComparison());
                        writeFile("quickSort.txt", auxArr);
                    } else if (op==5) {
                        long startingTime = System.currentTimeMillis();
                        float auxArr[] = binSort(arr, size-1);
                        long endingTime = System.currentTimeMillis();
                        JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovement() + getComparison());
                        writeFile("binSort.txt", auxArr);
                        for (float f : arr) {
                            System.out.print("["+f+"]");
                        } 
                    } else if (op==6) {
                        long startingTime = System.currentTimeMillis();
                        int auxArr[] = radixSort(INT_ARRAY);
                        long endingTime = System.currentTimeMillis();
                        JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovement() + getComparison());
                        writeFile("radixSort.txt", auxArr);
                    } else if (op==7) {
                        long startingTime = System.currentTimeMillis();
                        int auxArr[] = mergeSort(INT_ARRAY);
                        long endingTime = System.currentTimeMillis();
                        JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovement() + getComparison());
                        writeFile("mergeSort.txt", auxArr);
                    } else if (op==8) {
                        long startingTime = System.currentTimeMillis();
                        int auxArr[] = shellSort(INT_ARRAY);
                        long endingTime = System.currentTimeMillis();
                        JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovement() + getComparison());
                        writeFile("shellSort.txt", auxArr);
                    } else if (op==9) {
                        long startingTime = System.currentTimeMillis();
                        int auxArr[] = binaryTreeSort(INT_ARRAY);
                        long endingTime = System.currentTimeMillis();
                        JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovement() + getComparison());
                        writeFile("binaryTreeSort.txt", auxArr);
                    } else if (op==10) {
                        long startingTime = System.currentTimeMillis();
                        int auxArr[] = heapSort(INT_ARRAY);
                        long endingTime = System.currentTimeMillis();
                        JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovement() + getComparison());
                        writeFile("heapSort.txt", auxArr);
                    } else if (op==0){
                        System.exit(0);
                    }
                    
                } while (op!=0);

            } catch (NumberFormatException | HeadlessException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
