import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Tarea4 {

    public String[] stringArray;

    public Tarea4(String[] strArray) {
        this.stringArray = strArray;
    }

    public void numeroDeLetras(ArrayList<String> inputStr) {
        int vowelCount = 0, consCount = 0;

        for(int i = 0; i < inputStr.size(); i++) {

            String strAtIndex = (String) Array.get(inputStr, i); //Obtengo el objecto String del array de strings en el indice i

            for (int j = 0; j < strAtIndex.length(); j++) { //ahora recorro el string obtenido letra por letra

                char c = Character.toLowerCase(strAtIndex.charAt(j)); //convierto a char en minusculas en el indice j del string obtenido

                if (Character.isLetter(c)) {

                    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') { //validación si es vocal
                        vowelCount++;
                    } else {
                        consCount++;
                    }
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Vocales: " + vowelCount);
        JOptionPane.showMessageDialog(null, "Consonantes: " + consCount);

    }

    public void specialCharacter(String[] strArray) {

        int digit = 0, spaceChar = 0, specialChar = 0;

        for(int i = 0; i < strArray.length; i++) {

            String strAtIndex = (String) Array.get(strArray, i); //aplico el mismo razonamiento para poder manipular la funcion charAt

            for (int j = 0; j < strAtIndex.length(); j++) {

                char c = strAtIndex.charAt(j);
                if(Character.isDigit(c)) {
                    digit++;
                } else if(Character.isSpaceChar(c)) {
                    spaceChar++;
                } else if(!Character.isLetter(c)) {
                    specialChar++;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Número de digitos en la cadena: " + digit);
        JOptionPane.showMessageDialog(null, "Número de espacios en la cadena: " + spaceChar);
        JOptionPane.showMessageDialog(null, "Número de cáracteres especiales en la cadena: " + specialChar);
        
    }

    public void numeroDePalabras(String[] strArray) {

        String[] words;

        for (int i = 0; i < strArray.length; i++) {

            String strAtIndex = (String) Array.get(strArray, i);
            words = strAtIndex.split(" ");

            for (int j = 0; j < words.length; j++) {

                System.out.println(words[j]);

            }

            int wordsWithVowel = 0;

            for (String word : words) {

                if (word.matches("[aeiouAEIOU].*")) {
                    wordsWithVowel++;
                }

            }

            JOptionPane.showMessageDialog(null, "Cantidad de palabras que empiezan con una vocal: " + wordsWithVowel);

        }

    }

    public void invertirStrings(String[] strArray) {

        for (int i =0; i < strArray.length; i++) {

            String strAtIndex = (String) Array.get(strArray, i);

            StringBuilder reversedString = new StringBuilder(strAtIndex).reverse();

            JOptionPane.showMessageDialog(null, "Cadena invertida: " + reversedString);

        }

    }

    public static String encriptadoCesar(String texto, int shift) {

        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char currentChar = texto.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isLowerCase(currentChar) ? 'a' : 'A';
                encrypted.append((char) ((currentChar - base + shift) % 26 + base));
            } else {
                encrypted.append(currentChar);
            }
        }

        return encrypted.toString();
        
    }

    public static String desencriptadoCesar(String encrypted, int shift) {

        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < encrypted.length(); i++) {
            char currentChar = encrypted.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isLowerCase(currentChar) ? 'a' : 'A';
                int shiftedPos = (currentChar - base - shift + 26) % 26;
                decrypted.append((char) (shiftedPos + base));
            } else {
                decrypted.append(currentChar);
            }
        }

        return decrypted.toString();
        
    }

    public static String encryptJulioVerne(String text, String key) {

        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            char keyChar = key.charAt(i % key.length());
            
            encryptedText.append((char) (c ^ keyChar));
        }

        return encryptedText.toString();

    }

    public static String decryptJulioVerne(String encryptedText, String key) {

        return encryptJulioVerne(encryptedText, key);

    }

    public void nombresQueEmpiezanConVocalOCons(String[] strArray) {

        ArrayList<String> nombreConVocal = new ArrayList<String>();
        ArrayList<String> nombreConConsonante = new ArrayList<String>();

        int i=0;

        for (String word : strArray) {


            String strAtIndex = (String) Array.get(strArray, i);

            if (word.matches("[aeiouAEIOU].*")) {
                nombreConVocal.add(strAtIndex);
            } else if (word.matches("[bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ].*")) {
                nombreConConsonante.add(strAtIndex);
            }

            i++;
        }


        JOptionPane.showMessageDialog(null,"Nombres que incian con vocales: " + nombreConVocal);
        JOptionPane.showMessageDialog(null,"Nombres que inician con consonantes: " + nombreConConsonante);

    }

    public void buscarNombre(String[] strArray, String inputStr, String inputStr2) {

        ArrayList<String> nombreStartsWith = new ArrayList<String>();
        ArrayList<String> nombreEndsWith = new ArrayList<String>();

        for(int i = 0; i < strArray.length; i++) {

            String strAtIndex = (String) Array.get(strArray, i); //Obtengo el objecto String del array de strings en el indice i

            if (strAtIndex.startsWith(inputStr)) {
                nombreStartsWith.add(strAtIndex);
            } else if (strAtIndex.endsWith(inputStr2)) {
                nombreEndsWith.add(strAtIndex);
            }

        }

        JOptionPane.showMessageDialog(null,"Nombres que incian con " + inputStr + ": " + nombreStartsWith);
        JOptionPane.showMessageDialog(null,"Nombres que terminan con " + inputStr2 + ": " + nombreEndsWith);

    }

    public void buscarNombreApellido(String[] strArray, String inputStr) {
        ArrayList<String> nombresEnlistados = new ArrayList<String>();
        Pattern pattern = Pattern.compile(inputStr); //inputStr es el patrón que sirve como filtro de busqueda <, Pattern.CASE_INSENSITIVE>

        for (String fullName : strArray) {
            String[] partesNombre = fullName.split(" ");

            // Iterar sobre las partes del nombre (primer nombre, segundo nombre, apellido paterno, apellido materno)
            for (String parte : partesNombre) {
                Matcher matcher = pattern.matcher(parte);

                // Si la parte actual coincide con la expresión ingresada por el usuario, agregar el nombre/apellido a la lista
                if (matcher.find()) {
                    nombresEnlistados.add(fullName);
                    break; // Romper el bucle para no agregar el mismo nombre/apellido más de una vez
                }
            }
        }

        JOptionPane.showMessageDialog(null,"Los nombres/apellidos que contienen '" + inputStr + "' son: " + nombresEnlistados);
    }

    public static void main(String args[]) {

        ArrayList<String> inputStr; 
        inputStr.add("Frase de prueba numero 1");
        inputStr.add("Frase de prueba numero 2");
        inputStr.add("Frase de prueba numero 3");
        inputStr.add("Frase de prueba numero 4");
        inputStr.add("Frase de prueba numero 5");

        String startsW = "O", endsW = "z"; //para saber con q empieza la frase o con cual termina
        
        startsW.toUpperCase(); //convertidas a minuscula para evitar excepciones
        endsW.toLowerCase();

        int op = 0;

        String menu = "Menú de opciones\n";
        menu+= "1.- Ingresar frase nueva";
        menu += "2.- Tamaño de la frase\n";
        menu += "3.- Obtener número de letras";
        menu += "4.- Obtener dígitos, carácteres especiales y espacios de la frase";
        menu += "5.- Obtener cantidad de palabras";
        menu += "6.- Convertir String a mayúsculas";
        menu += "7.- Convertir String a minúsculas";
        menu += "8.- Palabras que inician con una vocal";
        menu += "9.- Invertir frase";
        menu += "10.- Encriptar frase";
        menu += "11.- Encriptamiento Julio Verne";
        menu += "12.- Cargar frases a arbol binario";
        menu += "13.- Realizar recorrido INORDER del arbol";
        menu += "14.- Realizar recorrido PREORDER del arbol";
        menu += "15.- Realizar recorrido POSTORDER del arbol";
        menu += "16.- Reproducir frase"; //debe elegir una frase del arreglo y reproducir su archivo de audio

        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa una opción ó 0 para salir\n"+menu));
            switch(op) {
                case 1:
                    inputStr.add(JOptionPane.showInputDialog(null,"Ingresa la frase"));
                break;

            }
        } while (op != 0);



        Tarea4 object1 = new Tarea4(inputStr);

        object1.numeroDeLetras(inputStr);
        object1.specialCharacter(inputStr);
        object1.numeroDePalabras(inputStr);
        object1.invertirStrings(inputStr);

        for (int i =0; i < inputStr.length; i++) {

            String texto = (String) Array.get(inputStr, i);

            String encryptedMsg = encriptadoCesar(texto, 2);
            JOptionPane.showMessageDialog(null,"Mensaje encriptado: " + encryptedMsg);
    
            String decryptedMsg = desencriptadoCesar(encryptedMsg, 2);
            JOptionPane.showMessageDialog(null,"Mensaje desencriptado: " + decryptedMsg);

        }

        for (int i =0; i < inputStr.length; i++) {

            String texto = (String) Array.get(inputStr, i);

            JOptionPane.showMessageDialog(null,"Mensaje en minúsculas: " + texto.toLowerCase());
            JOptionPane.showMessageDialog(null, "Mensaje en mayúsculas: " + texto.toUpperCase());
            
        }

        for (int i =0; i < inputStr.length; i++) {

            String texto = (String) Array.get(inputStr, i);

            String encryptedMsg = encryptJulioVerne(texto, "clave");
            JOptionPane.showMessageDialog(null, "Mensaje encriptado: " + encryptedMsg);
    
            String decryptedMsg = encryptJulioVerne(encryptedMsg, "clave");
            JOptionPane.showMessageDialog(null, "Mensaje desencriptado: " + decryptedMsg);

        }

        object1.nombresQueEmpiezanConVocalOCons(inputStr);
        object1.buscarNombre(inputStr, startsW, endsW);
        String texto = "Ra";
        object1.buscarNombreApellido(inputStr, texto);

    }
    
}