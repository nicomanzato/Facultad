package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import entidades.*;

public class NotificacionDao extends Dao {

	public synchronized void insertarNotificacion(Notificacion notificacion) throws ParseException {
		this.ejecutarSQL("INSERT INTO Notificacion(idCategoria, idContenido, idContexto, idPaciente,fecha) VALUES ('"
				+ notificacion.getCategoria().getId()
				+ "','"
				+ notificacion.getContenido().getId()
				+ "','"
				+ notificacion.getContexto().getId()
				+ "','"
				+ notificacion.getPaciente().getId()
				+ "','"
				+ notificacion.getFecha() + "')");
		
		this.setChanged();
		this.notifyObservers();
	}

	public synchronized List<Notificacion> filtrar(Contenido contenido, Contexto contexto,
			Categoria categoria, Paciente paciente, String fechaInicio,
			String fechaFin, Etiqueta etiqueta) throws ParseException {
		String sql = "SELECT * FROM Notificacion WHERE 1 = 1 ";
		if (contenido != null) {
			sql = sql + " AND idContenido = '" + contenido.getId() + "' ";
		}
		if (contexto != null) {
			sql = sql + " AND idContexto = '" + contexto.getId() + "' ";
		}
		if (categoria != null) {
			sql = sql + " AND idCategoria = '" + categoria.getId() + "' ";
		}
		if (paciente != null) {
			sql = sql + " AND idPaciente = '" + paciente.getId() + "' ";
		}
		if (etiqueta != null) {
			sql = sql
					+ " AND EXISTS(SELECT * FROM Etiqueta,Notificacion_Etiqueta WHERE Notificacion.id = Notificacion_Etiqueta.idNotificacion AND Etiqueta.id = Notificacion_Etiqueta.idEtiqueta AND Etiqueta.nombre = '"
					+ etiqueta.getNombre() + "')";
		}
		sql = sql + "ORDER BY fecha DESC";

		try{
			if ((fechaInicio != "")&&(fechaFin != "")) {	
				List<Notificacion> filtradas = new ArrayList<Notificacion>();
				for (Notificacion notificacion : new NotificacionDao().getNotificacionesSQL(sql)) {
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date fNotificacion = formatter.parse(notificacion.getFecha());
					Date fInicio = formatter.parse(fechaInicio);
					Date fFin = formatter.parse(fechaFin);
					if((fNotificacion.after(fInicio))&&(fNotificacion.before(fFin))){
						filtradas.add(notificacion);
			        }
				}
				return filtradas;
			} else {
				return this.getNotificacionesSQL(sql);
			}
		
		}catch(Exception e){}
		
		return this.getNotificacionesSQL(sql);
	}

	public synchronized List<Notificacion> getUpdatedNotificaciones(DefaultTableModel model){
		
		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		
		for(int i=0; i < model.getRowCount(); i++){
			
			notificaciones.add(DaoFactory.getNotificacionDao().getById(model.getValueAt(i, 6).toString()));
			
		}
	
		for (Notificacion notificacion : notificaciones) {

			notificacion.setEtiquetas(DaoFactory.getEtiquetaDao()
					.getByIdNotificacion(notificacion.getId()));

		}
		
		return notificaciones;
		
	}
	
	private synchronized List<Notificacion> getNotificacionesSQL(String sql) {

		List<Notificacion> notificaciones = new ArrayList<Notificacion>();

		ResultSet rs = this.selectSQL(sql);

		try {

			while (rs.next()) {

				notificaciones.add(new Notificacion(
						rs.getString("idContenido"),
						rs.getString("idContexto"),
						rs.getString("idCategoria"),
						rs.getString("idPaciente"), rs.getString("fecha"), rs
								.getString("id")));

			}

			for (Notificacion notificacion : notificaciones) {

				notificacion.setEtiquetas(DaoFactory.getEtiquetaDao()
						.getByIdNotificacion(notificacion.getId()));

			}

		} catch (Exception e) {
		}

		return notificaciones;

	}

