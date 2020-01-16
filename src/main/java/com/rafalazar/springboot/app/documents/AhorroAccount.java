package com.rafalazar.springboot.app.documents;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="ahorro_account")
public class AhorroAccount {
	
	private String id;
	private String numAccount;
	private String nameBank;
	private double total;
	private String status;
	private Date createAt;
	private Date updateAt;
	
	public AhorroAccount() {
		
	}
	
	public AhorroAccount(String numAccount, String nameBank, double total, String status) {
		this.numAccount = numAccount;
		this.nameBank = nameBank;
		this.total = total;
		this.status = status;
	}

}
