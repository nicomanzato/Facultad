package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import entidades.Contenido;
import entidades.Contexto;
import entidades.Etiqueta;

public class ContextoDao extends Dao{

	public synchronized void insertarContexto(String nombre){
    	
		this.ejecutarSQL("INSERT INTO Contexto(nombre) VALUES ('"+ nombre +"')");
		
		setChanged();
	    notifyObservers();
    	
    	
    }
	
	public synchronized List<Contexto> getContextos(){
		
		List<Contexto> contextos = new ArrayList<Contexto>();
		
		contextos.add(new Contexto("Seleccione Contexto"));
		
		ResultSet rs = this.selectSQL("SELECT * FROM Contexto ORDER BY Nombre" );
	    
	    try{
	    
		    while ( rs.next() ) {
		          
		         contextos.add(new Contexto(rs.getString("id"), rs.getString("nombre")));
		    
		    }
		
	    } catch (Exception e) {}
		
	    return contextos;
	    
	}
	
	public synchronized boolean existe(String contexto){
		
		return super.existe("SELECT count(id) as cantidad FROM Contexto WHERE Nombre = '"+ contexto +"'");
			
	}
	
	public synchronized Contexto getById(String id){
		
		Connection c = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    Contexto contexto = null;
	    
	    try {
		      
		      c= this.conectarBD();

		      stmt = c.createStatement();
		      rs = stmt.executeQuery("SELECT * FROM Contexto WHERE id = '"+ id +"'");
		      
		      try{
			  	    
				    while ( rs.next() ) {
				          
				         contexto = new Contexto(rs.getString("id"),rs.getString("nombre"));
				    
				    }
				    
			    } catch (Exception e) {}
		      
		      
		      c.close();
		      
		 }catch(Exception e){
		    	
		    	contexto = null;
		    	
		} 
			
			return contexto; 
		
	}
	
	public synchronized Contexto getByNombre(String nombre){
		
		Connection c = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    Contexto contexto = null;
	    
	    try {
	    	
		      c= this.conectarBD();

		      stmt = c.createStatement();
		      rs = stmt.executeQuery("SELECT * FROM Contexto WHERE nombre = '"+ nombre +"'");
		      
		      try{
			  	    
				    while ( rs.next() ) {
				          
				         contexto = new Contexto(rs.getString("id"),rs.getString("nombre"));
				    
				    }
				
			    } catch (Exception e) {}
		      
		      
		      c.close();
		      
		 }catch(Exception e){
		    	
		    	contexto = null;
		    	
		} 
	    
	    return contexto;
		
	}
	
}
