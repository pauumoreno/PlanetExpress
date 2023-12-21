import java.util.Scanner;

/**
 * Description of the class
 *
 * @author PAULA CAMILA MORENO RUBIO
 * @author DMYTRO KRYVITSKYY ROMANYUK
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
            System.out.println(mensaje);
            dia = leerNumero(teclado,"Ingrese el día: ",1,31);
            mes = leerNumero(teclado,"Ingrese el mes: ",1,12);
            anio = leerNumero(teclado,"Ingrese el año: ",1900,3000);
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
        do{
            System.out.println(mensaje);
            dia = leerNumero(teclado,"Ingrese el día: ",1,31);
            mes = leerNumero(teclado,"Ingrese el mes: ",1,12);
            anio = leerNumero(teclado,"Ingrese el año: ",1900,3000);
            hora = leerNumero(teclado,"Ingrese la hora: ",0,23);
            minuto = leerNumero(teclado,"Ingrese el minuto: ",0,59);
            segundo = leerNumero(teclado,"Ingrese el segundo: ",0,59);
        }while (!Fecha.comprobarFecha(dia,mes,anio)||!Fecha.comprobarHora(hora,minuto,segundo));
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

