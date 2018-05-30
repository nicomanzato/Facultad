package model;
import javax.persistence.*;

@Entity
@Table(name="Actividad")
public class Actividad {

	@Id@GeneratedValue
	@Column(name="ID_ACTIVIDAD")
	private long id;
	
	@Column(name="nombreCategoria")
	private String nombre; // Mountain bike / Ciclismo / Cicloturismo / Senderismo / Carrera por montaña / Alpinismo / Motociclismo / Cuatriciclo / Esquí / Kayac / Vela / A caballo .
	
	@Column(name="habilitada")
	private boolean habilitada;

	public Actividad(String nombre, boolean habilitada) {
		super();
		this.nombre = nombre;
		this.habilitada = habilitada;
	}
	
	public String toString(){
		
		return this.getNombre();
		
	}
	
	public Actividad() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private boolean isHabilitada() {
		return habilitada;
	}

	private void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
