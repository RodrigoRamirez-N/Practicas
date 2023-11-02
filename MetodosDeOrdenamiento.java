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
    public static void main(String []args) {
        int size = 1000;
        int arreglo[] = new int[size];

        float arr[] = new float[size];

        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = (float) rand.nextFloat();
        }

        long startingTime = System.currentTimeMillis();
        binSort(arr, size);
        long endingTime = System.currentTimeMillis();

        /*for (int i = 0; i < size; i++) {
            System.out.print("["+arr[i]+"]");
        } */

        for (float f : arr) {
            System.out.print("["+f+"]");
        }

        System.out.println("Tiempo de ejecución: " + (endingTime-startingTime) + " ms");
        System.out.println("Movimientos: " + mov + " Comparaciones: " + com);

    }
    
}
