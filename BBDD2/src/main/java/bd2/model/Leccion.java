package bd2.model;

public class Leccion {

	private long idLeccion;
	private String nombre;

	public Leccion(){
		
	}
	
	public Leccion(String s){
		this.setNombre(s);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getIdLeccion() {
		return idLeccion;
	}

	public void setIdLeccion(long idLeccion) {
		this.idLeccion = idLeccion;
	}

	
	
}
