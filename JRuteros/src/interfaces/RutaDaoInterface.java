package interfaces;

import java.util.List;

import model.Ruta;

public interface RutaDaoInterface{

	public void save(Ruta ruta);
	public void update(Ruta ruta);
	public List<Ruta> getRutas();
	public void delete(Ruta ruta);
	
}
