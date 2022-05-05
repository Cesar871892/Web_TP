package pe.edu.upc.meetus.model.repository.Impl;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.edu.upc.meetus.model.entity.Organizer;
import pe.edu.upc.meetus.model.repository.OrganizerRepository;

@Named
@ApplicationScoped
public class OrganizerRepositoryImpl implements OrganizerRepository {

	@PersistenceContext(unitName = "meetusPU")
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Optional<Organizer> findById(Integer id) throws Exception {
		return findById(id, Organizer.class);
	}

	@Override
	public List<Organizer> findAll() throws Exception {

		String jpql = "SELECT organizer FROM Organizer organizer";
		return findByQuery(Organizer.class, jpql);
	}

	@Override
	public List<Organizer> findByName(String name) throws Exception {
		String jpql = "SELECT o FROM Organizer o WHERE o.name LIKE '%" + name + "%'";
		System.out.println(jpql);
		return findByQuery(Organizer.class, jpql);
	}

}
