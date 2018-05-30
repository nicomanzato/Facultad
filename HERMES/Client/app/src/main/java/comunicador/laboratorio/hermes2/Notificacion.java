package comunicador.laboratorio.hermes2;



/**
 * Created by nicolasmanzato on 2/5/16.
 */
public class Notificacion {

    public String contenido;

    public String contexto;

    public String categoria;

    public String paciente;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }


    public Notificacion(String contenido, String categoria, String paciente){

        this.setCategoria(categoria);
        this.setContenido(contenido);
        this.setPaciente(paciente);
        this.setContexto("Cedica");

    }

}
