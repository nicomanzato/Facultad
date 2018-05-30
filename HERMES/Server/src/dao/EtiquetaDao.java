package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import entidades.Contenido;
import entidades.Etiqueta;

public class EtiquetaDao extends Dao{
	

	public synchronized void insertarEtiqueta(String nombre){
    	
    	
	    this.ejecutarSQL("INSERT INTO Etiqueta(nombre) VALUES ('"+ nombre +"')");
		
		setChanged();
	    notifyObservers();
    	
    	
    }
	
	public synchronized void eliminarEtiqueta(String nombre){
    	
		Etiqueta etiqueta = this.getByNombre(nombre);
		
    	this.ejecutarSQL("DELETE FROM Etiqueta WHERE nombre='"+ etiqueta.getNombre() + "'");
    	this.ejecutarSQL("DELETE FROM Notificacion_Etiqueta WHERE idEtiqueta ='"+ etiqueta.getId() + "'");
    	this.setChanged();
    	this.notifyObservers();
    	
    }
	
	public synchronized void renombrarEtiqueta(String nombreAntiguo, String nombreNuevo){
		
		this.ejecutarSQL("UPDATE Etiqueta SET nombre = '"+ nombreNuevo +"' WHERE nombre = '"+ nombreAntiguo +"'");
		this.setChanged();
		this.notifyObservers();
		
	}
	
	public synchronized List<Etiqueta> getEtiquetas(){
		
		List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
	    
		etiquetas.add(new Etiqueta("Seleccione Etiqueta"));
		
	    ResultSet rs = this.selectSQL("SELECT * FROM Etiqueta ORDER BY Nombre" );
	    
	    try{
	    
		    while ( rs.next() ) {
		          
		         etiquetas.add(new Etiqueta(rs.getString("id"), rs.getString("nombre")));
		    
		    }
		
	    } catch (Exception e) {}
	    
	    return etiquetas;
	    
	}
	
	public synchronized boolean existe(String etiqueta){
		
		return super.existe("SELECT count(id) as cantidad FROM Etiqueta WHERE Nombre = '"+ etiqueta +"'");
			
	}
	
	public synchronized List<Etiqueta> getByIdNotificacion(String id){
		
		Connection c = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
	    
	    try {
		      
		      c= this.conectarBD();

		      stmt = c.createStatement();
		      
		      rs = stmt.executeQuery("SELECT Etiqueta.id, Etiqueta.nombre FROM Etiqueta, Notificacion_Etiqueta WHERE idEtiqueta = Etiqueta.id AND idNotificacion = '"+ id +"' ORDER BY Etiqueta.nombre");
		      
		      try{
			  	    
				    while ( rs.next() ) {
				          
				         etiquetas.add(new Etiqueta(rs.getString("id"),rs.getString("nombre")));
				    
				    }
				    
			    } catch (Exception e) {}
		      
		      
		      c.close();
		      
		 }catch(Exception e){
		    	
		    	etiquetas = null;
		    	
		} 
			
			return etiquetas; 
		
	}
	
	public synchronized Etiqueta getByNombre(String nombre){
		
		Connection c = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    Etiqueta etiqueta = null;
	    
	    try {
		      
		      c= this.conectarBD();

		      stmt = c.createStatement();
		      rs = stmt.executeQuery("SELECT * FROM Etiqueta WHERE nombre = '"+ nombre +"'");
		      
		      try{
			  	    
				    while ( rs.next() ) {
				          
				         etiqueta = new Etiqueta(rs.getString("id"),rs.getString("nombre"));
				    
				    }
				
			    } catch (Exception e) {}
		      
		      
		      c.close();
		      
		 }catch(Exception e){
		    	
		    	etiqueta = null;
		    	
		} finally{ return etiqueta; }
		
	}
	
	
}
