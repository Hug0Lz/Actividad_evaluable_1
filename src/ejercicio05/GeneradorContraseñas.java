package ejercicio05;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GeneradorContraseñas {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String[] contrasenas;
        int passNum;
        int longitud;
        char opcion;

        System.out.println("¿Cúantas contraseñas quieres generar?: ");
        passNum = Integer.parseInt(teclado.nextLine());
        contrasenas = new String[passNum];

        for (int i = 0; i < passNum; i++) {
            System.out.println("Introduce la longitud de la contraseña " + (i + 1) + ": ");
            longitud = Integer.parseInt(teclado.nextLine());
            contrasenas[i] = generar(longitud);
        }

        guardar(contrasenas);

        contrasenas = leer();
        System.out.println("¿Quieres mostrar las contraseñas por pantalla?(s/n)");
        opcion = teclado.nextLine().charAt(0);
        if(opcion == 's'){
            for(String s  : contrasenas){
                System.out.println(s);
            }
        }
    }
/**
 * El método  {@code generar(int l)} devuelve una contraseña de la longitud especificada por parámetros.
 * **/
    public static String generar(int longitud) {
        String abecedario = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890" +
                "!¡\\#$%&'()*+,-./:;<=>¿?@[{]}^_´`|" +
                "áéíóúàèìòùÁÉÍÓÚÀÈÌÒÙÄËÏÖÜäëïöü";
        Random rdn = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(abecedario.charAt(rdn.nextInt(abecedario.length())));
        }
        return sb.toString();
    }
/**
 * El método  {@code guardar(String[] c) } guarda un array de contraseñas pasado por parámetros en contraseñas.txt
 * Devuelve true si se guardaron las contraseñas.
 * Devuelve false si no se guardaron las contraseñas.
 * **/
    public static boolean guardar(String[] c) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("datos/contraseñas.txt"))) {
            for (String s : c) {
                bw.write(s);
                bw.newLine();
            }
            bw.close();
            return true;

        } catch (IOException e) {
            System.out.println("Error al guardar las contraseñas en el archivo.");
            return false;
        }
    }
/**
 * El método {@code leer()} lee el archivo contraseñas.txt y devuelve un String[] con ellas.
 * En caso de error de lectura devuelve null.
 * **/
    public static String[] leer(){
        String linea = "";
        ArrayList<String> contrasenas = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("datos/contraseñas.txt"))){
            while((linea = br.readLine())!=null) contrasenas.add(linea);
        return contrasenas.toArray(new String[0]);
        }catch (IOException e){
            System.out.println("No se pudo leer el archivo de contraseñas");
            return null;
        }
    }
}
