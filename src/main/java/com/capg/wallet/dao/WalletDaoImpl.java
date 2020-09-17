package com.capg.wallet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capg.wallet.entity.BankBean;
import com.capg.wallet.entity.History;
@Repository
@Transactional
public class WalletDaoImpl implements WalletDao {

	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public BankBean addAccount(BankBean bean) {
		
		 em.persist(bean);
		 
		 return bean;
		 
	}

	@Override
	public BankBean getBalance(int id) {
		
		return em.find(BankBean.class, id);
	}

	@Override
	public BankBean deposit(int id, double amount) {
		BankBean bean = em.find(BankBean.class, id);
		try {
		bean.setAmount(amount+bean.getAmount());
		}
		catch(Exception e) {
			return null;
		}
		History history = new History("Deposited", id, amount);
		
		em.persist(history);
		
		return em.merge(bean);
	}

	@Override
	public BankBean withdraw(int id, double amount) {
		BankBean bean = em.find(BankBean.class, id);
		bean.setAmount(bean.getAmount()-amount);
		
	History history = new History("Withdrawn", id, amount);
		
		em.persist(history);
		
		return em.merge(bean);
	}

	@Override
	public BankBean fundTransfer(int id1, int id2, double amount) {
		BankBean bean = em.find(BankBean.class, id1);
		bean.setAmount(bean.getAmount()-amount);
		System.out.println(bean);
		em.persist(bean);
		
		History history = new History("FundTransfer To "+id2, id1, amount);
		em.persist(history);
		
		BankBean bean1 = em.find(BankBean.class, id2);
		bean1.setAmount(bean1.getAmount()+amount);
		System.out.println(bean1);
		em.persist(bean1);
		
		
		History history1 = new History("FundTransfer From "+id1, id2, amount);
		
		em.persist(history1);
		
		return bean;
	}


	
	@Override
	public List<BankBean> getAll() {
		TypedQuery<BankBean> query = em.createQuery("from BankBean", BankBean.class);
		return query.getResultList();
	}

	@Override
	public List<History> printTransactions(int id) {
		TypedQuery<History> query = em.createQuery("select h from History h where h.acc_id="+id, History.class);
		return query.getResultList();
	}

}
