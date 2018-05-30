package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Punto")
public class Punto {
	
	@Id@GeneratedValue
	@Column(name="ID_PUNTO")
	private long id;
	
	@Column(name="lon")
	private String lon;
	
	@Column(name="lat")
	private String lat;
	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Ruta ruta;
	
	public Punto(String lat, String lon){
		
		this.lon = lon;
		this.lat = lat;
		
	}

	public Punto(){
		
	}
	
	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
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
	
	public String toString(){
		
		return "Punto, lat:"+this.getLat()+" lon: "+this.getLon();
		
	}
	
}
