import java.util.ArrayList;
import java.util.Random;

class resultado{
    char[][] matriz;
    int puntaje;

    public resultado(char[][] matriz, int puntaje) {
        this.matriz = matriz;
        this.puntaje = puntaje;
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    } 
    
}

public class test {
    private static ArrayList<resultado> bestMatrices = new ArrayList<resultado>();
    public static void initialize(char[][] matriz, String[] secuencias){
        //Random random = new Random();
        for (int s = 0; s < secuencias.length; s++) {
            // Insertar la secuencia en la fila
            for (int i = 0; i < secuencias[s].length(); i++) {
                matriz[s][i] = secuencias[s].charAt(i);
            }
            boolean hayCaracter = generarGaps(matriz[s], secuencias[s]);
            //si no hay válido llamar hasta que haya uno
            while(!hayCaracter) {
                hayCaracter = generarGaps(matriz[s],secuencias[s]);
            }
            
        }
    }
    
    public static boolean generarGaps(char[] fila, String secuencia) {
        Random random = new Random();
        boolean hayCaracter = false;
        //recorrer fila
        for(int j = 0; j < fila.length; j++) {
            if(random.nextDouble() < 0.3){ //random gap ratio
                if(fila[j] == 0) {
                    char gap = '-'; 
                    int pos = random.nextInt(secuencia.length()+1);
                    // Mover los caracteres a la derecha
                    for (int k = fila.length - 1; k > pos; k--) {
                        fila[k] = fila[k - 1];
                    }
                    // Insertar el gap
                    fila[pos] = gap;
                }
            }
            //comprobar que haya caracter valido en la fila
            if (fila[j] != '-' && fila[j] != 0) {
                hayCaracter = true;
            }
        }
        return hayCaracter;
    }

    static boolean compareChars(char c1, char c2, char c3) {
        if (c1 != 0 && c2 != 0) {
            if (c1 == c2) return true;
        }
        if (c1 != 0 && c3 != 0) {
            if (c1 == c3) return true;
        }
        if (c2 != 0 && c3 != 0) {
            if (c2 == c3) return true;
        }
        return false;
    }

    public static int puntuarMatriz(char[][] matriz) {
        int puntaje = 0; // Variable para guardar el puntaje total
        int match=0, mismatch=0, gap=0;
        int comparaciones = 0;

        int maxLength = Math.max(Math.max(matriz[0].length, matriz[1].length), matriz[2].length);
        
        // Recorrer la matriz por columnas
        for (int j = 0; j < maxLength; j++) {
            comparaciones = j;
            // Comparar los caracteres de cada fila en la misma columna
            char c1 = matriz[0].length > j ? matriz[0][j] : 0; // Carácter de la primera fila
            char c2 = matriz[1].length > j ? matriz[1][j] : 0; // Carácter de la segunda fila
            char c3 = matriz[2].length > j ? matriz[2][j] : 0; // Carácter de la tercera fila

            if (compareChars(c1, c2, c3)) { // Si coinciden los caracteres, sumar el puntaje de match
                puntaje += 2;
                match++;
            } else { // Si no coinciden los caracteres, restar el puntaje de mismatch
                puntaje -= 1;
                mismatch++;
            }
            if (c1 == '-' || c2 == '-' || c3 == '-' ) { // Si hay un gap, restar el puntaje de gap
                puntaje -= 3;
                gap++;
            }
            
        }
        // Devolver el puntaje total
        System.out.println("matches: " + match);
        System.out.println("mismatches: " + mismatch);
        System.out.println("gaps: " + gap);
        System.out.println("Comparaciones: " + comparaciones);
        System.out.println("puntaje: " + puntaje);
        return puntaje;
    }

    public static resultado evaluarMatrices(String[] secuencias){
        int maxLength = Math.max(Math.max(secuencias[0].length(), secuencias[1].length()), secuencias[2].length());
        char matriz[][] = new char[3][maxLength]; //ajustar filas dependiendo al n de secuencias

        //System.out.println(matriz);

        //initialize(matriz, secuencias);

        char bestMatriz[][] = new char[0][0];
        int maxPuntaje = Integer.MIN_VALUE;

        for (int i = 0; i < 10 - bestMatrices.size(); i++) { //cantidad de iteraciones puede variar dependiendo a la mejora deseada
            System.out.println("Matriz "+i);
            for (int j = 0; j < matriz.length; j++) {
                for (int k = 0; k < matriz[j].length; k++) {
                    matriz[j][k] = 0;
                }
            }
            initialize(matriz, secuencias); //debo quedarme con las matrices de mejor puntuacion en cada iteración 
            int puntuacion = puntuarMatriz(matriz);

            if (puntuacion > maxPuntaje) {
                maxPuntaje = puntuacion;
                bestMatriz = matriz;
            }
        }
        return new resultado(bestMatriz, Math.abs(maxPuntaje));
    }

    public static void printMatriz(char[][] matriz){

        for (int j = 0; j < matriz.length; j++) {
            for (int k = 0; k < matriz[j].length; k++) {
                System.out.print(matriz[j][k]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String args[]) {
        String sec1 = "murcielago";
        String sec2 = "solidaridad";
        String sec3 = "abastecimiento";
        String[] secuencias = {sec1, sec2, sec3};

        //4 match y 7 gaps
        /* char matriz2[][] = {
            "murcielag-o-".toCharArray(),
            "solid-aridad".toCharArray(),
            "abasteci-miento".toCharArray()
        };
        puntuarMatriz(matriz2); */
        
        for (int i=0;i<10;i++)
        {
            bestMatrices.add(evaluarMatrices(secuencias));
        }
        
        // print all the matrices
        int counter = 1;
        System.out.println("-------------------");
        for (resultado matriz : bestMatrices) {
            System.out.println("Matriz: " + counter);
            System.out.println("Resultado: " + matriz.puntaje);
            printMatriz(matriz.matriz);
            counter++;
        }
        System.out.println("total de matrices guardadas " + bestMatrices.size());
    }
}
