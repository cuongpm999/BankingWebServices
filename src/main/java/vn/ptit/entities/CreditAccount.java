package vn.ptit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "CreditAccount")
@PrimaryKeyJoinColumn(name = "BankAccountId")
public class CreditAccount extends BankAccount {
	@Column(name = "LimitBalance", nullable = false)
	private double limitBalance;

	public double getLimitBalance() {
		return limitBalance;
	}

	public void setLimitBalance(double limitBalance) {
		this.limitBalance = limitBalance;
	}
}
