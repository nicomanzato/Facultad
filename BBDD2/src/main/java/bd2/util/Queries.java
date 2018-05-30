package bd2.util;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import bd2.model.*;

public class Queries {
	
	private static SessionFactory sessions;
	
	public static List<Documento> query_1(){
		
		// Listar los nombres de todos los doc
		
		Transaction tx = null;
		
		System.out.println("Listar los nombres de todos los doc");
		
		Configuration cfg = new Configuration();
        cfg.configure("hibernate/hibernate.cfg.xml");
		sessions = cfg.buildSessionFactory();
        
        Session session = sessions.openSession();
        
        tx = session.beginTransaction();
        tx.setTimeout(5);
        
		Query query = session.createQuery("from Documento");
		
		tx.commit();
		
		return query.list();
		
	}
	
	public static List<Moderador> query_2(){
		
		// Listar los emails de los moderadores que hayan evaluado traducciones al ingles
		
		Transaction tx = null;
		
		System.out.println("Listar los emails de los moderadores que hayan evaluado traducciones al ingles");
		
		Configuration cfg = new Configuration();
        cfg.configure("hibernate/hibernate.cfg.xml");
		sessions = cfg.buildSessionFactory();
        
        Session session = sessions.openSession();
        
        tx = session.beginTransaction();
        tx.setTimeout(5);
        
		Query query = session.createQuery(""
				+ "FROM bd2.model.Moderador as M "
				+ "WHERE :idIdioma in (SELECT E.traduccion.idioma.idIdioma FROM M.evaluaciones AS E)");
		
		query.setParameter("idIdioma", 3);
		
		tx.commit();
		
		
		return query.list();
		
	}
	
	public static List<Usuario> query_3(){ // REVISAR CURSADA REALIZADA !
		
		// Listar los usuarios que hayan iniciado una cursada de Frances de nive 3 como minimo
		
		Transaction tx = null;
		
		System.out.println("Listar los usuarios que hayan iniciado una cursada de Frances de nive 3 como minimo");
		
		Configuration cfg = new Configuration();
        cfg.configure("hibernate/hibernate.cfg.xml");
		sessions = cfg.buildSessionFactory();
        
        Session session = sessions.openSession();
        
        tx = session.beginTransaction();
        tx.setTimeout(5);
        
		Query query = session.createQuery(""
				+ "FROM bd2.model.Usuario as U "
				+ "WHERE U.idUsuario in (SELECT usuario FROM bd2.model.Cursada AS C WHERE U.idUsuario = C.usuario.idUsuario AND C.curso.nivel > 2 AND C.curso.idioma.idIdioma = :idIdiomaFrances)");
		
		query.setParameter("idIdiomaFrances", Long.valueOf(5));
		
		tx.commit();
		
		return query.list();
		
	}
	
	public static List<Usuario> query_4(){
		
		// Listar  moderadores que hayan revisado alguna traduccion entre dos fechas pasadas como argumento
		
		Transaction tx = null;
		
		System.out.println("Listar  moderadores que hayan revisado alguna traduccion entre dos fechas pasadas como argumento");
		
		Configuration cfg = new Configuration();
        cfg.configure("hibernate/hibernate.cfg.xml");
		sessions = cfg.buildSessionFactory();
        
        Session session = sessions.openSession();
        
        tx = session.beginTransaction();
        tx.setTimeout(5);
        
		Query query = session.createQuery(""
				+ "FROM bd2.model.Moderador as M "
				+ "WHERE EXISTS (FROM M.evaluaciones AS E WHERE E.fecha > :fechaInicial AND E.fecha < :fechaFinal)");
		
		query.setParameter("fechaInicial", new Date("07/01/2015") );
		
		query.setParameter("fechaFinal", new Date("12/31/2015"));
		
		tx.commit();
		
		return query.list();
		
	}
	
	public static List<Traduccion> query_5(){
		
		// Listar traducciones completas del Ingles al Frances
		
		Transaction tx = null;
		
		System.out.println("Listar traducciones completas del Ingles al Frances");
		
		Configuration cfg = new Configuration();
        cfg.configure("hibernate/hibernate.cfg.xml");
		sessions = cfg.buildSessionFactory();
        
        Session session = sessions.openSession();
        
        tx = session.beginTransaction();
        tx.setTimeout(5);
        
		Query query = session.createQuery(""
				+ "FROM bd2.model.Traduccion as T "
				+ "WHERE T.completa = 1 AND "
				+ "T.idioma.idIdioma = :idIdiomaFrances AND "
				+ "T.parrafo.documento.idioma.idIdioma = :idIdiomaIngles");
		
		query.setParameter("idIdiomaIngles", Long.valueOf(3) );
		
		query.setParameter("idIdiomaFrances", Long.valueOf(5) );
		
		tx.commit();
		
		return query.list();
		
	}
	
