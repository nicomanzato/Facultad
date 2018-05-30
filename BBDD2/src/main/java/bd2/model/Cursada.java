package bd2.model;
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Cursada {	
	
	private long idCursada;
	private java.util.Date inicio;
	private Collection<Prueba> pruebas = new ArrayList<Prueba>();
	private Curso curso;
	private Usuario usuario;
	
	public Cursada(){
		
	}
	
	public Cursada(Curso c, java.util.Date fec, Usuario u){
		this.curso = c;
		this.inicio = fec;
		this.usuario = u;
		this.usuario.agregarCursada(this);
	}
	
	public boolean finalizada(){
		/*
		 * 	Retorna un booleano indicando si la cursada esta finalizada. Esto significa que	 existe	 al	
			menos una prueba (aprobada) para cada lección del curso correspondiente.	
		 */

		for (Leccion leccion : this.getCurso().getLecciones()){
			
			if (!this.leccionesAprobadas().contains(leccion)) return false;
			
		}
		
		return true;
		
	}
	
	public void agregarPrueba(Prueba p){
		this.pruebas.add(p);
	}
	
	public Collection<Prueba> getPruebas(){
		return this.pruebas;
	}
	
	public Collection<Leccion> leccionesAprobadas(){
		/* Retorna una colección con todas las lecciones del	
			curso correspondiente para las que existe una prueba aprobada.
		 */
		
		Collection<Leccion> res = new ArrayList<Leccion>();
		
		Iterator<Prueba> iter = this.pruebas.iterator();
		while(iter.hasNext()){
			Prueba element = iter.next();
			if(element.aprobada()){
				res.add(element.getLeccion());
			}
		}
		
		return res;
		
	}
	
	public int getNivel(){
		return this.curso.getNivel();
	}

	public java.util.Date getInicio() {
		return inicio;
	}

	public void setInicio(java.util.Date inicio) {
		this.inicio = inicio;
	}

	public Idioma getIdioma() {
		return this.curso.getIdioma();
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario user) {
		this.usuario = user;
	}

	public long getIdCursada() {
		return idCursada;
	}

	public void setIdCursada(long idCursada) {
		this.idCursada = idCursada;
	}

	public void setPruebas(Collection<Prueba> pruebas) {
		this.pruebas = pruebas;
	}

	
	
}
