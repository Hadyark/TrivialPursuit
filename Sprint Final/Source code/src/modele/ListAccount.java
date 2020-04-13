package modele;

import java.util.ArrayList;

public class ListAccount {

	private ArrayList<Account> listAccount;
	
	/**
	 * It allows to create a listAccount
	 */
	public ListAccount() {
		this.listAccount = listAccount;
	}
	/**
	 * @return a string of the list of account
	 */
	@Override
	public String toString() {
		return "ListAccount [accounts=" + listAccount + "]";
	}
	/**
	 * @return the list of accounts
	 */
	public ArrayList<Account> getListAccount() {
		return listAccount;
	}
	/**
	 * @param accounts
	 * the new list of accounts
	 */
	public void setListAccount(ArrayList<Account> accounts) {
		this.listAccount = accounts;
	} 
	/**
	 * @param a
	 * The account to be added
	 * @return The resultat
	 */
	public boolean addAccount(Account a) {
		for (Account account : listAccount) {
			if(a.equalsId(account)) {	
				return false;
			}
		}
		this.listAccount.add(a);
		return true;
	}
}
