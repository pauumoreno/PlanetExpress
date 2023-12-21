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
 *
 *
 */



public class ListaNaves {
    private Nave[] naves;
    //
    private int capacidad;
    private int ocupacion;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaNaves(int capacidad) {

        naves = new Nave[capacidad];
        this.capacidad= capacidad;
        ocupacion =0;

    }
    // TODO: Devuelve el número de naves que hay en la lista
    public int getOcupacion() {

        return ocupacion;

    }
    // TODO: ¿Está llena la lista de naves?
    public boolean estaLlena() {

        return capacidad<=getOcupacion();

    }
    // TODO: Devuelve nave dado un indice
    public Nave getNave(int posicion) {
        return naves[posicion];
    }

    /**
     * TODO: insertamos una nueva nave en la lista
     * @param nave
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarNave(Nave nave) {

        if(estaLlena())
            return false;
        else{
            naves[getOcupacion()]=nave;
            ocupacion++;
            return true;
        }

    }
    /**
     * TODO: Buscamos la nave a partir de la matricula pasada como parámetro
     * @param matricula
     * @return la nave que encontramos o null si no existe
     */
    public Nave buscarNave(String matricula) {

        int k= 0;
        int j = 0;
        for(int i=0;i<getOcupacion();i++){
            if(matricula.equals(getNave(i).getMatricula())){
                k = 1;
                j = i;
            }
        }
            if (k == 1) {
            return getNave(j);
            } else return null;

    }
    // TODO: Muestra por pantalla las naves de la lista con el formato indicado en el enunciado
    public void mostrarNaves() {
        for(int i=0;i<naves.length;i++){
            getNave(i);
        }

    }



    /**
     * TODO: Permite seleccionar una nave existente a partir de su matrícula, y comprueba si dispone de un alcance
     *  mayor o igual que el pasado como argumento, usando el mensaje pasado como argumento para la solicitud y
     *  siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente la matrícula de la nave hasta que se introduzca una con alcance suficiente
     * @param teclado
     * @param mensaje
     * @param alcance
     * @return
     */
    public Nave seleccionarNave(Scanner teclado, String mensaje, double alcance) {
        Nave nave = null;
        boolean repetir = true;
        String buscar;
        do {
            System.out.print(mensaje);
            buscar = teclado.nextLine();
            for(int i = 0;i<getOcupacion();i++){
                if(buscar.equals(getNave(i).getMatricula())) {
                    nave = getNave(i);
                    repetir = false;
                }
            }
            if (nave == null) {
                System.out.println("Matrícula de nave no encontrada.");
                repetir = true;
            }
            if (nave!= null && nave.getAlcance() < alcance) {
                System.out.println("Nave seleccionada con alcance insuficiente. ");
                repetir = true;
            }
        } while (repetir==true);


        return nave;
    }


    /**
     * TODO: Genera un fichero CSV con la lista de Naves, SOBREESCRIBIÉNDOLO
     * @param nombre
     * @return
     */
    public boolean escribirNavesCsv(String nombre) {
        PrintWriter pw = null;
        try {

            return true;
        } catch (Exception e) {
            return false;
        } finally {

        }
    }


    /**
     * TODO: Genera una lista de naves a partir del fichero CSV, usando el argumento como capacidad máxima de la lista
     * @param fichero
     * @param capacidad
     * @return
     */
    public static ListaNaves leerNavesCsv(String fichero, int capacidad) {
        ListaNaves listaNaves = new ListaNaves(capacidad);
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(fichero));
            while (sc.hasNext()) {
                String linea = sc.nextLine();

                String[] info = linea.split(";", 6);
                Nave nave = new Nave(info[0],info[1],info[2],Integer.parseInt(info[4]),
                                     Integer.parseInt(info[3]), Double.parseDouble(info[5]));

                listaNaves.insertarNave(nave);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero Naves no encontrado.");
            return null;
        } catch (Exception e) {
            System.out.println("Error en la lectura del fichero Naves");
            return null;
        } finally {try {
            if (sc!= null) {
                sc.close();
            }
        } catch (Exception ex) {
            System.out.println("Error de cierre del fichero");
        }

        }
        return listaNaves;

    }
}
