package ejercicio04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * La clase {@code Partida} guarda información sobre la partida en curso:
 *
 * <p> String {@code palabraSecreta}, que contiene la palabra a adivinar</p>
 * <p> int {@code intentos}, que contiene el número de intentos restantes</p>
 * <p> Boolean[] {@code letras}, que contiene un booleano por cada letra
 * indicando si se ha adivinado o no</p>
 * **/

public class Partida {
    private String palabraSecreta;
    private int intentos;
    private boolean[] letras;

    public Partida(){
        this.palabraSecreta = null;
        this.intentos = 0;
    }

    public Partida(int intentos) {
        this.palabraSecreta = eligePalabraAleatoria();
        this.intentos = intentos;
        this.letras = new boolean[palabraSecreta.length()];
    }

    public Partida(int intentos,String palabraSecreta) {
        this.palabraSecreta = palabraSecreta;
        this.intentos = intentos;
        this.letras = new boolean[palabraSecreta.length()];

    }

    public String getLetrasAcertadas() {
        StringBuilder palabra = new StringBuilder();
        for (int i = 0; i < letras.length; i++) {
            if (letras[i]) palabra.append(this.palabraSecreta.charAt(i));
            else palabra.append('_');
        }
        return palabra.toString();
    }

    public int getIntentos() {
        return intentos;
    }

    public String getPalabraSecreta() {
        return palabraSecreta;
    }

    public void setLetras( int posicion, Boolean b) {
        letras[posicion] = b;
    }

    public boolean intento(char letra) {
        if (palabraSecreta.contains(String.valueOf(letra))) {
            for(int i = 0; i< letras.length; i++){
                if(palabraSecreta.charAt(i) == letra) letras[i] = true;
            }
            return true;
        } else {
            --intentos;
            return false;
        }
    }

    public boolean partidaGanada() {
        for(boolean b : letras){
            if (!b)return false;
        }
        return true;
    }

    public String eligePalabraAleatoria(){
        ArrayList<String> palabras = new ArrayList<>();
        String linea;
        int numeroPalabra;
        try(BufferedReader br = new BufferedReader(new FileReader("datos/palabras.txt"))){
           while((linea = br.readLine()) != null){
               palabras.add(linea);
           }
            return palabras.get((int) (Math.random()*palabras.size()));

        }catch(IOException e){
            System.out.println("Error al elegir una palabra del archivo");
        }
        return null;
    }
}
