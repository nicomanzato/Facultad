package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // o @Inheritance
@DiscriminatorColumn(name="ROL")
@DiscriminatorValue("USER")
@Table(name="Usuario")
public class Usuario {
	
	@Id@GeneratedValue
	@Column(name="ID_USUARIO")
	private long id;
	
	
	@Column(name="nombreUsuario", unique=true)
	protected String nombreUsuario;
	
	@Column(name="nombre")
	protected String nombre;
	
	@Column(name="segundoNombre")
	protected String segundoNombre;
	
	@Column(name="apellido")
	protected String apellido;
	
	@Column(name="dni")
	protected long DNI;
	
	@Column(name="domicilio")
	protected String domicilio;
	
	@Column(name="fechaNacimiento")
	protected Date fechaNacimiento;
	
	@Column(name="sexo")
	protected char sexo;
	
	@Column(name="email")
	protected String email;
	
	@Column(name="clave")
	protected String clave;
	
	@Column(name="habilitado")
	protected boolean habilitado;
	
	@OneToMany(cascade = CascadeType.ALL)
	protected List<Ruta> misRutas;
	
	@ManyToMany
	@JoinColumn(name="ID_RUTA")
	protected List<Ruta> rutasRealizadas;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="usuario")
	private List<Valoracion> valoraciones;
	
	public Usuario(){
		super();
		this.setMisRutas( new ArrayList<Ruta>());
		this.setRutasRealizadas(new ArrayList<Ruta>());;
		this.setValoraciones(new ArrayList<Valoracion>());
		
	}
	
	public Usuario(String nombreUsuario, String nombre, String segundoNombre, String apellido, long dNI,
			String domicilio, Date fechaNacimiento, char sexo, String email) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.nombre = nombre;
		this.segundoNombre = segundoNombre;
		this.apellido = apellido;
		DNI = dNI;
		this.domicilio = domicilio;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.email = email;
		this.habilitado = true;
		
		this.setMisRutas( new ArrayList<Ruta>());
		this.setRutasRealizadas(new ArrayList<Ruta>());;
		this.setValoraciones(new ArrayList<Valoracion>());

	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long getDNI() {
		return DNI;
	}

	public void setDNI(long dNI) {
		DNI = dNI;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public List<Ruta> getMisRutas() {
		return misRutas;
	}

	public List<Ruta> getRutasRealizadas() {
		return rutasRealizadas;
	}

	public void addMisRutas(Ruta ruta){
		
		this.getMisRutas().add(ruta);
		
	}
	
	public void addRutasRealizadas(Ruta ruta){
		
		this.getRutasRealizadas().add(ruta);
		
	}

	protected void setMisRutas(List<Ruta> misRutas) {
		this.misRutas = misRutas;
	}

	protected void setRutasRealizadas(List<Ruta> rutasRealizadas) {
		this.rutasRealizadas = rutasRealizadas;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	public boolean esAdministrador(){
		
		return false;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Valoracion> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(List<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}
	
	public void addValoracion(Valoracion valoracion){
		
		this.getValoraciones().add(valoracion);
		
	}

}
