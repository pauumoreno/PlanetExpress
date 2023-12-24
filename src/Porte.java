import java.io.FileNotFoundException;
import java.io.IOException;
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
        listaEnvios = new ListaEnvios(nave.getColumnas()*nave.getFilas());
        huecos = new boolean[nave.getColumnas()][nave.getFilas()];

        huecos = new boolean[nave.getFilas()][nave.getColumnas()];

    }

    /**
     * Getter de atributo id
     * @return id de porte
     */
    public String getID() {
        return id;
    }

    /**
     * Getter de atributo  nave
     * @return nave del porte
     */
    public Nave getNave(){
        return nave;
    }

    /**
     * Getter del origen
     * @return Puerto espacial de origen del porte
     */
    public PuertoEspacial getOrigen() {
        return origen;
    }

    /**
     * Getter de muelle de origen
     * @return Muelle de origen
     */
    public int getMuelleOrigen() {
        return muelleOrigen;
    }

    /**
     * Gettre de salida
     * @return fecha de salida del porte
     */
    public Fecha getSalida(){
        return salida;
    }

    /**
     * Getter del puerto espacial del porte
     * @return Puerto Espacial de destino del porte
     */
    public PuertoEspacial getDestino() {
        return destino;
    }

    /**
     * Getter del puerto espacial de destino
     * @return Muelle del destino
     */
    public int getMuelleDestino() {
        return muelleDestino;
    }

    /**
     * Getter de fecha de llegada
     * @return fecha de llegada del porte
     */
    public Fecha getLlegada() {
        return llegada;
    }

    /**
     * Getter del precio de porte
     * @return Precio del porte
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Metodo que devuelve numero de hhuecos libres
     * @return Devuelve el número de huecos libres que hay en el porte
     */
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

    /**
     * Metodo que comprueba si el porte esta lleno
     * @return True si esta lleno, false si tiene huecos libres
     */
    // TODO: ¿Están llenos todos los huecos?
    public boolean porteLleno() {

        return numHuecosLibres()==0;

    }

    /**
     * Metodo que comprueba si el hueco esta ocupado
     * @param fila filas de la matriz
     * @param columna columnas de la matriz
     * @return true si esta ocupado, false en caso contrario
     */
    // TODO: ¿Está ocupado el hueco consultado?
    public boolean huecoOcupado(int fila, int columna) {

        return huecos [fila][columna];

    }

    /**
     * Metodo que busca un envio en la lista de envios del porte
     * @param localizador localizador del envio
     * @return Envio del porte con el localizador que se ha introducido como parametro
     */
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }


    /**
     * TODO: Devuelve el objeto Envio que corresponde con una fila o columna,
     * @param fila fila del hueco
     * @param columna columna del hueco
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
     * @param envio envio para ocupar el hueco
     * @return devuelve valor booleno en funcion a si a podido ocupar el hueco o no
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
     * @param localizador localizador del envio que se quiere cancelar
     * @return devuelve valor booleno en funcion a si a podido desocupar el hueco o no
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
     * @param codigoOrigen codigo del puerto espacial de origen
     * @param codigoDestino codigo del puerto espacial de destino
     * @param fecha fecha del porte
     * @return valor booleano en funcion a si coincide o no el porte
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
     * @param fichero fichero en el que se gusrada la lista de envios
     *@return valor booleano en funcion a si se ha podido guardar la lista de envios o no
     */
    public boolean generarListaEnvios(String fichero) {
        PrintWriter pw = null;
        boolean correcto = true;
        try {
            pw = new PrintWriter(fichero);
            pw.println("--------------------------------------------------");
            pw.println("-------- Lista de envíos del porte "+id+" --------");
            pw.println("--------------------------------------------------");
            pw.printf("%-8s%-15s%n", "Hueco", "Cliente");
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
        } catch (FileNotFoundException e) {
            correcto= false;
        } finally{
            if (pw != null){
                pw.close();
            }
        }
        return correcto;
    }


    /**
     * TODO: Genera un ID de porte. Este consistirá en una cadena de 6 caracteres, de los cuales los dos primeros
     *  serán PM y los 4 siguientes serán números aleatorios.
     *  NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand genera numeros aleatorios
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
     * @param teclado objeto de la clase Scanner para leer datods por teclado
     * @param rand genera numeros aleatorios
     * @param puertosEspaciales lista de puertos espaciales
     * @param naves lista de naves
     * @param portes lista de portes
     * @return Objeto de la clase Porte
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

        muelleOrigen=Utilidades.leerNumero(teclado,"Ingrese el muelle de Origen (1-"+origen.getMuelles()+"): ",1,origen.getMuelles());

        destino = puertosEspaciales.seleccionarPuertoEspacial(teclado, "Ingrese código de puerto Destino: ");

        muelleDestino=Utilidades.leerNumero(teclado,"Ingrese codigo de puerto Destino (1-"+destino.getMuelles()+"): ",1,destino.getMuelles());

        naves.mostrarNaves();
        nave = naves.seleccionarNave(teclado, "Ingrese matrícula de la nave: ", origen.distancia(destino));


        do {
            salida = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de salida: ");
            llegada = Utilidades.leerFechaHora(teclado, "Introduzca fecha de llegada: ");
            if(llegada.anterior(salida)|| llegada.coincide(salida)){
                System.out.println("Llegada debe ser posterior a salida.");
            }
        }while(llegada.anterior(salida)||llegada.coincide(salida));


        precio = Utilidades.leerNumero(teclado,"Ingrese precio de pasaje: ",0.0,9999.0);

        Porte porte = new Porte(generarID(rand),nave,origen,muelleOrigen,salida,destino,muelleDestino,llegada,precio);
        portes.insertarPorte(porte);


        System.out.println("Porte "+porte.getID()+" creado con éxito.");

        return porte;





    }
}
