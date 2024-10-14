package ejercicio04;

import java.io.*;
/**
 * La clase {@code PartidaDAO} tiene los siguientes métodos:
 *
 * <p> {@code leerPartidaGuardada()}, que devuelve
 * la partida guardada anteriormente o null si no existe</p>
 * <p> {@code guardarPartida(Partida p)} que guarda
 * una partida pasada por parámetro en el fichero</p>
 * **/
public class PartidaDAO {
    final private static String rutaArchivoPartida = "datos/estado_juego.txt";


    public static boolean guardaPartida(Partida p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivoPartida))) {

            if (p.getIntentos() != 0 && p.getPalabraSecreta() != null) {
                bw.write(p.getIntentos());
                bw.write(p.getPalabraSecreta());
                bw.newLine();
                bw.write(p.getLetrasAcertadas());
            } else bw.close();

            return true;
        } catch (IOException e) {
            System.out.println("Error guardando la partida");
            return false;
        }
    }

    public static Partida leerPartidaGuardada() {
        int intentos;
        String palabraSecreta;
        String letrasAcertadas;
        Partida partida = null;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivoPartida))) {
            intentos = br.read();
            palabraSecreta = br.readLine();
            letrasAcertadas = br.readLine();
            if (palabraSecreta != null && intentos != 0) {
                partida = new Partida(intentos, palabraSecreta);
                for (int i = 0; i < letrasAcertadas.length(); i++) {
                    partida.setLetras(i, letrasAcertadas.charAt(i) != '_');
                }
                return partida;
            }
            return null;

        } catch (IOException e) {
            System.out.println("Error al leer la partida guardada");
            return null;
        }
    }


}
