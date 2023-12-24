import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaClientes {
    private Cliente[] clientes;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaClientes(int capacidad) {
        clientes = new Cliente[capacidad];

    }
    // TODO: Devuelve el número de clientes que hay en la lista de clientes
    public int getOcupacion() {
        int ocupados=0;
        for (int i=0; i<clientes.length;i++){
            if (getCliente(i)!=null){
                ocupados++;
            }
        }
        return ocupados;

    }
    // TODO: ¿Está llena la lista de clientes?
    public boolean estaLlena() {
        return clientes.length<=getOcupacion();

    }
    // TODO: Devuelve el cliente dada el indice
    public Cliente getCliente(int i) {
        return clientes[i];
    }
    // TODO: Inserta el cliente en la lista de clientes
    public boolean insertarCliente(Cliente cliente) {
        if(estaLlena()){
            return false;
        }else{
            clientes[getOcupacion()]=cliente;
            return true;
        }

    }
    // TODO: Devuelve el cliente que coincida con el email, o null en caso de no encontrarlo
    public Cliente buscarClienteEmail(String email) {
        Cliente cliente = null;
        for (int i = 0; i<getOcupacion(); i++){
            if (email.equals(getCliente(i).getEmail())){
                 cliente = getCliente(i);
            }
        }
        return cliente;


    }

    /**
     * TODO: Método para seleccionar un Cliente existente a partir de su email, usando el mensaje pasado como argumento
     *  para la solicitud y, siguiendo el orden y los textos mostrados en el enunciado.
     *  La función debe solicitar repetidamente hasta que se introduzca un email correcto
     * @param teclado
     * @param mensaje
     * @return
     */
    public Cliente seleccionarCliente(Scanner teclado, String mensaje) {
        Cliente cliente = null;
        String email;
        boolean correcto= false;
        do {
            email = Utilidades.leerCadena(teclado, mensaje);
            for (int i = 0; i < getOcupacion(); i++) {
                    if (email.equals(getCliente(i).getEmail())) {
                        cliente = getCliente(i);
                        correcto=true;
                    }
                }
                if (cliente==null){
                    System.out.println("Email no encontrado.");
                }

        }while(!correcto);


        return cliente;
    }

    /**
     * TODO: Método para guardar la lista de clientes en un fichero .csv, sobreescribiendo la información del mismo
     *  fichero
     * @param fichero
     * @return
     */
    public boolean escribirClientesCsv(String fichero) {
        PrintWriter pw = null;
        boolean correcto = true;

        try {
            pw = new PrintWriter(fichero);

            for(int i = 0;i<getOcupacion();i++) {
                pw.println(getCliente(i).getNombre()+";"+getCliente(i).getApellidos()+";"+getCliente(i).getEmail());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Fichero "+fichero+" no encontrado");
            correcto = false;
        } catch(Exception exc){
            System.out.println("Error de escritura");
            correcto= false;
        }finally {
            try{
                if(pw!=null)
                    pw.close();

            }catch(Exception exc){
                System.out.println("Error de cierre del fichero "+fichero);
                correcto = false;
            }

        }
        return correcto;
    }

    /**
     * TODO: Genera una lista de Clientes a partir del fichero CSV, usando los límites especificados como argumentos
     *  para la capacidad de la lista y el número de billetes máximo por pasajero
     * @param fichero
     * @param capacidad
     * @param maxEnviosPorCliente
     * @return lista de clientes
     */
    public static ListaClientes leerClientesCsv(String fichero, int capacidad, int maxEnviosPorCliente) {
        ListaClientes listaClientes = new ListaClientes(capacidad);
        Scanner sc= null;
        try {
            sc = new Scanner(new FileReader(fichero));
            while (sc.hasNext()) {
                String[] info = sc.nextLine().split(";");
                //Probar a defirnirlo directamente en la clase
                String nombre = info[0];
                String apellidos = info[1];
                String email = info[2];

                listaClientes.insertarCliente(new Cliente(nombre,apellidos,email,maxEnviosPorCliente));
                //listaClientes.insertarCliente(new Cliente(line[0],info[1],info[2],maxEnviosPorCliente));
                }

        } catch (FileNotFoundException e) {
            System.out.println("Fichero Clientes no encontrado");
        } finally {
            try{
                if(sc!=null)
                    sc.close();
            }catch (Exception exc){
                System.out.println("Error de cirre del fichero "+fichero);
            }

        }
        return listaClientes;
    }
}
