package entidades;

public class Etiqueta {
	
	private String id;
	private String nombre;
	
	public Etiqueta(String id, String nombre){
		
		this.id = id;
		this.nombre = nombre;
		
	}
	
	public Etiqueta(String nombre){
		
		this.nombre = nombre;
		
	}
	
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
