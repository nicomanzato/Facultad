package model;

import java.util.ArrayList;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Administrador extends Usuario{

	public Administrador(){
		
		super();
		
	}
	
	public Administrador(String nombreUsuario, String nombre, String segundoNombre, String apellido, long dni,
			String domicilio, Date fechaNacimiento, char sexo, String email) {
		
		super(nombreUsuario,nombre,segundoNombre,apellido,dni,domicilio,fechaNacimiento,sexo,email);
		
		// this.clave = generarClave(); y enviar email.

	}
	
	public boolean esAdministrador(){
		
		return true;
		
	}
	
}
