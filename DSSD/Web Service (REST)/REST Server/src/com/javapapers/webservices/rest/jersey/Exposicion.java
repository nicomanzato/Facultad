package com.javapapers.webservices.rest.jersey;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.jmx.snmp.Timestamp;

@XmlRootElement
public class Exposicion {
	
	private String id;
	private String dia;
	private String lugar;

	public Exposicion() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Exposicion(String id, String dia, String lugar) {
		super();
		this.id = id;
		this.dia = dia;
		this.lugar = lugar;
	}
	
	

}