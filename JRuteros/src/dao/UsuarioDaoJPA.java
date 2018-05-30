package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import interfaces.RutaDaoInterface;
import interfaces.UsuarioDaoInterface;
import model.Imagen;
import model.Ruta;
import model.Usuario;

public class UsuarioDaoJPA implements UsuarioDaoInterface {
	
	private EntityManagerFactory emf = DaoFactory.getEntityManagerFactory();

	@Override
	public void save(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
				
		em.persist(usuario);
								
		etx.commit();
		em.close();
		
	}

	@Override
	public void update(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.merge(usuario);			
		etx.commit();
		em.close();		
	}

	@Override
	public List<Usuario> getUsuarios() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		List<Usuario> rutas=(List<Usuario>)(em.createQuery("from model.Usuario r")).getResultList();
		etx.commit();
		em.close();
		return rutas;
	}
	
	public Usuario getUsuario(String username){
		
		try{
			
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		Query q = em.createQuery("from model.Usuario u WHERE u.nombreUsuario = :username");
		
		q.setParameter("username", username);
		
		Usuario user = (Usuario) q.getSingleResult();
		
		etx.commit();
		em.close();
		
		return user;
		
		}
		catch(Exception e){ return null; }
		
		
	}

	@Override
	public void delete(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
				
		Query query = em.createQuery("from model.Usuario a WHERE id = :id");
		
		query.setParameter("id", usuario.getId());
		
		em.remove(query.getSingleResult());
								
		etx.commit();
		em.close();
		
	}
	
	public boolean validateLogin(String username, String password){
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		Query q = em.createQuery("from model.Usuario WHERE nombreUsuario = :nombreUsuario AND clave = :clave");
	
		q.setParameter("nombreUsuario", username);
		q.setParameter("clave", password);
		
		int cant = q.getResultList().size();
		
		etx.commit();
		em.close();
		
		return cant > 0;
		
	}

}
