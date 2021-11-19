package vn.ptit.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@Column(name = "FullName", nullable = false, length = 255)
	private String fullName;
	
	@Column(name = "IdCard", nullable = false, length = 255)
	private String idCard;
	
	@Column(name = "DateOfBirth")
	private Date dateOfBirth;
	
	@Column(name = "Address", nullable = false, length = 255)
	private String address;
	
	public Customer() {
		super();
	}

	public Customer(String fullName, String idCard, Date dateOfBirth, String address) {
		super();
		this.fullName = fullName;
		this.idCard = idCard;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
