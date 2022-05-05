package pe.edu.upc.meetus.model.repository.Impl;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.edu.upc.meetus.model.entity.Payment;
import pe.edu.upc.meetus.model.repository.PaymentRepository;

@Named
@ApplicationScoped
public class PaymentRepositoryImpl implements PaymentRepository {

	@PersistenceContext(unitName = "meetusPU")
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public Optional<Payment> findById(Integer id) throws Exception {
		return findById(id, Payment.class);
	}
	
	@Override
	public List<Payment> findAll() throws Exception {
		String jpql = "SELECT payment FROM Payment payment";
		return findByQuery(Payment.class, jpql);
	}
	
	@Override
	public List<Payment> findByName(String name) throws Exception {
		String jpql = "SELECT p FROM Payment p WHERE p.name LIKE '%" + name + "%'" ;
		System.out.println(jpql);
		return findByQuery(Payment.class, jpql);
	}

}
