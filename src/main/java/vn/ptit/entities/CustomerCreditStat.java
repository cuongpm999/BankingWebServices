package vn.ptit.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

public class CustomerCreditStat extends Customer{
	private double credit;
	
	public CustomerCreditStat() {

	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

}
