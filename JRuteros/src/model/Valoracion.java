package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Valoracion")
public class Valoracion {
	
	
	@Id@GeneratedValue
	@Column(name="ID_VALORACION")
	private long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_RUTA")
	private Ruta ruta;
	
	@Column(name="valor")
	private int valor;
	
	public Valoracion(){
		super();
		this.valor = 0;
		
	}
	
	public Valoracion(Usuario usuario, Ruta ruta, int valor) {
		super();
		this.usuario = usuario;
		this.ruta = ruta;
		this.valor = valor;
		
		usuario.addValoracion(this);
		ruta.addValoracion(this);
		
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Ruta getRuta() {
		return ruta;
	}
	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	
}
