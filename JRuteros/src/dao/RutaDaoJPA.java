package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import interfaces.RutaDaoInterface;
import model.Imagen;
import model.Ruta;

public class RutaDaoJPA implements RutaDaoInterface {
	
	private EntityManagerFactory emf = DaoFactory.getEntityManagerFactory();

	@Override
	public void save(Ruta ruta) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
				
		em.persist(ruta);
								
		etx.commit();
		em.close();
		
	}

	@Override
	public void update(Ruta ruta) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.merge(ruta);			
		etx.commit();
		em.close();		
	}

	@Override
	public List<Ruta> getRutas() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		List<Ruta> rutas=(List<Ruta>)(em.createQuery("from model.Ruta r")).getResultList();
		etx.commit();
		em.close();
		return rutas;
	}

	@Override
	public void delete(Ruta ruta) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
				
		Query query = em.createQuery("from model.Ruta a WHERE id = :id");
		
		query.setParameter("id", ruta.getId());
		
		em.remove(query.getSingleResult());
								
		etx.commit();
		em.close();
		
	}

}
