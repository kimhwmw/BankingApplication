package net.mv.bankapp.domain;


public class AppUser {
	private long id;
	private String username;
	private String password;
	
	public AppUser(){}
	
	public AppUser(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public AppUser(long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "AppUser [id=" + id + ", username=" + username + ", password="
				+ password + "]";
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}