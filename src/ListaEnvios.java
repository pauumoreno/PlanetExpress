import java.io.*;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0.1
 */
public class ListaEnvios {
    private Envio[] envios;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaEnvios(int capacidad) {
        envios = new Envio[capacidad];

    }
    // TODO: Devuelve el número de envíos que hay en la lista
    public int getOcupacion() {
        int ocupados=0;
        for (int i=0; i<envios.length;i++){
            if (getEnvio(i)!=null){
                ocupados++;
            }
        }
        return ocupados;

    }
    // TODO: ¿Está llena la lista de envíos?
    public boolean estaLlena() {
        return envios.length==getOcupacion();

    }
    //TODO: Devuelve el envio dado un indice
    public Envio getEnvio(int i) {
        return envios[i];
        
    }

    /**
     * TODO: insertamos un nuevo envío en la lista
     * @param envio
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarEnvio(Envio envio) {

        if (estaLlena()){
            return false;
        } else{
            envios[getOcupacion()]=envio;
            
            //ocupacion++ aumenta la ocupacion 
            return true;
        }
    }

    /**
     * TODO: Buscamos el envio a partir del localizador pasado como parámetro
     * @param localizador
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String localizador) {

        Envio envio= null;
        for (int i = 0; i<getOcupacion(); i++){
            if (localizador.equals(getEnvio(i).getLocalizador())){
                envio = getEnvio(i);
            }
        }
        return envio;
    }

    /**
     * TODO: Buscamos el envio a partir del idPorte, fila y columna pasados como parámetros
     * @param idPorte
     * @param fila
     * @param columna
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String idPorte, int fila, int columna) {
        int j = 0;
        int salida = 0;
        for(int i=0;i<getOcupacion();i++){
            if(idPorte.equals(getEnvio(i).getPorte().getID())){
                if(fila==getEnvio(i).getFila() && columna==getEnvio(i).getColumna()){
                    salida = 1;
                    j = i;
                }
            }
        }
        if (salida == 1) {
            return getEnvio(j);
        } else {
            return null;
        }
    }

    /**
     * TODO: Eliminamos un envio a través del localizador pasado por parámetro
     * @param localizador
     * @return True si se ha borrado correctamente, false en cualquier otro caso
     */
    public boolean eliminarEnvio (String localizador) {
        Envio envio = buscarEnvio(localizador);
        if(envio!=null){
            for(int i=0;i<getOcupacion();i++){
                if(envio.equals(getEnvio(i))){
                    for(int j=i;j<getOcupacion()-1;j++){
                        envios[j]=envios[j+1];
                    }
                    
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * TODO: Muestra por pantalla los Envios de la lista, con el formato que aparece
     * en el enunciado
     */
    public void listarEnvios() {
        for(int i=0;i<envios.length;i++){
            System.out.println(getEnvio(i).toString());
        }

    }

    /**
     * TODO: Permite seleccionar un Envio existente a partir de su localizador, usando el mensaje pasado como argumento
     *  para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente hasta que se introduzca un localizador correcto
     * @param teclado
     * @param mensaje
     * @return
     */
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        Envio envio = null;
        boolean volver = true;
        String buscar;

        do {
            System.out.print(mensaje);
            buscar = teclado.nextLine();
            for(int i = 0;i<getOcupacion();i++) {
                if(buscar.equals(getEnvio(i).getLocalizador())) {
                    envio = getEnvio(i);
                    volver = false;
                }
            }
            if (envio == null) {
                System.out.println("Localizador incorrecto.");
            }
        } while (volver == true);


        return envio;
    }



    /**
     * TODO: Añade los Envios al final de un fichero CSV, SIN SOBREESCRIBIR la información
     * @param fichero
     * @return
     */
    public boolean aniadirEnviosCsv(String fichero) {
        PrintWriter pw = null;
        Scanner scanner = null;
        String[] infoEnvio;
        boolean puedeEscribir=true;
        boolean resultado = true;
        try {
            // De esta forma no se sobre escribiran los datos, y se pondran al final del archivo con append:true
            pw = new PrintWriter(new FileWriter(fichero, true));
            scanner = new Scanner(new FileReader(fichero));
            for (int i=0; i<getOcupacion();i++){
                while(scanner.hasNext()){
                    infoEnvio = scanner.next().split(";", 6);
                    if (infoEnvio[0].equals(getEnvio(i).getLocalizador())){
                        puedeEscribir=false;
                    }
                    if(scanner.hasNextLine()) {
                        scanner.nextLine();
                    }
                }
                if(puedeEscribir){
                    pw.println(getEnvio(i).getLocalizador()+";"+getEnvio(i).getPorte().getID()+";"+
                            getEnvio(i).getCliente().getEmail()+";"+getEnvio(i).getFila()+";"+getEnvio(i).getColumna()+";"+
                            getEnvio(i).getPrecio());

                }
                scanner = new Scanner(new FileReader(fichero));
                puedeEscribir=true;
            }

        } catch (FileNotFoundException e){
            System.out.println("Fichero "+fichero+" no encontrado.");
            resultado = false;
        } catch (Exception e) {
            System.out.println("Error de lectura de fichero "+fichero);
            resultado = false;

        } finally {

            try{
                if (pw !=null){
                    pw.close();
                }
            }catch(Exception exc){
            System.out.println("Error de cierre de fichero "+fichero);
            resultado = false;

            }

        }
        return resultado;
    }

    /**
     * TODO: Lee los Envios del fichero CSV y los añade a las listas de sus respectivos Portes y Clientes
     * @param ficheroEnvios
     * @param portes
     * @param clientes
     */
    public static void leerEnviosCsv(String ficheroEnvios, ListaPortes portes, ListaClientes clientes) {
        Scanner sc = null;
        Envio envio;
        Porte porte;
        Cliente cliente;
        try {
            sc = new Scanner(new FileReader(ficheroEnvios));
            while (sc.hasNext()) {
                String linea = sc.nextLine();
                String[] datos = linea.split(";", 6);

                envio = new Envio(datos[0], portes.buscarPorte(datos[1]), clientes.buscarClienteEmail(datos[2]),
                        Integer.parseInt(datos[3]), Integer.parseInt(datos[4]), Double.parseDouble(datos[5]));


                porte = portes.buscarPorte(datos[1]);
                porte.ocuparHueco(envio);
                cliente = clientes.buscarClienteEmail(datos[2]);
                cliente.aniadirEnvio(envio);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero "+ficheroEnvios);
        } catch(Exception e){
            System.out.println("Error en la lectura del fichero "+ficheroEnvios);

        }finally {
            try {
                if (sc != null) {
                    sc.close();
                }
            } catch (Exception exc) {
                System.out.println("Error en el cierre del fichero "+ ficheroEnvios);
            }

        }
    }
}

