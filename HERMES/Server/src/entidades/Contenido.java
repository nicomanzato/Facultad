package entidades;

public class Contenido {

	String id;
	String nombre;
	
	public Contenido(String id, String nombre){
		
		this.id = id;
		this.nombre = nombre;
		
	}
	
	public Contenido(String nombre){
		
		this.nombre = nombre;
		
	}
	
	public Contenido(){}
	
	public String toString(){
		
		return this.getNombre();
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
