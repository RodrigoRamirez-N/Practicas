import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.Scanner;

public class Tarea12 {

    public static String obtenerPalabraAdivinada(String palabraSecreta, boolean[] letrasAdivinadas) {
        StringBuilder palabraAdivinada = new StringBuilder();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (letrasAdivinadas[i]) {
                palabraAdivinada.append(palabraSecreta.charAt(i));
            } else {
                palabraAdivinada.append("_");
            }
            palabraAdivinada.append(" ");
        }
        return palabraAdivinada.toString();
    }

    public static boolean palabraAdivinada(boolean[] letrasAdivinadas) {
        for (boolean adivinada : letrasAdivinadas) {
            if (!adivinada) {
                return false;
            }
        }
        return true;
    }

    public static String[] cargarPalabrasDesdeArchivo(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            Scanner lector = new Scanner(archivo);
            int cantidadPalabras = 0;
            
            while (lector.hasNextLine()) {
                lector.nextLine();
                cantidadPalabras++;
            }

            String[] palabras = new String[cantidadPalabras];
            lector.close();

            lector = new Scanner(archivo);
            int indice = 0;
            
            while (lector.hasNextLine()) {
                palabras[indice] = lector.nextLine();
                indice++;
            }
            
            lector.close();
            return palabras;
        } catch (FileNotFoundException e) {
            System.err.println("No se pudo abrir el archivo: " + e.getMessage());
            return new String[0]; // Devolver un arreglo vacío en caso de error
        }

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        String temas = "frutas\n";
        temas+="verduras\n";
        temas+="animales\n";
        temas+="roedores\n";

        System.out.println("¿Con qué tema quieres jugar? teclealo tal cual aparece\n" + temas);

        String archivo = sc.next().toLowerCase();
        
        String[] palabras = cargarPalabrasDesdeArchivo(archivo + ".txt");

        String palabraSecreta = palabras[(int) (Math.random() * palabras.length)];
        int vidas = 3;
        int vidasRestantes = vidas;
        boolean[] letrasAdivinadas = new boolean[palabraSecreta.length()];
        letrasAdivinadas[0] = true;

        while (vidasRestantes > 0) {
            System.out.println(obtenerPalabraAdivinada(palabraSecreta, letrasAdivinadas));
            System.out.println("Vidas restantes: " + vidasRestantes);
            System.out.print("Ingresa una letra: ");
            String entrada = scanner.nextLine().toLowerCase();
            char letra = entrada.charAt(0);

            boolean letraAdivinada = false;
            for (int i = 1; i < palabraSecreta.length(); i++) {
                if (palabraSecreta.charAt(i) == letra && !letrasAdivinadas[i]) {
                    letrasAdivinadas[i] = true;
                    letraAdivinada = true;
                }
            }

            if(Character.isLetter(letra)) {
                if (!letraAdivinada) {
                    vidasRestantes--;
                    System.out.println("Letra incorrecta. ¡Te quedan " + vidasRestantes + " intentos!");
                }
            } else {
                System.out.println("Solo ingresar letras");
            }

            if (palabraAdivinada(letrasAdivinadas)) {
                System.out.println("Ganaste! La palabra era: " + palabraSecreta);
                break;
            }
        }

        if (vidasRestantes == 0) {
            System.out.println("Perdiste! La palabra era: " + palabraSecreta);
        }

        scanner.close();
        
    }

}