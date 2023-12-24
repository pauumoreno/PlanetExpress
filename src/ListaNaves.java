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
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaNaves(int capacidad) {
        naves = new Nave[capacidad];

    }
    // TODO: Devuelve el número de naves que hay en la lista
    public int getOcupacion() {
        int ocupados=0;
        for (int i=0; i<naves.length;i++){
            if (getNave(i)!=null){
                ocupados++;
            }
        }
        return ocupados;

    }
    // TODO: ¿Está llena la lista de naves?
    public boolean estaLlena() {

        return naves.length<=getOcupacion();

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
            return true;
        }

    }
    /**
     * TODO: Buscamos la nave a partir de la matricula pasada como parámetro
     * @param matricula
     * @return la nave que encontramos o null si no existe
     */
    public Nave buscarNave(String matricula) {
        Nave nave = null;
        for (int i = 0; i<getOcupacion(); i++){
            if (matricula.equals(getNave(i).getMatricula())){
                nave = getNave(i);
            }
        }
        return nave;
    }
    // TODO: Muestra por pantalla las naves de la lista con el formato indicado en el enunciado
    public void mostrarNaves() {
        for(int i=0;i<getOcupacion();i++){
            System.out.println(getNave(i).toString());;
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
        boolean volver = false;
        String matricula;
        do {
            System.out.print(mensaje);
            matricula = teclado.nextLine();
            nave =  buscarNave(matricula);

            if (nave == null) {
                System.out.println("Matrícula de nave no encontrada.");
                volver = true;
            }
            if (nave!= null && nave.getAlcance() < alcance) {
                System.out.println("Nave seleccionada con alcance insuficiente. ");
                volver = true;
            }
        } while (volver==true);


        return nave;
    }


    /**
     * TODO: Genera un fichero CSV con la lista de Naves, SOBREESCRIBIÉNDOLO
     * @param nombre
     * @return
     */
    public boolean escribirNavesCsv(String nombre) {
        PrintWriter pw = null;
        Scanner sc= null;
        boolean correcto= true;

        try {
            pw = new PrintWriter(nombre);
            sc = new Scanner(new FileReader(nombre));
            for (int i = 0; i < getOcupacion(); i++) {
                pw.println(getNave(i).getMarca() + ";" + getNave(i).getModelo() + ";" + getNave(i).getMatricula() + ";"
                        + getNave(i).getFilas() + ";" + getNave(i).getColumnas() + ";" + getNave(i).getAlcance());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Fichero "+nombre+" no encontrado");
            correcto = false;
        } catch (Exception e) {
            System.out.println("Error en la escritura del fichero "+nombre);
            correcto= false;
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (sc != null) {
                    sc.close();
                }
            } catch (Exception exc) {
                System.out.println("Error de cierre de fichero "+nombre);
                correcto = false;
            }

        }
        return correcto;
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
                Nave nave = new Nave(info[0],info[1],info[2],Integer.parseInt(info[3]),
                                     Integer.parseInt(info[4]), Double.parseDouble(info[5]));

                listaNaves.insertarNave(nave);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichero "+fichero+" no encontrado.");
        } catch (Exception e) {
            System.out.println("Error en la lectura del fichero "+fichero);
        } finally {try {

            if (sc!= null)
                sc.close();

        } catch (Exception exc) {
            System.out.println("Error de cierre del fichero" +fichero);
        }

        }
        return listaNaves;

    }
}
