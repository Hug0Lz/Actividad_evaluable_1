package ejercicio01;
/*Ejercicio 01 Portafolio*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.Normalizer;
import java.util.Scanner;

public class CifradoCesar {
    final static String ALFABETO = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int clave;
        int opcion = -1;
        String mensaje;

        while (opcion != 0){
            System.out.println("Cifrador / Descifrador César");
            System.out.println("1 - Cifrar");
            System.out.println("2 - Descifrar");
            System.out.println("0 - Salir");


        switch (opcion = Integer.parseInt(teclado.nextLine())) {
            case 1:
                System.out.println("Introduce la clave de cifrado: ");
                clave = Integer.parseInt(teclado.nextLine());

                mensaje = leeMensajeFichero("datos/mensajeOriginal.txt");

                if (guardaRespuestaFichero(cifrar(mensaje, clave), "datos/mensajeCifrado.txt")) {
                    System.out.println("Archivo cifrado guardado.");
                } else System.out.println("Error creando el archivo cifrado.");
                break;

            case 2:
                System.out.println("Introduce la clave de descifrado: ");
                clave = Integer.parseInt(teclado.nextLine());
                mensaje = leeMensajeFichero("datos/mensajeCifrado.txt");
                if(guardaRespuestaFichero(descifrar(mensaje, clave), "datos/mensajeDescifrado.txt")){
                    System.out.println("Archivo descifrado guardado.");
            } else System.out.println("Error creando el archivo descifrado.");
                break;
        }
        }
    }


    public static String cifrar(String mensaje, int clave) {
        StringBuilder nuevoMensaje = new StringBuilder();
        mensaje = mensaje.toUpperCase();
        mensaje = Normalizer.normalize(mensaje, Normalizer.Form.NFD);
        mensaje = mensaje.replaceAll("\\p{M}","");
        mensaje = mensaje.replaceAll("\\p{Punct}","");

        for (int i = 0; i < mensaje.length(); i++) {
            int posicion = ALFABETO.indexOf(mensaje.charAt(i));
            int nuevaPosicion = (posicion + clave) % ALFABETO.length();
            if (mensaje.charAt(i) == ' ') {

                nuevoMensaje.append(" ");
            } else {
                nuevoMensaje.append(ALFABETO.charAt(nuevaPosicion));
            }
        }

        return nuevoMensaje.toString();
    }

    public static String descifrar(String mensajeCifrado, int clave) {
        StringBuilder nuevoMensaje = new StringBuilder();
        mensajeCifrado = mensajeCifrado.toUpperCase();

        for (int i = 0; i < mensajeCifrado.length(); i++) {
            int posicion = ALFABETO.indexOf(mensajeCifrado.charAt(i));
            int nuevaPosicion = (posicion - clave) % ALFABETO.length();
            if (nuevaPosicion < 0) nuevaPosicion = nuevaPosicion + ALFABETO.length();
            if (mensajeCifrado.charAt(i) == ' ') {
                nuevoMensaje.append(' ');
            } else {
                nuevoMensaje.append(ALFABETO.charAt(nuevaPosicion));
            }
        }

        return nuevoMensaje.toString();
    }

    public static boolean guardaRespuestaFichero(String respuesta, String rutaFichero) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFichero))) {
            bw.write(respuesta);
            bw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String leeMensajeFichero(String ruta) {
        Scanner teclado = new Scanner(System.in);
        int clave;
        StringBuilder mensaje = new StringBuilder();
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            while ((linea = br.readLine()) != null) mensaje.append(linea);
            return mensaje.toString();
        } catch (Exception e) {
            System.out.println("Error en el acceso o lectura del archivo.");
            return null;
        }


    }
}
