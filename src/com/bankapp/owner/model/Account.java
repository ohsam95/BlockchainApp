package com.bankapp.owner.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

	private int id;
	private String name;
	private String pwd;
	private String phone;
	private int amount;
	
}