	public static List<Usuario> query_6(){
		
		// Listar traducciones completas del Ingles al Frances
		
		Transaction tx = null;
		
		System.out.println("Listar traducciones completas del Ingles al Frances");
		
		Configuration cfg = new Configuration();
        cfg.configure("hibernate/hibernate.cfg.xml");
		sessions = cfg.buildSessionFactory();
        
        Session session = sessions.openSession();
        
        tx = session.beginTransaction();
        tx.setTimeout(5);
        
		Query query = session.createQuery(""
				+ "FROM bd2.model.Usuario as U "
				+ "WHERE EXISTS (FROM U.cursadasRealizadas  AS C WHERE "
				+ "(SELECT count(*) FROM C.curso.lecciones ) = (SELECT DISTINCT count(P.leccion) FROM C.pruebas AS P WHERE P.puntaje > 59) )");
				
				//+ "C.curso.idCurso in (SELECT Curso.idCurso FROM C.curso AS Curso WHERE Curso.leccion.idLeccion in( SELECT P.leccion.idLeccion FROM C.pruebas AS P WHERE P.puntaje > 59)))");
		
		tx.commit();
		
		return query.list();
		
	}
	
	public static List<Idioma> query_7(){
		
		// Obtener el idioma que define la palabra enviada como parametro en su diccionario
		
		Transaction tx = null;
		
		System.out.println("Obtener el idioma que define la palabra enviada como parametro en su diccionario");
		
		Configuration cfg = new Configuration();
        cfg.configure("hibernate/hibernate.cfg.xml");
		sessions = cfg.buildSessionFactory();
        
        Session session = sessions.openSession();
        
        tx = session.beginTransaction();
        tx.setTimeout(5);
        
		Query query = session.createQuery(""
				+ "FROM bd2.model.Idioma as I "
				+ "WHERE EXISTS (FROM I.diccionario AS D join D.definiciones AS Def WHERE :palabra = index(Def))");
		
		query.setParameter("palabra", "Leuchtturm" );
		
		tx.commit();
		
		return query.list();
		
	}
	
	public static List<Documento> query_8(){
		
		// Obtener los nombres de los documentos que no tengan ningun parrafo traducido (en ningun idioma)
		
		Transaction tx = null;
		
		System.out.println("Obtener los nombres de los documentos que no tengan ningun parrafo traducido (en ningun idioma)");
		
		Configuration cfg = new Configuration();
        cfg.configure("hibernate/hibernate.cfg.xml");
		sessions = cfg.buildSessionFactory();
        
        Session session = sessions.openSession();
        
        tx = session.beginTransaction();
        tx.setTimeout(5);
        
		Query query = session.createQuery(""
				+ "FROM bd2.model.Documento as Doc "
				+ "WHERE NOT EXISTS (FROM Doc.parrafos AS P WHERE EXISTS (FROM bd2.model.Traduccion AS T WHERE T.parrafo.idParrafo = P.idParrafo))");
		
		tx.commit();
		
		return query.list();
		
	}
	
	public static List<Documento> query_9(){
		
		// Obtener los nombres de los documentos que no tengan ningun parrafo traducido (en ningun idioma)
		
		Transaction tx = null;
		
		System.out.println("Obtener los nombres de los documentos que no tengan ningun parrafo traducido (en ningun idioma)");
		
		Configuration cfg = new Configuration();
        cfg.configure("hibernate/hibernate.cfg.xml");
		sessions = cfg.buildSessionFactory();
        
        Session session = sessions.openSession();
        
        tx = session.beginTransaction();
        tx.setTimeout(5);
        
		Query query = session.createQuery(""
				+ "FROM bd2.model.Documento as Doc "
				+ "WHERE EXISTS (FROM Doc.parrafos AS P WHERE NOT EXISTS (FROM bd2.model.Traduccion AS T WHERE T.parrafo.idParrafo = P.idParrafo AND T.idioma.idIdioma = :idIdioma))");
		
		query.setParameter("idIdioma", Long.valueOf(2) );
		
		tx.commit();
		
		return query.list();
		
	}

}
