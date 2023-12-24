import java.util.Scanner;

/**
 * Cliente es una clase que contiene los atributos de un cliente: nombre, apellidos, email y los envios que ha contratado;
 * y sus respectivos getters y toString.
 * Además, encapsula otros métodos que sirven para saber si ha alcanzado su numero maximo de envios, añadir o cancelar
 * un envio, dar de alta un nuevo cliente o comprobar si el email es correcto.
 *
 *  @author PAULA CAMILA MORENO RUBIO
 *  @author DMYTRO KRYVITSKYY ROMANYUK
 */
public class Cliente {
    /**
     * Lista de envíos contratados por dicho Cliente
     */
    private final ListaEnvios listaEnvios;
    /**
     * Nombre del cliente
     */
    private final String nombre;
    /**
     * Apellidos del cliente
     */
    private final String apellidos;
    /**
     * Email del cliente
     */
    private final String email;

    /**
     * Constructor de la clase Cliente que define los siguientes parametros:
     *
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param email Email del cliente
     * @param maxEnvios Número máximo de envíos que puede tener el cliente
     */
    public Cliente(String nombre, String apellidos, String email, int maxEnvios) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.listaEnvios = new ListaEnvios(maxEnvios);
    }

    /**
     * Getter del atributo Nombre
     * @return Nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter del atributo Apellidos
     * @return Apellidos del cliente
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Getter del atributo Email
     * @return Email del cliente
     */
    public String getEmail() {
        return email;
    }
    // TODO: Texto que debe generar: Zapp Brannigan, zapp.brannigan@dop.gov
    public String toString() {
        return nombre+" "+apellidos+", "+email;
    }
    // TODO: Devuelve un booleano que indica si se ha alcanzado el número máximo de envíos
    public boolean maxEnviosAlcanzado() {
        return listaEnvios.estaLlena();
    }
    // TODO: Devuelve un envío en función de su posición
    public Envio getEnvio(int i) {
        return listaEnvios.getEnvio(i);
    }

    /**
     * Getter de la lista de envios del cliente
     * @return Lista de envíos contratados por dicho Cliente
     */
    public ListaEnvios getListaEnvios() {
        return listaEnvios;
    }
    // TODO: Añade un envío al cliente
    public boolean aniadirEnvio(Envio envio) {
        return listaEnvios.insertarEnvio(envio);
    }

    /**
     * Busca un envío por su localizador
     * @param localizador
     * @return envio con ese localizador
     */
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }
    // TODO: Elimina el envío de la lista de envíos del pasajero
    public boolean cancelarEnvio(String localizador) {
        return listaEnvios.eliminarEnvio(localizador);
    }

    /**
     * Método que lista todos los envios hechos por el cliente
     */
    public void listarEnvios() {
        listaEnvios.listarEnvios();
    }
    // Encapsula la funcionalidad seleccionarEnvio de ListaEnvios
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        return listaEnvios.seleccionarEnvio(teclado, mensaje);
    }


    /**
     * TODO: Método estático para crear un nuevo cliente "no repetido", se pide por teclado los datos necesarios
     * al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     * La función tiene que solicitar repetidamente los parámetros hasta que sean correctos
     * @param teclado
     * @param clientes
     * @param maxEnvios
     * @return Cliente
     */
    public static Cliente altaCliente(Scanner teclado, ListaClientes clientes, int maxEnvios) {
        String nombre = null;
        String apellidos = null;
        String email = null;
        Cliente nuevoCliente = null;
        boolean repetido = false;
        if (clientes.estaLlena()){ //si la lista está llena se imprime el mensaje y devuelve nuevoCliente = null
            System.out.println("No se pueden dar de alta más clientes.");
        }else {
            do {
                nombre = Utilidades.leerCadena(teclado, "Nombre: ");
                apellidos = Utilidades.leerCadena(teclado, "Apellidos: ");
                do {
                    email = Utilidades.leerCadena(teclado, "Email: ");
                } while (!correctoEmail(email)); //se pide el nombre y apellidos y se hace un bucle para pedir el correo hasta que sea correcto
                nuevoCliente = new Cliente(nombre, apellidos, email, maxEnvios); //cuando la informacion es correcta se crea al nuevo cliente
                if (clientes.buscarClienteEmail(email)==null){ //si el cliente no está en el sistema entonces se inserta al nuevo cliente
                    System.out.println("Cliente con email " + email + " creado correctamente");
                    clientes.insertarCliente(nuevoCliente);
                    repetido=false;
                }else{
                    System.out.println("Ya existe un cliente con este email");
                    repetido=true;
                }
            }while (repetido); //se repite todo el proceso hasta que se introduzca un cliente que no esté registrado
        }
        return nuevoCliente;
    }


    /**
     * TODO: Metodo para comprobar que el formato de email introducido sea correcto
     * @param email
     * @return boolean
     */
    public static boolean correctoEmail(String email) {
        String [] partesCorreo = email.split("@"); // se divide al correo en dos partes y se meten en un array
        boolean correcto = true;
        if (partesCorreo.length!=2){ //si hay menos o hay mas de 2 partes entonces el email no es correcto
            correcto = false;
        }else {
            String parteIzquierda = partesCorreo[0];
            String parteDerecha = partesCorreo[1];
            if (!parteIzquierda.matches("[a-z0-9_.]+")){ //si la parte izq tiene algo que no sean letras minusculas, numeros o puntos y guiones bajos entonces el email no es correcto (el + indica que se pueden repetir caracteres)
                correcto=false;
            }
            if (!(parteDerecha.equals("upm.es") || parteDerecha.equals("alumnos.upm.es"))) { //si la parte derecha no es upm.es o alumnos.upm.es entonces el email no es correcto
                correcto = false;
            }
        }
        return correcto;
    }
}
