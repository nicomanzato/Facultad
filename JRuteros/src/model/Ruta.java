package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="Ruta")
@XmlRootElement
public class Ruta {
	
	@Id@GeneratedValue
	@Column(name="ID_RUTA")
	@XmlElement
	private long id;
	
	@Column(name="nombre")
	@XmlElement
	private String nombre;
	
	@Column(name="descripcion")
	@XmlElement
	private String descripcion;
	
	@Column(name="publico")
	@XmlElement
	private boolean publico;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="ruta", fetch = FetchType.EAGER)
	@XmlElement
	private List<Punto> recorrido;
	
	@Column(name="formato")
	@XmlElement
	private FormatoRuta formato;
	
	@Column(name="distancia")
	@XmlElement
	private double distancia;
	
	@Column(name="dificultad")
	@XmlElement
	private DificultadRuta dificultad;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_ACTIVIDAD")
	@XmlElement
	private Actividad actividad;
	
	@Column(name="tiempoEstimado")
	@XmlElement
	private int tiempoEstimado; // minutos
	
	@Column(name="fechaRealizacion")
	@XmlElement
	private Date fechaRealizacion;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="ruta")
	@XmlElement
	private List<Imagen> fotos;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="ruta")
	@XmlElement
	private List<Valoracion> valoraciones;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	@XmlElement
	private List<Usuario> realizadaPorUsuarios;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="ID_USUARIO")
	@XmlElement
	private Usuario dueno;
	
	public Ruta(String nombre, String descripcion, boolean publico, FormatoRuta formato,
			double distancia, DificultadRuta dificultad, Actividad categoria, int tiempoEstimado,
			Date fechaRealizacion, Usuario duenoRuta) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.publico = publico;
		this.recorrido = new ArrayList<Punto>();
		this.formato = formato;
		this.distancia = distancia;
		this.dificultad = dificultad;
		this.actividad = categoria;
		this.tiempoEstimado = tiempoEstimado;
		this.fechaRealizacion = fechaRealizacion;
		this.setDueno(duenoRuta);
		this.setRecorrido(new ArrayList<Punto>());
		this.setRealizadaPorUsuarios(new ArrayList<Usuario>());
		this.setValoraciones(new ArrayList<Valoracion>());
		this.setFotos(new ArrayList<Imagen>());
	}
	
	public Ruta(){
		super();
		this.setRecorrido(new ArrayList<Punto>());
		this.setRealizadaPorUsuarios(new ArrayList<Usuario>());
		this.setValoraciones(new ArrayList<Valoracion>());
		this.setFotos(new ArrayList<Imagen>());
	}
	
	public List<Valoracion> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(List<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isPublico() {
		return publico;
	}
	public void setPublico(boolean publico) {
		this.publico = publico;
	}
	public List<Punto> getRecorrido() {
		return recorrido;
	}
	public void setRecorrido(List<Punto> recorrido) {
		this.recorrido = recorrido;
	}
	public FormatoRuta getFormato() {
		return formato;
	}
	public void setFormato(FormatoRuta formato) {
		this.formato = formato;
	}
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	public DificultadRuta getDificultad() {
		return dificultad;
	}
	public void setDificultad(DificultadRuta dificultad) {
		this.dificultad = dificultad;
	}
	public Actividad getActividad() {
		return actividad;
	}
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	public int getTiempoEstimado() {
		return tiempoEstimado;
	}
	public void setTiempoEstimado(int tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}
	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}
	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}
	public List<Imagen> getFotos() {
		return fotos;
	}
	
	private void setFotos(List<Imagen> fotos) {
		this.fotos = fotos;
	}

	public void addFoto(Imagen foto){
		
		this.getFotos().add(foto);
		
	}

	public List<Usuario> getRealizadaPorUsuarios() {
		return realizadaPorUsuarios;
	}


	private void setRealizadaPorUsuarios(List<Usuario> realizadaPorUsuarios) {
		this.realizadaPorUsuarios = realizadaPorUsuarios;
	}
	
	public void addRealizadaPorUsuario(Usuario usuario){
		
		this.getRealizadaPorUsuarios().add(usuario);
		
	}
	
	public boolean isRealizadaPorUsuario(Usuario usuario){
		
		return this.getRealizadaPorUsuarios().contains(usuario);
		
	}
	
	public Usuario getDueno() {
		return dueno;
	}


	public void setDueno(Usuario dueno) {
		this.dueno = dueno;
	}
	
	
	
	public void addValoracion(Valoracion valoracion){
		
		this.getValoraciones().add(valoracion);
		
	}
	
	public void addPunto(Punto punto){
		
		punto.setRuta(this);
		this.getRecorrido().add(punto);
		
	}
	
	public void removePunto(long idPunto){
		
		// hubo que resolverlo de estar forma ya que daba un error de concurrencia.
		
		List<Punto> toRemove = new ArrayList<Punto>();
		for (Punto punto : this.getRecorrido()) {
		    if (punto.getId() == idPunto) {
		        toRemove.add(punto);
		    }
		}
		this.getRecorrido().removeAll(toRemove);
		
	}
	
	public void removePunto(){
		
		this.setRecorrido(new ArrayList<Punto>());
		
	}
	
}
