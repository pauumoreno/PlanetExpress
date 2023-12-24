import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class: Clase Porte, que contiene los atributos(id, nave, PuertoEspacial origen/destino, precio... )
 * y metodos(numHuecosLibres, dar de alta un porte, buscar envio...) que representan la clase Porte.
 *
 * @author PAULA CAMILA MORENO RUBIO
 * @author DMYTRO KRYVITSKYY ROMANYUK
 * @version     1.0
 */
public class Porte {


    /**
     * Matriz que representa huecos del porte
     */
    private boolean[][] huecos;
    /**
     * Id del porte
     */
    private String id;
    /**
     * Nave realiza el porte
     */
    private Nave nave;
    /**
     * Puerto espacial de origen
     */
    private PuertoEspacial origen;
    /**
     * muelle de origen
     */
    private int muelleOrigen;
    /**
     * Fecha de salida del porte
     */
    private Fecha salida;
    /**
     * Puerto espacial de destino
     */
    private PuertoEspacial destino;
    /**
     * Muelle de destino
     */
    private int muelleDestino;
    /**
     * Fecha de llegada del porte
     */
    private Fecha llegada;
    /**
     * Precio del porte
     */
    private double precio;
    /**
     * Lista de envios del porte
     */
    private ListaEnvios listaEnvios;




