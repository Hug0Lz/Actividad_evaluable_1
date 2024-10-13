package ejercicio02;

import java.io.*;
import java.util.Scanner;

public class JuegoAdivinanzas {
    public static void main(String[] args) {
        final int NUMERO = (int) (Math.random() * 100 + 1);
        Scanner teclado = new Scanner(System.in);
        int num_usuario = -1;
        int num_intentos = 0;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("datos/intentos.txt"))) {
            bw.write("Número aleatorio generado: " + NUMERO + ".\n");
            System.out.println("Adivina el número. Introduce un número para continuar.");

            while (num_usuario != NUMERO) {
                System.out.println("Tu número: ");
                num_usuario = Integer.parseInt(teclado.nextLine());

                if (num_usuario < NUMERO) {
                    System.out.println("Tu número es menor que el mío");
                    num_intentos++;
                    bw.write(" Intento: " + num_intentos +
                            "  número del usuario: " + num_usuario +
                            "  (su número es menor).\n");
                } else if (num_usuario > NUMERO) {
                    System.out.println("Tu número es mayor que el mío");
                    num_intentos++;
                    bw.write(" Intento: " + num_intentos +
                            "  número del usuario: " + num_usuario +
                            "  (su número es mayor).\n");
                } else {
                    System.out.println("Sí! Ese era mi número!");
                    num_intentos++;
                    bw.write(" Intento: " + num_intentos +
                            "  número del usuario: " + num_usuario +
                            "  El usuario ha acertado. " +
                            "  \nFin de la partida. Número de intentos: " + num_intentos + ".\n");
                }
            }


        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de intentos.");
        }


    }

   /* private static String leeFicheroIntentos(String ruta) {
        StringBuilder contenido = new StringBuilder();
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            while ((linea = br.readLine()) != null) {
                contenido.append(linea);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el fichero de intentos");
        }


        return contenido.toString();
    }

    private static boolean escribeFicheroIntentos(String ruta, String contenido) {
        try (FileWriter fw = new FileWriter(ruta)) {
            System.out.println("CONTENIDO: " + leeFicheroIntentos(ruta));
            fw.append(leeFicheroIntentos(ruta));
            fw.append(contenido);

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero de intentos.");
        }

        return true;
    }*/

}
