package services;

import java.util.List;

import dao.DaoFactory;
import interfaces.UsuarioDaoInterface;
import model.Usuario;

public class UsuarioService {

	UsuarioDaoInterface usuarioDao;
	
	public UsuarioService(){
		
		usuarioDao = DaoFactory.getUsuarioDao();
		
	}
	
	public void updateUsuario(Usuario usuario){
		
		usuarioDao.update(usuario);
		
	}
	
	public void createUsuario(Usuario usuario){
		
		usuarioDao.save(usuario);
		
	}
	
	public Usuario getUsuario(String username){
		
		return usuarioDao.getUsuario(username);
		
	}
	
	public List<Usuario> getUsuarios(){
		
		return usuarioDao.getUsuarios();
		
	}
	
	public boolean validateLogin(String user, String password){
		
		return usuarioDao.validateLogin(user, password);
		
	}
	
}
