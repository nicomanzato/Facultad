package data;

import dao.DaoFactory;
import model.Ruta;
import model.Usuario;

public class addRuta {

	public static void main(String[] args){
		
		Usuario usuario = DaoFactory.getUsuarioDao().getUsuario("nico");
		
		Ruta ruta = new Ruta();
		
		ruta.setDueno(usuario);
		
		DaoFactory.getRutaDao().save(ruta);
		
	}
	
}
