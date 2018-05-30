package bd2.model;
import java.util.Date;

public class Traduccion extends Tarea {

	private String texto;
	private Parrafo parrafo;
	private Idioma idioma;
	
	public Traduccion(){
		
	}
	
	public Traduccion(Date fecha, String desc, Boolean completa, String text, Parrafo parr){
			
		this.setFecha(fecha);
		this.setDescripcion(desc);
		this.setCompleta(completa);
		this.setTexto(text);
		this.parrafo = parr;
	}
	
	public Traduccion(Date fecha, String desc, Boolean completa, String text, Parrafo parr, Idioma i){
		
		this.setFecha(fecha);
		this.setDescripcion(desc);
		this.setCompleta(completa);
		this.setTexto(text);
		this.parrafo = parr;
		this.idioma = i;
		
	}
	
	public Idioma getIdiomaOriginal(){
		return this.parrafo.getDocumento().getIdioma();
	}
	
	public Idioma getIdioma(){
		return this.idioma;
	}
	
	public void setIdioma(Idioma i){
		this.idioma = i;
	}
	
	public Parrafo getParrafo(){
		return this.parrafo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setParrafo(Parrafo parrafo) {
		this.parrafo = parrafo;
	}
	
	
}
