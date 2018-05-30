package bean;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import dao.DaoFactory;
import model.PasswordGenerator;
import model.Usuario;
import services.UsuarioService;

public class UserBean {
	
	// VARIABLES DEL MODELO
	
	String username;
	String password;
	String email;
	String nombre;
	String segundoNombre;
	String apellido;
	Long dni;
	String domicilio;
	Date fechaNacimiento;
	char sexo;
	
	
	// VARIABLES AUXILIARES
	
	Usuario user;
	boolean admin = false;
	boolean loggedIn = false;
	UsuarioService service;
	
	// Cambio de contrasena
	
	String auxPassword;
	String newPassword;
	String confirmNewPassword;
	
	//Bloqueo de usuarios
	
	String blockUsername;
	
	public UserBean() { service = new UsuarioService(); }
	
	public String getUsername() { return this.username; }
	
	public void setUsername(String data) { this.username = data; }
	
	public String getPassword() { return password; }
	
	public void setPassword(String data) {this.password = data; }
	
	public String getEmail() { return email; }
	
	public void setEmail(String email) { this.email = email; }
	
	public String getNombre() {
		return nombre;
	}
	
	

	public String getBlockUsername() {
		return blockUsername;
	}

	public void setBlockUsername(String blockUsername) {
		this.blockUsername = blockUsername;
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

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
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

	public String login() {
		
		if ( service.validateLogin(this.getUsername(), this.getPassword()) ) {
			
			this.setUser(service.getUsuario(this.getUsername()));
			
			if(this.getUser().isHabilitado()){
			
				loggedIn = true;
				
				this.setApellido(this.getUser().getApellido());
				this.setDni(this.getUser().getDNI());
				this.setDomicilio(this.getUser().getDomicilio());
				this.setEmail(this.getUser().getEmail());
				this.setPassword(this.getPassword());
				
				this.setFechaNacimiento(this.getUser().getFechaNacimiento());
				
				this.setNombre(this.getUser().getNombre());
				this.setSegundoNombre(this.getUser().getSegundoNombre());
				this.setSexo(this.getUser().getSexo());
				this.setUsername(this.getUser().getNombreUsuario());
				this.setAdmin(this.getUser().esAdministrador());
				
				return "success";
			
			}else{
				
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage("El usuario esta bloqueado");
				context.addMessage("loginForm", message);
				return "failure";
				
			}
			
		} else {
			
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage("Datos incorrectos");
			context.addMessage("loginForm", message);
			return "failure";
			
		}
		
	}
	
	public String register(){
			
		Usuario user = new Usuario(this.getUsername(), this.getNombre(), this.getSegundoNombre(), this.getApellido(), this.getDni(),
				this.getDomicilio(), this.getFechaNacimiento(), this.getSexo(), this.getEmail());
		
		user.setClave(new PasswordGenerator(6).getString());
		
		this.setPassword(user.getClave());
		
		DaoFactory.getUsuarioDao().save(user);
			
		return "success";
			
	}	
	
	public String update(){
		
		user.setApellido(this.getApellido());
		user.setDNI(Long.valueOf(this.getDni()));
		user.setDomicilio(this.getDomicilio());
		user.setEmail(this.getEmail());
		user.setFechaNacimiento(this.getFechaNacimiento());
		user.setNombre(this.getNombre());
		user.setSegundoNombre(this.getSegundoNombre());
		user.setSexo(this.getSexo());
		
		service.updateUsuario(user);
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage("Se ha actualizado la informacion");
		context.addMessage("backendForm", message);
		return "success";
		
	}
	
	public String blockUser(){
		
		Usuario user = service.getUsuario(this.getBlockUsername());
		
		if (user == null){ //Si el usuario no existe
			
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage("El usuario "+this.getBlockUsername()+" no existe.");
			context.addMessage("blockForm", message);
			
			return "failure";
			
		}
		
		if (user.isHabilitado()){
			
		user.setHabilitado(!user.isHabilitado());
		service.updateUsuario(user);
		
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage("Se ha bloqueado al usuario "+this.getBlockUsername());
		context.addMessage("blockForm", message);
		
		this.setBlockUsername(null);
		
		return "success";
		
		}else{
			
			user.setHabilitado(!user.isHabilitado());
			service.updateUsuario(user);
			
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage("Se ha desbloqueado al usuario "+this.getBlockUsername());
			context.addMessage("blockForm", message);
			
			this.setBlockUsername(null);
			
			return "success";
			
			
		}
		
	}
	
	public String logout(){
		
		this.loggedIn = false;
		this.setApellido(null);
		this.setDni(null);
		this.setDomicilio(null);
		this.setEmail(null);
		this.setFechaNacimiento(null);
		this.setNombre(null);
		this.setPassword(null);
		this.setSegundoNombre(null);
		this.setSexo(' ');
		this.setUser(null);
		this.setUsername(null);
		this.setAdmin(false);
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
		
	}
	
	public List<Usuario> getUsuariosBloqueados(){
		
		System.out.println("se ejecuto el get de usuarios");
		
		System.out.println(service.getUsuarios().size());
		
		return service.getUsuarios();
		
	}
	
	public String changePassword(){
		
		if(this.getPassword().equals(this.getAuxPassword())){
			
			if(this.getNewPassword().equals(this.getConfirmNewPassword())){
				
				this.setPassword(this.getNewPassword());
				
				this.getUser().setClave(this.getPassword());
				
				service.updateUsuario(this.getUser());
				
				return this.logout();
				
			}
			
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage("Las contrasenas no coinciden");
			context.addMessage("changePasswordForm", message);
			
			return "failure";
			
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage("La informacion ingresada no es correcta");
		context.addMessage("changePasswordForm", message);
		
		return "failure";
		
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	public String getAuxPassword() {
		return auxPassword;
	}

	public void setAuxPassword(String auxPassword) {
		this.auxPassword = auxPassword;
	}

	
	
} 