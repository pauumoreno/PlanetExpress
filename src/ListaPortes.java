import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author PAULA CAMILA MORENO RUBIO
 * @author DMYTRO KRYVITSKYY ROMANYUK
 * @version     1.0.1
 */
public class ListaPortes {
    private Porte[] portes;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaPortes(int capacidad) {
        portes = new Porte[capacidad];
    }
    // TODO: Devuelve el número de portes que hay en la lista
    public int getOcupacion() {
        int ocupados=0;
        for (int i=0; i<portes.length;i++){
            if (getPorte(i)!=null){
                ocupados++;
            }
        }
        return ocupados;
    }
    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        return portes.length==getOcupacion();
    }

    //TODO: devuelve un porte dado un indice
    public Porte getPorte(int i) {
        return portes[i];
    }


    /**
     * TODO: Devuelve true si puede insertar el porte
     * @param porte
     * @return false en caso de estar llena la lista o de error
     */
    public boolean insertarPorte(Porte porte) { //REVISAR
        if (estaLlena()){
            return false;
        } else{
            portes[getOcupacion()]=porte;
            return true;
        }
    }


    /**
     * TODO: Devuelve el objeto Porte que tenga el identificador igual al parámetro id
     * @param id
     * @return el objeto Porte que encontramos o null si no existe
     */
    public Porte buscarPorte(String id) {
        Porte porte = null;
        for (int i = 0; i<getOcupacion(); i++){
            if (id.equals(getPorte(i).getID())){
                porte = getPorte(i);
            }
        }
        return porte;
    }

    /**
     * TODO: Devuelve un nuevo objeto ListaPortes conteniendo los Portes que vayan de un puerto espacial a otro
     *  en una determinada fecha
     * @param codigoOrigen
     * @param codigoDestino
     * @param fecha
     * @return
     */
    public ListaPortes buscarPortes(String codigoOrigen, String codigoDestino, Fecha fecha) { //REVISAR
        ListaPortes listaPortes = new ListaPortes(getOcupacion());
        for (int i = 0; i<getOcupacion(); i++){
            if (getPorte(i).coincide(codigoOrigen,codigoDestino,fecha)){
                listaPortes.insertarPorte(getPorte(i));
            }
        }
        if (listaPortes.getOcupacion()==0){
            return null;
        }else {
            return listaPortes;
        }
    }

    /**
     * TODO: Muestra por pantalla los Portes siguiendo el formato de los ejemplos del enunciado
     */
    public void listarPortes() {
        for (int i=0; i<getOcupacion();i++){
            System.out.println(getPorte(i).toString());
        }
    }


    /**
     * TODO: Permite seleccionar un Porte existente a partir de su ID, usando el mensaje pasado como argumento para
     *  la solicitud y siguiendo el orden y los textos mostrados en el enunciado, y usando la cadena cancelar para
     *  salir devolviendo null.
     *  La función solicita repetidamente hasta que se introduzca un ID correcto
     * @param teclado
     * @param mensaje
     * @param cancelar
     * @return
     */
    public Porte seleccionarPorte(Scanner teclado, String mensaje, String cancelar) {
        listarPortes();
        Porte porte = null;
        String id;
        boolean correcto=false;
        do {
            id = Utilidades.leerCadena(teclado, mensaje);
            if (id.equalsIgnoreCase(cancelar)) {
                correcto=true;
            } else {
                for (int i = 0; i < getOcupacion(); i++) {
                    if (id.equals(getPorte(i).getID())) {
                        porte = getPorte(i);
                        correcto=true;
                    }
                }
                if (porte==null){
                    System.out.println("Porte no encontrado.");
                }
            }
        }while(!correcto);
        return porte;
    }

    /**
     * TODO: Ha de escribir la lista de Portes en la ruta y nombre del fichero pasado como parámetro.
     *  Si existe el fichero, SE SOBREESCRIBE, si no existe se crea.
     * @param fichero
     * @return
     */
    public boolean escribirPortesCsv(String fichero) {
        Scanner scanner = null;
        PrintWriter pw = null;
        boolean correcto = true;
        try {
            scanner = new Scanner(new FileReader(fichero));
            pw = new PrintWriter(fichero);
            for (int i=0; i<getOcupacion();i++){
                pw.println(getPorte(i).getID()+";"+getPorte(i).getNave().getMatricula()+";"+getPorte(i).getOrigen().getCodigo()
                        + ";"+getPorte(i).getMuelleOrigen()+";"+getPorte(i).getSalida()+";"+getPorte(i).getDestino().getCodigo()
                        +";"+getPorte(i).getMuelleDestino()+";"+getPorte(i).getLlegada()+";"+getPorte(i).getPrecio());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichero "+fichero+" no encontrado.");
            correcto = false; //PREGUNTAR SI AQUI SE TENDRIA QUE CREAR EL FICHERO
        } catch (Exception e){
            System.out.println("Error en la escritura del fichero "+fichero);
            correcto = false;
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (scanner != null) {
                    scanner.close();
                }
            } catch (Exception e){
                System.out.println("Error de cierre de fichero "+fichero);
                correcto = false;
            }
        }
        return correcto;
    }

    /**
     * TODO: Genera una lista de Portes a partir del fichero CSV, usando los límites especificados como argumentos para
     *  la capacidad de la lista
     * @param fichero
     * @param capacidad
     * @param puertosEspaciales
     * @param naves
     * @return
     */
    public static ListaPortes leerPortesCsv(String fichero, int capacidad, ListaPuertosEspaciales puertosEspaciales, ListaNaves naves) {
        ListaPortes listaPortes = new ListaPortes(capacidad);
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(fichero));
            while (scanner.hasNext()){
                String linea = scanner.nextLine();
                String [] atributos = linea.split(";",9);
                Porte porte = new Porte(atributos[0],naves.buscarNave(atributos[1]),puertosEspaciales.buscarPuertoEspacial(atributos[2]),
                        Integer.parseInt(atributos[3]),Fecha.fromString(atributos[4]),puertosEspaciales.buscarPuertoEspacial(atributos[5]),
                        Integer.parseInt(atributos[6]),Fecha.fromString(atributos[7]), Double.parseDouble(atributos[8]));
               listaPortes.insertarPorte(porte);
            }

        } catch (FileNotFoundException e){
            System.out.println("Fichero "+fichero+" no encontrado.");
            return null;
        } catch (Exception e) {
            System.out.println("Error en la lectura del fichero "+fichero);
            return null;
        } finally {
            try{
                if (scanner!=null){
                    scanner.close();
                }
            } catch (Exception e){
                System.out.println("Error de cierre de fichero "+fichero);
                // PREGUNTAR SI ACA TAMBIEN VA RETURN NULL
            }
        }
        return listaPortes;
    }
}
