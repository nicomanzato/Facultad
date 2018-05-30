package bd2.model;
import java.util.Date;

public class Tarea {

	private long idTarea;
	private java.util.Date fecha;
	private String descripcion;
	private Boolean completa;
	
	public Tarea(){
		
	}
	
	public Tarea(java.util.Date fec, String desc, Boolean comp){
		this.fecha = fec;
		this.descripcion = desc;
		this.completa = comp;
	}
	
	public void completar(){
		//preguntar que hace completar
		this.completa = true;
	}

	public java.util.Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setCompleta(Boolean est){
		this.completa = est;
	}
	
	public Boolean getCompleta(){
		return this.completa;
	}

	public long getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(long idTarea) {
		this.idTarea = idTarea;
	}

	
	
}
