package com.bankapp.owner.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountLog {

	private int id;
	private String send;
	private String receive;
	private int money;
	private Timestamp logTime;
	
}
