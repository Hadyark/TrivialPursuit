package modele;

import java.util.ArrayList;

public class ListAccount {

	private ArrayList<Account> listAccount;

	public ListAccount() {
		this.listAccount = listAccount;
	}

	@Override
	public String toString() {
		return "ListAccount [accounts=" + listAccount + "]";
	}

	public ArrayList<Account> getListAccount() {
		return listAccount;
	}

	public void setListAccount(ArrayList<Account> accounts) {
		this.listAccount = accounts;
	} 

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
