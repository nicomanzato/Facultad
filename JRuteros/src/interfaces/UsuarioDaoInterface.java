package interfaces;

import java.util.List;

import model.Actividad;
import model.Usuario;

public interface UsuarioDaoInterface {

	public void save(Usuario usuario);
	public void update(Usuario usuario);
	public List<Usuario> getUsuarios();
	public void delete(Usuario usuario);
	public Usuario getUsuario(String id);
	public boolean validateLogin(String username, String password);
	
}
