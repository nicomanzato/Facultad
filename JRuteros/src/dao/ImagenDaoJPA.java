package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import interfaces.ImagenDaoInterface;
import model.Actividad;
import model.Imagen;

public class ImagenDaoJPA implements ImagenDaoInterface {
	
	private EntityManagerFactory emf = DaoFactory.getEntityManagerFactory();

	@Override
	public void save(Imagen imagen) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
				
		em.persist(imagen);
								
		etx.commit();
		em.close();
		
	}

	@Override
	public void update(Imagen imagen) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
				
		em.merge(imagen);
								
		etx.commit();
		em.close();		
	}

	@Override
	public List<Imagen> getImagenes() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		List<Imagen> imagenes=(List<Imagen>)(em.createQuery("from model.Imagen i")).getResultList();
		etx.commit();
		em.close();
		return imagenes;
	}

	@Override
	public void delete(Imagen imagen) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
				
		Query query = em.createQuery("from model.Imagen a WHERE id = :id");
		
		query.setParameter("id", imagen.getId());
		
		em.remove(query.getSingleResult());
		
		etx.commit();
		em.close();
		
	}

}
