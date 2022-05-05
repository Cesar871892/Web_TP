package pe.edu.upc.meetus.model.repository;

import java.util.List;

import pe.edu.upc.meetus.model.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	List<Payment> findByName(String name) throws Exception;
}
