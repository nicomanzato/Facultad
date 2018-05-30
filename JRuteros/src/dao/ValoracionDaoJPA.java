package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import interfaces.ImagenDaoInterface;
import interfaces.ValoracionDaoInterface;
import model.Actividad;
import model.Imagen;
import model.Valoracion;

public class ValoracionDaoJPA implements ValoracionDaoInterface {
	
	private EntityManagerFactory emf = DaoFactory.getEntityManagerFactory();

	@Override
	public void save(Valoracion valoracion) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
				
		em.persist(valoracion);
								
		etx.commit();
		em.close();
		
	}

	@Override
	public void update(Valoracion valoracion) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
				
		em.merge(valoracion);
								
		etx.commit();
		em.close();		
	}

	@Override
	public void delete(Valoracion valoracion) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
				
		Query query = em.createQuery("from model.Valoracion a WHERE id = :id");
		
		query.setParameter("id", valoracion.getId());
		
		em.remove(query.getSingleResult());
		
		etx.commit();
		em.close();
		
	}

	@Override
	public List<Valoracion> getValoraciones() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		List<Valoracion> valoraciones=(List<Valoracion>)(em.createQuery("from model.Valoracion i")).getResultList();
		etx.commit();
		em.close();
		return valoraciones;
	}

}
