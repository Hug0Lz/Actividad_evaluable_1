package ejercicio04;

import java.util.Scanner;

public class Ahorcado {

    static Scanner teclado = new Scanner(System.in);


    public static void main(String[] args) {
        Partida p;
        int opcion = 0;
        if(PartidaDAO.leerPartidaGuardada()!=null){
            System.out.println("Recupera partida guardada");
            p = PartidaDAO.leerPartidaGuardada();
        }
        else p = new Partida(5);


        System.out.println("Intentos: " + p.getIntentos());
        System.out.println(p.getLetrasAcertadas());
        while (opcion != -1 && !p.partidaGanada() && p.getIntentos()>0) {
            opcion = menu(p);
            System.out.println(p.getLetrasAcertadas());
            System.out.println("Intentos: " + p.getIntentos());
            if(opcion!=-1)PartidaDAO.guardaPartida(p);
        }

        if(p.partidaGanada()){
            System.out.println("Has ganado! " + Character.toString(0x1F601));
            PartidaDAO.guardaPartida(new Partida());
        }
        else if( p.getIntentos() == 0){
            System.out.println("Has perdido "+Character.toString(0x1F614)+" La palabra era " + p.getPalabraSecreta());
            PartidaDAO.guardaPartida(new Partida());
        }
        else System.out.println("Partida sin terminar. Resultado guardado " + Character.toString(0x1F4E5));
    }

    /**
     * {@code menu(p)} devuelve:
     *
     * <p> -1 Si se selecciona salir del juego</p>
     *
     * <p> 0 Si no se acierta ninguna letra. Se restará un intento.</p>
     *
     * <p>1 Si se acierta una letra. No se restarán intentos.</p>
     **/
    public static int menu(Partida p) {
        int opcion = 0;
        String input_opcion;
        System.out.println("(Para salir pulsa enter)");
        System.out.println("Introduce una letra: ");
        input_opcion = teclado.nextLine();
        if (input_opcion.isEmpty()) return -1;
        else {
            opcion = p.intento(input_opcion.charAt(0)) ? 1 : 0;
            return opcion;
        }
    }
}
