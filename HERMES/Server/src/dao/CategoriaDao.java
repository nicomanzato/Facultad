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
import entidades.Paciente;

public class CategoriaDao extends Dao {

	
	public synchronized List<Categoria> getCategorias(){
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		categorias.add(new Categoria("Seleccione Categoria"));
		
		Connection c = null;
	    Statement stmt = null;
	    
	    try {
	      
	      c= this.conectarBD();

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM Categoria" );
	      
	      while ( rs.next() ) {
	          
	    	  categorias.add(new Categoria(rs.getString("id"), rs.getString("nombre")));
	         
	       }
	      
	      c.close();
		
	    } catch(Exception e) {}
	    
	    return categorias;
	    
	}
	
	public synchronized boolean existe(String categoria){
		
		return super.existe("SELECT count(id) as cantidad FROM Categoria WHERE Nombre = '"+ categoria +"'");
			
	}
	
	public synchronized void insertarCategoria(String nombre){
    	
    	
	    this.ejecutarSQL("INSERT INTO Categoria(nombre) VALUES ('"+ nombre +"')");
		
		setChanged();
	    notifyObservers();
    	
    	
    }
	
	public synchronized Categoria getById(String id){
		
		Connection c = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    Categoria categoria = null;
	    
	    try {
		      
		      c= this.conectarBD();

		      stmt = c.createStatement();
		      rs = stmt.executeQuery("SELECT * FROM Categoria WHERE id = '"+ id +"'");
		      
		      try{
			  	    
				    while ( rs.next() ) {
				          
				         categoria = new Categoria(rs.getString("id"),rs.getString("nombre"));
				    
				    }
				    
			    } catch (Exception e) {}
		      
		      
		      c.close();
		      
		 }catch(Exception e){
		    	
		    	categoria = null;
		    	
		} 
			
			return categoria; 
		
	}
	
	public synchronized Categoria getByNombre(String nombre){
		
		Connection c = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    Categoria categoria = null;
	    
	    try {
		      
		      c= this.conectarBD();

		      stmt = c.createStatement();
		      rs = stmt.executeQuery("SELECT * FROM Categoria WHERE nombre = '"+ nombre +"'");
		      
		      try{
			  	    
				    while ( rs.next() ) {
				          
				         categoria = new Categoria(rs.getString("id"),rs.getString("nombre"));
				    
				    }
				    
			    } catch (Exception e) {}
		      
		      
		      c.close();
		      
		 }catch(Exception e){
		    	
		    	categoria = null;
		    	
		} 
			
			return categoria; 
	    
		
	}
	
	
}
