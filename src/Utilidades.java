import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class Utilidades {

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return int numero
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int numero=0;
        boolean correcto = false;
        do {
            System.out.print(mensaje);
            if (teclado.hasNextInt()) {
                numero = teclado.nextInt();
                correcto = numero >= minimo && numero <= maximo;
            }
            teclado.nextLine();
        } while (!correcto);
        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return long numero
     */
    public static long leerNumero(Scanner teclado, String mensaje, long minimo, long maximo) {
        long numero=0;
        boolean correcto = false;
        do {
            System.out.print(mensaje);
            if (teclado.hasNextLong()) {
                numero = teclado.nextLong();
                correcto = numero >= minimo && numero <= maximo;
            }
            teclado.nextLine();
        } while (!correcto);
        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return double numero
     */
    public static double leerNumero(Scanner teclado, String mensaje, double minimo, double maximo) {
        double numero=0;
        boolean correcto = false;
        do {
            System.out.print(mensaje);
            if (teclado.hasNextDouble()) {
                numero = teclado.nextDouble();
                correcto = numero >= minimo && numero <= maximo;
            }
            teclado.nextLine();
        } while (!correcto);
        return numero;
    }

    /**
     * TODO: Solicita una letra repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return char letra
     */
    public static char leerLetra(Scanner teclado, String mensaje, char minimo, char maximo) {
        char letra=0;
        boolean correcto = false;
        do {
            System.out.print(mensaje);
            String entrada = teclado.next();
            if (entrada.length() == 1 && Character.isLetter(entrada.charAt(0))) {
                letra = entrada.charAt(0);
                correcto = letra >= minimo && letra <= maximo;
            }
            teclado.nextLine();
        } while (!correcto);
        return letra;
    }


    /**
     * TODO: Solicita una fecha repetidamente hasta que se introduzca una correcta
     * @param teclado
     * @param mensaje
     * @return Fecha
     */
    public static Fecha leerFecha(Scanner teclado, String mensaje) {
        int dia;
        int mes;
        int anio;
        do {
            System.out.print(mensaje);

            System.out.print("Ingrese el año: ");
            anio = teclado.nextInt();

            System.out.print("Ingrese el mes (1-12): ");
            mes = teclado.nextInt();

            System.out.print("Ingrese el día: ");
            dia = teclado.nextInt();

        } while (!Fecha.comprobarFecha(dia, mes, anio));



        return new Fecha(dia, mes, anio);
    }


    /**
     * TODO: Solicita una fecha y hora repetidamente hasta que se introduzcan unas correctas
     * @param teclado
     * @param mensaje
     * @return Fecha
     */
    public static Fecha leerFechaHora(Scanner teclado, String mensaje) {
        int dia;
        int mes;
        int anio;
        int hora;
        int minuto;
        int segundo;



        return new Fecha(dia, mes, anio, hora, minuto, segundo);
    }

    /**
     * TODO: Imprime por pantalla el String pasado por parámetro
     * @param teclado
     * @param s
     * @return
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.print(s);
        return teclado.next();
    }
}

