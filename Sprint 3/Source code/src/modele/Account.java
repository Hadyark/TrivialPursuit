package modele;

import java.util.ArrayList;

public class Account {

	private String id,password, mail;
	private boolean admin;
	private static Account instanceAccount = null;
	
	public Account(String id, String password, String mail, boolean admin) {
		this.id = id;
		this.password = password;
		this.mail = mail;
		this.admin = admin;
	}
	public Account() {
		this.id = id;
		this.password = password;
		this.admin = admin;
	}
	public static Account getInstanceAccount() {
		if(instanceAccount == null) {
			instanceAccount = new Account();
		}
		return instanceAccount;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public static void setInstanceAccount(Account instanceAccount) {
		Account.instanceAccount = instanceAccount;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", password=" + password + ", mail=" + mail + ", admin=" + admin + "]";
	}
	
	public boolean equalsId (Object o){
		if(o instanceof Account){
			return ((Account)o).getId().equals(getId());
		}
		return false;
	}
	

	
}
