package comunicador.laboratorio.hermes2;

/**
 * Created by nicolasmanzato on 13/12/15.
 */
public class Alumno {

    private int id;
    private String nombre;
    private String apellido;
    private String sexo;
    private int tamanoPictogramas;
    private int emocionesHabilitada;
    private int pistaHabilitada;
    private int necesidadHabilitada;


    public Alumno(int id,String nombre, String apellido, String sexo, int tamanoPictogramas, int emocionesHabilitada, int pistaHabilitada, int necesidadHabilitada, int establoHabilitada) {

        this.setId(id);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setSexo(sexo);
        this.setTamanoPictogramas(tamanoPictogramas);
        this.setEmocionesHabilitada(emocionesHabilitada);
        this.setPistaHabilitada(pistaHabilitada);
        this.setNecesidadHabilitada(necesidadHabilitada);
        this.setEstabloHabilitada(establoHabilitada);


    }

    public Alumno(String nombre, String apellido, String sexo, int tamanoPictogramas, int emocionesHabilitada, int pistaHabilitada, int necesidadHabilitada, int establoHabilitada){

        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setSexo(sexo);
        this.setTamanoPictogramas(tamanoPictogramas);
        this.setEmocionesHabilitada(emocionesHabilitada);
        this.setPistaHabilitada(pistaHabilitada);
        this.setNecesidadHabilitada(necesidadHabilitada);
        this.setEstabloHabilitada(establoHabilitada);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {

        return this.getNombre();

    }

    public int isNecesidadHabilitada() {
        return necesidadHabilitada;
    }

    public void setNecesidadHabilitada(int necesidadHabilitada) {
        this.necesidadHabilitada = necesidadHabilitada;
    }

    public int getTamanoPictogramas() {
        return tamanoPictogramas;
    }

    public void setTamanoPictogramas(int tamanoPictogramas) {
        this.tamanoPictogramas = tamanoPictogramas;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int isEmocionesHabilitada() {
        return emocionesHabilitada;
    }

    public void setEmocionesHabilitada(int emocionesHabilitada) {
        this.emocionesHabilitada = emocionesHabilitada;
    }

    public int isPistaHabilitada() {
        return pistaHabilitada;
    }

    public void setPistaHabilitada(int pistaHabilitada) {
        this.pistaHabilitada = pistaHabilitada;
    }

    public int isEstabloHabilitada() {
        return establoHabilitada;
    }

    public void setEstabloHabilitada(int establoHabilitada) {
        this.establoHabilitada = establoHabilitada;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public int getEmocionesHabilitada() {
        return emocionesHabilitada;
    }

    public int getPistaHabilitada() {
        return pistaHabilitada;
    }

    public int getNecesidadHabilitada() {
        return necesidadHabilitada;
    }

    public int getEstabloHabilitada() {
        return establoHabilitada;
    }

    private int establoHabilitada;


    public Alumno(String nombre){

        this.setNombre(nombre);

    }

}
