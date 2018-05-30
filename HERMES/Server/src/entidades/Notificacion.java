package entidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import dao.DaoFactory;
import MockPackage.Mock;
import MockPackage.MockStringAttribute;
import MockPackage.MockTodayAttribute;

@Mock
public class Notificacion {

	
	String id;
	
	@MockTodayAttribute
	public String fecha;

	@MockStringAttribute({"entusiasmado","feliz","triste"})
	public Contenido contenido;
	
	@MockStringAttribute({"casa","establo"})
	public Contexto contexto;
	
	@MockStringAttribute({"emociones","comida"})
	public Categoria categoria;
	
	@MockStringAttribute({"Ricardito Fort","pepe"})
	public Paciente paciente;
	
	List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
	
	public Notificacion(Contenido contenido, Contexto contexto, Categoria categoria, Paciente paciente){
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		this.fecha = format.format(new Date());
		
		if (!DaoFactory.getContenidoDao().existe(contenido.getNombre())){
			
			DaoFactory.getContenidoDao().insertarContenido(contenido.getNombre());
	
		}
		
		if (!DaoFactory.getContextoDao().existe(contexto.getNombre())){
			
			DaoFactory.getContextoDao().insertarContexto(contexto.getNombre());
			
		}
		
		if (!DaoFactory.getCategoriaDao().existe(categoria.getNombre())){
			
			DaoFactory.getCategoriaDao().insertarCategoria(categoria.getNombre());
			
		}
		
		if (!DaoFactory.getPacienteDao().existe(paciente.getNombre())){
			
			DaoFactory.getPacienteDao().insertarPaciente(paciente.getNombre());
			
		}
		
		this.setContenido(DaoFactory.getContenidoDao().getByNombre(contenido.getNombre()));
		
		this.setCategoria(DaoFactory.getCategoriaDao().getByNombre(categoria.getNombre()));
		
		this.setContexto(DaoFactory.getContextoDao().getByNombre(contexto.getNombre()));
		
		this.setPaciente(DaoFactory.getPacienteDao().getByNombre(paciente.getNombre()));

		this.setEtiquetas(new ArrayList<Etiqueta>());
		
	}
	
	public void asignarEtiqueta(Etiqueta etiqueta){
		
		etiquetas.add(etiqueta);
		
		DaoFactory.getNotificacionEtiquetaDao().asignarEtiqueta(this.getId(), etiqueta.getId());
		
	}
	
	public void desasignarEtiqueta(Etiqueta etiqueta){
		
		etiquetas.remove(etiqueta);
		
		DaoFactory.getNotificacionEtiquetaDao().desasignarEtiqueta(this.getId(), etiqueta.getId());
		
	}
	
	public Notificacion( String idContenido, String idContexto, String idCategoria, String idPaciente, String fecha, String id){
		
		this.setContenido(DaoFactory.getContenidoDao().getById(idContenido));
		this.setContexto(DaoFactory.getContextoDao().getById(idContexto));
		this.setCategoria(DaoFactory.getCategoriaDao().getById(idCategoria));
		this.setPaciente(DaoFactory.getPacienteDao().getById(idPaciente));
		this.setEtiquetas(DaoFactory.getEtiquetaDao().getByIdNotificacion(id));
		this.setId(id);
		this.setFecha(fecha);
		this.setEtiquetas(DaoFactory.getEtiquetaDao().getByIdNotificacion(id));
		
	}
	
	public Notificacion(){
		
		
		
	}
	
	public String toString(){
		
		return this.getPaciente()+"-"+this.getCategoria()+"-"+this.getContexto()+"-"+this.getContenido()+"-"+this.getFecha();
		
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Contenido getContenido() {
		return contenido;
	}

	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}

	public Contexto getContexto() {
		return contexto;
	}

	public void setContexto(Contexto contexto) {
		this.contexto = contexto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(List<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}
	
	public String getEtiquetasString(){
		
		String etiquetasString = "";
		
		for ( Etiqueta etiqueta : this.getEtiquetas() ){
			
			etiquetasString = etiquetasString + "<" + etiqueta + ">";
			
		}
		
		return etiquetasString;
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
	
}
