package entidades;

public class Contexto {

	String id;
	String nombre;
	
	public Contexto(String id, String nombre){
		
		this.id = id;
		this.nombre = nombre;
		
	}
	
	public Contexto(String nombre){
		
		this.nombre = nombre;
		
	}
	
	public Contexto(){}
	
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
