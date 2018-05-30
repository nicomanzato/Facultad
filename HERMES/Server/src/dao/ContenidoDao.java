package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import entidades.Categoria;
import entidades.Contenido;
import entidades.Etiqueta;

public class ContenidoDao extends Dao{
	
	public void insertarContenido(String nombre){
    	
		this.ejecutarSQL("INSERT INTO Contenido(nombre) VALUES ('"+ nombre +"')");
		
		setChanged();
	    notifyObservers();
    	
    	
    }
	
	public List<Contenido> getContenidos(){
		
		List<Contenido> contenidos = new ArrayList<Contenido>();
		
		contenidos.add(new Contenido("Seleccione Contenido"));
		
		ResultSet rs = this.selectSQL("SELECT * FROM Contenido ORDER BY Nombre" );
	    
	    try{
	    
		    while ( rs.next() ) {
		          
		         contenidos.add(new Contenido(rs.getString("id"), rs.getString("nombre")));
		    
		    }
		
	    } catch (Exception e) {}
		
	    return contenidos;
	    
	}
	
	public boolean existe(String contenido){
		
		return super.existe("SELECT count(id) as cantidad FROM Contenido WHERE Nombre = '"+ contenido +"'");
			
	}
	
	public Contenido getById(String id){
		
		Connection c = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    Contenido contenido = null;
	    
	    try {
		      
		      c= this.conectarBD();

		      stmt = c.createStatement();
		      rs = stmt.executeQuery("SELECT * FROM Contenido WHERE id = '"+ id +"'");
		      
		      try{
			  	    
				    while ( rs.next() ) {
				          
				         contenido = new Contenido(rs.getString("id"),rs.getString("nombre"));
				    
				    }
				    
			    } catch (Exception e) {}
		      
		      
		      c.close();
		      
		 }catch(Exception e){
		    	
		    	contenido = null;
		    	
		} 
			
			return contenido; 
		
	}
	
	public Contenido getByNombre(String nombre){
		
		Connection c = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    Contenido contenido = null;
	    
	    try {
		      
		      c= this.conectarBD();

		      stmt = c.createStatement();
		      rs = stmt.executeQuery("SELECT * FROM Contenido WHERE nombre = '"+ nombre +"'");
		      
		      try{
			  	    
				    while ( rs.next() ) {
				          
				         contenido = new Contenido(rs.getString("id"),rs.getString("nombre"));
				    
				    }
				
			    } catch (Exception e) {}
		      
		      
		      c.close();
		      
		 }catch(Exception e){
		    	
		    	contenido = null;
		    	
		} finally{ return contenido; }
		
	}

}
