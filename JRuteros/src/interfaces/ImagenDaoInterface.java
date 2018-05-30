package interfaces;

import java.util.List;

import model.Imagen;

public interface ImagenDaoInterface {
	
	public void save(Imagen imagen);
	public void update(Imagen imagen);
	public List<Imagen> getImagenes();
	public void delete(Imagen imagen);
}