    /**
     * TODO: Completa el constructo de la clase
     * Constructor de la clase Porte que define los siguientes parametros:
     *
     * @param id del porte
     * @param nave nave del porte
     * @param origen Puerto espacial del que sale el porte
     * @param muelleOrigen Muelle de origen
     * @param salida fecha de salida del porte
     * @param destino Puerto espacial al que llega el porte
     * @param muelleDestino Muelle destino
     * @param llegada fecha de llegada del porte
     * @param precio Precio en SSD del porte
     */
    public Porte(String id, Nave nave, PuertoEspacial origen, int muelleOrigen, Fecha salida, PuertoEspacial destino, int muelleDestino, Fecha llegada, double precio) {

        this.id = id;
        this.nave = nave;
        this.origen = origen;
        this.muelleOrigen = muelleOrigen;
        this.salida = salida;
        this.destino = destino;
        this.muelleDestino = muelleDestino;
        this.llegada = llegada;
        this.precio = precio;


    }
    public String getID() {
        return id;
    }
    public Nave getNave(){
        return nave;
    }
    public PuertoEspacial getOrigen() {
        return origen;
    }
    public int getMuelleOrigen() {
        return muelleOrigen;
    }
    public Fecha getSalida(){
        return salida;
    }
    public PuertoEspacial getDestino() {
        return destino;
    }
    public int getMuelleDestino() {
        return muelleDestino;
    }
    public Fecha getLlegada() {
        return llegada;
    }
    public double getPrecio() {
        return precio;
    }
    // TODO: Devuelve el número de huecos libres que hay en el porte
    public int numHuecosLibres() {

        int huecosLibres = 0;
        for(int i=0;i<nave.getFilas();i++){
            for(int j=0;j< nave.getColumnas();j++){
                if(!huecos[i][j]){
                    huecosLibres++;
                }
            }
        }
        return huecosLibres;

    }
    // TODO: ¿Están llenos todos los huecos?
    public boolean porteLleno() {

        return numHuecosLibres()==0;

    }
    // TODO: ¿Está ocupado el hueco consultado?
    public boolean huecoOcupado(int fila, int columna) {

        return huecos [fila][columna];

    }
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }


    /**
     * TODO: Devuelve el objeto Envio que corresponde con una fila o columna,
     * @param fila
     * @param columna
     * @return el objeto Envio que corresponde, o null si está libre o se excede en el límite de fila y columna
     */
    public Envio buscarEnvio(int fila, int columna) {

        if(fila>nave.getFilas()+1 || columna>nave.getColumnas()+1 || !huecoOcupado(fila-1, columna-1)){
            return null;
        }
        else {
            return listaEnvios.buscarEnvio(id,fila,columna);
        }

    }


    /**
     * TODO: Método que Si está desocupado el hueco que indica el envio, lo pone ocupado y devuelve true,
     *  si no devuelve false
     * @param envio
     * @return
     */
    public boolean ocuparHueco(Envio envio) {

        boolean desocupado = true;

        if(!huecoOcupado(envio.getFila()-1,envio.getColumna()-1)){
            huecos[envio.getFila()-1][envio.getColumna()-1]=true;
            listaEnvios.insertarEnvio(envio);
        }else desocupado = false;

        return desocupado;

    }


    /**
     * TODO: A través del localizador del envio, se desocupará el hueco
     * @param localizador
     * @return
     */
    public boolean desocuparHueco(String localizador) {

        boolean ocupado = true;
        if(huecos[buscarEnvio(localizador).getFila()-1][buscarEnvio(localizador).getColumna()-1]){

            huecos[buscarEnvio(localizador).getFila()-1][buscarEnvio(localizador).getColumna()-1] = false;
            listaEnvios.eliminarEnvio(localizador);
        }else ocupado = false;
        return ocupado;
    }

    /**
     * TODO: Devuelve una cadena con información completa del porte
     * @return ejemplo del formato -> "Porte PM0066 de Gaia Galactic Terminal(GGT) M5 (01/01/2023 08:15:00) a
     *  Cidonia(CID) M1 (01/01/2024 11:00:05) en Planet Express One(EP-245732X) por 13424,56 SSD, huecos libres: 10"
     */
    public String toString() {

        return "Porte "+id+" de "+origen.toStringSimple()+" M"+muelleOrigen+" ("+salida.toString()+") a "+
                destino.toStringSimple()+" M"+muelleDestino+" ("+llegada.toString()+") en"+
                nave.toStringSimple()+" por "+String.format("%.2f",precio)+ " SSD, huecos libres: "+
                numHuecosLibres();
    }


    /**
     * TODO: Devuelve una cadena con información abreviada del vuelo
     * @return ejemplo del formato -> "Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05)"
     */
    public String toStringSimple() {

        return "Porte "+id+" de "+origen.getCodigo()+" M"+muelleOrigen+" ("+salida.toString()+") a "+
                destino.getCodigo()+" M"+muelleDestino+" ("+llegada.toString()+")";
    }


    /**
     * TODO: Devuelve true si el código origen, destino y fecha son los mismos que el porte
     * @param codigoOrigen
     * @param codigoDestino
     * @param fecha
     * @return
     */
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha) {
        boolean igual;
        if(origen.getCodigo().equals(codigoOrigen) && destino.getCodigo().equals(codigoDestino) && fecha.coincide(salida)){
          igual = true;
        }else igual = false;
        return igual;
    }


    /**
     * TODO: Muestra la matriz de huecos del porte, ejemplo:
     *        A  B  C
     *      1[ ][ ][ ]
     *      2[X][X][X]
     *      3[ ][ ][ ]
     *      4[ ][X][ ]
     *     10[ ][ ][ ]
     */
    public void imprimirMatrizHuecos() {
        switch(nave.getColumnas()){
            case 1:
                System.out.println("  A"); break;
            case 2:
                System.out.println("  A  B");break;
            case 3:
                System.out.println("  A  B  C");break;
            case 4:
                System.out.println("  A  B  C  D");break;
            case 5:
                System.out.println("  A  B  C  D  E");break;
            case 6:
                System.out.println("  A  B  C  D  E  F");break;
            case 7:
                System.out.println("  A  B  C  D  E  F  G");break;
            case 8:
                System.out.println("  A  B  C  D  E  F  G  H");break;
            case 9:
                System.out.println("  A  B  C  D  E  F  G  H  I");break;
            case 10:
                System.out.println("  A  B  C  D  E  F  G  H  I  J");break;

        }

      char ocupado;
        for (int i= 0; i<nave.getFilas();i++){
            System.out.print(i+1);
            for(int j=0;j<nave.getColumnas();j++){
                if(huecoOcupado(i,j)){
                    ocupado= 'X';
                }else ocupado =' ';
                System.out.print("["+ocupado+"]");
            }
            System.out.println();
        }

    }




    /**
     * TODO: Devuelve true si ha podido escribir en un fichero la lista de envíos del porte, siguiendo las indicaciones
     *  del enunciado
     * @param fichero
     * @return
     */
    public boolean generarListaEnvios(String fichero) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fichero);
            pw.println("--------------------------------------------------");
            pw.println("-------- Lista de envíos del porte "+id+" --------");
            pw.println("--------------------------------------------------");
            pw.println(String.format("%-8"+"%-15","Hueco","Cliente"));
            for(int i = 1;i<nave.getFilas()+1;i++){
                for(int j = 1;j<nave.getColumnas()+1;j++){
                    Envio envio = buscarEnvio(i,j);
                    pw.print(String.format("%s%-7s",i,(char) (j+64)));
                    if (envio == null) {
                        pw.println();
                    } else {
                        pw.println(String.format("%-15s", envio.getCliente().toString()));
                    }
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }


    /**
     * TODO: Genera un ID de porte. Este consistirá en una cadena de 6 caracteres, de los cuales los dos primeros
     *  serán PM y los 4 siguientes serán números aleatorios.
     *  NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand
     * @return ejemplo -> "PM0123"
     */
    public static String generarID(Random rand) {
        return "PM"+ rand.nextInt(9)+rand.nextInt(9)+rand.nextInt(9)+rand.nextInt(9);
    }

    /**
     * TODO: Crea y devuelve un objeto Porte de los datos que selecciona el usuario de puertos espaciales
     *  y naves y la restricción de que no puede estar repetido el identificador, siguiendo las indicaciones
     *  del enunciado.
     *  La función solicita repetidamente los parametros hasta que sean correctos
     * @param teclado
     * @param rand
     * @param puertosEspaciales
     * @param naves
     * @param portes
     * @return
     */
    public static Porte altaPorte(Scanner teclado, Random rand,
                                  ListaPuertosEspaciales puertosEspaciales,
                                  ListaNaves naves,
                                  ListaPortes portes) {
        int muelleOrigen,muelleDestino;
        double precio;
        PuertoEspacial origen;
        PuertoEspacial destino;
        Nave nave;
        Fecha salida;
        Fecha llegada;


        origen = puertosEspaciales.seleccionarPuertoEspacial(teclado, "Ingrese código de puerto Origen: ");


        muelleOrigen=Utilidades.leerNumero(teclado,"Ingrese el muelle de  Origen (1-"+origen.getMuelles()+"): ",1,origen.getMuelles());


        destino = puertosEspaciales.seleccionarPuertoEspacial(teclado, "Ingrese código de Puerto Destino: ");


        muelleDestino=Utilidades.leerNumero(teclado,"Ingrese puerto Destino (1-"+destino.getMuelles()+"): ",1,destino.getMuelles());


        nave = naves.seleccionarNave(teclado, "Ingrese matrícula de la nave: ", origen.distancia(destino));


        do {
            salida = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de salida: ");
            llegada = Utilidades.leerFechaHora(teclado, "Introduzca fecha de llegada: ");
            if(llegada.anterior(salida)|| llegada.coincide(salida)){
                System.out.println("Llegada debe ser posterior a salida.");
            }
        }while(llegada.anterior(salida)||llegada.coincide(salida));


        precio = Utilidades.leerNumero(teclado,"Ingrese precio de porte: ",0.0,9999.0);

        Porte porte = new Porte(generarID(rand),nave,origen,muelleOrigen,salida,destino,muelleDestino,llegada,precio);

        System.out.println("Porte "+porte.getID()+" creado con éxito.");

        return porte;





    }
}
