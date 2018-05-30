package bd2.model;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Calendar;
import java.util.Collection;

public class Moderador extends Usuario {

	private Collection<Idioma> idiomas = new LinkedList<Idioma>();
	private Collection<Evaluacion> evaluaciones = new LinkedList<Evaluacion>();
	
	public Moderador(){
		
	}
	
	public Moderador(String email, String nombre, Date fec){
		this.setEmail(email);
		this.setNombre(nombre);
		this.setFechaDeCreacion(fec);
	}
	
	public int reputacion(){
		
		int rep=0;
		Iterator<Evaluacion> iter = this.evaluaciones.iterator();
		while(iter.hasNext()){
			
			Evaluacion element = iter.next();
			if(element != null){
				rep++;
			}
			
		}
		return rep;
	}
	
	/**
	 * @param t
	 * @param descripcion
	 * @param cali
	 * @throws Exception
	 */
	public void evaluar(Traduccion t, String descripcion, Integer cali) throws Exception{
		/*Registra	una	nueva evaluación (terminada) para la	Traduccion	recibida por parámetro en la colecciones de	evaluaciones.	El	puntaje	y	la	descripción	
		de la evaluación son recibidos por parámetro. En este caso, se toma el día actual como fecha de la evaluación.	
		
		
		boolean ok = false;
		Iterator<Idioma> iter = this.idiomas.iterator();
		
		while(!ok && iter.hasNext()){
			
			Idioma idio = iter.next();
			if(idio.getNombre() == t.getIdioma().getNombre()){
				ok=true;
			}
			
		}
		[
		*/
			if(this.idiomas.contains(t.getIdioma()) && this.idiomas.contains(t.getParrafo().getDocumento().getIdioma())){
				Calendar cal = Calendar.getInstance();
				Date fec = cal.getTime();
				String txt = t.getTexto();
				Evaluacion ev = new Evaluacion(fec, txt, t.getCompleta(), t, cali);
				this.evaluaciones.add(ev);
			}else{
				
				throw new Exception("No se pueden evaluar traducciones de idiomas que el moderador no maneja.");
				
			}
		
	}
	
	public void agregarIdioma(Idioma i){
		this.idiomas.add(i);
	}
	
	public Collection<Idioma> getIdiomas(){
		return idiomas;
	}
	
	public Collection<Evaluacion> getEvaluaciones(){
		return evaluaciones;
	}
	
	public boolean manejaIdioma(Idioma i){
		return this.idiomas.contains(i);
	}

	public void setIdiomas(Collection<Idioma> idiomas) {
		this.idiomas = idiomas;
	}

	public void setEvaluaciones(Collection<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}
	
	
	
}
