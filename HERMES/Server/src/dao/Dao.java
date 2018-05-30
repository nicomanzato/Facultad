package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Observable;

import entidades.Contenido;

public class Dao extends Observable{

	protected java.sql.Connection conectarBD(){
    	
    	Connection c = null;
    	
    	try {
    	      Class.forName("org.sqlite.JDBC");
    	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
    	      
    	} catch ( Exception e ) {
    	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	      System.exit(0);
    	}
    	
    	return c;
        
    }
	
	protected void ejecutarSQL(String sql){
		
		Connection c = null;
	    Statement stmt = null;
	    
	    try {
		      
		      c= this.conectarBD();

		      stmt = c.createStatement();
		      stmt.execute(sql);
		      
		      c.close();
		      
		    }catch(Exception e){
		    	
		    	
		    	
		    }
		
	}
	
	protected ResultSet selectSQL(String sql){
		
		Connection c = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    try {
		      
		      c= this.conectarBD();

		      stmt = c.createStatement();
		      rs = stmt.executeQuery(sql);
		      
		      rs.getString(0);
		      
		      c.close();
		      
		 }catch(Exception e){
		    	
		    	
		    	
		}
	    
	    return rs;
		
	}
	
	protected boolean existe(String sql){
		
		Connection c = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    int cant = 0;
	    
	    try {
		      
		      c= this.conectarBD();
		      stmt = c.createStatement();
		      rs = stmt.executeQuery(sql);
		      
		      try{
		  	    
				    while ( rs.next() ) {
				          
				         cant = rs.getInt("cantidad");
				    
				    }
				
			    } catch (Exception e) {}
		      
		      
		      
		      return cant > 0;
		      
		      
		      
		 }catch(Exception e){
		    	
		    	
		    	
		}
		
		return false;
		
	}
	
}
