package com.capg.wallet.service;

import java.util.List;

import com.capg.wallet.entity.BankBean;
import com.capg.wallet.entity.History;

public interface WalletService {

	
	public BankBean addAccount(BankBean bean);
	
	public BankBean getBalance(int id);
	
	public BankBean deposit(int id, double amount);
	
	public BankBean withdraw(int id, double amount);
	
	public  BankBean fundTransfer(int id1, int id2, double amount);
	
	public List<History> printTransactions(int id);
	
}
