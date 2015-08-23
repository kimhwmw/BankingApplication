package net.mv.bankapp.domain;


public class Account {
	private long id;
	private String name;
	private double balance;
	private long user_id;
	
	public Account(){}
	
	public Account(String name, double balance, long user_id){
		this.name = name;
		this.balance = balance;
		this.user_id = user_id;
	}

	public Account(long id, double balance) {
		this.id = id;
		this.balance = balance;
	}

	public Account(long id, String name, double balance, long user_id) {
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.user_id = user_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", balance=" + balance
				+ ", user_id=" + user_id + "]";
	}
	
	
}