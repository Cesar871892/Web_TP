package pe.edu.upc.meetus.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import pe.edu.upc.meetus.business.crud.PaymentService;
import pe.edu.upc.meetus.model.entity.Payment;

@Named("paymentView")
@ViewScoped
public class PaymentView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Payment> payments;
	private Payment paymentSelected;
	private List<Payment> paymentsSelected;
	private Payment paymentSearch;

	@Inject
	private PaymentService paymentService;

	@PostConstruct
	public void init() {
		paymentsSelected = new ArrayList<>();
		paymentSearch = new Payment();
		getAllPayment();
	}
	
	public boolean hasPaymentsSelected() {
		if (paymentsSelected.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean hasPaymentSelected() {
		if (paymentsSelected.size() == 1) {
			return true;
		}
		return false;
	}
	
	public void createNew() {
		paymentSelected = new Payment();		
	}
	public void editPaymentSelected() {
		paymentSelected = paymentsSelected.get(0);
	}
	
	public void savePayment() {
		try {
			if (paymentSelected.getId() == null) {
				paymentService.create(paymentSelected);
				payments.add(paymentSelected);
			} 
			else {
				paymentService.update(paymentSelected);
			}			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrimeFaces.current().executeScript("PF('paymentDialog').hide()");
        PrimeFaces.current().ajax().update("paymentDataTable");
	}
	
	public void deletePayment() {
		try {
			this.payments.remove(paymentSelected);
			paymentService.deleteById(this.paymentSelected.getId());
			this.paymentSelected = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Remove", "Item Removed"));
		
	}
	public void searchPayment() {
		try {
			payments = paymentService.findByName(paymentSearch.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	public void getAllPayment() {
		try {
			payments = paymentService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public List<Payment> getPayments() {
		return payments;
	}
	
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public Payment getPaymentSelected() {
		return paymentSelected;
	}

	public void setPaymentSelected(Payment paymentSelected) {
		this.paymentSelected = paymentSelected;
	}
	
	public List<Payment> getPaymentsSelected() {
		return paymentsSelected;
	}

	public void setPaymentsSelected(List<Payment> paymentsSelected) {
		this.paymentsSelected = paymentsSelected;
	}

	public Payment getPaymentSearch() {
		return paymentSearch;
	}

	public void setPaymentSearch(Payment paymentSearch) {
		this.paymentSearch = paymentSearch;
	}
}
