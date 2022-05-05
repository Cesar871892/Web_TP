package pe.edu.upc.meetus.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Integer id;
	
	@Column(name="payment_number", length = 16, nullable = false)
	private String number;
	
	@Column(name="payment_date", length = 10, nullable = false)
	private String date;
	
	@Column(name="payment_code", length = 3, nullable = false)
	private String code;
	
	@Column(name="payment_name", length = 30, nullable = false)
	private String name;
	
	@Column(name="payment_email", length = 30, nullable = false)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "organizer_id")
	private Organizer organizer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}
	
	
}
