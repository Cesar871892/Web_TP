package pe.edu.upc.meetus.business.crud;

import java.util.List;

import pe.edu.upc.meetus.model.entity.Payment;

public interface PaymentService extends CrudService<Payment, Integer>{
	List<Payment> findByName(String name) throws Exception;
}