	public synchronized void addToModel(List<Notificacion> notificaciones, TableModel model) {
		for (Notificacion notificacion : notificaciones) {
			((DefaultTableModel) model).addRow(new Object[] { notificacion.getFecha(),
					notificacion.getContenido(), notificacion.getContexto(),
					notificacion.getCategoria(), notificacion.getPaciente(),
					notificacion.getEtiquetasString(), notificacion.getId() });
		}
	}

	public synchronized List<Notificacion> getNotificaciones() {
		return getNotificacionesSQL("SELECT * FROM Notificacion ORDER BY fecha DESC");
	}

	public synchronized Notificacion getById(String id) {
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		Notificacion notificacion = null;
		try {
			c = this.conectarBD();
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Notificacion WHERE id = '"
					+ id + "'");
			try {
				while (rs.next()) {
					notificacion = new Notificacion(
							rs.getString("idContenido"),
							rs.getString("idContexto"),
							rs.getString("idCategoria"),
							rs.getString("idPaciente"),
							rs.getString("fecha"),
							rs.getString("id"));
				}
			} catch (Exception e) {
			}
			c.close();
		} catch (Exception e) {
			notificacion = null;
		}
		return notificacion;
	}
	/*
	public synchronized void insertarNotificacionesFromJson() throws JSONException, ParseException, IOException{
		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		BufferedReader jsonFile = new BufferedReader(new FileReader("notificaciones.json"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = jsonFile.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = jsonFile.readLine();
		    }
		    String everything = sb.toString();
			JSONArray array = new JSONArray(everything);
			for (int i = 0; i < array.length(); i++) {
			    JSONObject jsonobject = array.getJSONObject(i);
				
				Contexto contexto = new Gson().fromJson(String.valueOf(jsonobject.get("contexto")), Contexto.class);
				Contenido contenido = new Gson().fromJson(String.valueOf(jsonobject.get("contenido")), Contenido.class);
				Categoria categoria = new Gson().fromJson(String.valueOf(jsonobject.get("categoria")), Categoria.class);
				Paciente paciente = new Gson().fromJson(String.valueOf(jsonobject.get("paciente")), Paciente.class);
				
				Notificacion notificacion = new Notificacion(contenido, contexto, categoria, paciente);
				
				notificaciones.add(notificacion);
			}
			for ( Notificacion n : notificaciones ){
				 this.insertarNotificacion(n);
			}
		} finally {
			jsonFile.close();
		}
		
	}
	*/
	public synchronized void insertarNotificacionesFromJson(String notificacionesJson) throws JSONException, ParseException, IOException{
		
		
		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		
		try {
		    String everything = notificacionesJson;
		    System.out.println(everything);
			JSONArray array = new JSONArray(everything);
			
			for (int i = 0; i < array.length(); i++) {
				
			    JSONObject jsonobject = array.getJSONObject(i);
			    
			    Contexto contexto = new Contexto((String)jsonobject.get("contexto"));
			    Contenido contenido = new Contenido((String)jsonobject.get("contenido"));
			    Categoria categoria = new Categoria((String) jsonobject.get("categoria"));
			    Paciente paciente = new Paciente((String) jsonobject.get("paciente"));

				//Contexto contexto = new Gson().fromJson(String.valueOf(jsonobject.get("contexto")), Contexto.class);
				//Contenido contenido = new Gson().fromJson(String.valueOf(jsonobject.get("contenido")), Contenido.class);
				//Categoria categoria = new Gson().fromJson(String.valueOf(jsonobject.get("categoria")), Categoria.class);
				//Paciente paciente = new Gson().fromJson(String.valueOf(jsonobject.get("paciente")), Paciente.class);
				
				
				Notificacion notificacion = new Notificacion(contenido, contexto, categoria, paciente);
				
				notificaciones.add(notificacion);
				
			}
			for ( Notificacion n : notificaciones ){
				 this.insertarNotificacion(n);
			}
		} finally {
			
		}
		
	}
}
