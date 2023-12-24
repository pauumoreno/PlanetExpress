/**
 * Description of the class
 * Clase que describe las caracteristicas de un puerto espacial (nombre, codigo, numero de muelles...)
 * y las podemos obtener con sus respectivos getters.
 * Además de los metodos que permiten obtener la distancia entre dos puertos espaciales
 * y generar cadenas de texto con sus datos
 *
 * @author PAULA CAMILA MORENO RUBIO
 * @author DMYTRO KRYVITSKYY ROMANYUK
 * @version     1.0.0.1
 */
public class PuertoEspacial {

    /**
     * Nombre del puerto espacial
     */
    private String nombre;
    /**
     * Codigo del puerto espacial
     */
    private String codigo;
    /**
     * Radio del puerto espacial, distancia al Sol en unidades astronomicas
     */
    private double radio;
    /**
     * El angulo desde el eje positivo x hasta la proyeccion del punto en el plano xy
     */
    private double azimut;
    /**
     * El angulo desde el eje positivo z hasta el puerto
     */
    private double polar;
    /**
     * Numero de muelles del puerto espacial
     */
    private int numMuelles;

    /**
     * Constructor of the class
     * Constructor de la case Puerto Espacial para defirnir sus atributos
     *
     * @param nombre Nombre del puerto espacial
     * @param codigo codigo del puerto espacial
     * @param radio Radio del puerto espacial, distancia al Sol en unidades astronomicas
     * @param azimut El angulo desde el eje positivo x hasta la proyeccion del punto en el plano xy
     * @param polar El angulo desde el eje positivo z hasta el puerto
     * @param numMuelles Numero de muelles del puerto espacial
     */
    public PuertoEspacial(String nombre, String codigo, double radio, double azimut, double polar, int numMuelles) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.radio = radio;
        this.azimut = azimut;
        this.polar = polar;
        this.numMuelles = numMuelles;
    }

    /**
     * Getter de nombre del puerto espacial
     * @return Nombre del puerto espacial
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter del codigo del puerto espacial
     * @return Codigo del puerto espacial
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Getter de radio del puerto espacial
     * @return radio
     */
    public double getRadio() {
        return radio;
    }

    /**
     * Getter de azumit del puerto espacial
     * @return  azumit
     */
    public double getAzimut() {
        return azimut;
    }

    /**
     * Getter de polar del puerto espacial
     * @return Polar
     */
    public double getPolar() {
        return polar;
    }

    /**
     * Getter delnumero de muelles del puerto espacial
     * @return numero de muelles del puerto espacial
     */
    public int getMuelles() {
        return numMuelles;
    }

    /**
     * TODO: Método para calcular la distancia entre el puerto espacial que recibe el mensaje y el puerto
     *  espacial "destino" siguiendo las ecuaciones del enunciado (Las formulas se encuentran en el enunciado)
     * @param destino puerto espacial de destino
     * @return distancia entre los dos puertos espaciales
     */
    public double distancia(PuertoEspacial destino) {
        // TODO: Para calcular la distancia entre dos Puertos Espaciales, se transforman sus coordenadas esféricas a cartesianas
         // x1 donde estamos, x2 destino
        double res;

        double x1,x2,y1, y2, z1, z2;

        x1 = this.radio*Math.sin(this.azimut)*Math.cos(this.polar);
        x2 = destino.radio*Math.sin(destino.azimut)*Math.cos(destino.polar);

        y1 = this.radio*Math.sin(this.azimut)*Math.sin(this.polar);
        y2 = destino.radio*Math.sin(destino.azimut)*Math.sin(destino.polar);

        z1 = this.radio*Math.cos(this.azimut);
        z2 = destino.radio*Math.cos(destino.azimut);


        // TODO: Una vez se tienen las coordenadas cartesianas, basta con calcular la distancia euclídea entre ellas:
        res = Math.sqrt((Math.pow(x2-x1,2))+ Math.pow(y2-y1,2)+Math.pow(z2-z1,2));
        return res ;
    }

    /**
     * TODO: Método que crea un String con los datos de un puerto espacial con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal(GGT), en (1.0 90.0 0.0), con 8 muelles" (Radio, Azimut, Polar)
     */
    public String toString() {
        return this.nombre + "("+this.codigo+"), en ("+this.radio+" "+this.azimut+" "+this.polar+"), con "+this.numMuelles+" muelles";
    }

    /**
     * TODO: Método que crea un String con los datos de un aeropuerto con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal (GGT)"
     */
    public String toStringSimple() {
        return this.nombre + "("+this.codigo+")";
    }
}

