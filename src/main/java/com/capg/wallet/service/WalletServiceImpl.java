package com.capg.wallet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.wallet.dao.WalletDao;
import com.capg.wallet.entity.BankBean;
import com.capg.wallet.entity.History;

@Service
public class WalletServiceImpl implements WalletService {
	
	@Autowired
	WalletDao dao;
	
	@Override
	public BankBean addAccount(BankBean bean) {
		return dao.addAccount(bean);
	}

	@Override
	public BankBean getBalance(int id) {
		return dao.getBalance(id);
	}

	@Override
	public BankBean deposit(int id, double amount) {
		return dao.deposit(id, amount);
	}

	@Override
	public BankBean withdraw(int id, double amount) {

		return dao.withdraw(id, amount);
	}

	@Override
	public BankBean fundTransfer(int id1, int id2, double amount) {
		return dao.fundTransfer(id1, id2, amount);
	}

	@Override
	public List<History> printTransactions(int id) {
		return dao.printTransactions(id);
	}

	public List<BankBean> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

}
