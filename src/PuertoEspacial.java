/**
 * Description of the class
 *
 * @author PAULA CAMILA MORENO RUBIO
 * @author DMYTRO KRYVITSKYY ROMANYUK
 * @version     1.0.0.1
 */
public class PuertoEspacial {

    private String nombre;
    private String codigo;
    private double radio;
    private double azimut;
    private double polar;
    private int numMuelles;

    /**
     * Constructor of the class
     *
     * @param nombre
     * @param codigo
     * @param radio
     * @param azimut
     * @param polar
     * @param numMuelles
     */
    public PuertoEspacial(String nombre, String codigo, double radio, double azimut, double polar, int numMuelles) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.radio = radio;
        this.azimut = azimut;
        this.polar = polar;
        this.numMuelles = numMuelles;
    }
    public String getNombre() {
        return nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public double getRadio() {
        return radio;
    }
    public double getAzimut() {
        return azimut;
    }

    public double getPolar() {
        return polar;
    }
    public int getMuelles() {
        return numMuelles;
    }

    /**
     * TODO: Método para calcular la distancia entre el puerto espacial que recibe el mensaje y el puerto
     *  espacial "destino" siguiendo las ecuaciones del enunciado (Las formulas se encuentran en el enunciado)
     * @param destino
     * @return
     */
    public double distancia(PuertoEspacial destino) {
        // TODO: Para calcular la distancia entre dos Puertos Espaciales, se transforman sus coordenadas esféricas a cartesianas
         // x1 donde estamos, x2 destino
        double res= 0.0;

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

