package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import dao.DaoFactory;
import model.Actividad;
import model.DificultadRuta;
import model.FormatoRuta;
import model.Imagen;
import model.Ruta;
import model.Usuario;

public class RutaBean {

	String nombre;
	String descripcion;
	boolean publico;
	FormatoRuta formato;
	long distancia;
	DificultadRuta dificultad;
	Actividad actividad;
	int tiempoEstimado;
	Date fechaRealizacion;
	List<Imagen> imagenes;
	List<Usuario> realizadaPorUsuarios;
	Usuario dueno;
	
	
	/*  -------- AUX --------  */
	
	
	List<FormatoRuta> listadoFormatoRuta;
	List<DificultadRuta> listadoDificultadRuta;
	List<Actividad> listadoActividadRuta = DaoFactory.getActivityDao().getActividades();
	
	@ManagedProperty(value="#{UserBean}")
	private UserBean userBean;


	public List<Actividad> getListadoActividadRuta() {
		return listadoActividadRuta;
	}


	public void setListadoActividadRuta(List<Actividad> listadoActividadRuta) {
		this.listadoActividadRuta = listadoActividadRuta;
	}


	public List<DificultadRuta> getListadoDificultadRuta() {
		return listadoDificultadRuta;
	}


	public void setListadoDificultadRuta(List<DificultadRuta> listadoDificultadRuta) {
		this.listadoDificultadRuta = listadoDificultadRuta;
	}


	public List<FormatoRuta> getListadoFormatoRuta() {
		return listadoFormatoRuta;
	}


	public void setListadoFormatoRuta(List<FormatoRuta> listadoFormatoRuta) {
		this.listadoFormatoRuta = listadoFormatoRuta;
	}


	public RutaBean(){
		
		listadoFormatoRuta = new ArrayList<FormatoRuta>();
		
		for(FormatoRuta formatoAux : FormatoRuta.values()){	
			listadoFormatoRuta.add(formatoAux);
		}
		
		listadoDificultadRuta = new ArrayList<DificultadRuta>();
		
		for(DificultadRuta dificultadAux: DificultadRuta.values()){
			listadoDificultadRuta.add(dificultadAux);
		}
		
		
		
	}
	
	public UserBean getUserBean() {
		return userBean;
	}


	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public boolean isPublico() {
		return publico;
	}
	public FormatoRuta getFormato() {
		return formato;
	}
	public long getDistancia() {
		return distancia;
	}
	public DificultadRuta getDificultad() {
		return dificultad;
	}
	public Actividad getActividad() {
		return actividad;
	}
	public int getTiempoEstimado() {
		return tiempoEstimado;
	}
	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}
	public List<Imagen> getImagenes() {
		return imagenes;
	}
	public List<Usuario> getRealizadaPorUsuarios() {
		return realizadaPorUsuarios;
	}
	
	public Usuario getDueno(){
		return dueno;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public void setPublico(boolean publico) {
		this.publico = publico;
	}


	public void setFormato(FormatoRuta formato) {
		this.formato = formato;
	}


	public void setDistancia(long distancia) {
		this.distancia = distancia;
	}


	public void setDificultad(DificultadRuta dificultad) {
		this.dificultad = dificultad;
	}


	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}


	public void setTiempoEstimado(int tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}


	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}


	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}


	public void setRealizadaPorUsuarios(List<Usuario> realizadaPorUsuarios) {
		this.realizadaPorUsuarios = realizadaPorUsuarios;
	}


	public void setDueno(Usuario dueno) {
		this.dueno = dueno;
	}
	
	
	public String createRuta(){
		
		Ruta ruta = new Ruta(this.getNombre(),this.getDescripcion(),this.isPublico(),this.getFormato(),this.getDistancia(),
				this.getDificultad(),this.getActividad(),this.getTiempoEstimado(),this.getFechaRealizacion(),this.getUserBean().getUser());
		
		DaoFactory.getRutaDao().save(ruta);
		return "success";
	}
	
	
}
