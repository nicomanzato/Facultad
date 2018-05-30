package dao;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import interfaces.ActividadDaoInterface;
import interfaces.ImagenDaoInterface;
import interfaces.RutaDaoInterface;
import interfaces.UsuarioDaoInterface;
import interfaces.ValoracionDaoInterface;

public class DaoFactory {

	private static ActividadDaoInterface actividadDao = null;
	private static ImagenDaoInterface imagenDao = null;
	private static RutaDaoInterface rutaDao = null;
	private static UsuarioDaoInterface usuarioDao = null;
	private static ValoracionDaoInterface valoracionDao = null;
	private static EntityManagerFactory entityManagerFactory = null;
	
	public static ActividadDaoInterface getActivityDao(){
		
		if (actividadDao == null) { actividadDao = new ActividadDaoJPA(); }
		
		return actividadDao;
		
	}
	
	public static EntityManagerFactory getEntityManagerFactory(){
		
		if (entityManagerFactory == null) { entityManagerFactory = Persistence.createEntityManagerFactory("JRuteros"); }
		
		return entityManagerFactory;
		
	}
	
	public static ValoracionDaoInterface getValoracionDao(){
	
		if (valoracionDao == null) { valoracionDao = new ValoracionDaoJPA(); }
		
		return valoracionDao;
		
	}
	
	public static ImagenDaoInterface getImagenDao(){
		
		if (imagenDao == null) { imagenDao = new ImagenDaoJPA(); }
		
		return imagenDao;
		
	}
	
	public static RutaDaoInterface getRutaDao(){
		
		if (rutaDao == null) { rutaDao = new RutaDaoJPA(); }
		
		return rutaDao;
		
	}
	
	public static UsuarioDaoInterface getUsuarioDao(){
		
		if (usuarioDao == null) { usuarioDao = new UsuarioDaoJPA(); }
		
		return usuarioDao;
		
	}
	
}
