package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Imagen")
public class Imagen {

	@Id@GeneratedValue
	@Column(name="ID_IMAGEN")
	private long id;
	
	@Column(name="path")
	private String path;

	public Imagen(String path) {
		super();
		this.path = path;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Ruta ruta;
	
	public Imagen(){
		super();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	
	
	
}
