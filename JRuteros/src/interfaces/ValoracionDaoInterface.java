package interfaces;

import java.util.List;

import model.Actividad;
import model.Usuario;
import model.Valoracion;

public interface ValoracionDaoInterface {

	public void save(Valoracion valoracion);
	public void update(Valoracion valoracion);
	public List<Valoracion> getValoraciones();
	public void delete(Valoracion valoracion);
	
}
