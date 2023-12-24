import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
/**
 * Clase principal de Planet Express App, la práctica de Taller de Programación
 * Intergra los metodos cargar datos, gusrdar datos o buscar porte entre otros
 * Ademas del menu y el main del proyecto
 * y el resto de funcionalidades que hacen que este se pueda ejecutar
 *
 * @author      Taller de Progamación
 * @version     1.0.0.1
 */
public class PlanetExpress {
    /**
     * Numero maximo de portes
     */
    private final int maxPortes;
    /**
     * Numero maximo de naves
     */
    private final int maxNaves;
    /**
     * Numero maximo de clientes
     */
    private final int maxClientes;
    /**
     * Número maximo de envios por cliente
     */
    private final int maxEnviosPorCliente;
    /**
     * Lista de puertos espaciales
     */
    private ListaPuertosEspaciales listaPuertosEspaciales;
    /**
     * Número maximo de puertos espaciales
     */
    private final int maxPuertosEspaciales;
    /**
     * Lista de naves
     */
    private ListaNaves listaNaves;
    /**
     * Lista de clientes
     */
    private ListaClientes listaClientes;
    /**
     * Lista de portes
     */
    private ListaPortes listaPortes;
    /**
     * TODO: Rellene el constructor de la clase
     * Constructor de la clase
     * @param maxPuertosEspaciales Máximo número de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * @param maxNaves Máximo número de naves que tendrá la lista de naves de PlanetExpress App.
     * @param maxPortes Máximo número de portes que tendrá la lista de portes de PlanetExpress App.
     * @param maxClientes Máximo número de clientes que tendrá la lista de clientes de PlanetExpress App.
     * @param maxEnviosPorCliente Máximo número de envíos por cliente.
     */
    public PlanetExpress(int maxPuertosEspaciales, int maxNaves, int maxPortes, int maxClientes, int maxEnviosPorCliente) {
        this.maxPuertosEspaciales=maxPuertosEspaciales;
        this.maxNaves=maxNaves;
        this.maxPortes=maxPortes;
        this.maxClientes=maxClientes;
        this.maxEnviosPorCliente=maxEnviosPorCliente;
    }
    /**
     * TODO: Metodo para leer los datos de los ficheros específicados en el enunciado y los agrega a
     *  la información de PlanetExpress (listaPuertosEspaciales, listaNaves, listaPortes, listaClientes)
     * Metodo para leer los datos de los ficheros específicados en el enunciado y los agrega a
     * la información de PlanetExpress (listaPuertosEspaciales, listaNaves, listaPortes, listaClientes)
     * @param ficheroPuertos Fichero de puertos espaciales
     * @param ficheroNaves Fichero de naves
     * @param ficheroPortes Fichero de portes
     * @param ficheroClientes fichero de clientes
     * @param ficheroEnvios fichero de envios
     */
    public void cargarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
        listaPuertosEspaciales = ListaPuertosEspaciales.leerPuertosEspacialesCsv(ficheroPuertos,maxPuertosEspaciales);
        listaNaves = ListaNaves.leerNavesCsv(ficheroNaves,maxNaves);
        listaPortes = ListaPortes.leerPortesCsv(ficheroPortes,maxPortes,listaPuertosEspaciales,listaNaves);
        listaClientes = ListaClientes.leerClientesCsv(ficheroClientes,maxClientes,maxEnviosPorCliente);
        ListaEnvios.leerEnviosCsv(ficheroEnvios,listaPortes,listaClientes);



    }
    /**
     * TODO: Metodo para almacenar los datos de PlanetExpress en los ficheros .csv especificados
     *  en el enunciado de la práctica
     * Metodo para almacenar los datos de PlanetExpress en los ficheros .csv especificados
     *  en el enunciado de la práctica
     *@param ficheroPuertos Fichero de puertos espaciales
     * @param ficheroNaves Fichero de naves
     * @param ficheroPortes Fichero de portes
     * @param ficheroClientes fichero de clientes
     * @param ficheroEnvios fichero de envios
     */
    public void guardarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
        PrintWriter pw = null;
        boolean correcto = true;
        try {
            pw=new PrintWriter(new FileWriter(ficheroEnvios));
            pw.print("");
        } catch (FileNotFoundException e) {
            System.out.println("Fichero "+ficheroEnvios+" no encontrado");
            correcto = false;
        } catch (IOException e) {
            System.out.println("Error de escritura en fichero "+ficheroEnvios);
            correcto = false;
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (Exception e) {
                System.out.println("Error de cierre de fichero "+ficheroEnvios);
            }
        }
        for(int i = 0;i<listaPortes.getOcupacion();i++){
            listaPortes.getPorte(i).generarListaEnvios(ficheroEnvios);
        }
        listaPuertosEspaciales.escribirPuertosEspacialesCsv(ficheroPuertos);
        listaNaves.escribirNavesCsv(ficheroNaves);
        listaPortes.escribirPortesCsv(ficheroPortes);
        listaClientes.escribirClientesCsv(ficheroClientes);
    }

    /**
     * Metodo que devuelve un valor booleano en funcion si se ha alcanzado el maximo numero de portes
     * @return Valor booleano en funcion si se ha alcanzado el maximo numero de portes
     */
    public boolean maxPortesAlcanzado() {
        return listaPortes.estaLlena();
    }

    /**
     * Metodo que devuelve un valor booleano en funcion asi se ha podido insertar el porte
     * @param porte Objeto de la clase porte
     * @return valor booleano en funcion asi se ha podido insertar el porte
     */
    public boolean insertarPorte (Porte porte) {
        return listaPortes.insertarPorte(porte);
    }

    /**
     * Metodo que devuelve un valor booleano en funcion as si se ha alcanzado el maximo numero de clientes
     * @return Valor booleano en funcion as si se ha alcanzado el maximo numero de clientes
     */
    public boolean maxClientesAlcanzado() {
        return listaClientes.estaLlena();
    }

    /**
     * Metodo que devuelve alor bolleano en funcion a si se ha insertado un cliente o no
     * @param cliente Objeto de la clase cliente
     * @return valor bolleano en funcion a si se ha insertado un cliente o no
     */
    public boolean insertarCliente (Cliente cliente) {
        return listaClientes.insertarCliente(cliente);
    }
    /**
     * TODO: Metodo para buscar los portes especificados tal y como aparece en el enunciado de la práctica,
     *  Devuelve una lista de los portes entre dos puertos espaciales con una fecha de salida solicitados por teclado
     *  al usuario en el orden y con los textos establecidos (tomar como referencia los ejemplos de ejecución en el
     *  enunciado de la prática)
     * Metodo para buscar los portes especificados tal y como aparece en el enunciado de la práctica,
     * Devuelve una lista de los portes entre dos puertos espaciales con una fecha de salida solicitados por teclado
     *  al usuario en el orden y con los textos establecidos (tomar como referencia los ejemplos de ejecución en el
     *  enunciado de la prática)
     *
     * @param teclado objeto de la clase Scanner para leer datos desde teclado
     * @return devuelve una lista de portes
     */
    public ListaPortes buscarPorte(Scanner teclado) { //REVISAR
        String codigoOrigen = Utilidades.leerCadena(teclado,"Ingrese código de puerto Origen: ");
        String codigoDestino = Utilidades.leerCadena(teclado,"Ingrese código de puerto Destino: ");
        Fecha fecha = Utilidades.leerFecha(teclado, "Fecha de Salida: ");
        return listaPortes.buscarPortes(codigoOrigen, codigoDestino, fecha);
    }


    /**
     * TODO: Metodo para contratar un envio tal y como se indica en el enunciado de la práctica. Se contrata un envio para un porte
     *  especificado, pidiendo por teclado los datos necesarios al usuario en el orden y con los textos (tomar como referencia los
     *  ejemplos de ejecución en el enunciado de la prática)
     * Metodo para contratar un envio tal y como se indica en el enunciado de la práctica. Se contrata un envio para un porte
     * especificado, pidiendo por teclado los datos necesarios al usuario en el orden y con los textos (tomar como referencia los
     *  jemplos de ejecución en el enunciado de la prática)
     *
     * @param teclado objeto de la clase Scanner para leer datos desde teclado
     * @param rand genera un numero aleatorio
     * @param porte objeto de la clase porte
     */
    public void contratarEnvio(Scanner teclado, Random rand, Porte porte) { //REVISAR
        char letra;
        boolean opcionCorrecta = false;
        String email;
        Cliente cliente;
        Envio nuevoEnvio = null;
        boolean emailCorrecto = false;
        if (porte != null) {
            do {
                letra = Utilidades.leerLetra(teclado, "¿Comprar billete para un nuevo pasajero (n), o para uno ya existente (e)?", 'a', 'z');
                if (letra == 'n' || letra == 'e') {
                    opcionCorrecta = true;
                }else{
                    System.out.println("El valor de entrada debe ser 'n' o 'e'");
                }
            }while (!opcionCorrecta);
            if (letra=='n'){
                if(maxClientesAlcanzado()){
                    System.out.println("No se pueden añadir más clientes");
                }else{
                    cliente = Cliente.altaCliente(teclado,listaClientes,maxEnviosPorCliente);
                    insertarCliente(cliente);
                    nuevoEnvio = Envio.altaEnvio(teclado,rand,porte,cliente);
                    porte.ocuparHueco(nuevoEnvio);
                    cliente.aniadirEnvio(nuevoEnvio);
                }
            }
            if (letra=='e'){
                if (listaClientes.getOcupacion()==0){
                    System.out.println("No existen pasajeros en la lista, se procederá a crear uno nuevo");
                    cliente = Cliente.altaCliente(teclado,listaClientes,maxEnviosPorCliente);
                    insertarCliente(cliente);
                    nuevoEnvio = Envio.altaEnvio(teclado,rand,porte,cliente);
                    porte.ocuparHueco(nuevoEnvio);
                    cliente.aniadirEnvio(nuevoEnvio);
                }else {
                    do {
                        email = Utilidades.leerCadena(teclado, "Email del cliente: ");
                        cliente = listaClientes.buscarClienteEmail(email);
                        if (cliente != null) {
                            emailCorrecto=true;
                        }
                    } while (!emailCorrecto);
                    if (cliente.maxEnviosAlcanzado()){
                        System.out.println("El cliente seleccionado ha alcanzado el límite de envíos");
                    }else{
                        nuevoEnvio = Envio.altaEnvio(teclado, rand, porte, cliente);
                        porte.ocuparHueco(nuevoEnvio);
                        cliente.aniadirEnvio(nuevoEnvio);
                    }
                }
            }
        }
    }
    /**
     * TODO Metodo statico con la interfaz del menú de entrada a la App.
     * Metodo statico con la interfaz del menú de entrada a la App
     * Tiene que coincidir con las trazas de ejecución que se muestran en el enunciado
     * @param teclado objeto de la clase Scanner para leer datos desde teclado
     * @return opción seleccionada
     */
    public static int menu(Scanner teclado) {
        System.out.println("1. Alta de Porte\n2. Alta de Cliente\n3. Buscar Porte\n4. Mostrar envíos de un cliente\n5. Generar lista de envíos\n0. Salir");
        return Utilidades.leerNumero(teclado,"Seleccione opción: ",0,5);

    }

    /**
     * TODO: Método Main que carga los datos de los ficheros CSV pasados por argumento (consola) en PlanetExpress,
     *  llama iterativamente al menú y realiza la opción especificada hasta que se indique la opción Salir. Al finalizar
     *  guarda los datos de PlanetExpress en los mismos ficheros CSV.
     *
     *
     * Método Main que carga los datos de los ficheros CSV pasados por argumento (consola) en PlanetExpress,
     * llama iterativamente al menú y realiza la opción especificada hasta que se indique la opción Salir. Al finalizar
     * guarda los datos de PlanetExpress en los mismos ficheros CSV.
     * @param args argumentos de la línea de comandos, recibe **10 argumentos** estrictamente en el siguiente orden:
     * 1. Número máximo de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * 2. Número máximo de naves que tendrá la lista de naves de PlanetExpress App.
     * 3. Número máximo de portes que tendrá la lita de portes de PlanetExpress App.
     * 4. Número máximo de clientes que tendrá la lista de clientes de PlanetExpress App.
     * 5. Número máximo de envíos por pasajero.
     * 6. Nombre del fichero CSV que contiene la lista de puertos espaciales de PlanetExpress App (tanto para lectura como escritura).
     * 7. Nombre del fichero CSV que contiene la lista de naves de PlanetExpress App (tanto para lectura como escritura).
     * 8. Nombre del fichero CSV que contiene la lista de portes de PlanetExpress App (tanto para lectura como escritura).
     * 9. Nombre del fichero CSV que contiene la lista de clientes de PlanetExpress App (tanto para lectura como escritura).
     * 10. Nombre del fichero CSV que contiene los envíos adquiridos en PlanetExpress App (tanto para lectura como escritura).
     * En el caso de que no se reciban exactamente estos argumentos, el programa mostrará el siguiente mensaje
     * y concluirá la ejecución del mismo: `Número de argumentos incorrecto`.
     */
    public static void main(String[] args) {
        if (args.length != 10) {
            System.out.println("Número de argumentos incorrecto");
            //return;
        }else {
            PlanetExpress planetExpress = new PlanetExpress(Integer.parseInt(args[0]),Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]));
            planetExpress.cargarDatos(args[5],args[6],args[7],args[8],args[9]);
            Scanner teclado = new Scanner(System.in);
            Random rand = new Random();
            int opcion;
            do {
                opcion = menu(teclado);
                switch (opcion) {
                    case 1:     // TODO: Alta de Porte

                        if(planetExpress.maxPortesAlcanzado())
                            System.out.println("Maximo numero de portes alcanzado");
                        else{
                            planetExpress.insertarPorte(Porte.altaPorte(teclado, rand,planetExpress.listaPuertosEspaciales,
                                    planetExpress.listaNaves,planetExpress.listaPortes));
                        }

                        break;
                    case 2:     // TODO: Alta de Cliente
                        if(planetExpress.maxClientesAlcanzado())
                            System.out.println("Maximo numero de clientes alcanzado");
                        else{
                            planetExpress.insertarCliente(Cliente.altaCliente(teclado,planetExpress.listaClientes, planetExpress.maxEnviosPorCliente));
                        }

                        break;
                    case 3:     // TODO: Buscar Porte
                        ListaPortes portes = planetExpress.buscarPorte(teclado);
                        if(portes!= null){
                            portes.listarPortes();
                            Porte porteId = portes.seleccionarPorte(teclado,"Seleccione un porte: ","cancelar" );

                        if (porteId!= null)
                            planetExpress.contratarEnvio(teclado, rand, porteId);


                        }else System.out.println("Porte no encontrado");

                        break;
                    case 4:     // TODO: Listado de envíos de un cliente

                        Cliente cliente = planetExpress.listaClientes.seleccionarCliente(teclado, "Email del cliente: ");

                        if(cliente.getListaEnvios().getOcupacion() ==0){
                            System.out.println("El cliente no tiene ningun envio");

                        }else{
                            cliente.listarEnvios();
                            Envio envio = cliente.seleccionarEnvio(teclado, "Seleccione un envio: ");
                            char letra;
                            boolean opcCorrecta=false;
                            do {
                                letra = Utilidades.leerLetra(teclado, "¿Cancelar envío (c), o generar factura (f)? ", 'a', 'z');
                                if (letra == 'c' || letra == 'f') {
                                    opcCorrecta = true;
                                } else {
                                    System.out.println("Carácter incorrecto");
                                }
                            }while (!opcCorrecta);
                            if (letra == 'f'){
                                String fichero = Utilidades.leerCadena(teclado, "Nombre del fichero: ");
                                if (envio.generarFactura(fichero)) {
                                    System.out.println("Factura generada correctamente");
                                }
                            }
                            if (letra == 'c') {
                                if (envio.cancelar()) {
                                    System.out.println("Envio cancelado");
                                }
                            }
                        }

                        break;
                    case 5:     // TODO: Lista de envíos de un porte
                        Porte porte = planetExpress.listaPortes.seleccionarPorte(teclado, "Seleccione un porte: ","cancelar");
                        String fichero = Utilidades.leerCadena(teclado,"Nombre del fichero: ");
                        if(porte.generarListaEnvios(fichero)) {
                            System.out.println("\t\tFichero creado correctamente");
                        }

                        break;
                }
            } while (opcion != 0);
            planetExpress.guardarDatos(args[5],args[6],args[7],args[8],args[9]);
        }
    }
}
