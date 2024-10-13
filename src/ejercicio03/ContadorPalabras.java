package ejercicio03;

import java.io.*;
import java.text.Normalizer;
import java.util.Scanner;

public class ContadorPalabras {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int numPalabras = 0;
        int numCaracteres = 0;
        int numPalabraBuscada = 0;
        String linea;
        String[] palabras;
        String palabraBuscada;

        try (BufferedReader br = new BufferedReader(new FileReader("datos/documento.txt"))) {
            System.out.println("Introduce la palabra a buscar:");
            palabraBuscada = teclado.nextLine().toLowerCase();
            while ((linea = br.readLine()) != null) {
                numCaracteres += linea.length();
                linea = Normalizer.normalize(linea, Normalizer.Form.NFD);
                linea = linea.replaceAll("\\p{Punct}", "");
                palabras = linea.toLowerCase().split(" ");
                for (String s : palabras) {
                    if (!s.isEmpty()) {
                        numPalabras++;
                        if (s.equalsIgnoreCase(palabraBuscada)) numPalabraBuscada++;
                    }
                }
            }
            System.out.println("Veces que aparece \"" + palabraBuscada + "\":  " + numPalabraBuscada);
            System.out.println("Número de palabras: " + numPalabras);
            System.out.println("Número de caracteres: " + numCaracteres);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("datos/resultado.txt"))) {
                bw.write("Veces que aparece \"" + palabraBuscada + "\":  " + numPalabraBuscada +
                        "\nNúmero de palabras: " + numPalabras +
                        "\nNúmero de caracteres: " + numCaracteres);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
    }
}
