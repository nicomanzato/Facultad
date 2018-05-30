package interfaces;

import java.util.List;

import model.Actividad;

public interface ActividadDaoInterface{

	public void save(Actividad actividad);
	public void update(Actividad actividad);
	public List<Actividad> getActividades();
	public void delete(Actividad actiidad);
	
}
