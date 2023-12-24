/**
 * Nave es una clase que contiene los atributos de una nave: marca, modelo, matricula, filas y columnas de la bodega
 * y su alcance. Además de sus respectivos getters y toString.
 *
 * @author PAULA CAMILA MORENO RUBIO
 * @author DMYTRO KRYVITSKYY ROMANYUK
 * @version     1.0
 */
public class Nave {
    /**
     * Marca de la nave
     */
    private String marca;
    /**
     * Modelo de la nave
     */
    private String modelo;
    /**
     * Matrícula de la nave
     */
    private String matricula;
    /**
     * Columnas de la bodega de carga de la nave para almacenar contenedores
     */
    private int columnas;
    /**
     * Filas de la bodega de carga de la nave para almacenar contenedores
     */
    private int filas;
    /**
     * Alcance máximo de la nave (en UA's)
     */
    private double alcance;


    /**
     * Constructor de la clase Nave que define los siguientes parametros:
     *
     * @param marca
     * @param modelo
     * @param matricula
     * @param columnas
     * @param filas
     * @param alcance
     */
    public Nave(String marca, String modelo, String matricula, int columnas, int filas, double alcance) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.columnas = columnas;
        this.filas = filas;
        this.alcance = alcance;
    }

    /**
     * Getter de atributo Marca
     * @return Marca de la nave
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Getter de atributo Modelo
     * @return Modelo de la nave
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Getter de atributo Matrícula
     * @return Matrícula de la nave
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Getter de atributo Columnas
     * @return Columnas de la bodega
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * Getter de atributo Filas
     * @return Filas de la bodega
     */
    public int getFilas() {
        return filas;
    }

    /**
     * Getter de atributo Alcance
     * @return Alcance máximo de la nave
     */
    public double getAlcance() {
        return alcance;
    }


    /**
     * TODO: Crea un String con los datos de una nave con el siguiente formato:
     * @return ejemplo del formato -> "Planet Express One (EP-245732X): 40 contenedores, hasta 1.57 UA"
     */
    public String toString() {
        return marca+" "+modelo+" ("+matricula+"): "+(filas*columnas)+" contenedores, hasta "+alcance+" UA";//preguntar si ese es el alcance
    }


    /**
     * TODO: Crea un String con los datos de una nave con el siguiente formato:
     * @return ejemplo del formato -> "Planet Express One (EP-245732X)"
     */
    public String toStringSimple() {
        return marca+" "+modelo+" ("+matricula+")";
    }
}

