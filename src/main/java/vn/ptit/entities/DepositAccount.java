package vn.ptit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "DepositAccount")
@PrimaryKeyJoinColumn(name = "BankAccountId")
public class DepositAccount extends BankAccount{
	@Column(name = "InterestRate", nullable = false)
	private double interestRate;
	@Column(name = "MinimumBalance", nullable = false)
	private double minimumBalance;

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}
}
