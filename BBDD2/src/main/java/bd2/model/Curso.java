package bd2.model;
import java.util.ArrayList;
import java.util.Collection;

public class Curso {

	private long idCurso;
	private String nombre;
	private int nivel;
	private Collection<Leccion> lecciones = new ArrayList<Leccion>();
	private Idioma idioma;
	
	public Curso(){
		
	}
	
	public Curso (String name, Idioma idiom, int i){
		this.setIdioma(idiom);
		this.setNivel(i);
		this.setNombre(name);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public Idioma getIdioma() {
		return idioma;
	}
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	public Collection<Leccion> getLecciones() {
		return lecciones;
	}
	public void agregarLeccion(Leccion lecciones) {
		this.lecciones.add(lecciones);
	}

	public long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(long idCurso) {
		this.idCurso = idCurso;
	}

	public void setLecciones(Collection<Leccion> lecciones) {
		this.lecciones = lecciones;
	}

	
	
}
