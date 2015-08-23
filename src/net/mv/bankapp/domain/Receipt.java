package net.mv.bankapp.domain;

import java.sql.Date;

public class Receipt {
	private long R_Id;
	private long Acc_Id;
	private String name;
	private double New_Balance;
	private double Old_Balance;
	private Date opDate;
	private String operation;
	
	public Receipt(){}

	public Receipt(long acc_Id, String name, double new_Balance,
			double old_Balance, Date opDate, String operation) {
		Acc_Id = acc_Id;
		this.name = name;
		New_Balance = new_Balance;
		Old_Balance = old_Balance;
		this.opDate = opDate;
		this.operation = operation;
	}
	
	public Receipt(long r_Id, long acc_Id, String name, double new_Balance,
			double old_Balance, Date opDate, String operation) {
		R_Id = r_Id;
		Acc_Id = acc_Id;
		this.name = name;
		New_Balance = new_Balance;
		Old_Balance = old_Balance;
		this.opDate = opDate;
		this.operation = operation;
	}

	public long getR_Id() {
		return R_Id;
	}

	public void setR_Id(long r_Id) {
		R_Id = r_Id;
	}

	public long getAcc_Id() {
		return Acc_Id;
	}

	public void setAcc_Id(long acc_Id) {
		Acc_Id = acc_Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getNew_Balance() {
		return New_Balance;
	}

	public void setNew_Balance(double new_Balance) {
		New_Balance = new_Balance;
	}

	public double getOld_Balance() {
		return Old_Balance;
	}

	public void setOld_Balance(double old_Balance) {
		Old_Balance = old_Balance;
	}

	public Date getOpDate() {
		return opDate;
	}

	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "Receipt [R_Id=" + R_Id + ", Acc_Id=" + Acc_Id + ", name="
				+ name + ", New_Balance=" + New_Balance + ", Old_Balance="
				+ Old_Balance + ", opDate=" + opDate + ", operation="
				+ operation + "]";
	}
	
	
}
