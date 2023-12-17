import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author PAULA CAMILA MORENO RUBIO
 * @author DMYTRO KRYVITSKYY ROMANYUK
 * @version     1.0.1
 */
public class ListaPuertosEspaciales {
    private PuertoEspacial[] lista;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaPuertosEspaciales(int capacidad) {
        lista = new PuertoEspacial[capacidad];
    }
    // TODO: Devuelve el número de puertos espaciales que hay en la lista
    public int getOcupacion() {
        int ocupados=0;
        for (int i=0; i<lista.length;i++){
            if (getPuertoEspacial(i)!=null){
                ocupados++;
            }
        }
        return ocupados;
    }
    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        return getOcupacion()== lista.length;
    }
    // TODO: Devuelve un puerto espacial dado un indice
    public PuertoEspacial getPuertoEspacial(int i) {
        return lista[i];
    }

    /**
     * TODO: insertamos un Puerto espacial nuevo en la lista
     * @param puertoEspacial
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarPuertoEspacial(PuertoEspacial puertoEspacial) {
        if (estaLlena()){
            return false;
        } else{
            lista[getOcupacion()]=puertoEspacial;
            return true;
        }
    }

    /**
     * TODO: Buscamos un Puerto espacial a partir del codigo pasado como parámetro
     * @param codigo
     * @return Puerto espacial que encontramos o null si no existe
     */
    public PuertoEspacial buscarPuertoEspacial(String codigo) {
        PuertoEspacial puertoEspacial = null;
        for (int i = 0; i<getOcupacion(); i++){
            if (codigo.equals(getPuertoEspacial(i).getCodigo())){
                puertoEspacial = getPuertoEspacial(i);
            }
        }
        return puertoEspacial;
    }

    /**
     * TODO: Permite seleccionar un puerto espacial existente a partir de su código, usando el mensaje pasado como
     *  argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente el código hasta que se introduzca uno correcto
     * @param teclado
     * @param mensaje
     * @return
     */
    public PuertoEspacial seleccionarPuertoEspacial(Scanner teclado, String mensaje) {
        PuertoEspacial puertoEspacial = null;
        String codigo = null;
        boolean correcto = false;
        do {
            codigo = Utilidades.leerCadena(teclado, mensaje);
            for (int i=0; i<getOcupacion();i++){
                if (codigo.equals(getPuertoEspacial(i).getCodigo())){
                    puertoEspacial = getPuertoEspacial(i);
                    correcto = true;
                }
            }
        } while (!correcto);
        return puertoEspacial;
    }

    /**
     * TODO: Genera un fichero CSV con la lista de puertos espaciales, SOBREESCRIBIENDOLO
     * @param nombre
     * @return
     */
    public boolean escribirPuertosEspacialesCsv(String nombre) {
        PrintWriter pw = null;
        Scanner scanner = null;
        boolean correcto = true;
        try {
            pw = new PrintWriter(nombre);
            scanner = new Scanner(new FileReader(nombre));
            for (int i=0; i<getOcupacion();i++){
                pw.println(getPuertoEspacial(i).getNombre()+";"+getPuertoEspacial(i).getCodigo()+";"+getPuertoEspacial(i).getRadio()
                        +";"+getPuertoEspacial(i).getAzimut()+";"+getPuertoEspacial(i).getPolar()+";"+getPuertoEspacial(i).getMuelles());
            }
        } catch (FileNotFoundException e){
            System.out.println("Fichero "+nombre+" no encontrado.");
            correcto = false;
        } catch (Exception e) {
            System.out.println("Error de lectura de fichero "+nombre);
            correcto = false;
        } finally {
            try{
                if (pw != null){
                    pw.close();
                }
                if (scanner != null){
                    scanner.close();
                }
            } catch (Exception e){
                System.out.println("Error de cierre de fichero "+nombre);
                correcto = false;
            }
        }
        return correcto;
    }


    /**
     * TODO: Genera una lista de PuertosEspaciales a partir del fichero CSV, usando el argumento como capacidad máxima
     *  de la lista
     * @param fichero
     * @param capacidad
     * @return
     */
    public static ListaPuertosEspaciales leerPuertosEspacialesCsv(String fichero, int capacidad) {
        ListaPuertosEspaciales listaPuertosEspaciales = new ListaPuertosEspaciales(capacidad);
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(fichero));
            while (sc.hasNext()){
                String linea = sc.nextLine();
                String [] atributos = linea.split(";",6);
                PuertoEspacial puertoEspacial = new PuertoEspacial(atributos[0],atributos[1],Double.parseDouble(atributos[2]),
                        Double.parseDouble(atributos[3]),Double.parseDouble(atributos[4]),Integer.parseInt(atributos[5]));
                listaPuertosEspaciales.insertarPuertoEspacial(puertoEspacial);
            }
        } catch (FileNotFoundException e){
            System.out.println("Fichero "+fichero+" no encontrado.");
            return null;
        }catch (Exception e) {
            System.out.println("Error en la lectura del fichero "+fichero);
            return null;
        } finally {
            try{
                if (sc != null){
                    sc.close();
                }
            } catch (Exception e){
                System.out.println("Error de cierre de fichero "+fichero);
                // PREGUNTAR SI ACA TAMBIEN VA RETURN NULL
            }
        }
        return listaPuertosEspaciales;
    }
}

