package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import interfaces.ActividadDaoInterface;
import model.Actividad;

public class ActividadDaoJPA implements ActividadDaoInterface {

	private EntityManagerFactory emf = DaoFactory.getEntityManagerFactory();
	
	@Override
	public void save(Actividad actividad) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
				
		em.persist(actividad);
								
		etx.commit();
		em.close();
		
	}

	@Override
	public void update(Actividad actividad) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
				
		em.merge(actividad);
								
		etx.commit();
		em.close();
		
	}

	@Override
	public List<Actividad> getActividades() {

		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		List<Actividad> actividades=(List<Actividad>)(em.createQuery("from model.Actividad a")).getResultList();
		etx.commit();
		em.close();
		return actividades;
	}

	@Override
	public void delete(Actividad actividad) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		Query query = em.createQuery("from model.Actividad a WHERE id = :id");
		
		query.setParameter("id", actividad.getId());
		
		em.remove(query.getSingleResult());
								
		etx.commit();
		em.close();
		
	}

}
