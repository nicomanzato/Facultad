package data;

import java.util.Date;

import model.Administrador;
import services.UsuarioService;

public class DBInitialize {

	public static void main(String[] args){
		
		/*
		 * Administrador(String nombreUsuario, String nombre, String segundoNombre, String apellido, long dni,
			String domicilio, Date fechaNacimiento, char sexo, String email)
		 * 
		 */
		
		Administrador admin = new Administrador("nico","nicolas","dario","manzato",Long.valueOf(37983418),"calle siempreviva 1234", new Date("24/12/1993"), 'm', "nicomanzato@gmail.com");
		
		admin.setClave("nico");
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuarioService.createUsuario(admin);
		
		
		
	}
	
}
