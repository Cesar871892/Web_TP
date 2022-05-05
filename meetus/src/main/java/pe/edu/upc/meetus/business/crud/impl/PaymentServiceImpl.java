package pe.edu.upc.meetus.business.crud.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.meetus.business.crud.PaymentService;
import pe.edu.upc.meetus.model.entity.Payment;
import pe.edu.upc.meetus.model.repository.JpaRepository;
import pe.edu.upc.meetus.model.repository.PaymentRepository;

@Named
@ApplicationScoped
public class PaymentServiceImpl implements PaymentService {

	@Inject
	private PaymentRepository paymentRepository;
	
	@Override
	public JpaRepository<Payment, Integer> getJpaRepository() {
		
		return paymentRepository;
	}

	@Override
	public List<Payment> findByName(String name) throws Exception {
		
		return paymentRepository.findByName(name);
	}

}
