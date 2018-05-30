package bd2.model;

public class Idioma {

	private long idIdioma;
	private String nombre;
	private Diccionario diccionario;
	
	public Idioma(){
		
	}
	
	public Idioma(String i){
		this.setNombre(i);
		this.diccionario = new Diccionario();
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Diccionario getDiccionario() {
		return diccionario;
	}

	public void setDiccionario(Diccionario diccionario) {
		this.diccionario = diccionario;
	}

	public long getIdIdioma() {
		return idIdioma;
	}

	public void setIdIdioma(long idIdioma) {
		this.idIdioma = idIdioma;
	}

	
	
	
}
