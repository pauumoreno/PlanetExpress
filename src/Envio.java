import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author PAULA CAMILA MORENO RUBIO
 * @author DMYTRO KRYVITSKYY ROMANYUK
 * @version     1.0.3
 */
public class Envio {

    private String localizador;
    private Porte porte;
    private Cliente cliente;
    private int fila;
    private int columna;
    private double precio;

    /**
     * Constructor of the class
     *
     * @param localizador
     * @param porte
     * @param cliente
     * @param fila
     * @param columna
     * @param precio
     */
    public Envio(String localizador, Porte porte, Cliente cliente, int fila, int columna, double precio) {
        this.localizador = localizador;
        this.porte = porte;
        this.cliente = cliente;
        this.fila = fila;
        this.columna = columna;
        this.precio = precio;
    }
    public String getLocalizador() {
        return localizador;
    }
    public Porte getPorte() {
        return porte;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public int getFila() {
        return fila;
    }
    public int getColumna() {
        return columna;
    }
    // TODO: Ejemplos: "1A" para el hueco con fila 1 y columna 1, "3D" para el hueco con fila 3 y columna 4
    public String getHueco() {
        final String[] abecedario = {" ","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        return abecedario[columna];
    }
    public double getPrecio() {
        return precio;
    }
    //TODO: Texto que debe generar: Envío PM1111AAAABBBBC para Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05) en hueco 6C por 13424,56 SSD
    public String toString() {
        return "Envío "+localizador+" para Porte "+porte.getID()+" de "+porte.getOrigen()+" "+porte.getMuelleOrigen()+" "+porte.getSalida()+" a "+porte.getDestino()+" "+porte.getMuelleDestino()+" "+porte.getLlegada()+" en hueco "+getHueco()+" por "+precio+" SSD";
    }

    // TODO: Cancela este envío, eliminándolo de la lista de envíos del porte y del cliente correspondiente
    public boolean cancelar() {
        return cliente.cancelarEnvio(localizador) && porte.desocuparHueco(localizador);
    }

    /**
     * TODO: Método para imprimir la información de este envío en un fichero que respecta el formato descrito en el
     *  enunciado
     * @param fichero
     * @return Devuelve la información con el siguiente formato como ejemplo ->
     *     -----------------------------------------------------
     *     --------- Factura del envío PM1111AAAABBBBC ---------
     *     -----------------------------------------------------
     *     Porte: PM0066
     *     Origen: Gaia Galactic Terminal (GGT) M5
     *     Destino: Cidonia (CID) M1
     *     Salida: 01/01/2023 08:15:00
     *     Llegada: 01/01/2024 11:00:05
     *     Cliente: Zapp Brannigan, zapp.brannigan@dop.gov
     *     Hueco: 6C
     *     Precio: 13424,56 SSD
     */
    public boolean generarFactura(String fichero) {
        try (PrintWriter factura=new PrintWriter(fichero)) {
            factura.println("-----------------------------------------------------");
            factura.println("--------- Factura del envío "+localizador+" ---------");
            factura.println("-----------------------------------------------------");
            factura.println("Porte: "+porte.getID());
            factura.println("Origen: "+porte.getOrigen()+" "+porte.getMuelleOrigen());
            factura.println("Destino: "+porte.getDestino()+" "+porte.getMuelleDestino());
            factura.println("Salida: "+porte.getSalida());
            factura.println("Llegada: "+porte.getLlegada());
            factura.println("Cliente: "+cliente.toString());
            factura.println("Hueco: "+getHueco());
            factura.println("Precio: "+getPrecio());
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }



    /**
     *	TODO: Genera un localizador de envío. Este consistirá en una cadena de 15 caracteres, de los cuales los seis
     *   primeros será el ID del porte asociado y los 9 siguientes serán letras mayúsculas aleatorias. Ejemplo: PM0123ABCD
     *   NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand
     * @param idPorte
     * @return
     */
    public static String generarLocalizador(Random rand, String idPorte) {
        StringBuilder localizador = new StringBuilder(idPorte);
        char letraAleatoria;
        for (int i=0; i<9;i++){
            letraAleatoria= (char) ('A'+rand.nextInt(26));
            localizador.append(letraAleatoria);
        }
        return localizador.toString();
    }


    /**
     * TODO: Método para crear un nuevo envío para un porte y cliente específico, pidiendo por teclado los datos
     *  necesarios al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     *  La función solicita repetidamente los parámetros hasta que sean correctos
     * @param teclado
     * @param rand
     * @param porte
     * @param cliente
     * @return Envio para el porte y cliente especificados (podría hacer return de un null)
     */
    public static Envio altaEnvio(Scanner teclado, Random rand, Porte porte, Cliente cliente) {
        int fila=0;
        int columna=0;
        double precio=0;
        Envio nuevoEnvio=null;
        if (porte.porteLleno()){
            System.out.println("El porte seleccionado está lleno");
        } else{
            do {
                System.out.println("A continuación se mostrarán los huecos libres del porte para que elija un hueco vacío:");
                porte.imprimirMatrizHuecos();
                fila = Utilidades.leerNumero(teclado, "Fila del hueco: ", 1, 26);
                columna = Utilidades.leerNumero(teclado, "Columna del hueco: ", 1, 26);
                precio = Utilidades.leerNumero(teclado, "Precio del envío (mínimo " + porte.getPrecio() + " SSD): ", porte.getPrecio(), 100000000.00);
                nuevoEnvio = new Envio(generarLocalizador(rand, porte.getID()), porte, cliente, fila, columna, precio);
                if (porte.ocuparHueco(nuevoEnvio)) {  //el metodo ocuparHueco lee si esta desocupado el hueco
                    System.out.println("Envío " + nuevoEnvio.getLocalizador() + " creado correctamente");
                } else {
                    System.out.println("El hueco seleccionado esta ocupado.");
                }
            }while (!porte.ocuparHueco(nuevoEnvio));
        }
        return nuevoEnvio;
    }
}