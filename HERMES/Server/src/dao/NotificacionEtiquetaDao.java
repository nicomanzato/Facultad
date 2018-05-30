package dao;

import entidades.Etiqueta;

public class NotificacionEtiquetaDao extends Dao {

	public synchronized boolean existeAsignacionEtiqueta(String idEtiqueta, String idNotificacion){
		
		return super.existe("SELECT count(id) as cantidad FROM Notificacion_Etiqueta WHERE idEtiqueta = '"+ idEtiqueta +"' AND idNotificacion = '"+ idNotificacion +"'");
			
	}
	
	public synchronized void asignarEtiqueta(Etiqueta etiqueta, String idNotificacion) {
		
		if (!this.existeAsignacionEtiqueta(etiqueta.getId(),idNotificacion)){
			
			DaoFactory.getNotificacionDao().getById(idNotificacion).asignarEtiqueta(etiqueta);
			
		}else{
			
			DaoFactory.getNotificacionDao().getById(idNotificacion).desasignarEtiqueta(etiqueta);
			
		}
	}
	
	public synchronized void asignarEtiqueta(String idNotificacion, String idEtiqueta) {
		this.ejecutarSQL("INSERT INTO Notificacion_Etiqueta(idNotificacion, idEtiqueta) VALUES ('"
				+ idNotificacion + "','" + idEtiqueta + "')");
		this.setChanged();
		this.notifyObservers();
	}
	
	public synchronized void desasignarEtiqueta(String idNotificacion, String idEtiqueta) {
		this.ejecutarSQL("DELETE FROM Notificacion_Etiqueta WHERE idNotificacion ='"
				+ idNotificacion + "' AND idEtiqueta ='" + idEtiqueta + "'");
		this.setChanged();
		this.notifyObservers();
	}
	
	
}

