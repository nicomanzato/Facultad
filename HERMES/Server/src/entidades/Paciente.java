package entidades;

public class Paciente {

	String id;
	String nombre;
	
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
	
	public Paciente(){}
	
	public Paciente( String id, String nombre){
		
		this.id = id;
		this.nombre = nombre;
		
	}
	
	public Paciente(String nombre){
		
		this.nombre = nombre;
		
	}
	
	public String toString(){
		
		return this.getNombre();
		
	}
	
}
