package dao;
import dao.*;

public class DaoFactory {
	
    private static NotificacionDao notificacionDao;
    private static EtiquetaDao etiquetaDao;
    private static CategoriaDao categoriaDao;
    private static PacienteDao pacienteDao;
    private static ContenidoDao contenidoDao;
    private static ContextoDao contextoDao;
    private static NotificacionEtiquetaDao notificacionEtiquetaDao;

    public static NotificacionDao getNotificacionDao(){
        if (notificacionDao == null){
        	notificacionDao = new NotificacionDao();
        }
        return notificacionDao;
    }
    
    public static NotificacionEtiquetaDao getNotificacionEtiquetaDao(){
        if (notificacionEtiquetaDao == null){
        	notificacionEtiquetaDao = new NotificacionEtiquetaDao();
        }
        return notificacionEtiquetaDao;
    }

    public static EtiquetaDao getEtiquetaDao(){
        if (etiquetaDao == null){
            etiquetaDao = new EtiquetaDao();
        }
        return etiquetaDao;
    }

    public static CategoriaDao getCategoriaDao(){
        if (categoriaDao == null){
            categoriaDao = new CategoriaDao();
        }
        return categoriaDao;
    }

    public static PacienteDao getPacienteDao(){
        if (pacienteDao == null){
        	pacienteDao = new PacienteDao();
        }
        return pacienteDao;
    }
    
    public static ContenidoDao getContenidoDao(){
        if (contenidoDao == null){
        	contenidoDao = new ContenidoDao();
        }
        return contenidoDao;
    }
    
    public static ContextoDao getContextoDao(){
  
    	if (contextoDao == null){
    		
    		contextoDao = new ContextoDao();
    		
    	}
    	
    	return contextoDao;
    	
    }
}