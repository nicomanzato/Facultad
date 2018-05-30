package dao;

import java.sql.*;
import java.util.Observable;
import java.util.ArrayList;
import java.util.List;

import entidades.Contenido;
import entidades.Contexto;
import entidades.Etiqueta;
import entidades.Paciente;

public class PacienteDao extends Dao {
    
    public synchronized void insertarPaciente(String nombre){
    	
    	this.ejecutarSQL("INSERT INTO Paciente(nombre) VALUES ('"+ nombre +"')");
		
		setChanged();
	    notifyObservers();
    	
    }
    
    public synchronized Paciente getById(String id){
		
		Connection c = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    Paciente paciente = null;
	    
	    try {
		      
		      c= this.conectarBD();

		      stmt = c.createStatement();
		      rs = stmt.executeQuery("SELECT * FROM Paciente WHERE id = '"+ id +"'");
		      
		      try{
			  	    
				    while ( rs.next() ) {
				          
				         paciente = new Paciente(rs.getString("id"),rs.getString("nombre"));
				    
				    }
				    
			    } catch (Exception e) {}
		      
		      
		      c.close();
		      
		 }catch(Exception e){
		    	
		    	paciente = null;
		    	
		} 
			
			return paciente; 
		
	}
    
    public synchronized List<Paciente> getPacientes(){
		
		List<Paciente> pacientes = new ArrayList<Paciente>();
		
		pacientes.add(new Paciente("Seleccione Paciente"));
		
		ResultSet rs = this.selectSQL("SELECT * FROM Paciente ORDER BY Nombre" );
	    
	    try{
	    
		    while ( rs.next() ) {
		          
		         pacientes.add(new Paciente(rs.getString("id"), rs.getString("nombre")));
		    
		    }
		
	    } catch (Exception e) {}
	    
	    return pacientes;
	    
	}
    
    public synchronized boolean existe(String paciente){
		
		return super.existe("SELECT count(id) as cantidad FROM Paciente WHERE Nombre = '"+ paciente +"'");
			
	}
    
    public synchronized Paciente getByNombre(String nombre){
		
		Connection c = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    Paciente paciente = null;
	    
	    try {
		      
		      c= this.conectarBD();

		      stmt = c.createStatement();
		      rs = stmt.executeQuery("SELECT * FROM Paciente WHERE nombre = '"+ nombre +"'");
		      
		      try{
			  	    
				    while ( rs.next() ) {
				          
				         paciente = new Paciente(rs.getString("id"),rs.getString("nombre"));
				    
				    }
				
			    } catch (Exception e) {}
		      
		      
		      c.close();
		      
		 }catch(Exception e){
		    	
		    	paciente = null;
		    	
		} finally{ return paciente; }
		
	}
	
}
