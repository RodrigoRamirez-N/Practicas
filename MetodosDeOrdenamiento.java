import java.util.Random;
import java.util.Vector;
import java.util.*;
import java.util.Collections;

public class MetodosDeOrdenamiento {

    static int nextPos, currentPos;
    static long mov, com;

    public static void bubbleSort(int A[]) {
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
    }

    public static void insertionSort(int A[]) {
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
    }

    public static void selectionSort(int A[]) {
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
    }

    public static void quickSort(int[] A, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(A, left, right);
            quickSort(A, left, pivotIndex - 1);
            quickSort(A, pivotIndex + 1, right);
        }
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

    public static void binSort(float A[], int size) {
        if (size <= 0) {
            return;
        }
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

    }
    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
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

    public static void radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
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

    public static void heapSort(int[] arr) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(x -> x));
        for (int value : arr) {
            minHeap.add(value);
            mov++;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = minHeap.poll();
            mov++;
        }
    }

    public static void binaryTreeSort(int[] arr) {
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
    }

    public static void shellSort(int[] arr) {
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
    }

    public static String getMovAndCom() {
        return "Movimientos: " + mov + "\nComparaciones: " + com;
    }

    public static void main(String []args) {
        int size = 1000;
        int arreglo[] = new int[size];

        float arr[] = new float[size];

        Random rand = new Random();

        for (int i = 0; i < size; i++) { //utilizar para binSort y radixSort
            arr[i] = (float) rand.nextFloat();
        }

        for (int i = 0; i < size; i++) {
            arreglo[i] = (int) rand.nextInt(size*size);
        }

        String menu = "MENU DE OPCIONES\n";
        menu+="1.- INSERCION DIRECTA\n";
        menu+="2.- SELECCION\n";
        menu+="3.- BUBBLESORT\n";
        menu+="4.- QUICKSORT\n";
        menu+="5.- BINSORT\n";
        menu+="6.- RADIXSORT\n";
        menu+="7.- MERGESORT\n";
        menu+="8.- SHELLSORT\n";
        menu+="9.- ARBOL BINARIO - SORT\n";
        menu+="10.- HEAPSORT\n";
        menu+="0.- SALIR";

        int op;

        do {

            op=Integer.parseInt(JOptionPane.showInputDialog(null, menu));

            if (op==1) {
                long startingTime = System.currentTimeMillis();
                insertionSort(arreglo);
                long endingTime = System.currentTimeMillis();
                JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovAndCom());
            } else if (op==2) {
                long startingTime = System.currentTimeMillis();
                selectionSort(arreglo);
                long endingTime = System.currentTimeMillis();
                JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovAndCom());
            } else if (op==3) {
                long startingTime = System.currentTimeMillis();
                bubbleSort(arreglo);
                long endingTime = System.currentTimeMillis();
                JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovAndCom());
            } else if (op==4) {
                long startingTime = System.currentTimeMillis();
                quickSort(arreglo, 0, size-1);
                long endingTime = System.currentTimeMillis();
                JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovAndCom());
            } else if (op==5) {
                long startingTime = System.currentTimeMillis();
                binSort(arr, size-1);
                long endingTime = System.currentTimeMillis();
                JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovAndCom());
            } else if (op==6) {
                long startingTime = System.currentTimeMillis();
                radixSort(arreglo);
                long endingTime = System.currentTimeMillis();
                JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovAndCom());
            } else if (op==7) {
                long startingTime = System.currentTimeMillis();
                mergeSort(arreglo);
                long endingTime = System.currentTimeMillis();
                JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovAndCom());
            } else if (op==8) {
                long startingTime = System.currentTimeMillis();
                shellSort(arreglo);
                long endingTime = System.currentTimeMillis();
                JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovAndCom());
            } else if (op==9) {
                long startingTime = System.currentTimeMillis();
                binaryTreeSort(arreglo);
                long endingTime = System.currentTimeMillis();
                JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovAndCom());
            } else if (op==10) {
                long startingTime = System.currentTimeMillis();
                heapSort(arreglo);
                long endingTime = System.currentTimeMillis();
                JOptionPane.showMessageDialog(null, "Tiempo de ejecución: " + (endingTime-startingTime) + " ms\n" + getMovAndCom());
            } else if (op==0){
                System.exit(0);
            }
            
        } while (op!=0);

        /*
        for (float f : arr) {
            System.out.print("["+f+"]");
        } 
        
        for (int i = 0; i < size; i++) {
            System.out.print("["+arreglo[i]+"]");
        }
        */

    }
    
}
