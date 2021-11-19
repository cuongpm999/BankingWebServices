package vn.ptit.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Type")
	private String type;
	
	@Column(name = "DateCreate")
	private Date dateCreate;
	
	@Column(name = "Money", nullable = false)
	private double money;
	
	@Column(name = "AfterBalanceDeposit")
	private double afterBalanceDeposit;
	
	@Column(name = "AfterBalanceCredit")
	private double afterBalanceCredit;
	
	@Column(name = "Note")
	private String note;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CustomerId")
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EmployeeId")
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DepositAccountId")
	private DepositAccount depositAccount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CreditAccountId")
	private CreditAccount creditAccount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getAfterBalanceDeposit() {
		return afterBalanceDeposit;
	}

	public void setAfterBalanceDeposit(double afterBalanceDeposit) {
		this.afterBalanceDeposit = afterBalanceDeposit;
	}

	public double getAfterBalanceCredit() {
		return afterBalanceCredit;
	}

	public void setAfterBalanceCredit(double afterBalanceCredit) {
		this.afterBalanceCredit = afterBalanceCredit;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public DepositAccount getDepositAccount() {
		return depositAccount;
	}

	public void setDepositAccount(DepositAccount depositAccount) {
		this.depositAccount = depositAccount;
	}

	public CreditAccount getCreditAccount() {
		return creditAccount;
	}

	public void setCreditAccount(CreditAccount creditAccount) {
		this.creditAccount = creditAccount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
