package modele;

import java.util.ArrayList;

public class Account {

	private String id,password, mail;
	private boolean admin;
	private static Account instanceAccount = null;
	
	/**
	 * It allows to create an account
	 * @param id
	 * id of the account
	 * @param password
	 * password of the account
	 * @param mail
	 * mail of the account
	 * @param admin
	 * it allows to be an admin
	 */
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
	/**
	 * @return an instance of the deck
	 */
	public static Account getInstanceAccount() {
		if(instanceAccount == null) {
			instanceAccount = new Account();
		}
		return instanceAccount;
	}	
	/**
	 * @return the id of the account
	 */
	public String getId() {
		return id;
	}
	/**
	 * Update the name of the account
	 * @param id
	 * The new id of the account
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the password of the account
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password
	 * The new password of the account
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the mail of the account
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail
	 * the new mail of the account
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return if the account is admin
	 */
	public boolean isAdmin() {
		return admin;
	}
	/**
	 * @param admin
	 * the new state of the account
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	/**
	 * @param instanceAccount
	 * the new instance of the account
	 */
	public static void setInstanceAccount(Account instanceAccount) {
		Account.instanceAccount = instanceAccount;
	}
	/**
	 * retrun a string of the account
	 */
	@Override
	public String toString() {
		return "Account [id=" + id + ", password=" + password + ", mail=" + mail + ", admin=" + admin + "]";
	}
	/**
	 * 
	 * @param o
	 * The object to be compared
	 * @return the resultat
	 */
	public boolean equalsId (Object o){
		if(o instanceof Account){
			return ((Account)o).getId().equals(getId());
		}
		return false;
	}
	

	
}
