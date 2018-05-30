package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.DaoFactory;
import interfaces.RutaDaoInterface;
import model.Actividad;
import model.DificultadRuta;
import model.FormatoRuta;
import model.Punto;
import model.Ruta;
import model.Usuario;

public class RutaService {

	RutaDaoInterface rutaDao;
	static Ruta ruta = new Ruta("unaRuta", "una descripcion", true, FormatoRuta.SOLO_IDA,5.66,DificultadRuta.FACIL, new Actividad() , 5, new Date(), new Usuario());;
	
	public RutaService(){
		
		rutaDao = DaoFactory.getRutaDao();
		
	}
	
	public void updateRuta(Ruta ruta){
		
		rutaDao.update(ruta);
		
	}
	
	public void createRuta(Ruta ruta){
		
		rutaDao.save(ruta);
		
	}
	
	public Ruta getRuta(String id){
		
		return ruta;
		
	}
	
	public List<Ruta> getRutas(){
		
		return rutaDao.getRutas();
		
	}
	
	public int getRutasCount(){
		
		return rutaDao.getRutas().size();
		
	}
	
	static int idAux = 0;
	
	public void addPunto(String id,Punto punto){
		
		punto.setId(idAux);
		idAux++;
		this.getRuta(id).addPunto(punto);
		
		System.out.println("RutaSize: "+this.getRuta(id).getRecorrido().size());
		
	}
	
	public void removePunto(String id,long idPunto){
		
		this.getRuta(id).removePunto(idPunto);
		
	}
	
	public void removePunto(String id){
		
		this.getRuta(id).removePunto();
		
	}
	
}
