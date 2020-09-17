package com.capg.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capg.wallet.entity.BankBean;
import com.capg.wallet.entity.History;
import com.capg.wallet.service.WalletServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class WalletRestController {

	@Autowired
	WalletServiceImpl bsi;

	@PostMapping("/wallet/create")
	public String createAccount(@RequestBody BankBean bean) {
		BankBean b = bsi.addAccount(bean);
		return  "Hello " + b.getCustomer_name() + "\n Your Registration is Successfull." + "\n Your Account Id is "
				+ b.getAccount_id();
	}

	@GetMapping(value="/wallet/showBalance/{id}", produces = "application/text")
	public String showBalance(@PathVariable int id) throws Exception {

		BankBean b = bsi.getBalance(id);

		if (b == null) {
			throw new Exception("Invalid id");
		}
		return "Your Account Balance is " + b.getAmount();
	}

		

	@GetMapping("/wallet/deposit/{id}/{amount}")
	public String deposit(@PathVariable int id, @PathVariable double amount) throws Exception {
		BankBean b = bsi.deposit(id, amount);

		if (b == null) {
			throw new Exception("Invalid id");
		}
		return "Hello " + b.getCustomer_name() + "\n Your Amount is Deposited Succesfully."
				+ "\n Your Current Account Balance is  " + b.getAmount();

	}

	@GetMapping("/wallet/withdraw/{id}/{amount}")
	public String withdraw(@PathVariable int id, @PathVariable double amount) throws Exception {
		BankBean b = bsi.withdraw(id, amount);

		if (b == null) {
			throw new Exception("Invalid id");
		}
		return "Hello " + b.getCustomer_name() + "\n Your Amount is Withdrawn Succesfully."
		+ "\n Your Current Account Balance is " + b.getAmount();
		

	}

	@GetMapping("/wallet/fundtransfer/{id1}/{id2}/{amount}")
	public String deposit(@PathVariable int id1, @PathVariable int id2, @PathVariable double amount) throws Exception {
		BankBean b = bsi.fundTransfer(id1, id2, amount);

		if (b == null) {
			throw new Exception("Invalid id");
		}
		return "Hello " + b.getCustomer_name() + "\n Your Amount is Transfered Succesfully."
		+ "\n Your Current Account Balance is " + b.getAmount();
	}

	
	
	@GetMapping("/wallet/transactions/{id}")
	public List<History> transactions(@PathVariable int id) throws Exception {

		if (bsi.printTransactions(id) == null) {
			throw new Exception("Invalid id");
		}

		return bsi.printTransactions(id);
	}


	@GetMapping("/wallet/findall")
	public List<BankBean> getall() {

		List<BankBean> bean = bsi.getAll();
		return bean;
	}

	@ExceptionHandler(Exception.class)
	public String inValid(Exception e) {
		return e.getMessage();
	}

}
